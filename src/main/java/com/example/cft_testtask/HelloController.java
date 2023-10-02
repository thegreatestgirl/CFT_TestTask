package com.example.cft_testtask;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.cft_testtask.repositories.*;


public class HelloController implements Initializable {

    @FXML
    private TableView<Reader> tblView;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblView.getColumns().clear();
        TableColumn idCol = new TableColumn<>("id");
        TableColumn surnameCol = new TableColumn<>("surname");
        TableColumn nameCol = new TableColumn<>("name");
        TableColumn patronymicCol = new TableColumn<>("patronymic");
        TableColumn dateOfBirthCol = new TableColumn<>("dateOfBirth");

        tblView.getColumns().addAll(idCol, surnameCol, nameCol, patronymicCol, dateOfBirthCol);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        patronymicCol.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        //patronymicCol.setCellValueFactory(new PropertyValueFactory<>("patronymic"));

        JdbcDataSource dataSource = new JdbcDataSource();
        MessagesRepository repository = new MessagesRepositoryJdbcImpl(dataSource.getDataSource());
        tblView.setItems(FXCollections.observableList(repository.getAllReaders()));

//
//        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        //nameCol.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        //passCol.setCellValueFactory(new PropertyValueFactory<User, String>("password"));

//        tblView.getColumns().addAll(idCol, nameCol, passCol);
//        tblView.setItems(ou);
//        JdbcDataSource dataSource = new JdbcDataSource();
//        MessagesRepository repository = new MessagesRepositoryJdbcImpl(dataSource.getDataSource());
//        tblView.getColumns().clear();
    }
}