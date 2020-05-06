package com.ywc.crm.eim.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 嘟嘟~
 * @date 2020/3/29 15:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionVo implements Serializable {
    private Integer permId;
    private String permName;
    private String url;
    private String permission;
    private Date createTime;
    private Date updateTime;
    private Integer isDel;
    private List<String> roleName;
}
