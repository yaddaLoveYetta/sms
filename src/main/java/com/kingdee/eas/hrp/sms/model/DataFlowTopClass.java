package com.kingdee.eas.hrp.sms.model;

public class DataFlowTopClass {
    private Integer topClassId;

    private String topClassName;

    private Integer index;

    private Boolean visible;

    private String icon;

    public Integer getTopClassId() {
        return topClassId;
    }

    public void setTopClassId(Integer topClassId) {
        this.topClassId = topClassId;
    }

    public String getTopClassName() {
        return topClassName;
    }

    public void setTopClassName(String topClassName) {
        this.topClassName = topClassName == null ? null : topClassName.trim();
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }
}