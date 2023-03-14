package com.perglib.pergamumlibrary.model;

import java.util.Objects;

public class Book implements Comparable<Book>{
    private String authorName;
    private String bookName;

    public Book(String authorName, String bookName) {
        this.authorName = authorName;
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public boolean equals(Object obj) {
        //using null-safe equals : Objects.equals(x,y)
        if(obj instanceof Book)
            return Objects.equals(((Book) obj).bookName, this.bookName) && Objects.equals(((Book) obj).authorName, this.authorName);
        else return false;
    }

    @Override
    public int compareTo(Book o) {
        if( this.authorName.compareTo(o.authorName) == 0)
            return this.bookName.compareTo(o.bookName);
        else
            return this.authorName.compareTo(o.authorName);
    }
}
