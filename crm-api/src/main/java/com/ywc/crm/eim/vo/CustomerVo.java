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
 * @date 2020/3/30 21:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVo  implements Serializable {
    private Integer customerId;
    private String customerName;
    private String sex;
    private String telphone;
    private String company;
    private String address;
    private Integer isOrders;
    private Date createTime;
    private Date updateTime;
    private Integer isDel;
    private String empName;
}
