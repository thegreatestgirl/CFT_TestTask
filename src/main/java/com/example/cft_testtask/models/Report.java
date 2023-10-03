package com.example.cft_testtask.models;

import java.util.Date;

public class Report {
    String readerSurname, readerName, readerPatronymic;
    Date withDate, byDate;

    public Report(String readerSurname, String readerName, String readerPatronymic,
                   Date withDate, Date byDate) {
        this.readerSurname = readerSurname;
        this.readerName = readerName;
        this.readerPatronymic = readerPatronymic;
        this.withDate = withDate;
        this.byDate = byDate;
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

    public Date getWithDate() {
        return withDate;
    }

    public void setWithDate(Date withDate) {
        this.withDate = withDate;
    }

    public Date getByDate() {
        return byDate;
    }

    public void setByDate(Date byDate) {
        this.byDate = byDate;
    }
}
