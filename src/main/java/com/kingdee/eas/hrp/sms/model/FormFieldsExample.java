package com.kingdee.eas.hrp.sms.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FormFieldsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FormFieldsExample() {
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

        public Criteria andPageIsNull() {
            addCriterion("page is null");
            return (Criteria) this;
        }

        public Criteria andPageIsNotNull() {
            addCriterion("page is not null");
            return (Criteria) this;
        }

        public Criteria andPageEqualTo(Integer value) {
            addCriterion("page =", value, "page");
            return (Criteria) this;
        }

        public Criteria andPageNotEqualTo(Integer value) {
            addCriterion("page <>", value, "page");
            return (Criteria) this;
        }

        public Criteria andPageGreaterThan(Integer value) {
            addCriterion("page >", value, "page");
            return (Criteria) this;
        }

        public Criteria andPageGreaterThanOrEqualTo(Integer value) {
            addCriterion("page >=", value, "page");
            return (Criteria) this;
        }

        public Criteria andPageLessThan(Integer value) {
            addCriterion("page <", value, "page");
            return (Criteria) this;
        }

        public Criteria andPageLessThanOrEqualTo(Integer value) {
            addCriterion("page <=", value, "page");
            return (Criteria) this;
        }

        public Criteria andPageIn(List<Integer> values) {
            addCriterion("page in", values, "page");
            return (Criteria) this;
        }

        public Criteria andPageNotIn(List<Integer> values) {
            addCriterion("page not in", values, "page");
            return (Criteria) this;
        }

        public Criteria andPageBetween(Integer value1, Integer value2) {
            addCriterion("page between", value1, value2, "page");
            return (Criteria) this;
        }

        public Criteria andPageNotBetween(Integer value1, Integer value2) {
            addCriterion("page not between", value1, value2, "page");
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

        public Criteria andSqlColumnNameIsNull() {
            addCriterion("sqlColumnName is null");
            return (Criteria) this;
        }

        public Criteria andSqlColumnNameIsNotNull() {
            addCriterion("sqlColumnName is not null");
            return (Criteria) this;
        }

        public Criteria andSqlColumnNameEqualTo(String value) {
            addCriterion("sqlColumnName =", value, "sqlColumnName");
            return (Criteria) this;
        }

        public Criteria andSqlColumnNameNotEqualTo(String value) {
            addCriterion("sqlColumnName <>", value, "sqlColumnName");
            return (Criteria) this;
        }

        public Criteria andSqlColumnNameGreaterThan(String value) {
            addCriterion("sqlColumnName >", value, "sqlColumnName");
            return (Criteria) this;
        }

        public Criteria andSqlColumnNameGreaterThanOrEqualTo(String value) {
            addCriterion("sqlColumnName >=", value, "sqlColumnName");
            return (Criteria) this;
        }

        public Criteria andSqlColumnNameLessThan(String value) {
            addCriterion("sqlColumnName <", value, "sqlColumnName");
            return (Criteria) this;
        }

        public Criteria andSqlColumnNameLessThanOrEqualTo(String value) {
            addCriterion("sqlColumnName <=", value, "sqlColumnName");
            return (Criteria) this;
        }

        public Criteria andSqlColumnNameLike(String value) {
            addCriterion("sqlColumnName like", value, "sqlColumnName");
            return (Criteria) this;
        }

        public Criteria andSqlColumnNameNotLike(String value) {
            addCriterion("sqlColumnName not like", value, "sqlColumnName");
            return (Criteria) this;
        }

        public Criteria andSqlColumnNameIn(List<String> values) {
            addCriterion("sqlColumnName in", values, "sqlColumnName");
            return (Criteria) this;
        }

        public Criteria andSqlColumnNameNotIn(List<String> values) {
            addCriterion("sqlColumnName not in", values, "sqlColumnName");
            return (Criteria) this;
        }

        public Criteria andSqlColumnNameBetween(String value1, String value2) {
            addCriterion("sqlColumnName between", value1, value2, "sqlColumnName");
            return (Criteria) this;
        }

        public Criteria andSqlColumnNameNotBetween(String value1, String value2) {
            addCriterion("sqlColumnName not between", value1, value2, "sqlColumnName");
            return (Criteria) this;
        }

        public Criteria andKeyIsNull() {
            addCriterion("[key] is null");
            return (Criteria) this;
        }

        public Criteria andKeyIsNotNull() {
            addCriterion("[key] is not null");
            return (Criteria) this;
        }

        public Criteria andKeyEqualTo(String value) {
            addCriterion("[key] =", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotEqualTo(String value) {
            addCriterion("[key] <>", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyGreaterThan(String value) {
            addCriterion("[key] >", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyGreaterThanOrEqualTo(String value) {
            addCriterion("[key] >=", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLessThan(String value) {
            addCriterion("[key] <", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLessThanOrEqualTo(String value) {
            addCriterion("[key] <=", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLike(String value) {
            addCriterion("[key] like", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotLike(String value) {
            addCriterion("[key] not like", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyIn(List<String> values) {
            addCriterion("[key] in", values, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotIn(List<String> values) {
            addCriterion("[key] not in", values, "key");
            return (Criteria) this;
        }

        public Criteria andKeyBetween(String value1, String value2) {
            addCriterion("[key] between", value1, value2, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotBetween(String value1, String value2) {
            addCriterion("[key] not between", value1, value2, "key");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNull() {
            addCriterion("dataType is null");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNotNull() {
            addCriterion("dataType is not null");
            return (Criteria) this;
        }

        public Criteria andDataTypeEqualTo(Integer value) {
            addCriterion("dataType =", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotEqualTo(Integer value) {
            addCriterion("dataType <>", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThan(Integer value) {
            addCriterion("dataType >", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("dataType >=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThan(Integer value) {
            addCriterion("dataType <", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThanOrEqualTo(Integer value) {
            addCriterion("dataType <=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeIn(List<Integer> values) {
            addCriterion("dataType in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotIn(List<Integer> values) {
            addCriterion("dataType not in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeBetween(Integer value1, Integer value2) {
            addCriterion("dataType between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("dataType not between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andCtrlTypeIsNull() {
            addCriterion("ctrlType is null");
            return (Criteria) this;
        }

        public Criteria andCtrlTypeIsNotNull() {
            addCriterion("ctrlType is not null");
            return (Criteria) this;
        }

        public Criteria andCtrlTypeEqualTo(Integer value) {
            addCriterion("ctrlType =", value, "ctrlType");
            return (Criteria) this;
        }

        public Criteria andCtrlTypeNotEqualTo(Integer value) {
            addCriterion("ctrlType <>", value, "ctrlType");
            return (Criteria) this;
        }

        public Criteria andCtrlTypeGreaterThan(Integer value) {
            addCriterion("ctrlType >", value, "ctrlType");
            return (Criteria) this;
        }

        public Criteria andCtrlTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ctrlType >=", value, "ctrlType");
            return (Criteria) this;
        }

        public Criteria andCtrlTypeLessThan(Integer value) {
            addCriterion("ctrlType <", value, "ctrlType");
            return (Criteria) this;
        }

        public Criteria andCtrlTypeLessThanOrEqualTo(Integer value) {
            addCriterion("ctrlType <=", value, "ctrlType");
            return (Criteria) this;
        }

        public Criteria andCtrlTypeIn(List<Integer> values) {
            addCriterion("ctrlType in", values, "ctrlType");
            return (Criteria) this;
        }

        public Criteria andCtrlTypeNotIn(List<Integer> values) {
            addCriterion("ctrlType not in", values, "ctrlType");
            return (Criteria) this;
        }

        public Criteria andCtrlTypeBetween(Integer value1, Integer value2) {
            addCriterion("ctrlType between", value1, value2, "ctrlType");
            return (Criteria) this;
        }

        public Criteria andCtrlTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ctrlType not between", value1, value2, "ctrlType");
            return (Criteria) this;
        }

        public Criteria andCtlIndexIsNull() {
            addCriterion("ctlIndex is null");
            return (Criteria) this;
        }

        public Criteria andCtlIndexIsNotNull() {
            addCriterion("ctlIndex is not null");
            return (Criteria) this;
        }

        public Criteria andCtlIndexEqualTo(Integer value) {
            addCriterion("ctlIndex =", value, "ctlIndex");
            return (Criteria) this;
        }

        public Criteria andCtlIndexNotEqualTo(Integer value) {
            addCriterion("ctlIndex <>", value, "ctlIndex");
            return (Criteria) this;
        }

        public Criteria andCtlIndexGreaterThan(Integer value) {
            addCriterion("ctlIndex >", value, "ctlIndex");
            return (Criteria) this;
        }

        public Criteria andCtlIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("ctlIndex >=", value, "ctlIndex");
            return (Criteria) this;
        }

        public Criteria andCtlIndexLessThan(Integer value) {
            addCriterion("ctlIndex <", value, "ctlIndex");
            return (Criteria) this;
        }

        public Criteria andCtlIndexLessThanOrEqualTo(Integer value) {
            addCriterion("ctlIndex <=", value, "ctlIndex");
            return (Criteria) this;
        }

        public Criteria andCtlIndexIn(List<Integer> values) {
            addCriterion("ctlIndex in", values, "ctlIndex");
            return (Criteria) this;
        }

        public Criteria andCtlIndexNotIn(List<Integer> values) {
            addCriterion("ctlIndex not in", values, "ctlIndex");
            return (Criteria) this;
        }

        public Criteria andCtlIndexBetween(Integer value1, Integer value2) {
            addCriterion("ctlIndex between", value1, value2, "ctlIndex");
            return (Criteria) this;
        }

        public Criteria andCtlIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("ctlIndex not between", value1, value2, "ctlIndex");
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

        public Criteria andDisplayIsNull() {
            addCriterion("display is null");
            return (Criteria) this;
        }

        public Criteria andDisplayIsNotNull() {
            addCriterion("display is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayEqualTo(Integer value) {
            addCriterion("display =", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayNotEqualTo(Integer value) {
            addCriterion("display <>", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayGreaterThan(Integer value) {
            addCriterion("display >", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayGreaterThanOrEqualTo(Integer value) {
            addCriterion("display >=", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayLessThan(Integer value) {
            addCriterion("display <", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayLessThanOrEqualTo(Integer value) {
            addCriterion("display <=", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayIn(List<Integer> values) {
            addCriterion("display in", values, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayNotIn(List<Integer> values) {
            addCriterion("display not in", values, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayBetween(Integer value1, Integer value2) {
            addCriterion("display between", value1, value2, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayNotBetween(Integer value1, Integer value2) {
            addCriterion("display not between", value1, value2, "display");
            return (Criteria) this;
        }

        public Criteria andShowWidthIsNull() {
            addCriterion("showWidth is null");
            return (Criteria) this;
        }

        public Criteria andShowWidthIsNotNull() {
            addCriterion("showWidth is not null");
            return (Criteria) this;
        }

        public Criteria andShowWidthEqualTo(Integer value) {
            addCriterion("showWidth =", value, "showWidth");
            return (Criteria) this;
        }

        public Criteria andShowWidthNotEqualTo(Integer value) {
            addCriterion("showWidth <>", value, "showWidth");
            return (Criteria) this;
        }

        public Criteria andShowWidthGreaterThan(Integer value) {
            addCriterion("showWidth >", value, "showWidth");
            return (Criteria) this;
        }

        public Criteria andShowWidthGreaterThanOrEqualTo(Integer value) {
            addCriterion("showWidth >=", value, "showWidth");
            return (Criteria) this;
        }

        public Criteria andShowWidthLessThan(Integer value) {
            addCriterion("showWidth <", value, "showWidth");
            return (Criteria) this;
        }

        public Criteria andShowWidthLessThanOrEqualTo(Integer value) {
            addCriterion("showWidth <=", value, "showWidth");
            return (Criteria) this;
        }

        public Criteria andShowWidthIn(List<Integer> values) {
            addCriterion("showWidth in", values, "showWidth");
            return (Criteria) this;
        }

        public Criteria andShowWidthNotIn(List<Integer> values) {
            addCriterion("showWidth not in", values, "showWidth");
            return (Criteria) this;
        }

        public Criteria andShowWidthBetween(Integer value1, Integer value2) {
            addCriterion("showWidth between", value1, value2, "showWidth");
            return (Criteria) this;
        }

        public Criteria andShowWidthNotBetween(Integer value1, Integer value2) {
            addCriterion("showWidth not between", value1, value2, "showWidth");
            return (Criteria) this;
        }

        public Criteria andLookUpTypeIsNull() {
            addCriterion("lookUpType is null");
            return (Criteria) this;
        }

        public Criteria andLookUpTypeIsNotNull() {
            addCriterion("lookUpType is not null");
            return (Criteria) this;
        }

        public Criteria andLookUpTypeEqualTo(Integer value) {
            addCriterion("lookUpType =", value, "lookUpType");
            return (Criteria) this;
        }

        public Criteria andLookUpTypeNotEqualTo(Integer value) {
            addCriterion("lookUpType <>", value, "lookUpType");
            return (Criteria) this;
        }

        public Criteria andLookUpTypeGreaterThan(Integer value) {
            addCriterion("lookUpType >", value, "lookUpType");
            return (Criteria) this;
        }

        public Criteria andLookUpTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("lookUpType >=", value, "lookUpType");
            return (Criteria) this;
        }

        public Criteria andLookUpTypeLessThan(Integer value) {
            addCriterion("lookUpType <", value, "lookUpType");
            return (Criteria) this;
        }

        public Criteria andLookUpTypeLessThanOrEqualTo(Integer value) {
            addCriterion("lookUpType <=", value, "lookUpType");
            return (Criteria) this;
        }

        public Criteria andLookUpTypeIn(List<Integer> values) {
            addCriterion("lookUpType in", values, "lookUpType");
            return (Criteria) this;
        }

        public Criteria andLookUpTypeNotIn(List<Integer> values) {
            addCriterion("lookUpType not in", values, "lookUpType");
            return (Criteria) this;
        }

        public Criteria andLookUpTypeBetween(Integer value1, Integer value2) {
            addCriterion("lookUpType between", value1, value2, "lookUpType");
            return (Criteria) this;
        }

        public Criteria andLookUpTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("lookUpType not between", value1, value2, "lookUpType");
            return (Criteria) this;
        }

        public Criteria andLookUpClassIDIsNull() {
            addCriterion("lookUpClassID is null");
            return (Criteria) this;
        }

        public Criteria andLookUpClassIDIsNotNull() {
            addCriterion("lookUpClassID is not null");
            return (Criteria) this;
        }

        public Criteria andLookUpClassIDEqualTo(Integer value) {
            addCriterion("lookUpClassID =", value, "lookUpClassID");
            return (Criteria) this;
        }

        public Criteria andLookUpClassIDNotEqualTo(Integer value) {
            addCriterion("lookUpClassID <>", value, "lookUpClassID");
            return (Criteria) this;
        }

        public Criteria andLookUpClassIDGreaterThan(Integer value) {
            addCriterion("lookUpClassID >", value, "lookUpClassID");
            return (Criteria) this;
        }

        public Criteria andLookUpClassIDGreaterThanOrEqualTo(Integer value) {
            addCriterion("lookUpClassID >=", value, "lookUpClassID");
            return (Criteria) this;
        }

        public Criteria andLookUpClassIDLessThan(Integer value) {
            addCriterion("lookUpClassID <", value, "lookUpClassID");
            return (Criteria) this;
        }

        public Criteria andLookUpClassIDLessThanOrEqualTo(Integer value) {
            addCriterion("lookUpClassID <=", value, "lookUpClassID");
            return (Criteria) this;
        }

        public Criteria andLookUpClassIDIn(List<Integer> values) {
            addCriterion("lookUpClassID in", values, "lookUpClassID");
            return (Criteria) this;
        }

        public Criteria andLookUpClassIDNotIn(List<Integer> values) {
            addCriterion("lookUpClassID not in", values, "lookUpClassID");
            return (Criteria) this;
        }

        public Criteria andLookUpClassIDBetween(Integer value1, Integer value2) {
            addCriterion("lookUpClassID between", value1, value2, "lookUpClassID");
            return (Criteria) this;
        }

        public Criteria andLookUpClassIDNotBetween(Integer value1, Integer value2) {
            addCriterion("lookUpClassID not between", value1, value2, "lookUpClassID");
            return (Criteria) this;
        }

        public Criteria andSrcTableIsNull() {
            addCriterion("srcTable is null");
            return (Criteria) this;
        }

        public Criteria andSrcTableIsNotNull() {
            addCriterion("srcTable is not null");
            return (Criteria) this;
        }

        public Criteria andSrcTableEqualTo(String value) {
            addCriterion("srcTable =", value, "srcTable");
            return (Criteria) this;
        }

        public Criteria andSrcTableNotEqualTo(String value) {
            addCriterion("srcTable <>", value, "srcTable");
            return (Criteria) this;
        }

        public Criteria andSrcTableGreaterThan(String value) {
            addCriterion("srcTable >", value, "srcTable");
            return (Criteria) this;
        }

        public Criteria andSrcTableGreaterThanOrEqualTo(String value) {
            addCriterion("srcTable >=", value, "srcTable");
            return (Criteria) this;
        }

        public Criteria andSrcTableLessThan(String value) {
            addCriterion("srcTable <", value, "srcTable");
            return (Criteria) this;
        }

        public Criteria andSrcTableLessThanOrEqualTo(String value) {
            addCriterion("srcTable <=", value, "srcTable");
            return (Criteria) this;
        }

        public Criteria andSrcTableLike(String value) {
            addCriterion("srcTable like", value, "srcTable");
            return (Criteria) this;
        }

        public Criteria andSrcTableNotLike(String value) {
            addCriterion("srcTable not like", value, "srcTable");
            return (Criteria) this;
        }

        public Criteria andSrcTableIn(List<String> values) {
            addCriterion("srcTable in", values, "srcTable");
            return (Criteria) this;
        }

        public Criteria andSrcTableNotIn(List<String> values) {
            addCriterion("srcTable not in", values, "srcTable");
            return (Criteria) this;
        }

        public Criteria andSrcTableBetween(String value1, String value2) {
            addCriterion("srcTable between", value1, value2, "srcTable");
            return (Criteria) this;
        }

        public Criteria andSrcTableNotBetween(String value1, String value2) {
            addCriterion("srcTable not between", value1, value2, "srcTable");
            return (Criteria) this;
        }

        public Criteria andSrcTableAlisAsIsNull() {
            addCriterion("srcTableAlisAs is null");
            return (Criteria) this;
        }

        public Criteria andSrcTableAlisAsIsNotNull() {
            addCriterion("srcTableAlisAs is not null");
            return (Criteria) this;
        }

        public Criteria andSrcTableAlisAsEqualTo(String value) {
            addCriterion("srcTableAlisAs =", value, "srcTableAlisAs");
            return (Criteria) this;
        }

        public Criteria andSrcTableAlisAsNotEqualTo(String value) {
            addCriterion("srcTableAlisAs <>", value, "srcTableAlisAs");
            return (Criteria) this;
        }

        public Criteria andSrcTableAlisAsGreaterThan(String value) {
            addCriterion("srcTableAlisAs >", value, "srcTableAlisAs");
            return (Criteria) this;
        }

        public Criteria andSrcTableAlisAsGreaterThanOrEqualTo(String value) {
            addCriterion("srcTableAlisAs >=", value, "srcTableAlisAs");
            return (Criteria) this;
        }

        public Criteria andSrcTableAlisAsLessThan(String value) {
            addCriterion("srcTableAlisAs <", value, "srcTableAlisAs");
            return (Criteria) this;
        }

        public Criteria andSrcTableAlisAsLessThanOrEqualTo(String value) {
            addCriterion("srcTableAlisAs <=", value, "srcTableAlisAs");
            return (Criteria) this;
        }

        public Criteria andSrcTableAlisAsLike(String value) {
            addCriterion("srcTableAlisAs like", value, "srcTableAlisAs");
            return (Criteria) this;
        }

        public Criteria andSrcTableAlisAsNotLike(String value) {
            addCriterion("srcTableAlisAs not like", value, "srcTableAlisAs");
            return (Criteria) this;
        }

        public Criteria andSrcTableAlisAsIn(List<String> values) {
            addCriterion("srcTableAlisAs in", values, "srcTableAlisAs");
            return (Criteria) this;
        }

        public Criteria andSrcTableAlisAsNotIn(List<String> values) {
            addCriterion("srcTableAlisAs not in", values, "srcTableAlisAs");
            return (Criteria) this;
        }

        public Criteria andSrcTableAlisAsBetween(String value1, String value2) {
            addCriterion("srcTableAlisAs between", value1, value2, "srcTableAlisAs");
            return (Criteria) this;
        }

        public Criteria andSrcTableAlisAsNotBetween(String value1, String value2) {
            addCriterion("srcTableAlisAs not between", value1, value2, "srcTableAlisAs");
            return (Criteria) this;
        }

        public Criteria andSrcFieldIsNull() {
            addCriterion("srcField is null");
            return (Criteria) this;
        }

        public Criteria andSrcFieldIsNotNull() {
            addCriterion("srcField is not null");
            return (Criteria) this;
        }

        public Criteria andSrcFieldEqualTo(String value) {
            addCriterion("srcField =", value, "srcField");
            return (Criteria) this;
        }

        public Criteria andSrcFieldNotEqualTo(String value) {
            addCriterion("srcField <>", value, "srcField");
            return (Criteria) this;
        }

        public Criteria andSrcFieldGreaterThan(String value) {
            addCriterion("srcField >", value, "srcField");
            return (Criteria) this;
        }

        public Criteria andSrcFieldGreaterThanOrEqualTo(String value) {
            addCriterion("srcField >=", value, "srcField");
            return (Criteria) this;
        }

        public Criteria andSrcFieldLessThan(String value) {
            addCriterion("srcField <", value, "srcField");
            return (Criteria) this;
        }

        public Criteria andSrcFieldLessThanOrEqualTo(String value) {
            addCriterion("srcField <=", value, "srcField");
            return (Criteria) this;
        }

        public Criteria andSrcFieldLike(String value) {
            addCriterion("srcField like", value, "srcField");
            return (Criteria) this;
        }

        public Criteria andSrcFieldNotLike(String value) {
            addCriterion("srcField not like", value, "srcField");
            return (Criteria) this;
        }

        public Criteria andSrcFieldIn(List<String> values) {
            addCriterion("srcField in", values, "srcField");
            return (Criteria) this;
        }

        public Criteria andSrcFieldNotIn(List<String> values) {
            addCriterion("srcField not in", values, "srcField");
            return (Criteria) this;
        }

        public Criteria andSrcFieldBetween(String value1, String value2) {
            addCriterion("srcField between", value1, value2, "srcField");
            return (Criteria) this;
        }

        public Criteria andSrcFieldNotBetween(String value1, String value2) {
            addCriterion("srcField not between", value1, value2, "srcField");
            return (Criteria) this;
        }

        public Criteria andDisPlayFieldIsNull() {
            addCriterion("disPlayField is null");
            return (Criteria) this;
        }

        public Criteria andDisPlayFieldIsNotNull() {
            addCriterion("disPlayField is not null");
            return (Criteria) this;
        }

        public Criteria andDisPlayFieldEqualTo(String value) {
            addCriterion("disPlayField =", value, "disPlayField");
            return (Criteria) this;
        }

        public Criteria andDisPlayFieldNotEqualTo(String value) {
            addCriterion("disPlayField <>", value, "disPlayField");
            return (Criteria) this;
        }

        public Criteria andDisPlayFieldGreaterThan(String value) {
            addCriterion("disPlayField >", value, "disPlayField");
            return (Criteria) this;
        }

        public Criteria andDisPlayFieldGreaterThanOrEqualTo(String value) {
            addCriterion("disPlayField >=", value, "disPlayField");
            return (Criteria) this;
        }

        public Criteria andDisPlayFieldLessThan(String value) {
            addCriterion("disPlayField <", value, "disPlayField");
            return (Criteria) this;
        }

        public Criteria andDisPlayFieldLessThanOrEqualTo(String value) {
            addCriterion("disPlayField <=", value, "disPlayField");
            return (Criteria) this;
        }

        public Criteria andDisPlayFieldLike(String value) {
            addCriterion("disPlayField like", value, "disPlayField");
            return (Criteria) this;
        }

        public Criteria andDisPlayFieldNotLike(String value) {
            addCriterion("disPlayField not like", value, "disPlayField");
            return (Criteria) this;
        }

        public Criteria andDisPlayFieldIn(List<String> values) {
            addCriterion("disPlayField in", values, "disPlayField");
            return (Criteria) this;
        }

        public Criteria andDisPlayFieldNotIn(List<String> values) {
            addCriterion("disPlayField not in", values, "disPlayField");
            return (Criteria) this;
        }

        public Criteria andDisPlayFieldBetween(String value1, String value2) {
            addCriterion("disPlayField between", value1, value2, "disPlayField");
            return (Criteria) this;
        }

        public Criteria andDisPlayFieldNotBetween(String value1, String value2) {
            addCriterion("disPlayField not between", value1, value2, "disPlayField");
            return (Criteria) this;
        }

        public Criteria andDisPlayNumIsNull() {
            addCriterion("disPlayNum is null");
            return (Criteria) this;
        }

        public Criteria andDisPlayNumIsNotNull() {
            addCriterion("disPlayNum is not null");
            return (Criteria) this;
        }

        public Criteria andDisPlayNumEqualTo(String value) {
            addCriterion("disPlayNum =", value, "disPlayNum");
            return (Criteria) this;
        }

        public Criteria andDisPlayNumNotEqualTo(String value) {
            addCriterion("disPlayNum <>", value, "disPlayNum");
            return (Criteria) this;
        }

        public Criteria andDisPlayNumGreaterThan(String value) {
            addCriterion("disPlayNum >", value, "disPlayNum");
            return (Criteria) this;
        }

        public Criteria andDisPlayNumGreaterThanOrEqualTo(String value) {
            addCriterion("disPlayNum >=", value, "disPlayNum");
            return (Criteria) this;
        }

        public Criteria andDisPlayNumLessThan(String value) {
            addCriterion("disPlayNum <", value, "disPlayNum");
            return (Criteria) this;
        }

        public Criteria andDisPlayNumLessThanOrEqualTo(String value) {
            addCriterion("disPlayNum <=", value, "disPlayNum");
            return (Criteria) this;
        }

        public Criteria andDisPlayNumLike(String value) {
            addCriterion("disPlayNum like", value, "disPlayNum");
            return (Criteria) this;
        }

        public Criteria andDisPlayNumNotLike(String value) {
            addCriterion("disPlayNum not like", value, "disPlayNum");
            return (Criteria) this;
        }

        public Criteria andDisPlayNumIn(List<String> values) {
            addCriterion("disPlayNum in", values, "disPlayNum");
            return (Criteria) this;
        }

        public Criteria andDisPlayNumNotIn(List<String> values) {
            addCriterion("disPlayNum not in", values, "disPlayNum");
            return (Criteria) this;
        }

        public Criteria andDisPlayNumBetween(String value1, String value2) {
            addCriterion("disPlayNum between", value1, value2, "disPlayNum");
            return (Criteria) this;
        }

        public Criteria andDisPlayNumNotBetween(String value1, String value2) {
            addCriterion("disPlayNum not between", value1, value2, "disPlayNum");
            return (Criteria) this;
        }

        public Criteria andJoinTypeIsNull() {
            addCriterion("joinType is null");
            return (Criteria) this;
        }

        public Criteria andJoinTypeIsNotNull() {
            addCriterion("joinType is not null");
            return (Criteria) this;
        }

        public Criteria andJoinTypeEqualTo(String value) {
            addCriterion("joinType =", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeNotEqualTo(String value) {
            addCriterion("joinType <>", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeGreaterThan(String value) {
            addCriterion("joinType >", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeGreaterThanOrEqualTo(String value) {
            addCriterion("joinType >=", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeLessThan(String value) {
            addCriterion("joinType <", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeLessThanOrEqualTo(String value) {
            addCriterion("joinType <=", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeLike(String value) {
            addCriterion("joinType like", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeNotLike(String value) {
            addCriterion("joinType not like", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeIn(List<String> values) {
            addCriterion("joinType in", values, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeNotIn(List<String> values) {
            addCriterion("joinType not in", values, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeBetween(String value1, String value2) {
            addCriterion("joinType between", value1, value2, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeNotBetween(String value1, String value2) {
            addCriterion("joinType not between", value1, value2, "joinType");
            return (Criteria) this;
        }

        public Criteria andFilterIsNull() {
            addCriterion("[filter] is null");
            return (Criteria) this;
        }

        public Criteria andFilterIsNotNull() {
            addCriterion("[filter] is not null");
            return (Criteria) this;
        }

        public Criteria andFilterEqualTo(String value) {
            addCriterion("[filter] =", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterNotEqualTo(String value) {
            addCriterion("[filter] <>", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterGreaterThan(String value) {
            addCriterion("[filter] >", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterGreaterThanOrEqualTo(String value) {
            addCriterion("[filter] >=", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterLessThan(String value) {
            addCriterion("[filter] <", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterLessThanOrEqualTo(String value) {
            addCriterion("[filter] <=", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterLike(String value) {
            addCriterion("[filter] like", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterNotLike(String value) {
            addCriterion("[filter] not like", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterIn(List<String> values) {
            addCriterion("[filter] in", values, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterNotIn(List<String> values) {
            addCriterion("[filter] not in", values, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterBetween(String value1, String value2) {
            addCriterion("[filter] between", value1, value2, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterNotBetween(String value1, String value2) {
            addCriterion("[filter] not between", value1, value2, "filter");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIsNull() {
            addCriterion("defaultValue is null");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIsNotNull() {
            addCriterion("defaultValue is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultValueEqualTo(String value) {
            addCriterion("defaultValue =", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotEqualTo(String value) {
            addCriterion("defaultValue <>", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueGreaterThan(String value) {
            addCriterion("defaultValue >", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueGreaterThanOrEqualTo(String value) {
            addCriterion("defaultValue >=", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLessThan(String value) {
            addCriterion("defaultValue <", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLessThanOrEqualTo(String value) {
            addCriterion("defaultValue <=", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLike(String value) {
            addCriterion("defaultValue like", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotLike(String value) {
            addCriterion("defaultValue not like", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIn(List<String> values) {
            addCriterion("defaultValue in", values, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotIn(List<String> values) {
            addCriterion("defaultValue not in", values, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueBetween(String value1, String value2) {
            addCriterion("defaultValue between", value1, value2, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotBetween(String value1, String value2) {
            addCriterion("defaultValue not between", value1, value2, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueIsNull() {
            addCriterion("[maxValue] is null");
            return (Criteria) this;
        }

        public Criteria andMaxValueIsNotNull() {
            addCriterion("[maxValue] is not null");
            return (Criteria) this;
        }

        public Criteria andMaxValueEqualTo(BigDecimal value) {
            addCriterion("[maxValue] =", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueNotEqualTo(BigDecimal value) {
            addCriterion("[maxValue] <>", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueGreaterThan(BigDecimal value) {
            addCriterion("[maxValue] >", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("[maxValue] >=", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueLessThan(BigDecimal value) {
            addCriterion("[maxValue] <", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("[maxValue] <=", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueIn(List<BigDecimal> values) {
            addCriterion("[maxValue] in", values, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueNotIn(List<BigDecimal> values) {
            addCriterion("[maxValue] not in", values, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("[maxValue] between", value1, value2, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("[maxValue] not between", value1, value2, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMinValueIsNull() {
            addCriterion("[minValue] is null");
            return (Criteria) this;
        }

        public Criteria andMinValueIsNotNull() {
            addCriterion("[minValue] is not null");
            return (Criteria) this;
        }

        public Criteria andMinValueEqualTo(BigDecimal value) {
            addCriterion("[minValue] =", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueNotEqualTo(BigDecimal value) {
            addCriterion("[minValue] <>", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueGreaterThan(BigDecimal value) {
            addCriterion("[minValue] >", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("[minValue] >=", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueLessThan(BigDecimal value) {
            addCriterion("[minValue] <", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("[minValue] <=", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueIn(List<BigDecimal> values) {
            addCriterion("[minValue] in", values, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueNotIn(List<BigDecimal> values) {
            addCriterion("[minValue] not in", values, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("[minValue] between", value1, value2, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("[minValue] not between", value1, value2, "minValue");
            return (Criteria) this;
        }

        public Criteria andMustInputIsNull() {
            addCriterion("mustInput is null");
            return (Criteria) this;
        }

        public Criteria andMustInputIsNotNull() {
            addCriterion("mustInput is not null");
            return (Criteria) this;
        }

        public Criteria andMustInputEqualTo(Integer value) {
            addCriterion("mustInput =", value, "mustInput");
            return (Criteria) this;
        }

        public Criteria andMustInputNotEqualTo(Integer value) {
            addCriterion("mustInput <>", value, "mustInput");
            return (Criteria) this;
        }

        public Criteria andMustInputGreaterThan(Integer value) {
            addCriterion("mustInput >", value, "mustInput");
            return (Criteria) this;
        }

        public Criteria andMustInputGreaterThanOrEqualTo(Integer value) {
            addCriterion("mustInput >=", value, "mustInput");
            return (Criteria) this;
        }

        public Criteria andMustInputLessThan(Integer value) {
            addCriterion("mustInput <", value, "mustInput");
            return (Criteria) this;
        }

        public Criteria andMustInputLessThanOrEqualTo(Integer value) {
            addCriterion("mustInput <=", value, "mustInput");
            return (Criteria) this;
        }

        public Criteria andMustInputIn(List<Integer> values) {
            addCriterion("mustInput in", values, "mustInput");
            return (Criteria) this;
        }

        public Criteria andMustInputNotIn(List<Integer> values) {
            addCriterion("mustInput not in", values, "mustInput");
            return (Criteria) this;
        }

        public Criteria andMustInputBetween(Integer value1, Integer value2) {
            addCriterion("mustInput between", value1, value2, "mustInput");
            return (Criteria) this;
        }

        public Criteria andMustInputNotBetween(Integer value1, Integer value2) {
            addCriterion("mustInput not between", value1, value2, "mustInput");
            return (Criteria) this;
        }

        public Criteria andLengthIsNull() {
            addCriterion("[length] is null");
            return (Criteria) this;
        }

        public Criteria andLengthIsNotNull() {
            addCriterion("[length] is not null");
            return (Criteria) this;
        }

        public Criteria andLengthEqualTo(Integer value) {
            addCriterion("[length] =", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotEqualTo(Integer value) {
            addCriterion("[length] <>", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthGreaterThan(Integer value) {
            addCriterion("[length] >", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("[length] >=", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLessThan(Integer value) {
            addCriterion("[length] <", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLessThanOrEqualTo(Integer value) {
            addCriterion("[length] <=", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthIn(List<Integer> values) {
            addCriterion("[length] in", values, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotIn(List<Integer> values) {
            addCriterion("[length] not in", values, "length");
            return (Criteria) this;
        }

        public Criteria andLengthBetween(Integer value1, Integer value2) {
            addCriterion("[length] between", value1, value2, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("[length] not between", value1, value2, "length");
            return (Criteria) this;
        }

        public Criteria andLockIsNull() {
            addCriterion("[lock] is null");
            return (Criteria) this;
        }

        public Criteria andLockIsNotNull() {
            addCriterion("[lock] is not null");
            return (Criteria) this;
        }

        public Criteria andLockEqualTo(Integer value) {
            addCriterion("[lock] =", value, "lock");
            return (Criteria) this;
        }

        public Criteria andLockNotEqualTo(Integer value) {
            addCriterion("[lock] <>", value, "lock");
            return (Criteria) this;
        }

        public Criteria andLockGreaterThan(Integer value) {
            addCriterion("[lock] >", value, "lock");
            return (Criteria) this;
        }

        public Criteria andLockGreaterThanOrEqualTo(Integer value) {
            addCriterion("[lock] >=", value, "lock");
            return (Criteria) this;
        }

        public Criteria andLockLessThan(Integer value) {
            addCriterion("[lock] <", value, "lock");
            return (Criteria) this;
        }

        public Criteria andLockLessThanOrEqualTo(Integer value) {
            addCriterion("[lock] <=", value, "lock");
            return (Criteria) this;
        }

        public Criteria andLockIn(List<Integer> values) {
            addCriterion("[lock] in", values, "lock");
            return (Criteria) this;
        }

        public Criteria andLockNotIn(List<Integer> values) {
            addCriterion("[lock] not in", values, "lock");
            return (Criteria) this;
        }

        public Criteria andLockBetween(Integer value1, Integer value2) {
            addCriterion("[lock] between", value1, value2, "lock");
            return (Criteria) this;
        }

        public Criteria andLockNotBetween(Integer value1, Integer value2) {
            addCriterion("[lock] not between", value1, value2, "lock");
            return (Criteria) this;
        }

        public Criteria andIsConditionIsNull() {
            addCriterion("isCondition is null");
            return (Criteria) this;
        }

        public Criteria andIsConditionIsNotNull() {
            addCriterion("isCondition is not null");
            return (Criteria) this;
        }

        public Criteria andIsConditionEqualTo(Integer value) {
            addCriterion("isCondition =", value, "isCondition");
            return (Criteria) this;
        }

        public Criteria andIsConditionNotEqualTo(Integer value) {
            addCriterion("isCondition <>", value, "isCondition");
            return (Criteria) this;
        }

        public Criteria andIsConditionGreaterThan(Integer value) {
            addCriterion("isCondition >", value, "isCondition");
            return (Criteria) this;
        }

        public Criteria andIsConditionGreaterThanOrEqualTo(Integer value) {
            addCriterion("isCondition >=", value, "isCondition");
            return (Criteria) this;
        }

        public Criteria andIsConditionLessThan(Integer value) {
            addCriterion("isCondition <", value, "isCondition");
            return (Criteria) this;
        }

        public Criteria andIsConditionLessThanOrEqualTo(Integer value) {
            addCriterion("isCondition <=", value, "isCondition");
            return (Criteria) this;
        }

        public Criteria andIsConditionIn(List<Integer> values) {
            addCriterion("isCondition in", values, "isCondition");
            return (Criteria) this;
        }

        public Criteria andIsConditionNotIn(List<Integer> values) {
            addCriterion("isCondition not in", values, "isCondition");
            return (Criteria) this;
        }

        public Criteria andIsConditionBetween(Integer value1, Integer value2) {
            addCriterion("isCondition between", value1, value2, "isCondition");
            return (Criteria) this;
        }

        public Criteria andIsConditionNotBetween(Integer value1, Integer value2) {
            addCriterion("isCondition not between", value1, value2, "isCondition");
            return (Criteria) this;
        }

        public Criteria andIsCountIsNull() {
            addCriterion("isCount is null");
            return (Criteria) this;
        }

        public Criteria andIsCountIsNotNull() {
            addCriterion("isCount is not null");
            return (Criteria) this;
        }

        public Criteria andIsCountEqualTo(Integer value) {
            addCriterion("isCount =", value, "isCount");
            return (Criteria) this;
        }

        public Criteria andIsCountNotEqualTo(Integer value) {
            addCriterion("isCount <>", value, "isCount");
            return (Criteria) this;
        }

        public Criteria andIsCountGreaterThan(Integer value) {
            addCriterion("isCount >", value, "isCount");
            return (Criteria) this;
        }

        public Criteria andIsCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("isCount >=", value, "isCount");
            return (Criteria) this;
        }

        public Criteria andIsCountLessThan(Integer value) {
            addCriterion("isCount <", value, "isCount");
            return (Criteria) this;
        }

        public Criteria andIsCountLessThanOrEqualTo(Integer value) {
            addCriterion("isCount <=", value, "isCount");
            return (Criteria) this;
        }

        public Criteria andIsCountIn(List<Integer> values) {
            addCriterion("isCount in", values, "isCount");
            return (Criteria) this;
        }

        public Criteria andIsCountNotIn(List<Integer> values) {
            addCriterion("isCount not in", values, "isCount");
            return (Criteria) this;
        }

        public Criteria andIsCountBetween(Integer value1, Integer value2) {
            addCriterion("isCount between", value1, value2, "isCount");
            return (Criteria) this;
        }

        public Criteria andIsCountNotBetween(Integer value1, Integer value2) {
            addCriterion("isCount not between", value1, value2, "isCount");
            return (Criteria) this;
        }

        public Criteria andNeedSaveIsNull() {
            addCriterion("needSave is null");
            return (Criteria) this;
        }

        public Criteria andNeedSaveIsNotNull() {
            addCriterion("needSave is not null");
            return (Criteria) this;
        }

        public Criteria andNeedSaveEqualTo(Byte value) {
            addCriterion("needSave =", value, "needSave");
            return (Criteria) this;
        }

        public Criteria andNeedSaveNotEqualTo(Byte value) {
            addCriterion("needSave <>", value, "needSave");
            return (Criteria) this;
        }

        public Criteria andNeedSaveGreaterThan(Byte value) {
            addCriterion("needSave >", value, "needSave");
            return (Criteria) this;
        }

        public Criteria andNeedSaveGreaterThanOrEqualTo(Byte value) {
            addCriterion("needSave >=", value, "needSave");
            return (Criteria) this;
        }

        public Criteria andNeedSaveLessThan(Byte value) {
            addCriterion("needSave <", value, "needSave");
            return (Criteria) this;
        }

        public Criteria andNeedSaveLessThanOrEqualTo(Byte value) {
            addCriterion("needSave <=", value, "needSave");
            return (Criteria) this;
        }

        public Criteria andNeedSaveIn(List<Byte> values) {
            addCriterion("needSave in", values, "needSave");
            return (Criteria) this;
        }

        public Criteria andNeedSaveNotIn(List<Byte> values) {
            addCriterion("needSave not in", values, "needSave");
            return (Criteria) this;
        }

        public Criteria andNeedSaveBetween(Byte value1, Byte value2) {
            addCriterion("needSave between", value1, value2, "needSave");
            return (Criteria) this;
        }

        public Criteria andNeedSaveNotBetween(Byte value1, Byte value2) {
            addCriterion("needSave not between", value1, value2, "needSave");
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