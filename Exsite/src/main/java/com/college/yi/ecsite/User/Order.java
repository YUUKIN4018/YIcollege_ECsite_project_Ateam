package com.college.yi.ecsite.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {

    private Long id;                      // 注文ID（主キー）
    private Long userId;                  // ユーザーID
    private LocalDateTime orderDate;      // 注文日時
    private BigDecimal totalAmount;       // 合計金額
    private String name;                  // 名前（注文者）
    private String zipCode;               // 郵便番号
    private String address;               // 住所
    private String phone;                 // 電話番号
    private String note;                  // 備考
    private String specifyDatetimeValue;  // 配達希望日時（任意）

    public Order() {}

    // Getter / Setter 一覧
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSpecifyDatetimeValue() {
        return specifyDatetimeValue;
    }

    public void setSpecifyDatetimeValue(String specifyDatetimeValue) {
        this.specifyDatetimeValue = specifyDatetimeValue;
    }
}
