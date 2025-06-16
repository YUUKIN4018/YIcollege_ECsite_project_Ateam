package com.college.yi.ecsite.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.yi.ecsite.admin.dto.AdminDashboardDto;
import com.college.yi.ecsite.admin.repository.OrderMapper;
import com.college.yi.ecsite.admin.repository.ProductMapper;

@Service
public class AdminDashboardService {
    
    @Autowired
    private ProductMapper productMapper;
    
    @Autowired
    private OrderMapper orderMapper;
    
    public AdminDashboardDto getDashBoardData() {
        AdminDashboardDto dto = new AdminDashboardDto();
        
        //登録商品数
        int productCount = productMapper.countProducts();
        
        //在庫切れ商品数
        int outOfstockCount = productMapper.countOutOfStockProducts();
        
        //注文件数
        int orderCount = orderMapper.countOrders();
        
        //DTOにセット
        dto.setProductCount(productCount);
        dto.setOutOfStockCount(outOfstockCount);
        dto.setOrderCount(orderCount);
        
        return dto;
    }

}
