package com.semenov.bookshop.exceptionhandling;

public class BookshopException extends RuntimeException {
    public BookshopException(String message) {
        super(message);
    }
}
