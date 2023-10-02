package com.example.cft_testtask;

import com.example.cft_testtask.models.Book;
import com.example.cft_testtask.models.Reader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class BookAddController {
    @FXML
    private TextField authorInput;

    @FXML
    private Button bookApplyButton;

    @FXML
    private Button bookCancelButton;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField yearInput;

    private Boolean modalResult = false;

    private Integer currentBookId;

    public Integer getBookId() {
        return currentBookId;
    }

    public Book getBookProperties() {
        return (new Book(currentBookId, nameInput.getText(), authorInput.getText(), Integer.parseInt(yearInput.getText().toString())));
    }

    public void setBook(Book book) {
        currentBookId = book.getId();
        nameInput.setText(book.getName());
        authorInput.setText(book.getAuthor());
        yearInput.setText(book.getYear().toString());
    }

    public void onBookApplyClick(ActionEvent actionEvent) {
        this.modalResult = true;
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void onBookCancelClick(ActionEvent actionEvent) {
        this.modalResult = false;
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public Boolean getModalResult() {
        return modalResult;
    }
}
