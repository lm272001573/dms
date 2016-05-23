package cn.dms.controller.userManage.login;

import javax.servlet.http.HttpServletRequest;

import cn.dms.dto.UserInfo;

public class BaseController {
	protected UserInfo getUserInfo(HttpServletRequest httpServletRequest) {
        return (UserInfo) httpServletRequest.getSession().getAttribute("userInfo");
    }
}
