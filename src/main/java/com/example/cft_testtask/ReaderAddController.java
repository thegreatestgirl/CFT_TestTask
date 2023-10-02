package com.example.cft_testtask;

import com.example.cft_testtask.models.Reader;
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
import java.util.Date;

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

    public Reader getReader() {
        LocalDate localDate = dateOfBirthInput.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        return (new Reader(surnameInput.getText(), nameINput.getText(), patronymicInput.getText(), date));
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
