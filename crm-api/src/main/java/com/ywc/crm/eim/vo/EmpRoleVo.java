package com.ywc.crm.eim.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 嘟嘟~
 * @date 2020/3/24 1:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpRoleVo implements Serializable {
    private Integer empId;
    private String empName;
    private Integer age;
    private String sex;
    private String phone;
    private String address;
    private Date createTime;
    private Date updateTime;
    private Integer isDel;
    private String roleName;
    private String deptName;
}
