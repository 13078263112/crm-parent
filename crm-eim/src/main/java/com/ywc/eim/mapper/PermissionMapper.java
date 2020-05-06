package com.ywc.eim.mapper;

import com.ywc.crm.eim.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ywc.crm.eim.vo.RolePermissionVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-23
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    List<RolePermissionVo> lists();
    RolePermissionVo queryById(int premId);
}
