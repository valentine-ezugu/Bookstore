package com.bookstore.service.impl;

import com.bookstore.domain.*;

import java.util.List;

/**
 * Created by Pc on 9/8/2017.
 */
public interface CartItemService  {
    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

    CartItem updateCartItem(CartItem cartItem);

    CartItem addBookToCartItem(Book book, User user, int qty);

     CartItem findById(Long id) ;

    void removeCartItem(CartItem cartItem);

    CartItem save(CartItem cartItem);

    List<CartItem> findByOrder(Order order);
}
