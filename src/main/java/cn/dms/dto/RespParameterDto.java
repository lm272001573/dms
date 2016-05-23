
package cn.dms.dto;

import java.util.List;

public class RespParameterDto extends BaseRspDto {

    private List<ParameterDto> rsp_list;

    public List<ParameterDto> getRsp_list() {
        return rsp_list;
    }

    public void setRsp_list(List<ParameterDto> rsp_list) {
        this.rsp_list = rsp_list;
    }
}
