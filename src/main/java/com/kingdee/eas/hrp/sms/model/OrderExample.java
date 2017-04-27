package com.kingdee.eas.hrp.sms.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andSupplier_idIsNull() {
            addCriterion("supplier_id is null");
            return (Criteria) this;
        }

        public Criteria andSupplier_idIsNotNull() {
            addCriterion("supplier_id is not null");
            return (Criteria) this;
        }

        public Criteria andSupplier_idEqualTo(String value) {
            addCriterion("supplier_id =", value, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSupplier_idNotEqualTo(String value) {
            addCriterion("supplier_id <>", value, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSupplier_idGreaterThan(String value) {
            addCriterion("supplier_id >", value, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSupplier_idGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_id >=", value, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSupplier_idLessThan(String value) {
            addCriterion("supplier_id <", value, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSupplier_idLessThanOrEqualTo(String value) {
            addCriterion("supplier_id <=", value, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSupplier_idLike(String value) {
            addCriterion("supplier_id like", value, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSupplier_idNotLike(String value) {
            addCriterion("supplier_id not like", value, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSupplier_idIn(List<String> values) {
            addCriterion("supplier_id in", values, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSupplier_idNotIn(List<String> values) {
            addCriterion("supplier_id not in", values, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSupplier_idBetween(String value1, String value2) {
            addCriterion("supplier_id between", value1, value2, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSupplier_idNotBetween(String value1, String value2) {
            addCriterion("supplier_id not between", value1, value2, "supplier_id");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameIsNull() {
            addCriterion("supplier_name is null");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameIsNotNull() {
            addCriterion("supplier_name is not null");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameEqualTo(String value) {
            addCriterion("supplier_name =", value, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameNotEqualTo(String value) {
            addCriterion("supplier_name <>", value, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameGreaterThan(String value) {
            addCriterion("supplier_name >", value, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_name >=", value, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameLessThan(String value) {
            addCriterion("supplier_name <", value, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameLessThanOrEqualTo(String value) {
            addCriterion("supplier_name <=", value, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameLike(String value) {
            addCriterion("supplier_name like", value, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameNotLike(String value) {
            addCriterion("supplier_name not like", value, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameIn(List<String> values) {
            addCriterion("supplier_name in", values, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameNotIn(List<String> values) {
            addCriterion("supplier_name not in", values, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameBetween(String value1, String value2) {
            addCriterion("supplier_name between", value1, value2, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andSupplier_nameNotBetween(String value1, String value2) {
            addCriterion("supplier_name not between", value1, value2, "supplier_name");
            return (Criteria) this;
        }

        public Criteria andLine_numbersIsNull() {
            addCriterion("line_numbers is null");
            return (Criteria) this;
        }

        public Criteria andLine_numbersIsNotNull() {
            addCriterion("line_numbers is not null");
            return (Criteria) this;
        }

        public Criteria andLine_numbersEqualTo(String value) {
            addCriterion("line_numbers =", value, "line_numbers");
            return (Criteria) this;
        }

        public Criteria andLine_numbersNotEqualTo(String value) {
            addCriterion("line_numbers <>", value, "line_numbers");
            return (Criteria) this;
        }

        public Criteria andLine_numbersGreaterThan(String value) {
            addCriterion("line_numbers >", value, "line_numbers");
            return (Criteria) this;
        }

        public Criteria andLine_numbersGreaterThanOrEqualTo(String value) {
            addCriterion("line_numbers >=", value, "line_numbers");
            return (Criteria) this;
        }

        public Criteria andLine_numbersLessThan(String value) {
            addCriterion("line_numbers <", value, "line_numbers");
            return (Criteria) this;
        }

        public Criteria andLine_numbersLessThanOrEqualTo(String value) {
            addCriterion("line_numbers <=", value, "line_numbers");
            return (Criteria) this;
        }

        public Criteria andLine_numbersLike(String value) {
            addCriterion("line_numbers like", value, "line_numbers");
            return (Criteria) this;
        }

        public Criteria andLine_numbersNotLike(String value) {
            addCriterion("line_numbers not like", value, "line_numbers");
            return (Criteria) this;
        }

        public Criteria andLine_numbersIn(List<String> values) {
            addCriterion("line_numbers in", values, "line_numbers");
            return (Criteria) this;
        }

        public Criteria andLine_numbersNotIn(List<String> values) {
            addCriterion("line_numbers not in", values, "line_numbers");
            return (Criteria) this;
        }

        public Criteria andLine_numbersBetween(String value1, String value2) {
            addCriterion("line_numbers between", value1, value2, "line_numbers");
            return (Criteria) this;
        }

        public Criteria andLine_numbersNotBetween(String value1, String value2) {
            addCriterion("line_numbers not between", value1, value2, "line_numbers");
            return (Criteria) this;
        }

        public Criteria andPurchase_order_noIsNull() {
            addCriterion("purchase_order_no is null");
            return (Criteria) this;
        }

        public Criteria andPurchase_order_noIsNotNull() {
            addCriterion("purchase_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andPurchase_order_noEqualTo(String value) {
            addCriterion("purchase_order_no =", value, "purchase_order_no");
            return (Criteria) this;
        }

        public Criteria andPurchase_order_noNotEqualTo(String value) {
            addCriterion("purchase_order_no <>", value, "purchase_order_no");
            return (Criteria) this;
        }

        public Criteria andPurchase_order_noGreaterThan(String value) {
            addCriterion("purchase_order_no >", value, "purchase_order_no");
            return (Criteria) this;
        }

        public Criteria andPurchase_order_noGreaterThanOrEqualTo(String value) {
            addCriterion("purchase_order_no >=", value, "purchase_order_no");
            return (Criteria) this;
        }

        public Criteria andPurchase_order_noLessThan(String value) {
            addCriterion("purchase_order_no <", value, "purchase_order_no");
            return (Criteria) this;
        }

        public Criteria andPurchase_order_noLessThanOrEqualTo(String value) {
            addCriterion("purchase_order_no <=", value, "purchase_order_no");
            return (Criteria) this;
        }

        public Criteria andPurchase_order_noLike(String value) {
            addCriterion("purchase_order_no like", value, "purchase_order_no");
            return (Criteria) this;
        }

        public Criteria andPurchase_order_noNotLike(String value) {
            addCriterion("purchase_order_no not like", value, "purchase_order_no");
            return (Criteria) this;
        }

        public Criteria andPurchase_order_noIn(List<String> values) {
            addCriterion("purchase_order_no in", values, "purchase_order_no");
            return (Criteria) this;
        }

        public Criteria andPurchase_order_noNotIn(List<String> values) {
            addCriterion("purchase_order_no not in", values, "purchase_order_no");
            return (Criteria) this;
        }

        public Criteria andPurchase_order_noBetween(String value1, String value2) {
            addCriterion("purchase_order_no between", value1, value2, "purchase_order_no");
            return (Criteria) this;
        }

        public Criteria andPurchase_order_noNotBetween(String value1, String value2) {
            addCriterion("purchase_order_no not between", value1, value2, "purchase_order_no");
            return (Criteria) this;
        }

        public Criteria andOrder_timeIsNull() {
            addCriterion("order_time is null");
            return (Criteria) this;
        }

        public Criteria andOrder_timeIsNotNull() {
            addCriterion("order_time is not null");
            return (Criteria) this;
        }

        public Criteria andOrder_timeEqualTo(Date value) {
            addCriterionForJDBCDate("order_time =", value, "order_time");
            return (Criteria) this;
        }

        public Criteria andOrder_timeNotEqualTo(Date value) {
            addCriterionForJDBCDate("order_time <>", value, "order_time");
            return (Criteria) this;
        }

        public Criteria andOrder_timeGreaterThan(Date value) {
            addCriterionForJDBCDate("order_time >", value, "order_time");
            return (Criteria) this;
        }

        public Criteria andOrder_timeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("order_time >=", value, "order_time");
            return (Criteria) this;
        }

        public Criteria andOrder_timeLessThan(Date value) {
            addCriterionForJDBCDate("order_time <", value, "order_time");
            return (Criteria) this;
        }

        public Criteria andOrder_timeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("order_time <=", value, "order_time");
            return (Criteria) this;
        }

        public Criteria andOrder_timeIn(List<Date> values) {
            addCriterionForJDBCDate("order_time in", values, "order_time");
            return (Criteria) this;
        }

        public Criteria andOrder_timeNotIn(List<Date> values) {
            addCriterionForJDBCDate("order_time not in", values, "order_time");
            return (Criteria) this;
        }

        public Criteria andOrder_timeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("order_time between", value1, value2, "order_time");
            return (Criteria) this;
        }

        public Criteria andOrder_timeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("order_time not between", value1, value2, "order_time");
            return (Criteria) this;
        }

        public Criteria andBuyer_idIsNull() {
            addCriterion("buyer_id is null");
            return (Criteria) this;
        }

        public Criteria andBuyer_idIsNotNull() {
            addCriterion("buyer_id is not null");
            return (Criteria) this;
        }

        public Criteria andBuyer_idEqualTo(String value) {
            addCriterion("buyer_id =", value, "buyer_id");
            return (Criteria) this;
        }

        public Criteria andBuyer_idNotEqualTo(String value) {
            addCriterion("buyer_id <>", value, "buyer_id");
            return (Criteria) this;
        }

        public Criteria andBuyer_idGreaterThan(String value) {
            addCriterion("buyer_id >", value, "buyer_id");
            return (Criteria) this;
        }

        public Criteria andBuyer_idGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_id >=", value, "buyer_id");
            return (Criteria) this;
        }

        public Criteria andBuyer_idLessThan(String value) {
            addCriterion("buyer_id <", value, "buyer_id");
            return (Criteria) this;
        }

        public Criteria andBuyer_idLessThanOrEqualTo(String value) {
            addCriterion("buyer_id <=", value, "buyer_id");
            return (Criteria) this;
        }

        public Criteria andBuyer_idLike(String value) {
            addCriterion("buyer_id like", value, "buyer_id");
            return (Criteria) this;
        }

        public Criteria andBuyer_idNotLike(String value) {
            addCriterion("buyer_id not like", value, "buyer_id");
            return (Criteria) this;
        }

        public Criteria andBuyer_idIn(List<String> values) {
            addCriterion("buyer_id in", values, "buyer_id");
            return (Criteria) this;
        }

        public Criteria andBuyer_idNotIn(List<String> values) {
            addCriterion("buyer_id not in", values, "buyer_id");
            return (Criteria) this;
        }

        public Criteria andBuyer_idBetween(String value1, String value2) {
            addCriterion("buyer_id between", value1, value2, "buyer_id");
            return (Criteria) this;
        }

        public Criteria andBuyer_idNotBetween(String value1, String value2) {
            addCriterion("buyer_id not between", value1, value2, "buyer_id");
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
            addCriterionForJDBCDate("delivery_time =", value, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeNotEqualTo(Date value) {
            addCriterionForJDBCDate("delivery_time <>", value, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeGreaterThan(Date value) {
            addCriterionForJDBCDate("delivery_time >", value, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("delivery_time >=", value, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeLessThan(Date value) {
            addCriterionForJDBCDate("delivery_time <", value, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("delivery_time <=", value, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeIn(List<Date> values) {
            addCriterionForJDBCDate("delivery_time in", values, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeNotIn(List<Date> values) {
            addCriterionForJDBCDate("delivery_time not in", values, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("delivery_time between", value1, value2, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andDelivery_timeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("delivery_time not between", value1, value2, "delivery_time");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeIsNull() {
            addCriterion("Cutasingle_time is null");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeIsNotNull() {
            addCriterion("Cutasingle_time is not null");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeEqualTo(Date value) {
            addCriterionForJDBCDate("Cutasingle_time =", value, "cutasingle_time");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeNotEqualTo(Date value) {
            addCriterionForJDBCDate("Cutasingle_time <>", value, "cutasingle_time");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeGreaterThan(Date value) {
            addCriterionForJDBCDate("Cutasingle_time >", value, "cutasingle_time");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("Cutasingle_time >=", value, "cutasingle_time");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeLessThan(Date value) {
            addCriterionForJDBCDate("Cutasingle_time <", value, "cutasingle_time");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("Cutasingle_time <=", value, "cutasingle_time");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeIn(List<Date> values) {
            addCriterionForJDBCDate("Cutasingle_time in", values, "cutasingle_time");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeNotIn(List<Date> values) {
            addCriterionForJDBCDate("Cutasingle_time not in", values, "cutasingle_time");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("Cutasingle_time between", value1, value2, "cutasingle_time");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("Cutasingle_time not between", value1, value2, "cutasingle_time");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_timeIsNull() {
            addCriterion("confirm_delivery_time is null");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_timeIsNotNull() {
            addCriterion("confirm_delivery_time is not null");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_timeEqualTo(Date value) {
            addCriterionForJDBCDate("confirm_delivery_time =", value, "confirm_delivery_time");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_timeNotEqualTo(Date value) {
            addCriterionForJDBCDate("confirm_delivery_time <>", value, "confirm_delivery_time");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_timeGreaterThan(Date value) {
            addCriterionForJDBCDate("confirm_delivery_time >", value, "confirm_delivery_time");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_timeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("confirm_delivery_time >=", value, "confirm_delivery_time");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_timeLessThan(Date value) {
            addCriterionForJDBCDate("confirm_delivery_time <", value, "confirm_delivery_time");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_timeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("confirm_delivery_time <=", value, "confirm_delivery_time");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_timeIn(List<Date> values) {
            addCriterionForJDBCDate("confirm_delivery_time in", values, "confirm_delivery_time");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_timeNotIn(List<Date> values) {
            addCriterionForJDBCDate("confirm_delivery_time not in", values, "confirm_delivery_time");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_timeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("confirm_delivery_time between", value1, value2, "confirm_delivery_time");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_timeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("confirm_delivery_time not between", value1, value2, "confirm_delivery_time");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_numbersIsNull() {
            addCriterion("confirm_delivery_numbers is null");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_numbersIsNotNull() {
            addCriterion("confirm_delivery_numbers is not null");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_numbersEqualTo(Integer value) {
            addCriterion("confirm_delivery_numbers =", value, "confirm_delivery_numbers");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_numbersNotEqualTo(Integer value) {
            addCriterion("confirm_delivery_numbers <>", value, "confirm_delivery_numbers");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_numbersGreaterThan(Integer value) {
            addCriterion("confirm_delivery_numbers >", value, "confirm_delivery_numbers");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_numbersGreaterThanOrEqualTo(Integer value) {
            addCriterion("confirm_delivery_numbers >=", value, "confirm_delivery_numbers");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_numbersLessThan(Integer value) {
            addCriterion("confirm_delivery_numbers <", value, "confirm_delivery_numbers");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_numbersLessThanOrEqualTo(Integer value) {
            addCriterion("confirm_delivery_numbers <=", value, "confirm_delivery_numbers");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_numbersIn(List<Integer> values) {
            addCriterion("confirm_delivery_numbers in", values, "confirm_delivery_numbers");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_numbersNotIn(List<Integer> values) {
            addCriterion("confirm_delivery_numbers not in", values, "confirm_delivery_numbers");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_numbersBetween(Integer value1, Integer value2) {
            addCriterion("confirm_delivery_numbers between", value1, value2, "confirm_delivery_numbers");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_numbersNotBetween(Integer value1, Integer value2) {
            addCriterion("confirm_delivery_numbers not between", value1, value2, "confirm_delivery_numbers");
            return (Criteria) this;
        }

        public Criteria andPurchasing_modeIsNull() {
            addCriterion("purchasing_mode is null");
            return (Criteria) this;
        }

        public Criteria andPurchasing_modeIsNotNull() {
            addCriterion("purchasing_mode is not null");
            return (Criteria) this;
        }

        public Criteria andPurchasing_modeEqualTo(Integer value) {
            addCriterion("purchasing_mode =", value, "purchasing_mode");
            return (Criteria) this;
        }

        public Criteria andPurchasing_modeNotEqualTo(Integer value) {
            addCriterion("purchasing_mode <>", value, "purchasing_mode");
            return (Criteria) this;
        }

        public Criteria andPurchasing_modeGreaterThan(Integer value) {
            addCriterion("purchasing_mode >", value, "purchasing_mode");
            return (Criteria) this;
        }

        public Criteria andPurchasing_modeGreaterThanOrEqualTo(Integer value) {
            addCriterion("purchasing_mode >=", value, "purchasing_mode");
            return (Criteria) this;
        }

        public Criteria andPurchasing_modeLessThan(Integer value) {
            addCriterion("purchasing_mode <", value, "purchasing_mode");
            return (Criteria) this;
        }

        public Criteria andPurchasing_modeLessThanOrEqualTo(Integer value) {
            addCriterion("purchasing_mode <=", value, "purchasing_mode");
            return (Criteria) this;
        }

        public Criteria andPurchasing_modeIn(List<Integer> values) {
            addCriterion("purchasing_mode in", values, "purchasing_mode");
            return (Criteria) this;
        }

        public Criteria andPurchasing_modeNotIn(List<Integer> values) {
            addCriterion("purchasing_mode not in", values, "purchasing_mode");
            return (Criteria) this;
        }

        public Criteria andPurchasing_modeBetween(Integer value1, Integer value2) {
            addCriterion("purchasing_mode between", value1, value2, "purchasing_mode");
            return (Criteria) this;
        }

        public Criteria andPurchasing_modeNotBetween(Integer value1, Integer value2) {
            addCriterion("purchasing_mode not between", value1, value2, "purchasing_mode");
            return (Criteria) this;
        }

        public Criteria andTaxIsNull() {
            addCriterion("tax is null");
            return (Criteria) this;
        }

        public Criteria andTaxIsNotNull() {
            addCriterion("tax is not null");
            return (Criteria) this;
        }

        public Criteria andTaxEqualTo(Integer value) {
            addCriterion("tax =", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotEqualTo(Integer value) {
            addCriterion("tax <>", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxGreaterThan(Integer value) {
            addCriterion("tax >", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("tax >=", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxLessThan(Integer value) {
            addCriterion("tax <", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxLessThanOrEqualTo(Integer value) {
            addCriterion("tax <=", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxIn(List<Integer> values) {
            addCriterion("tax in", values, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotIn(List<Integer> values) {
            addCriterion("tax not in", values, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxBetween(Integer value1, Integer value2) {
            addCriterion("tax between", value1, value2, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotBetween(Integer value1, Integer value2) {
            addCriterion("tax not between", value1, value2, "tax");
            return (Criteria) this;
        }

        public Criteria andConfirm_orderIsNull() {
            addCriterion("confirm_order is null");
            return (Criteria) this;
        }

        public Criteria andConfirm_orderIsNotNull() {
            addCriterion("confirm_order is not null");
            return (Criteria) this;
        }

        public Criteria andConfirm_orderEqualTo(Integer value) {
            addCriterion("confirm_order =", value, "confirm_order");
            return (Criteria) this;
        }

        public Criteria andConfirm_orderNotEqualTo(Integer value) {
            addCriterion("confirm_order <>", value, "confirm_order");
            return (Criteria) this;
        }

        public Criteria andConfirm_orderGreaterThan(Integer value) {
            addCriterion("confirm_order >", value, "confirm_order");
            return (Criteria) this;
        }

        public Criteria andConfirm_orderGreaterThanOrEqualTo(Integer value) {
            addCriterion("confirm_order >=", value, "confirm_order");
            return (Criteria) this;
        }

        public Criteria andConfirm_orderLessThan(Integer value) {
            addCriterion("confirm_order <", value, "confirm_order");
            return (Criteria) this;
        }

        public Criteria andConfirm_orderLessThanOrEqualTo(Integer value) {
            addCriterion("confirm_order <=", value, "confirm_order");
            return (Criteria) this;
        }

        public Criteria andConfirm_orderIn(List<Integer> values) {
            addCriterion("confirm_order in", values, "confirm_order");
            return (Criteria) this;
        }

        public Criteria andConfirm_orderNotIn(List<Integer> values) {
            addCriterion("confirm_order not in", values, "confirm_order");
            return (Criteria) this;
        }

        public Criteria andConfirm_orderBetween(Integer value1, Integer value2) {
            addCriterion("confirm_order between", value1, value2, "confirm_order");
            return (Criteria) this;
        }

        public Criteria andConfirm_orderNotBetween(Integer value1, Integer value2) {
            addCriterion("confirm_order not between", value1, value2, "confirm_order");
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