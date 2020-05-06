package com.ywc.eim.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.crm.eim.entity.DeptEmp;
import com.ywc.crm.eim.service.DeptEmpService;
import com.ywc.eim.mapper.DeptEmpMapper;
import org.springframework.stereotype.Component;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-26
 */
@Service
@Component
public class DeptEmpServiceImpl extends ServiceImpl<DeptEmpMapper, DeptEmp> implements DeptEmpService {

    @Override
    public int add(DeptEmp deptEmp) {
        return baseMapper.insert(deptEmp);
    }

    @Override
    public int updateDeptEmp(DeptEmp deptEmp) {
        return baseMapper.updateById(deptEmp);
    }
}
