package com.ywc.eim.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ywc.crm.eim.entity.Customer;
import com.ywc.crm.eim.service.CustomerService;
import com.ywc.crm.eim.vo.CustomerVo;
import com.ywc.crm.eim.vo.EmpRoleVo;
import com.ywc.eim.mapper.CustomerMapper;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-30
 */
@Service
@Component
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {



    @Override
    public PageInfo<CustomerVo> lists(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //startPage后紧跟的这个查询就是分页查询
        List<CustomerVo> lists = baseMapper.lists();
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        return new PageInfo<>(lists);
    }

    @Override
    public int deleteById(Customer customer) {

        return baseMapper.updateById(customer);
    }

    @Override
    public Customer queryById(Integer customerId) {
        return baseMapper.selectById(customerId);
    }

    @Override
    public int addClient(Customer customer) {
        return baseMapper.insert(customer);
    }
}
