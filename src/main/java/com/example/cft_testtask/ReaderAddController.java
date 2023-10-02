package com.example.cft_testtask;

import com.example.cft_testtask.models.Reader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class ReaderAddController {
    @FXML
    private DatePicker dateOfBirthInput;

    @FXML
    private TextField nameINput;

    @FXML
    private TextField patronymicInput;

    @FXML
    private TextField surnameInput;

    @FXML
    private Button readerApplyButton;

    @FXML
    private Button readerCancelButton;

    private Boolean modalResult = false;

    private Integer currentReaderId;

    public Integer getReaderId() {
        return currentReaderId;
    }

    public Reader getReaderProperties() {
        LocalDate localDate = dateOfBirthInput.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        return (new Reader(currentReaderId, surnameInput.getText(), nameINput.getText(), patronymicInput.getText(), date));
    }

    public void setReader(Reader reader) {
        currentReaderId = reader.getId();
        surnameInput.setText(reader.getSurname());
        nameINput.setText(reader.getName());
        patronymicInput.setText(reader.getPatronymic());

        String inputt = reader.getDateOfBirth().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.US );
        LocalDate datee = LocalDate.parse(inputt, formatter);
        dateOfBirthInput.setValue(datee);
    }

    public void onReaderApplyClick(ActionEvent actionEvent) {
        this.modalResult = true;
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void onReaderCancelClick(ActionEvent actionEvent) {
        this.modalResult = false;
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public Boolean getModalResult() {
        return modalResult;
    }
}
