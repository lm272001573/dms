
package cn.dms.service;

import cn.dms.dto.ReqQueryFunctionsDto;
import cn.dms.dto.RespQueryFunctionsDto;

public interface FunctionService {

    /**
     * 查询功能权限数据
     * 
     * @date:2015年4月20日下午4:43:46
     * @param req
     * @param errors
     * @return
     */
    RespQueryFunctionsDto queryFunctions(ReqQueryFunctionsDto req);
}
