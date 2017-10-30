package com.kingdee.eas.hrp.sms.model;

import java.util.ArrayList;
import java.util.List;

public class AccessControlExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AccessControlExample() {
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

        public Criteria andRoleIdIsNull() {
            addCriterion("roleId is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("roleId is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(String value) {
            addCriterion("roleId =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(String value) {
            addCriterion("roleId <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(String value) {
            addCriterion("roleId >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(String value) {
            addCriterion("roleId >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(String value) {
            addCriterion("roleId <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(String value) {
            addCriterion("roleId <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLike(String value) {
            addCriterion("roleId like", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotLike(String value) {
            addCriterion("roleId not like", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<String> values) {
            addCriterion("roleId in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<String> values) {
            addCriterion("roleId not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(String value1, String value2) {
            addCriterion("roleId between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(String value1, String value2) {
            addCriterion("roleId not between", value1, value2, "roleId");
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