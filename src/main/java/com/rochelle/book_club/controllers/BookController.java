package com.rochelle.book_club.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.rochelle.book_club.models.Book;
import com.rochelle.book_club.services.BookService;
import com.rochelle.book_club.services.UserService;

// designate this as Controllers
// the utilizes the service
@Controller
public class BookController {
    // now to hook up my controller to my service
            // this the type 
                            // this is the variable 
    @Autowired BookService bookService;
    // added this get user
    @Autowired UserService userService;

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

    // for validations -> need to pass in @Valid and BindingResult results
    public String createBook(@Valid @ModelAttribute("book")Book book, BindingResult results) {
        //~ musst do the checks first
        // need to do an if statement for validations
            // built into Binding result object and will retunr a boolean if there are errors
        if (results.hasErrors()) {
            return "books/new.jsp";
        } else {

            // controller talks to the service
            // service talks to the repo
            // take the book from that form and pass it on to my DB 
            bookService.addBook(book);
        }

        return "redirect:/books";
    }
    // when create a book can check DB in MySQL to see if it worked


    // ^ Read All ^
    @GetMapping("/books")
    // need model to get books to index.jsp
    public String allBooks(Model model, HttpSession session) {
        // always at the top -> do not want anyone who is not the current user to see the delete or edit do the below
        // -> check the session -> redirect them to the logout
        // -> have it on the home page -> /books
        // * Note -> Black Belt -> if not the owner -> should not see the edit page nor delete it 
            // -> only for the owner

        // if no userId is found, redirect to log out
        // should not be able to type in /books on the browser and get in when you are not logged in
        if(session.getAttribute("userId") == null){
            return "redirect:/logout";
        }


        // getActiveUser -> get user id from session
        Long id = (Long)session.getAttribute("userId");
        model.addAttribute("userName", userService.getActiveUser(id).getUserName());

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
    // get mapping -> b/c getting the book
    @GetMapping("/books/{id}")
    // id will come in on our path so we need path variables 
    // this path variable is going to be a type Long and call it id
    public String show(@PathVariable("id")Long id, Model model) {
        // where do we use that id to get our book from the DB -> sevice
        // from DB getting one book object from our service
        // pass in the id that i get from my url
        Book book = bookService.getOneBook(id);
                                // going to be a book and passing down a book
        model.addAttribute("book", book);
        return "books/show.jsp";
    }
    // now have my route need my template -> go to books folder in webapp and create show.jsp 
    // -> copy index.jsp since it is similar and paste it -> change name to show.jsp


    // ^ Update ^
    // rendering the form is get mapping
    @GetMapping("/books/edit/{id}")
    // this route is editing the form
    public String edit(@PathVariable("id")Long id, Model model) {
        /* 
        -> the edit page is often similar to the new page so copy it and change a few things
        -> samething as read one since need to view one burger to edit it
        -> so can use that code as well
        */
                // where do we use that id to get our book from the DB -> sevice
        // from DB getting one book object from our service
        // pass in the id that i get from my url
        Book book = bookService.getOneBook(id);
                                // going to be a book and passing down a book
        model.addAttribute("book", book);
        return "books/edit.jsp";
    }

    // need to made route that will handle editing the book
    // need to update and not create another book
    // This is the route it to handle the edit -> send the book
    @PutMapping("/books/{id}")
    // this route is updating the form/ handling the form
    public String update(@Valid @ModelAttribute("book")Book book, BindingResult results) {
        //~ musst do the checks first
        // need to do an if statement for validations
        // builtd into Binding result object and will retunr a boolean if there are errors
        if (results.hasErrors()) {
            return "books/edit.jsp";
        } else {

            // controller talks to the service
            // service talks to the repo
            // take the book from that form and pass it on to my DB 
            // just make sure to make a different functions name
                            // pass down the book that i want to update
            bookService.updateBook(book);
        }
        // want to redirect it /books -> index.jsp for books
        return "redirect:/books";
    }


    // ^ Delete ^
    @DeleteMapping("/books/{id}")
    public String obliterate(@PathVariable("id")Long id) {
        // get to DB
        // Pass in id -> have to use something special with delete
        /* 
        ~ this goes on my show page            
        circumvent the limitation of our browser by adding the hidden input
            <form action="/books/${book.id}" method="post">
                <input type="hidden" name="_method" value="delete">
                <input class="btn btn-danger btn-sm" type="submit" value="Delete">
            </form>
        */
        bookService.obliterateBook(id);
        return "redirect:/books";
    }


}
