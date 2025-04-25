package model;

import controller.AdminController;

public class Admin extends User {

    public Admin(int id, String username, String password) {
        super(id, username, password);
    }

    @Override
    public void showMenu() {
        System.out.println("\n=== Selamat Datang, Admin " + getUsername() + " ===");
        AdminController.menu();
    }
}
