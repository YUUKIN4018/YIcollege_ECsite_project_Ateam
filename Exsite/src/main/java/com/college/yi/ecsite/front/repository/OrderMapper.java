package com.college.yi.ecsite.front.repository;

import com.college.yi.ecsite.User.OrderItem;
import com.college.yi.ecsite.entity.Order;

public interface OrderMapper {
	void insertOrder(Order order);
	void insertOrderDetail(OrderItem item);

}
