package com.ywc.crm.eim.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("eim_contact")
@ApiModel(value="Contact对象", description="")
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "contact_id", type = IdType.AUTO)
    private Integer contactId;

    @TableField("way")
    private String way;

    @TableField("content")
    private String content;

    @TableField("customer_id")
    private Integer customerId;

    @TableField("emp_id")
    private Integer empId;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("is_del")
    private Integer isDel;


}
