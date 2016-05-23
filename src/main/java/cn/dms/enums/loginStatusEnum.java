package cn.dms.enums;

public enum loginStatusEnum {
	SUCCESS(0, "登录成功"), 
	FAIL(1, "登录失败");
	
	loginStatusEnum(int code, String desc) {
	        this.code = code;
	        this.desc = desc;
    }
	
	private int code;
	private String desc;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
