package com.kingdee.eas.hrp.sms.model;

import java.math.BigDecimal;

public class Supplier {
    private String id;

    private String name;

    private String taxId;

    private String CORP;

    private String BRNO;

    private String taxCategoryId;

    private BigDecimal taxRate;

    private String country;

    private String city;

    private String province;

    private String county;

    private String address;

    private String industryId;

    private String categoryId;

    private String createOrganization;

    private String banOrganization;

    private String status;

    private String currencyId;

    private String settlementId;

    private String payId;

    private String number;

    private String syncStatus;

    private String review;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId == null ? null : taxId.trim();
    }

    public String getCORP() {
        return CORP;
    }

    public void setCORP(String CORP) {
        this.CORP = CORP == null ? null : CORP.trim();
    }

    public String getBRNO() {
        return BRNO;
    }

    public void setBRNO(String BRNO) {
        this.BRNO = BRNO == null ? null : BRNO.trim();
    }

    public String getTaxCategoryId() {
        return taxCategoryId;
    }

    public void setTaxCategoryId(String taxCategoryId) {
        this.taxCategoryId = taxCategoryId == null ? null : taxCategoryId.trim();
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId == null ? null : industryId.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getCreateOrganization() {
        return createOrganization;
    }

    public void setCreateOrganization(String createOrganization) {
        this.createOrganization = createOrganization == null ? null : createOrganization.trim();
    }

    public String getBanOrganization() {
        return banOrganization;
    }

    public void setBanOrganization(String banOrganization) {
        this.banOrganization = banOrganization == null ? null : banOrganization.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId == null ? null : currencyId.trim();
    }

    public String getSettlementId() {
        return settlementId;
    }

    public void setSettlementId(String settlementId) {
        this.settlementId = settlementId == null ? null : settlementId.trim();
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId == null ? null : payId.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus == null ? null : syncStatus.trim();
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review == null ? null : review.trim();
    }
}