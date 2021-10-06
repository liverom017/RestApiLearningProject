package com.BookstoreLearning.bookstore.bookStore.services;

import com.BookstoreLearning.bookstore.bookStore.dao.BookDao;
import com.BookstoreLearning.bookstore.bookStore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BookService {
    private final BookDao bookDao;

    public void cheatCode() {
        bookDao.cheatCode();
    }

    @Autowired
    public BookService(@Qualifier("fakeBookDao") BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public boolean createBook(Book book) {
        return bookDao.createBook(book);
    }

    public boolean createBook(Book[] book) {
        return bookDao.createBook(book);
    }

    public Book getBook(String title) {
        return bookDao.getBook(title);
    }

    public void deleteBook(String title, String author) {
        bookDao.deleteBook(title, author);
    }

    public void updateBook(String title, Book book) {
        bookDao.updateBook(title, book);
    }

    public Book getBookByID(String id) {
        return bookDao.getBookByID(id);
    }

    public Map<String, Book> getBookByDate(String date) {
        return bookDao.getBookByDate(date);
    }

    public Map<String, Book> getBookByPrice(double price) {
        return bookDao.getBookByPrice(price);
    }

    public Map<String, Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    public Map<String, Book> getAllBooksByAuthor(String author) {
        return bookDao.getAllBooksByAuthor(author);
    }

}
