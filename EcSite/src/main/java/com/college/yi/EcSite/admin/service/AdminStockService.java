package com.college.yi.EcSite.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.college.yi.EcSite.admin.form.StockUpdateForm;
import com.college.yi.EcSite.admin.repository.ProductMapper;
import com.college.yi.EcSite.entity.Product;

@Service
public class AdminStockService {
    private final ProductMapper productMapper;

    public AdminStockService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public Product getProduct(Long productId) {
        return productMapper.findById(productId).orElse(null);
    }

    @Transactional
    public void updateStock(StockUpdateForm form) {
        int updated = productMapper.updateStock(form.getProductId(), form.getStockQuantity());
        if (updated == 0) {
            throw new RuntimeException("在庫更新に失敗しました");
        }
    }
}
