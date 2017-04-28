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

        public Criteria andMaterial_codeIsNull() {
            addCriterion("material_code is null");
            return (Criteria) this;
        }

        public Criteria andMaterial_codeIsNotNull() {
            addCriterion("material_code is not null");
            return (Criteria) this;
        }

        public Criteria andMaterial_codeEqualTo(String value) {
            addCriterion("material_code =", value, "material_code");
            return (Criteria) this;
        }

        public Criteria andMaterial_codeNotEqualTo(String value) {
            addCriterion("material_code <>", value, "material_code");
            return (Criteria) this;
        }

        public Criteria andMaterial_codeGreaterThan(String value) {
            addCriterion("material_code >", value, "material_code");
            return (Criteria) this;
        }

        public Criteria andMaterial_codeGreaterThanOrEqualTo(String value) {
            addCriterion("material_code >=", value, "material_code");
            return (Criteria) this;
        }

        public Criteria andMaterial_codeLessThan(String value) {
            addCriterion("material_code <", value, "material_code");
            return (Criteria) this;
        }

        public Criteria andMaterial_codeLessThanOrEqualTo(String value) {
            addCriterion("material_code <=", value, "material_code");
            return (Criteria) this;
        }

        public Criteria andMaterial_codeLike(String value) {
            addCriterion("material_code like", value, "material_code");
            return (Criteria) this;
        }

        public Criteria andMaterial_codeNotLike(String value) {
            addCriterion("material_code not like", value, "material_code");
            return (Criteria) this;
        }

        public Criteria andMaterial_codeIn(List<String> values) {
            addCriterion("material_code in", values, "material_code");
            return (Criteria) this;
        }

        public Criteria andMaterial_codeNotIn(List<String> values) {
            addCriterion("material_code not in", values, "material_code");
            return (Criteria) this;
        }

        public Criteria andMaterial_codeBetween(String value1, String value2) {
            addCriterion("material_code between", value1, value2, "material_code");
            return (Criteria) this;
        }

        public Criteria andMaterial_codeNotBetween(String value1, String value2) {
            addCriterion("material_code not between", value1, value2, "material_code");
            return (Criteria) this;
        }

        public Criteria andMaterial_nameIsNull() {
            addCriterion("material_name is null");
            return (Criteria) this;
        }

        public Criteria andMaterial_nameIsNotNull() {
            addCriterion("material_name is not null");
            return (Criteria) this;
        }

        public Criteria andMaterial_nameEqualTo(String value) {
            addCriterion("material_name =", value, "material_name");
            return (Criteria) this;
        }

        public Criteria andMaterial_nameNotEqualTo(String value) {
            addCriterion("material_name <>", value, "material_name");
            return (Criteria) this;
        }

        public Criteria andMaterial_nameGreaterThan(String value) {
            addCriterion("material_name >", value, "material_name");
            return (Criteria) this;
        }

        public Criteria andMaterial_nameGreaterThanOrEqualTo(String value) {
            addCriterion("material_name >=", value, "material_name");
            return (Criteria) this;
        }

        public Criteria andMaterial_nameLessThan(String value) {
            addCriterion("material_name <", value, "material_name");
            return (Criteria) this;
        }

        public Criteria andMaterial_nameLessThanOrEqualTo(String value) {
            addCriterion("material_name <=", value, "material_name");
            return (Criteria) this;
        }

        public Criteria andMaterial_nameLike(String value) {
            addCriterion("material_name like", value, "material_name");
            return (Criteria) this;
        }

        public Criteria andMaterial_nameNotLike(String value) {
            addCriterion("material_name not like", value, "material_name");
            return (Criteria) this;
        }

        public Criteria andMaterial_nameIn(List<String> values) {
            addCriterion("material_name in", values, "material_name");
            return (Criteria) this;
        }

        public Criteria andMaterial_nameNotIn(List<String> values) {
            addCriterion("material_name not in", values, "material_name");
            return (Criteria) this;
        }

        public Criteria andMaterial_nameBetween(String value1, String value2) {
            addCriterion("material_name between", value1, value2, "material_name");
            return (Criteria) this;
        }

        public Criteria andMaterial_nameNotBetween(String value1, String value2) {
            addCriterion("material_name not between", value1, value2, "material_name");
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

        public Criteria andBasic_unit_measurementIsNull() {
            addCriterion("basic_unit_measurement is null");
            return (Criteria) this;
        }

        public Criteria andBasic_unit_measurementIsNotNull() {
            addCriterion("basic_unit_measurement is not null");
            return (Criteria) this;
        }

        public Criteria andBasic_unit_measurementEqualTo(String value) {
            addCriterion("basic_unit_measurement =", value, "basic_unit_measurement");
            return (Criteria) this;
        }

        public Criteria andBasic_unit_measurementNotEqualTo(String value) {
            addCriterion("basic_unit_measurement <>", value, "basic_unit_measurement");
            return (Criteria) this;
        }

        public Criteria andBasic_unit_measurementGreaterThan(String value) {
            addCriterion("basic_unit_measurement >", value, "basic_unit_measurement");
            return (Criteria) this;
        }

        public Criteria andBasic_unit_measurementGreaterThanOrEqualTo(String value) {
            addCriterion("basic_unit_measurement >=", value, "basic_unit_measurement");
            return (Criteria) this;
        }

        public Criteria andBasic_unit_measurementLessThan(String value) {
            addCriterion("basic_unit_measurement <", value, "basic_unit_measurement");
            return (Criteria) this;
        }

        public Criteria andBasic_unit_measurementLessThanOrEqualTo(String value) {
            addCriterion("basic_unit_measurement <=", value, "basic_unit_measurement");
            return (Criteria) this;
        }

        public Criteria andBasic_unit_measurementLike(String value) {
            addCriterion("basic_unit_measurement like", value, "basic_unit_measurement");
            return (Criteria) this;
        }

        public Criteria andBasic_unit_measurementNotLike(String value) {
            addCriterion("basic_unit_measurement not like", value, "basic_unit_measurement");
            return (Criteria) this;
        }

        public Criteria andBasic_unit_measurementIn(List<String> values) {
            addCriterion("basic_unit_measurement in", values, "basic_unit_measurement");
            return (Criteria) this;
        }

        public Criteria andBasic_unit_measurementNotIn(List<String> values) {
            addCriterion("basic_unit_measurement not in", values, "basic_unit_measurement");
            return (Criteria) this;
        }

        public Criteria andBasic_unit_measurementBetween(String value1, String value2) {
            addCriterion("basic_unit_measurement between", value1, value2, "basic_unit_measurement");
            return (Criteria) this;
        }

        public Criteria andBasic_unit_measurementNotBetween(String value1, String value2) {
            addCriterion("basic_unit_measurement not between", value1, value2, "basic_unit_measurement");
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

        public Criteria andDelivery_timeIsNull() {
            addCriterion("delivery_time is null");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeIsNotNull() {
            addCriterion("delivery_time is not null");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeEqualTo(Date value) {
            addCriterion("delivery_time =", value, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeNotEqualTo(Date value) {
            addCriterion("delivery_time <>", value, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeGreaterThan(Date value) {
            addCriterion("delivery_time >", value, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("delivery_time >=", value, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeLessThan(Date value) {
            addCriterion("delivery_time <", value, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeLessThanOrEqualTo(Date value) {
            addCriterion("delivery_time <=", value, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeIn(List<Date> values) {
            addCriterion("delivery_time in", values, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeNotIn(List<Date> values) {
            addCriterion("delivery_time not in", values, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeBetween(Date value1, Date value2) {
            addCriterion("delivery_time between", value1, value2, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeNotBetween(Date value1, Date value2) {
            addCriterion("delivery_time not between", value1, value2, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andOrder_idIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrder_idIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrder_idEqualTo(String value) {
            addCriterion("order_id =", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idNotEqualTo(String value) {
            addCriterion("order_id <>", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idGreaterThan(String value) {
            addCriterion("order_id >", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idGreaterThanOrEqualTo(String value) {
            addCriterion("order_id >=", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idLessThan(String value) {
            addCriterion("order_id <", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idLessThanOrEqualTo(String value) {
            addCriterion("order_id <=", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idLike(String value) {
            addCriterion("order_id like", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idNotLike(String value) {
            addCriterion("order_id not like", value, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idIn(List<String> values) {
            addCriterion("order_id in", values, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idNotIn(List<String> values) {
            addCriterion("order_id not in", values, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idBetween(String value1, String value2) {
            addCriterion("order_id between", value1, value2, "order_id");
            return (Criteria) this;
        }

        public Criteria andOrder_idNotBetween(String value1, String value2) {
            addCriterion("order_id not between", value1, value2, "order_id");
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