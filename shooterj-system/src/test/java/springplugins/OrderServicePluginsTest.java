package springplugins;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.plugin.core.config.EnablePluginRegistries;

/**
 * @author: ShooterJ
 * @description 下单测试类
 * @date: 2023/7/3 13:16
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@EnablePluginRegistries(value = {IOrderService.class})
public class OrderServicePluginsTest {

    @Autowired
    private PluginRegistry<IOrderService, OrderInfo> registry;

    @Test
    private void testOrderService() {
        OrderInfo info = new OrderInfo();
        info.setUserType("General");
        registry.getPluginFor(info).ifPresent(o -> o.order(info));
    }


}
