package com.college.yi.ecsite.front.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterForm {
	@NotBlank(message = "未入力があります。")
	private String lastName;

	@NotBlank(message = "未入力があります。")
	private String firstName;

	@NotBlank(message = "未入力があります。")
	private String lastNameKana;

	@NotBlank(message = "未入力があります。")
	private String firstNameKana;

	@NotBlank(message = "未入力があります。")
	@Email(message = "メールアドレスの形式が正しくありません。")
	private String email;

	@NotBlank(message = "未入力があります。")
	@Size(min = 8, message = "パスワードは8文字以上で入力してください")
	private String password;

	@NotBlank(message = "未入力があります。")
	private String confirmPassword;    
}
