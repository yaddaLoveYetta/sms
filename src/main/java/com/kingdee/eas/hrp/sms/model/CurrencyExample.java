package com.kingdee.eas.hrp.sms.model;

import java.util.ArrayList;
import java.util.List;

public class CurrencyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CurrencyExample() {
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

        public Criteria andCurrencyNameIsNull() {
            addCriterion("currencyName is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyNameIsNotNull() {
            addCriterion("currencyName is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyNameEqualTo(String value) {
            addCriterion("currencyName =", value, "currencyName");
            return (Criteria) this;
        }

        public Criteria andCurrencyNameNotEqualTo(String value) {
            addCriterion("currencyName <>", value, "currencyName");
            return (Criteria) this;
        }

        public Criteria andCurrencyNameGreaterThan(String value) {
            addCriterion("currencyName >", value, "currencyName");
            return (Criteria) this;
        }

        public Criteria andCurrencyNameGreaterThanOrEqualTo(String value) {
            addCriterion("currencyName >=", value, "currencyName");
            return (Criteria) this;
        }

        public Criteria andCurrencyNameLessThan(String value) {
            addCriterion("currencyName <", value, "currencyName");
            return (Criteria) this;
        }

        public Criteria andCurrencyNameLessThanOrEqualTo(String value) {
            addCriterion("currencyName <=", value, "currencyName");
            return (Criteria) this;
        }

        public Criteria andCurrencyNameLike(String value) {
            addCriterion("currencyName like", value, "currencyName");
            return (Criteria) this;
        }

        public Criteria andCurrencyNameNotLike(String value) {
            addCriterion("currencyName not like", value, "currencyName");
            return (Criteria) this;
        }

        public Criteria andCurrencyNameIn(List<String> values) {
            addCriterion("currencyName in", values, "currencyName");
            return (Criteria) this;
        }

        public Criteria andCurrencyNameNotIn(List<String> values) {
            addCriterion("currencyName not in", values, "currencyName");
            return (Criteria) this;
        }

        public Criteria andCurrencyNameBetween(String value1, String value2) {
            addCriterion("currencyName between", value1, value2, "currencyName");
            return (Criteria) this;
        }

        public Criteria andCurrencyNameNotBetween(String value1, String value2) {
            addCriterion("currencyName not between", value1, value2, "currencyName");
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