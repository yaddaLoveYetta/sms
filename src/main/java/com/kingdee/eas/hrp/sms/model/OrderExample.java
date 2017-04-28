package com.kingdee.eas.hrp.sms.model;

import java.util.ArrayList;
import java.util.Date;
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

        public Criteria andLine_numbersIsNull() {
            addCriterion("line_numbers is null");
            return (Criteria) this;
        }

        public Criteria andLine_numbersIsNotNull() {
            addCriterion("line_numbers is not null");
            return (Criteria) this;
        }

        public Criteria andLine_numbersEqualTo(Integer value) {
            addCriterion("line_numbers =", value, "line_numbers");
            return (Criteria) this;
        }

        public Criteria andLine_numbersNotEqualTo(Integer value) {
            addCriterion("line_numbers <>", value, "line_numbers");
            return (Criteria) this;
        }

        public Criteria andLine_numbersGreaterThan(Integer value) {
            addCriterion("line_numbers >", value, "line_numbers");
            return (Criteria) this;
        }

        public Criteria andLine_numbersGreaterThanOrEqualTo(Integer value) {
            addCriterion("line_numbers >=", value, "line_numbers");
            return (Criteria) this;
        }

        public Criteria andLine_numbersLessThan(Integer value) {
            addCriterion("line_numbers <", value, "line_numbers");
            return (Criteria) this;
        }

        public Criteria andLine_numbersLessThanOrEqualTo(Integer value) {
            addCriterion("line_numbers <=", value, "line_numbers");
            return (Criteria) this;
        }

        public Criteria andLine_numbersIn(List<Integer> values) {
            addCriterion("line_numbers in", values, "line_numbers");
            return (Criteria) this;
        }

        public Criteria andLine_numbersNotIn(List<Integer> values) {
            addCriterion("line_numbers not in", values, "line_numbers");
            return (Criteria) this;
        }

        public Criteria andLine_numbersBetween(Integer value1, Integer value2) {
            addCriterion("line_numbers between", value1, value2, "line_numbers");
            return (Criteria) this;
        }

        public Criteria andLine_numbersNotBetween(Integer value1, Integer value2) {
            addCriterion("line_numbers not between", value1, value2, "line_numbers");
            return (Criteria) this;
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
            addCriterion("order_time =", value, "order_time");
            return (Criteria) this;
        }

        public Criteria andOrder_timeNotEqualTo(Date value) {
            addCriterion("order_time <>", value, "order_time");
            return (Criteria) this;
        }

        public Criteria andOrder_timeGreaterThan(Date value) {
            addCriterion("order_time >", value, "order_time");
            return (Criteria) this;
        }

        public Criteria andOrder_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("order_time >=", value, "order_time");
            return (Criteria) this;
        }

        public Criteria andOrder_timeLessThan(Date value) {
            addCriterion("order_time <", value, "order_time");
            return (Criteria) this;
        }

        public Criteria andOrder_timeLessThanOrEqualTo(Date value) {
            addCriterion("order_time <=", value, "order_time");
            return (Criteria) this;
        }

        public Criteria andOrder_timeIn(List<Date> values) {
            addCriterion("order_time in", values, "order_time");
            return (Criteria) this;
        }

        public Criteria andOrder_timeNotIn(List<Date> values) {
            addCriterion("order_time not in", values, "order_time");
            return (Criteria) this;
        }

        public Criteria andOrder_timeBetween(Date value1, Date value2) {
            addCriterion("order_time between", value1, value2, "order_time");
            return (Criteria) this;
        }

        public Criteria andOrder_timeNotBetween(Date value1, Date value2) {
            addCriterion("order_time not between", value1, value2, "order_time");
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

        public Criteria andCutasingle_timeIsNull() {
            addCriterion("cutasingle_time is null");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeIsNotNull() {
            addCriterion("cutasingle_time is not null");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeEqualTo(Date value) {
            addCriterion("cutasingle_time =", value, "cutasingle_time");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeNotEqualTo(Date value) {
            addCriterion("cutasingle_time <>", value, "cutasingle_time");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeGreaterThan(Date value) {
            addCriterion("cutasingle_time >", value, "cutasingle_time");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("cutasingle_time >=", value, "cutasingle_time");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeLessThan(Date value) {
            addCriterion("cutasingle_time <", value, "cutasingle_time");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeLessThanOrEqualTo(Date value) {
            addCriterion("cutasingle_time <=", value, "cutasingle_time");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeIn(List<Date> values) {
            addCriterion("cutasingle_time in", values, "cutasingle_time");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeNotIn(List<Date> values) {
            addCriterion("cutasingle_time not in", values, "cutasingle_time");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeBetween(Date value1, Date value2) {
            addCriterion("cutasingle_time between", value1, value2, "cutasingle_time");
            return (Criteria) this;
        }

        public Criteria andCutasingle_timeNotBetween(Date value1, Date value2) {
            addCriterion("cutasingle_time not between", value1, value2, "cutasingle_time");
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
            addCriterion("confirm_delivery_time =", value, "confirm_delivery_time");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_timeNotEqualTo(Date value) {
            addCriterion("confirm_delivery_time <>", value, "confirm_delivery_time");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_timeGreaterThan(Date value) {
            addCriterion("confirm_delivery_time >", value, "confirm_delivery_time");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("confirm_delivery_time >=", value, "confirm_delivery_time");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_timeLessThan(Date value) {
            addCriterion("confirm_delivery_time <", value, "confirm_delivery_time");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_timeLessThanOrEqualTo(Date value) {
            addCriterion("confirm_delivery_time <=", value, "confirm_delivery_time");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_timeIn(List<Date> values) {
            addCriterion("confirm_delivery_time in", values, "confirm_delivery_time");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_timeNotIn(List<Date> values) {
            addCriterion("confirm_delivery_time not in", values, "confirm_delivery_time");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_timeBetween(Date value1, Date value2) {
            addCriterion("confirm_delivery_time between", value1, value2, "confirm_delivery_time");
            return (Criteria) this;
        }

        public Criteria andConfirm_delivery_timeNotBetween(Date value1, Date value2) {
            addCriterion("confirm_delivery_time not between", value1, value2, "confirm_delivery_time");
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