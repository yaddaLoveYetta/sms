package com.kingdee.eas.hrp.sms.model;

import java.util.ArrayList;
import java.util.List;

public class DataFlowSubClassExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataFlowSubClassExample() {
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

        public Criteria andSubSysIdIsNull() {
            addCriterion("subSysId is null");
            return (Criteria) this;
        }

        public Criteria andSubSysIdIsNotNull() {
            addCriterion("subSysId is not null");
            return (Criteria) this;
        }

        public Criteria andSubSysIdEqualTo(Integer value) {
            addCriterion("subSysId =", value, "subSysId");
            return (Criteria) this;
        }

        public Criteria andSubSysIdNotEqualTo(Integer value) {
            addCriterion("subSysId <>", value, "subSysId");
            return (Criteria) this;
        }

        public Criteria andSubSysIdGreaterThan(Integer value) {
            addCriterion("subSysId >", value, "subSysId");
            return (Criteria) this;
        }

        public Criteria andSubSysIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("subSysId >=", value, "subSysId");
            return (Criteria) this;
        }

        public Criteria andSubSysIdLessThan(Integer value) {
            addCriterion("subSysId <", value, "subSysId");
            return (Criteria) this;
        }

        public Criteria andSubSysIdLessThanOrEqualTo(Integer value) {
            addCriterion("subSysId <=", value, "subSysId");
            return (Criteria) this;
        }

        public Criteria andSubSysIdIn(List<Integer> values) {
            addCriterion("subSysId in", values, "subSysId");
            return (Criteria) this;
        }

        public Criteria andSubSysIdNotIn(List<Integer> values) {
            addCriterion("subSysId not in", values, "subSysId");
            return (Criteria) this;
        }

        public Criteria andSubSysIdBetween(Integer value1, Integer value2) {
            addCriterion("subSysId between", value1, value2, "subSysId");
            return (Criteria) this;
        }

        public Criteria andSubSysIdNotBetween(Integer value1, Integer value2) {
            addCriterion("subSysId not between", value1, value2, "subSysId");
            return (Criteria) this;
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

        public Criteria andIndexIsNull() {
            addCriterion("[index] is null");
            return (Criteria) this;
        }

        public Criteria andIndexIsNotNull() {
            addCriterion("[index] is not null");
            return (Criteria) this;
        }

        public Criteria andIndexEqualTo(Integer value) {
            addCriterion("[index] =", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexNotEqualTo(Integer value) {
            addCriterion("[index] <>", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexGreaterThan(Integer value) {
            addCriterion("[index] >", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("[index] >=", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexLessThan(Integer value) {
            addCriterion("[index] <", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexLessThanOrEqualTo(Integer value) {
            addCriterion("[index] <=", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexIn(List<Integer> values) {
            addCriterion("[index] in", values, "index");
            return (Criteria) this;
        }

        public Criteria andIndexNotIn(List<Integer> values) {
            addCriterion("[index] not in", values, "index");
            return (Criteria) this;
        }

        public Criteria andIndexBetween(Integer value1, Integer value2) {
            addCriterion("[index] between", value1, value2, "index");
            return (Criteria) this;
        }

        public Criteria andIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("[index] not between", value1, value2, "index");
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

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
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

        public Criteria andOwnerTypeIsNull() {
            addCriterion("ownerType is null");
            return (Criteria) this;
        }

        public Criteria andOwnerTypeIsNotNull() {
            addCriterion("ownerType is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerTypeEqualTo(Integer value) {
            addCriterion("ownerType =", value, "ownerType");
            return (Criteria) this;
        }

        public Criteria andOwnerTypeNotEqualTo(Integer value) {
            addCriterion("ownerType <>", value, "ownerType");
            return (Criteria) this;
        }

        public Criteria andOwnerTypeGreaterThan(Integer value) {
            addCriterion("ownerType >", value, "ownerType");
            return (Criteria) this;
        }

        public Criteria andOwnerTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ownerType >=", value, "ownerType");
            return (Criteria) this;
        }

        public Criteria andOwnerTypeLessThan(Integer value) {
            addCriterion("ownerType <", value, "ownerType");
            return (Criteria) this;
        }

        public Criteria andOwnerTypeLessThanOrEqualTo(Integer value) {
            addCriterion("ownerType <=", value, "ownerType");
            return (Criteria) this;
        }

        public Criteria andOwnerTypeIn(List<Integer> values) {
            addCriterion("ownerType in", values, "ownerType");
            return (Criteria) this;
        }

        public Criteria andOwnerTypeNotIn(List<Integer> values) {
            addCriterion("ownerType not in", values, "ownerType");
            return (Criteria) this;
        }

        public Criteria andOwnerTypeBetween(Integer value1, Integer value2) {
            addCriterion("ownerType between", value1, value2, "ownerType");
            return (Criteria) this;
        }

        public Criteria andOwnerTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ownerType not between", value1, value2, "ownerType");
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