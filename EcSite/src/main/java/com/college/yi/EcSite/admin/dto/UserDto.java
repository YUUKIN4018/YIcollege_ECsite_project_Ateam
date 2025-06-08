package com.college.yi.EcSite.admin.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.college.yi.EcSite.entity.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer userId;
    private String email;
    private String lastName;
    private String firstName;
    private String lastNameKana;
    private String firstNameKana;
    private LocalDate birthDate;
    private Short gender;
    private Short status;
    private Short role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;

    
    public UserRole getUserRole() {
        return UserRole.fromCode(this.role);
    }

    public boolean isAdmin() {
        return getUserRole() == UserRole.ADMIN;
    }

    public boolean isUser() {
        return getUserRole() == UserRole.USER;
    }
}
