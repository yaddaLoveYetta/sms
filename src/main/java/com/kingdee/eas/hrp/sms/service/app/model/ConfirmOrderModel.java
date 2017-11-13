package com.kingdee.eas.hrp.sms.service.app.model;

import java.util.Date;

/**
 * @author yadda
 */
public class ConfirmOrderModel {

    private String orderId;
    private String entryId;

    private Integer confirmQty;
    private Date confirmDate;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public Integer getConfirmQty() {
        return confirmQty;
    }

    public void setConfirmQty(Integer confirmQty) {
        this.confirmQty = confirmQty;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    @Override
    public String toString() {
        return "ConfirmOrderModel{" +
                "orderId='" + orderId + '\'' +
                ", entryId='" + entryId + '\'' +
                ", confirmQty=" + confirmQty +
                ", confirmDate=" + confirmDate +
                '}';
    }
}
