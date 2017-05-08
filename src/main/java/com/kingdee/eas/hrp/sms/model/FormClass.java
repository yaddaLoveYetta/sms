package com.kingdee.eas.hrp.sms.model;

public class FormClass {
    private Integer classId;

    private String name;

    private String tableName;

    private String primaryKey;

    private String bosType;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey == null ? null : primaryKey.trim();
    }

    public String getBosType() {
        return bosType;
    }

    public void setBosType(String bosType) {
        this.bosType = bosType == null ? null : bosType.trim();
    }
}