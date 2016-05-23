package cn.dms.dao;


public interface BaseDao<T> {

	T selectByPrimaryKey(String primaryKey);
	
	T selectByPrimaryKey(T keyObject);

	int deleteByPrimaryKey(String primaryKey);
	
	int deleteByPrimaryKey(T keyObject);
	
	int insert(T Object);
	
	int insertSelective(T Object);
	
	int updateByPrimaryKeySelective(T Object);
	
	int updateByPrimaryKey(T Object);
}