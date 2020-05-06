package com.ywc.eim.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.crm.eim.entity.Permission;
import com.ywc.crm.eim.service.PermissionService;
import com.ywc.crm.eim.vo.RolePermissionVo;
import com.ywc.eim.mapper.PermissionMapper;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public List<RolePermissionVo> lists() {
        return baseMapper.lists();
    }

    @Override
    public int deleteById(Integer roleId, Integer isDel) {
        Permission permission = new Permission();
        permission.setPermId(roleId);
        permission.setIsDel(isDel);
        return baseMapper.updateById(permission);
    }

    @Override
    public RolePermissionVo queryById(int premId) {
        return baseMapper.queryById(premId);
    }

    @Override
    public int updatePerm(Permission permission) {
        return baseMapper.updateById(permission);
    }

    @Override
    public Permission addPerm(Permission permission) {
        int insert = baseMapper.insert(permission);
        return permission;
    }

    @Override
    public List<Permission> queryAll() {
        QueryWrapper<Permission> Permission = new QueryWrapper<Permission>();
        return baseMapper.selectList(Permission);
    }
}
