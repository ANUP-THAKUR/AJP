package dao;

import entities.Book;

import java.util.List;

public interface BookDao {
    void addBook(Book book);
    List<Book> getAllBooks();
    void deleteBook(int bookId);
}

