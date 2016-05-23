package cn.dms.entity;

public class UcPermission {
    private String resCode;

    private String resName;

    private String ressContent;

    private String status;

    private String resType;

    private Integer resSequence;

    private String resParent;
    
    // 自定义
    private String opType;

    private String roleCode;

    private String menuId;

    private String menuName;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode == null ? null : resCode.trim();
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName == null ? null : resName.trim();
    }

    public String getRessContent() {
        return ressContent;
    }

    public void setRessContent(String ressContent) {
        this.ressContent = ressContent == null ? null : ressContent.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getResType() {
        return resType;
    }

    public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public void setResType(String resType) {
        this.resType = resType == null ? null : resType.trim();
    }

    public Integer getResSequence() {
        return resSequence;
    }

    public void setResSequence(Integer resSequence) {
        this.resSequence = resSequence;
    }

    public String getResParent() {
        return resParent;
    }

    public void setResParent(String resParent) {
        this.resParent = resParent == null ? null : resParent.trim();
    }
}