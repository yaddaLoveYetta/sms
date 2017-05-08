package com.kingdee.eas.hrp.sms.model;

public class FormEntries extends FormEntriesKey {
    private String tableName;

    private String foreignKey;

    private String primaryKey;

    private String bosType;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey == null ? null : foreignKey.trim();
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