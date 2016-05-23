package cn.dms.entity;

public class Parameter {
    private String prmCode;

    private String prmValue;

    private String prmLang;

    private String prmName;

    private String prmShowmsg;

    public String getPrmCode() {
        return prmCode;
    }

    public void setPrmCode(String prmCode) {
        this.prmCode = prmCode == null ? null : prmCode.trim();
    }

    public String getPrmValue() {
        return prmValue;
    }

    public void setPrmValue(String prmValue) {
        this.prmValue = prmValue == null ? null : prmValue.trim();
    }

    public String getPrmLang() {
        return prmLang;
    }

    public void setPrmLang(String prmLang) {
        this.prmLang = prmLang == null ? null : prmLang.trim();
    }

    public String getPrmName() {
        return prmName;
    }

    public void setPrmName(String prmName) {
        this.prmName = prmName == null ? null : prmName.trim();
    }

    public String getPrmShowmsg() {
        return prmShowmsg;
    }

    public void setPrmShowmsg(String prmShowmsg) {
        this.prmShowmsg = prmShowmsg == null ? null : prmShowmsg.trim();
    }
}