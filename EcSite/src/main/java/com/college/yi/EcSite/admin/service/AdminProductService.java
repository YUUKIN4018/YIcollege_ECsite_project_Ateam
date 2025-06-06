package com.college.yi.EcSite.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.validation.NoProviderFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.college.yi.EcSite.admin.dto.ProductImageDto;
import com.college.yi.EcSite.admin.dto.ProductsDto;
import com.college.yi.EcSite.admin.dto.UserDto;
import com.college.yi.EcSite.admin.repository.ProductImageMapper;
import com.college.yi.EcSite.admin.repository.ProductMapper;
import com.college.yi.EcSite.entity.Product;
import com.college.yi.EcSite.entity.ProductImage;

@Service
public class AdminProductService {
    
    private final ProductMapper productMapper;
    private final ProductImageMapper productImageMapper;
            
    public AdminProductService(ProductMapper productMapper, ProductImageMapper productImageMapper) {
        this.productMapper = productMapper;
        this.productImageMapper = productImageMapper;
    }
    
    public List<ProductsDto> getProdutList(UserDto user){
        if(user == null || !user.isAdmin()) {
            throw new AccessDeniedException("管理者のみ商品一覧を取得できます");
        }
        
        List<Product> products = productMapper.findAll();
        
        if(products.isEmpty()) {
            throw new NoProviderFoundException("現在登録されている商品はありません");
            
        }
        
        List<Long> productIds = products.stream()
                .map(Product::getProductId)
                .collect(Collectors.toList());

        List<ProductImage> images = productImageMapper.findByProductIds(productIds);

        List<ProductImageDto> imageDtos = images.stream()
                .map(img -> {
                    ProductImageDto dto = new ProductImageDto();
                    BeanUtils.copyProperties(img, dto);
                    return dto;
                })
                .collect(Collectors.toList());
        
        Map<Long, List<ProductImageDto>> imageMap = imageDtos.stream()
                .collect(Collectors.groupingBy(ProductImageDto::getProductId));
        
        return products.stream().map(product -> {
            ProductsDto dto = new ProductsDto();
            BeanUtils.copyProperties(product, dto);
            dto.setImages(imageMap.getOrDefault(product.getProductId(), new ArrayList<>()));
            return dto;
        }).collect(Collectors.toList());



    }
        }
