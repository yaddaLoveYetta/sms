package com.kingdee.eas.hrp.sms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderExample() {
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

        public Criteria andLineNumbersIsNull() {
            addCriterion("lineNumbers is null");
            return (Criteria) this;
        }

        public Criteria andLineNumbersIsNotNull() {
            addCriterion("lineNumbers is not null");
            return (Criteria) this;
        }

        public Criteria andLineNumbersEqualTo(Integer value) {
            addCriterion("lineNumbers =", value, "lineNumbers");
            return (Criteria) this;
        }

        public Criteria andLineNumbersNotEqualTo(Integer value) {
            addCriterion("lineNumbers <>", value, "lineNumbers");
            return (Criteria) this;
        }

        public Criteria andLineNumbersGreaterThan(Integer value) {
            addCriterion("lineNumbers >", value, "lineNumbers");
            return (Criteria) this;
        }

        public Criteria andLineNumbersGreaterThanOrEqualTo(Integer value) {
            addCriterion("lineNumbers >=", value, "lineNumbers");
            return (Criteria) this;
        }

        public Criteria andLineNumbersLessThan(Integer value) {
            addCriterion("lineNumbers <", value, "lineNumbers");
            return (Criteria) this;
        }

        public Criteria andLineNumbersLessThanOrEqualTo(Integer value) {
            addCriterion("lineNumbers <=", value, "lineNumbers");
            return (Criteria) this;
        }

        public Criteria andLineNumbersIn(List<Integer> values) {
            addCriterion("lineNumbers in", values, "lineNumbers");
            return (Criteria) this;
        }

        public Criteria andLineNumbersNotIn(List<Integer> values) {
            addCriterion("lineNumbers not in", values, "lineNumbers");
            return (Criteria) this;
        }

        public Criteria andLineNumbersBetween(Integer value1, Integer value2) {
            addCriterion("lineNumbers between", value1, value2, "lineNumbers");
            return (Criteria) this;
        }

        public Criteria andLineNumbersNotBetween(Integer value1, Integer value2) {
            addCriterion("lineNumbers not between", value1, value2, "lineNumbers");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNull() {
            addCriterion("supplierId is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNotNull() {
            addCriterion("supplierId is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdEqualTo(String value) {
            addCriterion("supplierId =", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotEqualTo(String value) {
            addCriterion("supplierId <>", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThan(String value) {
            addCriterion("supplierId >", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThanOrEqualTo(String value) {
            addCriterion("supplierId >=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThan(String value) {
            addCriterion("supplierId <", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThanOrEqualTo(String value) {
            addCriterion("supplierId <=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLike(String value) {
            addCriterion("supplierId like", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotLike(String value) {
            addCriterion("supplierId not like", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIn(List<String> values) {
            addCriterion("supplierId in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotIn(List<String> values) {
            addCriterion("supplierId not in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdBetween(String value1, String value2) {
            addCriterion("supplierId between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotBetween(String value1, String value2) {
            addCriterion("supplierId not between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierNameIsNull() {
            addCriterion("supplierName is null");
            return (Criteria) this;
        }

        public Criteria andSupplierNameIsNotNull() {
            addCriterion("supplierName is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierNameEqualTo(String value) {
            addCriterion("supplierName =", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameNotEqualTo(String value) {
            addCriterion("supplierName <>", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameGreaterThan(String value) {
            addCriterion("supplierName >", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameGreaterThanOrEqualTo(String value) {
            addCriterion("supplierName >=", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameLessThan(String value) {
            addCriterion("supplierName <", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameLessThanOrEqualTo(String value) {
            addCriterion("supplierName <=", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameLike(String value) {
            addCriterion("supplierName like", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameNotLike(String value) {
            addCriterion("supplierName not like", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameIn(List<String> values) {
            addCriterion("supplierName in", values, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameNotIn(List<String> values) {
            addCriterion("supplierName not in", values, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameBetween(String value1, String value2) {
            addCriterion("supplierName between", value1, value2, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameNotBetween(String value1, String value2) {
            addCriterion("supplierName not between", value1, value2, "supplierName");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNull() {
            addCriterion("orderTime is null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNotNull() {
            addCriterion("orderTime is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeEqualTo(Date value) {
            addCriterion("orderTime =", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotEqualTo(Date value) {
            addCriterion("orderTime <>", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThan(Date value) {
            addCriterion("orderTime >", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("orderTime >=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThan(Date value) {
            addCriterion("orderTime <", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThanOrEqualTo(Date value) {
            addCriterion("orderTime <=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIn(List<Date> values) {
            addCriterion("orderTime in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotIn(List<Date> values) {
            addCriterion("orderTime not in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeBetween(Date value1, Date value2) {
            addCriterion("orderTime between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotBetween(Date value1, Date value2) {
            addCriterion("orderTime not between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andBuyerIsNull() {
            addCriterion("buyer is null");
            return (Criteria) this;
        }

        public Criteria andBuyerIsNotNull() {
            addCriterion("buyer is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerEqualTo(String value) {
            addCriterion("buyer =", value, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerNotEqualTo(String value) {
            addCriterion("buyer <>", value, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerGreaterThan(String value) {
            addCriterion("buyer >", value, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerGreaterThanOrEqualTo(String value) {
            addCriterion("buyer >=", value, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerLessThan(String value) {
            addCriterion("buyer <", value, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerLessThanOrEqualTo(String value) {
            addCriterion("buyer <=", value, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerLike(String value) {
            addCriterion("buyer like", value, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerNotLike(String value) {
            addCriterion("buyer not like", value, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerIn(List<String> values) {
            addCriterion("buyer in", values, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerNotIn(List<String> values) {
            addCriterion("buyer not in", values, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerBetween(String value1, String value2) {
            addCriterion("buyer between", value1, value2, "buyer");
            return (Criteria) this;
        }

        public Criteria andBuyerNotBetween(String value1, String value2) {
            addCriterion("buyer not between", value1, value2, "buyer");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeIsNull() {
            addCriterion("purchasingMode is null");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeIsNotNull() {
            addCriterion("purchasingMode is not null");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeEqualTo(Integer value) {
            addCriterion("purchasingMode =", value, "purchasingMode");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeNotEqualTo(Integer value) {
            addCriterion("purchasingMode <>", value, "purchasingMode");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeGreaterThan(Integer value) {
            addCriterion("purchasingMode >", value, "purchasingMode");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("purchasingMode >=", value, "purchasingMode");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeLessThan(Integer value) {
            addCriterion("purchasingMode <", value, "purchasingMode");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeLessThanOrEqualTo(Integer value) {
            addCriterion("purchasingMode <=", value, "purchasingMode");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeIn(List<Integer> values) {
            addCriterion("purchasingMode in", values, "purchasingMode");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeNotIn(List<Integer> values) {
            addCriterion("purchasingMode not in", values, "purchasingMode");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeBetween(Integer value1, Integer value2) {
            addCriterion("purchasingMode between", value1, value2, "purchasingMode");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeNotBetween(Integer value1, Integer value2) {
            addCriterion("purchasingMode not between", value1, value2, "purchasingMode");
            return (Criteria) this;
        }

        public Criteria andTaxIsNull() {
            addCriterion("tax is null");
            return (Criteria) this;
        }

        public Criteria andTaxIsNotNull() {
            addCriterion("tax is not null");
            return (Criteria) this;
        }

        public Criteria andTaxEqualTo(Integer value) {
            addCriterion("tax =", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotEqualTo(Integer value) {
            addCriterion("tax <>", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxGreaterThan(Integer value) {
            addCriterion("tax >", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("tax >=", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxLessThan(Integer value) {
            addCriterion("tax <", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxLessThanOrEqualTo(Integer value) {
            addCriterion("tax <=", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxIn(List<Integer> values) {
            addCriterion("tax in", values, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotIn(List<Integer> values) {
            addCriterion("tax not in", values, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxBetween(Integer value1, Integer value2) {
            addCriterion("tax between", value1, value2, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotBetween(Integer value1, Integer value2) {
            addCriterion("tax not between", value1, value2, "tax");
            return (Criteria) this;
        }

        public Criteria andCutasingleTimeIsNull() {
            addCriterion("cutasingleTime is null");
            return (Criteria) this;
        }

        public Criteria andCutasingleTimeIsNotNull() {
            addCriterion("cutasingleTime is not null");
            return (Criteria) this;
        }

        public Criteria andCutasingleTimeEqualTo(Date value) {
            addCriterion("cutasingleTime =", value, "cutasingleTime");
            return (Criteria) this;
        }

        public Criteria andCutasingleTimeNotEqualTo(Date value) {
            addCriterion("cutasingleTime <>", value, "cutasingleTime");
            return (Criteria) this;
        }

        public Criteria andCutasingleTimeGreaterThan(Date value) {
            addCriterion("cutasingleTime >", value, "cutasingleTime");
            return (Criteria) this;
        }

        public Criteria andCutasingleTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("cutasingleTime >=", value, "cutasingleTime");
            return (Criteria) this;
        }

        public Criteria andCutasingleTimeLessThan(Date value) {
            addCriterion("cutasingleTime <", value, "cutasingleTime");
            return (Criteria) this;
        }

        public Criteria andCutasingleTimeLessThanOrEqualTo(Date value) {
            addCriterion("cutasingleTime <=", value, "cutasingleTime");
            return (Criteria) this;
        }

        public Criteria andCutasingleTimeIn(List<Date> values) {
            addCriterion("cutasingleTime in", values, "cutasingleTime");
            return (Criteria) this;
        }

        public Criteria andCutasingleTimeNotIn(List<Date> values) {
            addCriterion("cutasingleTime not in", values, "cutasingleTime");
            return (Criteria) this;
        }

        public Criteria andCutasingleTimeBetween(Date value1, Date value2) {
            addCriterion("cutasingleTime between", value1, value2, "cutasingleTime");
            return (Criteria) this;
        }

        public Criteria andCutasingleTimeNotBetween(Date value1, Date value2) {
            addCriterion("cutasingleTime not between", value1, value2, "cutasingleTime");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryTimeIsNull() {
            addCriterion("confirmDeliveryTime is null");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryTimeIsNotNull() {
            addCriterion("confirmDeliveryTime is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryTimeEqualTo(Date value) {
            addCriterion("confirmDeliveryTime =", value, "confirmDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryTimeNotEqualTo(Date value) {
            addCriterion("confirmDeliveryTime <>", value, "confirmDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryTimeGreaterThan(Date value) {
            addCriterion("confirmDeliveryTime >", value, "confirmDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("confirmDeliveryTime >=", value, "confirmDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryTimeLessThan(Date value) {
            addCriterion("confirmDeliveryTime <", value, "confirmDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryTimeLessThanOrEqualTo(Date value) {
            addCriterion("confirmDeliveryTime <=", value, "confirmDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryTimeIn(List<Date> values) {
            addCriterion("confirmDeliveryTime in", values, "confirmDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryTimeNotIn(List<Date> values) {
            addCriterion("confirmDeliveryTime not in", values, "confirmDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryTimeBetween(Date value1, Date value2) {
            addCriterion("confirmDeliveryTime between", value1, value2, "confirmDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryTimeNotBetween(Date value1, Date value2) {
            addCriterion("confirmDeliveryTime not between", value1, value2, "confirmDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryNumbersIsNull() {
            addCriterion("confirmDeliveryNumbers is null");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryNumbersIsNotNull() {
            addCriterion("confirmDeliveryNumbers is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryNumbersEqualTo(Integer value) {
            addCriterion("confirmDeliveryNumbers =", value, "confirmDeliveryNumbers");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryNumbersNotEqualTo(Integer value) {
            addCriterion("confirmDeliveryNumbers <>", value, "confirmDeliveryNumbers");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryNumbersGreaterThan(Integer value) {
            addCriterion("confirmDeliveryNumbers >", value, "confirmDeliveryNumbers");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryNumbersGreaterThanOrEqualTo(Integer value) {
            addCriterion("confirmDeliveryNumbers >=", value, "confirmDeliveryNumbers");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryNumbersLessThan(Integer value) {
            addCriterion("confirmDeliveryNumbers <", value, "confirmDeliveryNumbers");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryNumbersLessThanOrEqualTo(Integer value) {
            addCriterion("confirmDeliveryNumbers <=", value, "confirmDeliveryNumbers");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryNumbersIn(List<Integer> values) {
            addCriterion("confirmDeliveryNumbers in", values, "confirmDeliveryNumbers");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryNumbersNotIn(List<Integer> values) {
            addCriterion("confirmDeliveryNumbers not in", values, "confirmDeliveryNumbers");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryNumbersBetween(Integer value1, Integer value2) {
            addCriterion("confirmDeliveryNumbers between", value1, value2, "confirmDeliveryNumbers");
            return (Criteria) this;
        }

        public Criteria andConfirmDeliveryNumbersNotBetween(Integer value1, Integer value2) {
            addCriterion("confirmDeliveryNumbers not between", value1, value2, "confirmDeliveryNumbers");
            return (Criteria) this;
        }

        public Criteria andConfirmOrderIsNull() {
            addCriterion("confirmOrder is null");
            return (Criteria) this;
        }

        public Criteria andConfirmOrderIsNotNull() {
            addCriterion("confirmOrder is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmOrderEqualTo(Integer value) {
            addCriterion("confirmOrder =", value, "confirmOrder");
            return (Criteria) this;
        }

        public Criteria andConfirmOrderNotEqualTo(Integer value) {
            addCriterion("confirmOrder <>", value, "confirmOrder");
            return (Criteria) this;
        }

        public Criteria andConfirmOrderGreaterThan(Integer value) {
            addCriterion("confirmOrder >", value, "confirmOrder");
            return (Criteria) this;
        }

        public Criteria andConfirmOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("confirmOrder >=", value, "confirmOrder");
            return (Criteria) this;
        }

        public Criteria andConfirmOrderLessThan(Integer value) {
            addCriterion("confirmOrder <", value, "confirmOrder");
            return (Criteria) this;
        }

        public Criteria andConfirmOrderLessThanOrEqualTo(Integer value) {
            addCriterion("confirmOrder <=", value, "confirmOrder");
            return (Criteria) this;
        }

        public Criteria andConfirmOrderIn(List<Integer> values) {
            addCriterion("confirmOrder in", values, "confirmOrder");
            return (Criteria) this;
        }

        public Criteria andConfirmOrderNotIn(List<Integer> values) {
            addCriterion("confirmOrder not in", values, "confirmOrder");
            return (Criteria) this;
        }

        public Criteria andConfirmOrderBetween(Integer value1, Integer value2) {
            addCriterion("confirmOrder between", value1, value2, "confirmOrder");
            return (Criteria) this;
        }

        public Criteria andConfirmOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("confirmOrder not between", value1, value2, "confirmOrder");
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