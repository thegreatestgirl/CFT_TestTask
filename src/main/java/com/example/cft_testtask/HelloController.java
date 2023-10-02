package com.example.cft_testtask;

import com.example.cft_testtask.models.Basic;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.example.cft_testtask.repositories.*;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class HelloController implements Initializable {
    @FXML
    private Button addReaderButton;

    @FXML
    private Button updateReaderButton;

    @FXML
    private Button deleteReaderButton;

    @FXML
    private TableView<Reader> readersView;

    @FXML
    private TableView<Book> booksView;

    @FXML
    private TableView<Booking> bookingsView;

    @FXML
    private Tab bookedBooksTab;

    @FXML
    private Tab booksTab;

    @FXML
    private Tab readersTab;

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

    public void addReader(FXMLLoader loader) throws IOException {
        ReaderAddController controller = loader.getController();
        if (controller.getModalResult()) {
            JdbcDataSource dataSource = new JdbcDataSource();
            MessagesRepository repository = new MessagesRepositoryJdbcImpl(dataSource.getDataSource());
            repository.addNewReader(controller.getReaderProperties());
            readersView.setItems(FXCollections.observableList(repository.getAllReaders()));
        }
    }

    public void addBook(FXMLLoader loader) throws IOException {
        BookAddController controller = loader.getController();
        if (controller.getModalResult()) {
            JdbcDataSource dataSource = new JdbcDataSource();
            MessagesRepository repository = new MessagesRepositoryJdbcImpl(dataSource.getDataSource());
            repository.addNewBook(controller.getBookProperties());
            booksView.setItems(FXCollections.observableList(repository.getAllBooks()));
        }
    }

    public void addBooking(FXMLLoader loader) throws IOException {
        BookingAddController controller = loader.getController();
        if (controller.getModalResult()) {
            JdbcDataSource dataSource = new JdbcDataSource();
            MessagesRepository repository = new MessagesRepositoryJdbcImpl(dataSource.getDataSource());
            repository.addNewBooking(controller.getBookingProperties());
            bookingsView.setItems(FXCollections.observableList(repository.getAllBookings()));
        }
    }

    public void updateReader(FXMLLoader loader, Stage stage) throws IOException {
        ReaderAddController controller = loader.getController();
        controller.setReader((Reader) readersView.getSelectionModel().getSelectedItem());

        stage.showAndWait();

        if (controller.getModalResult()) {
            JdbcDataSource dataSource = new JdbcDataSource();
            MessagesRepository repository = new MessagesRepositoryJdbcImpl(dataSource.getDataSource());
            repository.updateReader(controller.getReaderProperties());
            readersView.setItems(FXCollections.observableList(repository.getAllReaders()));
        }
    }

    public void updateBook(FXMLLoader loader, Stage stage) throws IOException {
        BookAddController controller = loader.getController();
        controller.setBook((Book) booksView.getSelectionModel().getSelectedItem());

        stage.showAndWait();

        if (controller.getModalResult()) {
            JdbcDataSource dataSource = new JdbcDataSource();
            MessagesRepository repository = new MessagesRepositoryJdbcImpl(dataSource.getDataSource());
            repository.updateBook(controller.getBookProperties());
            booksView.setItems(FXCollections.observableList(repository.getAllBooks()));
        }
    }

    public void onDeleteClick(ActionEvent actionEvent) {
        Basic basic;
        if (readersTab.isSelected()) {
            basic = (Reader)readersView.getSelectionModel().getSelectedItem();
        } else if (booksTab.isSelected()) {
            basic = (Book)booksView.getSelectionModel().getSelectedItem();
        } else {
            basic = (Booking)bookingsView.getSelectionModel().getSelectedItem();
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText(String.format("Delete?"));

        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            JdbcDataSource dataSource = new JdbcDataSource();
            MessagesRepository repository = new MessagesRepositoryJdbcImpl(dataSource.getDataSource());
            if (readersTab.isSelected()) {
                repository.deleteItemById(basic.getId(), "readers");
                readersView.getItems().remove(basic);
            } else if (booksTab.isSelected()) {
                repository.deleteItemById(basic.getId(), "books");
                booksView.getItems().remove(basic);
            } else if (bookedBooksTab.isSelected()) {
                repository.deleteItemById(basic.getId(), "booked_books");
                bookingsView.getItems().remove(basic);
            }


        }
    }

    public void onAddClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        if (readersTab.isSelected()) {
            loader.setLocation(getClass().getResource("readerAdd.fxml"));
        } else if (booksTab.isSelected()) {
            loader.setLocation(getClass().getResource("bookAdd.fxml"));
        } else {
            loader.setLocation(getClass().getResource("bookingAdd.fxml"));
        }
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.readersView.getScene().getWindow());
        stage.showAndWait();

        if (readersTab.isSelected()) {
            addReader(loader);
        } else if (booksTab.isSelected()) {
            addBook(loader);
        } else {
            addBooking(loader);
        }
    }

    public void onUpdateClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        if (readersTab.isSelected()) {
            loader.setLocation(getClass().getResource("readerAdd.fxml"));
        } else if (booksTab.isSelected()) {
            loader.setLocation(getClass().getResource("bookAdd.fxml"));
        } else {
            loader.setLocation(getClass().getResource("bookingAdd.fxml"));
        }
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(readersView.getScene().getWindow());


        if (readersTab.isSelected()) {
            updateReader(loader, stage);
        } else if (booksTab.isSelected()) {
            updateBook(loader, stage);
        } else {

        }
    }
}