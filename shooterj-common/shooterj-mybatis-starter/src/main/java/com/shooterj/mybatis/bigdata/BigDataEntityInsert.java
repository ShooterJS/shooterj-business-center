package com.shooterj.mybatis.bigdata;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public abstract class BigDataEntityInsert<T> {
    int groupCount = 10000;
    int threadPoolCount = 4;
    // 创建一个固定大小的线程池
    private ExecutorService service = null;

    BaseMapper baseMapper;

    IService iService;

    public void saveBatch(List<T> list) {

        service = Executors.newFixedThreadPool(threadPoolCount);
        // 将需保存集合分组
        List<List<T>> listList = new ArrayList<>();
        if (list.size() > groupCount) {
            listList = fixedGrouping(list, groupCount);
        } else {
            listList.add(list);
        }
        // 计数器
        final CountDownLatch latch = new CountDownLatch(listList.size());
        //开始总计时
        long bTime1 = System.currentTimeMillis();

        for (int i = 0; i < listList.size(); i++) {
            int finalI = i;
            List<List<T>> finalListList = listList;
            try {
                // 多线程保存
                service.execute(() -> {
                    //baseMapper.saveBatch(finalListList.get(finalI));
                    iService.saveBatch(finalListList.get(finalI));
                });
            } catch (Exception e) {
                log.error("批量保存失败！", e);
            } finally {
                latch.countDown();
            }
        }

        try {
            latch.await();
        } catch (Exception e) {
            log.error("多线程分析数据中途异常!,{}", e);
        }
        //关闭总计时
        long eTime1 = System.currentTimeMillis();
        System.out.println("插入" + list.size() + "数据共耗时：" + (eTime1 - bTime1));
    }

    /**
     * 将一组数据固定分组，每组n个元素
     * @param source 要分组的数据源
     * @param n      每组n个元素
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> fixedGrouping(List<T> source, int n) {

        if (null == source || source.size() == 0 || n <= 0)
            return null;
        List<List<T>> result = new ArrayList<List<T>>();
        int remainder = source.size() % n;
        int size = (source.size() / n);
        for (int i = 0; i < size; i++) {
            List<T> subset = null;
            subset = source.subList(i * n, (i + 1) * n);
            result.add(subset);
        }
        if (remainder > 0) {
            List<T> subset = null;
            subset = source.subList(size * n, size * n + remainder);
            result.add(subset);
        }
        return result;
    }

    protected abstract void saveEntity(List<T> finalListList);
}
