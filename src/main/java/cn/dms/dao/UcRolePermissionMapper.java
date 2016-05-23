package cn.dms.dao;

import java.util.List;

import cn.dms.entity.UcRolePermission;

public interface UcRolePermissionMapper extends BaseDao<UcRolePermission>{

    int deleteByRoleCode(String role_code);

    public int batchInsert(List<UcRolePermission> records);
}	