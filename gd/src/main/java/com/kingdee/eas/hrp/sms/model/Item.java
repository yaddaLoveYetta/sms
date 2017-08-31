package com.kingdee.eas.hrp.sms.model;

public class Item {
    private String id;

    private String name;

    private String number;

    private String specification;

    private Byte highConsumable;

    private Byte isLotNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    public Byte getHighConsumable() {
        return highConsumable;
    }

    public void setHighConsumable(Byte highConsumable) {
        this.highConsumable = highConsumable;
    }

    public Byte getIsLotNumber() {
        return isLotNumber;
    }

    public void setIsLotNumber(Byte isLotNumber) {
        this.isLotNumber = isLotNumber;
    }
}