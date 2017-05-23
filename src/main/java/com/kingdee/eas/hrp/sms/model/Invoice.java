package com.kingdee.eas.hrp.sms.model;

import java.util.Date;

public class Invoice {
    private Integer id;

    private String number;

    private Date date;

    private String supplier;

    private String logistics;

    private String logisticsNo;

    private Byte saleProxy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics == null ? null : logistics.trim();
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo == null ? null : logisticsNo.trim();
    }

    public Byte getSaleProxy() {
        return saleProxy;
    }

    public void setSaleProxy(Byte saleProxy) {
        this.saleProxy = saleProxy;
    }
}