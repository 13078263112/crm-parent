package com.ywc.crm.eim.service;

import com.ywc.crm.eim.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.crm.eim.vo.RolePermissionVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-23
 */
public interface PermissionService extends IService<Permission> {
    List<RolePermissionVo> lists();

    int deleteById(Integer roleId, Integer isDel);

    RolePermissionVo queryById(int premId);

    int updatePerm(Permission permission);

    Permission addPerm(Permission permission);

    List<Permission> queryAll();
}
