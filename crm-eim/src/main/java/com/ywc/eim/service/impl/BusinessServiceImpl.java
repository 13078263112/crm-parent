package com.ywc.eim.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.crm.eim.entity.Business;
import com.ywc.crm.eim.service.BusinessService;
import com.ywc.eim.mapper.BusinessMapper;
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
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {

}
