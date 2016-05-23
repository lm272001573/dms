
package cn.dms.controller.userManage.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.dms.common.ErrorCodes;
import cn.dms.dto.ReqQueryFunctionsDto;
import cn.dms.dto.RespQueryFunctionsDto;
import cn.dms.dto.UserInfo;
import cn.dms.service.FunctionService;

/**
 * 
 * 权限查询
 * <p>
 * @version 1.0.0,2016年2月14日
 * @author liming
 */
@RestController
@RequestMapping("function")
public class FunctionController extends BaseController{

    private final static Logger logger = LoggerFactory.getLogger(FunctionController.class);

    @Autowired
    private FunctionService functionService;

    @RequestMapping(value = "/queryFunctions.do", method = RequestMethod.POST)
    public RespQueryFunctionsDto queryFunctions(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
        HttpSession session, @RequestBody ReqQueryFunctionsDto req) {
        logger.debug("queryFunctions Start ...");

        UserInfo usrInfo = super.getUserInfo(httpRequest);
        if (usrInfo == null)
            return null;
        req.setUser_id(usrInfo.getUserId());

	    RespQueryFunctionsDto rsp = new RespQueryFunctionsDto();
	    try {
	        rsp = functionService.queryFunctions(req);
	    } catch (Exception e) {
	        rsp.setBaseRespCode(ErrorCodes.SYSTEM_ERROR);
	        rsp.setBaseRespMsg(ErrorCodes.SYSTEM_ERROR_DESC);
	    }
	    logger.debug("queryFunctions end...");
	    return rsp;
    }
}
