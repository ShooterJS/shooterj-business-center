package compelatefutrue;

import com.google.common.collect.Lists;
import com.shooterj.common.util.TransactionUtil;
import com.shooterj.sys.domain.model.item.Item;
import com.shooterj.sys.domain.service.futrue.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: ShooterJ
 * @description
 * @date: 2023/6/27 20:10
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
@Slf4j
@Component
public class MultiBatchSaveTest {

    @Autowired
    TransactionUtil transactionUtil;
    @Resource
    ItemService itemService;

    /**
     * 多线程批量插入
     * 全回滚
     */
    @Test
    public void multithreadBatchInsert() {
        int nThreads = 2;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(nThreads);
        AtomicReference<Boolean> rollback = new AtomicReference(false);
        //制造一批数据
        List<Item> list = prepareData();
        int size = list.size();
        //分批分割数据
        int batchSize = (int) Math.ceil((double) size / nThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < nThreads; i++) {
            int startIndex = batchSize * i;
            int endIndex = Math.min(startIndex + nThreads, size);
            List<Item> items = list.subList(startIndex, endIndex);
            executorService.execute(() -> {
                //开启事务
                TransactionStatus transactionStatus = transactionUtil.begin();
                //保存数据,给回滚标记
                try {
                    itemService.saveBatch(items);
                    throw new RuntimeException("数据插入失败");
                } catch (Exception e) {
                    log.error("数据插入失败");
                    rollback.set(true);
                }

                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(rollback.get()){
                    transactionUtil.rollback(transactionStatus);
                    return;
                }

                transactionUtil.commit(transactionStatus);
            });
        }
        executorService.shutdown();
    }

    private List<Item> prepareData() {
        Item item1 = Item.builder().age(30).name("钟馗").build();
        Item item2 = Item.builder().age(10).name("孙悟空").build();
        Item item3 = Item.builder().age(40).name("阿珂").build();
        Item item4 = Item.builder().age(20).name("孙尚香").build();
        ArrayList<Item> items = Lists.newArrayList(item1, item2, item3, item4);
        return items;
    }
}
