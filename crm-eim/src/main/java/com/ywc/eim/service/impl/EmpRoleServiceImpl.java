package com.ywc.eim.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.crm.eim.entity.EmpRole;
import com.ywc.crm.eim.service.EmpRoleService;
import com.ywc.eim.mapper.EmpRoleMapper;
import org.springframework.stereotype.Component;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-25
 */
@Service
@Component
public class EmpRoleServiceImpl extends ServiceImpl<EmpRoleMapper, EmpRole> implements EmpRoleService {

    @Override
    public EmpRole add(EmpRole empRole) {
        int insert = baseMapper.insert(empRole);
        return empRole;
    }

    @Override
    public int updateEmpRole(EmpRole empRole) {
        return baseMapper.updateById(empRole);
    }

    @Override
    public EmpRole queryByEmpId(Integer empId) {

        return baseMapper.selectById(empId);
    }


}
