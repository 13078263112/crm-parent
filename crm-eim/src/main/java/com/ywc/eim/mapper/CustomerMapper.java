package com.ywc.eim.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ywc.crm.eim.entity.Customer;
import com.ywc.crm.eim.vo.CustomerVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-30
 */
public interface CustomerMapper extends BaseMapper<Customer> {
        List<CustomerVo> lists();

}
