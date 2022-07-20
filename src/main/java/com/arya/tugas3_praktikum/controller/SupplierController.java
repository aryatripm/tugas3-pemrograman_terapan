package com.arya.tugas3_praktikum.controller;

import com.arya.tugas3_praktikum.model.Barang;
import com.arya.tugas3_praktikum.model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class SupplierController {
    public TextField id;
    public TextField nama;
    public TextField alamat;
    public TableView<Supplier> supplierTable;
    public TableColumn<Supplier, String> idColumn;
    public TableColumn<Supplier, String> namaColumn;
    public TableColumn<Supplier, String> alamatColumn;
    public Button saveBtn;
    public Button resetBtn;
    public Button updateBtn;

    private ObservableList<Supplier> supplierList;

    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("id"));
        namaColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("nama"));
        alamatColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("alamat"));

        updateBtn.setDisable(true);
    }

    public void setSupplierList(ObservableList<Supplier> list) {
        supplierList = list;
        supplierTable.setItems(supplierList);
    }

    public void save(ActionEvent actionEvent) {
        Supplier newSuppplier = new Supplier(
                id.getText(),
                nama.getText(),
                alamat.getText()
        );
        supplierList.add(newSuppplier);
        resetForm();
    }

    public void reset(ActionEvent actionEvent) {
        resetForm();

        supplierTable.getSelectionModel().clearSelection();

        saveBtn.setDisable(false);
        updateBtn.setDisable(true);
    }

    private void resetForm() {
        id.setText("");
        nama.setText("");
        alamat.setText("");
    }

    public void update(ActionEvent actionEvent) {
        Supplier supplier = supplierTable.getSelectionModel().getSelectedItem();
        Supplier newSupplier = new Supplier(
                id.getText(),
                nama.getText(),
                alamat.getText()
        );

        supplierList.remove(supplier);
        supplierList.add(newSupplier);
    }

    public void getDataTable(MouseEvent mouseEvent) {
        if (!supplierTable.getSelectionModel().getSelectedCells().isEmpty()) {
            Supplier supplier = supplierTable.getSelectionModel().getSelectedItem();
            id.setText(supplier.getId());
            nama.setText(supplier.getNama());
            alamat.setText(supplier.getAlamat());

            saveBtn.setDisable(true);
            updateBtn.setDisable(false);
        }
    }
}
