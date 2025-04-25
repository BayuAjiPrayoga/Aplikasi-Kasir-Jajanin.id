/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.DatabaseConnection;
import model.Admin;
import model.Kasir;
import model.User;

import java.sql.*;

public class UserController {
    public static User login(String username, String password) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM users WHERE username = ? AND password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                if ("admin".equalsIgnoreCase(role)) {
                    return new Admin(rs.getInt("id"), username, password);
                } else if ("kasir".equalsIgnoreCase(role)) {
                    return new Kasir(rs.getInt("id"), username, password);
                }
            }

        } catch (SQLException e) {
            System.out.println("Login gagal: " + e.getMessage());
        }
        return null;
    }
}
