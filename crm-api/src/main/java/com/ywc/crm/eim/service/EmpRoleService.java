package com.ywc.crm.eim.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.crm.eim.entity.EmpRole;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-25
 */
public interface EmpRoleService extends IService<EmpRole> {

    EmpRole add(EmpRole empRole);

    int updateEmpRole(EmpRole empRole);


    EmpRole queryByEmpId(Integer empId);
}
