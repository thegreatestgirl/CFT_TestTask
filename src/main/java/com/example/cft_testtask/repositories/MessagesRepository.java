package com.example.cft_testtask.repositories;

import com.example.cft_testtask.models.*;

import java.util.List;

public interface MessagesRepository {
    List<Reader> getAllReaders();
    List<Book> getAllBooks();
    List<Booking> getAllBookings();

    Integer getLastIdAtTable();

    void addNewReader(Reader newReader);
    void addNewBook(Book newBook);
    void addNewBooking(Booking newBooking);
    void updateReader(Reader updatedReader);
    void updateBook(Book updatedBook);
    void updateBooking(Booking updatedBooking);
    void deleteItemById(Integer valId, String table);
    List<ReportLine> createReport(Report report);
}

