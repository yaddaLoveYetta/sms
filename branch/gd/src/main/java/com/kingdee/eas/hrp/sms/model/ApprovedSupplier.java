package com.kingdee.eas.hrp.sms.model;

import java.math.BigDecimal;
import java.util.Date;

public class ApprovedSupplier {
    private String id;

    private String supplier;

    private String materialItem;

    private Byte isStopped;

    private String measureUnit;

    private BigDecimal supplierRate;

    private BigDecimal taxPrice;

    private Date effectualDate;

    private Date uneffectualDate;

    private Byte syncStatus;

    private Byte review;

    private String purMeasureUnit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

    public String getMaterialItem() {
        return materialItem;
    }

    public void setMaterialItem(String materialItem) {
        this.materialItem = materialItem == null ? null : materialItem.trim();
    }

    public Byte getIsStopped() {
        return isStopped;
    }

    public void setIsStopped(Byte isStopped) {
        this.isStopped = isStopped;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit == null ? null : measureUnit.trim();
    }

    public BigDecimal getSupplierRate() {
        return supplierRate;
    }

    public void setSupplierRate(BigDecimal supplierRate) {
        this.supplierRate = supplierRate;
    }

    public BigDecimal getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }

    public Date getEffectualDate() {
        return effectualDate;
    }

    public void setEffectualDate(Date effectualDate) {
        this.effectualDate = effectualDate;
    }

    public Date getUneffectualDate() {
        return uneffectualDate;
    }

    public void setUneffectualDate(Date uneffectualDate) {
        this.uneffectualDate = uneffectualDate;
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

    public String getPurMeasureUnit() {
        return purMeasureUnit;
    }

    public void setPurMeasureUnit(String purMeasureUnit) {
        this.purMeasureUnit = purMeasureUnit == null ? null : purMeasureUnit.trim();
    }
}