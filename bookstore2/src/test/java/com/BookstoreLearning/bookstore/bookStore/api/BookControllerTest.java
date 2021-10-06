package com.BookstoreLearning.bookstore.bookStore.api;

import com.BookstoreLearning.bookstore.BookstoreApplication;
import com.BookstoreLearning.bookstore.bookStore.model.Book;
import com.BookstoreLearning.bookstore.bookStore.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebAppConfiguration
@ContextConfiguration(classes = BookstoreApplication.class)
@WebMvcTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

//    @MockBean
//    private BookController bookController;

    @Test
    void createBook() {
    // To Do assert that POST request creates a book

    }

    @Test
    void testCreateBook() {
        // To Do assert that Test POST request creates a book
    }

    @Test
    void getBook() {
        // To Do assert that GET request returns the requested book based on the title
//        mvc.perform(post("api/book/"))
//                .andDo(print())
//                .andExpect(new Book({
//                        "John Wick2",
//                "John",
//                "Book about that same assassin",
//                "Action",
//                "12-12-2020",
//                "https://imageUrl.com",
//                "price":1122.50
//                });
    }

    @Test
    void deleteBook() {
        // To Do assert that DELETE request deletes the correct book in the hashMap and that the author is the books owner
    }

    @Test
    void updateBook() {
        // To Do assert that PUT request correctly updates the correct book and the author is the owner
    }

    @Test
    void getBookByID() {
        // To Do assert that GET request returns the correct book by ID
    }

    @Test
    void getBookByDate() {
        // To Do assert that returned collection only contains books that have specific publishDate.
    }

    @Test
    void getBookByPrice() {
        // To Do assert that GET request returns collection containing only books of certain price
    }

    @Test
    void getAllBooks() {
        // To Do assert that GET request returns collection of all books in the database
    }

    @Test
    void getAllBooksByAuthor() {
        // To Do assert that GET request returns collection of all books in database by a given author
    }
}