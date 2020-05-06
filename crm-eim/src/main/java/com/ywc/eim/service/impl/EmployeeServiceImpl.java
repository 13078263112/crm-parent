package com.ywc.eim.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ywc.crm.eim.entity.Employee;
import com.ywc.crm.eim.service.EmployeeService;
import com.ywc.crm.eim.vo.EmpRoleVo;
import com.ywc.eim.mapper.EmployeeMapper;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Override
    public Employee selectByName(String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("emp_name",name);
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public PageInfo<EmpRoleVo> lists(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //startPage后紧跟的这个查询就是分页查询
        List<EmpRoleVo> empRoleVoList = baseMapper.lists();
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以

        return  new PageInfo<>(empRoleVoList);
    }

    @Override
    public int deleteById(Integer empId, Integer isDel) {
       Employee employee=new Employee();
        employee.setEmpId(empId);
        employee.setIsDel(isDel);
        return baseMapper.updateById(employee);
    }

    @Override
    public int updateEmp(Employee employee) {
        return baseMapper.updateById(employee);
    }

    @Override
    public Employee addEmp(Employee employee) {
        int insert = baseMapper.insert(employee);
        return employee;
    }

    @Override
    public EmpRoleVo queryById(Integer empId) {
        return baseMapper.queryById(empId);
    }

    @Override
    public List<Employee> queryAll() {
        QueryWrapper queryWrapper = new QueryWrapper();
        return baseMapper.selectList(queryWrapper);
    }
}
