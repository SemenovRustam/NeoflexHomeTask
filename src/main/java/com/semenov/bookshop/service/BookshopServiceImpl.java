package com.semenov.bookshop.service;

import com.semenov.bookshop.entity.Author;
import com.semenov.bookshop.entity.Book;
import com.semenov.bookshop.exceptionhandling.BookshopException;
import com.semenov.bookshop.repository.AuthorRepository;
import com.semenov.bookshop.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookshopServiceImpl implements BookshopService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public List<String> getAllBook() {
        log.info("find all book");
        return bookRepository.findAll()
                .stream()
                .map(Book::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllAuthor() {
        log.info("find all author");
        return authorRepository.findAll()
                .stream()
                .map(Author::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBookByAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new BookshopException("Author is not exists"));
        return author.getBook()
                .stream()
                .map(Book::getName)
                .collect(Collectors.toList());
    }

    @Override
    public Book getBookInfo(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new BookshopException("Book is not exists"));
    }

    @Override
    public Integer getCoastAllBookByAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new BookshopException("Author is not exists"));
        return author.getBook()
                .stream()
                .map(Book::getCoast)
                .flatMapToInt(IntStream::of)
                .sum();
    }

    @Override
    public List<String> getAuthorWithExpensiveBooks() {
        ArrayList<String> listWithExpensiveAuthor = new ArrayList<>();

        authorRepository.findAll().forEach(author -> {
                    long count = author.getBook()
                            .stream()
                            .filter(getBookPredicate())
                            .count();

                    if (count >= 3) {
                        listWithExpensiveAuthor.add(author.getName());
                    }
                }
        );
        return listWithExpensiveAuthor;
    }

    private Predicate<Book> getBookPredicate() {
        return book -> book.getCoast() > 2000;
    }
}
