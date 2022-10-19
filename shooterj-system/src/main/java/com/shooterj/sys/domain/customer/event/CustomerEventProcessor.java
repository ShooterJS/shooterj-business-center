package com.shooterj.sys.domain.customer.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class CustomerEventProcessor {

   /* @EventListener
    public void handleCustomerUpdateEvent(CustomerEvent.CustomerUpdateEvent event){
        System.out.println("收到消息");
        System.out.println(event.getCustomer().getId());
        System.out.println(event.getCustomer().getGrade());
        System.out.println(event.getCustomer().getUsername());
    }*/


    /**
     * 有事务问题
     */
    @EventListener
    public void handleCustomerUpdateEventForException(CustomerEvent.CustomerUpdateEvent event){
        System.out.println("异常了");
        throw new RuntimeException();
    }

}
