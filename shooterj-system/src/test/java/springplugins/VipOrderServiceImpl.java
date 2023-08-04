package springplugins;

import org.springframework.stereotype.Service;

/**
 * @author: ShooterJ
 * @description VIP下单流程
 * @date: 2023/7/3 13:15
 */
@Service
public class VipOrderServiceImpl implements IOrderService{
    @Override
    public String order(OrderInfo orderInfo) {
        System.out.println("vip 用户下单");
        return null;
    }

    @Override
    public boolean supports(OrderInfo orderInfo) {
        return "VIP".equals(orderInfo.getUserType());
    }
}
