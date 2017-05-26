package com.kingdee.eas.hrp.sms.model;

public class ObjectType {
    private Integer topClassId;

    private Integer subSysId;

    private Integer objectType;

    private Integer objectId;

    private String name;

    private String description;

    private Integer classId;

    public Integer getTopClassId() {
        return topClassId;
    }

    public void setTopClassId(Integer topClassId) {
        this.topClassId = topClassId;
    }

    public Integer getSubSysId() {
        return subSysId;
    }

    public void setSubSysId(Integer subSysId) {
        this.subSysId = subSysId;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}