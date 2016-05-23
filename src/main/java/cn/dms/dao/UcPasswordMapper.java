package cn.dms.dao;

import cn.dms.entity.UcPassword;

public interface UcPasswordMapper extends BaseDao<UcPassword>{
	UcPassword verifyPassword(UcPassword reqParams);
}