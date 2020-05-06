package com.ywc.eim.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.crm.eim.entity.Department;
import com.ywc.crm.eim.service.DepartmentService;
import com.ywc.eim.mapper.DepartmentMapper;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Override
    public List<Department> lists() {

        return baseMapper.selectList(new QueryWrapper());
    }

    @Override
    public Department queryById(Integer deptId) {
        return baseMapper.selectById(deptId);
    }

    @Override
    public int updateRole(Department department) {
        return baseMapper.updateById(department);
    }

    @Override
    public int deleteById(Integer deptId, Integer isDel) {
        Department department = new Department();
        department.setDeptId(deptId);
        department.setIsDel(isDel);
        return baseMapper.updateById(department);
    }

    @Override
    public Department addDept(Department department) {
        int insert = baseMapper.insert(department);
        return department;
    }
}
