package com.college.yi.ecsite.front.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.college.yi.ecsite.entity.CartItem;

@Mapper
public interface CartItemMapper {
    List<CartItem> findByUserId(@Param("userId") Long userId);

    Optional<CartItem> findByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    void insert(CartItem cartItem);

    void update(CartItem cartItem);

    void delete(@Param("userId") Long userId, @Param("productId") Long productId);

	void deleteAllByUserId(Long userId);
}
