package com.shooterj.sys.domain.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shooterj.sys.domain.customer.Customer;
import com.shooterj.sys.domain.customer.CustomerDTO;
import com.shooterj.sys.domain.customer.CustomerUpdater;

public interface ICustomerService extends IService<Customer> {

    Long createCustomer(CustomerDTO dto);

    void updateCustomer(CustomerUpdater updater);

}
