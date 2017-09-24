package com.bookstore.service.impl;

import com.bookstore.domain.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

/**
 * Created by Pc on 9/19/2017.
 */

@Service
public interface OrderService {

   Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, BillingAddress billingAddress, Payment payment, String shippingMethod, User user);


   Order findOne(Long id);
}
