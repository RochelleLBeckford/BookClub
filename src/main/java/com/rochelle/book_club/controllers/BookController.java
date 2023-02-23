package com.rochelle.book_club.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rochelle.book_club.models.Book;
import com.rochelle.book_club.services.BookService;

// designate this as Controllers
// the utilizes the service
@Controller
public class BookController {
    // now to hook up my controller to my service
            // this the type 
                            // this is the variable 
    @Autowired BookService bookService;

    // need a route that is going to take me to /books
    // ^ Create Book^
    @GetMapping("/books/new")
    // how to get my modelAttribute to work -> so that I have an empty book to fill out on this page
                        // creating something have to include this
    public String newBook(@ModelAttribute("book")Book book) {
        return "books/new.jsp";
    }
    // need to make a post mapping to handle my form
    // complimentary method that is going to go with this

    @PostMapping("/books")
    // us the modelattribute to access the book that we made in our form
    // need this in both methods that reference it
        // need this in this method the references the book that has been completed in the form
    public String createBook(@ModelAttribute("book")Book book) {
        // controller talks to the service
        // service talks to the repo
        // take the book from that form and pass it on to my DB 
        bookService.addBook(book);
        return "redirect:/books";
    }
    // when creat a book can check DB in MySQL to see if it worked

    // ^ Read All ^

    @GetMapping("/books")
    // need model to get books to index.jsp
    public String allBooks(Model model) {
        // how to get all books in DB to index.jsp
        // i need to save this in a variable
        // this is going to be a list of books
        List<Book> books = bookService.getAll();
        // get books to index.jsp
        model.addAttribute("books", books);

        // or simplier way but like the thorough way -> just directly adding it to the model
        // would just beed the below and not line 56 & 58
        // model.addAttribute("books", bookService.getAll());
        return "books/index.jsp";
    }
    // now if go to books index page will see if all my books are there through a test 
    // -> ${books} at the top of the page -> and it works

    // need to make a route for show -> Read One
    // ^ Read One ^
    // get mapping -> b/c getting the list of books
    @GetMapping("/books/{id}");
    public String show() {
        return "books/show.jsp";
    }
    // now have my route need my template -> go to books folder in webapp and create show.jsp 
    // -> 

}
