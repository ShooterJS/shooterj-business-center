package com.shooterj.sys.domain.customer_.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shooterj.sys.domain.customer_.Customer;
import com.shooterj.sys.domain.customer_.CustomerDTO;
import com.shooterj.sys.domain.customer_.CustomerUpdater;

public interface ICustomerService extends IService<Customer> {

    Long createCustomer(CustomerDTO dto);

    void updateCustomer(CustomerUpdater updater);

}
