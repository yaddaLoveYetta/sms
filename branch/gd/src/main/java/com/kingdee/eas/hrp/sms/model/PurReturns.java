package com.kingdee.eas.hrp.sms.model;

import java.util.Date;

public class PurReturns {
    private String id;

    private String number;

    private Date bizDate;

    private Byte baseStatus;

    private String sourceBillType;

    private String supplier;

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

    public Date getBizDate() {
        return bizDate;
    }

    public void setBizDate(Date bizDate) {
        this.bizDate = bizDate;
    }

    public Byte getBaseStatus() {
        return baseStatus;
    }

    public void setBaseStatus(Byte baseStatus) {
        this.baseStatus = baseStatus;
    }

    public String getSourceBillType() {
        return sourceBillType;
    }

    public void setSourceBillType(String sourceBillType) {
        this.sourceBillType = sourceBillType == null ? null : sourceBillType.trim();
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }
}