package com.BookstoreLearning.bookstore.bookStore.api;

import com.BookstoreLearning.bookstore.bookStore.model.Book;
import com.BookstoreLearning.bookstore.bookStore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RequestMapping("api/book/")
@RestController
public class BookController {
    private final BookService bookService;

    // Constructor
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
//        this.bookService.cheatCode();
    }

    // POST request: add a new book to the database
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','AUTHOR')")
    public void createBook(@RequestBody Book book) {
        bookService.createBook(book);
    }

    // POST request: Add a list of books
    @PostMapping(path = "/list")
    @PreAuthorize("hasRole('ADMIN')")
    public void createBook(@RequestBody Book[] book) {
        bookService.createBook(book);
    }

    // GET request: get a book by title
    @GetMapping(path = "search/{title}")
    public Book getBook(@PathVariable("title") String title) {
        return bookService.getBook(title);
    }

    // DELETE request: Delete a user
    @DeleteMapping(path = "delete/{author}/{title}")
//    @PreAuthorize("#author == authenticate.name && hasRole('AUTHOR')")
    @PreAuthorize("hasRole('AUTHOR')")
    public void deleteBook(@PathVariable("title") String title, @PathVariable("author") String author) {
        this.bookService.deleteBook(title, author);
    }

    // PUT request: Update book
    @PutMapping(path = "update/{title}")
//    @PreAuthorize("#book.author == principal.name")
    @PreAuthorize("hasRole('AUTHOR')")
    public void updateBook(@PathVariable("title") String title, @RequestBody Book book) {
        this.bookService.updateBook(title, book);
    }

    // GET request: get user by id
    @GetMapping(path = "search/id/{id}")
    public Book getBookByID(@PathVariable("id") String id) {
        return this.bookService.getBookByID(id);
    }

    // GET request: get collection of books by date
    @GetMapping(path = "search/date/{date}")
    public Map<String, Book> getBookByDate(@PathVariable("date") String date) {
        return this.bookService.getBookByDate(date);
    }

    // Get request: get collection of books by price
    @GetMapping(path = "search/price/{price}")
    public Map<String, Book> getBookByPrice(@PathVariable("price") double price) {
        return this.bookService.getBookByPrice(price);
    }

    // Get request: get all books in Data base
    @GetMapping(path = "search/getAll")
    @PostAuthorize("hasAnyRole('ADMIN','AUTHOR')")
    public Map<String, Book> getAllBooks() {
        return this.bookService.getAllBooks();
    }

    // Get request get All books by an author
    @GetMapping(path = "search/author/{author}")
    public Map<String, Book> getAllBooksByAuthor(@PathVariable("author") String author) {
        return this.bookService.getAllBooksByAuthor(author);
    }
}
