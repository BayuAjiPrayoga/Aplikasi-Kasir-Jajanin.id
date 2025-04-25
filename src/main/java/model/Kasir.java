package model;

import controller.KasirController;

public class Kasir extends User {

    public Kasir(int id, String username, String password) {
        super(id, username, password);
    }

    @Override
    public void showMenu() {
        System.out.println("\n=== Selamat Datang, Kasir " + getUsername() + " ===");
        KasirController.menu(getUsername());
    }
}
