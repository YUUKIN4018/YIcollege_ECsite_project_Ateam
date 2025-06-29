package com.college.yi.EcSite.admin.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.college.yi.EcSite.admin.form.ProductImageRegisterForm;
import com.college.yi.EcSite.admin.form.ProductRegisterForm;
import com.college.yi.EcSite.admin.repository.CategoryMapper;
import com.college.yi.EcSite.admin.repository.ProductImageMapper;
import com.college.yi.EcSite.admin.repository.ProductMapper;
import com.college.yi.EcSite.entity.Category;
import com.college.yi.EcSite.entity.Product;
import com.college.yi.EcSite.entity.ProductImage;

@Service
public class AdminProductRegisterService {

    private final ProductMapper productMapper;
    private final ProductImageMapper productImageMapper;
    private final CategoryMapper categoryMapper;

    private final String imageSaveDir = "C:\\tmp\\uploaded_images\\";

    public AdminProductRegisterService(ProductMapper productMapper,
                                      ProductImageMapper productImageMapper,
                                      CategoryMapper categoryMapper) {
        this.productMapper = productMapper;
        this.productImageMapper = productImageMapper;
        this.categoryMapper = categoryMapper;
    }

    @Transactional
    public void registerProduct(ProductRegisterForm form) {
        validateForm(form);

        
        Category category = categoryMapper.findById(form.getCategoryId());
        if (category == null) {
            throw new IllegalArgumentException("選択されたカテゴリが存在しません");
        }

        Product product = new Product();
        product.setShopId(form.getShopId());
        product.setCategoryId(form.getCategoryId());
        product.setName(form.getName());
        product.setDescription(form.getDescription());
        product.setPrice(form.getPrice());
        product.setTaxRate(form.getTaxRate());
        product.setStockQuantity(form.getStockQuantity());
        product.setStatus(form.getStatus());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        productMapper.insert(product);

        Long productId = product.getProductId();

        if (form.getImageList() != null) {
            for (ProductImageRegisterForm imgForm : form.getImageList()) {
                MultipartFile file = imgForm.getImageFile();
                if (file != null && !file.isEmpty()) {

                    String imageUrl = saveImageAndGetUrl(file);

                    ProductImage image = new ProductImage();
                    image.setProductId(productId);
                    image.setImageUrl(imageUrl);
                    image.setSortOrder(imgForm.getSortOrder());
                    image.setIsMain(imgForm.getIsMain());
                    image.setCreatedAt(LocalDateTime.now());
                    productImageMapper.insert(image);
                }
            }
        }
    }

    private void validateForm(ProductRegisterForm form) {

        if (!StringUtils.hasText(form.getName())) {
            throw new IllegalArgumentException("商品名を入力してください");
        }

        if (form.getPrice() == null) {
            throw new IllegalArgumentException("価格を入力してください");
        }

        try {
            form.getPrice().doubleValue();
        } catch (Exception e) {
            throw new IllegalArgumentException("価格は数値で入力してください");
        }

        if (form.getCategoryId() == null) {
            throw new IllegalArgumentException("カテゴリを選択してください");
        }

        if (form.getImageList() != null) {
            for (ProductImageRegisterForm img : form.getImageList()) {
                MultipartFile file = img.getImageFile();
                if (file != null && !file.isEmpty()) {
                    if (file.getSize() > 5 * 1024 * 1024) {
                        throw new IllegalArgumentException("画像の容量は5MB以下にしてください");
                    }
                }
            }
        }
    }

    private String saveImageAndGetUrl(MultipartFile file) {
        File dir = new File(imageSaveDir);
        if (!dir.exists()) dir.mkdirs();

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File saveFile = new File(dir, fileName);
        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            throw new RuntimeException("画像ファイルの保存に失敗しました", e);
        }
        return "/uploaded_images/" + fileName;
    }
}
