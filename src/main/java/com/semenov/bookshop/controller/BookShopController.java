package com.semenov.bookshop.controller;

import com.semenov.bookshop.entity.Book;
import com.semenov.bookshop.service.BookshopService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookShopController {

    private final BookshopService bookshopService;

    @PostMapping("/getAllBook")
    @ApiOperation(value = "Выбрать все книги", notes = "Выбрать все   книги")
    public ResponseEntity<List<String>> getAllBook() {
        return ResponseEntity.ok(bookshopService.getAllBook());
    }


    @PostMapping("/getAllAuthor")
    @ApiOperation(value = "Выбрать всех авторов", notes = "Выбрать всех авторов")
    public ResponseEntity<List<String>> getAllAuthor() {
        return ResponseEntity.ok(bookshopService.getAllAuthor());
    }

    @PostMapping("/getAllBookByAuthor/{id}")
    @ApiOperation(value = "Выбрать все книги одного автора", notes = "Выбрать все книги одного автора")
    public ResponseEntity<List<String>> getAllBookByAuthor(@PathVariable Long id) {
        return ResponseEntity.ok(bookshopService.getAllBookByAuthor(id));
    }

    @PostMapping("/getBookInfo/{id}")
    @ApiOperation(value = "Получение информации о книге", notes = "Получить информацию о книге")
    public ResponseEntity<Book> getBookInfo(@PathVariable Long id) {
        return ResponseEntity.ok(bookshopService.getBookInfo(id));
    }

    @PostMapping("/getCoastAllBook/{id}")
    @ApiOperation(value = "Получение стоимости  всех  книг одного автора", notes = "Получить стоимость всех книг")
    public ResponseEntity<Integer> getCoastAllBookByAuthor(@PathVariable Long id) {
        return ResponseEntity.ok(bookshopService.getCoastAllBookByAuthor(id));
    }


    @PostMapping("/getExpensiveBook")
    @ApiOperation(value = "Получение авторов с 3 и более  книгами, дороже 2000", notes = "Получить список авторов")
    public ResponseEntity<List<String>> getExpensiveAuthor() {
        return ResponseEntity.ok(bookshopService.getAuthorWithExpensiveBooks());
    }
}
