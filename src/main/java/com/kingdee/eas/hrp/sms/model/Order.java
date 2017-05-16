package com.kingdee.eas.hrp.sms.model;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String id;

    private String supplier;

    private Date bizDate;

    private String purchasePerson;

    private String saleProxy;

    private Byte isInTax;

    private Date tickDate;

    private Date confirmTickDate;

    private Byte tickType;

    private Byte confirmTick;

    private String number;

    private Byte isQuicken;

    private String currency;

    private String settlementType;

    private BigDecimal totalAmount;

    private BigDecimal totalTax;

    private BigDecimal totalTaxAmount;

    private Date createTime;

    private Byte baseStatus;

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

    public Date getBizDate() {
        return bizDate;
    }

    public void setBizDate(Date bizDate) {
        this.bizDate = bizDate;
    }

    public String getPurchasePerson() {
        return purchasePerson;
    }

    public void setPurchasePerson(String purchasePerson) {
        this.purchasePerson = purchasePerson == null ? null : purchasePerson.trim();
    }

    public String getSaleProxy() {
        return saleProxy;
    }

    public void setSaleProxy(String saleProxy) {
        this.saleProxy = saleProxy == null ? null : saleProxy.trim();
    }

    public Byte getIsInTax() {
        return isInTax;
    }

    public void setIsInTax(Byte isInTax) {
        this.isInTax = isInTax;
    }

    public Date getTickDate() {
        return tickDate;
    }

    public void setTickDate(Date tickDate) {
        this.tickDate = tickDate;
    }

    public Date getConfirmTickDate() {
        return confirmTickDate;
    }

    public void setConfirmTickDate(Date confirmTickDate) {
        this.confirmTickDate = confirmTickDate;
    }

    public Byte getTickType() {
        return tickType;
    }

    public void setTickType(Byte tickType) {
        this.tickType = tickType;
    }

    public Byte getConfirmTick() {
        return confirmTick;
    }

    public void setConfirmTick(Byte confirmTick) {
        this.confirmTick = confirmTick;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Byte getIsQuicken() {
        return isQuicken;
    }

    public void setIsQuicken(Byte isQuicken) {
        this.isQuicken = isQuicken;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType == null ? null : settlementType.trim();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getBaseStatus() {
        return baseStatus;
    }

    public void setBaseStatus(Byte baseStatus) {
        this.baseStatus = baseStatus;
    }
}