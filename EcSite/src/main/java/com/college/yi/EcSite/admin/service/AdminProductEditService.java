package com.college.yi.EcSite.admin.service;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.college.yi.EcSite.admin.dto.ProductEditDto;
import com.college.yi.EcSite.admin.form.ProductEditForm;
import com.college.yi.EcSite.admin.form.ProductImageRegisterForm;
import com.college.yi.EcSite.admin.repository.CategoryMapper;
import com.college.yi.EcSite.admin.repository.ProductImageMapper;
import com.college.yi.EcSite.admin.repository.ProductMapper;
import com.college.yi.EcSite.entity.Category;
import com.college.yi.EcSite.entity.Product;
import com.college.yi.EcSite.entity.ProductImage;

@Service
public class AdminProductEditService {

    private final ProductMapper productMapper;
    private final ProductImageMapper productImageMapper;
    private final CategoryMapper categoryMapper;

    private final String imageSaveDir = "C:\\tmp\\uploaded_images\\";

    public AdminProductEditService(ProductMapper productMapper, ProductImageMapper productImageMapper, CategoryMapper categoryMapper) {
        this.productMapper = productMapper;
        this.productImageMapper = productImageMapper;
        this.categoryMapper = categoryMapper;
    }

    public ProductEditDto getProductForEdit(Long productId) {
        Product product = productMapper.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("商品が見つかりません"));
        List<ProductImage> images = productImageMapper.findByProductId(productId);
        return ProductEditDto.of(product, images);
    }

    @Transactional
    public void updateProduct(Long productId, ProductEditForm form) {
        
        Product product = productMapper.findById(productId)
                .orElseThrow(() -> new RuntimeException("商品が見つかりません"));

        
        if (!StringUtils.hasText(form.getName())) {
            throw new IllegalArgumentException("商品名を入力してください");
        }
        if (form.getName().length() > 50) {
            throw new IllegalArgumentException("商品名は50文字以内で入力してください");
        }

       
        if (form.getPrice() == null) {
            throw new IllegalArgumentException("価格を入力してください");
        }
        try {
            int price = form.getPrice().intValue();
            if (price < 0) {
                throw new IllegalArgumentException("価格は0以上で入力してください");
            }
            if (price >= 1_000_000) {
                throw new IllegalArgumentException("価格は100万円未満で入力してください");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("価格は数値で入力してください");
        }

        
        if (form.getCategoryId() == null) {
            throw new IllegalArgumentException("カテゴリを選択してください");
        }
        Category category = categoryMapper.findById(form.getCategoryId());
        if (category == null) {
            throw new IllegalArgumentException("選択されたカテゴリが存在しません");
        }

        
        if (form.getImageList() != null) {
            for (ProductImageRegisterForm img : form.getImageList()) {
                MultipartFile file = img.getImageFile();
                if (file != null && !file.isEmpty()) {
                    String originalName = file.getOriginalFilename();
                    if (originalName != null) {
                        String lowerName = originalName.toLowerCase();
                        if (!(lowerName.endsWith(".jpg") || lowerName.endsWith(".jpeg") ||
                              lowerName.endsWith(".png") || lowerName.endsWith(".gif"))) {
                            throw new IllegalArgumentException("画像ファイルはjpg、png、gif形式でアップロードしてください");
                        }
                    }
                    if (file.getSize() > 5 * 1024 * 1024) {
                        throw new IllegalArgumentException("画像の容量は5MB以下にしてください");
                    }
                }
            }
        }
        
        product.setCategoryId(form.getCategoryId());
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setDescription(form.getDescription());
        product.setStockQuantity(form.getStockQuantity());
        product.setStatus(form.getStatus());
        product.setUpdatedAt(LocalDateTime.now());
        productMapper.update(product);
        
        productImageMapper.deleteByProductId(productId);
        if (form.getImageList() != null) {
            for (ProductImageRegisterForm imgForm : form.getImageList()) {
                MultipartFile file = imgForm.getImageFile();
                String imageUrl = null;
                if (file != null && !file.isEmpty()) {
                    imageUrl = saveImageAndGetUrl(file);
                } else if (imgForm.getExistingImageUrl() != null) {
                    imageUrl = imgForm.getExistingImageUrl();
                }
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

    private String saveImageAndGetUrl(MultipartFile file) {
        java.io.File dir = new java.io.File(imageSaveDir);
        if (!dir.exists()) dir.mkdirs();

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        java.io.File saveFile = new java.io.File(dir, fileName);
        try {
            file.transferTo(saveFile);
        } catch (Exception e) {
            throw new RuntimeException("画像ファイルの保存に失敗しました", e);
        }
        return "/uploaded_images/" + fileName;
    }

    }



