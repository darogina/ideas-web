package com.github.darogina.ideas.model.api.v1;

public class Author extends BaseModel {

    private String name;
//    private Set<Book> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//
//    public Set<Book> getBooks() {
//        return books;
//    }
//
//    public void setBooks(Set<Book> books) {
//        this.books = books;
//    }
}
