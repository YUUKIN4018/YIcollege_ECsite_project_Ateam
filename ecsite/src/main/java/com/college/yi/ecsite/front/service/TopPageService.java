package com.college.yi.ecsite.front.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.college.yi.ecsite.front.dto.TopPageProductDto;
import com.college.yi.ecsite.front.repository.ProductMapper;

@Service
public class TopPageService {
    private final ProductMapper productMapper;

    public TopPageService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public List<TopPageProductDto> getTopProducts(int page) {
        int limit = 12;
        int offset = (page - 1) * limit;
        return productMapper.findAll(limit, offset);
    }

    public List<TopPageProductDto> searchProducts(String keyword) {
        return productMapper.searchByKeyword(keyword);
    }

    public List<TopPageProductDto> filterByCategory(String category) {
        return productMapper.filterByCategory(category);
    }
}
