package com.kingdee.eas.hrp.sms.model;

import java.util.ArrayList;
import java.util.List;

public class DataFlowTopClassExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataFlowTopClassExample() {
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

        public Criteria andTopClassIdIsNull() {
            addCriterion("topClassId is null");
            return (Criteria) this;
        }

        public Criteria andTopClassIdIsNotNull() {
            addCriterion("topClassId is not null");
            return (Criteria) this;
        }

        public Criteria andTopClassIdEqualTo(Integer value) {
            addCriterion("topClassId =", value, "topClassId");
            return (Criteria) this;
        }

        public Criteria andTopClassIdNotEqualTo(Integer value) {
            addCriterion("topClassId <>", value, "topClassId");
            return (Criteria) this;
        }

        public Criteria andTopClassIdGreaterThan(Integer value) {
            addCriterion("topClassId >", value, "topClassId");
            return (Criteria) this;
        }

        public Criteria andTopClassIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("topClassId >=", value, "topClassId");
            return (Criteria) this;
        }

        public Criteria andTopClassIdLessThan(Integer value) {
            addCriterion("topClassId <", value, "topClassId");
            return (Criteria) this;
        }

        public Criteria andTopClassIdLessThanOrEqualTo(Integer value) {
            addCriterion("topClassId <=", value, "topClassId");
            return (Criteria) this;
        }

        public Criteria andTopClassIdIn(List<Integer> values) {
            addCriterion("topClassId in", values, "topClassId");
            return (Criteria) this;
        }

        public Criteria andTopClassIdNotIn(List<Integer> values) {
            addCriterion("topClassId not in", values, "topClassId");
            return (Criteria) this;
        }

        public Criteria andTopClassIdBetween(Integer value1, Integer value2) {
            addCriterion("topClassId between", value1, value2, "topClassId");
            return (Criteria) this;
        }

        public Criteria andTopClassIdNotBetween(Integer value1, Integer value2) {
            addCriterion("topClassId not between", value1, value2, "topClassId");
            return (Criteria) this;
        }

        public Criteria andTopClassNameIsNull() {
            addCriterion("topClassName is null");
            return (Criteria) this;
        }

        public Criteria andTopClassNameIsNotNull() {
            addCriterion("topClassName is not null");
            return (Criteria) this;
        }

        public Criteria andTopClassNameEqualTo(String value) {
            addCriterion("topClassName =", value, "topClassName");
            return (Criteria) this;
        }

        public Criteria andTopClassNameNotEqualTo(String value) {
            addCriterion("topClassName <>", value, "topClassName");
            return (Criteria) this;
        }

        public Criteria andTopClassNameGreaterThan(String value) {
            addCriterion("topClassName >", value, "topClassName");
            return (Criteria) this;
        }

        public Criteria andTopClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("topClassName >=", value, "topClassName");
            return (Criteria) this;
        }

        public Criteria andTopClassNameLessThan(String value) {
            addCriterion("topClassName <", value, "topClassName");
            return (Criteria) this;
        }

        public Criteria andTopClassNameLessThanOrEqualTo(String value) {
            addCriterion("topClassName <=", value, "topClassName");
            return (Criteria) this;
        }

        public Criteria andTopClassNameLike(String value) {
            addCriterion("topClassName like", value, "topClassName");
            return (Criteria) this;
        }

        public Criteria andTopClassNameNotLike(String value) {
            addCriterion("topClassName not like", value, "topClassName");
            return (Criteria) this;
        }

        public Criteria andTopClassNameIn(List<String> values) {
            addCriterion("topClassName in", values, "topClassName");
            return (Criteria) this;
        }

        public Criteria andTopClassNameNotIn(List<String> values) {
            addCriterion("topClassName not in", values, "topClassName");
            return (Criteria) this;
        }

        public Criteria andTopClassNameBetween(String value1, String value2) {
            addCriterion("topClassName between", value1, value2, "topClassName");
            return (Criteria) this;
        }

        public Criteria andTopClassNameNotBetween(String value1, String value2) {
            addCriterion("topClassName not between", value1, value2, "topClassName");
            return (Criteria) this;
        }

        public Criteria andIndexIsNull() {
            addCriterion("index is null");
            return (Criteria) this;
        }

        public Criteria andIndexIsNotNull() {
            addCriterion("index is not null");
            return (Criteria) this;
        }

        public Criteria andIndexEqualTo(Integer value) {
            addCriterion("index =", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexNotEqualTo(Integer value) {
            addCriterion("index <>", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexGreaterThan(Integer value) {
            addCriterion("index >", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("index >=", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexLessThan(Integer value) {
            addCriterion("index <", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexLessThanOrEqualTo(Integer value) {
            addCriterion("index <=", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexIn(List<Integer> values) {
            addCriterion("index in", values, "index");
            return (Criteria) this;
        }

        public Criteria andIndexNotIn(List<Integer> values) {
            addCriterion("index not in", values, "index");
            return (Criteria) this;
        }

        public Criteria andIndexBetween(Integer value1, Integer value2) {
            addCriterion("index between", value1, value2, "index");
            return (Criteria) this;
        }

        public Criteria andIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("index not between", value1, value2, "index");
            return (Criteria) this;
        }

        public Criteria andVisibleIsNull() {
            addCriterion("visible is null");
            return (Criteria) this;
        }

        public Criteria andVisibleIsNotNull() {
            addCriterion("visible is not null");
            return (Criteria) this;
        }

        public Criteria andVisibleEqualTo(Boolean value) {
            addCriterion("visible =", value, "visible");
            return (Criteria) this;
        }

        public Criteria andVisibleNotEqualTo(Boolean value) {
            addCriterion("visible <>", value, "visible");
            return (Criteria) this;
        }

        public Criteria andVisibleGreaterThan(Boolean value) {
            addCriterion("visible >", value, "visible");
            return (Criteria) this;
        }

        public Criteria andVisibleGreaterThanOrEqualTo(Boolean value) {
            addCriterion("visible >=", value, "visible");
            return (Criteria) this;
        }

        public Criteria andVisibleLessThan(Boolean value) {
            addCriterion("visible <", value, "visible");
            return (Criteria) this;
        }

        public Criteria andVisibleLessThanOrEqualTo(Boolean value) {
            addCriterion("visible <=", value, "visible");
            return (Criteria) this;
        }

        public Criteria andVisibleIn(List<Boolean> values) {
            addCriterion("visible in", values, "visible");
            return (Criteria) this;
        }

        public Criteria andVisibleNotIn(List<Boolean> values) {
            addCriterion("visible not in", values, "visible");
            return (Criteria) this;
        }

        public Criteria andVisibleBetween(Boolean value1, Boolean value2) {
            addCriterion("visible between", value1, value2, "visible");
            return (Criteria) this;
        }

        public Criteria andVisibleNotBetween(Boolean value1, Boolean value2) {
            addCriterion("visible not between", value1, value2, "visible");
            return (Criteria) this;
        }

        public Criteria andIconIsNull() {
            addCriterion("icon is null");
            return (Criteria) this;
        }

        public Criteria andIconIsNotNull() {
            addCriterion("icon is not null");
            return (Criteria) this;
        }

        public Criteria andIconEqualTo(String value) {
            addCriterion("icon =", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotEqualTo(String value) {
            addCriterion("icon <>", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThan(String value) {
            addCriterion("icon >", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThanOrEqualTo(String value) {
            addCriterion("icon >=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThan(String value) {
            addCriterion("icon <", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThanOrEqualTo(String value) {
            addCriterion("icon <=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLike(String value) {
            addCriterion("icon like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotLike(String value) {
            addCriterion("icon not like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconIn(List<String> values) {
            addCriterion("icon in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotIn(List<String> values) {
            addCriterion("icon not in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconBetween(String value1, String value2) {
            addCriterion("icon between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotBetween(String value1, String value2) {
            addCriterion("icon not between", value1, value2, "icon");
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