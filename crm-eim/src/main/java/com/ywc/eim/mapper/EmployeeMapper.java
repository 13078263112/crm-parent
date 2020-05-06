package com.ywc.eim.mapper;

import com.ywc.crm.eim.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ywc.crm.eim.vo.EmpRoleVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-23
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    List<EmpRoleVo> lists();

    EmpRoleVo queryById(Integer empId);
}
