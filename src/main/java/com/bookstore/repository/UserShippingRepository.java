package com.bookstore.repository;


import com.bookstore.domain.UserShipping;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Pc on 8/31/2017.
 */
public interface UserShippingRepository extends CrudRepository<UserShipping, Long > {

    UserShipping findOne(long id);
}
