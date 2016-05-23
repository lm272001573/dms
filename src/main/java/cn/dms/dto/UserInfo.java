
package cn.dms.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 用户信息
 * <p>
 * @version 1.0.0,2016年2月14日
 * @author liming
 */
public class UserInfo {

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 登录状态
     */
    private int loggingStatus = 0;

    /**
     * 用户权限集合
     */
    private List<String> permissionList = new ArrayList<String>();
    
    
    private String loginSystemType; //用户登录系统类型

    public UserInfo() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getLoggingStatus() {
        return loggingStatus;
    }

    public void setLoggingStatus(int loggingStatus) {
        this.loggingStatus = loggingStatus;
    }

    public List<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public String toString() {
        return "UserInfo [userId=" + userId + ", loggingStatus=" + loggingStatus + ", permissionList=" + permissionList + "]";
    }
    

  	public String getLoginSystemType() {
  		return loginSystemType;
  	}

  	public void setLoginSystemType(String loginSystemType) {
  		this.loginSystemType = loginSystemType;
  	}
}
