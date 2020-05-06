package com.ywc.crm.eim.service;

import com.ywc.crm.eim.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.crm.eim.entity.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-23
 */
public interface DepartmentService extends IService<Department> {

    List<Department> lists();


    Department queryById(Integer deptId);

    int updateRole(Department department);

    int deleteById(Integer deptId, Integer isDel);

    Department addDept(Department department);
}
