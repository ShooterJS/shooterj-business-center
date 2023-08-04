package com.shooterj.sys.domain.service.futrue;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shooterj.sys.domain.model.item.Item;
import com.shooterj.sys.infrastructure.persistence.mapper.ItemMapper;
import org.springframework.stereotype.Service;

/**
 * @author: ShooterJ
 * @date: 2023/6/27
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper,Item> implements ItemService{

}
