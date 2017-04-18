package com.kingdee.eas.hrp.sms.model;

public class Settlement {
    private Integer settlementId;

    private String settlementName;

    public Integer getSettlementId() {
        return settlementId;
    }

    public void setSettlementId(Integer settlementId) {
        this.settlementId = settlementId;
    }

    public String getSettlementName() {
        return settlementName;
    }

    public void setSettlementName(String settlementName) {
        this.settlementName = settlementName == null ? null : settlementName.trim();
    }
}