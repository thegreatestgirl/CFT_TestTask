package com.example.cft_testtask;

import com.example.cft_testtask.models.Book;
import com.example.cft_testtask.models.Booking;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class BookingAddController {
    @FXML
    private TextField bookNameInput;

    @FXML
    private Button bookingApplyButton;

    @FXML
    private Button bookingCancelButton;

    @FXML
    private DatePicker givenDateInput;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField patronymicInput;

    @FXML
    private DatePicker returnDateInput;

    @FXML
    private TextField surnameInput;

    private Boolean modalResult = false;

    private Integer currentBookingId;

    public Integer getBookingId() {
        return currentBookingId;
    }

    public Booking getBookingProperties() {
        LocalDate localDate = givenDateInput.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date givenDate = Date.from(instant);

        LocalDate localDate1 = returnDateInput.getValue();
        Instant instant1 = Instant.from(localDate1.atStartOfDay(ZoneId.systemDefault()));
        Date returnDate = Date.from(instant1);
        return (new Booking(currentBookingId, surnameInput.getText(), nameInput.getText(),
                patronymicInput.getText(), bookNameInput.getText(),
                givenDate, returnDate));
    }

    public void setBooking(Booking booking) {
        currentBookingId = booking.getId();
        surnameInput.setText(booking.getReaderSurname());
        nameInput.setText(booking.getReaderName());
        patronymicInput.setText(booking.getReaderPatronymic());
        bookNameInput.setText(booking.getBookName());

        String inputt = booking.getGivenDate().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.US );
        LocalDate datee = LocalDate.parse(inputt, formatter);
        givenDateInput.setValue(datee);

        String input = booking.getReturnDate().toString();
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formater = formater.withLocale( Locale.US );
        LocalDate date = LocalDate.parse(input, formater);
        returnDateInput.setValue(date);
    }

    public void onBookingApplyClick(ActionEvent actionEvent) {
        this.modalResult = true;
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void onBookingCancelClick(ActionEvent actionEvent) {
        this.modalResult = false;
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public Boolean getModalResult() {
        return modalResult;
    }
}
