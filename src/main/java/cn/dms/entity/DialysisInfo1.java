package cn.dms.entity;

public class DialysisInfo1 {
    private Integer id;

    private String name;

    private String startDialysisDate;

    private String dialysisInterval;

    private String lastDialysisDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStartDialysisDate() {
        return startDialysisDate;
    }

    public void setStartDialysisDate(String startDialysisDate) {
        this.startDialysisDate = startDialysisDate == null ? null : startDialysisDate.trim();
    }

    public String getDialysisInterval() {
        return dialysisInterval;
    }

    public void setDialysisInterval(String dialysisInterval) {
        this.dialysisInterval = dialysisInterval == null ? null : dialysisInterval.trim();
    }

    public String getLastDialysisDate() {
        return lastDialysisDate;
    }

    public void setLastDialysisDate(String lastDialysisDate) {
        this.lastDialysisDate = lastDialysisDate == null ? null : lastDialysisDate.trim();
    }
}