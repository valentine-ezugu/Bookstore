package com.bookstore.repository;

import com.bookstore.domain.UserPayment;
import com.bookstore.domain.security.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Pc on 8/26/2017.
 */
public interface UserPaymentRepository extends CrudRepository<UserPayment, Long> {

}
