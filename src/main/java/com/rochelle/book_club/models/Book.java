package com.rochelle.book_club.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// Establish a book as an item going to track in our Application -> need to link this to my DB
@Entity
@Table(name = "books")
public class Book {
// need `@Entity` and `@Table` `@Id` `@GeneratedValue` to the model to connect to my DB
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title ;
    private String author;
    private String myThoughts;

    // how to show that a user has many books and that a book has one user
    // -> from book it would be many to one
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    // a book belongs to a user
    private User user;
    // how to link my user to a book.

    // need the No-arg constructor
    // Generate an empty constructor
    public Book() {
    }

    // Generate constructor using all fields
    public Book(Long id, String title, String author, String myThoughts, User user) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.myThoughts = myThoughts;
        this.user = user;
    }

    // Generate getters and setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMyThoughts() {
        return this.myThoughts;
    }

    public void setMyThoughts(String myThoughts) {
        this.myThoughts = myThoughts;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //* Note -> note that I have a book I can create a new book
    // -> can send down an empty book down to my new book page 

}
