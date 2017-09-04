package com.kingdee.eas.hrp.sms.model;

public class ObjectTypeKey {
    private Integer topClassId;

    private Integer subSysId;

    private Integer objectType;

    private Integer objectId;

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
}