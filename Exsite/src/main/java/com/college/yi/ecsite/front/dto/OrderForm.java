package com.college.yi.ecsite.front.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class OrderForm {
	
	@NotBlank(message="氏名を入力してください。")
	private String name;
	
	@NotBlank(message="郵便番号を入力してください。")
	@Pattern(regexp="\\\\d{3}-\\\\d{4}\", message = \"入力された郵便番号の形式が正しくありません。")
	private String zipCode;
	
	@NotBlank(message = "住所を入力してください。")
    private String address;

    @NotBlank(message = "電話番号を入力してください。")
    @Pattern(regexp = "\\d{2,4}-\\d{2,4}-\\d{4}", message = "入力された電話番号の形式が正しくありません。")
    private String phone;

    private String note;

    private boolean specifyDatetime; // 日時指定チェックボックス
    private String specifyDatetimeValue;
    
    
    //以下は、OrderControllerで使用するメソッド

    public boolean isSpecifyDatetime() {
        return specifyDatetime;
    }

    public void setSpecifyDatetime(boolean specifyDatetime) {
        this.specifyDatetime = specifyDatetime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public String getSpecifyDatetimeValue() {
        return specifyDatetimeValue;
    }
    public void setSpecifyDatetimeValue(String specifyDatetimeValue) {
        this.specifyDatetimeValue = specifyDatetimeValue;
    }

	

}
