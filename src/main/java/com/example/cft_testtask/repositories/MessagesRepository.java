package com.example.cft_testtask.repositories;

import com.example.cft_testtask.Book;
import com.example.cft_testtask.Booking;
import com.example.cft_testtask.Reader;

import java.util.List;
import java.util.Optional;

public interface MessagesRepository {
    List<Reader> getAllReaders();
    List<Book> getAllBooks();
    List<Booking> getAllBookings();
    //Optional<Message> findById(Long id);
}

