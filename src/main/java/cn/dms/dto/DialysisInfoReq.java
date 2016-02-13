package cn.dms.dto;


public class DialysisInfoReq  {
	private String name;// 姓名
	private String date; // 透析日期

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "DialysisReq [name=" + name + ", date=" + date + "]";
	}

}
