package com.mycompany.jajanin;

import java.util.Scanner;

import controller.AuthController;
import model.User;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("========================================");
        System.out.println("         SELAMAT DATANG DI JAJANIN.ID   ");
        System.out.println("========================================");
        System.out.println("  Solusi Belanja Mudah dan Cepat untuk Anda");
        System.out.println("========================================");

        while (true) {
            System.out.println("\n------------- LOGIN -------------");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            System.out.println("---------------------------------");

            User user = AuthController.login(username, password);
            if (user != null) {
                System.out.println("\nLogin berhasil! Selamat datang, " + user.getUsername() + "!");
                user.showMenu(); // Polymorphism
            } else {
                System.out.println("\nLogin gagal. Username atau password salah. Silakan coba lagi.");
            }
        }
    }
}
