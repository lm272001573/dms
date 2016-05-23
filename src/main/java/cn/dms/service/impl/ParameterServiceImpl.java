
package cn.dms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dms.dao.ParameterMapper;
import cn.dms.dto.ParameterDto;
import cn.dms.dto.ReqParameterDto;
import cn.dms.dto.RespParameterDto;
import cn.dms.entity.Parameter;
import cn.dms.service.ParameterService;

@Service("cn.dms.service.ParameterService")
public class ParameterServiceImpl implements ParameterService {

    private static final Logger logger = LoggerFactory.getLogger(ParameterServiceImpl.class);

    @Autowired
    private ParameterMapper parameterDao;

    /**
     * 根据输入的查询条件获取相应的参数列表 提供给前端用
     */
    @Override
    public RespParameterDto selectByParameter(ReqParameterDto reqParameterDto) {
        logger.info("reqParameterDto is {}", reqParameterDto);
        List<ParameterDto> rsp_list = new ArrayList<ParameterDto>();
        RespParameterDto rspParameterDto = new RespParameterDto();
        for (ParameterDto paramDto : reqParameterDto.getReq_list()) {
            Parameter parameter = new Parameter();
            parameter.setPrmCode(paramDto.getPrm_code());
            parameter.setPrmLang(paramDto.getPrm_lang());
            logger.info(parameter.toString());
            List<Parameter> parameterList = parameterDao.selectByParameter(parameter);
            logger.info("parameterList size is : {}",parameterList.size());
            for (Parameter tempParameter : parameterList) {
                ParameterDto parameterClientDto = new ParameterDto();
                parameterClientDto.setPrm_code(tempParameter.getPrmCode());
                parameterClientDto.setPrm_value(tempParameter.getPrmValue());
                parameterClientDto.setPrm_lang(tempParameter.getPrmLang());
                parameterClientDto.setPrm_name(tempParameter.getPrmName());
                parameterClientDto.setPrm_showmsg(tempParameter.getPrmShowmsg());
                rsp_list.add(parameterClientDto);
            }
        }
        logger.info("rsp_list size is : {}",rsp_list.size());
        rspParameterDto.setRsp_list(rsp_list);
        return rspParameterDto;
    }
}
