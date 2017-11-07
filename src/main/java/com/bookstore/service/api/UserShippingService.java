package com.bookstore.service.api;


import com.bookstore.domain.UserShipping;

/**
 * Created by Pc on 8/31/2017.
 */
public interface UserShippingService {
     UserShipping findById(long id);

     void removeById(Long id);
}
