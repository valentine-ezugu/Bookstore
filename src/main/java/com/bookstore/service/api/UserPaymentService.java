package com.bookstore.service.api;

import com.bookstore.domain.UserPayment;

/**
 * Created by Pc on 8/26/2017.
 */
public interface UserPaymentService {

    UserPayment findById(Long id);

    void removeById(Long id);
}
