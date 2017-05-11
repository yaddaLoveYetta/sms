package com.kingdee.eas.hrp.sms.model;

import java.math.BigDecimal;
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

        public Criteria andDateIsNull() {
            addCriterion("[date] is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("[date] is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(Date value) {
            addCriterion("[date] =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(Date value) {
            addCriterion("[date] <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(Date value) {
            addCriterion("[date] >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(Date value) {
            addCriterion("[date] >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(Date value) {
            addCriterion("[date] <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(Date value) {
            addCriterion("[date] <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<Date> values) {
            addCriterion("[date] in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<Date> values) {
            addCriterion("[date] not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(Date value1, Date value2) {
            addCriterion("[date] between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(Date value1, Date value2) {
            addCriterion("[date] not between", value1, value2, "date");
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

        public Criteria andPurchasingModeEqualTo(String value) {
            addCriterion("purchasingMode =", value, "purchasingMode");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeNotEqualTo(String value) {
            addCriterion("purchasingMode <>", value, "purchasingMode");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeGreaterThan(String value) {
            addCriterion("purchasingMode >", value, "purchasingMode");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeGreaterThanOrEqualTo(String value) {
            addCriterion("purchasingMode >=", value, "purchasingMode");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeLessThan(String value) {
            addCriterion("purchasingMode <", value, "purchasingMode");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeLessThanOrEqualTo(String value) {
            addCriterion("purchasingMode <=", value, "purchasingMode");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeLike(String value) {
            addCriterion("purchasingMode like", value, "purchasingMode");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeNotLike(String value) {
            addCriterion("purchasingMode not like", value, "purchasingMode");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeIn(List<String> values) {
            addCriterion("purchasingMode in", values, "purchasingMode");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeNotIn(List<String> values) {
            addCriterion("purchasingMode not in", values, "purchasingMode");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeBetween(String value1, String value2) {
            addCriterion("purchasingMode between", value1, value2, "purchasingMode");
            return (Criteria) this;
        }

        public Criteria andPurchasingModeNotBetween(String value1, String value2) {
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

        public Criteria andOrderNoIsNull() {
            addCriterion("orderNo is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("orderNo is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("orderNo =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("orderNo <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("orderNo >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("orderNo >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("orderNo <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("orderNo <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("orderNo like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("orderNo not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("orderNo in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("orderNo not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("orderNo between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("orderNo not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andPurchasingTypeIsNull() {
            addCriterion("purchasingType is null");
            return (Criteria) this;
        }

        public Criteria andPurchasingTypeIsNotNull() {
            addCriterion("purchasingType is not null");
            return (Criteria) this;
        }

        public Criteria andPurchasingTypeEqualTo(String value) {
            addCriterion("purchasingType =", value, "purchasingType");
            return (Criteria) this;
        }

        public Criteria andPurchasingTypeNotEqualTo(String value) {
            addCriterion("purchasingType <>", value, "purchasingType");
            return (Criteria) this;
        }

        public Criteria andPurchasingTypeGreaterThan(String value) {
            addCriterion("purchasingType >", value, "purchasingType");
            return (Criteria) this;
        }

        public Criteria andPurchasingTypeGreaterThanOrEqualTo(String value) {
            addCriterion("purchasingType >=", value, "purchasingType");
            return (Criteria) this;
        }

        public Criteria andPurchasingTypeLessThan(String value) {
            addCriterion("purchasingType <", value, "purchasingType");
            return (Criteria) this;
        }

        public Criteria andPurchasingTypeLessThanOrEqualTo(String value) {
            addCriterion("purchasingType <=", value, "purchasingType");
            return (Criteria) this;
        }

        public Criteria andPurchasingTypeLike(String value) {
            addCriterion("purchasingType like", value, "purchasingType");
            return (Criteria) this;
        }

        public Criteria andPurchasingTypeNotLike(String value) {
            addCriterion("purchasingType not like", value, "purchasingType");
            return (Criteria) this;
        }

        public Criteria andPurchasingTypeIn(List<String> values) {
            addCriterion("purchasingType in", values, "purchasingType");
            return (Criteria) this;
        }

        public Criteria andPurchasingTypeNotIn(List<String> values) {
            addCriterion("purchasingType not in", values, "purchasingType");
            return (Criteria) this;
        }

        public Criteria andPurchasingTypeBetween(String value1, String value2) {
            addCriterion("purchasingType between", value1, value2, "purchasingType");
            return (Criteria) this;
        }

        public Criteria andPurchasingTypeNotBetween(String value1, String value2) {
            addCriterion("purchasingType not between", value1, value2, "purchasingType");
            return (Criteria) this;
        }

        public Criteria andUrgentIsNull() {
            addCriterion("urgent is null");
            return (Criteria) this;
        }

        public Criteria andUrgentIsNotNull() {
            addCriterion("urgent is not null");
            return (Criteria) this;
        }

        public Criteria andUrgentEqualTo(Integer value) {
            addCriterion("urgent =", value, "urgent");
            return (Criteria) this;
        }

        public Criteria andUrgentNotEqualTo(Integer value) {
            addCriterion("urgent <>", value, "urgent");
            return (Criteria) this;
        }

        public Criteria andUrgentGreaterThan(Integer value) {
            addCriterion("urgent >", value, "urgent");
            return (Criteria) this;
        }

        public Criteria andUrgentGreaterThanOrEqualTo(Integer value) {
            addCriterion("urgent >=", value, "urgent");
            return (Criteria) this;
        }

        public Criteria andUrgentLessThan(Integer value) {
            addCriterion("urgent <", value, "urgent");
            return (Criteria) this;
        }

        public Criteria andUrgentLessThanOrEqualTo(Integer value) {
            addCriterion("urgent <=", value, "urgent");
            return (Criteria) this;
        }

        public Criteria andUrgentIn(List<Integer> values) {
            addCriterion("urgent in", values, "urgent");
            return (Criteria) this;
        }

        public Criteria andUrgentNotIn(List<Integer> values) {
            addCriterion("urgent not in", values, "urgent");
            return (Criteria) this;
        }

        public Criteria andUrgentBetween(Integer value1, Integer value2) {
            addCriterion("urgent between", value1, value2, "urgent");
            return (Criteria) this;
        }

        public Criteria andUrgentNotBetween(Integer value1, Integer value2) {
            addCriterion("urgent not between", value1, value2, "urgent");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNull() {
            addCriterion("currency is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("currency is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(String value) {
            addCriterion("currency =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(String value) {
            addCriterion("currency <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(String value) {
            addCriterion("currency >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("currency >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(String value) {
            addCriterion("currency <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(String value) {
            addCriterion("currency <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLike(String value) {
            addCriterion("currency like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotLike(String value) {
            addCriterion("currency not like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<String> values) {
            addCriterion("currency in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<String> values) {
            addCriterion("currency not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(String value1, String value2) {
            addCriterion("currency between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(String value1, String value2) {
            addCriterion("currency not between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andPaymentConditionsIsNull() {
            addCriterion("paymentConditions is null");
            return (Criteria) this;
        }

        public Criteria andPaymentConditionsIsNotNull() {
            addCriterion("paymentConditions is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentConditionsEqualTo(String value) {
            addCriterion("paymentConditions =", value, "paymentConditions");
            return (Criteria) this;
        }

        public Criteria andPaymentConditionsNotEqualTo(String value) {
            addCriterion("paymentConditions <>", value, "paymentConditions");
            return (Criteria) this;
        }

        public Criteria andPaymentConditionsGreaterThan(String value) {
            addCriterion("paymentConditions >", value, "paymentConditions");
            return (Criteria) this;
        }

        public Criteria andPaymentConditionsGreaterThanOrEqualTo(String value) {
            addCriterion("paymentConditions >=", value, "paymentConditions");
            return (Criteria) this;
        }

        public Criteria andPaymentConditionsLessThan(String value) {
            addCriterion("paymentConditions <", value, "paymentConditions");
            return (Criteria) this;
        }

        public Criteria andPaymentConditionsLessThanOrEqualTo(String value) {
            addCriterion("paymentConditions <=", value, "paymentConditions");
            return (Criteria) this;
        }

        public Criteria andPaymentConditionsLike(String value) {
            addCriterion("paymentConditions like", value, "paymentConditions");
            return (Criteria) this;
        }

        public Criteria andPaymentConditionsNotLike(String value) {
            addCriterion("paymentConditions not like", value, "paymentConditions");
            return (Criteria) this;
        }

        public Criteria andPaymentConditionsIn(List<String> values) {
            addCriterion("paymentConditions in", values, "paymentConditions");
            return (Criteria) this;
        }

        public Criteria andPaymentConditionsNotIn(List<String> values) {
            addCriterion("paymentConditions not in", values, "paymentConditions");
            return (Criteria) this;
        }

        public Criteria andPaymentConditionsBetween(String value1, String value2) {
            addCriterion("paymentConditions between", value1, value2, "paymentConditions");
            return (Criteria) this;
        }

        public Criteria andPaymentConditionsNotBetween(String value1, String value2) {
            addCriterion("paymentConditions not between", value1, value2, "paymentConditions");
            return (Criteria) this;
        }

        public Criteria andPaymentWayIsNull() {
            addCriterion("paymentWay is null");
            return (Criteria) this;
        }

        public Criteria andPaymentWayIsNotNull() {
            addCriterion("paymentWay is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentWayEqualTo(String value) {
            addCriterion("paymentWay =", value, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayNotEqualTo(String value) {
            addCriterion("paymentWay <>", value, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayGreaterThan(String value) {
            addCriterion("paymentWay >", value, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayGreaterThanOrEqualTo(String value) {
            addCriterion("paymentWay >=", value, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayLessThan(String value) {
            addCriterion("paymentWay <", value, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayLessThanOrEqualTo(String value) {
            addCriterion("paymentWay <=", value, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayLike(String value) {
            addCriterion("paymentWay like", value, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayNotLike(String value) {
            addCriterion("paymentWay not like", value, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayIn(List<String> values) {
            addCriterion("paymentWay in", values, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayNotIn(List<String> values) {
            addCriterion("paymentWay not in", values, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayBetween(String value1, String value2) {
            addCriterion("paymentWay between", value1, value2, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayNotBetween(String value1, String value2) {
            addCriterion("paymentWay not between", value1, value2, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andSettlementWayIsNull() {
            addCriterion("settlementWay is null");
            return (Criteria) this;
        }

        public Criteria andSettlementWayIsNotNull() {
            addCriterion("settlementWay is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementWayEqualTo(String value) {
            addCriterion("settlementWay =", value, "settlementWay");
            return (Criteria) this;
        }

        public Criteria andSettlementWayNotEqualTo(String value) {
            addCriterion("settlementWay <>", value, "settlementWay");
            return (Criteria) this;
        }

        public Criteria andSettlementWayGreaterThan(String value) {
            addCriterion("settlementWay >", value, "settlementWay");
            return (Criteria) this;
        }

        public Criteria andSettlementWayGreaterThanOrEqualTo(String value) {
            addCriterion("settlementWay >=", value, "settlementWay");
            return (Criteria) this;
        }

        public Criteria andSettlementWayLessThan(String value) {
            addCriterion("settlementWay <", value, "settlementWay");
            return (Criteria) this;
        }

        public Criteria andSettlementWayLessThanOrEqualTo(String value) {
            addCriterion("settlementWay <=", value, "settlementWay");
            return (Criteria) this;
        }

        public Criteria andSettlementWayLike(String value) {
            addCriterion("settlementWay like", value, "settlementWay");
            return (Criteria) this;
        }

        public Criteria andSettlementWayNotLike(String value) {
            addCriterion("settlementWay not like", value, "settlementWay");
            return (Criteria) this;
        }

        public Criteria andSettlementWayIn(List<String> values) {
            addCriterion("settlementWay in", values, "settlementWay");
            return (Criteria) this;
        }

        public Criteria andSettlementWayNotIn(List<String> values) {
            addCriterion("settlementWay not in", values, "settlementWay");
            return (Criteria) this;
        }

        public Criteria andSettlementWayBetween(String value1, String value2) {
            addCriterion("settlementWay between", value1, value2, "settlementWay");
            return (Criteria) this;
        }

        public Criteria andSettlementWayNotBetween(String value1, String value2) {
            addCriterion("settlementWay not between", value1, value2, "settlementWay");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andTaxAmountIsNull() {
            addCriterion("taxAmount is null");
            return (Criteria) this;
        }

        public Criteria andTaxAmountIsNotNull() {
            addCriterion("taxAmount is not null");
            return (Criteria) this;
        }

        public Criteria andTaxAmountEqualTo(BigDecimal value) {
            addCriterion("taxAmount =", value, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andTaxAmountNotEqualTo(BigDecimal value) {
            addCriterion("taxAmount <>", value, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andTaxAmountGreaterThan(BigDecimal value) {
            addCriterion("taxAmount >", value, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andTaxAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("taxAmount >=", value, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andTaxAmountLessThan(BigDecimal value) {
            addCriterion("taxAmount <", value, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andTaxAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("taxAmount <=", value, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andTaxAmountIn(List<BigDecimal> values) {
            addCriterion("taxAmount in", values, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andTaxAmountNotIn(List<BigDecimal> values) {
            addCriterion("taxAmount not in", values, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andTaxAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("taxAmount between", value1, value2, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andTaxAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("taxAmount not between", value1, value2, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andLeviedCombinedIsNull() {
            addCriterion("leviedCombined is null");
            return (Criteria) this;
        }

        public Criteria andLeviedCombinedIsNotNull() {
            addCriterion("leviedCombined is not null");
            return (Criteria) this;
        }

        public Criteria andLeviedCombinedEqualTo(BigDecimal value) {
            addCriterion("leviedCombined =", value, "leviedCombined");
            return (Criteria) this;
        }

        public Criteria andLeviedCombinedNotEqualTo(BigDecimal value) {
            addCriterion("leviedCombined <>", value, "leviedCombined");
            return (Criteria) this;
        }

        public Criteria andLeviedCombinedGreaterThan(BigDecimal value) {
            addCriterion("leviedCombined >", value, "leviedCombined");
            return (Criteria) this;
        }

        public Criteria andLeviedCombinedGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("leviedCombined >=", value, "leviedCombined");
            return (Criteria) this;
        }

        public Criteria andLeviedCombinedLessThan(BigDecimal value) {
            addCriterion("leviedCombined <", value, "leviedCombined");
            return (Criteria) this;
        }

        public Criteria andLeviedCombinedLessThanOrEqualTo(BigDecimal value) {
            addCriterion("leviedCombined <=", value, "leviedCombined");
            return (Criteria) this;
        }

        public Criteria andLeviedCombinedIn(List<BigDecimal> values) {
            addCriterion("leviedCombined in", values, "leviedCombined");
            return (Criteria) this;
        }

        public Criteria andLeviedCombinedNotIn(List<BigDecimal> values) {
            addCriterion("leviedCombined not in", values, "leviedCombined");
            return (Criteria) this;
        }

        public Criteria andLeviedCombinedBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("leviedCombined between", value1, value2, "leviedCombined");
            return (Criteria) this;
        }

        public Criteria andLeviedCombinedNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("leviedCombined not between", value1, value2, "leviedCombined");
            return (Criteria) this;
        }

        public Criteria andMakeDateIsNull() {
            addCriterion("makeDate is null");
            return (Criteria) this;
        }

        public Criteria andMakeDateIsNotNull() {
            addCriterion("makeDate is not null");
            return (Criteria) this;
        }

        public Criteria andMakeDateEqualTo(Date value) {
            addCriterion("makeDate =", value, "makeDate");
            return (Criteria) this;
        }

        public Criteria andMakeDateNotEqualTo(Date value) {
            addCriterion("makeDate <>", value, "makeDate");
            return (Criteria) this;
        }

        public Criteria andMakeDateGreaterThan(Date value) {
            addCriterion("makeDate >", value, "makeDate");
            return (Criteria) this;
        }

        public Criteria andMakeDateGreaterThanOrEqualTo(Date value) {
            addCriterion("makeDate >=", value, "makeDate");
            return (Criteria) this;
        }

        public Criteria andMakeDateLessThan(Date value) {
            addCriterion("makeDate <", value, "makeDate");
            return (Criteria) this;
        }

        public Criteria andMakeDateLessThanOrEqualTo(Date value) {
            addCriterion("makeDate <=", value, "makeDate");
            return (Criteria) this;
        }

        public Criteria andMakeDateIn(List<Date> values) {
            addCriterion("makeDate in", values, "makeDate");
            return (Criteria) this;
        }

        public Criteria andMakeDateNotIn(List<Date> values) {
            addCriterion("makeDate not in", values, "makeDate");
            return (Criteria) this;
        }

        public Criteria andMakeDateBetween(Date value1, Date value2) {
            addCriterion("makeDate between", value1, value2, "makeDate");
            return (Criteria) this;
        }

        public Criteria andMakeDateNotBetween(Date value1, Date value2) {
            addCriterion("makeDate not between", value1, value2, "makeDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateIsNull() {
            addCriterion("auditDate is null");
            return (Criteria) this;
        }

        public Criteria andAuditDateIsNotNull() {
            addCriterion("auditDate is not null");
            return (Criteria) this;
        }

        public Criteria andAuditDateEqualTo(Date value) {
            addCriterion("auditDate =", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateNotEqualTo(Date value) {
            addCriterion("auditDate <>", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateGreaterThan(Date value) {
            addCriterion("auditDate >", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("auditDate >=", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateLessThan(Date value) {
            addCriterion("auditDate <", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateLessThanOrEqualTo(Date value) {
            addCriterion("auditDate <=", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateIn(List<Date> values) {
            addCriterion("auditDate in", values, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateNotIn(List<Date> values) {
            addCriterion("auditDate not in", values, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateBetween(Date value1, Date value2) {
            addCriterion("auditDate between", value1, value2, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateNotBetween(Date value1, Date value2) {
            addCriterion("auditDate not between", value1, value2, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditUserIsNull() {
            addCriterion("auditUser is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserIsNotNull() {
            addCriterion("auditUser is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserEqualTo(String value) {
            addCriterion("auditUser =", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserNotEqualTo(String value) {
            addCriterion("auditUser <>", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserGreaterThan(String value) {
            addCriterion("auditUser >", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserGreaterThanOrEqualTo(String value) {
            addCriterion("auditUser >=", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserLessThan(String value) {
            addCriterion("auditUser <", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserLessThanOrEqualTo(String value) {
            addCriterion("auditUser <=", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserLike(String value) {
            addCriterion("auditUser like", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserNotLike(String value) {
            addCriterion("auditUser not like", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserIn(List<String> values) {
            addCriterion("auditUser in", values, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserNotIn(List<String> values) {
            addCriterion("auditUser not in", values, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserBetween(String value1, String value2) {
            addCriterion("auditUser between", value1, value2, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserNotBetween(String value1, String value2) {
            addCriterion("auditUser not between", value1, value2, "auditUser");
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