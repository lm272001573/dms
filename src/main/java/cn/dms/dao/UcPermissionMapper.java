package cn.dms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dms.entity.UcPermission;

public interface UcPermissionMapper extends BaseDao<UcPermission> {

    public List<UcPermission> SelectAllAuth();

    public List<UcPermission> selectByIdAndUidAndType(@Param(value = "id") int id, @Param(value = "userId") String userId,
        @Param(value = "res_type") String res_type);

    List<UcPermission> selectPermissionBySelective(UcPermission record);

    List<UcPermission> queryPermissionOfRole(UcPermission record);

    List<UcPermission> queryAllBtnPermission(UcPermission record);


}