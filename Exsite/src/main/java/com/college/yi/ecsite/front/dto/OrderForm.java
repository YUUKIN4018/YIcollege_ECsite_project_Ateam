package com.college.yi.ecsite.front.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data 
public class OrderForm {

    @NotBlank(message = "氏名を入力してください。")
    private String name;

    @NotBlank(message = "郵便番号を入力してください。")
    @Pattern(regexp = "\\d{3}-\\d{4}", message = "入力された郵便番号の形式が正しくありません。（例：123-4567）")
    private String zipCode;

    @NotBlank(message = "住所を入力してください。")
    private String address;

    @NotBlank(message = "電話番号を入力してください。")
    @Pattern(regexp = "\\d{2,4}-\\d{2,4}-\\d{4}", message = "入力された電話番号の形式が正しくありません。（例：03-1234-5678）")
    private String phone;

    private String note;

    private boolean specifyDatetime; // 日時指定チェックボックス
    private String specifyDatetimeValue;
}
