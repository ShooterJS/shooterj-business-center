package com.shooterj.sys.domain.customer.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shooterj.mybatis.support.EntityOperations;
import com.shooterj.sys.domain.customer.Customer;
import com.shooterj.sys.domain.customer.CustomerDTO;
import com.shooterj.sys.domain.customer.CustomerUpdater;
import com.shooterj.sys.domain.customer.event.CustomerEvent;
import com.shooterj.sys.domain.customer.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    /**
     * 创建实体
     * @param dto
     * @return
     */
    @Override
    public Long createCustomer(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setGrade(dto.getGrade());
        customer.setUsername(dto.getUsername());

        EntityOperations
                .doCreate(getBaseMapper())
                .create(() -> customer)
                .update(e -> e.init())
                .execute();
        return null;
    }

    /**
     * 更新实体
     * @param updater
     */
    @Override
    public void updateCustomer(CustomerUpdater updater) {
        EntityOperations
                .doUpdate(getBaseMapper())
                .load(() -> getById(updater.getId()))
                .update(e -> e.doUpdate(updater))
                .successHook(e -> eventPublisher.publishEvent(new CustomerEvent.CustomerUpdateEvent(e)))
                .execute();
    }

}
