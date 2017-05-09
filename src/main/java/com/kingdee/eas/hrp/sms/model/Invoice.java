package com.kingdee.eas.hrp.sms.model;

import java.math.BigDecimal;
import java.util.Date;

public class Invoice {
    private Integer id;

    private String orderId;

    private Integer orderlineNumbers;

    private String materialCode;

    private String materialName;

    private String specifications;

    private String batch;

    private String productBatchNumber;

    private String individualCode;

    private BigDecimal price;

    private String basicUnitMeasurement;

    private Integer numbers;

    private Date productionTime;

    private String productionManufacturer;

    private Integer productRegistrationNumber;

    private BigDecimal amount ;

    private String effectiveTime;

    private String invoiceNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getOrderlineNumbers() {
        return orderlineNumbers;
    }

    public void setOrderlineNumbers(Integer orderlineNumbers) {
        this.orderlineNumbers = orderlineNumbers;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode == null ? null : materialCode.trim();
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName == null ? null : materialName.trim();
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications == null ? null : specifications.trim();
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch == null ? null : batch.trim();
    }

    public String getProductBatchNumber() {
        return productBatchNumber;
    }

    public void setProductBatchNumber(String productBatchNumber) {
        this.productBatchNumber = productBatchNumber == null ? null : productBatchNumber.trim();
    }

    public String getIndividualCode() {
        return individualCode;
    }

    public void setIndividualCode(String individualCode) {
        this.individualCode = individualCode == null ? null : individualCode.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBasicUnitMeasurement() {
        return basicUnitMeasurement;
    }

    public void setBasicUnitMeasurement(String basicUnitMeasurement) {
        this.basicUnitMeasurement = basicUnitMeasurement == null ? null : basicUnitMeasurement.trim();
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public Date getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(Date productionTime) {
        this.productionTime = productionTime;
    }

    public String getProductionManufacturer() {
        return productionManufacturer;
    }

    public void setProductionManufacturer(String productionManufacturer) {
        this.productionManufacturer = productionManufacturer == null ? null : productionManufacturer.trim();
    }

    public Integer getProductRegistrationNumber() {
        return productRegistrationNumber;
    }

    public void setProductRegistrationNumber(Integer productRegistrationNumber) {
        this.productRegistrationNumber = productRegistrationNumber;
    }

    public BigDecimal getAmount () {
        return amount ;
    }

    public void setAmount (BigDecimal amount ) {
        this.amount  = amount ;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime == null ? null : effectiveTime.trim();
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo == null ? null : invoiceNo.trim();
    }
}