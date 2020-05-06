package com.ywc.eim.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.crm.eim.entity.Permission;
import com.ywc.crm.eim.entity.RolePermission;
import com.ywc.crm.eim.service.RolePermissionService;
import com.ywc.eim.mapper.RolePermissionMapper;
import org.springframework.stereotype.Component;

import java.util.List;


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
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Override
    public int deleteById(Integer permId) {
        return baseMapper.deleteById(permId);
    }

    @Override
    public int add(RolePermission rolePermission) {
        return baseMapper.insert(rolePermission);
    }

    @Override
    public List<RolePermission> queryByEmpId(Integer roleId) {
        QueryWrapper<RolePermission> permission = new QueryWrapper<RolePermission>();
        permission.eq("role_id",roleId);
        return baseMapper.selectList(permission);
    }
}
