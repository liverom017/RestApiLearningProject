package com.BookstoreLearning.bookstore.bookStore.dao;


import com.BookstoreLearning.bookstore.bookStore.model.Book;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository("fakeBookDao")
public class BookDataAccessService implements BookDao {
    /**
     * Hashmap list of all books.
     * hashing of title provides O(1)
     * look up times for books.
     * Local database
     */
    private static Map<String, Book> DB = new HashMap<>();

    // Cheat method for testing
    public void cheatCode() {
        DB.put("Game of Thrones", new Book("Game of Thrones",
                "George",
                "Book about dragons",
                "Fantasy",
                "12-12-2020",
                "https://imageUrl.com",
                100));

        DB.put("John Wick", new Book("John Wick",
                "John",
                "Book about framing a dude",
                "Action",
                "12-12-2020",
                "https://imageUrl.com",
                22.50));

        DB.put("John Wick2", new Book("John Wick2",
                "Marry",
                "Book about framing a dude",
                "Action",
                "12-12-2020",
                "https://imageUrl.com",
                12.223));

        DB.put("Princess Bride", new Book("Princess Bride",
                "Marry",
                "Book about...",
                "Action",
                "12-12-2020",
                "https://imageUrl.com",
                12.223));

        DB.put("The notebook", new Book("The notebook",
                "Someone",
                "Book about framing a dude",
                "Action",
                "12-12-2020",
                "https://imageUrl.com",
                1.223));
    }

    /**
     * Adds a new book to the database
     *
     * @param book
     * @return
     */
    @Override
    public boolean createBook(Book book) {
        try {
            DB.put(book.getTitle(), book);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Book cannot be added");
        }
    }

    /**
     * @param books
     * @return
     */
    @Override
    public boolean createBook(Book[] books) {
        try {
            for (Book b : books) {
                DB.put(b.getTitle(), b);
            }
            //DB.forEach((key, value) -> System.out.println("Key: "+key+ " " +" value: "+value));
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Unable to create book");
        }
    }

    /**
     * Method to return all books
     * note(Should only be accessible by Admin)
     *
     * @return DB
     */
    @Override
    public Map<String, Book> getAllBooks() {
        return DB;
    }

    /**
     * Gets a book from the hashmap using
     * the key:title
     *
     * @param title
     * @return
     */
    @Override
    public Book getBook(String title) {
        return DB.get(title);
    }


    /**
     * Removes a book from the hashmap
     * using its given title as the key
     *
     * @param title
     * @param author
     */
    @Override
    public void deleteBook(String title, String author) {
        try {
            if (DB.get(title).getAuthor().equals(author)) {
                DB.remove(title);
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("Item already deleted");
        }
    }

    /**
     * Replaces a book with a given title in
     * the hash map
     *
     * @param book
     */


    @Override
    public void updateBook(String title, Book book) {
        /**
         * We to see if the title of the new book matches the title of the book in the DB
         * Also we need to check that the authors match.
         * If the title of the new book is changed we must remove the old version and put
         **/
        try {
            if (book.getAuthor().equals(DB.get(title).getAuthor())) {
                if (DB.get(title).getTitle().equals(book.getTitle())) {
                    DB.replace(book.getTitle(), book);
                } else {
                    DB.remove(DB.get(title).getTitle());
                    DB.put(book.getTitle(), book);
                }
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("Item not in database");
        }
    }

    /**
     * Gets all books from author
     * by filtering the hashmap
     * using a stream pipeline
     *
     * @param author
     * @return
     */
    @Override
    public Map<String, Book> getAllBooksByAuthor(String author) {
        return DB.entrySet()
                .stream()
                .filter(b -> author.equals(b.getValue().getAuthor()))
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
    }

    /**
     * Finds a book by its UUID
     * filters the hashmap using a stream
     *
     * @param id
     * @return
     */
    @Override
    public Book getBookByID(String id) {
        return DB.entrySet()
                .stream()  // For a big stream .parallelStream() may offer performance benefit vs threading allocation cost
                .filter(b -> id.equals(b.getValue().getId()))
                .map(map -> map.getValue())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Book with id: " + id + " does not exist"));
    }

    /**
     * Finds all book from a given publishing date
     * uses a stream to filter the hashmap
     *
     * @param date
     * @return
     */
    @Override
    public Map<String, Book> getBookByDate(String date) {
        return DB.entrySet()
                .stream()
                .filter(b -> date.equals(b.getValue().getPublishDate()))
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
    }

    /**
     * Finds all books by given price
     * Using stream to filter the HashMap
     *
     * @param price
     * @return
     */
    @Override
    public Map<String, Book> getBookByPrice(double price) {
        return DB.entrySet()
                .stream()
                .filter(b -> price == b.getValue().getPrice())
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
    }
}
