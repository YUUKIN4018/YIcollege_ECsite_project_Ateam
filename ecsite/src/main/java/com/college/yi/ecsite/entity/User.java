package com.college.yi.ecsite.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class User {
    private Integer userId;
    private String email;
    private String passwordHash;
    private String lastName;
    private String firstName;
    private String lastNameKana;
    private String firstNameKana;
    private Integer gender;         // 1:男性,2:女性,3:その他
    private Integer accountStatus;  // 0:無効,1:有効
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}