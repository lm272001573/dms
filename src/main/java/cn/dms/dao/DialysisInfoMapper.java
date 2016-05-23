package cn.dms.dao;

import java.util.List;

import cn.dms.entity.DialysisInfo;

public interface DialysisInfoMapper extends BaseDao<DialysisInfo>{
	List<DialysisInfo> find(DialysisInfo dialysisInfo);
}
