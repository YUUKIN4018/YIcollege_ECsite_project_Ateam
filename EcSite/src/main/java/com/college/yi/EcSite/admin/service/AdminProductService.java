package com.college.yi.EcSite.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.validation.NoProviderFoundException;

import org.springframework.beans.BeanUtils;
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

    public List<ProductsDto> getProductList() {
        List<Product> products = productMapper.findAll();

        if (products.isEmpty()) {
            throw new NoProviderFoundException("現在登録されている商品はありません");
        }

        
        List<Long> productIds = products.stream()
                .map(Product::getProductId)
                .collect(Collectors.toList());
        List<ProductImage> images = productImageMapper.findByProductIds(productIds);
        Map<Long, List<ProductImageDto>> imageMap = images.stream()
                .map(img -> {
                    ProductImageDto dto = new ProductImageDto();
                    BeanUtils.copyProperties(img, dto);
                    return dto;
                })
                .collect(Collectors.groupingBy(ProductImageDto::getProductId));

        
        List<Long> categoryIds = products.stream()
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

        return products.stream().map(product -> {
            ProductsDto dto = new ProductsDto();
            BeanUtils.copyProperties(product, dto);
            dto.setImages(imageMap.getOrDefault(product.getProductId(), new ArrayList<>()));
            dto.setCategory(categoryMap.get(product.getCategoryId())); // ←追加部分
            return dto;
        }).collect(Collectors.toList());
    }
}
