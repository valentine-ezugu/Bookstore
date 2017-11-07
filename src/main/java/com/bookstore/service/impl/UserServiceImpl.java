package com.bookstore.service.impl;

import com.bookstore.domain.*;
import com.bookstore.domain.security.PasswordResetToken;
import com.bookstore.domain.security.UserRole;
import com.bookstore.repository.*;
import com.bookstore.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@javax.transaction.Transactional
public class UserServiceImpl implements UserService {

    private static final Logger Log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPaymentRepository userPaymentRepository;

    @Autowired
    private UserShippingRepository userShippingRepository;

    @Override
    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void createPasswordResetTokenForUser(final User user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(myToken);
    }
    @Override
   public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByEmail(String email){
       return userRepository.findByEmail(email);
    }

    //handling the logic of creating a user and role saving
    @Override
    @Transactional
     public User createUser(User user, Set<UserRole> userRoles){
     User localUser = userRepository.findByUsername(user.getUsername());

     if(localUser != null){
         Log.info("user {} already exists. Nothing will be done",user.getUsername());
     }else {
         for (UserRole ur : userRoles){
             roleRepository.save(ur.getRole());
         }
         user.getUserRoles().addAll(userRoles);

         //After adding anotation transactional we added this algorithm
         ShoppingCart shoppingCart = new ShoppingCart();
         shoppingCart.setUser(user);
         user.setShoppingCart(shoppingCart);

         user.setUserShippingList(new ArrayList<UserShipping>());
         user.setUserPaymentList(new ArrayList<UserPayment>());

         localUser = userRepository.save(user);
     }
    return localUser;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user) {

         userPayment.setUser(user);
         userPayment.setUserBilling(userBilling);
         userPayment.setDefaultPayment(true);
         userBilling.setUserPayment(userPayment);
         user.getUserPaymentList().add(userPayment);
          save(user);

    }

    @Override
    public void updateUserShipping(UserShipping userShipping, User user) {
            userShipping.setUser(user);
            userShipping.setUserShippingDefault(true);
            user.getUserShippingList().add(userShipping);
            save(user);
        }

    @Override
    public void setUserDefaultShipping(Long userShippingId, User user) {
        List<UserShipping> userShippingList =(List<UserShipping>)userShippingRepository.findAll();

        for(UserShipping userShipping : userShippingList){ //first we look through shipping list if any
            if(userShipping.getId()==userShippingId){
                userShipping.setUserShippingDefault(true);
                userShippingRepository.save(userShipping);
            }else {
                userShipping.setUserShippingDefault(false);
                userShippingRepository.save(userShipping);
            }
        }
    }

    @Override
    public void setUserDefaultPayment(Long userPaymentId, User user) {

        List<UserPayment >userPaymentsList =(List<UserPayment>)userPaymentRepository.findAll();

        for(UserPayment userPayment : userPaymentsList){
            if(userPayment.getId()==userPaymentId){
                userPayment.setDefaultPayment(true);
                userPaymentRepository.save(userPayment);
            }else {
                userPayment.setDefaultPayment(false);
                userPaymentRepository.save(userPayment);
            }
        }
    }
}
