package com.ywc.crm.eim.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ywc.crm.eim.entity.Customer;
import com.ywc.crm.eim.vo.CustomerVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-30
 */
public interface CustomerService extends IService<Customer> {

    PageInfo<CustomerVo> lists(Integer pageNum, Integer pageSize);

    int deleteById(Customer customer);


    Customer queryById(Integer customerId);

    int addClient(Customer customer);
}
