/**
 * (C) 2014 Tencent Group Holding Limited.
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License version 2 as published by the Free Software Foundation.
 * 
 */

package cn.dms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dms.common.ErrorCodes;
import cn.dms.dao.UcPermissionMapper;
import cn.dms.dto.FunctionResArrayDto;
import cn.dms.dto.ReqQueryFunctionsDto;
import cn.dms.dto.RespQueryFunctionsDto;
import cn.dms.entity.UcPermission;
import cn.dms.service.FunctionService;

@Service("cn.dms.service.impl.FunctionServiceImpl")
public class FunctionServiceImpl implements FunctionService {

    private final static Logger Log = LoggerFactory.getLogger(FunctionServiceImpl.class);

    @Autowired
    private UcPermissionMapper umPermissionDao;

    @Override
    public RespQueryFunctionsDto queryFunctions(ReqQueryFunctionsDto req) {
        Log.info("enter impl.queryFunctions, params:{}",req);
        RespQueryFunctionsDto rsp = new RespQueryFunctionsDto();
        rsp.setReturn_code(ErrorCodes.NORMAL);
        try {
            List<UcPermission> permission_list = umPermissionDao.selectByIdAndUidAndType(req.getMenu_id(), req.getUser_id(), "2");

            List<FunctionResArrayDto> function_res_array = new ArrayList<FunctionResArrayDto>();
            if (null != permission_list && 0 < permission_list.size()) {
                for (UcPermission permissionDTO : permission_list) {
                    FunctionResArrayDto tmp_dto = new FunctionResArrayDto();
                    tmp_dto.setRes_code(permissionDTO.getResCode());
                    tmp_dto.setRes_name(permissionDTO.getResName());
                    function_res_array.add(tmp_dto);
                }
            }
            rsp.setFunction_res_array(function_res_array);
            return rsp;
        } catch (Exception e) {
            Log.error("queryFunctions exception: " + e.getMessage());
            rsp.setReturn_code(ErrorCodes.SYSTEM_ERROR);
            rsp.setReturn_message(ErrorCodes.SYSTEM_ERROR_DESC);
            return rsp;
        }
    }
}
