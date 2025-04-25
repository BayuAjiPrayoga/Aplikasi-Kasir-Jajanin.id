package model;

import java.time.LocalDateTime;

public class Transaksi {
    private int id;
    private int idProduk;
    private int jumlah;
    private double total;
    private LocalDateTime waktu;

    public Transaksi() {}

    public Transaksi(int idProduk, int jumlah, double total, LocalDateTime waktu) {
        this.idProduk = idProduk;
        this.jumlah = jumlah;
        this.total = total;
        this.waktu = waktu;
    }

    // Getters & Setters
    public int getId() { return id; }
    public int getIdProduk() { return idProduk; }
    public int getJumlah() { return jumlah; }
    public double getTotal() { return total; }
    public LocalDateTime getWaktu() { return waktu; }

    public void setId(int id) { this.id = id; }
    public void setIdProduk(int idProduk) { this.idProduk = idProduk; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }
    public void setTotal(double total) { this.total = total; }
    public void setWaktu(LocalDateTime waktu) { this.waktu = waktu; }
    
}
