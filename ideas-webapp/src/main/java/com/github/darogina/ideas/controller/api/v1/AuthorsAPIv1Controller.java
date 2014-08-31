package com.github.darogina.ideas.controller.api.v1;

import com.github.darogina.ideas.assembler.api.v1.AuthorResourceAssembler;
import com.github.darogina.ideas.assembler.api.v1.BookResourceAssembler;
import com.github.darogina.ideas.controller.ServiceBasedRestController;
import com.github.darogina.ideas.entity.AuthorEntity;
import com.github.darogina.ideas.entity.BaseEntity;
import com.github.darogina.ideas.exception.NotFoundException;
import com.github.darogina.ideas.model.api.v1.Author;
import com.github.darogina.ideas.model.api.v1.Book;
import com.github.darogina.ideas.service.AuthorService;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.inject.Named;

@Controller
@ExposesResourceFor(Author.class)
@RequestMapping(value = "/api/v1/authors")
@ControllerAdvice
public class AuthorsAPIv1Controller extends ServiceBasedRestController<Author, Long, AuthorService, AuthorResourceAssembler> {

    @Inject @Named("bookResourceAssembler")
    private BookResourceAssembler bookResourceAssembler;

    @Override
    @Inject
    @Named("authorService")
    protected void setService(AuthorService service) {
        this.service = service;
    }

    @Override
    @Inject
    @Named("authorResourceAssembler")
    protected void setResourceAssembler(AuthorResourceAssembler resourceAssembler) {
        this.resourceAssembler = resourceAssembler;
    }

    @Override
    protected BaseEntity toEntity(Author model) {
        if (model == null) {
            return null;
        }

        return modelMapper.map(model, AuthorEntity.class);
    }

    @RequestMapping(value = "/{authorId}/books", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Book> findBooks(@PathVariable Long authorId) {
        AuthorEntity author = service.findById(authorId);

        if (author == null) {
            throw new NotFoundException();
        }

        return bookResourceAssembler.toResources(author.getBooks());
    }

}