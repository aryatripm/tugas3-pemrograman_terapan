module com.arya.tugas3_praktikum {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.arya.tugas3_praktikum to javafx.fxml;
    exports com.arya.tugas3_praktikum;
    exports com.arya.tugas3_praktikum.controller;
    exports com.arya.tugas3_praktikum.model;
}