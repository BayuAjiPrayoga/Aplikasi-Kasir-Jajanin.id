package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.Produk;

public class ProdukController {

    public static void tambahProduk(String nama, double harga, int stok) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO produk (nama, harga, stok) VALUES (?, ?, ?)");
            stmt.setString(1, nama);
            stmt.setDouble(2, harga);
            stmt.setInt(3, stok);
            stmt.executeUpdate();
            System.out.println("Produk berhasil ditambahkan!");
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan produk: " + e.getMessage());
        }
    }

    public static void tampilkanProduk() {
        List<Produk> produkList = getAllProduk();

        System.out.println("==================================================================");
        System.out.printf("| %-3s | %-20s | %-10s | %-5s |\n", "ID", "Nama Produk", "Harga", "Stok");
        System.out.println("==================================================================");

        for (Produk produk : produkList) {
            System.out.printf("| %-3d | %-20s | Rp%-8.0f | %-5d |\n",
                produk.getId(), produk.getNama(), produk.getHarga(), produk.getStok());
        }

        System.out.println("==================================================================");
    }

    public static List<Produk> getAllProduk() {
        List<Produk> produkList = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM produk");

            while (rs.next()) {
                Produk produk = new Produk(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getDouble("harga"),
                    rs.getInt("stok")
                );
                produkList.add(produk);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil daftar produk: " + e.getMessage());
        }
        return produkList;
    }

    public static Produk getProdukById(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM produk WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Produk(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getDouble("harga"),
                    rs.getInt("stok")
                );
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil produk: " + e.getMessage());
        }
        return null;
    }

    public static void updateStok(int id, int stokBaru) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE produk SET stok = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, stokBaru);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui stok: " + e.getMessage());
        }
    }

    public static void hapusProduk(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM produk WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produk dengan ID " + id + " berhasil dihapus.");
            } else {
                System.out.println("Produk dengan ID " + id + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menghapus produk: " + e.getMessage());
        }
    }
}
