package cn.dms.dto;

import java.util.List;

public class RespQueryFunctionsDto extends BaseRspDto{

	private String return_code;
	
	private String return_message;
	
	private List<FunctionResArrayDto> function_res_array;

	public RespQueryFunctionsDto() {
		super();
	}

	public RespQueryFunctionsDto(String return_code, String return_message,
			List<FunctionResArrayDto> function_res_array) {
		super();
		this.return_code = return_code;
		this.return_message = return_message;
		this.function_res_array = function_res_array;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_message() {
		return return_message;
	}

	public void setReturn_message(String return_message) {
		this.return_message = return_message;
	}

	public List<FunctionResArrayDto> getFunction_res_array() {
		return function_res_array;
	}

	public void setFunction_res_array(List<FunctionResArrayDto> function_res_array) {
		this.function_res_array = function_res_array;
	}
}
