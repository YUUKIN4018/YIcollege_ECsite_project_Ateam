package com.college.yi.ecsite.admin.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class AdminLoginForm {
    
   @Size(min = 1, max = 50)
   @Email(message = "メールアドレスの形式が正しくありません")
   @NotBlank(message = "メールアドレスは必須です")
    private String email;   
    
   @Size(min = 1, max = 50)
   @NotBlank(message = "パスワードは必須です")
    private String  password;

}
