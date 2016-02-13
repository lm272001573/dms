package cn.dms.dao;

import java.util.List;

import cn.dms.entity.DialysisInfo;

public interface DialysisInfoDao {
	List<DialysisInfo> find(DialysisInfo dialysisInfo);
}
