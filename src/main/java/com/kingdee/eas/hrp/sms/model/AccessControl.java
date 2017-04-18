package com.kingdee.eas.hrp.sms.model;

public class AccessControl {
    private Integer objectType;

    private Integer objectId;

    private Integer roleId;

    private Integer accessMask;

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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAccessMask() {
        return accessMask;
    }

    public void setAccessMask(Integer accessMask) {
        this.accessMask = accessMask;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", objectType=").append(objectType);
        sb.append(", objectId=").append(objectId);
        sb.append(", roleId=").append(roleId);
        sb.append(", accessMask=").append(accessMask);
        sb.append("]");
        return sb.toString();
    }
}