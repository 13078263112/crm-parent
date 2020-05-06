package com.ywc.eim.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.crm.eim.entity.Role;
import com.ywc.crm.eim.service.RoleService;
import com.ywc.eim.mapper.RoleMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-23
 */
@Service
@Component
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<Role> lists() {

        return baseMapper.selectList(new QueryWrapper());
    }

    @Override
    public Role queryById(Integer empId) {
        return baseMapper.selectById(empId);
    }

    @Override
    public int updateRole(Role role) {
        return baseMapper.updateById(role);
    }

    @Override
    public int deleteById(Integer roleId, Integer isDel) {
        Role role = new Role();
        role.setRoleId(roleId);
        role.setIsDel(isDel);
        return baseMapper.updateById(role);
    }

    @Override
    public Role addRole(Role role) {
        int insert = baseMapper.insert(role);
        return role;
    }
}
