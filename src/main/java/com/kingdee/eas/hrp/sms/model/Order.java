package com.kingdee.eas.hrp.sms.model;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String supplier_id;

    private String supplier_name;

    private String line_numbers;

    private String purchase_order_no;

    private Date order_time;

    private String buyer_id;

    private String material_code;

    private String material_name;

    private String specifications;

    private String basic_unit_measurement;

    private BigDecimal price;

    private Integer numbers;

    private Date delivery_time;

    private Date cutasingle_time;

    private Date confirm_delivery_time;

    private Integer confirm_delivery_numbers;

    private Integer purchasing_mode;

    private Integer tax;

    private Integer confirm_order;

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id == null ? null : supplier_id.trim();
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name == null ? null : supplier_name.trim();
    }

    public String getLine_numbers() {
        return line_numbers;
    }

    public void setLine_numbers(String line_numbers) {
        this.line_numbers = line_numbers == null ? null : line_numbers.trim();
    }

    public String getPurchase_order_no() {
        return purchase_order_no;
    }

    public void setPurchase_order_no(String purchase_order_no) {
        this.purchase_order_no = purchase_order_no == null ? null : purchase_order_no.trim();
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id == null ? null : buyer_id.trim();
    }

    public String getMaterial_code() {
        return material_code;
    }

    public void setMaterial_code(String material_code) {
        this.material_code = material_code == null ? null : material_code.trim();
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name == null ? null : material_name.trim();
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications == null ? null : specifications.trim();
    }

    public String getBasic_unit_measurement() {
        return basic_unit_measurement;
    }

    public void setBasic_unit_measurement(String basic_unit_measurement) {
        this.basic_unit_measurement = basic_unit_measurement == null ? null : basic_unit_measurement.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public Date getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(Date delivery_time) {
        this.delivery_time = delivery_time;
    }

    public Date getCutasingle_time() {
        return cutasingle_time;
    }

    public void setCutasingle_time(Date cutasingle_time) {
        this.cutasingle_time = cutasingle_time;
    }

    public Date getConfirm_delivery_time() {
        return confirm_delivery_time;
    }

    public void setConfirm_delivery_time(Date confirm_delivery_time) {
        this.confirm_delivery_time = confirm_delivery_time;
    }

    public Integer getConfirm_delivery_numbers() {
        return confirm_delivery_numbers;
    }

    public void setConfirm_delivery_numbers(Integer confirm_delivery_numbers) {
        this.confirm_delivery_numbers = confirm_delivery_numbers;
    }

    public Integer getPurchasing_mode() {
        return purchasing_mode;
    }

    public void setPurchasing_mode(Integer purchasing_mode) {
        this.purchasing_mode = purchasing_mode;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Integer getConfirm_order() {
        return confirm_order;
    }

    public void setConfirm_order(Integer confirm_order) {
        this.confirm_order = confirm_order;
    }
}