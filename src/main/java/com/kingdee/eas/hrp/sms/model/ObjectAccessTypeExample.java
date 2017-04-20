package com.kingdee.eas.hrp.sms.model;

import java.util.ArrayList;
import java.util.List;

public class ObjectAccessTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ObjectAccessTypeExample() {
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

        public Criteria andObjectTypeIsNull() {
            addCriterion("objectType is null");
            return (Criteria) this;
        }

        public Criteria andObjectTypeIsNotNull() {
            addCriterion("objectType is not null");
            return (Criteria) this;
        }

        public Criteria andObjectTypeEqualTo(Integer value) {
            addCriterion("objectType =", value, "objectType");
            return (Criteria) this;
        }

        public Criteria andObjectTypeNotEqualTo(Integer value) {
            addCriterion("objectType <>", value, "objectType");
            return (Criteria) this;
        }

        public Criteria andObjectTypeGreaterThan(Integer value) {
            addCriterion("objectType >", value, "objectType");
            return (Criteria) this;
        }

        public Criteria andObjectTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("objectType >=", value, "objectType");
            return (Criteria) this;
        }

        public Criteria andObjectTypeLessThan(Integer value) {
            addCriterion("objectType <", value, "objectType");
            return (Criteria) this;
        }

        public Criteria andObjectTypeLessThanOrEqualTo(Integer value) {
            addCriterion("objectType <=", value, "objectType");
            return (Criteria) this;
        }

        public Criteria andObjectTypeIn(List<Integer> values) {
            addCriterion("objectType in", values, "objectType");
            return (Criteria) this;
        }

        public Criteria andObjectTypeNotIn(List<Integer> values) {
            addCriterion("objectType not in", values, "objectType");
            return (Criteria) this;
        }

        public Criteria andObjectTypeBetween(Integer value1, Integer value2) {
            addCriterion("objectType between", value1, value2, "objectType");
            return (Criteria) this;
        }

        public Criteria andObjectTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("objectType not between", value1, value2, "objectType");
            return (Criteria) this;
        }

        public Criteria andObjectIdIsNull() {
            addCriterion("objectId is null");
            return (Criteria) this;
        }

        public Criteria andObjectIdIsNotNull() {
            addCriterion("objectId is not null");
            return (Criteria) this;
        }

        public Criteria andObjectIdEqualTo(Integer value) {
            addCriterion("objectId =", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdNotEqualTo(Integer value) {
            addCriterion("objectId <>", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdGreaterThan(Integer value) {
            addCriterion("objectId >", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("objectId >=", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdLessThan(Integer value) {
            addCriterion("objectId <", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("objectId <=", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdIn(List<Integer> values) {
            addCriterion("objectId in", values, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdNotIn(List<Integer> values) {
            addCriterion("objectId not in", values, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdBetween(Integer value1, Integer value2) {
            addCriterion("objectId between", value1, value2, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("objectId not between", value1, value2, "objectId");
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

        public Criteria andAccessMaskIsNull() {
            addCriterion("accessMask is null");
            return (Criteria) this;
        }

        public Criteria andAccessMaskIsNotNull() {
            addCriterion("accessMask is not null");
            return (Criteria) this;
        }

        public Criteria andAccessMaskEqualTo(Integer value) {
            addCriterion("accessMask =", value, "accessMask");
            return (Criteria) this;
        }

        public Criteria andAccessMaskNotEqualTo(Integer value) {
            addCriterion("accessMask <>", value, "accessMask");
            return (Criteria) this;
        }

        public Criteria andAccessMaskGreaterThan(Integer value) {
            addCriterion("accessMask >", value, "accessMask");
            return (Criteria) this;
        }

        public Criteria andAccessMaskGreaterThanOrEqualTo(Integer value) {
            addCriterion("accessMask >=", value, "accessMask");
            return (Criteria) this;
        }

        public Criteria andAccessMaskLessThan(Integer value) {
            addCriterion("accessMask <", value, "accessMask");
            return (Criteria) this;
        }

        public Criteria andAccessMaskLessThanOrEqualTo(Integer value) {
            addCriterion("accessMask <=", value, "accessMask");
            return (Criteria) this;
        }

        public Criteria andAccessMaskIn(List<Integer> values) {
            addCriterion("accessMask in", values, "accessMask");
            return (Criteria) this;
        }

        public Criteria andAccessMaskNotIn(List<Integer> values) {
            addCriterion("accessMask not in", values, "accessMask");
            return (Criteria) this;
        }

        public Criteria andAccessMaskBetween(Integer value1, Integer value2) {
            addCriterion("accessMask between", value1, value2, "accessMask");
            return (Criteria) this;
        }

        public Criteria andAccessMaskNotBetween(Integer value1, Integer value2) {
            addCriterion("accessMask not between", value1, value2, "accessMask");
            return (Criteria) this;
        }

        public Criteria andAccessUseIsNull() {
            addCriterion("accessUse is null");
            return (Criteria) this;
        }

        public Criteria andAccessUseIsNotNull() {
            addCriterion("accessUse is not null");
            return (Criteria) this;
        }

        public Criteria andAccessUseEqualTo(Integer value) {
            addCriterion("accessUse =", value, "accessUse");
            return (Criteria) this;
        }

        public Criteria andAccessUseNotEqualTo(Integer value) {
            addCriterion("accessUse <>", value, "accessUse");
            return (Criteria) this;
        }

        public Criteria andAccessUseGreaterThan(Integer value) {
            addCriterion("accessUse >", value, "accessUse");
            return (Criteria) this;
        }

        public Criteria andAccessUseGreaterThanOrEqualTo(Integer value) {
            addCriterion("accessUse >=", value, "accessUse");
            return (Criteria) this;
        }

        public Criteria andAccessUseLessThan(Integer value) {
            addCriterion("accessUse <", value, "accessUse");
            return (Criteria) this;
        }

        public Criteria andAccessUseLessThanOrEqualTo(Integer value) {
            addCriterion("accessUse <=", value, "accessUse");
            return (Criteria) this;
        }

        public Criteria andAccessUseIn(List<Integer> values) {
            addCriterion("accessUse in", values, "accessUse");
            return (Criteria) this;
        }

        public Criteria andAccessUseNotIn(List<Integer> values) {
            addCriterion("accessUse not in", values, "accessUse");
            return (Criteria) this;
        }

        public Criteria andAccessUseBetween(Integer value1, Integer value2) {
            addCriterion("accessUse between", value1, value2, "accessUse");
            return (Criteria) this;
        }

        public Criteria andAccessUseNotBetween(Integer value1, Integer value2) {
            addCriterion("accessUse not between", value1, value2, "accessUse");
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

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
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