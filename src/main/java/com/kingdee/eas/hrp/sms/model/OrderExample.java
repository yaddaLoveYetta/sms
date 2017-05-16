package com.kingdee.eas.hrp.sms.model;

import java.math.BigDecimal;
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

        public Criteria andSupplierIsNull() {
            addCriterion("supplier is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIsNotNull() {
            addCriterion("supplier is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierEqualTo(String value) {
            addCriterion("supplier =", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotEqualTo(String value) {
            addCriterion("supplier <>", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierGreaterThan(String value) {
            addCriterion("supplier >", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierGreaterThanOrEqualTo(String value) {
            addCriterion("supplier >=", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLessThan(String value) {
            addCriterion("supplier <", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLessThanOrEqualTo(String value) {
            addCriterion("supplier <=", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLike(String value) {
            addCriterion("supplier like", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotLike(String value) {
            addCriterion("supplier not like", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierIn(List<String> values) {
            addCriterion("supplier in", values, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotIn(List<String> values) {
            addCriterion("supplier not in", values, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierBetween(String value1, String value2) {
            addCriterion("supplier between", value1, value2, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotBetween(String value1, String value2) {
            addCriterion("supplier not between", value1, value2, "supplier");
            return (Criteria) this;
        }

        public Criteria andBizDateIsNull() {
            addCriterion("bizDate is null");
            return (Criteria) this;
        }

        public Criteria andBizDateIsNotNull() {
            addCriterion("bizDate is not null");
            return (Criteria) this;
        }

        public Criteria andBizDateEqualTo(Date value) {
            addCriterion("bizDate =", value, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateNotEqualTo(Date value) {
            addCriterion("bizDate <>", value, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateGreaterThan(Date value) {
            addCriterion("bizDate >", value, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateGreaterThanOrEqualTo(Date value) {
            addCriterion("bizDate >=", value, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateLessThan(Date value) {
            addCriterion("bizDate <", value, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateLessThanOrEqualTo(Date value) {
            addCriterion("bizDate <=", value, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateIn(List<Date> values) {
            addCriterion("bizDate in", values, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateNotIn(List<Date> values) {
            addCriterion("bizDate not in", values, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateBetween(Date value1, Date value2) {
            addCriterion("bizDate between", value1, value2, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateNotBetween(Date value1, Date value2) {
            addCriterion("bizDate not between", value1, value2, "bizDate");
            return (Criteria) this;
        }

        public Criteria andPurchasePersonIsNull() {
            addCriterion("purchasePerson is null");
            return (Criteria) this;
        }

        public Criteria andPurchasePersonIsNotNull() {
            addCriterion("purchasePerson is not null");
            return (Criteria) this;
        }

        public Criteria andPurchasePersonEqualTo(String value) {
            addCriterion("purchasePerson =", value, "purchasePerson");
            return (Criteria) this;
        }

        public Criteria andPurchasePersonNotEqualTo(String value) {
            addCriterion("purchasePerson <>", value, "purchasePerson");
            return (Criteria) this;
        }

        public Criteria andPurchasePersonGreaterThan(String value) {
            addCriterion("purchasePerson >", value, "purchasePerson");
            return (Criteria) this;
        }

        public Criteria andPurchasePersonGreaterThanOrEqualTo(String value) {
            addCriterion("purchasePerson >=", value, "purchasePerson");
            return (Criteria) this;
        }

        public Criteria andPurchasePersonLessThan(String value) {
            addCriterion("purchasePerson <", value, "purchasePerson");
            return (Criteria) this;
        }

        public Criteria andPurchasePersonLessThanOrEqualTo(String value) {
            addCriterion("purchasePerson <=", value, "purchasePerson");
            return (Criteria) this;
        }

        public Criteria andPurchasePersonLike(String value) {
            addCriterion("purchasePerson like", value, "purchasePerson");
            return (Criteria) this;
        }

        public Criteria andPurchasePersonNotLike(String value) {
            addCriterion("purchasePerson not like", value, "purchasePerson");
            return (Criteria) this;
        }

        public Criteria andPurchasePersonIn(List<String> values) {
            addCriterion("purchasePerson in", values, "purchasePerson");
            return (Criteria) this;
        }

        public Criteria andPurchasePersonNotIn(List<String> values) {
            addCriterion("purchasePerson not in", values, "purchasePerson");
            return (Criteria) this;
        }

        public Criteria andPurchasePersonBetween(String value1, String value2) {
            addCriterion("purchasePerson between", value1, value2, "purchasePerson");
            return (Criteria) this;
        }

        public Criteria andPurchasePersonNotBetween(String value1, String value2) {
            addCriterion("purchasePerson not between", value1, value2, "purchasePerson");
            return (Criteria) this;
        }

        public Criteria andSaleProxyIsNull() {
            addCriterion("saleProxy is null");
            return (Criteria) this;
        }

        public Criteria andSaleProxyIsNotNull() {
            addCriterion("saleProxy is not null");
            return (Criteria) this;
        }

        public Criteria andSaleProxyEqualTo(String value) {
            addCriterion("saleProxy =", value, "saleProxy");
            return (Criteria) this;
        }

        public Criteria andSaleProxyNotEqualTo(String value) {
            addCriterion("saleProxy <>", value, "saleProxy");
            return (Criteria) this;
        }

        public Criteria andSaleProxyGreaterThan(String value) {
            addCriterion("saleProxy >", value, "saleProxy");
            return (Criteria) this;
        }

        public Criteria andSaleProxyGreaterThanOrEqualTo(String value) {
            addCriterion("saleProxy >=", value, "saleProxy");
            return (Criteria) this;
        }

        public Criteria andSaleProxyLessThan(String value) {
            addCriterion("saleProxy <", value, "saleProxy");
            return (Criteria) this;
        }

        public Criteria andSaleProxyLessThanOrEqualTo(String value) {
            addCriterion("saleProxy <=", value, "saleProxy");
            return (Criteria) this;
        }

        public Criteria andSaleProxyLike(String value) {
            addCriterion("saleProxy like", value, "saleProxy");
            return (Criteria) this;
        }

        public Criteria andSaleProxyNotLike(String value) {
            addCriterion("saleProxy not like", value, "saleProxy");
            return (Criteria) this;
        }

        public Criteria andSaleProxyIn(List<String> values) {
            addCriterion("saleProxy in", values, "saleProxy");
            return (Criteria) this;
        }

        public Criteria andSaleProxyNotIn(List<String> values) {
            addCriterion("saleProxy not in", values, "saleProxy");
            return (Criteria) this;
        }

        public Criteria andSaleProxyBetween(String value1, String value2) {
            addCriterion("saleProxy between", value1, value2, "saleProxy");
            return (Criteria) this;
        }

        public Criteria andSaleProxyNotBetween(String value1, String value2) {
            addCriterion("saleProxy not between", value1, value2, "saleProxy");
            return (Criteria) this;
        }

        public Criteria andIsInTaxIsNull() {
            addCriterion("isInTax is null");
            return (Criteria) this;
        }

        public Criteria andIsInTaxIsNotNull() {
            addCriterion("isInTax is not null");
            return (Criteria) this;
        }

        public Criteria andIsInTaxEqualTo(Byte value) {
            addCriterion("isInTax =", value, "isInTax");
            return (Criteria) this;
        }

        public Criteria andIsInTaxNotEqualTo(Byte value) {
            addCriterion("isInTax <>", value, "isInTax");
            return (Criteria) this;
        }

        public Criteria andIsInTaxGreaterThan(Byte value) {
            addCriterion("isInTax >", value, "isInTax");
            return (Criteria) this;
        }

        public Criteria andIsInTaxGreaterThanOrEqualTo(Byte value) {
            addCriterion("isInTax >=", value, "isInTax");
            return (Criteria) this;
        }

        public Criteria andIsInTaxLessThan(Byte value) {
            addCriterion("isInTax <", value, "isInTax");
            return (Criteria) this;
        }

        public Criteria andIsInTaxLessThanOrEqualTo(Byte value) {
            addCriterion("isInTax <=", value, "isInTax");
            return (Criteria) this;
        }

        public Criteria andIsInTaxIn(List<Byte> values) {
            addCriterion("isInTax in", values, "isInTax");
            return (Criteria) this;
        }

        public Criteria andIsInTaxNotIn(List<Byte> values) {
            addCriterion("isInTax not in", values, "isInTax");
            return (Criteria) this;
        }

        public Criteria andIsInTaxBetween(Byte value1, Byte value2) {
            addCriterion("isInTax between", value1, value2, "isInTax");
            return (Criteria) this;
        }

        public Criteria andIsInTaxNotBetween(Byte value1, Byte value2) {
            addCriterion("isInTax not between", value1, value2, "isInTax");
            return (Criteria) this;
        }

        public Criteria andTickDateIsNull() {
            addCriterion("tickDate is null");
            return (Criteria) this;
        }

        public Criteria andTickDateIsNotNull() {
            addCriterion("tickDate is not null");
            return (Criteria) this;
        }

        public Criteria andTickDateEqualTo(Date value) {
            addCriterion("tickDate =", value, "tickDate");
            return (Criteria) this;
        }

        public Criteria andTickDateNotEqualTo(Date value) {
            addCriterion("tickDate <>", value, "tickDate");
            return (Criteria) this;
        }

        public Criteria andTickDateGreaterThan(Date value) {
            addCriterion("tickDate >", value, "tickDate");
            return (Criteria) this;
        }

        public Criteria andTickDateGreaterThanOrEqualTo(Date value) {
            addCriterion("tickDate >=", value, "tickDate");
            return (Criteria) this;
        }

        public Criteria andTickDateLessThan(Date value) {
            addCriterion("tickDate <", value, "tickDate");
            return (Criteria) this;
        }

        public Criteria andTickDateLessThanOrEqualTo(Date value) {
            addCriterion("tickDate <=", value, "tickDate");
            return (Criteria) this;
        }

        public Criteria andTickDateIn(List<Date> values) {
            addCriterion("tickDate in", values, "tickDate");
            return (Criteria) this;
        }

        public Criteria andTickDateNotIn(List<Date> values) {
            addCriterion("tickDate not in", values, "tickDate");
            return (Criteria) this;
        }

        public Criteria andTickDateBetween(Date value1, Date value2) {
            addCriterion("tickDate between", value1, value2, "tickDate");
            return (Criteria) this;
        }

        public Criteria andTickDateNotBetween(Date value1, Date value2) {
            addCriterion("tickDate not between", value1, value2, "tickDate");
            return (Criteria) this;
        }

        public Criteria andConfirmTickDateIsNull() {
            addCriterion("confirmTickDate is null");
            return (Criteria) this;
        }

        public Criteria andConfirmTickDateIsNotNull() {
            addCriterion("confirmTickDate is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmTickDateEqualTo(Date value) {
            addCriterion("confirmTickDate =", value, "confirmTickDate");
            return (Criteria) this;
        }

        public Criteria andConfirmTickDateNotEqualTo(Date value) {
            addCriterion("confirmTickDate <>", value, "confirmTickDate");
            return (Criteria) this;
        }

        public Criteria andConfirmTickDateGreaterThan(Date value) {
            addCriterion("confirmTickDate >", value, "confirmTickDate");
            return (Criteria) this;
        }

        public Criteria andConfirmTickDateGreaterThanOrEqualTo(Date value) {
            addCriterion("confirmTickDate >=", value, "confirmTickDate");
            return (Criteria) this;
        }

        public Criteria andConfirmTickDateLessThan(Date value) {
            addCriterion("confirmTickDate <", value, "confirmTickDate");
            return (Criteria) this;
        }

        public Criteria andConfirmTickDateLessThanOrEqualTo(Date value) {
            addCriterion("confirmTickDate <=", value, "confirmTickDate");
            return (Criteria) this;
        }

        public Criteria andConfirmTickDateIn(List<Date> values) {
            addCriterion("confirmTickDate in", values, "confirmTickDate");
            return (Criteria) this;
        }

        public Criteria andConfirmTickDateNotIn(List<Date> values) {
            addCriterion("confirmTickDate not in", values, "confirmTickDate");
            return (Criteria) this;
        }

        public Criteria andConfirmTickDateBetween(Date value1, Date value2) {
            addCriterion("confirmTickDate between", value1, value2, "confirmTickDate");
            return (Criteria) this;
        }

        public Criteria andConfirmTickDateNotBetween(Date value1, Date value2) {
            addCriterion("confirmTickDate not between", value1, value2, "confirmTickDate");
            return (Criteria) this;
        }

        public Criteria andTickTypeIsNull() {
            addCriterion("tickType is null");
            return (Criteria) this;
        }

        public Criteria andTickTypeIsNotNull() {
            addCriterion("tickType is not null");
            return (Criteria) this;
        }

        public Criteria andTickTypeEqualTo(Byte value) {
            addCriterion("tickType =", value, "tickType");
            return (Criteria) this;
        }

        public Criteria andTickTypeNotEqualTo(Byte value) {
            addCriterion("tickType <>", value, "tickType");
            return (Criteria) this;
        }

        public Criteria andTickTypeGreaterThan(Byte value) {
            addCriterion("tickType >", value, "tickType");
            return (Criteria) this;
        }

        public Criteria andTickTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("tickType >=", value, "tickType");
            return (Criteria) this;
        }

        public Criteria andTickTypeLessThan(Byte value) {
            addCriterion("tickType <", value, "tickType");
            return (Criteria) this;
        }

        public Criteria andTickTypeLessThanOrEqualTo(Byte value) {
            addCriterion("tickType <=", value, "tickType");
            return (Criteria) this;
        }

        public Criteria andTickTypeIn(List<Byte> values) {
            addCriterion("tickType in", values, "tickType");
            return (Criteria) this;
        }

        public Criteria andTickTypeNotIn(List<Byte> values) {
            addCriterion("tickType not in", values, "tickType");
            return (Criteria) this;
        }

        public Criteria andTickTypeBetween(Byte value1, Byte value2) {
            addCriterion("tickType between", value1, value2, "tickType");
            return (Criteria) this;
        }

        public Criteria andTickTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("tickType not between", value1, value2, "tickType");
            return (Criteria) this;
        }

        public Criteria andConfirmTickIsNull() {
            addCriterion("confirmTick is null");
            return (Criteria) this;
        }

        public Criteria andConfirmTickIsNotNull() {
            addCriterion("confirmTick is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmTickEqualTo(Byte value) {
            addCriterion("confirmTick =", value, "confirmTick");
            return (Criteria) this;
        }

        public Criteria andConfirmTickNotEqualTo(Byte value) {
            addCriterion("confirmTick <>", value, "confirmTick");
            return (Criteria) this;
        }

        public Criteria andConfirmTickGreaterThan(Byte value) {
            addCriterion("confirmTick >", value, "confirmTick");
            return (Criteria) this;
        }

        public Criteria andConfirmTickGreaterThanOrEqualTo(Byte value) {
            addCriterion("confirmTick >=", value, "confirmTick");
            return (Criteria) this;
        }

        public Criteria andConfirmTickLessThan(Byte value) {
            addCriterion("confirmTick <", value, "confirmTick");
            return (Criteria) this;
        }

        public Criteria andConfirmTickLessThanOrEqualTo(Byte value) {
            addCriterion("confirmTick <=", value, "confirmTick");
            return (Criteria) this;
        }

        public Criteria andConfirmTickIn(List<Byte> values) {
            addCriterion("confirmTick in", values, "confirmTick");
            return (Criteria) this;
        }

        public Criteria andConfirmTickNotIn(List<Byte> values) {
            addCriterion("confirmTick not in", values, "confirmTick");
            return (Criteria) this;
        }

        public Criteria andConfirmTickBetween(Byte value1, Byte value2) {
            addCriterion("confirmTick between", value1, value2, "confirmTick");
            return (Criteria) this;
        }

        public Criteria andConfirmTickNotBetween(Byte value1, Byte value2) {
            addCriterion("confirmTick not between", value1, value2, "confirmTick");
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

        public Criteria andIsQuickenIsNull() {
            addCriterion("isQuicken is null");
            return (Criteria) this;
        }

        public Criteria andIsQuickenIsNotNull() {
            addCriterion("isQuicken is not null");
            return (Criteria) this;
        }

        public Criteria andIsQuickenEqualTo(Byte value) {
            addCriterion("isQuicken =", value, "isQuicken");
            return (Criteria) this;
        }

        public Criteria andIsQuickenNotEqualTo(Byte value) {
            addCriterion("isQuicken <>", value, "isQuicken");
            return (Criteria) this;
        }

        public Criteria andIsQuickenGreaterThan(Byte value) {
            addCriterion("isQuicken >", value, "isQuicken");
            return (Criteria) this;
        }

        public Criteria andIsQuickenGreaterThanOrEqualTo(Byte value) {
            addCriterion("isQuicken >=", value, "isQuicken");
            return (Criteria) this;
        }

        public Criteria andIsQuickenLessThan(Byte value) {
            addCriterion("isQuicken <", value, "isQuicken");
            return (Criteria) this;
        }

        public Criteria andIsQuickenLessThanOrEqualTo(Byte value) {
            addCriterion("isQuicken <=", value, "isQuicken");
            return (Criteria) this;
        }

        public Criteria andIsQuickenIn(List<Byte> values) {
            addCriterion("isQuicken in", values, "isQuicken");
            return (Criteria) this;
        }

        public Criteria andIsQuickenNotIn(List<Byte> values) {
            addCriterion("isQuicken not in", values, "isQuicken");
            return (Criteria) this;
        }

        public Criteria andIsQuickenBetween(Byte value1, Byte value2) {
            addCriterion("isQuicken between", value1, value2, "isQuicken");
            return (Criteria) this;
        }

        public Criteria andIsQuickenNotBetween(Byte value1, Byte value2) {
            addCriterion("isQuicken not between", value1, value2, "isQuicken");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNull() {
            addCriterion("currency is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("currency is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(String value) {
            addCriterion("currency =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(String value) {
            addCriterion("currency <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(String value) {
            addCriterion("currency >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("currency >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(String value) {
            addCriterion("currency <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(String value) {
            addCriterion("currency <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLike(String value) {
            addCriterion("currency like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotLike(String value) {
            addCriterion("currency not like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<String> values) {
            addCriterion("currency in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<String> values) {
            addCriterion("currency not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(String value1, String value2) {
            addCriterion("currency between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(String value1, String value2) {
            addCriterion("currency not between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andSettlementTypeIsNull() {
            addCriterion("settlementType is null");
            return (Criteria) this;
        }

        public Criteria andSettlementTypeIsNotNull() {
            addCriterion("settlementType is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementTypeEqualTo(String value) {
            addCriterion("settlementType =", value, "settlementType");
            return (Criteria) this;
        }

        public Criteria andSettlementTypeNotEqualTo(String value) {
            addCriterion("settlementType <>", value, "settlementType");
            return (Criteria) this;
        }

        public Criteria andSettlementTypeGreaterThan(String value) {
            addCriterion("settlementType >", value, "settlementType");
            return (Criteria) this;
        }

        public Criteria andSettlementTypeGreaterThanOrEqualTo(String value) {
            addCriterion("settlementType >=", value, "settlementType");
            return (Criteria) this;
        }

        public Criteria andSettlementTypeLessThan(String value) {
            addCriterion("settlementType <", value, "settlementType");
            return (Criteria) this;
        }

        public Criteria andSettlementTypeLessThanOrEqualTo(String value) {
            addCriterion("settlementType <=", value, "settlementType");
            return (Criteria) this;
        }

        public Criteria andSettlementTypeLike(String value) {
            addCriterion("settlementType like", value, "settlementType");
            return (Criteria) this;
        }

        public Criteria andSettlementTypeNotLike(String value) {
            addCriterion("settlementType not like", value, "settlementType");
            return (Criteria) this;
        }

        public Criteria andSettlementTypeIn(List<String> values) {
            addCriterion("settlementType in", values, "settlementType");
            return (Criteria) this;
        }

        public Criteria andSettlementTypeNotIn(List<String> values) {
            addCriterion("settlementType not in", values, "settlementType");
            return (Criteria) this;
        }

        public Criteria andSettlementTypeBetween(String value1, String value2) {
            addCriterion("settlementType between", value1, value2, "settlementType");
            return (Criteria) this;
        }

        public Criteria andSettlementTypeNotBetween(String value1, String value2) {
            addCriterion("settlementType not between", value1, value2, "settlementType");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNull() {
            addCriterion("totalAmount is null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNotNull() {
            addCriterion("totalAmount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountEqualTo(BigDecimal value) {
            addCriterion("totalAmount =", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotEqualTo(BigDecimal value) {
            addCriterion("totalAmount <>", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThan(BigDecimal value) {
            addCriterion("totalAmount >", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("totalAmount >=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThan(BigDecimal value) {
            addCriterion("totalAmount <", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("totalAmount <=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIn(List<BigDecimal> values) {
            addCriterion("totalAmount in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotIn(List<BigDecimal> values) {
            addCriterion("totalAmount not in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalAmount between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalAmount not between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalTaxIsNull() {
            addCriterion("totalTax is null");
            return (Criteria) this;
        }

        public Criteria andTotalTaxIsNotNull() {
            addCriterion("totalTax is not null");
            return (Criteria) this;
        }

        public Criteria andTotalTaxEqualTo(BigDecimal value) {
            addCriterion("totalTax =", value, "totalTax");
            return (Criteria) this;
        }

        public Criteria andTotalTaxNotEqualTo(BigDecimal value) {
            addCriterion("totalTax <>", value, "totalTax");
            return (Criteria) this;
        }

        public Criteria andTotalTaxGreaterThan(BigDecimal value) {
            addCriterion("totalTax >", value, "totalTax");
            return (Criteria) this;
        }

        public Criteria andTotalTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("totalTax >=", value, "totalTax");
            return (Criteria) this;
        }

        public Criteria andTotalTaxLessThan(BigDecimal value) {
            addCriterion("totalTax <", value, "totalTax");
            return (Criteria) this;
        }

        public Criteria andTotalTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("totalTax <=", value, "totalTax");
            return (Criteria) this;
        }

        public Criteria andTotalTaxIn(List<BigDecimal> values) {
            addCriterion("totalTax in", values, "totalTax");
            return (Criteria) this;
        }

        public Criteria andTotalTaxNotIn(List<BigDecimal> values) {
            addCriterion("totalTax not in", values, "totalTax");
            return (Criteria) this;
        }

        public Criteria andTotalTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalTax between", value1, value2, "totalTax");
            return (Criteria) this;
        }

        public Criteria andTotalTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalTax not between", value1, value2, "totalTax");
            return (Criteria) this;
        }

        public Criteria andTotalTaxAmountIsNull() {
            addCriterion("totalTaxAmount is null");
            return (Criteria) this;
        }

        public Criteria andTotalTaxAmountIsNotNull() {
            addCriterion("totalTaxAmount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalTaxAmountEqualTo(BigDecimal value) {
            addCriterion("totalTaxAmount =", value, "totalTaxAmount");
            return (Criteria) this;
        }

        public Criteria andTotalTaxAmountNotEqualTo(BigDecimal value) {
            addCriterion("totalTaxAmount <>", value, "totalTaxAmount");
            return (Criteria) this;
        }

        public Criteria andTotalTaxAmountGreaterThan(BigDecimal value) {
            addCriterion("totalTaxAmount >", value, "totalTaxAmount");
            return (Criteria) this;
        }

        public Criteria andTotalTaxAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("totalTaxAmount >=", value, "totalTaxAmount");
            return (Criteria) this;
        }

        public Criteria andTotalTaxAmountLessThan(BigDecimal value) {
            addCriterion("totalTaxAmount <", value, "totalTaxAmount");
            return (Criteria) this;
        }

        public Criteria andTotalTaxAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("totalTaxAmount <=", value, "totalTaxAmount");
            return (Criteria) this;
        }

        public Criteria andTotalTaxAmountIn(List<BigDecimal> values) {
            addCriterion("totalTaxAmount in", values, "totalTaxAmount");
            return (Criteria) this;
        }

        public Criteria andTotalTaxAmountNotIn(List<BigDecimal> values) {
            addCriterion("totalTaxAmount not in", values, "totalTaxAmount");
            return (Criteria) this;
        }

        public Criteria andTotalTaxAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalTaxAmount between", value1, value2, "totalTaxAmount");
            return (Criteria) this;
        }

        public Criteria andTotalTaxAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totalTaxAmount not between", value1, value2, "totalTaxAmount");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("createTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andBaseStatusIsNull() {
            addCriterion("baseStatus is null");
            return (Criteria) this;
        }

        public Criteria andBaseStatusIsNotNull() {
            addCriterion("baseStatus is not null");
            return (Criteria) this;
        }

        public Criteria andBaseStatusEqualTo(Byte value) {
            addCriterion("baseStatus =", value, "baseStatus");
            return (Criteria) this;
        }

        public Criteria andBaseStatusNotEqualTo(Byte value) {
            addCriterion("baseStatus <>", value, "baseStatus");
            return (Criteria) this;
        }

        public Criteria andBaseStatusGreaterThan(Byte value) {
            addCriterion("baseStatus >", value, "baseStatus");
            return (Criteria) this;
        }

        public Criteria andBaseStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("baseStatus >=", value, "baseStatus");
            return (Criteria) this;
        }

        public Criteria andBaseStatusLessThan(Byte value) {
            addCriterion("baseStatus <", value, "baseStatus");
            return (Criteria) this;
        }

        public Criteria andBaseStatusLessThanOrEqualTo(Byte value) {
            addCriterion("baseStatus <=", value, "baseStatus");
            return (Criteria) this;
        }

        public Criteria andBaseStatusIn(List<Byte> values) {
            addCriterion("baseStatus in", values, "baseStatus");
            return (Criteria) this;
        }

        public Criteria andBaseStatusNotIn(List<Byte> values) {
            addCriterion("baseStatus not in", values, "baseStatus");
            return (Criteria) this;
        }

        public Criteria andBaseStatusBetween(Byte value1, Byte value2) {
            addCriterion("baseStatus between", value1, value2, "baseStatus");
            return (Criteria) this;
        }

        public Criteria andBaseStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("baseStatus not between", value1, value2, "baseStatus");
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