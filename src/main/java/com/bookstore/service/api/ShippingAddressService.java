package com.bookstore.service.api;

import com.bookstore.domain.ShippingAddress;
import com.bookstore.domain.UserShipping;

public interface ShippingAddressService {
    ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
