module com.example.cft_testtask {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.zaxxer.hikari;

    opens com.example.cft_testtask to javafx.fxml;
    exports com.example.cft_testtask;
    exports com.example.cft_testtask.models;
    opens com.example.cft_testtask.models to javafx.fxml;
}