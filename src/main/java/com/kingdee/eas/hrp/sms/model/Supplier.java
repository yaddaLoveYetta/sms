package com.kingdee.eas.hrp.sms.model;

public class Supplier {
    private Integer supplierId;

    private String supplierName;

    private String shortName;

    private Integer taxID;

    private String CORP;

    private String BRNO;

    private Integer taxCategoryID;

    private Integer taxRate;

    private String country;

    private String city;

    private String province;

    private String county;

    private String address;

    private String industryID;

    private Integer categoryID;

    private String createOrganization;

    private String banOrganization;

    private Integer status;

    private Integer certificateId;

    private Integer currencyId;

    private Integer settlementId;

    private Integer payId;

    private Integer itemId;

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public Integer getTaxID() {
        return taxID;
    }

    public void setTaxID(Integer taxID) {
        this.taxID = taxID;
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

    public Integer getTaxCategoryID() {
        return taxCategoryID;
    }

    public void setTaxCategoryID(Integer taxCategoryID) {
        this.taxCategoryID = taxCategoryID;
    }

    public Integer getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Integer taxRate) {
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

    public String getIndustryID() {
        return industryID;
    }

    public void setIndustryID(String industryID) {
        this.industryID = industryID == null ? null : industryID.trim();
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public Integer getSettlementId() {
        return settlementId;
    }

    public void setSettlementId(Integer settlementId) {
        this.settlementId = settlementId;
    }

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
}