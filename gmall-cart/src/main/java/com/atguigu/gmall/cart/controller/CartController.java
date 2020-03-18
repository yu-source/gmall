package com.atguigu.gmall.cart.controller;

import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.cart.pojo.Cart;
import com.atguigu.gmall.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;


    /**
     * 添加购物车
     */
    @PostMapping
    public Resp<Object> addCart(@RequestBody Cart cart){
        this.cartService.addCart(cart);
        return Resp.ok(null);
    }

    /**
     * 查询购物车
     */
    @GetMapping
    public Resp<List<Cart>> queryCarts(){
        List<Cart> carts = this.cartService.queryCarts();
        return Resp.ok(carts);
    }

    /**
     * 修改数量
     */
    @PostMapping("update")
    public Resp<Object> updateNum(@RequestBody Cart cart){
        this.cartService.updateNum(cart);
        return Resp.ok(null);
    }

    /**
     * 更新选中状态
     */
    @PostMapping("check")
    public Resp<Object> updateCheck(@RequestBody Cart cart){
        this.cartService.updateCheck(cart);
        return Resp.ok(null);
    }

    /**
     * 删除购物车商品
     */
    @PostMapping("{skuId}")
    public Resp<Object> deleteCart(@PathVariable("skuId")Long skuId){
        this.cartService.deleteCart(skuId);
        return Resp.ok(null);
    }
}
