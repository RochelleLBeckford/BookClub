package com.rochelle.book_club.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rochelle.book_club.models.Book;

@Repository
// give it the model we are keeping track of and it's id type
public interface BookRepository extends CrudRepository<Book, Long>{
    // need to overide the findAll
    List<Book> findAll();
}
