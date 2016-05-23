package cn.dms.dto;


public class ReqQueryFunctionsDto{
	
	private String user_id;
	
	private int menu_id;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	

	public ReqQueryFunctionsDto(String user_id, int menu_id) {
		super();
		this.user_id = user_id;
		this.menu_id = menu_id;
	}

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public ReqQueryFunctionsDto() {
		super();
	}

	@Override
	public String toString() {
		return "ReqQueryFunctionsDto [user_id=" + user_id + ", menu_id=" + menu_id + "]";
	}
	
	
}
