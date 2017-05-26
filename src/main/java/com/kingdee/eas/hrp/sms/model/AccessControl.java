package com.kingdee.eas.hrp.sms.model;

public class AccessControl extends AccessControlKey {
    private Integer accessMask;

    public Integer getAccessMask() {
        return accessMask;
    }

    public void setAccessMask(Integer accessMask) {
        this.accessMask = accessMask;
    }
}