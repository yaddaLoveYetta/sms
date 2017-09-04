package com.kingdee.eas.hrp.sms.model;

public class SupplierLicenseType {
    private String id;

    private String number;

    private String name;

    private Byte isMust;

    private Byte isControl;

    private Byte syncStatus;

    private Byte review;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getIsMust() {
        return isMust;
    }

    public void setIsMust(Byte isMust) {
        this.isMust = isMust;
    }

    public Byte getIsControl() {
        return isControl;
    }

    public void setIsControl(Byte isControl) {
        this.isControl = isControl;
    }

    public Byte getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(Byte syncStatus) {
        this.syncStatus = syncStatus;
    }

    public Byte getReview() {
        return review;
    }

    public void setReview(Byte review) {
        this.review = review;
    }
}