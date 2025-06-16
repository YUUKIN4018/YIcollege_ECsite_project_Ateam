package com.college.yi.EcSite.admin.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductEditForm {
    @NotBlank
    private String name;

    @NotNull @Min(1)
    private BigDecimal price;

    private String description;

    @NotNull @Min(1)
    private Integer stockQuantity;

    private Integer status;

    @NotNull
    private Long categoryId;

    @Valid
    private List<ProductImageRegisterForm> imageList = new ArrayList<>();
}
