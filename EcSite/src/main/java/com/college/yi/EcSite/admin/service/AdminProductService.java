package com.college.yi.EcSite.admin.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.college.yi.EcSite.admin.dto.CategoryDto;
import com.college.yi.EcSite.admin.dto.ProductImageDto;
import com.college.yi.EcSite.admin.dto.ProductsDto;
import com.college.yi.EcSite.admin.repository.CategoryMapper;
import com.college.yi.EcSite.admin.repository.ProductImageMapper;
import com.college.yi.EcSite.admin.repository.ProductMapper;
import com.college.yi.EcSite.entity.Category;
import com.college.yi.EcSite.entity.Product;
import com.college.yi.EcSite.entity.ProductImage;

@Service
public class AdminProductService {

    private final ProductMapper productMapper;
    private final ProductImageMapper productImageMapper;
    private final CategoryMapper categoryMapper;

    public AdminProductService(ProductMapper productMapper,
                               ProductImageMapper productImageMapper,
                               CategoryMapper categoryMapper) {
        this.productMapper = productMapper;
        this.productImageMapper = productImageMapper;
        this.categoryMapper = categoryMapper;
    }

    public Page<ProductsDto> getProductPage(Pageable pageable) {
        List<Product> allProducts = productMapper.findAll();

        if (allProducts.isEmpty()) {
            return new PageImpl<>(List.of(), pageable, 0);
        }

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allProducts.size());
        List<Product> pageContent = allProducts.subList(start, end);

        List<Long> productIds = pageContent.stream()
                .map(Product::getProductId)
                .collect(Collectors.toList());

        List<ProductImage> images = productImageMapper.findByProductIds(productIds);
        Map<Long, List<ProductImageDto>> imageMap = images.stream()
                .map(ProductImageDto::of)
                .collect(Collectors.groupingBy(ProductImageDto::getProductId));

        List<Long> categoryIds = pageContent.stream()
                .map(Product::getCategoryId)
                .distinct()
                .collect(Collectors.toList());

        List<Category> categories = categoryMapper.findByIds(categoryIds);
        Map<Long, CategoryDto> categoryMap = categories.stream()
                .map(cat -> {
                    CategoryDto dto = new CategoryDto();
                    BeanUtils.copyProperties(cat, dto);
                    return dto;
                })
                .collect(Collectors.toMap(CategoryDto::getCategoryId, c -> c));

        List<ProductsDto> dtos = pageContent.stream().map(product -> {
            ProductsDto dto = new ProductsDto();
            BeanUtils.copyProperties(product, dto);

            List<ProductImageDto> imagesForProduct = imageMap.getOrDefault(product.getProductId(), new ArrayList<>());
            dto.setImages(imagesForProduct);
            dto.setImageUrl(
                    imagesForProduct.stream()
                            .filter(img -> Boolean.TRUE.equals(img.getIsMain()))
                            .map(ProductImageDto::getImageUrl)
                            .filter(url -> url != null && !url.isBlank())
                            .findFirst()
                            .orElse("NO_IMAGE")
            );
            dto.setCategory(categoryMap.get(product.getCategoryId()));
            return dto;
        }).collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, allProducts.size());
    }

    public Optional<Product> getProduct(Long productId) {
        return productMapper.findById(productId);
    }

    public void updateStock(Long productId, Integer stockQuantity) {
        if (stockQuantity == null || stockQuantity < 0) {
            throw new IllegalArgumentException("在庫数は0以上の整数で入力してください");
        }

        if (!productMapper.findById(productId).isPresent()) {
            throw new IllegalArgumentException("対象商品が見つかりません");
        }

        int updatedRows = productMapper.updateStock(productId, stockQuantity);
        if (updatedRows == 0) {
            throw new RuntimeException("在庫更新に失敗しました");
        }
    }
    public void deleteProduct(Long productId) {
        if (!productMapper.findById(productId).isPresent()) {
            throw new IllegalArgumentException("対象商品が見つかりません");
        }
        
        LocalDateTime now = LocalDateTime.now();

        int updatedRows = productMapper.logicalDelete(productId, now);
        if (updatedRows == 0) {
            throw new RuntimeException("削除に失敗しました。再度お試しください");
        }
    }
}
