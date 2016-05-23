package cn.dms.dao;

import java.util.List;

import cn.dms.entity.Parameter;

public interface ParameterMapper extends BaseDao<Parameter>{
	 List<Parameter> selectByParameter(Parameter parameter);
}