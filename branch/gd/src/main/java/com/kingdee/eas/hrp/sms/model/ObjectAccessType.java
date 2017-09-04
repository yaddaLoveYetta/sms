package com.kingdee.eas.hrp.sms.model;

public class ObjectAccessType extends ObjectAccessTypeKey {
    private String name;

    private Integer accessMask;

    private Integer accessUse;

    private Integer ownerType;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAccessMask() {
        return accessMask;
    }

    public void setAccessMask(Integer accessMask) {
        this.accessMask = accessMask;
    }

    public Integer getAccessUse() {
        return accessUse;
    }

    public void setAccessUse(Integer accessUse) {
        this.accessUse = accessUse;
    }

    public Integer getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(Integer ownerType) {
        this.ownerType = ownerType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}