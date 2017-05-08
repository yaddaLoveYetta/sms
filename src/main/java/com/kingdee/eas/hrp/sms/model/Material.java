package com.kingdee.eas.hrp.sms.model;

public class Material {
    private Integer id;

    private String materialCode;

    private String materialName;

    private String specifications;

    private String basicUnitMeasurement;

    private String orderId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}