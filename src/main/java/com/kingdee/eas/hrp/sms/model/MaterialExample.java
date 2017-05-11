package com.kingdee.eas.hrp.sms.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MaterialExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MaterialExample() {
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

        public Criteria andMaterialIdIsNull() {
            addCriterion("materialId is null");
            return (Criteria) this;
        }

        public Criteria andMaterialIdIsNotNull() {
            addCriterion("materialId is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialIdEqualTo(String value) {
            addCriterion("materialId =", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdNotEqualTo(String value) {
            addCriterion("materialId <>", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdGreaterThan(String value) {
            addCriterion("materialId >", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdGreaterThanOrEqualTo(String value) {
            addCriterion("materialId >=", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdLessThan(String value) {
            addCriterion("materialId <", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdLessThanOrEqualTo(String value) {
            addCriterion("materialId <=", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdLike(String value) {
            addCriterion("materialId like", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdNotLike(String value) {
            addCriterion("materialId not like", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdIn(List<String> values) {
            addCriterion("materialId in", values, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdNotIn(List<String> values) {
            addCriterion("materialId not in", values, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdBetween(String value1, String value2) {
            addCriterion("materialId between", value1, value2, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdNotBetween(String value1, String value2) {
            addCriterion("materialId not between", value1, value2, "materialId");
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

        public Criteria andUnitPriceIsNull() {
            addCriterion("unitPrice is null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNotNull() {
            addCriterion("unitPrice is not null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceEqualTo(BigDecimal value) {
            addCriterion("unitPrice =", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotEqualTo(BigDecimal value) {
            addCriterion("unitPrice <>", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThan(BigDecimal value) {
            addCriterion("unitPrice >", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unitPrice >=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThan(BigDecimal value) {
            addCriterion("unitPrice <", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unitPrice <=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIn(List<BigDecimal> values) {
            addCriterion("unitPrice in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotIn(List<BigDecimal> values) {
            addCriterion("unitPrice not in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unitPrice between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unitPrice not between", value1, value2, "unitPrice");
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

        public Criteria andDeliveryTimeIsNull() {
            addCriterion("deliveryTime is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIsNotNull() {
            addCriterion("deliveryTime is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeEqualTo(Date value) {
            addCriterion("deliveryTime =", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotEqualTo(Date value) {
            addCriterion("deliveryTime <>", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThan(Date value) {
            addCriterion("deliveryTime >", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("deliveryTime >=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThan(Date value) {
            addCriterion("deliveryTime <", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThanOrEqualTo(Date value) {
            addCriterion("deliveryTime <=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIn(List<Date> values) {
            addCriterion("deliveryTime in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotIn(List<Date> values) {
            addCriterion("deliveryTime not in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeBetween(Date value1, Date value2) {
            addCriterion("deliveryTime between", value1, value2, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotBetween(Date value1, Date value2) {
            addCriterion("deliveryTime not between", value1, value2, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNull() {
            addCriterion("discount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("discount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(Double value) {
            addCriterion("discount =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(Double value) {
            addCriterion("discount <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(Double value) {
            addCriterion("discount >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(Double value) {
            addCriterion("discount >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(Double value) {
            addCriterion("discount <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(Double value) {
            addCriterion("discount <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<Double> values) {
            addCriterion("discount in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<Double> values) {
            addCriterion("discount not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(Double value1, Double value2) {
            addCriterion("discount between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(Double value1, Double value2) {
            addCriterion("discount not between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andRateIsNull() {
            addCriterion("rate is null");
            return (Criteria) this;
        }

        public Criteria andRateIsNotNull() {
            addCriterion("rate is not null");
            return (Criteria) this;
        }

        public Criteria andRateEqualTo(Double value) {
            addCriterion("rate =", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotEqualTo(Double value) {
            addCriterion("rate <>", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThan(Double value) {
            addCriterion("rate >", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThanOrEqualTo(Double value) {
            addCriterion("rate >=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThan(Double value) {
            addCriterion("rate <", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThanOrEqualTo(Double value) {
            addCriterion("rate <=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateIn(List<Double> values) {
            addCriterion("rate in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotIn(List<Double> values) {
            addCriterion("rate not in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateBetween(Double value1, Double value2) {
            addCriterion("rate between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotBetween(Double value1, Double value2) {
            addCriterion("rate not between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andTaxUnitPriceIsNull() {
            addCriterion("taxUnitPrice is null");
            return (Criteria) this;
        }

        public Criteria andTaxUnitPriceIsNotNull() {
            addCriterion("taxUnitPrice is not null");
            return (Criteria) this;
        }

        public Criteria andTaxUnitPriceEqualTo(BigDecimal value) {
            addCriterion("taxUnitPrice =", value, "taxUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTaxUnitPriceNotEqualTo(BigDecimal value) {
            addCriterion("taxUnitPrice <>", value, "taxUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTaxUnitPriceGreaterThan(BigDecimal value) {
            addCriterion("taxUnitPrice >", value, "taxUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTaxUnitPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("taxUnitPrice >=", value, "taxUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTaxUnitPriceLessThan(BigDecimal value) {
            addCriterion("taxUnitPrice <", value, "taxUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTaxUnitPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("taxUnitPrice <=", value, "taxUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTaxUnitPriceIn(List<BigDecimal> values) {
            addCriterion("taxUnitPrice in", values, "taxUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTaxUnitPriceNotIn(List<BigDecimal> values) {
            addCriterion("taxUnitPrice not in", values, "taxUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTaxUnitPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("taxUnitPrice between", value1, value2, "taxUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTaxUnitPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("taxUnitPrice not between", value1, value2, "taxUnitPrice");
            return (Criteria) this;
        }

        public Criteria andActualTaxUnitPriceIsNull() {
            addCriterion("actualTaxUnitPrice is null");
            return (Criteria) this;
        }

        public Criteria andActualTaxUnitPriceIsNotNull() {
            addCriterion("actualTaxUnitPrice is not null");
            return (Criteria) this;
        }

        public Criteria andActualTaxUnitPriceEqualTo(BigDecimal value) {
            addCriterion("actualTaxUnitPrice =", value, "actualTaxUnitPrice");
            return (Criteria) this;
        }

        public Criteria andActualTaxUnitPriceNotEqualTo(BigDecimal value) {
            addCriterion("actualTaxUnitPrice <>", value, "actualTaxUnitPrice");
            return (Criteria) this;
        }

        public Criteria andActualTaxUnitPriceGreaterThan(BigDecimal value) {
            addCriterion("actualTaxUnitPrice >", value, "actualTaxUnitPrice");
            return (Criteria) this;
        }

        public Criteria andActualTaxUnitPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("actualTaxUnitPrice >=", value, "actualTaxUnitPrice");
            return (Criteria) this;
        }

        public Criteria andActualTaxUnitPriceLessThan(BigDecimal value) {
            addCriterion("actualTaxUnitPrice <", value, "actualTaxUnitPrice");
            return (Criteria) this;
        }

        public Criteria andActualTaxUnitPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("actualTaxUnitPrice <=", value, "actualTaxUnitPrice");
            return (Criteria) this;
        }

        public Criteria andActualTaxUnitPriceIn(List<BigDecimal> values) {
            addCriterion("actualTaxUnitPrice in", values, "actualTaxUnitPrice");
            return (Criteria) this;
        }

        public Criteria andActualTaxUnitPriceNotIn(List<BigDecimal> values) {
            addCriterion("actualTaxUnitPrice not in", values, "actualTaxUnitPrice");
            return (Criteria) this;
        }

        public Criteria andActualTaxUnitPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actualTaxUnitPrice between", value1, value2, "actualTaxUnitPrice");
            return (Criteria) this;
        }

        public Criteria andActualTaxUnitPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actualTaxUnitPrice not between", value1, value2, "actualTaxUnitPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIsNull() {
            addCriterion("discountPrice is null");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIsNotNull() {
            addCriterion("discountPrice is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceEqualTo(BigDecimal value) {
            addCriterion("discountPrice =", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotEqualTo(BigDecimal value) {
            addCriterion("discountPrice <>", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceGreaterThan(BigDecimal value) {
            addCriterion("discountPrice >", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discountPrice >=", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceLessThan(BigDecimal value) {
            addCriterion("discountPrice <", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discountPrice <=", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIn(List<BigDecimal> values) {
            addCriterion("discountPrice in", values, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotIn(List<BigDecimal> values) {
            addCriterion("discountPrice not in", values, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discountPrice between", value1, value2, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discountPrice not between", value1, value2, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andTaxPriceIsNull() {
            addCriterion("taxPrice is null");
            return (Criteria) this;
        }

        public Criteria andTaxPriceIsNotNull() {
            addCriterion("taxPrice is not null");
            return (Criteria) this;
        }

        public Criteria andTaxPriceEqualTo(BigDecimal value) {
            addCriterion("taxPrice =", value, "taxPrice");
            return (Criteria) this;
        }

        public Criteria andTaxPriceNotEqualTo(BigDecimal value) {
            addCriterion("taxPrice <>", value, "taxPrice");
            return (Criteria) this;
        }

        public Criteria andTaxPriceGreaterThan(BigDecimal value) {
            addCriterion("taxPrice >", value, "taxPrice");
            return (Criteria) this;
        }

        public Criteria andTaxPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("taxPrice >=", value, "taxPrice");
            return (Criteria) this;
        }

        public Criteria andTaxPriceLessThan(BigDecimal value) {
            addCriterion("taxPrice <", value, "taxPrice");
            return (Criteria) this;
        }

        public Criteria andTaxPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("taxPrice <=", value, "taxPrice");
            return (Criteria) this;
        }

        public Criteria andTaxPriceIn(List<BigDecimal> values) {
            addCriterion("taxPrice in", values, "taxPrice");
            return (Criteria) this;
        }

        public Criteria andTaxPriceNotIn(List<BigDecimal> values) {
            addCriterion("taxPrice not in", values, "taxPrice");
            return (Criteria) this;
        }

        public Criteria andTaxPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("taxPrice between", value1, value2, "taxPrice");
            return (Criteria) this;
        }

        public Criteria andTaxPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("taxPrice not between", value1, value2, "taxPrice");
            return (Criteria) this;
        }

        public Criteria andFunctionalCurrencyAmountIsNull() {
            addCriterion("functionalCurrencyAmount is null");
            return (Criteria) this;
        }

        public Criteria andFunctionalCurrencyAmountIsNotNull() {
            addCriterion("functionalCurrencyAmount is not null");
            return (Criteria) this;
        }

        public Criteria andFunctionalCurrencyAmountEqualTo(BigDecimal value) {
            addCriterion("functionalCurrencyAmount =", value, "functionalCurrencyAmount");
            return (Criteria) this;
        }

        public Criteria andFunctionalCurrencyAmountNotEqualTo(BigDecimal value) {
            addCriterion("functionalCurrencyAmount <>", value, "functionalCurrencyAmount");
            return (Criteria) this;
        }

        public Criteria andFunctionalCurrencyAmountGreaterThan(BigDecimal value) {
            addCriterion("functionalCurrencyAmount >", value, "functionalCurrencyAmount");
            return (Criteria) this;
        }

        public Criteria andFunctionalCurrencyAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("functionalCurrencyAmount >=", value, "functionalCurrencyAmount");
            return (Criteria) this;
        }

        public Criteria andFunctionalCurrencyAmountLessThan(BigDecimal value) {
            addCriterion("functionalCurrencyAmount <", value, "functionalCurrencyAmount");
            return (Criteria) this;
        }

        public Criteria andFunctionalCurrencyAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("functionalCurrencyAmount <=", value, "functionalCurrencyAmount");
            return (Criteria) this;
        }

        public Criteria andFunctionalCurrencyAmountIn(List<BigDecimal> values) {
            addCriterion("functionalCurrencyAmount in", values, "functionalCurrencyAmount");
            return (Criteria) this;
        }

        public Criteria andFunctionalCurrencyAmountNotIn(List<BigDecimal> values) {
            addCriterion("functionalCurrencyAmount not in", values, "functionalCurrencyAmount");
            return (Criteria) this;
        }

        public Criteria andFunctionalCurrencyAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("functionalCurrencyAmount between", value1, value2, "functionalCurrencyAmount");
            return (Criteria) this;
        }

        public Criteria andFunctionalCurrencyAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("functionalCurrencyAmount not between", value1, value2, "functionalCurrencyAmount");
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