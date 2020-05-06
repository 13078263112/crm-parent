package com.ywc.crm.eim.service;

import com.ywc.crm.eim.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-23
 */
public interface RoleService extends IService<Role> {

    List<Role> lists();

    Role queryById(Integer empId);

    int updateRole(Role role);

    int deleteById(Integer empId, Integer isDel);

    Role addRole(Role role);
}
