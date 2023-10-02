package com.example.cft_testtask.repositories;

import com.example.cft_testtask.models.Book;
import com.example.cft_testtask.models.Booking;
import com.example.cft_testtask.models.Reader;

import java.util.List;

public interface MessagesRepository {
    List<Reader> getAllReaders();
    List<Book> getAllBooks();
    List<Booking> getAllBookings();
    //Optional<Message> findById(Long id);
}

