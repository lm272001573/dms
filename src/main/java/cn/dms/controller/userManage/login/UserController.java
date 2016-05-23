
package cn.dms.controller.userManage.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dms.dao.UcMenuMapper;
import cn.dms.dao.UcPasswordMapper;
import cn.dms.dto.UserInfo;
import cn.dms.entity.Menu;
import cn.dms.entity.UcPassword;
import cn.dms.enums.loginStatusEnum;

/**
 * 
 * 用户登录控制器 UmController
 * <p>
 * @version 1.0.0,2016年2月14日
 * @author liming
 */
@RestController
@RequestMapping("um")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UcMenuMapper menuDao;
    
    @Autowired
    private UcPasswordMapper passwordDao;

    /**
     * @Title: login
     * @Description: 登录
     * @param httpRequest
     * @param session
     * @param userId
     * @param password
     * @return设定文件 Map<String,Integer>返回类型
     * @throws
     */
    @RequestMapping("/login")
    public Map<String, Integer> login(HttpServletRequest httpRequest, HttpSession session, String userId, String password) {
        this.clearSession(session);
        logger.info("request params are [userId = {}],[password = {}]", userId, password);
        Map<String, Integer> result = new HashMap<String, Integer>();

        UcPassword ucPassword = checkIfExists(userId, password);
        if (null != ucPassword) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setLoggingStatus(loginStatusEnum.SUCCESS.getCode());
            session.setAttribute("userInfo", userInfo);
        }
        result.put("result", loginStatusEnum.SUCCESS.getCode());

        return result;
    }

    private UcPassword checkIfExists(String userId, String password) {
		UcPassword reqParams = new UcPassword();
		reqParams.setUserId(userId);
		reqParams.setPassword(password);
		return passwordDao.verifyPassword(reqParams);
	}
    
    @RequestMapping("/getUserInfo")
    public UserInfo getUserInfo(HttpSession session) {
    	logger.info("getUserInfo");
        UserInfo userInfo = null;
        if (session != null && session.getAttribute("userInfo") != null) {
            userInfo = (UserInfo) session.getAttribute("userInfo");
        }
        return userInfo;
    }

	/**
     * @Title: getMenu
     * @Description: 加载用户菜单
     * @param session
     * @return设定文件 List<Menu>返回类型
     * @throws
     */
    @RequestMapping("/getMenuTree")
    public List<Menu> getMenu(HttpSession session) {
        List<Menu> rootMenus = null;
        if (session.getAttribute("userInfo") != null) {
            UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
            if (userInfo != null && userInfo.getUserId() != null) {
                String userId = userInfo.getUserId();
                // 获取菜单
                List<Menu> menuItems = menuDao.selectMenuByUserId(userId);
                // 转换树形菜单
                if (menuItems != null && menuItems.size() > 0) {
                    rootMenus = new ArrayList<Menu>();
                    for (Menu menuItem : menuItems) {
                        this.initTreeMenu(rootMenus, menuItem);
                    }
                    // 验证菜单的有效性
                    this.validateMenu(rootMenus, true, null);
                }
            }
        }
        return rootMenus;
    }

    @RequestMapping("/logout")
    public Map<String, Integer> logout(HttpSession session) {
        clearSession(session);
        Map<String, Integer> result = new HashMap<String, Integer>();
        result.put("result", 0);
        return result;
    }

    private void clearSession(HttpSession session) {
        session.removeAttribute("userInfo");
    }

    /**
     * @Title: initTreeMenu
     * @Description: 构造树形菜单
     * @param parent
     * @param node设定文件 void返回类型
     * @throws
     */
    private void initTreeMenu(List<Menu> parent, Menu node) {
        // 添加一级节点
        if (node.getPid() == 1) {
            parent.add(node);
            return;
        }
        for (Menu item : parent) {
            List<Menu> subItems = item.getChildren();
            if (node.getPid().equals(item.getId())) {
                if (subItems == null) {
                    subItems = new ArrayList<>();
                    item.setChildren(subItems);
                }
                subItems.add(node);
                return;
            } else {
                if (null != subItems && subItems.size() > 0) {
                    // 需找节点的父节点
                    this.initTreeMenu(subItems, node);
                }
            }
        }
    }

    /**
     * @Title: validateMenu
     * @Description: 去掉无叶子节点的菜单节点
     * @param parent
     * @param isInited
     * @param child
     * @return设定文件 boolean返回类型
     * @throws
     */
    private boolean validateMenu(List<Menu> parent, boolean isInited, Menu child) {
        // 无效叶子节点
        boolean isValid = false;
        if (child != null && (child.getChildren() == null || child.getChildren().size() <= 0) && StringUtils.isEmpty(child.getAction())) {
            parent.remove(child);
            isValid = true;
        } else {
            List<Menu> objMenus = isInited == true ? parent : child.getChildren();
            if (objMenus != null && objMenus.size() >= 1) {
                for (int i = 0; i < objMenus.size();) {
                    Menu objMenu = objMenus.get(i);
                    isValid = validateMenu(objMenus, false, objMenu);
                    // 删除无效的父节点
                    if (objMenu.getChildren() != null && objMenu.getChildren().size() <= 0) {
                        objMenus.remove(objMenu);
                        isValid = true;
                    }
                    if (!isValid)
                        i++; // 防止删除节点后，节点索引越界
                }
            }
        }
        return isValid;
    }

    /**
     * @Title: getAllMenus
     * @Description: 获取所有菜单
     * @return设定文件 List<Menu>返回类型
     * @throws
     */
    @RequestMapping("/getAllMenus")
    public List<Menu> getAllMenus() {
        List<Menu> rootMenus = null;
        List<Menu> menuItems = menuDao.selectAllMenus();
        // 转换树形菜单
        if (menuItems != null && menuItems.size() > 0) {
            rootMenus = new ArrayList<Menu>();
            for (Menu menuItem : menuItems) {
                initTreeMenu(rootMenus, menuItem);
            }
            // 验证菜单的有效性
            this.validateMenu(rootMenus, true, null);
        }
        return rootMenus;
    }
}
