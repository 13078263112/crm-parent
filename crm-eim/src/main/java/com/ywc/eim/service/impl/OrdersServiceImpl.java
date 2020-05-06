package com.ywc.eim.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.crm.eim.entity.Orders;
import com.ywc.crm.eim.service.OrdersService;
import com.ywc.eim.mapper.OrdersMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-30
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

}
