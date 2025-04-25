package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.Produk;
import model.Transaksi;

public class TransaksiController {

    public static void cetakStruk(List<Transaksi> daftarTransaksi, String usernameKasir) {
        System.out.println("==========================================");
        System.out.println("                 Jajanin.ID               ");
        System.out.println("      Jl. Cikijing No. 1, Majalengka     ");
        System.out.println("==========================================");
        System.out.printf("Kasir     : %-20s%n", usernameKasir);
        System.out.printf("Tanggal   : %-20s%n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss")));
        System.out.println("------------------------------------------");
        System.out.printf("%-15s %5s %10s %12s%n", "Nama Barang", "Qty", "Harga", "Subtotal");
        System.out.println("------------------------------------------");

        double total = 0;

        for (Transaksi t : daftarTransaksi) {
            Produk p = ProdukController.getProdukById(t.getIdProduk());
            if (p != null) {
                double subtotal = t.getJumlah() * p.getHarga();
                total += subtotal;
                System.out.printf("%-15.15s %5d %10.0f %12.0f%n",
                    p.getNama(), t.getJumlah(), p.getHarga(), subtotal);
            } else {
                System.out.printf("Produk dengan ID %d tidak ditemukan.%n", t.getIdProduk());
            }
        }

        System.out.println("------------------------------------------");
        System.out.printf("%-15s %5s %10s %12s%n", "", "", "Total:", String.format("Rp %,.0f", total));
        System.out.println("==========================================");
        System.out.println("       Terima kasih telah berbelanja!     ");
        System.out.println("        Belanja Hemat di Jajanin.ID       ");
        System.out.println("==========================================");
    }
}
