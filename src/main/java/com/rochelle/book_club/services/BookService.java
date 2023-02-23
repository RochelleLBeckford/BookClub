package com.rochelle.book_club.services;

import java.util.List;
import java.util.Optional;

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


    // ^ Create ^
    // allow my controller to talk to my DB 
    // -> make whatever methods we want for whatever CRUD operations we want 
    public void addBook(Book book) {
        // what do i need to pass along to my service -> call on repo to perform this operation
        bookRepository.save(book);
    }


    // ^ Read All ^
    // method that will link to repo
    public List<Book> getAll() {
        return bookRepository.findAll();
    }


    // ^ Read One ^
    // get one book
    public Book getOneBook(Long id) {
        // the service talks to the repo
        // pass in an id where there is no record in our DB of our id -> use Optional
        Optional<Book> optionalBook = bookRepository.findById(id);
        // instead of returning null -> want to return that book if it comes back
        // return null;
        // if there is no book with that id -> .orElse(null)
        return optionalBook.orElse(null);
    }

    // ^ Update ^
    public void updateBook(Book book) {
        bookRepository.save(book);
    }


    // ^ Delete ^
    // send the request to delete
    public void obliterateBook(Long id) {
        bookRepository.deleteById(id);
    }
}
