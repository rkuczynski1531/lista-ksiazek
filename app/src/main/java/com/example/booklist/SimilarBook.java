package com.example.booklist;

import java.io.Serializable;

public class SimilarBook implements Serializable {
    String title;
    String author;

    public SimilarBook(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
