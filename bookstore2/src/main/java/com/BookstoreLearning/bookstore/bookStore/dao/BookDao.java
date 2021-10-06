package com.BookstoreLearning.bookstore.bookStore.dao;

import com.BookstoreLearning.bookstore.bookStore.model.Book;

import java.util.*;

public interface BookDao {
    void cheatCode();

    // Create a new Book
    boolean createBook(Book book);

    //Progressive overloading of a list for admin
    boolean createBook(Book[] books);

    // Returns a single book based on the title(key:title)
    Book getBook(String title);

    // Deletes/un-publish a single Book
    void deleteBook(String author, String title);

    //Update a single book
    void updateBook(String title, Book book);

    // gets a book with given id
    Book getBookByID(String id);

    // gets all books by publishDate
    Map<String, Book> getBookByDate(String date);

    // gets all books of a certain price
    Map<String, Book> getBookByPrice(double price);

    // Method to return all books
    Map<String, Book> getAllBooks();

    // Get all books from a given author
    Map<String, Book> getAllBooksByAuthor(String author);


}
