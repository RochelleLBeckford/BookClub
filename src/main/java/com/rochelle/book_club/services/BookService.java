package com.rochelle.book_club.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rochelle.book_club.models.Book;
import com.rochelle.book_club.repositories.BookRepository;

@Service
public class BookService {

    // now to hook up my service to my repo
            // this the type 
                            // this is the variable 
    @Autowired BookRepository bookRepository;

    // allow my controller to talk to my DB 
    // -> make whatever methods we want for whatever CRUD operations we want 
    public void addBook(Book book) {
        // what do i need to pass along to my service -> call on repo to perform this operation
        bookRepository.save(book);

    }

    // method that will link to repo
    public List<Book> getAll() {
        return bookRepository.findAll();
    }
}
