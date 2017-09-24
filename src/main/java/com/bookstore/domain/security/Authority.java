package com.bookstore.domain.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Pc Valentine Ezugu on 7/30/2017.
 */
public class Authority implements GrantedAuthority {

    private final String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return  authority;
    }

}
