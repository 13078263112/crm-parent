package com.ywc.crm.eim.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.crm.eim.entity.DeptEmp;
import com.ywc.crm.eim.entity.EmpRole;
import com.ywc.crm.eim.entity.Employee;
import com.ywc.crm.eim.service.*;
import com.ywc.crm.eim.utils.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author 嘟嘟~
 * @date 2020/3/23 17:19
 */
@Api(tags = "EimController", description = "用户管理")
@Controller
public class EimController {

    @Reference
    EmployeeService employeeService;
    @Reference
    RoleService roleService;
    @Reference
    DepartmentService departmentService;
    @Reference
    EmpRoleService empRoleService;
    @Reference
    DeptEmpService deptEmpService;
    @ApiOperation(value = "登录以后跳转主页")
    @PostMapping("/login")
    public String login(String name, String pwd, Model model){
        Subject subject = SecurityUtils.getSubject();
        //将用户名和密码包装到token中  （加密密码）
        UsernamePasswordToken token = new UsernamePasswordToken(name,pwd);
        //通过捕获异常来判断用户是否是否可以登录
        try{
            subject.login(token);
        }catch (UnknownAccountException uae){
            model.addAttribute("msg","账号错误或账号停用");
            return "login";
        }catch (IncorrectCredentialsException ice){
            model.addAttribute("msg","密码错误");
            return "login";
        }
        return "redirect:/";
    }

    @ApiOperation(value = "没有登录跳转登录页面")
    @GetMapping("/toLogin")
    public String tologin(){
        return "login";
    }

    @ApiOperation(value = "退出功能")
    @GetMapping("/logout")
    public String logout(){
        return "login";
    }

    @ApiOperation(value = "主页数据")
    @GetMapping("/")
    public String index(Model model){
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("msg",employee.getEmpName());
        return "index";
    }
    @ApiOperation(value = "员工信息列表")
    @GetMapping("/empLists")
    public String empLists(Model model, Integer pageNum){
        model.addAttribute("lists",employeeService.lists(pageNum, 10));
        return "eim/emp/list";
    }
    @ApiOperation(value = "模糊查询员工信息列表")
    @PostMapping("/empListsDim")
    @ResponseBody
    public String empListsDim(@RequestParam Map<String,String> map,Model model){
        System.out.println(map);
        //model.addAttribute("list",employeeService.lists(pageNum, 10));
        return "eim/emp/list";
    }

    @ApiOperation(value = "员工账号停用或启也")
    @DeleteMapping("/emp")
    @ResponseBody
    public void empLists( Integer empId,Integer isDel){
        employeeService.deleteById(empId,isDel);
    }

    @ApiOperation(value = "回显个人信息")
    @GetMapping("/updateEmp")
    public String echoEmp(Model model){
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("employee1",employeeService.selectByName(employee.getEmpName()));
        return "updateEmp";
    }
    @ApiOperation(value = "修改个人信息")
    @PostMapping(value = "/updateEmp")
    @ResponseBody
    public Map updateEmp(@RequestBody Employee employee){
        Map<String,Object> map = new HashMap<String,Object>();
        employee.setSalt(ShiroUtils.randomSalt());
        employee.setPwd(new SimpleHash("md5", employee.getPwd(), employee.getSalt(), 1024).toString());
        employee.setUpdateTime(new Date());
        int i =employeeService.updateEmp(employee);
        if (i>0){map.put("msg","修改成功");}
        return map;
    }
    @ApiOperation(value = "回显用户信息")
    @GetMapping("/updateUser/{empId}")
    public String echoUser(@PathVariable("empId") Integer empId, Model model){
        model.addAttribute("RoleList",roleService.lists());
        model.addAttribute("DepartmentList",departmentService.lists());
        model.addAttribute("employee1",employeeService.queryById(empId));
        return "eim/emp/update";
    }
    @ApiOperation(value = "修改用户信息")
    @PostMapping(value = "/updateUser")
    @ResponseBody
    public Map updateEmp(@RequestBody Map<String,String> map){
        Map<String,Object> map1 = new HashMap<String,Object>();
        Employee employee = new Employee();
        employee.setEmpId(Integer.valueOf(map.get("empId")));
        employee.setEmpName(map.get("empName"));
        employee.setAge(Integer.valueOf(map.get("age")));
        employee.setSex(map.get("sex"));
        employee.setPhone(map.get("phone"));
        employee.setAddress(map.get("address"));
        employee.setUpdateTime(new Date());
        employeeService.updateEmp(employee);

        EmpRole empRole = new EmpRole();
        empRole.setEmpId(Integer.valueOf(map.get("empId")));
        empRole.setRoleId(Integer.valueOf(map.get("roleId")));
        empRole.setUpdateTime(new Date());
        empRoleService.updateEmpRole(empRole);

        DeptEmp deptEmp = new DeptEmp();
        deptEmp.setEmpId(Integer.valueOf(map.get("empId")));
        deptEmp.setDeptId(Integer.valueOf(map.get("deptId")));
        deptEmp.setUpdateTime(new Date());
        deptEmpService.updateDeptEmp(deptEmp);
        map1.put("msg","修改成功");
        return map1;
    }
    @ApiOperation(value = "添加个人信息")
    @PostMapping(value = "/addEmp")
    @ResponseBody
    public Map addEmp(@RequestBody Map<String,String> map) {
        System.out.println(map);

        Employee employee = new Employee();
        employee.setEmpName(map.get("empName"));
        employee.setAge(Integer.valueOf(map.get("age")));
        employee.setSex(map.get("sex"));
        employee.setPhone(map.get("phone"));
        employee.setSalt(ShiroUtils.randomSalt());
        employee.setAddress(map.get("address"));
        employee.setPwd(new SimpleHash("md5",map.get("pwd"), employee.getSalt(), 1024).toString());
        employee.setCreateTime(new Date());
        employee.setUpdateTime(new Date());
        employee.setIsDel(0);
        Employee employee1 = employeeService.addEmp(employee);

        EmpRole empRole = new EmpRole();
        empRole.setEmpId(employee1.getEmpId());
        empRole.setRoleId(Integer.valueOf(map.get("roleId")));
        empRole.setCreateTime(new Date());
        empRole.setUpdateTime(new Date());
        empRole.setIsDel(0);
        empRoleService.add(empRole);

        DeptEmp deptEmp = new DeptEmp();
        deptEmp.setEmpId(employee1.getEmpId());
        deptEmp.setDeptId(Integer.valueOf(map.get("deptId")));
        deptEmp.setCreateTime(new Date());
        deptEmp.setUpdateTime(new Date());
        deptEmp.setIsDel(0);
        int i = deptEmpService.add(deptEmp);
        Map<String,Object> map1 = new HashMap<String,Object>();
        if (i>0){map1.put("msg","添加成功");}
        return map1;
    }
    @ApiOperation(value = "跳转添加页面")
    @GetMapping("/addEmp")
    public String addEmp( Model model){
        model.addAttribute("RoleList",roleService.lists());
        model.addAttribute("DepartmentList",departmentService.lists());
        return "eim/emp/addEmp";
    }
}
