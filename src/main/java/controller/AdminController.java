package controller;

import java.util.Scanner;

public class AdminController {
    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        int pilih;

        do {
            System.out.println("\n=== MENU ADMIN ===");
            System.out.println("1. Tambah Produk");
            System.out.println("2. Lihat Produk");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");
            pilih = scanner.nextInt();
            scanner.nextLine();

            switch (pilih) {
                case 1:
                    System.out.print("Nama produk: ");
                    String nama = scanner.nextLine();
                    System.out.print("Harga: ");
                    double harga = scanner.nextDouble();
                    System.out.print("Stok: ");
                    int stok = scanner.nextInt();
                    ProdukController.tambahProduk(nama, harga, stok);
                    break;
                case 2:
                    ProdukController.tampilkanProduk();
                    break;
            }

        } while (pilih != 0);
    }
}
