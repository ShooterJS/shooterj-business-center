package com.shooterj.sys.domain.customer_.event;

import com.shooterj.sys.domain.customer_.Customer;
import lombok.Value;

public interface CustomerEvent {


    @Value
    class CustomerUpdateEvent{
        private Customer customer;
    }
}
