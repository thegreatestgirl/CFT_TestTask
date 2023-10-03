package com.example.cft_testtask;

import com.example.cft_testtask.models.Booking;
import com.example.cft_testtask.models.Report;
import com.example.cft_testtask.models.ReportLine;
import com.example.cft_testtask.repositories.JdbcDataSource;
import com.example.cft_testtask.repositories.MessagesRepository;
import com.example.cft_testtask.repositories.MessagesRepositoryJdbcImpl;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class ReportCreationController implements Initializable {
    @FXML
    private DatePicker byDateInput;

    @FXML
    private Button createReportButton;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField patronymicInput;

    @FXML
    private TableView<ReportLine> reportView;

    @FXML
    private TextField surnameInput;

    @FXML
    private DatePicker withDateInput;

    private void reportTableInitialize() {
        reportView.getColumns().clear();

        TableColumn idCol = new TableColumn<>("id");
        TableColumn bookNameCol = new TableColumn<>("bookName");
        TableColumn authorCol = new TableColumn<>("author");
        TableColumn yearCol = new TableColumn<>("year");
        TableColumn givenDateCol = new TableColumn<>("givenDate");
        TableColumn returnDateCol = new TableColumn<>("returnDate");

        reportView.getColumns().addAll(idCol, bookNameCol, authorCol, yearCol, givenDateCol, returnDateCol);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        givenDateCol.setCellValueFactory(new PropertyValueFactory<>("givenDate"));
        returnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
    }

    private Report getReportParameters() {
        LocalDate localDate = withDateInput.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date withDate = Date.from(instant);

        LocalDate localDate1 = byDateInput.getValue();
        Instant instant1 = Instant.from(localDate1.atStartOfDay(ZoneId.systemDefault()));
        Date byDate = Date.from(instant1);
        return (new Report(surnameInput.getText(), nameInput.getText(),
                patronymicInput.getText(), withDate, byDate));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reportTableInitialize();
    }

    public void onReportCreateClick(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        ReportCreationController controller = loader.getController();
        JdbcDataSource dataSource = new JdbcDataSource();
        MessagesRepository repository = new MessagesRepositoryJdbcImpl(dataSource.getDataSource());
        reportView.setItems(FXCollections.observableList(repository.createReport(getReportParameters())));
    }
}
