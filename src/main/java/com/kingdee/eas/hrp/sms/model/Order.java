package com.kingdee.eas.hrp.sms.model;

import java.util.Date;

public class Order {
    private String id;

    private Integer lineNumbers;

    private String supplierId;

    private String supplierName;

    private Date orderTime;

    private String buyer;

    private Integer purchasingMode;

    private Integer tax;

    private Date cutasingleTime;

    private Date confirmDeliveryTime;

    private Integer confirmDeliveryNumbers;

    private Integer confirmOrder;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getLineNumbers() {
        return lineNumbers;
    }

    public void setLineNumbers(Integer lineNumbers) {
        this.lineNumbers = lineNumbers;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer == null ? null : buyer.trim();
    }

    public Integer getPurchasingMode() {
        return purchasingMode;
    }

    public void setPurchasingMode(Integer purchasingMode) {
        this.purchasingMode = purchasingMode;
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
}