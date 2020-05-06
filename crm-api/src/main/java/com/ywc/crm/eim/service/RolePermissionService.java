package com.ywc.crm.eim.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.crm.eim.entity.Role;
import com.ywc.crm.eim.entity.RolePermission;
import com.ywc.crm.eim.vo.RolePermissionVo;
import org.omg.CORBA.INTERNAL;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-25
 */
public interface RolePermissionService extends IService<RolePermission> {

    int deleteById(Integer permId);

    int add(RolePermission rolePermission);

    List<RolePermission> queryByEmpId(Integer roleId);
}
