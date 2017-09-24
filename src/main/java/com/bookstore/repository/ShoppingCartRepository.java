package com.bookstore.repository;


import com.bookstore.domain.ShoppingCart;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by Pc on 9/8/2017.
 */
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {


}
