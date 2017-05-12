package com.kingdee.eas.hrp.sms.model;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String id;

    private String supplier;

    private Date bizDate;

    private String purchasePerson;

    private String saleProxy;

    private Double isInTax;

    private Date tickTime;

    private Date confirmTickTime;

    private Integer tickType;

    private Integer confirmTick;

    private String number;

    private Integer isQuicken;

    private String currency;

    private String paymentCondition;

    private String paymentType;

    private String settlementType;

    private BigDecimal totalAmount;

    private BigDecimal totalTax;

    private BigDecimal totalTaxAmount;

    private Date createTime;

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

    public Double getIsInTax() {
        return isInTax;
    }

    public void setIsInTax(Double isInTax) {
        this.isInTax = isInTax;
    }

    public Date getTickTime() {
        return tickTime;
    }

    public void setTickTime(Date tickTime) {
        this.tickTime = tickTime;
    }

    public Date getConfirmTickTime() {
        return confirmTickTime;
    }

    public void setConfirmTickTime(Date confirmTickTime) {
        this.confirmTickTime = confirmTickTime;
    }

    public Integer getTickType() {
        return tickType;
    }

    public void setTickType(Integer tickType) {
        this.tickType = tickType;
    }

    public Integer getConfirmTick() {
        return confirmTick;
    }

    public void setConfirmTick(Integer confirmTick) {
        this.confirmTick = confirmTick;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Integer getIsQuicken() {
        return isQuicken;
    }

    public void setIsQuicken(Integer isQuicken) {
        this.isQuicken = isQuicken;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getPaymentCondition() {
        return paymentCondition;
    }

    public void setPaymentCondition(String paymentCondition) {
        this.paymentCondition = paymentCondition == null ? null : paymentCondition.trim();
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
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
}