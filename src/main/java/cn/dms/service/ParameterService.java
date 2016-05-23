
package cn.dms.service;

import cn.dms.dto.ReqParameterDto;
import cn.dms.dto.RespParameterDto;


public interface ParameterService {

    RespParameterDto selectByParameter(ReqParameterDto reqParameter);
}
