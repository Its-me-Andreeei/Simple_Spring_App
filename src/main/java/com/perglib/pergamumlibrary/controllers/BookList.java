package com.perglib.pergamumlibrary.controllers;

import com.perglib.pergamumlibrary.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class BookList {
    private final ArrayList<Book> bookList;

    @Autowired
    public BookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    @GetMapping("/library/book")
    public @ResponseBody Book getBookByTitle(@RequestParam String title)
    {
        for(Book book : bookList)
        {
            if(Objects.equals(book.getBookName(), title))
                return book;
        }
        return null;
    }

    @GetMapping("/library/all_books")
    public @ResponseBody ArrayList<Book> getAllBooksSorted()
    {
        return bookList.stream()
                .sorted().collect(Collectors.toCollection(ArrayList::new));
    }

    @PostMapping("/library/add")
    public @ResponseBody String addNewBook(@RequestParam String title, @RequestParam String authorName)
    {
        Book new_book = new Book(authorName, title);
        bookList.add(new_book);
        return "Book Added Successfully !";
    }

    @PostMapping("library/add_if_not_there")
    public @ResponseBody String addUniqueBookToLibrary(@RequestParam String authorName, @RequestParam String title)
    {
        Book new_book = new Book(authorName, title);
        if( ! bookList.contains(new_book)) {
            bookList.add(new_book);
            return "Book Added Successfully !";
        }
        else
            return "Book already exists !";
    }

    @DeleteMapping("library/delete_title")
    public @ResponseBody String deleteBookByTitle(@RequestParam String title)
    {
        if(bookList.removeIf(book -> Objects.equals(book.getBookName(), title)))
            return "Book found and deleted !";
        else
            return "Book not found : Done nothing !";
    }
    @PutMapping("library/modify_book")
    public @ResponseBody String modifyExistingBook(
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam(required = false) String new_title,
            @RequestParam(required = false) String new_author
    )
    {
        int found=0;
        for (Book book : bookList) {
            if (Objects.equals(book.getBookName(), title) && Objects.equals(book.getAuthorName(), author)) {
                if (new_author != null) {
                    book.setAuthorName(new_author);
                    found = 1;
                }
                if (new_title != null) {
                    book.setBookName(new_title);
                    found = 1;
                }
            }
        }
        return found==1 ? "Book modified successfully !" : "Book not found";
    }



}
