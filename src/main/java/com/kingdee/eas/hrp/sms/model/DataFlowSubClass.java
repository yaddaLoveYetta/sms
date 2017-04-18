package com.kingdee.eas.hrp.sms.model;

public class DataFlowSubClass {
    private Integer subSysId;

    private Integer topClassId;

    private String name;

    private Integer index;

    private Boolean visible;

    private String url;

    private String icon;

    public Integer getSubSysId() {
        return subSysId;
    }

    public void setSubSysId(Integer subSysId) {
        this.subSysId = subSysId;
    }

    public Integer getTopClassId() {
        return topClassId;
    }

    public void setTopClassId(Integer topClassId) {
        this.topClassId = topClassId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", subSysId=").append(subSysId);
        sb.append(", topClassId=").append(topClassId);
        sb.append(", name=").append(name);
        sb.append(", index=").append(index);
        sb.append(", visible=").append(visible);
        sb.append(", url=").append(url);
        sb.append(", icon=").append(icon);
        sb.append("]");
        return sb.toString();
    }
}