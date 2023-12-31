package com.example.cft_testtask.models;

public class Book extends Basic {
    Integer id, year;
    String name, author;

    public Book(Integer id, String name, String author, Integer year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
