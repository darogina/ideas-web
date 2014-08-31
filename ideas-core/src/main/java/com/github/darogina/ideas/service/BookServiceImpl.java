package com.github.darogina.ideas.service;

import com.github.darogina.ideas.entity.BookEntity;
import com.github.darogina.ideas.repository.BookRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named("bookService")
public class BookServiceImpl extends AbstractCrudService<BookEntity, Long, BookRepository> implements BookService {

    @Override @Inject @Named("bookRepository")
    public void setRepository(BookRepository repository) {
        this.repository = repository;
    }
}
