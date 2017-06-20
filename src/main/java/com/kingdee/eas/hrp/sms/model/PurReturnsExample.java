package com.kingdee.eas.hrp.sms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurReturnsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PurReturnsExample() {
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

        public Criteria andBizDateIsNull() {
            addCriterion("bizDate is null");
            return (Criteria) this;
        }

        public Criteria andBizDateIsNotNull() {
            addCriterion("bizDate is not null");
            return (Criteria) this;
        }

        public Criteria andBizDateEqualTo(Date value) {
            addCriterion("bizDate =", value, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateNotEqualTo(Date value) {
            addCriterion("bizDate <>", value, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateGreaterThan(Date value) {
            addCriterion("bizDate >", value, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateGreaterThanOrEqualTo(Date value) {
            addCriterion("bizDate >=", value, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateLessThan(Date value) {
            addCriterion("bizDate <", value, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateLessThanOrEqualTo(Date value) {
            addCriterion("bizDate <=", value, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateIn(List<Date> values) {
            addCriterion("bizDate in", values, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateNotIn(List<Date> values) {
            addCriterion("bizDate not in", values, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateBetween(Date value1, Date value2) {
            addCriterion("bizDate between", value1, value2, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateNotBetween(Date value1, Date value2) {
            addCriterion("bizDate not between", value1, value2, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBaseStatusIsNull() {
            addCriterion("baseStatus is null");
            return (Criteria) this;
        }

        public Criteria andBaseStatusIsNotNull() {
            addCriterion("baseStatus is not null");
            return (Criteria) this;
        }

        public Criteria andBaseStatusEqualTo(Byte value) {
            addCriterion("baseStatus =", value, "baseStatus");
            return (Criteria) this;
        }

        public Criteria andBaseStatusNotEqualTo(Byte value) {
            addCriterion("baseStatus <>", value, "baseStatus");
            return (Criteria) this;
        }

        public Criteria andBaseStatusGreaterThan(Byte value) {
            addCriterion("baseStatus >", value, "baseStatus");
            return (Criteria) this;
        }

        public Criteria andBaseStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("baseStatus >=", value, "baseStatus");
            return (Criteria) this;
        }

        public Criteria andBaseStatusLessThan(Byte value) {
            addCriterion("baseStatus <", value, "baseStatus");
            return (Criteria) this;
        }

        public Criteria andBaseStatusLessThanOrEqualTo(Byte value) {
            addCriterion("baseStatus <=", value, "baseStatus");
            return (Criteria) this;
        }

        public Criteria andBaseStatusIn(List<Byte> values) {
            addCriterion("baseStatus in", values, "baseStatus");
            return (Criteria) this;
        }

        public Criteria andBaseStatusNotIn(List<Byte> values) {
            addCriterion("baseStatus not in", values, "baseStatus");
            return (Criteria) this;
        }

        public Criteria andBaseStatusBetween(Byte value1, Byte value2) {
            addCriterion("baseStatus between", value1, value2, "baseStatus");
            return (Criteria) this;
        }

        public Criteria andBaseStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("baseStatus not between", value1, value2, "baseStatus");
            return (Criteria) this;
        }

        public Criteria andSourceBillTypeIsNull() {
            addCriterion("sourceBillType is null");
            return (Criteria) this;
        }

        public Criteria andSourceBillTypeIsNotNull() {
            addCriterion("sourceBillType is not null");
            return (Criteria) this;
        }

        public Criteria andSourceBillTypeEqualTo(String value) {
            addCriterion("sourceBillType =", value, "sourceBillType");
            return (Criteria) this;
        }

        public Criteria andSourceBillTypeNotEqualTo(String value) {
            addCriterion("sourceBillType <>", value, "sourceBillType");
            return (Criteria) this;
        }

        public Criteria andSourceBillTypeGreaterThan(String value) {
            addCriterion("sourceBillType >", value, "sourceBillType");
            return (Criteria) this;
        }

        public Criteria andSourceBillTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sourceBillType >=", value, "sourceBillType");
            return (Criteria) this;
        }

        public Criteria andSourceBillTypeLessThan(String value) {
            addCriterion("sourceBillType <", value, "sourceBillType");
            return (Criteria) this;
        }

        public Criteria andSourceBillTypeLessThanOrEqualTo(String value) {
            addCriterion("sourceBillType <=", value, "sourceBillType");
            return (Criteria) this;
        }

        public Criteria andSourceBillTypeLike(String value) {
            addCriterion("sourceBillType like", value, "sourceBillType");
            return (Criteria) this;
        }

        public Criteria andSourceBillTypeNotLike(String value) {
            addCriterion("sourceBillType not like", value, "sourceBillType");
            return (Criteria) this;
        }

        public Criteria andSourceBillTypeIn(List<String> values) {
            addCriterion("sourceBillType in", values, "sourceBillType");
            return (Criteria) this;
        }

        public Criteria andSourceBillTypeNotIn(List<String> values) {
            addCriterion("sourceBillType not in", values, "sourceBillType");
            return (Criteria) this;
        }

        public Criteria andSourceBillTypeBetween(String value1, String value2) {
            addCriterion("sourceBillType between", value1, value2, "sourceBillType");
            return (Criteria) this;
        }

        public Criteria andSourceBillTypeNotBetween(String value1, String value2) {
            addCriterion("sourceBillType not between", value1, value2, "sourceBillType");
            return (Criteria) this;
        }

        public Criteria andSupplierIsNull() {
            addCriterion("supplier is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIsNotNull() {
            addCriterion("supplier is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierEqualTo(String value) {
            addCriterion("supplier =", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotEqualTo(String value) {
            addCriterion("supplier <>", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierGreaterThan(String value) {
            addCriterion("supplier >", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierGreaterThanOrEqualTo(String value) {
            addCriterion("supplier >=", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLessThan(String value) {
            addCriterion("supplier <", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLessThanOrEqualTo(String value) {
            addCriterion("supplier <=", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLike(String value) {
            addCriterion("supplier like", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotLike(String value) {
            addCriterion("supplier not like", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierIn(List<String> values) {
            addCriterion("supplier in", values, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotIn(List<String> values) {
            addCriterion("supplier not in", values, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierBetween(String value1, String value2) {
            addCriterion("supplier between", value1, value2, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotBetween(String value1, String value2) {
            addCriterion("supplier not between", value1, value2, "supplier");
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