package com.kingdee.eas.hrp.sms.model;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String id;

    private String supplierId;

    private Date date;

    private String buyer;

    private String purchasingMode;

    private Integer tax;

    private Date cutasingleTime;

    private Date confirmDeliveryTime;

    private Integer confirmDeliveryNumbers;

    private Integer confirmOrder;

    private String orderNo;

    private String purchasingType;

    private Integer urgent;

    private String currency;

    private String paymentConditions;

    private String paymentWay;

    private String settlementWay;

    private BigDecimal amount;

    private BigDecimal taxAmount;

    private BigDecimal leviedCombined;

    private Date makeDate;

    private Date auditDate;

    private String auditUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer == null ? null : buyer.trim();
    }

    public String getPurchasingMode() {
        return purchasingMode;
    }

    public void setPurchasingMode(String purchasingMode) {
        this.purchasingMode = purchasingMode == null ? null : purchasingMode.trim();
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Date getCutasingleTime() {
        return cutasingleTime;
    }

    public void setCutasingleTime(Date cutasingleTime) {
        this.cutasingleTime = cutasingleTime;
    }

    public Date getConfirmDeliveryTime() {
        return confirmDeliveryTime;
    }

    public void setConfirmDeliveryTime(Date confirmDeliveryTime) {
        this.confirmDeliveryTime = confirmDeliveryTime;
    }

    public Integer getConfirmDeliveryNumbers() {
        return confirmDeliveryNumbers;
    }

    public void setConfirmDeliveryNumbers(Integer confirmDeliveryNumbers) {
        this.confirmDeliveryNumbers = confirmDeliveryNumbers;
    }

    public Integer getConfirmOrder() {
        return confirmOrder;
    }

    public void setConfirmOrder(Integer confirmOrder) {
        this.confirmOrder = confirmOrder;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getPurchasingType() {
        return purchasingType;
    }

    public void setPurchasingType(String purchasingType) {
        this.purchasingType = purchasingType == null ? null : purchasingType.trim();
    }

    public Integer getUrgent() {
        return urgent;
    }

    public void setUrgent(Integer urgent) {
        this.urgent = urgent;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getPaymentConditions() {
        return paymentConditions;
    }

    public void setPaymentConditions(String paymentConditions) {
        this.paymentConditions = paymentConditions == null ? null : paymentConditions.trim();
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay == null ? null : paymentWay.trim();
    }

    public String getSettlementWay() {
        return settlementWay;
    }

    public void setSettlementWay(String settlementWay) {
        this.settlementWay = settlementWay == null ? null : settlementWay.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getLeviedCombined() {
        return leviedCombined;
    }

    public void setLeviedCombined(BigDecimal leviedCombined) {
        this.leviedCombined = leviedCombined;
    }

    public Date getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(Date makeDate) {
        this.makeDate = makeDate;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser == null ? null : auditUser.trim();
    }
}