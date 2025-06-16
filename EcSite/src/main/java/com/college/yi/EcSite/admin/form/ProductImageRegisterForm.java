package com.college.yi.EcSite.admin.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductImageRegisterForm {
    private MultipartFile imageFile;
    private String existingImageUrl; 
    private Integer sortOrder;     
    private Boolean isMain;    
}
