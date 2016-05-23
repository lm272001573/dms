/*@(#)ParameterController.java   2015年7月14日 
 * Copy Right 2015 Tencent Group Holding Limited.
 * All Copyright Reserved
 */

package cn.dms.controller.userManage.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.dms.dto.ReqParameterDto;
import cn.dms.dto.RespParameterDto;
import cn.dms.service.ParameterService;

/**
 * 
 * 参数控制类
 * <p>
 * @version 1.0.0,2016年2月14日
 * @author liming
 */
@RestController
@RequestMapping("parameter")
public class ParameterController{

    private final static Logger logger = LoggerFactory.getLogger(ParameterController.class);

    @Autowired
    @Qualifier("cn.dms.service.ParameterService")
    private ParameterService parameterService;

    /**
     * @Title: queryParameter 
     * @Description: 查询系统参数 
     * @param @param httpRequest
     * @param @param httpResponse
     * @param @param reqParameterDto
     * @throws
     */
    @RequestMapping(value = "/queryParameter.do", method = RequestMethod.POST)
    public RespParameterDto queryParameter(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
        @RequestBody ReqParameterDto reqParameterDto) {
        logger.info("request body:{}", reqParameterDto.toString());
        return parameterService.selectByParameter(reqParameterDto);
	}
}
