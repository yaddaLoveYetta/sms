package com.kingdee.eas.hrp.sms.model;

public class TaxCategory {
    private Integer taxCategoryId;

    private String taxCategoryName;

    public Integer getTaxCategoryId() {
        return taxCategoryId;
    }

    public void setTaxCategoryId(Integer taxCategoryId) {
        this.taxCategoryId = taxCategoryId;
    }

    public String getTaxCategoryName() {
        return taxCategoryName;
    }

    public void setTaxCategoryName(String taxCategoryName) {
        this.taxCategoryName = taxCategoryName == null ? null : taxCategoryName.trim();
    }
}