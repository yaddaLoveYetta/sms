package com.kingdee.eas.hrp.sms.model;

public class AccessControl extends AccessControlKey {
    private Integer accessMask;

    private Integer classId;

    public Integer getAccessMask() {
        return accessMask;
    }

    public void setAccessMask(Integer accessMask) {
        this.accessMask = accessMask;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}