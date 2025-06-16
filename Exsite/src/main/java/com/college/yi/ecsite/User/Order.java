package com.college.yi.ecsite.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
}
