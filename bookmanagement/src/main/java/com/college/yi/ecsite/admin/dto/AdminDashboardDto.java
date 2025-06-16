package com.college.yi.ecsite.admin.dto;


public class AdminDashboardDto {
    
    private int productCount;
    private int orderCount;
    private int outOfStockCount;
    
    public AdminDashboardDto(int productCount, int orderCount, int outOfStockCount) {
        this.productCount = productCount;
        this.orderCount = orderCount;
        this.outOfStockCount = outOfStockCount;
    }
    
    public AdminDashboardDto() {
        
    }
    
    public int getProductCount() {
        return productCount;
    }
    
    public int getOrderCount() {
        return orderCount;
    }
    
    public int getOutOfStockCount() {
        return outOfStockCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }
    
    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }
 
    public void setOutOfStockCount(int outOfStockCount) {
        this.outOfStockCount = outOfStockCount;
    }
}
