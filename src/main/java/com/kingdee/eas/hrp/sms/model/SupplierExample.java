package com.kingdee.eas.hrp.sms.model;

import java.util.ArrayList;
import java.util.List;

public class SupplierExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SupplierExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("[name] is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("[name] is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("[name] =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("[name] <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("[name] >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("[name] >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("[name] <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("[name] <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("[name] like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("[name] not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("[name] in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("[name] not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("[name] between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("[name] not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andTaxIdIsNull() {
            addCriterion("taxId is null");
            return (Criteria) this;
        }

        public Criteria andTaxIdIsNotNull() {
            addCriterion("taxId is not null");
            return (Criteria) this;
        }

        public Criteria andTaxIdEqualTo(Integer value) {
            addCriterion("taxId =", value, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdNotEqualTo(Integer value) {
            addCriterion("taxId <>", value, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdGreaterThan(Integer value) {
            addCriterion("taxId >", value, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("taxId >=", value, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdLessThan(Integer value) {
            addCriterion("taxId <", value, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdLessThanOrEqualTo(Integer value) {
            addCriterion("taxId <=", value, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdIn(List<Integer> values) {
            addCriterion("taxId in", values, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdNotIn(List<Integer> values) {
            addCriterion("taxId not in", values, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdBetween(Integer value1, Integer value2) {
            addCriterion("taxId between", value1, value2, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdNotBetween(Integer value1, Integer value2) {
            addCriterion("taxId not between", value1, value2, "taxId");
            return (Criteria) this;
        }

        public Criteria andCORPIsNull() {
            addCriterion("CORP is null");
            return (Criteria) this;
        }

        public Criteria andCORPIsNotNull() {
            addCriterion("CORP is not null");
            return (Criteria) this;
        }

        public Criteria andCORPEqualTo(String value) {
            addCriterion("CORP =", value, "CORP");
            return (Criteria) this;
        }

        public Criteria andCORPNotEqualTo(String value) {
            addCriterion("CORP <>", value, "CORP");
            return (Criteria) this;
        }

        public Criteria andCORPGreaterThan(String value) {
            addCriterion("CORP >", value, "CORP");
            return (Criteria) this;
        }

        public Criteria andCORPGreaterThanOrEqualTo(String value) {
            addCriterion("CORP >=", value, "CORP");
            return (Criteria) this;
        }

        public Criteria andCORPLessThan(String value) {
            addCriterion("CORP <", value, "CORP");
            return (Criteria) this;
        }

        public Criteria andCORPLessThanOrEqualTo(String value) {
            addCriterion("CORP <=", value, "CORP");
            return (Criteria) this;
        }

        public Criteria andCORPLike(String value) {
            addCriterion("CORP like", value, "CORP");
            return (Criteria) this;
        }

        public Criteria andCORPNotLike(String value) {
            addCriterion("CORP not like", value, "CORP");
            return (Criteria) this;
        }

        public Criteria andCORPIn(List<String> values) {
            addCriterion("CORP in", values, "CORP");
            return (Criteria) this;
        }

        public Criteria andCORPNotIn(List<String> values) {
            addCriterion("CORP not in", values, "CORP");
            return (Criteria) this;
        }

        public Criteria andCORPBetween(String value1, String value2) {
            addCriterion("CORP between", value1, value2, "CORP");
            return (Criteria) this;
        }

        public Criteria andCORPNotBetween(String value1, String value2) {
            addCriterion("CORP not between", value1, value2, "CORP");
            return (Criteria) this;
        }

        public Criteria andBRNOIsNull() {
            addCriterion("BRNO is null");
            return (Criteria) this;
        }

        public Criteria andBRNOIsNotNull() {
            addCriterion("BRNO is not null");
            return (Criteria) this;
        }

        public Criteria andBRNOEqualTo(String value) {
            addCriterion("BRNO =", value, "BRNO");
            return (Criteria) this;
        }

        public Criteria andBRNONotEqualTo(String value) {
            addCriterion("BRNO <>", value, "BRNO");
            return (Criteria) this;
        }

        public Criteria andBRNOGreaterThan(String value) {
            addCriterion("BRNO >", value, "BRNO");
            return (Criteria) this;
        }

        public Criteria andBRNOGreaterThanOrEqualTo(String value) {
            addCriterion("BRNO >=", value, "BRNO");
            return (Criteria) this;
        }

        public Criteria andBRNOLessThan(String value) {
            addCriterion("BRNO <", value, "BRNO");
            return (Criteria) this;
        }

        public Criteria andBRNOLessThanOrEqualTo(String value) {
            addCriterion("BRNO <=", value, "BRNO");
            return (Criteria) this;
        }

        public Criteria andBRNOLike(String value) {
            addCriterion("BRNO like", value, "BRNO");
            return (Criteria) this;
        }

        public Criteria andBRNONotLike(String value) {
            addCriterion("BRNO not like", value, "BRNO");
            return (Criteria) this;
        }

        public Criteria andBRNOIn(List<String> values) {
            addCriterion("BRNO in", values, "BRNO");
            return (Criteria) this;
        }

        public Criteria andBRNONotIn(List<String> values) {
            addCriterion("BRNO not in", values, "BRNO");
            return (Criteria) this;
        }

        public Criteria andBRNOBetween(String value1, String value2) {
            addCriterion("BRNO between", value1, value2, "BRNO");
            return (Criteria) this;
        }

        public Criteria andBRNONotBetween(String value1, String value2) {
            addCriterion("BRNO not between", value1, value2, "BRNO");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryIdIsNull() {
            addCriterion("taxCategoryId is null");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryIdIsNotNull() {
            addCriterion("taxCategoryId is not null");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryIdEqualTo(Integer value) {
            addCriterion("taxCategoryId =", value, "taxCategoryId");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryIdNotEqualTo(Integer value) {
            addCriterion("taxCategoryId <>", value, "taxCategoryId");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryIdGreaterThan(Integer value) {
            addCriterion("taxCategoryId >", value, "taxCategoryId");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("taxCategoryId >=", value, "taxCategoryId");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryIdLessThan(Integer value) {
            addCriterion("taxCategoryId <", value, "taxCategoryId");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("taxCategoryId <=", value, "taxCategoryId");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryIdIn(List<Integer> values) {
            addCriterion("taxCategoryId in", values, "taxCategoryId");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryIdNotIn(List<Integer> values) {
            addCriterion("taxCategoryId not in", values, "taxCategoryId");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("taxCategoryId between", value1, value2, "taxCategoryId");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("taxCategoryId not between", value1, value2, "taxCategoryId");
            return (Criteria) this;
        }

        public Criteria andTaxRateIsNull() {
            addCriterion("taxRate is null");
            return (Criteria) this;
        }

        public Criteria andTaxRateIsNotNull() {
            addCriterion("taxRate is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRateEqualTo(Integer value) {
            addCriterion("taxRate =", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotEqualTo(Integer value) {
            addCriterion("taxRate <>", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateGreaterThan(Integer value) {
            addCriterion("taxRate >", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("taxRate >=", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLessThan(Integer value) {
            addCriterion("taxRate <", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLessThanOrEqualTo(Integer value) {
            addCriterion("taxRate <=", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateIn(List<Integer> values) {
            addCriterion("taxRate in", values, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotIn(List<Integer> values) {
            addCriterion("taxRate not in", values, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateBetween(Integer value1, Integer value2) {
            addCriterion("taxRate between", value1, value2, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotBetween(Integer value1, Integer value2) {
            addCriterion("taxRate not between", value1, value2, "taxRate");
            return (Criteria) this;
        }

        public Criteria andCountryIsNull() {
            addCriterion("country is null");
            return (Criteria) this;
        }

        public Criteria andCountryIsNotNull() {
            addCriterion("country is not null");
            return (Criteria) this;
        }

        public Criteria andCountryEqualTo(String value) {
            addCriterion("country =", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotEqualTo(String value) {
            addCriterion("country <>", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThan(String value) {
            addCriterion("country >", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThanOrEqualTo(String value) {
            addCriterion("country >=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThan(String value) {
            addCriterion("country <", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThanOrEqualTo(String value) {
            addCriterion("country <=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLike(String value) {
            addCriterion("country like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotLike(String value) {
            addCriterion("country not like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryIn(List<String> values) {
            addCriterion("country in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotIn(List<String> values) {
            addCriterion("country not in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryBetween(String value1, String value2) {
            addCriterion("country between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotBetween(String value1, String value2) {
            addCriterion("country not between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCountyIsNull() {
            addCriterion("county is null");
            return (Criteria) this;
        }

        public Criteria andCountyIsNotNull() {
            addCriterion("county is not null");
            return (Criteria) this;
        }

        public Criteria andCountyEqualTo(String value) {
            addCriterion("county =", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotEqualTo(String value) {
            addCriterion("county <>", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyGreaterThan(String value) {
            addCriterion("county >", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyGreaterThanOrEqualTo(String value) {
            addCriterion("county >=", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLessThan(String value) {
            addCriterion("county <", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLessThanOrEqualTo(String value) {
            addCriterion("county <=", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLike(String value) {
            addCriterion("county like", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotLike(String value) {
            addCriterion("county not like", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyIn(List<String> values) {
            addCriterion("county in", values, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotIn(List<String> values) {
            addCriterion("county not in", values, "county");
            return (Criteria) this;
        }

        public Criteria andCountyBetween(String value1, String value2) {
            addCriterion("county between", value1, value2, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotBetween(String value1, String value2) {
            addCriterion("county not between", value1, value2, "county");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andIndustryIdIsNull() {
            addCriterion("industryId is null");
            return (Criteria) this;
        }

        public Criteria andIndustryIdIsNotNull() {
            addCriterion("industryId is not null");
            return (Criteria) this;
        }

        public Criteria andIndustryIdEqualTo(Integer value) {
            addCriterion("industryId =", value, "industryId");
            return (Criteria) this;
        }

        public Criteria andIndustryIdNotEqualTo(Integer value) {
            addCriterion("industryId <>", value, "industryId");
            return (Criteria) this;
        }

        public Criteria andIndustryIdGreaterThan(Integer value) {
            addCriterion("industryId >", value, "industryId");
            return (Criteria) this;
        }

        public Criteria andIndustryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("industryId >=", value, "industryId");
            return (Criteria) this;
        }

        public Criteria andIndustryIdLessThan(Integer value) {
            addCriterion("industryId <", value, "industryId");
            return (Criteria) this;
        }

        public Criteria andIndustryIdLessThanOrEqualTo(Integer value) {
            addCriterion("industryId <=", value, "industryId");
            return (Criteria) this;
        }

        public Criteria andIndustryIdIn(List<Integer> values) {
            addCriterion("industryId in", values, "industryId");
            return (Criteria) this;
        }

        public Criteria andIndustryIdNotIn(List<Integer> values) {
            addCriterion("industryId not in", values, "industryId");
            return (Criteria) this;
        }

        public Criteria andIndustryIdBetween(Integer value1, Integer value2) {
            addCriterion("industryId between", value1, value2, "industryId");
            return (Criteria) this;
        }

        public Criteria andIndustryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("industryId not between", value1, value2, "industryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("categoryId is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("categoryId is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Integer value) {
            addCriterion("categoryId =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Integer value) {
            addCriterion("categoryId <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Integer value) {
            addCriterion("categoryId >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("categoryId >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Integer value) {
            addCriterion("categoryId <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("categoryId <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Integer> values) {
            addCriterion("categoryId in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Integer> values) {
            addCriterion("categoryId not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("categoryId between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("categoryId not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCreateOrganizationIsNull() {
            addCriterion("createOrganization is null");
            return (Criteria) this;
        }

        public Criteria andCreateOrganizationIsNotNull() {
            addCriterion("createOrganization is not null");
            return (Criteria) this;
        }

        public Criteria andCreateOrganizationEqualTo(String value) {
            addCriterion("createOrganization =", value, "createOrganization");
            return (Criteria) this;
        }

        public Criteria andCreateOrganizationNotEqualTo(String value) {
            addCriterion("createOrganization <>", value, "createOrganization");
            return (Criteria) this;
        }

        public Criteria andCreateOrganizationGreaterThan(String value) {
            addCriterion("createOrganization >", value, "createOrganization");
            return (Criteria) this;
        }

        public Criteria andCreateOrganizationGreaterThanOrEqualTo(String value) {
            addCriterion("createOrganization >=", value, "createOrganization");
            return (Criteria) this;
        }

        public Criteria andCreateOrganizationLessThan(String value) {
            addCriterion("createOrganization <", value, "createOrganization");
            return (Criteria) this;
        }

        public Criteria andCreateOrganizationLessThanOrEqualTo(String value) {
            addCriterion("createOrganization <=", value, "createOrganization");
            return (Criteria) this;
        }

        public Criteria andCreateOrganizationLike(String value) {
            addCriterion("createOrganization like", value, "createOrganization");
            return (Criteria) this;
        }

        public Criteria andCreateOrganizationNotLike(String value) {
            addCriterion("createOrganization not like", value, "createOrganization");
            return (Criteria) this;
        }

        public Criteria andCreateOrganizationIn(List<String> values) {
            addCriterion("createOrganization in", values, "createOrganization");
            return (Criteria) this;
        }

        public Criteria andCreateOrganizationNotIn(List<String> values) {
            addCriterion("createOrganization not in", values, "createOrganization");
            return (Criteria) this;
        }

        public Criteria andCreateOrganizationBetween(String value1, String value2) {
            addCriterion("createOrganization between", value1, value2, "createOrganization");
            return (Criteria) this;
        }

        public Criteria andCreateOrganizationNotBetween(String value1, String value2) {
            addCriterion("createOrganization not between", value1, value2, "createOrganization");
            return (Criteria) this;
        }

        public Criteria andBanOrganizationIsNull() {
            addCriterion("banOrganization is null");
            return (Criteria) this;
        }

        public Criteria andBanOrganizationIsNotNull() {
            addCriterion("banOrganization is not null");
            return (Criteria) this;
        }

        public Criteria andBanOrganizationEqualTo(String value) {
            addCriterion("banOrganization =", value, "banOrganization");
            return (Criteria) this;
        }

        public Criteria andBanOrganizationNotEqualTo(String value) {
            addCriterion("banOrganization <>", value, "banOrganization");
            return (Criteria) this;
        }

        public Criteria andBanOrganizationGreaterThan(String value) {
            addCriterion("banOrganization >", value, "banOrganization");
            return (Criteria) this;
        }

        public Criteria andBanOrganizationGreaterThanOrEqualTo(String value) {
            addCriterion("banOrganization >=", value, "banOrganization");
            return (Criteria) this;
        }

        public Criteria andBanOrganizationLessThan(String value) {
            addCriterion("banOrganization <", value, "banOrganization");
            return (Criteria) this;
        }

        public Criteria andBanOrganizationLessThanOrEqualTo(String value) {
            addCriterion("banOrganization <=", value, "banOrganization");
            return (Criteria) this;
        }

        public Criteria andBanOrganizationLike(String value) {
            addCriterion("banOrganization like", value, "banOrganization");
            return (Criteria) this;
        }

        public Criteria andBanOrganizationNotLike(String value) {
            addCriterion("banOrganization not like", value, "banOrganization");
            return (Criteria) this;
        }

        public Criteria andBanOrganizationIn(List<String> values) {
            addCriterion("banOrganization in", values, "banOrganization");
            return (Criteria) this;
        }

        public Criteria andBanOrganizationNotIn(List<String> values) {
            addCriterion("banOrganization not in", values, "banOrganization");
            return (Criteria) this;
        }

        public Criteria andBanOrganizationBetween(String value1, String value2) {
            addCriterion("banOrganization between", value1, value2, "banOrganization");
            return (Criteria) this;
        }

        public Criteria andBanOrganizationNotBetween(String value1, String value2) {
            addCriterion("banOrganization not between", value1, value2, "banOrganization");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("[status] is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("[status] is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("[status] =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("[status] <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("[status] >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("[status] >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("[status] <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("[status] <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("[status] in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("[status] not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("[status] between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("[status] not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCertificateIdIsNull() {
            addCriterion("certificateId is null");
            return (Criteria) this;
        }

        public Criteria andCertificateIdIsNotNull() {
            addCriterion("certificateId is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateIdEqualTo(Integer value) {
            addCriterion("certificateId =", value, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdNotEqualTo(Integer value) {
            addCriterion("certificateId <>", value, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdGreaterThan(Integer value) {
            addCriterion("certificateId >", value, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("certificateId >=", value, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdLessThan(Integer value) {
            addCriterion("certificateId <", value, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdLessThanOrEqualTo(Integer value) {
            addCriterion("certificateId <=", value, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdIn(List<Integer> values) {
            addCriterion("certificateId in", values, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdNotIn(List<Integer> values) {
            addCriterion("certificateId not in", values, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdBetween(Integer value1, Integer value2) {
            addCriterion("certificateId between", value1, value2, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("certificateId not between", value1, value2, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCurrencyIdIsNull() {
            addCriterion("currencyId is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIdIsNotNull() {
            addCriterion("currencyId is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIdEqualTo(Integer value) {
            addCriterion("currencyId =", value, "currencyId");
            return (Criteria) this;
        }

        public Criteria andCurrencyIdNotEqualTo(Integer value) {
            addCriterion("currencyId <>", value, "currencyId");
            return (Criteria) this;
        }

        public Criteria andCurrencyIdGreaterThan(Integer value) {
            addCriterion("currencyId >", value, "currencyId");
            return (Criteria) this;
        }

        public Criteria andCurrencyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("currencyId >=", value, "currencyId");
            return (Criteria) this;
        }

        public Criteria andCurrencyIdLessThan(Integer value) {
            addCriterion("currencyId <", value, "currencyId");
            return (Criteria) this;
        }

        public Criteria andCurrencyIdLessThanOrEqualTo(Integer value) {
            addCriterion("currencyId <=", value, "currencyId");
            return (Criteria) this;
        }

        public Criteria andCurrencyIdIn(List<Integer> values) {
            addCriterion("currencyId in", values, "currencyId");
            return (Criteria) this;
        }

        public Criteria andCurrencyIdNotIn(List<Integer> values) {
            addCriterion("currencyId not in", values, "currencyId");
            return (Criteria) this;
        }

        public Criteria andCurrencyIdBetween(Integer value1, Integer value2) {
            addCriterion("currencyId between", value1, value2, "currencyId");
            return (Criteria) this;
        }

        public Criteria andCurrencyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("currencyId not between", value1, value2, "currencyId");
            return (Criteria) this;
        }

        public Criteria andSettlementIdIsNull() {
            addCriterion("settlementId is null");
            return (Criteria) this;
        }

        public Criteria andSettlementIdIsNotNull() {
            addCriterion("settlementId is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementIdEqualTo(Integer value) {
            addCriterion("settlementId =", value, "settlementId");
            return (Criteria) this;
        }

        public Criteria andSettlementIdNotEqualTo(Integer value) {
            addCriterion("settlementId <>", value, "settlementId");
            return (Criteria) this;
        }

        public Criteria andSettlementIdGreaterThan(Integer value) {
            addCriterion("settlementId >", value, "settlementId");
            return (Criteria) this;
        }

        public Criteria andSettlementIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("settlementId >=", value, "settlementId");
            return (Criteria) this;
        }

        public Criteria andSettlementIdLessThan(Integer value) {
            addCriterion("settlementId <", value, "settlementId");
            return (Criteria) this;
        }

        public Criteria andSettlementIdLessThanOrEqualTo(Integer value) {
            addCriterion("settlementId <=", value, "settlementId");
            return (Criteria) this;
        }

        public Criteria andSettlementIdIn(List<Integer> values) {
            addCriterion("settlementId in", values, "settlementId");
            return (Criteria) this;
        }

        public Criteria andSettlementIdNotIn(List<Integer> values) {
            addCriterion("settlementId not in", values, "settlementId");
            return (Criteria) this;
        }

        public Criteria andSettlementIdBetween(Integer value1, Integer value2) {
            addCriterion("settlementId between", value1, value2, "settlementId");
            return (Criteria) this;
        }

        public Criteria andSettlementIdNotBetween(Integer value1, Integer value2) {
            addCriterion("settlementId not between", value1, value2, "settlementId");
            return (Criteria) this;
        }

        public Criteria andPayIdIsNull() {
            addCriterion("payId is null");
            return (Criteria) this;
        }

        public Criteria andPayIdIsNotNull() {
            addCriterion("payId is not null");
            return (Criteria) this;
        }

        public Criteria andPayIdEqualTo(Integer value) {
            addCriterion("payId =", value, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdNotEqualTo(Integer value) {
            addCriterion("payId <>", value, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdGreaterThan(Integer value) {
            addCriterion("payId >", value, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("payId >=", value, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdLessThan(Integer value) {
            addCriterion("payId <", value, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdLessThanOrEqualTo(Integer value) {
            addCriterion("payId <=", value, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdIn(List<Integer> values) {
            addCriterion("payId in", values, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdNotIn(List<Integer> values) {
            addCriterion("payId not in", values, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdBetween(Integer value1, Integer value2) {
            addCriterion("payId between", value1, value2, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdNotBetween(Integer value1, Integer value2) {
            addCriterion("payId not between", value1, value2, "payId");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNull() {
            addCriterion("itemId is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("itemId is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(Integer value) {
            addCriterion("itemId =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(Integer value) {
            addCriterion("itemId <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(Integer value) {
            addCriterion("itemId >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("itemId >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(Integer value) {
            addCriterion("itemId <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("itemId <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<Integer> values) {
            addCriterion("itemId in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<Integer> values) {
            addCriterion("itemId not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(Integer value1, Integer value2) {
            addCriterion("itemId between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("itemId not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("[number] is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("[number] is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(String value) {
            addCriterion("[number] =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(String value) {
            addCriterion("[number] <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(String value) {
            addCriterion("[number] >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(String value) {
            addCriterion("[number] >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(String value) {
            addCriterion("[number] <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(String value) {
            addCriterion("[number] <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLike(String value) {
            addCriterion("[number] like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotLike(String value) {
            addCriterion("[number] not like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<String> values) {
            addCriterion("[number] in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<String> values) {
            addCriterion("[number] not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(String value1, String value2) {
            addCriterion("[number] between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(String value1, String value2) {
            addCriterion("[number] not between", value1, value2, "number");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}