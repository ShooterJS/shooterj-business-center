package com.shooterj.sys.domain.customer.event;

import com.shooterj.sys.domain.customer.Customer;
import lombok.Value;

public interface CustomerEvent {


    @Value
    class CustomerUpdateEvent{
        private Customer customer;
    }
}
