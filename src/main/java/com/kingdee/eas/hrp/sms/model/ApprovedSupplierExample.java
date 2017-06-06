package com.kingdee.eas.hrp.sms.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApprovedSupplierExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ApprovedSupplierExample() {
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

        public Criteria andMaterialItemIsNull() {
            addCriterion("materialItem is null");
            return (Criteria) this;
        }

        public Criteria andMaterialItemIsNotNull() {
            addCriterion("materialItem is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialItemEqualTo(String value) {
            addCriterion("materialItem =", value, "materialItem");
            return (Criteria) this;
        }

        public Criteria andMaterialItemNotEqualTo(String value) {
            addCriterion("materialItem <>", value, "materialItem");
            return (Criteria) this;
        }

        public Criteria andMaterialItemGreaterThan(String value) {
            addCriterion("materialItem >", value, "materialItem");
            return (Criteria) this;
        }

        public Criteria andMaterialItemGreaterThanOrEqualTo(String value) {
            addCriterion("materialItem >=", value, "materialItem");
            return (Criteria) this;
        }

        public Criteria andMaterialItemLessThan(String value) {
            addCriterion("materialItem <", value, "materialItem");
            return (Criteria) this;
        }

        public Criteria andMaterialItemLessThanOrEqualTo(String value) {
            addCriterion("materialItem <=", value, "materialItem");
            return (Criteria) this;
        }

        public Criteria andMaterialItemLike(String value) {
            addCriterion("materialItem like", value, "materialItem");
            return (Criteria) this;
        }

        public Criteria andMaterialItemNotLike(String value) {
            addCriterion("materialItem not like", value, "materialItem");
            return (Criteria) this;
        }

        public Criteria andMaterialItemIn(List<String> values) {
            addCriterion("materialItem in", values, "materialItem");
            return (Criteria) this;
        }

        public Criteria andMaterialItemNotIn(List<String> values) {
            addCriterion("materialItem not in", values, "materialItem");
            return (Criteria) this;
        }

        public Criteria andMaterialItemBetween(String value1, String value2) {
            addCriterion("materialItem between", value1, value2, "materialItem");
            return (Criteria) this;
        }

        public Criteria andMaterialItemNotBetween(String value1, String value2) {
            addCriterion("materialItem not between", value1, value2, "materialItem");
            return (Criteria) this;
        }

        public Criteria andIsStoppedIsNull() {
            addCriterion("isStopped is null");
            return (Criteria) this;
        }

        public Criteria andIsStoppedIsNotNull() {
            addCriterion("isStopped is not null");
            return (Criteria) this;
        }

        public Criteria andIsStoppedEqualTo(Byte value) {
            addCriterion("isStopped =", value, "isStopped");
            return (Criteria) this;
        }

        public Criteria andIsStoppedNotEqualTo(Byte value) {
            addCriterion("isStopped <>", value, "isStopped");
            return (Criteria) this;
        }

        public Criteria andIsStoppedGreaterThan(Byte value) {
            addCriterion("isStopped >", value, "isStopped");
            return (Criteria) this;
        }

        public Criteria andIsStoppedGreaterThanOrEqualTo(Byte value) {
            addCriterion("isStopped >=", value, "isStopped");
            return (Criteria) this;
        }

        public Criteria andIsStoppedLessThan(Byte value) {
            addCriterion("isStopped <", value, "isStopped");
            return (Criteria) this;
        }

        public Criteria andIsStoppedLessThanOrEqualTo(Byte value) {
            addCriterion("isStopped <=", value, "isStopped");
            return (Criteria) this;
        }

        public Criteria andIsStoppedIn(List<Byte> values) {
            addCriterion("isStopped in", values, "isStopped");
            return (Criteria) this;
        }

        public Criteria andIsStoppedNotIn(List<Byte> values) {
            addCriterion("isStopped not in", values, "isStopped");
            return (Criteria) this;
        }

        public Criteria andIsStoppedBetween(Byte value1, Byte value2) {
            addCriterion("isStopped between", value1, value2, "isStopped");
            return (Criteria) this;
        }

        public Criteria andIsStoppedNotBetween(Byte value1, Byte value2) {
            addCriterion("isStopped not between", value1, value2, "isStopped");
            return (Criteria) this;
        }

        public Criteria andMeasureUnitIsNull() {
            addCriterion("measureUnit is null");
            return (Criteria) this;
        }

        public Criteria andMeasureUnitIsNotNull() {
            addCriterion("measureUnit is not null");
            return (Criteria) this;
        }

        public Criteria andMeasureUnitEqualTo(String value) {
            addCriterion("measureUnit =", value, "measureUnit");
            return (Criteria) this;
        }

        public Criteria andMeasureUnitNotEqualTo(String value) {
            addCriterion("measureUnit <>", value, "measureUnit");
            return (Criteria) this;
        }

        public Criteria andMeasureUnitGreaterThan(String value) {
            addCriterion("measureUnit >", value, "measureUnit");
            return (Criteria) this;
        }

        public Criteria andMeasureUnitGreaterThanOrEqualTo(String value) {
            addCriterion("measureUnit >=", value, "measureUnit");
            return (Criteria) this;
        }

        public Criteria andMeasureUnitLessThan(String value) {
            addCriterion("measureUnit <", value, "measureUnit");
            return (Criteria) this;
        }

        public Criteria andMeasureUnitLessThanOrEqualTo(String value) {
            addCriterion("measureUnit <=", value, "measureUnit");
            return (Criteria) this;
        }

        public Criteria andMeasureUnitLike(String value) {
            addCriterion("measureUnit like", value, "measureUnit");
            return (Criteria) this;
        }

        public Criteria andMeasureUnitNotLike(String value) {
            addCriterion("measureUnit not like", value, "measureUnit");
            return (Criteria) this;
        }

        public Criteria andMeasureUnitIn(List<String> values) {
            addCriterion("measureUnit in", values, "measureUnit");
            return (Criteria) this;
        }

        public Criteria andMeasureUnitNotIn(List<String> values) {
            addCriterion("measureUnit not in", values, "measureUnit");
            return (Criteria) this;
        }

        public Criteria andMeasureUnitBetween(String value1, String value2) {
            addCriterion("measureUnit between", value1, value2, "measureUnit");
            return (Criteria) this;
        }

        public Criteria andMeasureUnitNotBetween(String value1, String value2) {
            addCriterion("measureUnit not between", value1, value2, "measureUnit");
            return (Criteria) this;
        }

        public Criteria andSupplierRateIsNull() {
            addCriterion("supplierRate is null");
            return (Criteria) this;
        }

        public Criteria andSupplierRateIsNotNull() {
            addCriterion("supplierRate is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierRateEqualTo(BigDecimal value) {
            addCriterion("supplierRate =", value, "supplierRate");
            return (Criteria) this;
        }

        public Criteria andSupplierRateNotEqualTo(BigDecimal value) {
            addCriterion("supplierRate <>", value, "supplierRate");
            return (Criteria) this;
        }

        public Criteria andSupplierRateGreaterThan(BigDecimal value) {
            addCriterion("supplierRate >", value, "supplierRate");
            return (Criteria) this;
        }

        public Criteria andSupplierRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("supplierRate >=", value, "supplierRate");
            return (Criteria) this;
        }

        public Criteria andSupplierRateLessThan(BigDecimal value) {
            addCriterion("supplierRate <", value, "supplierRate");
            return (Criteria) this;
        }

        public Criteria andSupplierRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("supplierRate <=", value, "supplierRate");
            return (Criteria) this;
        }

        public Criteria andSupplierRateIn(List<BigDecimal> values) {
            addCriterion("supplierRate in", values, "supplierRate");
            return (Criteria) this;
        }

        public Criteria andSupplierRateNotIn(List<BigDecimal> values) {
            addCriterion("supplierRate not in", values, "supplierRate");
            return (Criteria) this;
        }

        public Criteria andSupplierRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("supplierRate between", value1, value2, "supplierRate");
            return (Criteria) this;
        }

        public Criteria andSupplierRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("supplierRate not between", value1, value2, "supplierRate");
            return (Criteria) this;
        }

        public Criteria andTaxPriceIsNull() {
            addCriterion("taxPrice is null");
            return (Criteria) this;
        }

        public Criteria andTaxPriceIsNotNull() {
            addCriterion("taxPrice is not null");
            return (Criteria) this;
        }

        public Criteria andTaxPriceEqualTo(BigDecimal value) {
            addCriterion("taxPrice =", value, "taxPrice");
            return (Criteria) this;
        }

        public Criteria andTaxPriceNotEqualTo(BigDecimal value) {
            addCriterion("taxPrice <>", value, "taxPrice");
            return (Criteria) this;
        }

        public Criteria andTaxPriceGreaterThan(BigDecimal value) {
            addCriterion("taxPrice >", value, "taxPrice");
            return (Criteria) this;
        }

        public Criteria andTaxPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("taxPrice >=", value, "taxPrice");
            return (Criteria) this;
        }

        public Criteria andTaxPriceLessThan(BigDecimal value) {
            addCriterion("taxPrice <", value, "taxPrice");
            return (Criteria) this;
        }

        public Criteria andTaxPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("taxPrice <=", value, "taxPrice");
            return (Criteria) this;
        }

        public Criteria andTaxPriceIn(List<BigDecimal> values) {
            addCriterion("taxPrice in", values, "taxPrice");
            return (Criteria) this;
        }

        public Criteria andTaxPriceNotIn(List<BigDecimal> values) {
            addCriterion("taxPrice not in", values, "taxPrice");
            return (Criteria) this;
        }

        public Criteria andTaxPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("taxPrice between", value1, value2, "taxPrice");
            return (Criteria) this;
        }

        public Criteria andTaxPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("taxPrice not between", value1, value2, "taxPrice");
            return (Criteria) this;
        }

        public Criteria andEffectualDateIsNull() {
            addCriterion("effectualDate is null");
            return (Criteria) this;
        }

        public Criteria andEffectualDateIsNotNull() {
            addCriterion("effectualDate is not null");
            return (Criteria) this;
        }

        public Criteria andEffectualDateEqualTo(Date value) {
            addCriterion("effectualDate =", value, "effectualDate");
            return (Criteria) this;
        }

        public Criteria andEffectualDateNotEqualTo(Date value) {
            addCriterion("effectualDate <>", value, "effectualDate");
            return (Criteria) this;
        }

        public Criteria andEffectualDateGreaterThan(Date value) {
            addCriterion("effectualDate >", value, "effectualDate");
            return (Criteria) this;
        }

        public Criteria andEffectualDateGreaterThanOrEqualTo(Date value) {
            addCriterion("effectualDate >=", value, "effectualDate");
            return (Criteria) this;
        }

        public Criteria andEffectualDateLessThan(Date value) {
            addCriterion("effectualDate <", value, "effectualDate");
            return (Criteria) this;
        }

        public Criteria andEffectualDateLessThanOrEqualTo(Date value) {
            addCriterion("effectualDate <=", value, "effectualDate");
            return (Criteria) this;
        }

        public Criteria andEffectualDateIn(List<Date> values) {
            addCriterion("effectualDate in", values, "effectualDate");
            return (Criteria) this;
        }

        public Criteria andEffectualDateNotIn(List<Date> values) {
            addCriterion("effectualDate not in", values, "effectualDate");
            return (Criteria) this;
        }

        public Criteria andEffectualDateBetween(Date value1, Date value2) {
            addCriterion("effectualDate between", value1, value2, "effectualDate");
            return (Criteria) this;
        }

        public Criteria andEffectualDateNotBetween(Date value1, Date value2) {
            addCriterion("effectualDate not between", value1, value2, "effectualDate");
            return (Criteria) this;
        }

        public Criteria andUneffectualDateIsNull() {
            addCriterion("uneffectualDate is null");
            return (Criteria) this;
        }

        public Criteria andUneffectualDateIsNotNull() {
            addCriterion("uneffectualDate is not null");
            return (Criteria) this;
        }

        public Criteria andUneffectualDateEqualTo(Date value) {
            addCriterion("uneffectualDate =", value, "uneffectualDate");
            return (Criteria) this;
        }

        public Criteria andUneffectualDateNotEqualTo(Date value) {
            addCriterion("uneffectualDate <>", value, "uneffectualDate");
            return (Criteria) this;
        }

        public Criteria andUneffectualDateGreaterThan(Date value) {
            addCriterion("uneffectualDate >", value, "uneffectualDate");
            return (Criteria) this;
        }

        public Criteria andUneffectualDateGreaterThanOrEqualTo(Date value) {
            addCriterion("uneffectualDate >=", value, "uneffectualDate");
            return (Criteria) this;
        }

        public Criteria andUneffectualDateLessThan(Date value) {
            addCriterion("uneffectualDate <", value, "uneffectualDate");
            return (Criteria) this;
        }

        public Criteria andUneffectualDateLessThanOrEqualTo(Date value) {
            addCriterion("uneffectualDate <=", value, "uneffectualDate");
            return (Criteria) this;
        }

        public Criteria andUneffectualDateIn(List<Date> values) {
            addCriterion("uneffectualDate in", values, "uneffectualDate");
            return (Criteria) this;
        }

        public Criteria andUneffectualDateNotIn(List<Date> values) {
            addCriterion("uneffectualDate not in", values, "uneffectualDate");
            return (Criteria) this;
        }

        public Criteria andUneffectualDateBetween(Date value1, Date value2) {
            addCriterion("uneffectualDate between", value1, value2, "uneffectualDate");
            return (Criteria) this;
        }

        public Criteria andUneffectualDateNotBetween(Date value1, Date value2) {
            addCriterion("uneffectualDate not between", value1, value2, "uneffectualDate");
            return (Criteria) this;
        }

        public Criteria andSyncStatusIsNull() {
            addCriterion("syncStatus is null");
            return (Criteria) this;
        }

        public Criteria andSyncStatusIsNotNull() {
            addCriterion("syncStatus is not null");
            return (Criteria) this;
        }

        public Criteria andSyncStatusEqualTo(Byte value) {
            addCriterion("syncStatus =", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusNotEqualTo(Byte value) {
            addCriterion("syncStatus <>", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusGreaterThan(Byte value) {
            addCriterion("syncStatus >", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("syncStatus >=", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusLessThan(Byte value) {
            addCriterion("syncStatus <", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusLessThanOrEqualTo(Byte value) {
            addCriterion("syncStatus <=", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusIn(List<Byte> values) {
            addCriterion("syncStatus in", values, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusNotIn(List<Byte> values) {
            addCriterion("syncStatus not in", values, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusBetween(Byte value1, Byte value2) {
            addCriterion("syncStatus between", value1, value2, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("syncStatus not between", value1, value2, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andReviewIsNull() {
            addCriterion("review is null");
            return (Criteria) this;
        }

        public Criteria andReviewIsNotNull() {
            addCriterion("review is not null");
            return (Criteria) this;
        }

        public Criteria andReviewEqualTo(Byte value) {
            addCriterion("review =", value, "review");
            return (Criteria) this;
        }

        public Criteria andReviewNotEqualTo(Byte value) {
            addCriterion("review <>", value, "review");
            return (Criteria) this;
        }

        public Criteria andReviewGreaterThan(Byte value) {
            addCriterion("review >", value, "review");
            return (Criteria) this;
        }

        public Criteria andReviewGreaterThanOrEqualTo(Byte value) {
            addCriterion("review >=", value, "review");
            return (Criteria) this;
        }

        public Criteria andReviewLessThan(Byte value) {
            addCriterion("review <", value, "review");
            return (Criteria) this;
        }

        public Criteria andReviewLessThanOrEqualTo(Byte value) {
            addCriterion("review <=", value, "review");
            return (Criteria) this;
        }

        public Criteria andReviewIn(List<Byte> values) {
            addCriterion("review in", values, "review");
            return (Criteria) this;
        }

        public Criteria andReviewNotIn(List<Byte> values) {
            addCriterion("review not in", values, "review");
            return (Criteria) this;
        }

        public Criteria andReviewBetween(Byte value1, Byte value2) {
            addCriterion("review between", value1, value2, "review");
            return (Criteria) this;
        }

        public Criteria andReviewNotBetween(Byte value1, Byte value2) {
            addCriterion("review not between", value1, value2, "review");
            return (Criteria) this;
        }

        public Criteria andPurMeasureUnitIsNull() {
            addCriterion("purMeasureUnit is null");
            return (Criteria) this;
        }

        public Criteria andPurMeasureUnitIsNotNull() {
            addCriterion("purMeasureUnit is not null");
            return (Criteria) this;
        }

        public Criteria andPurMeasureUnitEqualTo(String value) {
            addCriterion("purMeasureUnit =", value, "purMeasureUnit");
            return (Criteria) this;
        }

        public Criteria andPurMeasureUnitNotEqualTo(String value) {
            addCriterion("purMeasureUnit <>", value, "purMeasureUnit");
            return (Criteria) this;
        }

        public Criteria andPurMeasureUnitGreaterThan(String value) {
            addCriterion("purMeasureUnit >", value, "purMeasureUnit");
            return (Criteria) this;
        }

        public Criteria andPurMeasureUnitGreaterThanOrEqualTo(String value) {
            addCriterion("purMeasureUnit >=", value, "purMeasureUnit");
            return (Criteria) this;
        }

        public Criteria andPurMeasureUnitLessThan(String value) {
            addCriterion("purMeasureUnit <", value, "purMeasureUnit");
            return (Criteria) this;
        }

        public Criteria andPurMeasureUnitLessThanOrEqualTo(String value) {
            addCriterion("purMeasureUnit <=", value, "purMeasureUnit");
            return (Criteria) this;
        }

        public Criteria andPurMeasureUnitLike(String value) {
            addCriterion("purMeasureUnit like", value, "purMeasureUnit");
            return (Criteria) this;
        }

        public Criteria andPurMeasureUnitNotLike(String value) {
            addCriterion("purMeasureUnit not like", value, "purMeasureUnit");
            return (Criteria) this;
        }

        public Criteria andPurMeasureUnitIn(List<String> values) {
            addCriterion("purMeasureUnit in", values, "purMeasureUnit");
            return (Criteria) this;
        }

        public Criteria andPurMeasureUnitNotIn(List<String> values) {
            addCriterion("purMeasureUnit not in", values, "purMeasureUnit");
            return (Criteria) this;
        }

        public Criteria andPurMeasureUnitBetween(String value1, String value2) {
            addCriterion("purMeasureUnit between", value1, value2, "purMeasureUnit");
            return (Criteria) this;
        }

        public Criteria andPurMeasureUnitNotBetween(String value1, String value2) {
            addCriterion("purMeasureUnit not between", value1, value2, "purMeasureUnit");
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