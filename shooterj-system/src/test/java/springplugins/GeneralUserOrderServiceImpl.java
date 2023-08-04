package springplugins;

import org.springframework.stereotype.Service;

/**
 * @author: ShooterJ
 * @description 普通用户下单流程
 * @date: 2023/7/3 13:16
 */
@Service
public class GeneralUserOrderServiceImpl implements IOrderService{

    @Override
    public String order(OrderInfo orderInfo) {
        System.out.println("普通 用户下单");
        return null;
    }

    @Override
    public boolean supports(OrderInfo orderInfo) {
        return "General".equals(orderInfo.getUserType());
    }
}
