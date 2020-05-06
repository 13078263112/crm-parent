package com.ywc.crm.eim.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ywc.crm.eim.entity.DeptEmp;
import com.ywc.crm.eim.entity.EmpRole;
import com.ywc.crm.eim.entity.Employee;
import com.ywc.crm.eim.entity.Role;
import com.ywc.crm.eim.service.RoleService;
import com.ywc.crm.eim.utils.ShiroUtils;
import com.ywc.crm.eim.vo.EmpRoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 嘟嘟~
 * @date 2020/3/28 14:13
 */
@Api(tags = "RoleController", description = "角色管理")
@Controller
public class RoleController {
    @Reference
    RoleService roleService;

    @ApiOperation(value = "角色信息列表")
    @GetMapping("/roleLists")
    public String roleLists(Model model, Integer pageNum){
        PageHelper.startPage(pageNum, 10);
        //startPage后紧跟的这个查询就是分页查询
        List<Role> lists = roleService.lists();
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo<Role> rolePageInfo = new PageInfo<>(lists);
        model.addAttribute("lists",rolePageInfo);
        return "eim/role/list";
    }
    @ApiOperation(value = "回显角色信息")
    @GetMapping("/echoRole/{empId}")
    public String echoRole(@PathVariable("empId") Integer empId, Model model){
        Role role = roleService.queryById(empId);
        model.addAttribute("role",role);
        return "eim/role/update";
    }
    @ApiOperation(value = "修改角色信息")
    @PostMapping(value = "/updateRole")
    @ResponseBody
    public Map updateEmp(@RequestBody Map<String,String> map){
        Map<String,Object> map1 = new HashMap<String,Object>();
        Role role = new Role();
        role.setRoleId(Integer.valueOf(map.get("roleId")));
        role.setRoleName(map.get("roleName"));
        role.setUpdateTime(new Date());
        roleService.updateRole(role);
        map1.put("msg","修改成功");
        return map1;
    }

    @ApiOperation(value = "角色停用或启也")
    @DeleteMapping("/role")
    @ResponseBody
    public void empLists( Integer roleId,Integer isDel){
        roleService.deleteById(roleId,isDel);
    }

    @ApiOperation(value = "添加角色信息")
    @PostMapping("/addRole")
    @ResponseBody
    public Map addRole(@RequestBody Map<String,String> map) {
        Role role = new Role();
        role.setRoleName(map.get("roleName"));
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        role.setIsDel(0);
        Role role1 = roleService.addRole(role);
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("msg","添加成功");
        return map1;
    }
    @ApiOperation(value = "跳转添加页面")
    @GetMapping("/addRole")
    public String addEmp(){
        return "eim/role/addRole";
    }
}
