package com.example.cft_testtask.models;

import java.util.Date;

public class ReportLine extends Basic {
    Integer id, year;
    String bookName, author;
    Date givenDate, returnDate;

    public ReportLine(Integer id, String bookName, String author, Integer year, Date givenDate, Date returnDate) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.year = year;
        this.givenDate = givenDate;
        this.returnDate = returnDate;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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

    public Date getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(Date givenDate) {
        this.givenDate = givenDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
