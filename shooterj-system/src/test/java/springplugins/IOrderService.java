package springplugins;

import org.springframework.plugin.core.Plugin;

/**
 * @author: ShooterJ
 */
public interface IOrderService extends Plugin<OrderInfo> {

     String order(OrderInfo orderInfo);
}
