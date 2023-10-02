package com.example.cft_testtask;

import com.example.cft_testtask.models.Book;
import com.example.cft_testtask.models.Booking;
import com.example.cft_testtask.models.Reader;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.cft_testtask.repositories.*;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class HelloController implements Initializable {
    @FXML
    private Button addReaderButton;

    @FXML
    private TableView<Reader> readersView;

    @FXML
    private TableView<Book> booksView;

    @FXML
    private TableView<Booking> bookingsView;

    private void readersTableInitialize() {
        readersView.getColumns().clear();

        TableColumn idCol = new TableColumn<>("id");
        TableColumn surnameCol = new TableColumn<>("surname");
        TableColumn nameCol = new TableColumn<>("name");
        TableColumn patronymicCol = new TableColumn<>("patronymic");
        TableColumn dateOfBirthCol = new TableColumn<>("dateOfBirth");

        readersView.getColumns().addAll(idCol, surnameCol, nameCol, patronymicCol, dateOfBirthCol);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        patronymicCol.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
    }

    private void booksTableInitialize() {
        booksView.getColumns().clear();

        TableColumn idCol = new TableColumn<>("id");
        TableColumn nameCol = new TableColumn<>("name");
        TableColumn authorCol = new TableColumn<>("author");
        TableColumn yearCol = new TableColumn<>("year");

        booksView.getColumns().addAll(idCol, nameCol, authorCol, yearCol);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
    }

    private void bookingsTableInitialize() {
        bookingsView.getColumns().clear();

        TableColumn idCol = new TableColumn<>("id");
        TableColumn readerSurnameCol = new TableColumn<>("readerSurname");
        TableColumn readerNameCol = new TableColumn<>("readerName");
        TableColumn readerPatronymicCol = new TableColumn<>("readerPatronymic");
        TableColumn bookNameCol = new TableColumn<>("bookName");
        TableColumn givenDateCol = new TableColumn<>("givenDate");
        TableColumn returnDateCol = new TableColumn<>("returnDate");

        bookingsView.getColumns().addAll(idCol, readerSurnameCol, readerNameCol, readerPatronymicCol, bookNameCol, givenDateCol, returnDateCol);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        readerSurnameCol.setCellValueFactory(new PropertyValueFactory<>("readerSurname"));
        readerNameCol.setCellValueFactory(new PropertyValueFactory<>("readerName"));
        readerPatronymicCol.setCellValueFactory(new PropertyValueFactory<>("readerPatronymic"));
        bookNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        givenDateCol.setCellValueFactory(new PropertyValueFactory<>("givenDate"));
        returnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        readersTableInitialize();
        booksTableInitialize();
        bookingsTableInitialize();

        JdbcDataSource dataSource = new JdbcDataSource();
        MessagesRepository repository = new MessagesRepositoryJdbcImpl(dataSource.getDataSource());
        readersView.setItems(FXCollections.observableList(repository.getAllReaders()));
        booksView.setItems(FXCollections.observableList(repository.getAllBooks()));
        bookingsView.setItems(FXCollections.observableList(repository.getAllBookings()));
    }

    public void onAddReaderClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("readerAdd.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.readersView.getScene().getWindow());
        stage.showAndWait();

        ReaderAddController controller = loader.getController();
        if (controller.getModalResult()) {
            JdbcDataSource dataSource = new JdbcDataSource();
            MessagesRepository repository = new MessagesRepositoryJdbcImpl(dataSource.getDataSource());
            repository.addNewReader(controller.getReader());
            readersView.setItems(FXCollections.observableList(repository.getAllReaders()));
            // добавляем в список
            //this.foodList.add(newFood);
        }
    }
}