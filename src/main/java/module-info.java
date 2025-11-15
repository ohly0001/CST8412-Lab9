module com.example.lab9exe {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.graphics;
    requires javafx.base;

    opens com.example.lab9exe to javafx.fxml;
    exports com.example.lab9exe;
}