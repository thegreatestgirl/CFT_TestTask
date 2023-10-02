package com.example.cft_testtask.repositories;

import com.example.cft_testtask.models.Message;
import com.example.cft_testtask.Reader;

import java.util.List;
import java.util.Optional;

public interface MessagesRepository {
    List<Reader> getAllReaders();
    //Optional<Message> findById(Long id);
}

