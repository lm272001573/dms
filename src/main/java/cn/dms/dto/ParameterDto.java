
package cn.dms.dto;


public class ParameterDto{

    /**
     * 参数代码
     */
    private String prm_code;

    /**
     * 参数值
     */
    private String prm_value;

    /**
     * 参数语种
     */
    private String prm_lang;

    /**
     * 参数名称
     */
    private String prm_name;

    /**
     * 参数显示信息
     */
    private String prm_showmsg;

    /**
     * 渠道
     */
    private String prm_channel;

    public String getPrm_code() {
        return prm_code;
    }

    public void setPrm_code(String prm_code) {
        this.prm_code = prm_code;
    }

    public String getPrm_lang() {
        return prm_lang;
    }

    public void setPrm_lang(String prm_lang) {
        this.prm_lang = prm_lang;
    }

    public String getPrm_value() {
        return prm_value;
    }

    public void setPrm_value(String prm_value) {
        this.prm_value = prm_value;
    }

    public String getPrm_name() {
        return prm_name;
    }

    public void setPrm_name(String prm_name) {
        this.prm_name = prm_name;
    }

    public String getPrm_showmsg() {
        return prm_showmsg;
    }

    public void setPrm_showmsg(String prm_showmsg) {
        this.prm_showmsg = prm_showmsg;
    }

    public String getPrm_channel() {
        return prm_channel;
    }

    public void setPrm_channel(String prm_channel) {
        this.prm_channel = prm_channel;
    }
}
