package com.github.darogina.ideas.assembler.api.v1;

import com.github.darogina.ideas.assembler.api.GenericResourceAssemler;
import com.github.darogina.ideas.controller.api.v1.AuthorsAPIv1Controller;
import com.github.darogina.ideas.entity.AuthorEntity;
import com.github.darogina.ideas.model.api.v1.Author;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Named("authorResourceAssembler")
public class AuthorResourceAssembler extends GenericResourceAssemler<AuthorsAPIv1Controller, AuthorEntity, Author> {

    private ModelMapper modelMapper;

    public AuthorResourceAssembler() {
        super(AuthorsAPIv1Controller.class, Author.class);
    }

    @Override
    public Author toResource(AuthorEntity entity) {
        // will add also a link with rel self pointing itself
        Author resource = createResourceWithId(entity.getId(), entity);

        // adding a link with rel books pointing to the author's books
        resource.add(linkTo(methodOn(AuthorsAPIv1Controller.class).findBooks(entity.getId())).withRel("books"));

        return resource;
    }

    @Override
    protected Author instantiateResource(AuthorEntity entity) {
        return entity == null ? null : modelMapper.map(entity, Author.class);
    }

    @PostConstruct
    private void init() {
        modelMapper = new ModelMapper();
    }
}
