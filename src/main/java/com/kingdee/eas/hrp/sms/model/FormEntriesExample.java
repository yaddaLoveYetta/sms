package com.kingdee.eas.hrp.sms.model;

import java.util.ArrayList;
import java.util.List;

public class FormEntriesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FormEntriesExample() {
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

        public Criteria andClassIdIsNull() {
            addCriterion("classId is null");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNotNull() {
            addCriterion("classId is not null");
            return (Criteria) this;
        }

        public Criteria andClassIdEqualTo(Integer value) {
            addCriterion("classId =", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotEqualTo(Integer value) {
            addCriterion("classId <>", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThan(Integer value) {
            addCriterion("classId >", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("classId >=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThan(Integer value) {
            addCriterion("classId <", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThanOrEqualTo(Integer value) {
            addCriterion("classId <=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdIn(List<Integer> values) {
            addCriterion("classId in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotIn(List<Integer> values) {
            addCriterion("classId not in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdBetween(Integer value1, Integer value2) {
            addCriterion("classId between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotBetween(Integer value1, Integer value2) {
            addCriterion("classId not between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andEntryIndexIsNull() {
            addCriterion("entryIndex is null");
            return (Criteria) this;
        }

        public Criteria andEntryIndexIsNotNull() {
            addCriterion("entryIndex is not null");
            return (Criteria) this;
        }

        public Criteria andEntryIndexEqualTo(Integer value) {
            addCriterion("entryIndex =", value, "entryIndex");
            return (Criteria) this;
        }

        public Criteria andEntryIndexNotEqualTo(Integer value) {
            addCriterion("entryIndex <>", value, "entryIndex");
            return (Criteria) this;
        }

        public Criteria andEntryIndexGreaterThan(Integer value) {
            addCriterion("entryIndex >", value, "entryIndex");
            return (Criteria) this;
        }

        public Criteria andEntryIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("entryIndex >=", value, "entryIndex");
            return (Criteria) this;
        }

        public Criteria andEntryIndexLessThan(Integer value) {
            addCriterion("entryIndex <", value, "entryIndex");
            return (Criteria) this;
        }

        public Criteria andEntryIndexLessThanOrEqualTo(Integer value) {
            addCriterion("entryIndex <=", value, "entryIndex");
            return (Criteria) this;
        }

        public Criteria andEntryIndexIn(List<Integer> values) {
            addCriterion("entryIndex in", values, "entryIndex");
            return (Criteria) this;
        }

        public Criteria andEntryIndexNotIn(List<Integer> values) {
            addCriterion("entryIndex not in", values, "entryIndex");
            return (Criteria) this;
        }

        public Criteria andEntryIndexBetween(Integer value1, Integer value2) {
            addCriterion("entryIndex between", value1, value2, "entryIndex");
            return (Criteria) this;
        }

        public Criteria andEntryIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("entryIndex not between", value1, value2, "entryIndex");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNull() {
            addCriterion("tableName is null");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNotNull() {
            addCriterion("tableName is not null");
            return (Criteria) this;
        }

        public Criteria andTableNameEqualTo(String value) {
            addCriterion("tableName =", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotEqualTo(String value) {
            addCriterion("tableName <>", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThan(String value) {
            addCriterion("tableName >", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("tableName >=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThan(String value) {
            addCriterion("tableName <", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThanOrEqualTo(String value) {
            addCriterion("tableName <=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLike(String value) {
            addCriterion("tableName like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotLike(String value) {
            addCriterion("tableName not like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameIn(List<String> values) {
            addCriterion("tableName in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotIn(List<String> values) {
            addCriterion("tableName not in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameBetween(String value1, String value2) {
            addCriterion("tableName between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotBetween(String value1, String value2) {
            addCriterion("tableName not between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andForeignKeyIsNull() {
            addCriterion("foreignKey is null");
            return (Criteria) this;
        }

        public Criteria andForeignKeyIsNotNull() {
            addCriterion("foreignKey is not null");
            return (Criteria) this;
        }

        public Criteria andForeignKeyEqualTo(String value) {
            addCriterion("foreignKey =", value, "foreignKey");
            return (Criteria) this;
        }

        public Criteria andForeignKeyNotEqualTo(String value) {
            addCriterion("foreignKey <>", value, "foreignKey");
            return (Criteria) this;
        }

        public Criteria andForeignKeyGreaterThan(String value) {
            addCriterion("foreignKey >", value, "foreignKey");
            return (Criteria) this;
        }

        public Criteria andForeignKeyGreaterThanOrEqualTo(String value) {
            addCriterion("foreignKey >=", value, "foreignKey");
            return (Criteria) this;
        }

        public Criteria andForeignKeyLessThan(String value) {
            addCriterion("foreignKey <", value, "foreignKey");
            return (Criteria) this;
        }

        public Criteria andForeignKeyLessThanOrEqualTo(String value) {
            addCriterion("foreignKey <=", value, "foreignKey");
            return (Criteria) this;
        }

        public Criteria andForeignKeyLike(String value) {
            addCriterion("foreignKey like", value, "foreignKey");
            return (Criteria) this;
        }

        public Criteria andForeignKeyNotLike(String value) {
            addCriterion("foreignKey not like", value, "foreignKey");
            return (Criteria) this;
        }

        public Criteria andForeignKeyIn(List<String> values) {
            addCriterion("foreignKey in", values, "foreignKey");
            return (Criteria) this;
        }

        public Criteria andForeignKeyNotIn(List<String> values) {
            addCriterion("foreignKey not in", values, "foreignKey");
            return (Criteria) this;
        }

        public Criteria andForeignKeyBetween(String value1, String value2) {
            addCriterion("foreignKey between", value1, value2, "foreignKey");
            return (Criteria) this;
        }

        public Criteria andForeignKeyNotBetween(String value1, String value2) {
            addCriterion("foreignKey not between", value1, value2, "foreignKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyIsNull() {
            addCriterion("primaryKey is null");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyIsNotNull() {
            addCriterion("primaryKey is not null");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyEqualTo(String value) {
            addCriterion("primaryKey =", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyNotEqualTo(String value) {
            addCriterion("primaryKey <>", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyGreaterThan(String value) {
            addCriterion("primaryKey >", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyGreaterThanOrEqualTo(String value) {
            addCriterion("primaryKey >=", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyLessThan(String value) {
            addCriterion("primaryKey <", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyLessThanOrEqualTo(String value) {
            addCriterion("primaryKey <=", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyLike(String value) {
            addCriterion("primaryKey like", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyNotLike(String value) {
            addCriterion("primaryKey not like", value, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyIn(List<String> values) {
            addCriterion("primaryKey in", values, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyNotIn(List<String> values) {
            addCriterion("primaryKey not in", values, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyBetween(String value1, String value2) {
            addCriterion("primaryKey between", value1, value2, "primaryKey");
            return (Criteria) this;
        }

        public Criteria andPrimaryKeyNotBetween(String value1, String value2) {
            addCriterion("primaryKey not between", value1, value2, "primaryKey");
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