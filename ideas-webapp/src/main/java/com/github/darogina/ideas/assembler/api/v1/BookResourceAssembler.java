package com.github.darogina.ideas.assembler.api.v1;

import com.github.darogina.ideas.assembler.api.GenericResourceAssemler;
import com.github.darogina.ideas.controller.api.v1.AuthorsAPIv1Controller;
import com.github.darogina.ideas.controller.api.v1.BooksAPIv1Controller;
import com.github.darogina.ideas.entity.BookEntity;
import com.github.darogina.ideas.model.api.v1.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component("bookResourceAssembler")
public class BookResourceAssembler extends GenericResourceAssemler<BooksAPIv1Controller, BookEntity, Book> {

    private ModelMapper modelMapper;

    public BookResourceAssembler() {
        super(BooksAPIv1Controller.class, Book.class);
    }

    @Override
    public Book toResource(BookEntity entity) {
        // will add also a link with rel self pointing itself
        Book resource = createResourceWithId(entity.getId(), entity);

        // adding a link with rel author pointing to the book's author
        resource.add(linkTo(AuthorsAPIv1Controller.class).slash(entity.getAuthor().getId()).withRel("author"));

        return resource;
    }

    @Override
    protected Book instantiateResource(BookEntity entity) {
        return entity == null ? null : modelMapper.map(entity, Book.class);
    }

    @PostConstruct
    private void init() {
        modelMapper = new ModelMapper();
    }
}
