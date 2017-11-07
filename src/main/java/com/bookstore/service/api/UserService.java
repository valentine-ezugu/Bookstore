package com.bookstore.service.api;

import com.bookstore.domain.User;
import com.bookstore.domain.UserBilling;
import com.bookstore.domain.UserPayment;
import com.bookstore.domain.UserShipping;
import com.bookstore.domain.security.PasswordResetToken;
import com.bookstore.domain.security.UserRole;

import java.util.Set;

public interface UserService {

    PasswordResetToken getPasswordResetToken(final String token);

    void createPasswordResetTokenForUser(final User user, final String token);

    User findByUsername(String username);

    User findById(Long id);

    User findByEmail(String email);

    User createUser(User user, Set<UserRole> userRoles)throws Exception;

    User save (User user);

    void setUserDefaultShipping(Long userShippingId , User user);

    void  updateUserBilling(UserBilling userBilling, UserPayment userPayment,User user);

    void setUserDefaultPayment(Long userPaymentId, User user);

    void updateUserShipping(UserShipping userShipping, User user);

}

