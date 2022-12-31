package com.example.booklist;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Book implements Serializable {
    int id;
    String title;
    String author;
    int pages;
    String released;
    String publishingHouse;
    String genre;
    double rating;
    int numberOfRatings;
    String translator;
    Site site;
    SimilarBook similarBook;
    String image;

    public Book(int id, String title, String author, int pages, String released, String publishingHouse, String genre, double rating, int numberOfRatings, String translator, Site site, SimilarBook similarBook, String image) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.released = released;
        this.publishingHouse = publishingHouse;
        this.genre = genre;
        this.rating = rating;
        this.numberOfRatings = numberOfRatings;
        this.translator = translator;
        this.site = site;
        this.similarBook = similarBook;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public SimilarBook getSimilarBook() {
        return similarBook;
    }

    public void setSimilarBook(SimilarBook similarBook) {
        this.similarBook = similarBook;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
