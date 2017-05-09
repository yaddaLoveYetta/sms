package com.kingdee.eas.hrp.sms.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InvoiceExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("orderId is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("orderId is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("orderId =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("orderId <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("orderId >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("orderId >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("orderId <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("orderId <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("orderId like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("orderId not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("orderId in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("orderId not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("orderId between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("orderId not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderlineNumbersIsNull() {
            addCriterion("orderlineNumbers is null");
            return (Criteria) this;
        }

        public Criteria andOrderlineNumbersIsNotNull() {
            addCriterion("orderlineNumbers is not null");
            return (Criteria) this;
        }

        public Criteria andOrderlineNumbersEqualTo(Integer value) {
            addCriterion("orderlineNumbers =", value, "orderlineNumbers");
            return (Criteria) this;
        }

        public Criteria andOrderlineNumbersNotEqualTo(Integer value) {
            addCriterion("orderlineNumbers <>", value, "orderlineNumbers");
            return (Criteria) this;
        }

        public Criteria andOrderlineNumbersGreaterThan(Integer value) {
            addCriterion("orderlineNumbers >", value, "orderlineNumbers");
            return (Criteria) this;
        }

        public Criteria andOrderlineNumbersGreaterThanOrEqualTo(Integer value) {
            addCriterion("orderlineNumbers >=", value, "orderlineNumbers");
            return (Criteria) this;
        }

        public Criteria andOrderlineNumbersLessThan(Integer value) {
            addCriterion("orderlineNumbers <", value, "orderlineNumbers");
            return (Criteria) this;
        }

        public Criteria andOrderlineNumbersLessThanOrEqualTo(Integer value) {
            addCriterion("orderlineNumbers <=", value, "orderlineNumbers");
            return (Criteria) this;
        }

        public Criteria andOrderlineNumbersIn(List<Integer> values) {
            addCriterion("orderlineNumbers in", values, "orderlineNumbers");
            return (Criteria) this;
        }

        public Criteria andOrderlineNumbersNotIn(List<Integer> values) {
            addCriterion("orderlineNumbers not in", values, "orderlineNumbers");
            return (Criteria) this;
        }

        public Criteria andOrderlineNumbersBetween(Integer value1, Integer value2) {
            addCriterion("orderlineNumbers between", value1, value2, "orderlineNumbers");
            return (Criteria) this;
        }

        public Criteria andOrderlineNumbersNotBetween(Integer value1, Integer value2) {
            addCriterion("orderlineNumbers not between", value1, value2, "orderlineNumbers");
            return (Criteria) this;
        }

        public Criteria andMaterialCodeIsNull() {
            addCriterion("materialCode is null");
            return (Criteria) this;
        }

        public Criteria andMaterialCodeIsNotNull() {
            addCriterion("materialCode is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialCodeEqualTo(String value) {
            addCriterion("materialCode =", value, "materialCode");
            return (Criteria) this;
        }

        public Criteria andMaterialCodeNotEqualTo(String value) {
            addCriterion("materialCode <>", value, "materialCode");
            return (Criteria) this;
        }

        public Criteria andMaterialCodeGreaterThan(String value) {
            addCriterion("materialCode >", value, "materialCode");
            return (Criteria) this;
        }

        public Criteria andMaterialCodeGreaterThanOrEqualTo(String value) {
            addCriterion("materialCode >=", value, "materialCode");
            return (Criteria) this;
        }

        public Criteria andMaterialCodeLessThan(String value) {
            addCriterion("materialCode <", value, "materialCode");
            return (Criteria) this;
        }

        public Criteria andMaterialCodeLessThanOrEqualTo(String value) {
            addCriterion("materialCode <=", value, "materialCode");
            return (Criteria) this;
        }

        public Criteria andMaterialCodeLike(String value) {
            addCriterion("materialCode like", value, "materialCode");
            return (Criteria) this;
        }

        public Criteria andMaterialCodeNotLike(String value) {
            addCriterion("materialCode not like", value, "materialCode");
            return (Criteria) this;
        }

        public Criteria andMaterialCodeIn(List<String> values) {
            addCriterion("materialCode in", values, "materialCode");
            return (Criteria) this;
        }

        public Criteria andMaterialCodeNotIn(List<String> values) {
            addCriterion("materialCode not in", values, "materialCode");
            return (Criteria) this;
        }

        public Criteria andMaterialCodeBetween(String value1, String value2) {
            addCriterion("materialCode between", value1, value2, "materialCode");
            return (Criteria) this;
        }

        public Criteria andMaterialCodeNotBetween(String value1, String value2) {
            addCriterion("materialCode not between", value1, value2, "materialCode");
            return (Criteria) this;
        }

        public Criteria andMaterialNameIsNull() {
            addCriterion("materialName is null");
            return (Criteria) this;
        }

        public Criteria andMaterialNameIsNotNull() {
            addCriterion("materialName is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialNameEqualTo(String value) {
            addCriterion("materialName =", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameNotEqualTo(String value) {
            addCriterion("materialName <>", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameGreaterThan(String value) {
            addCriterion("materialName >", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameGreaterThanOrEqualTo(String value) {
            addCriterion("materialName >=", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameLessThan(String value) {
            addCriterion("materialName <", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameLessThanOrEqualTo(String value) {
            addCriterion("materialName <=", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameLike(String value) {
            addCriterion("materialName like", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameNotLike(String value) {
            addCriterion("materialName not like", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameIn(List<String> values) {
            addCriterion("materialName in", values, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameNotIn(List<String> values) {
            addCriterion("materialName not in", values, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameBetween(String value1, String value2) {
            addCriterion("materialName between", value1, value2, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameNotBetween(String value1, String value2) {
            addCriterion("materialName not between", value1, value2, "materialName");
            return (Criteria) this;
        }

        public Criteria andSpecificationsIsNull() {
            addCriterion("specifications is null");
            return (Criteria) this;
        }

        public Criteria andSpecificationsIsNotNull() {
            addCriterion("specifications is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificationsEqualTo(String value) {
            addCriterion("specifications =", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotEqualTo(String value) {
            addCriterion("specifications <>", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsGreaterThan(String value) {
            addCriterion("specifications >", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsGreaterThanOrEqualTo(String value) {
            addCriterion("specifications >=", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsLessThan(String value) {
            addCriterion("specifications <", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsLessThanOrEqualTo(String value) {
            addCriterion("specifications <=", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsLike(String value) {
            addCriterion("specifications like", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotLike(String value) {
            addCriterion("specifications not like", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsIn(List<String> values) {
            addCriterion("specifications in", values, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotIn(List<String> values) {
            addCriterion("specifications not in", values, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsBetween(String value1, String value2) {
            addCriterion("specifications between", value1, value2, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotBetween(String value1, String value2) {
            addCriterion("specifications not between", value1, value2, "specifications");
            return (Criteria) this;
        }

        public Criteria andBatchIsNull() {
            addCriterion("batch is null");
            return (Criteria) this;
        }

        public Criteria andBatchIsNotNull() {
            addCriterion("batch is not null");
            return (Criteria) this;
        }

        public Criteria andBatchEqualTo(String value) {
            addCriterion("batch =", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchNotEqualTo(String value) {
            addCriterion("batch <>", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchGreaterThan(String value) {
            addCriterion("batch >", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchGreaterThanOrEqualTo(String value) {
            addCriterion("batch >=", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchLessThan(String value) {
            addCriterion("batch <", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchLessThanOrEqualTo(String value) {
            addCriterion("batch <=", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchLike(String value) {
            addCriterion("batch like", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchNotLike(String value) {
            addCriterion("batch not like", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchIn(List<String> values) {
            addCriterion("batch in", values, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchNotIn(List<String> values) {
            addCriterion("batch not in", values, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchBetween(String value1, String value2) {
            addCriterion("batch between", value1, value2, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchNotBetween(String value1, String value2) {
            addCriterion("batch not between", value1, value2, "batch");
            return (Criteria) this;
        }

        public Criteria andProductBatchNumberIsNull() {
            addCriterion("productBatchNumber is null");
            return (Criteria) this;
        }

        public Criteria andProductBatchNumberIsNotNull() {
            addCriterion("productBatchNumber is not null");
            return (Criteria) this;
        }

        public Criteria andProductBatchNumberEqualTo(String value) {
            addCriterion("productBatchNumber =", value, "productBatchNumber");
            return (Criteria) this;
        }

        public Criteria andProductBatchNumberNotEqualTo(String value) {
            addCriterion("productBatchNumber <>", value, "productBatchNumber");
            return (Criteria) this;
        }

        public Criteria andProductBatchNumberGreaterThan(String value) {
            addCriterion("productBatchNumber >", value, "productBatchNumber");
            return (Criteria) this;
        }

        public Criteria andProductBatchNumberGreaterThanOrEqualTo(String value) {
            addCriterion("productBatchNumber >=", value, "productBatchNumber");
            return (Criteria) this;
        }

        public Criteria andProductBatchNumberLessThan(String value) {
            addCriterion("productBatchNumber <", value, "productBatchNumber");
            return (Criteria) this;
        }

        public Criteria andProductBatchNumberLessThanOrEqualTo(String value) {
            addCriterion("productBatchNumber <=", value, "productBatchNumber");
            return (Criteria) this;
        }

        public Criteria andProductBatchNumberLike(String value) {
            addCriterion("productBatchNumber like", value, "productBatchNumber");
            return (Criteria) this;
        }

        public Criteria andProductBatchNumberNotLike(String value) {
            addCriterion("productBatchNumber not like", value, "productBatchNumber");
            return (Criteria) this;
        }

        public Criteria andProductBatchNumberIn(List<String> values) {
            addCriterion("productBatchNumber in", values, "productBatchNumber");
            return (Criteria) this;
        }

        public Criteria andProductBatchNumberNotIn(List<String> values) {
            addCriterion("productBatchNumber not in", values, "productBatchNumber");
            return (Criteria) this;
        }

        public Criteria andProductBatchNumberBetween(String value1, String value2) {
            addCriterion("productBatchNumber between", value1, value2, "productBatchNumber");
            return (Criteria) this;
        }

        public Criteria andProductBatchNumberNotBetween(String value1, String value2) {
            addCriterion("productBatchNumber not between", value1, value2, "productBatchNumber");
            return (Criteria) this;
        }

        public Criteria andIndividualCodeIsNull() {
            addCriterion("individualCode is null");
            return (Criteria) this;
        }

        public Criteria andIndividualCodeIsNotNull() {
            addCriterion("individualCode is not null");
            return (Criteria) this;
        }

        public Criteria andIndividualCodeEqualTo(String value) {
            addCriterion("individualCode =", value, "individualCode");
            return (Criteria) this;
        }

        public Criteria andIndividualCodeNotEqualTo(String value) {
            addCriterion("individualCode <>", value, "individualCode");
            return (Criteria) this;
        }

        public Criteria andIndividualCodeGreaterThan(String value) {
            addCriterion("individualCode >", value, "individualCode");
            return (Criteria) this;
        }

        public Criteria andIndividualCodeGreaterThanOrEqualTo(String value) {
            addCriterion("individualCode >=", value, "individualCode");
            return (Criteria) this;
        }

        public Criteria andIndividualCodeLessThan(String value) {
            addCriterion("individualCode <", value, "individualCode");
            return (Criteria) this;
        }

        public Criteria andIndividualCodeLessThanOrEqualTo(String value) {
            addCriterion("individualCode <=", value, "individualCode");
            return (Criteria) this;
        }

        public Criteria andIndividualCodeLike(String value) {
            addCriterion("individualCode like", value, "individualCode");
            return (Criteria) this;
        }

        public Criteria andIndividualCodeNotLike(String value) {
            addCriterion("individualCode not like", value, "individualCode");
            return (Criteria) this;
        }

        public Criteria andIndividualCodeIn(List<String> values) {
            addCriterion("individualCode in", values, "individualCode");
            return (Criteria) this;
        }

        public Criteria andIndividualCodeNotIn(List<String> values) {
            addCriterion("individualCode not in", values, "individualCode");
            return (Criteria) this;
        }

        public Criteria andIndividualCodeBetween(String value1, String value2) {
            addCriterion("individualCode between", value1, value2, "individualCode");
            return (Criteria) this;
        }

        public Criteria andIndividualCodeNotBetween(String value1, String value2) {
            addCriterion("individualCode not between", value1, value2, "individualCode");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andBasicUnitMeasurementIsNull() {
            addCriterion("basicUnitMeasurement is null");
            return (Criteria) this;
        }

        public Criteria andBasicUnitMeasurementIsNotNull() {
            addCriterion("basicUnitMeasurement is not null");
            return (Criteria) this;
        }

        public Criteria andBasicUnitMeasurementEqualTo(String value) {
            addCriterion("basicUnitMeasurement =", value, "basicUnitMeasurement");
            return (Criteria) this;
        }

        public Criteria andBasicUnitMeasurementNotEqualTo(String value) {
            addCriterion("basicUnitMeasurement <>", value, "basicUnitMeasurement");
            return (Criteria) this;
        }

        public Criteria andBasicUnitMeasurementGreaterThan(String value) {
            addCriterion("basicUnitMeasurement >", value, "basicUnitMeasurement");
            return (Criteria) this;
        }

        public Criteria andBasicUnitMeasurementGreaterThanOrEqualTo(String value) {
            addCriterion("basicUnitMeasurement >=", value, "basicUnitMeasurement");
            return (Criteria) this;
        }

        public Criteria andBasicUnitMeasurementLessThan(String value) {
            addCriterion("basicUnitMeasurement <", value, "basicUnitMeasurement");
            return (Criteria) this;
        }

        public Criteria andBasicUnitMeasurementLessThanOrEqualTo(String value) {
            addCriterion("basicUnitMeasurement <=", value, "basicUnitMeasurement");
            return (Criteria) this;
        }

        public Criteria andBasicUnitMeasurementLike(String value) {
            addCriterion("basicUnitMeasurement like", value, "basicUnitMeasurement");
            return (Criteria) this;
        }

        public Criteria andBasicUnitMeasurementNotLike(String value) {
            addCriterion("basicUnitMeasurement not like", value, "basicUnitMeasurement");
            return (Criteria) this;
        }

        public Criteria andBasicUnitMeasurementIn(List<String> values) {
            addCriterion("basicUnitMeasurement in", values, "basicUnitMeasurement");
            return (Criteria) this;
        }

        public Criteria andBasicUnitMeasurementNotIn(List<String> values) {
            addCriterion("basicUnitMeasurement not in", values, "basicUnitMeasurement");
            return (Criteria) this;
        }

        public Criteria andBasicUnitMeasurementBetween(String value1, String value2) {
            addCriterion("basicUnitMeasurement between", value1, value2, "basicUnitMeasurement");
            return (Criteria) this;
        }

        public Criteria andBasicUnitMeasurementNotBetween(String value1, String value2) {
            addCriterion("basicUnitMeasurement not between", value1, value2, "basicUnitMeasurement");
            return (Criteria) this;
        }

        public Criteria andNumbersIsNull() {
            addCriterion("numbers is null");
            return (Criteria) this;
        }

        public Criteria andNumbersIsNotNull() {
            addCriterion("numbers is not null");
            return (Criteria) this;
        }

        public Criteria andNumbersEqualTo(Integer value) {
            addCriterion("numbers =", value, "numbers");
            return (Criteria) this;
        }

        public Criteria andNumbersNotEqualTo(Integer value) {
            addCriterion("numbers <>", value, "numbers");
            return (Criteria) this;
        }

        public Criteria andNumbersGreaterThan(Integer value) {
            addCriterion("numbers >", value, "numbers");
            return (Criteria) this;
        }

        public Criteria andNumbersGreaterThanOrEqualTo(Integer value) {
            addCriterion("numbers >=", value, "numbers");
            return (Criteria) this;
        }

        public Criteria andNumbersLessThan(Integer value) {
            addCriterion("numbers <", value, "numbers");
            return (Criteria) this;
        }

        public Criteria andNumbersLessThanOrEqualTo(Integer value) {
            addCriterion("numbers <=", value, "numbers");
            return (Criteria) this;
        }

        public Criteria andNumbersIn(List<Integer> values) {
            addCriterion("numbers in", values, "numbers");
            return (Criteria) this;
        }

        public Criteria andNumbersNotIn(List<Integer> values) {
            addCriterion("numbers not in", values, "numbers");
            return (Criteria) this;
        }

        public Criteria andNumbersBetween(Integer value1, Integer value2) {
            addCriterion("numbers between", value1, value2, "numbers");
            return (Criteria) this;
        }

        public Criteria andNumbersNotBetween(Integer value1, Integer value2) {
            addCriterion("numbers not between", value1, value2, "numbers");
            return (Criteria) this;
        }

        public Criteria andProductionTimeIsNull() {
            addCriterion("productionTime is null");
            return (Criteria) this;
        }

        public Criteria andProductionTimeIsNotNull() {
            addCriterion("productionTime is not null");
            return (Criteria) this;
        }

        public Criteria andProductionTimeEqualTo(Date value) {
            addCriterion("productionTime =", value, "productionTime");
            return (Criteria) this;
        }

        public Criteria andProductionTimeNotEqualTo(Date value) {
            addCriterion("productionTime <>", value, "productionTime");
            return (Criteria) this;
        }

        public Criteria andProductionTimeGreaterThan(Date value) {
            addCriterion("productionTime >", value, "productionTime");
            return (Criteria) this;
        }

        public Criteria andProductionTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("productionTime >=", value, "productionTime");
            return (Criteria) this;
        }

        public Criteria andProductionTimeLessThan(Date value) {
            addCriterion("productionTime <", value, "productionTime");
            return (Criteria) this;
        }

        public Criteria andProductionTimeLessThanOrEqualTo(Date value) {
            addCriterion("productionTime <=", value, "productionTime");
            return (Criteria) this;
        }

        public Criteria andProductionTimeIn(List<Date> values) {
            addCriterion("productionTime in", values, "productionTime");
            return (Criteria) this;
        }

        public Criteria andProductionTimeNotIn(List<Date> values) {
            addCriterion("productionTime not in", values, "productionTime");
            return (Criteria) this;
        }

        public Criteria andProductionTimeBetween(Date value1, Date value2) {
            addCriterion("productionTime between", value1, value2, "productionTime");
            return (Criteria) this;
        }

        public Criteria andProductionTimeNotBetween(Date value1, Date value2) {
            addCriterion("productionTime not between", value1, value2, "productionTime");
            return (Criteria) this;
        }

        public Criteria andProductionManufacturerIsNull() {
            addCriterion("productionManufacturer is null");
            return (Criteria) this;
        }

        public Criteria andProductionManufacturerIsNotNull() {
            addCriterion("productionManufacturer is not null");
            return (Criteria) this;
        }

        public Criteria andProductionManufacturerEqualTo(String value) {
            addCriterion("productionManufacturer =", value, "productionManufacturer");
            return (Criteria) this;
        }

        public Criteria andProductionManufacturerNotEqualTo(String value) {
            addCriterion("productionManufacturer <>", value, "productionManufacturer");
            return (Criteria) this;
        }

        public Criteria andProductionManufacturerGreaterThan(String value) {
            addCriterion("productionManufacturer >", value, "productionManufacturer");
            return (Criteria) this;
        }

        public Criteria andProductionManufacturerGreaterThanOrEqualTo(String value) {
            addCriterion("productionManufacturer >=", value, "productionManufacturer");
            return (Criteria) this;
        }

        public Criteria andProductionManufacturerLessThan(String value) {
            addCriterion("productionManufacturer <", value, "productionManufacturer");
            return (Criteria) this;
        }

        public Criteria andProductionManufacturerLessThanOrEqualTo(String value) {
            addCriterion("productionManufacturer <=", value, "productionManufacturer");
            return (Criteria) this;
        }

        public Criteria andProductionManufacturerLike(String value) {
            addCriterion("productionManufacturer like", value, "productionManufacturer");
            return (Criteria) this;
        }

        public Criteria andProductionManufacturerNotLike(String value) {
            addCriterion("productionManufacturer not like", value, "productionManufacturer");
            return (Criteria) this;
        }

        public Criteria andProductionManufacturerIn(List<String> values) {
            addCriterion("productionManufacturer in", values, "productionManufacturer");
            return (Criteria) this;
        }

        public Criteria andProductionManufacturerNotIn(List<String> values) {
            addCriterion("productionManufacturer not in", values, "productionManufacturer");
            return (Criteria) this;
        }

        public Criteria andProductionManufacturerBetween(String value1, String value2) {
            addCriterion("productionManufacturer between", value1, value2, "productionManufacturer");
            return (Criteria) this;
        }

        public Criteria andProductionManufacturerNotBetween(String value1, String value2) {
            addCriterion("productionManufacturer not between", value1, value2, "productionManufacturer");
            return (Criteria) this;
        }

        public Criteria andProductRegistrationNumberIsNull() {
            addCriterion("productRegistrationNumber is null");
            return (Criteria) this;
        }

        public Criteria andProductRegistrationNumberIsNotNull() {
            addCriterion("productRegistrationNumber is not null");
            return (Criteria) this;
        }

        public Criteria andProductRegistrationNumberEqualTo(Integer value) {
            addCriterion("productRegistrationNumber =", value, "productRegistrationNumber");
            return (Criteria) this;
        }

        public Criteria andProductRegistrationNumberNotEqualTo(Integer value) {
            addCriterion("productRegistrationNumber <>", value, "productRegistrationNumber");
            return (Criteria) this;
        }

        public Criteria andProductRegistrationNumberGreaterThan(Integer value) {
            addCriterion("productRegistrationNumber >", value, "productRegistrationNumber");
            return (Criteria) this;
        }

        public Criteria andProductRegistrationNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("productRegistrationNumber >=", value, "productRegistrationNumber");
            return (Criteria) this;
        }

        public Criteria andProductRegistrationNumberLessThan(Integer value) {
            addCriterion("productRegistrationNumber <", value, "productRegistrationNumber");
            return (Criteria) this;
        }

        public Criteria andProductRegistrationNumberLessThanOrEqualTo(Integer value) {
            addCriterion("productRegistrationNumber <=", value, "productRegistrationNumber");
            return (Criteria) this;
        }

        public Criteria andProductRegistrationNumberIn(List<Integer> values) {
            addCriterion("productRegistrationNumber in", values, "productRegistrationNumber");
            return (Criteria) this;
        }

        public Criteria andProductRegistrationNumberNotIn(List<Integer> values) {
            addCriterion("productRegistrationNumber not in", values, "productRegistrationNumber");
            return (Criteria) this;
        }

        public Criteria andProductRegistrationNumberBetween(Integer value1, Integer value2) {
            addCriterion("productRegistrationNumber between", value1, value2, "productRegistrationNumber");
            return (Criteria) this;
        }

        public Criteria andProductRegistrationNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("productRegistrationNumber not between", value1, value2, "productRegistrationNumber");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("[amount ] is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("[amount ] is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("[amount ] =", value, "amount ");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("[amount ] <>", value, "amount ");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("[amount ] >", value, "amount ");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("[amount ] >=", value, "amount ");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("[amount ] <", value, "amount ");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("[amount ] <=", value, "amount ");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("[amount ] in", values, "amount ");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("[amount ] not in", values, "amount ");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("[amount ] between", value1, value2, "amount ");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("[amount ] not between", value1, value2, "amount ");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeIsNull() {
            addCriterion("effectiveTime is null");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeIsNotNull() {
            addCriterion("effectiveTime is not null");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeEqualTo(String value) {
            addCriterion("effectiveTime =", value, "effectiveTime");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeNotEqualTo(String value) {
            addCriterion("effectiveTime <>", value, "effectiveTime");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeGreaterThan(String value) {
            addCriterion("effectiveTime >", value, "effectiveTime");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeGreaterThanOrEqualTo(String value) {
            addCriterion("effectiveTime >=", value, "effectiveTime");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeLessThan(String value) {
            addCriterion("effectiveTime <", value, "effectiveTime");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeLessThanOrEqualTo(String value) {
            addCriterion("effectiveTime <=", value, "effectiveTime");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeLike(String value) {
            addCriterion("effectiveTime like", value, "effectiveTime");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeNotLike(String value) {
            addCriterion("effectiveTime not like", value, "effectiveTime");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeIn(List<String> values) {
            addCriterion("effectiveTime in", values, "effectiveTime");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeNotIn(List<String> values) {
            addCriterion("effectiveTime not in", values, "effectiveTime");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeBetween(String value1, String value2) {
            addCriterion("effectiveTime between", value1, value2, "effectiveTime");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeNotBetween(String value1, String value2) {
            addCriterion("effectiveTime not between", value1, value2, "effectiveTime");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoIsNull() {
            addCriterion("invoiceNo is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoIsNotNull() {
            addCriterion("invoiceNo is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoEqualTo(String value) {
            addCriterion("invoiceNo =", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotEqualTo(String value) {
            addCriterion("invoiceNo <>", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoGreaterThan(String value) {
            addCriterion("invoiceNo >", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoGreaterThanOrEqualTo(String value) {
            addCriterion("invoiceNo >=", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoLessThan(String value) {
            addCriterion("invoiceNo <", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoLessThanOrEqualTo(String value) {
            addCriterion("invoiceNo <=", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoLike(String value) {
            addCriterion("invoiceNo like", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotLike(String value) {
            addCriterion("invoiceNo not like", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoIn(List<String> values) {
            addCriterion("invoiceNo in", values, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotIn(List<String> values) {
            addCriterion("invoiceNo not in", values, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoBetween(String value1, String value2) {
            addCriterion("invoiceNo between", value1, value2, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotBetween(String value1, String value2) {
            addCriterion("invoiceNo not between", value1, value2, "invoiceNo");
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