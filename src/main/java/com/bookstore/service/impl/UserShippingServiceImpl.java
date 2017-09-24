package com.bookstore.service.impl;

import com.bookstore.domain.UserShipping;
import com.bookstore.repository.UserShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Pc on 8/31/2017.
 */
@Service
public class UserShippingServiceImpl implements UserShippingService{

    @Autowired
    UserShippingRepository userShippingRepository;


    @Override
    public UserShipping findById(long id) {
        return userShippingRepository.findOne(id);
    }

    @Override
    public void removeById(Long id) {
         userShippingRepository.delete(id);
    }
}
