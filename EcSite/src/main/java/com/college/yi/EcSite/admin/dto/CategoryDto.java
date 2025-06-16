package com.college.yi.EcSite.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto{
    private Long categoryId;
    private Long parentCategoryId;
    private String name;
    private String description;

}
