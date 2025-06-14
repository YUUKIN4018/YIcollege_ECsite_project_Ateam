package com.college.yi.ecsite.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class User {
    private Long userId;            // ユーザーID（BIGSERIAL）
    private String email;           // メールアドレス（UNIQUE）
    private String passwordHash;    // パスワード（ハッシュ化）

    private String lastName;        // 名前（姓）
    private String firstName;       // 名前（名）

    private String lastNameKana;    // 名前（姓）フリガナ
    private String firstNameKana;   // 名前（名）フリガナ

    private LocalDate birthDate;    // 生年月日
    private Short gender;           // 性別（1:男性, 2:女性, 3:その他）

    private Short status;           // ステータス（アカウント状態）
    private Short role;             // 管理権限

    private LocalDateTime createdAt;     // 登録日時
    private LocalDateTime updatedAt;     // 更新日時
    private LocalDateTime lastLoginAt;   // 最終ログイン日時
}
