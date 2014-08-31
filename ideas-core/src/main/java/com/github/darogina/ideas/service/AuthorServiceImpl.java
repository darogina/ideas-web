package com.github.darogina.ideas.service;

import com.github.darogina.ideas.entity.AuthorEntity;
import com.github.darogina.ideas.repository.AuthorRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named("authorService")
public class AuthorServiceImpl extends AbstractCrudService<AuthorEntity, Long, AuthorRepository> implements AuthorService {

    @Override @Inject @Named("authorRepository")
    public void setRepository(AuthorRepository repository) {
        this.repository = repository;
    }
}
