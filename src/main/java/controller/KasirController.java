package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import database.DatabaseConnection;
import model.Produk;
import model.Transaksi;

public class KasirController {
    public static void menu(String usernameKasir) {
        Scanner scanner = new Scanner(System.in);
        List<Transaksi> daftarTransaksi = new ArrayList<>();
        boolean selesai = false;

        while (!selesai) {
            System.out.println("\n=== TRANSAKSI ===");
            ProdukController.tampilkanProduk();
            System.out.print("Masukkan ID produk: ");
            int id = scanner.nextInt();
            System.out.print("Jumlah beli: ");
            int jumlah = scanner.nextInt();

            Produk produk = ProdukController.getProdukById(id);
            if (produk == null) {
                System.out.println("Produk tidak ditemukan.");
                continue;
            }

            if (produk.getStok() < jumlah) {
                System.out.println("Stok tidak mencukupi.");
                continue;
            }

            // Kurangi stok produk
            ProdukController.updateStok(id, produk.getStok() - jumlah);

            // Tambahkan transaksi ke daftar
            double total = jumlah * produk.getHarga();
            daftarTransaksi.add(new Transaksi(id, jumlah, total, LocalDateTime.now()));

            System.out.print("\nApakah ingin menambahkan produk lain? (y/n): ");
            String pilihan = scanner.next();
            if (pilihan.equalsIgnoreCase("n")) {
                selesai = true;
            }
        }

        // Simpan transaksi ke database
        try (Connection conn = DatabaseConnection.getConnection()) {
            for (Transaksi transaksi : daftarTransaksi) {
                PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO transaksi (id_produk, jumlah, total, waktu) VALUES (?, ?, ?, ?)");
                stmt.setInt(1, transaksi.getIdProduk());
                stmt.setInt(2, transaksi.getJumlah());
                stmt.setDouble(3, transaksi.getTotal());
                stmt.setTimestamp(4, Timestamp.valueOf(transaksi.getWaktu()));
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan transaksi: " + e.getMessage());
        }

        // Cetak struk
        TransaksiController.cetakStruk(daftarTransaksi, usernameKasir);
    }
}
