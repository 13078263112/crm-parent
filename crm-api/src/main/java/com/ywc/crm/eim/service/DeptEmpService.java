package com.ywc.crm.eim.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.crm.eim.entity.DeptEmp;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-26
 */
public interface DeptEmpService extends IService<DeptEmp> {

    int add(DeptEmp deptEmp);

    int updateDeptEmp(DeptEmp deptEmp);

}
