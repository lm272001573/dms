package cn.dms.dao;

import java.util.List;

import cn.dms.entity.UcUserRole;

public interface UcUserRoleMapper extends BaseDao<UcUserRole>{
	public List<UcUserRole> selectByDTO(UcUserRole record);

    public void updateByUserIdSelective(UcUserRole record);

    UcUserRole selectByPrimaryKey(UcUserRole record);

    int deleteByPrimaryKey(UcUserRole record);

    int deleteByRoleCode(UcUserRole record);

    int deleteByUserId(String user_id);

    public int batchInsert(List<UcUserRole> records);
}