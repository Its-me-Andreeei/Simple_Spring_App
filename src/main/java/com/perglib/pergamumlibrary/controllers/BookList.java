package com.perglib.pergamumlibrary.controllers;

import com.perglib.pergamumlibrary.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class BookList {
    private ArrayList<Book> bookList;

    @Autowired
    public BookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    @GetMapping("/library/books")
    public ArrayList<Book> getListOfBooks()
    {
        return bookList;
    }

    @PostMapping("/library/newBook")
    public void createNewListOfBooks()
    {
        bookList = new ArrayList<Book>();
        bookList.add(new Book("Alecsandri", "Ana"));
        bookList.add(new Book("Alecsandri V", "Ana A"));
    }

}
