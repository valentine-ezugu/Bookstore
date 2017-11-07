package com.bookstore.service.impl;

import com.bookstore.domain.UserPayment;
import com.bookstore.repository.UserPaymentRepository;
import com.bookstore.service.api.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserPaymentServiceImpl implements UserPaymentService {
    @Autowired
    UserPaymentRepository userPaymentRepository;

    @Override
    public UserPayment findById(Long id) {
        return userPaymentRepository.findOne(id);
    }

    @Override
    public void removeById(Long id) {
       userPaymentRepository.delete(id);
    }
}
