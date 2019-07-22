package com.app.api.entity.response;

import java.util.List;

public class ResponseBook extends ResponseEntity {
    private String name;
    private String author;
    private String user;

    public ResponseBook(long id, List<String> msg, String name, String author, String user) {
        super(id, msg);
        this.name = name;
        this.author = author;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
