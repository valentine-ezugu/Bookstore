package com.bookstore.service.impl;


import com.bookstore.domain.Book;

import java.util.List;

/**
 * Created by Pc on 8/15/2017.
 */
public interface BookService {
    List<Book> findAll();
    Book findOne(long id);

    List<Book> findByCategory(String category);

    List<Book> blurrySearch(String title);
}
