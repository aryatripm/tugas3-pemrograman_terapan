package com.arya.tugas3_praktikum.model;

public class Barang {
    private String id;
    private String nama;
    private Supplier supplier;

    public Barang(String id, String nama, Supplier supplier) {
        this.id = id;
        this.nama = nama;
        this.supplier = supplier;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return nama;
    }
}
