package com.arya.tugas3_praktikum.controller;

import com.arya.tugas3_praktikum.Application;
import com.arya.tugas3_praktikum.model.Barang;
import com.arya.tugas3_praktikum.model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class BarangController {
    public TextField id;
    public TextField nama;
    public ComboBox<Supplier> supplier;
    public TableColumn<Barang, String> idColumn;
    public TableColumn<Barang, String> namaColumn;
    public TableColumn<Barang, Supplier> supplierColumn;
    public TableView<Barang> barangTable;
    public Button saveBtn;
    public Button resetBtn;
    public Button updateBtn;

    private ObservableList<Barang> listBarang;
    private ObservableList<Supplier> listSupplier;

    private SupplierController supplierController;

    public void initialize() {
        listSupplier = FXCollections.observableArrayList(
                new Supplier("1", "Supplier 1", "Alamat1"),
                new Supplier("2", "Supplier 2", "Alamat2"),
                new Supplier("3", "Supplier 3", "Alamat3")
        );
        listBarang = FXCollections.observableArrayList();

        supplier.setItems(listSupplier);
        supplier.getSelectionModel().selectFirst();

        barangTable.setItems(listBarang);
        idColumn.setCellValueFactory(new PropertyValueFactory<Barang, String>("id"));
        namaColumn.setCellValueFactory(new PropertyValueFactory<Barang, String>("nama"));
        supplierColumn.setCellValueFactory(new PropertyValueFactory<Barang, Supplier>("supplier"));
        
        updateBtn.setDisable(true);
    }

    public void openSupplier(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("supplier-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        supplierController = fxmlLoader.getController();
        supplierController.setSupplierList(listSupplier);

        stage.setTitle("Supplier Management");
        stage.setScene(scene);
//        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    public void save(ActionEvent actionEvent) {
        Barang newBarang = new Barang(
                id.getText(),
                nama.getText(),
                supplier.getValue()
        );
        listBarang.add(newBarang);
        resetForm();
    }

    public void reset(ActionEvent actionEvent) {
        resetForm();

        barangTable.getSelectionModel().clearSelection();

        saveBtn.setDisable(false);
        updateBtn.setDisable(true);
    }

    private void resetForm() {
        id.setText("");
        nama.setText("");
        supplier.getSelectionModel().selectFirst();
    }

    public void update(ActionEvent actionEvent) {
        Barang barang = barangTable.getSelectionModel().getSelectedItem();
        Barang newBarang = new Barang(
                id.getText(),
                nama.getText(),
                supplier.getValue()
        );

        listBarang.remove(barang);
        listBarang.add(newBarang);
    }

    public void getDataTable(MouseEvent mouseEvent) {
        if (!barangTable.getSelectionModel().getSelectedCells().isEmpty()) {
            Barang barang = barangTable.getSelectionModel().getSelectedItem();
            id.setText(barang.getId());
            nama.setText(barang.getNama());
            supplier.getSelectionModel().select(barang.getSupplier());

            saveBtn.setDisable(true);
            updateBtn.setDisable(false);
        }
    }
}
