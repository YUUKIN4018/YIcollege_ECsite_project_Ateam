package com.college.yi.ecsite.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class User {
    
    private Long user_id;
    private String email;
    private String password_hash;
    private String last_name;
    private String first_name;
    private String last_name_kana;
    private String first_name_kana;
    private String bitrth_date;
    private Integer gender;
    private Integer status;
    private String role;
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate last_login_at;
       
}
