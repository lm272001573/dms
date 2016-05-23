package cn.dms.dto;

public class BaseRspDto {
	private String baseRespCode; //用于判断查询是否成功返回码
	private String baseRespMsg;  //用于判断查询是否成功消息
	public String getBaseRespCode() {
		return baseRespCode;
	}
	public void setBaseRespCode(String baseRespCode) {
		this.baseRespCode = baseRespCode;
	}
	public String getBaseRespMsg() {
		return baseRespMsg;
	}
	public void setBaseRespMsg(String baseRespMsg) {
		this.baseRespMsg = baseRespMsg;
	}
}
