package controller;

import model.Transaksi;
import model.Produk;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransaksiController {

    public static void cetakStruk(List<Transaksi> daftarTransaksi, String usernameKasir) {
        System.out.println("========================================");
        System.out.println("             Jajanin.ID");
        System.out.println("         Jl. Cikijing No. 1, Majalengka");
        System.out.println("========================================");
        System.out.printf("Kasir     : %s%n", usernameKasir);
        System.out.printf("Tanggal   : %s%n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss")));
        System.out.println("----------------------------------------");
        System.out.println("Nama Barang        Qty   Harga   Subtotal");
        System.out.println("----------------------------------------");

        double total = 0;

        for (Transaksi t : daftarTransaksi) {
            Produk p = ProdukController.getProdukById(t.getIdProduk());
            if (p != null) {
                double subtotal = t.getJumlah() * p.getHarga();
                total += subtotal;
                System.out.printf("%-18s %3d %7.0f %9.0f%n",
                    p.getNama(), t.getJumlah(), p.getHarga(), subtotal);
            }
        }

        System.out.println("----------------------------------------");
        System.out.printf("Total%30sRp %,.0f%n", "", total);
        System.out.println("========================================");
        System.out.println("     Terima kasih telah berbelanja!");
        System.out.println("        Belanja Hemat di Jajanin.ID");
        System.out.println("========================================");
    }
}
