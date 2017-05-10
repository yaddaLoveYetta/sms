package com.kingdee.eas.hrp.sms.model;

public class Supplier_License_Type {
    private String id;

    private String number;

    private String name;

    private Byte isMust;

    private Byte isControl;

    private String syncStatus;

    private String review;

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

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus == null ? null : syncStatus.trim();
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review == null ? null : review.trim();
    }
}