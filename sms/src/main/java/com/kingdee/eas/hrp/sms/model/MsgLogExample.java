package com.kingdee.eas.hrp.sms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MsgLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MsgLogExample() {
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

        public Criteria andSeqidIsNull() {
            addCriterion("seqid is null");
            return (Criteria) this;
        }

        public Criteria andSeqidIsNotNull() {
            addCriterion("seqid is not null");
            return (Criteria) this;
        }

        public Criteria andSeqidEqualTo(String value) {
            addCriterion("seqid =", value, "seqid");
            return (Criteria) this;
        }

        public Criteria andSeqidNotEqualTo(String value) {
            addCriterion("seqid <>", value, "seqid");
            return (Criteria) this;
        }

        public Criteria andSeqidGreaterThan(String value) {
            addCriterion("seqid >", value, "seqid");
            return (Criteria) this;
        }

        public Criteria andSeqidGreaterThanOrEqualTo(String value) {
            addCriterion("seqid >=", value, "seqid");
            return (Criteria) this;
        }

        public Criteria andSeqidLessThan(String value) {
            addCriterion("seqid <", value, "seqid");
            return (Criteria) this;
        }

        public Criteria andSeqidLessThanOrEqualTo(String value) {
            addCriterion("seqid <=", value, "seqid");
            return (Criteria) this;
        }

        public Criteria andSeqidLike(String value) {
            addCriterion("seqid like", value, "seqid");
            return (Criteria) this;
        }

        public Criteria andSeqidNotLike(String value) {
            addCriterion("seqid not like", value, "seqid");
            return (Criteria) this;
        }

        public Criteria andSeqidIn(List<String> values) {
            addCriterion("seqid in", values, "seqid");
            return (Criteria) this;
        }

        public Criteria andSeqidNotIn(List<String> values) {
            addCriterion("seqid not in", values, "seqid");
            return (Criteria) this;
        }

        public Criteria andSeqidBetween(String value1, String value2) {
            addCriterion("seqid between", value1, value2, "seqid");
            return (Criteria) this;
        }

        public Criteria andSeqidNotBetween(String value1, String value2) {
            addCriterion("seqid not between", value1, value2, "seqid");
            return (Criteria) this;
        }

        public Criteria andMobilesIsNull() {
            addCriterion("mobiles is null");
            return (Criteria) this;
        }

        public Criteria andMobilesIsNotNull() {
            addCriterion("mobiles is not null");
            return (Criteria) this;
        }

        public Criteria andMobilesEqualTo(String value) {
            addCriterion("mobiles =", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesNotEqualTo(String value) {
            addCriterion("mobiles <>", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesGreaterThan(String value) {
            addCriterion("mobiles >", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesGreaterThanOrEqualTo(String value) {
            addCriterion("mobiles >=", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesLessThan(String value) {
            addCriterion("mobiles <", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesLessThanOrEqualTo(String value) {
            addCriterion("mobiles <=", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesLike(String value) {
            addCriterion("mobiles like", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesNotLike(String value) {
            addCriterion("mobiles not like", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesIn(List<String> values) {
            addCriterion("mobiles in", values, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesNotIn(List<String> values) {
            addCriterion("mobiles not in", values, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesBetween(String value1, String value2) {
            addCriterion("mobiles between", value1, value2, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesNotBetween(String value1, String value2) {
            addCriterion("mobiles not between", value1, value2, "mobiles");
            return (Criteria) this;
        }

        public Criteria andSmsContentIsNull() {
            addCriterion("smsContent is null");
            return (Criteria) this;
        }

        public Criteria andSmsContentIsNotNull() {
            addCriterion("smsContent is not null");
            return (Criteria) this;
        }

        public Criteria andSmsContentEqualTo(String value) {
            addCriterion("smsContent =", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentNotEqualTo(String value) {
            addCriterion("smsContent <>", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentGreaterThan(String value) {
            addCriterion("smsContent >", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentGreaterThanOrEqualTo(String value) {
            addCriterion("smsContent >=", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentLessThan(String value) {
            addCriterion("smsContent <", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentLessThanOrEqualTo(String value) {
            addCriterion("smsContent <=", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentLike(String value) {
            addCriterion("smsContent like", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentNotLike(String value) {
            addCriterion("smsContent not like", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentIn(List<String> values) {
            addCriterion("smsContent in", values, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentNotIn(List<String> values) {
            addCriterion("smsContent not in", values, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentBetween(String value1, String value2) {
            addCriterion("smsContent between", value1, value2, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentNotBetween(String value1, String value2) {
            addCriterion("smsContent not between", value1, value2, "smsContent");
            return (Criteria) this;
        }

        public Criteria andRestrIsNull() {
            addCriterion("restr is null");
            return (Criteria) this;
        }

        public Criteria andRestrIsNotNull() {
            addCriterion("restr is not null");
            return (Criteria) this;
        }

        public Criteria andRestrEqualTo(String value) {
            addCriterion("restr =", value, "restr");
            return (Criteria) this;
        }

        public Criteria andRestrNotEqualTo(String value) {
            addCriterion("restr <>", value, "restr");
            return (Criteria) this;
        }

        public Criteria andRestrGreaterThan(String value) {
            addCriterion("restr >", value, "restr");
            return (Criteria) this;
        }

        public Criteria andRestrGreaterThanOrEqualTo(String value) {
            addCriterion("restr >=", value, "restr");
            return (Criteria) this;
        }

        public Criteria andRestrLessThan(String value) {
            addCriterion("restr <", value, "restr");
            return (Criteria) this;
        }

        public Criteria andRestrLessThanOrEqualTo(String value) {
            addCriterion("restr <=", value, "restr");
            return (Criteria) this;
        }

        public Criteria andRestrLike(String value) {
            addCriterion("restr like", value, "restr");
            return (Criteria) this;
        }

        public Criteria andRestrNotLike(String value) {
            addCriterion("restr not like", value, "restr");
            return (Criteria) this;
        }

        public Criteria andRestrIn(List<String> values) {
            addCriterion("restr in", values, "restr");
            return (Criteria) this;
        }

        public Criteria andRestrNotIn(List<String> values) {
            addCriterion("restr not in", values, "restr");
            return (Criteria) this;
        }

        public Criteria andRestrBetween(String value1, String value2) {
            addCriterion("restr between", value1, value2, "restr");
            return (Criteria) this;
        }

        public Criteria andRestrNotBetween(String value1, String value2) {
            addCriterion("restr not between", value1, value2, "restr");
            return (Criteria) this;
        }

        public Criteria andSendtimeIsNull() {
            addCriterion("sendtime is null");
            return (Criteria) this;
        }

        public Criteria andSendtimeIsNotNull() {
            addCriterion("sendtime is not null");
            return (Criteria) this;
        }

        public Criteria andSendtimeEqualTo(Date value) {
            addCriterion("sendtime =", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotEqualTo(Date value) {
            addCriterion("sendtime <>", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeGreaterThan(Date value) {
            addCriterion("sendtime >", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sendtime >=", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeLessThan(Date value) {
            addCriterion("sendtime <", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeLessThanOrEqualTo(Date value) {
            addCriterion("sendtime <=", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeIn(List<Date> values) {
            addCriterion("sendtime in", values, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotIn(List<Date> values) {
            addCriterion("sendtime not in", values, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeBetween(Date value1, Date value2) {
            addCriterion("sendtime between", value1, value2, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotBetween(Date value1, Date value2) {
            addCriterion("sendtime not between", value1, value2, "sendtime");
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