package com.bookstore.repository;

import com.bookstore.domain.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Pc on 9/19/2017.
 */
public interface  OrderRepository extends CrudRepository<Order,Long> {

}
