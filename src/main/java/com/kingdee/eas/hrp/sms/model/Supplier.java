package com.kingdee.eas.hrp.sms.model;

public class Supplier {
    private String id;

    private String name;

    private Integer taxId;

    private String CORP;

    private String BRNO;

    private Integer taxCategoryId;

    private Integer taxRate;

    private String country;

    private String city;

    private String province;

    private String county;

    private String address;

    private Integer industryId;

    private Integer categoryId;

    private String createOrganization;

    private String banOrganization;

    private Integer status;

    private Integer certificateId;

    private Integer currencyId;

    private Integer settlementId;

    private Integer payId;

    private Integer itemId;

    private String number;

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

    public Integer getTaxId() {
        return taxId;
    }

    public void setTaxId(Integer taxId) {
        this.taxId = taxId;
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

    public Integer getTaxCategoryId() {
        return taxCategoryId;
    }

    public void setTaxCategoryId(Integer taxCategoryId) {
        this.taxCategoryId = taxCategoryId;
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

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }
}