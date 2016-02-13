package cn.dms.entity;

public class DialysisInfo {
	private int id;
	private String name;// 姓名
	private String bedId;// 床位号
	private String phoneNo;// 床位号
	private String startDialysisDate; // 开始透析日期
	private String dialysisInterval; // 透析间隔
	private String lastDialysisDate; // 最近透析日期
	private String first;
	private String second;
	private String third;
	private String fourth;
	private String fifth;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDialysisDate() {
		return startDialysisDate;
	}

	public void setStartDialysisDate(String startDialysisDate) {
		this.startDialysisDate = startDialysisDate;
	}

	public String getDialysisInterval() {
		return dialysisInterval;
	}

	public void setDialysisInterval(String dialysisInterval) {
		this.dialysisInterval = dialysisInterval;
	}

	public String getLastDialysisDate() {
		return lastDialysisDate;
	}

	public void setLastDialysisDate(String lastDialysisDate) {
		this.lastDialysisDate = lastDialysisDate;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public String getThird() {
		return third;
	}

	public void setThird(String third) {
		this.third = third;
	}

	public String getFourth() {
		return fourth;
	}

	public void setFourth(String fourth) {
		this.fourth = fourth;
	}

	public String getFifth() {
		return fifth;
	}

	public void setFifth(String fifth) {
		this.fifth = fifth;
	}

	public String getBedId() {
		return bedId;
	}

	public void setBedId(String bedId) {
		this.bedId = bedId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String toString() {
		return "DialysisInfo [id=" + id + ", name=" + name + ", bedId=" + bedId + ", phoneNo="
				+ phoneNo + ", startDialysisDate=" + startDialysisDate + ", dialysisInterval="
				+ dialysisInterval + ", lastDialysisDate=" + lastDialysisDate + ", first=" + first
				+ ", second=" + second + ", third=" + third + ", fourth=" + fourth + ", fifth="
				+ fifth + "]";
	}
}
