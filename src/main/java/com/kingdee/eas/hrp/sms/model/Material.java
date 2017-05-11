package com.kingdee.eas.hrp.sms.model;

import java.math.BigDecimal;
import java.util.Date;

public class Material {
    private Integer id;

    private String materialId;

    private String basicUnitMeasurement;

    private String orderId;

    private BigDecimal unitPrice;

    private Integer numbers;

    private Date deliveryTime;

    private Double discount;

    private Double rate;

    private BigDecimal taxUnitPrice;

    private BigDecimal actualTaxUnitPrice;

    private BigDecimal discountPrice;

    private BigDecimal taxPrice;

    private BigDecimal functionalCurrencyAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId == null ? null : materialId.trim();
    }

    public String getBasicUnitMeasurement() {
        return basicUnitMeasurement;
    }

    public void setBasicUnitMeasurement(String basicUnitMeasurement) {
        this.basicUnitMeasurement = basicUnitMeasurement == null ? null : basicUnitMeasurement.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public BigDecimal getTaxUnitPrice() {
        return taxUnitPrice;
    }

    public void setTaxUnitPrice(BigDecimal taxUnitPrice) {
        this.taxUnitPrice = taxUnitPrice;
    }

    public BigDecimal getActualTaxUnitPrice() {
        return actualTaxUnitPrice;
    }

    public void setActualTaxUnitPrice(BigDecimal actualTaxUnitPrice) {
        this.actualTaxUnitPrice = actualTaxUnitPrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }

    public BigDecimal getFunctionalCurrencyAmount() {
        return functionalCurrencyAmount;
    }

    public void setFunctionalCurrencyAmount(BigDecimal functionalCurrencyAmount) {
        this.functionalCurrencyAmount = functionalCurrencyAmount;
    }
}