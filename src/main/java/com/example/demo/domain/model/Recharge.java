package com.example.demo.domain.model;

public class Recharge {

    private Integer value;

    private String supplierId;

    private String cellPhone;

    private String supplierName;

    public Recharge(Integer value, String supplierId, String cellPhone, String supplierName) {
        this.value = value;
        this.supplierId = supplierId;
        this.cellPhone = cellPhone;
        this.supplierName = supplierName;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }



}
