package com.ywc.crm.eim.service;

import com.github.pagehelper.PageInfo;
import com.ywc.crm.eim.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.crm.eim.vo.EmpRoleVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-23
 */
public interface EmployeeService extends IService<Employee> {

    Employee selectByName(String name);

    PageInfo<EmpRoleVo> lists(Integer pageNum, Integer pageSize);

    int deleteById(Integer empId, Integer isDel);

    int updateEmp(Employee employee);

    Employee addEmp(Employee employee);

    EmpRoleVo queryById(Integer empId);

    List<Employee> queryAll();
}
