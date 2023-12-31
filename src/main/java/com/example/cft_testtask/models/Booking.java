package com.example.cft_testtask.models;

import java.util.Date;

public class Booking extends Basic {
    Integer id;
    String readerSurname, readerName, readerPatronymic, bookName;
    Date givenDate, returnDate;

    public Booking(Integer id, String readerSurname, String readerName, String readerPatronymic,
                   String bookName, Date givenDate, Date returnDate) {
        this.id = id;
        this.readerSurname = readerSurname;
        this.readerName = readerName;
        this.readerPatronymic = readerPatronymic;
        this.bookName = bookName;
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

    public String getReaderSurname() {
        return readerSurname;
    }

    public void setReaderSurname(String readerSurname) {
        this.readerSurname = readerSurname;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getReaderPatronymic() {
        return readerPatronymic;
    }

    public void setReaderPatronymic(String readerPatronymic) {
        this.readerPatronymic = readerPatronymic;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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
