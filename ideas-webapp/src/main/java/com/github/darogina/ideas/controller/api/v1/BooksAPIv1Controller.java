package com.github.darogina.ideas.controller.api.v1;

import com.github.darogina.ideas.assembler.api.v1.BookResourceAssembler;
import com.github.darogina.ideas.controller.ServiceBasedRestController;
import com.github.darogina.ideas.entity.BaseEntity;
import com.github.darogina.ideas.entity.BookEntity;
import com.github.darogina.ideas.model.api.v1.Book;
import com.github.darogina.ideas.service.BookService;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.inject.Named;

@Controller
@ExposesResourceFor(Book.class)
@RequestMapping(value = "/api/v1/books")
public class BooksAPIv1Controller extends ServiceBasedRestController<Book, Long, BookService, BookResourceAssembler> {

    @Override @Inject @Named("bookService")
    protected void setService(BookService service) {
        this.service = service;
    }

    @Override @Inject @Named("bookResourceAssembler")
    protected void setResourceAssembler(BookResourceAssembler resourceAssembler) {
        this.resourceAssembler = resourceAssembler;
    }

    @Override
    protected BaseEntity toEntity(Book model) {
        if (model == null) {
            return null;
        }

        return modelMapper.map(model, BookEntity.class);
    }

}
