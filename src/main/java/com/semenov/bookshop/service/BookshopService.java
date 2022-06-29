package com.semenov.bookshop.service;

import com.semenov.bookshop.entity.Book;

import java.util.List;

public interface BookshopService {
    List<String> getAllAuthor();

    List<String> getAllBook();

    List<String> getAllBookByAuthor(Long authorId);

    Book getBookInfo(Long bookId);

    Integer getCoastAllBookByAuthor(Long id);

    public List<String> getAuthorWithExpensiveBooks();

}
