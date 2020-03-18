package com.atguigu.gmall.cart.service;

import com.atguigu.gmall.cart.pojo.Cart;

import java.util.List;

public interface CartService {
    void addCart(Cart cart);

    List<Cart> queryCarts();

    void updateNum(Cart cart);

    void updateCheck(Cart cart);

    void deleteCart(Long skuId);
}
