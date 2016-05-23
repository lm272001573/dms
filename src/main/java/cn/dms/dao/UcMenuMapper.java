
package cn.dms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dms.entity.Menu;

public interface UcMenuMapper extends BaseDao<Menu> {

    /**
     * @Title: selectUcMenuByUserId
     * @Description: 查询用户所属菜单
     * @param userId
     * @return设定文件 List<UcMenu>返回类型
     * @throws
     */
    List<Menu> selectMenuByUserId(@Param(value = "userId") String userId);

    /**
     * @Title: selectAllUcMenus
     * @Description: 查询所有菜单
     * @return设定文件 List<UcMenu>返回类型
     * @throws
     */
    List<Menu> selectAllMenus();
}