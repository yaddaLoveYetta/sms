package com.kingdee.eas.hrp.sms.model;

import java.math.BigDecimal;

public class FormFields {
    private Integer classId;

    private Integer page;

    private String name;

    private String sqlColumnName;

    private String key;

    private Integer dataType;

    private Integer ctrlType;

    private Integer ctlIndex;

    private Integer index;

    private Integer display;

    private Integer showWidth;

    private Integer lookUpType;

    private Integer lookUpClassID;

    private String srcTable;

    private String srcTableAlisAs;

    private String srcField;

    private String disPlayField;

    private String disPlayNum;

    private String joinType;

    private String filter;

    private String defaultValue;

    private BigDecimal maxValue;

    private BigDecimal minValue;

    private Integer mustInput;

    private Integer length;

    private Integer lock;

    private Integer isCondition;

    private Integer isCount;

    private Byte needSave;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSqlColumnName() {
        return sqlColumnName;
    }

    public void setSqlColumnName(String sqlColumnName) {
        this.sqlColumnName = sqlColumnName == null ? null : sqlColumnName.trim();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getCtrlType() {
        return ctrlType;
    }

    public void setCtrlType(Integer ctrlType) {
        this.ctrlType = ctrlType;
    }

    public Integer getCtlIndex() {
        return ctlIndex;
    }

    public void setCtlIndex(Integer ctlIndex) {
        this.ctlIndex = ctlIndex;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public Integer getShowWidth() {
        return showWidth;
    }

    public void setShowWidth(Integer showWidth) {
        this.showWidth = showWidth;
    }

    public Integer getLookUpType() {
        return lookUpType;
    }

    public void setLookUpType(Integer lookUpType) {
        this.lookUpType = lookUpType;
    }

    public Integer getLookUpClassID() {
        return lookUpClassID;
    }

    public void setLookUpClassID(Integer lookUpClassID) {
        this.lookUpClassID = lookUpClassID;
    }

    public String getSrcTable() {
        return srcTable;
    }

    public void setSrcTable(String srcTable) {
        this.srcTable = srcTable == null ? null : srcTable.trim();
    }

    public String getSrcTableAlisAs() {
        return srcTableAlisAs;
    }

    public void setSrcTableAlisAs(String srcTableAlisAs) {
        this.srcTableAlisAs = srcTableAlisAs == null ? null : srcTableAlisAs.trim();
    }

    public String getSrcField() {
        return srcField;
    }

    public void setSrcField(String srcField) {
        this.srcField = srcField == null ? null : srcField.trim();
    }

    public String getDisPlayField() {
        return disPlayField;
    }

    public void setDisPlayField(String disPlayField) {
        this.disPlayField = disPlayField == null ? null : disPlayField.trim();
    }

    public String getDisPlayNum() {
        return disPlayNum;
    }

    public void setDisPlayNum(String disPlayNum) {
        this.disPlayNum = disPlayNum == null ? null : disPlayNum.trim();
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType == null ? null : joinType.trim();
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter == null ? null : filter.trim();
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue == null ? null : defaultValue.trim();
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    public BigDecimal getMinValue() {
        return minValue;
    }

    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    public Integer getMustInput() {
        return mustInput;
    }

    public void setMustInput(Integer mustInput) {
        this.mustInput = mustInput;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getLock() {
        return lock;
    }

    public void setLock(Integer lock) {
        this.lock = lock;
    }

    public Integer getIsCondition() {
        return isCondition;
    }

    public void setIsCondition(Integer isCondition) {
        this.isCondition = isCondition;
    }

    public Integer getIsCount() {
        return isCount;
    }

    public void setIsCount(Integer isCount) {
        this.isCount = isCount;
    }

    public Byte getNeedSave() {
        return needSave;
    }

    public void setNeedSave(Byte needSave) {
        this.needSave = needSave;
    }
}