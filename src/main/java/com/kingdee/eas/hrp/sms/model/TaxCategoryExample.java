package com.kingdee.eas.hrp.sms.model;

import java.util.ArrayList;
import java.util.List;

public class TaxCategoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TaxCategoryExample() {
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

        public Criteria andTaxCategoryNameIsNull() {
            addCriterion("taxCategoryName is null");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryNameIsNotNull() {
            addCriterion("taxCategoryName is not null");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryNameEqualTo(String value) {
            addCriterion("taxCategoryName =", value, "taxCategoryName");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryNameNotEqualTo(String value) {
            addCriterion("taxCategoryName <>", value, "taxCategoryName");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryNameGreaterThan(String value) {
            addCriterion("taxCategoryName >", value, "taxCategoryName");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("taxCategoryName >=", value, "taxCategoryName");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryNameLessThan(String value) {
            addCriterion("taxCategoryName <", value, "taxCategoryName");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("taxCategoryName <=", value, "taxCategoryName");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryNameLike(String value) {
            addCriterion("taxCategoryName like", value, "taxCategoryName");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryNameNotLike(String value) {
            addCriterion("taxCategoryName not like", value, "taxCategoryName");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryNameIn(List<String> values) {
            addCriterion("taxCategoryName in", values, "taxCategoryName");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryNameNotIn(List<String> values) {
            addCriterion("taxCategoryName not in", values, "taxCategoryName");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryNameBetween(String value1, String value2) {
            addCriterion("taxCategoryName between", value1, value2, "taxCategoryName");
            return (Criteria) this;
        }

        public Criteria andTaxCategoryNameNotBetween(String value1, String value2) {
            addCriterion("taxCategoryName not between", value1, value2, "taxCategoryName");
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