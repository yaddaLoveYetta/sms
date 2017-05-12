package com.kingdee.eas.hrp.sms.model;

import java.math.BigDecimal;
import java.util.Date;

public class Material {
    private Integer id;

    private String supplierMaterialNumber;

    private String noNumMaterialModel;

    private String orderId;

    private BigDecimal price;

    private Integer qty;

    private Date deliveryDate;

    private Double discountRate;

    private Double taxRate;

    private BigDecimal taxPrice;

    private BigDecimal actualTaxPrice;

    private BigDecimal discountPrice;

    private BigDecimal tax;

    private BigDecimal localPrice;

    private Integer lineNumbers;

    private Date confirmDate;

    private Integer confirmQty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupplierMaterialNumber() {
        return supplierMaterialNumber;
    }

    public void setSupplierMaterialNumber(String supplierMaterialNumber) {
        this.supplierMaterialNumber = supplierMaterialNumber == null ? null : supplierMaterialNumber.trim();
    }

    public String getNoNumMaterialModel() {
        return noNumMaterialModel;
    }

    public void setNoNumMaterialModel(String noNumMaterialModel) {
        this.noNumMaterialModel = noNumMaterialModel == null ? null : noNumMaterialModel.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }

    public BigDecimal getActualTaxPrice() {
        return actualTaxPrice;
    }

    public void setActualTaxPrice(BigDecimal actualTaxPrice) {
        this.actualTaxPrice = actualTaxPrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getLocalPrice() {
        return localPrice;
    }

    public void setLocalPrice(BigDecimal localPrice) {
        this.localPrice = localPrice;
    }

    public Integer getLineNumbers() {
        return lineNumbers;
    }

    public void setLineNumbers(Integer lineNumbers) {
        this.lineNumbers = lineNumbers;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public Integer getConfirmQty() {
        return confirmQty;
    }

    public void setConfirmQty(Integer confirmQty) {
        this.confirmQty = confirmQty;
    }
}