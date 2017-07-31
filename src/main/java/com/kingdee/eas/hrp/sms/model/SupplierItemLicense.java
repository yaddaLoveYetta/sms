package com.kingdee.eas.hrp.sms.model;

import java.util.Date;

public class SupplierItemLicense {
    private String id;

    private String type;

    private String supplier;

    private String authOrg;

    private String description;

    private Date beginDate;

    private Date endDate;

    private Byte syncStatus;

    private Byte review;

    private String material;

    private Byte isMust;

    private Byte isControl;

    private Byte prohibited;

    private String name;

    private String number;

    private String dyManufacturer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

    public String getAuthOrg() {
        return authOrg;
    }

    public void setAuthOrg(String authOrg) {
        this.authOrg = authOrg == null ? null : authOrg.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material == null ? null : material.trim();
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

    public Byte getProhibited() {
        return prohibited;
    }

    public void setProhibited(Byte prohibited) {
        this.prohibited = prohibited;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getDyManufacturer() {
        return dyManufacturer;
    }

    public void setDyManufacturer(String dyManufacturer) {
        this.dyManufacturer = dyManufacturer == null ? null : dyManufacturer.trim();
    }
}