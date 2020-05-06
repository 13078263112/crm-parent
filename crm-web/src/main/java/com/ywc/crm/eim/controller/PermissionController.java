package com.ywc.crm.eim.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ywc.crm.eim.entity.Department;
import com.ywc.crm.eim.entity.Permission;
import com.ywc.crm.eim.entity.Role;
import com.ywc.crm.eim.entity.RolePermission;
import com.ywc.crm.eim.service.PermissionService;
import com.ywc.crm.eim.service.RolePermissionService;
import com.ywc.crm.eim.service.RoleService;
import com.ywc.crm.eim.vo.RolePermissionVo;
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
 * @date 2020/3/29 15:02
 */
@Api(tags = "PermissionController", description = "权限管理")
@Controller
public class PermissionController {
    @Reference
    PermissionService permissionService;
    @Reference
    RoleService roleService;
    @Reference
    RolePermissionService rolePermissionService;

    @ApiOperation(value = "部门信息列表")
    @GetMapping("/permLists")
    public String permLists(Model model, Integer pageNum){
        PageHelper.startPage(pageNum, 10);
        //startPage后紧跟的这个查询就是分页查询
        List<RolePermissionVo> lists = permissionService.lists();
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo<RolePermissionVo> rolePageInfo = new PageInfo<>(lists);
        model.addAttribute("lists",rolePageInfo);
        return "eim/perm/list";
    }
    @ApiOperation(value = "角色停用或启也")
    @DeleteMapping("/perm")
    @ResponseBody
    public void permLists( Integer permId,Integer isDel){
        permissionService.deleteById(permId,isDel);
    }
    @ApiOperation(value = "回显权限信息")
    @GetMapping("/updatePerm/{permId}")
    public String echoUser(@PathVariable("permId") Integer permId, Model model){
        model.addAttribute("RermList",permissionService.queryById(permId));
        model.addAttribute("RoleList",roleService.lists());
        return "eim/perm/update";

    }
    @ApiOperation(value = "修改权限信息")
    @PostMapping(value = "/updatePerm")
    @ResponseBody
    public Map updateEmp(@RequestBody Map<String,String> map){
        Map<String,Object> map1 = new HashMap<String,Object>();
        Permission permission = new Permission();
        permission.setPermId(Integer.valueOf(map.get("permId")));
        permission.setPermName(map.get("permName"));
        permission.setUrl(map.get("url"));
        permission.setPermission(map.get("permission"));
        permission.setUpdateTime(new Date());
        permissionService.updatePerm(permission);
        rolePermissionService.deleteById(Integer.valueOf(map.get("permId")));
        List<Role> lists = roleService.lists();
        for (Role list : lists) {
            String roleId="roleId"+list.getRoleId();
            if (map.containsKey(roleId)){
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(Integer.valueOf(map.get(roleId)));
                rolePermission.setPermId(Integer.valueOf(map.get("permId")));
                rolePermission.setCreateTime(new Date());
                rolePermission.setUpdateTime(new Date());
                rolePermission.setIsDel(0);
                rolePermissionService.add(rolePermission);
            }

        }
        map1.put("msg","修改成功");
        return map1;
    }
    @ApiOperation(value = "跳转添加页面")
    @GetMapping("/addPerm")
    public String addEmp(Model model){
        model.addAttribute("RoleList",roleService.lists());
        return "eim/perm/addPerm";
    }
    @ApiOperation(value = "添加角色信息")
    @PostMapping("/addPerm")
    @ResponseBody
    public Map addPerm(@RequestBody Map<String,String> map) {
        System.out.println(map);
        Map<String,Object> map1 = new HashMap<String,Object>();
        Permission permission = new Permission();
        permission.setPermName(map.get("permName"));
        permission.setUrl(map.get("url"));
        permission.setPermission(map.get("permission"));
        permission.setUpdateTime(new Date());
        permission.setCreateTime(new Date());
        permission.setIsDel(0);
        Permission permission1 = permissionService.addPerm(permission);
        List<Role> lists = roleService.lists();
        for (Role list : lists) {
            String roleId="roleId"+list.getRoleId();
            if (map.containsKey(roleId)){
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(Integer.valueOf(map.get(roleId)));
                rolePermission.setPermId(permission1.getPermId());
                rolePermission.setCreateTime(new Date());
                rolePermission.setUpdateTime(new Date());
                rolePermission.setIsDel(0);
                rolePermissionService.add(rolePermission);
            }

        }
        map1.put("msg","添加成功");
        return map1;
    }
}
