package com.college.yi.EcSite.admin.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductImageRegisterForm {
    private MultipartFile imageFile; // アップロード画像
    private Integer sortOrder;       // 表示順
    private Boolean isMain; 

}
