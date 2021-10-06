package com.BookstoreLearning.bookstore.bookStore.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Book {
    private static int idCounter = 0;
    // Attributes
    private String id;
    private String title;
    private String author;
    private String catagory;
    private String description;
    private String publishDate;
    private String coverImageURl;
    private double price;

    //constructor
    public Book(@JsonProperty("title") String title, @JsonProperty("author") String author,
                @JsonProperty("description") String description, @JsonProperty("catagory") String catogory,
                @JsonProperty("publishDate") String publishDate, @JsonProperty("coverImageURl") String coverImageURl,
                @JsonProperty("price") double price) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.catagory = catogory;
        this.description = description;
        this.publishDate = publishDate;
        this.coverImageURl = coverImageURl;
        this.price = price;
    }

    //Getter & Setter Methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getCoverImageURl() {
        return coverImageURl;
    }

    public void setCoverImageURl(String coverImageURl) {
        this.coverImageURl = coverImageURl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
