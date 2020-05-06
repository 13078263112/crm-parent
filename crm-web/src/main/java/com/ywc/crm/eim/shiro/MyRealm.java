package com.ywc.crm.eim.shiro;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ywc.crm.eim.entity.EmpRole;
import com.ywc.crm.eim.entity.Employee;
import com.ywc.crm.eim.entity.RolePermission;
import com.ywc.crm.eim.service.EmpRoleService;
import com.ywc.crm.eim.service.EmployeeService;
import com.ywc.crm.eim.service.PermissionService;
import com.ywc.crm.eim.service.RolePermissionService;
import com.ywc.crm.eim.vo.RolePermissionVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import java.util.List;

/**
 * @author 嘟嘟~
 * @date 2020/3/23 17:37
 */
public class MyRealm extends AuthorizingRealm {
    @Reference
    EmployeeService employeeService;
    @Reference
    EmpRoleService empRoleService;
    @Reference
    RolePermissionService rolePermissionService;
    @Reference
    PermissionService permissionService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        EmpRole empRole = empRoleService.queryByEmpId(employee.getEmpId());
        List<RolePermission> rolePermissions = rolePermissionService.queryByEmpId(empRole.getRoleId());
        for (RolePermission rolePermission : rolePermissions) {
            RolePermissionVo rolePermissionVo = permissionService.queryById(rolePermission.getPermId());
            simpleAuthorizationInfo.addStringPermission(rolePermissionVo.getPermission());
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken authenticationToken1 = (UsernamePasswordToken) authenticationToken;
        Employee employee = employeeService.selectByName(authenticationToken1.getUsername());
        if (employee==null||employee.getIsDel()==1){
            return null;
        }
        return new SimpleAuthenticationInfo(employee,employee.getPwd(), ByteSource.Util.bytes(employee.getSalt()),getName());
    }
}
