package com.bloodbank.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.bloodbank.management.RecordNotFoundException;

public class InventoryManager {

    public void addInventory(Scanner scanner) {
        System.out.print("Enter Donor ID: ");
        int donorId = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter Donation Date (YYYY-MM-DD): ");
        String donationDate = scanner.nextLine();
        System.out.print("Enter Blood Group: ");
        String bloodGroup = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter Expiry Date (YYYY-MM-DD): ");
        String expiryDate = scanner.nextLine();

        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO Inventory (donor_id, donation_date, blood_group, quantity, expiry_date) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, donorId);
            stmt.setString(2, donationDate);
            stmt.setString(3, bloodGroup);
            stmt.setInt(4, quantity);
            stmt.setString(5, expiryDate);
            stmt.executeUpdate();
            System.out.println("Inventory added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewInventory(Scanner scanner) {
        System.out.print("Enter Inventory ID to view: ");
        int inventoryId = scanner.nextInt();

        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM Inventory WHERE donation_id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, inventoryId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Inventory ID: " + rs.getInt("donation_id"));
                System.out.println("Donor ID: " + rs.getInt("donor_id"));
                System.out.println("Donation Date: " + rs.getDate("donation_date"));
                System.out.println("Blood Group: " + rs.getString("blood_group"));
                System.out.println("Quantity: " + rs.getInt("quantity"));
                System.out.println("Expiry Date: " + rs.getDate("expiry_date"));
            } else {
                throw new RecordNotFoundException("Inventory record with ID " + inventoryId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RecordNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateInventory(Scanner scanner) {
        System.out.print("Enter Inventory ID to update: ");
        int inventoryId = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter new Donor ID: ");
        int donorId = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter new Donation Date (YYYY-MM-DD): ");
        String donationDate = scanner.nextLine();
        System.out.print("Enter new Blood Group: ");
        String bloodGroup = scanner.nextLine();
        System.out.print("Enter new Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new Expiry Date (YYYY-MM-DD): ");
        String expiryDate = scanner.nextLine();

        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE Inventory SET donor_id = ?, donation_date = ?, blood_group = ?, quantity = ?, expiry_date = ? WHERE donation_id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, donorId);
            stmt.setString(2, donationDate);
            stmt.setString(3, bloodGroup);
            stmt.setInt(4, quantity);
            stmt.setString(5, expiryDate);
            stmt.setInt(6, inventoryId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new RecordNotFoundException("Inventory record with ID " + inventoryId + " not found.");
            }
            System.out.println("Inventory updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RecordNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteInventory(Scanner scanner) {
        System.out.print("Enter Inventory ID to delete: ");
        int inventoryId = scanner.nextInt();

        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM Inventory WHERE donation_id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, inventoryId);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new RecordNotFoundException("Inventory record with ID " + inventoryId + " not found.");
            }
            System.out.println("Inventory deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RecordNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
