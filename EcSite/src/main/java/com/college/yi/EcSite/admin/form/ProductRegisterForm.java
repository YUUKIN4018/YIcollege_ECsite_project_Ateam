package com.college.yi.EcSite.admin.form;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRegisterForm {
    
    private Long productId;

    @NotNull(message = "ショップIDは必須です")
    private Long shopId;

    @NotNull(message = "カテゴリIDは必須です")
    private Long categoryId;

    @NotBlank(message = "商品名は必須です")
    @Size(max = 255, message = "商品名は255文字以内で入力してください")
    private String name;

    @Size(max = 1000, message = "商品説明は1000文字以内で入力してください")
    private String description;

    @NotNull(message = "価格は必須です")
    @DecimalMin(value = "0.0", inclusive = false, message = "価格は0より大きい値を入力してください")
    @Digits(integer = 10, fraction = 2, message = "価格は整数10桁、小数2桁までです")
    private BigDecimal price;

    @NotNull(message = "税率は必須です")
    @DecimalMin(value = "0.0", inclusive = true, message = "税率は0以上で入力してください")
    @Digits(integer = 2, fraction = 2, message = "税率は整数2桁、小数2桁までです")
    private BigDecimal taxRate;

    @NotNull(message = "在庫数は必須です")
    @Min(value = 0, message = "在庫数は0以上で入力してください")
    private Integer stockQuantity;

    @NotNull(message = "ステータスは必須です")
    private Integer status;

    private List<ProductImageRegisterForm> imageList;

}
