package com.kingdee.eas.hrp.sms.model;

import java.util.Date;

public class PurReceival {
    private String id;

    private String number;

    private Date bizDate;

    private Byte baseStatus;

    private Byte sourceBillType;

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

    public Byte getSourceBillType() {
        return sourceBillType;
    }

    public void setSourceBillType(Byte sourceBillType) {
        this.sourceBillType = sourceBillType;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }
}