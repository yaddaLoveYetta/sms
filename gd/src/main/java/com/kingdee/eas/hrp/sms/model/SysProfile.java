package com.kingdee.eas.hrp.sms.model;

public class SysProfile extends SysProfileKey {
    private String name;

    private String desc;

    private String value;

    private Integer index;

    private String explanation;

    private Byte readOnly;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation == null ? null : explanation.trim();
    }

    public Byte getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(Byte readOnly) {
        this.readOnly = readOnly;
    }
}