package com.ywc.crm.eim.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.ywc.crm.eim.entity.Customer;
import com.ywc.crm.eim.entity.Department;
import com.ywc.crm.eim.entity.Employee;
import com.ywc.crm.eim.service.CustomerService;
import com.ywc.crm.eim.service.EmployeeService;
import com.ywc.crm.eim.vo.CustomerVo;
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
 * @date 2020/3/30 21:21
 */
@Api(tags = "ClientController", description = "客户管理")
@Controller
public class ClientController {
    @Reference
    CustomerService customerService;
    @Reference
    EmployeeService employeeService;
    @ApiOperation(value = "查询所有客户信息列表")
    @GetMapping("/clientLists")
    public String clientLists(Model model, Integer pageNum){
        PageInfo<CustomerVo> lists = customerService.lists(pageNum, 10);
        model.addAttribute("lists",lists);
        return "eim/client/list";
    }
    @ApiOperation(value = "客户停用或启也")
    @DeleteMapping("/client")
    @ResponseBody
    public void clientLists( Integer customerId,Integer isDel){
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setIsDel(isDel);
        customerService.deleteById(customer);
    }
    @ApiOperation(value = "回显用户信息")
    @GetMapping("/echoClient/{customerId}")
    public String echoClient(@PathVariable("customerId") Integer customerId, Model model){
        Customer customer = customerService.queryById(customerId);
        List<Employee> employees = employeeService.queryAll();
        model.addAttribute("employees",employees);
        model.addAttribute("customer",customer);
        return "eim/client/update";
    }
    @ApiOperation(value = "修改用户信息")
    @PostMapping(value = "/updateClient")
    @ResponseBody
    public Map updateClient(@RequestBody Map<String,String> map){
        Map<String,Object> map1 = new HashMap<String,Object>();
        Customer customer = new Customer();
        customer.setCustomerId(Integer.valueOf(map.get("customerId")));
        customer.setCustomerName(map.get("customerName"));
        customer.setSex(map.get("sex"));
        customer.setTelphone(map.get("telphone"));
        customer.setCompany(map.get("company"));
        customer.setAddress(map.get("address"));
        customer.setEmpId(Integer.valueOf(map.get("empId")));
        customer.setIsOrders(Integer.valueOf(map.get("isOrders")));
        customer.setUpdateTime(new Date());
        customerService.deleteById(customer);
        map1.put("msg","修改成功");
        return map1;
    }
    @ApiOperation(value = "跳转添加页面")
    @GetMapping("/addClient")
    public String addEmp(Model model){
        List<Employee> employees = employeeService.queryAll();
        model.addAttribute("employees",employees);
        return "eim/client/addClient";
    }
    @ApiOperation(value = "添加部门信息")
    @PostMapping("/addClient")
    @ResponseBody
    public Map addClient(@RequestBody Map<String,String> map) {
        Customer customer = new Customer();
        customer.setCustomerName(map.get("customerName"));
        customer.setSex(map.get("sex"));
        customer.setTelphone(map.get("telphone"));
        customer.setCompany(map.get("company"));
        customer.setAddress(map.get("address"));
        customer.setEmpId(Integer.valueOf(map.get("empId")));
        customer.setIsOrders(Integer.valueOf(map.get("isOrders")));
        customer.setUpdateTime(new Date());
        customer.setCreateTime(new Date());
        customer.setIsDel(0);
        customerService.addClient(customer);
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("msg","添加成功");
        return map1;
    }
}
