package com.college.yi.ecsite.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String email;
    private String password;
    private String name;
    private String role; // 例: "USER", "ADMIN"
}
