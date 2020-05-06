package com.ywc.crm.eim.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ywc.crm.eim.entity.Department;
import com.ywc.crm.eim.service.DepartmentService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 嘟嘟~
 * @date 2020/3/28 16:35
 */
@Api(tags = "DeptController", description = "部门管理")
@Controller
public class DeptController {
    @Reference
    DepartmentService departmentService;

    @ApiOperation(value = "部门信息列表")
    @GetMapping("/deptLists")
    public String deptLists(Model model, Integer pageNum){
        PageHelper.startPage(pageNum, 10);
        //startPage后紧跟的这个查询就是分页查询
        List<Department> lists = departmentService.lists();
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo<Department> rolePageInfo = new PageInfo<>(lists);
        model.addAttribute("lists",rolePageInfo);
        return "eim/dept/list";
    }
    @ApiOperation(value = "回显部门信息")
    @GetMapping("/echoDept/{deptId}")
    public String echoRole(@PathVariable("deptId") Integer deptId, Model model){
        Department department = departmentService.queryById(deptId);
        model.addAttribute("department",department);
        return "eim/dept/update";
    }
    @ApiOperation(value = "修改部门信息")
    @PostMapping(value = "/updateDept")
    @ResponseBody
    public Map updateEmp(@RequestBody Map<String,String> map){
        Map<String,Object> map1 = new HashMap<String,Object>();
        Department department = new Department();
        department.setDeptId(Integer.valueOf(map.get("deptId")));
        department.setDeptName(map.get("deptName"));
        department.setCreateTime(new Date());
        departmentService.updateRole(department);
        map1.put("msg","修改成功");
        return map1;
    }

    @ApiOperation(value = "部门停用或启也")
    @DeleteMapping("/dept")
    @ResponseBody
    public void empLists( Integer deptId,Integer isDel){
        departmentService.deleteById(deptId,isDel);
    }

    @ApiOperation(value = "添加部门信息")
    @PostMapping("/addDept")
    @ResponseBody
    public Map addRole(@RequestBody Map<String,String> map) {
        Department department = new Department();
        department.setDeptName(map.get("deptName"));
        department.setCreateTime(new Date());
        department.setUpdateTime(new Date());
        department.setIsDel(0);
         departmentService.addDept(department);
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("msg","添加成功");
        return map1;
    }
    @ApiOperation(value = "跳转添加页面")
    @GetMapping("/addDept")
    public String addEmp(){
        return "eim/dept/addDept";
    }
}
