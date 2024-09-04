package com.bloodbank.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.bloodbank.management.RecordNotFoundException;

public class DonorManager {

    public void addDonor(Scanner scanner) {
        System.out.print("Enter Donor Name: ");
        String donorName = scanner.nextLine();
        System.out.print("Enter Blood Group: ");
        String bloodGroup = scanner.nextLine();
        System.out.print("Enter Contact Number: ");
        String contactNumber = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Last Donation Date (YYYY-MM-DD): ");
        String lastDonationDate = scanner.nextLine();

        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO Donor (donor_name, blood_group, contact_number, email, last_donation_date) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, donorName);
            stmt.setString(2, bloodGroup);
            stmt.setString(3, contactNumber);
            stmt.setString(4, email);
            stmt.setString(5, lastDonationDate);
            stmt.executeUpdate();
            System.out.println("Donor added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewDonor(Scanner scanner) {
        System.out.print("Enter Donor ID to view: ");
        int donorId = scanner.nextInt();

        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM Donor WHERE donor_id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, donorId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Donor ID: " + rs.getInt("donor_id"));
                System.out.println("Name: " + rs.getString("donor_name"));
                System.out.println("Blood Group: " + rs.getString("blood_group"));
                System.out.println("Contact Number: " + rs.getString("contact_number"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Last Donation Date: " + rs.getDate("last_donation_date"));
            } else {
                throw new RecordNotFoundException("Donor with ID " + donorId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RecordNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateDonor(Scanner scanner) {
        System.out.print("Enter Donor ID to update: ");
        int donorId = scanner.nextInt();
        scanner.nextLine();  

        System.out.print("Enter new Donor Name: ");
        String donorName = scanner.nextLine();
        System.out.print("Enter new Blood Group: ");
        String bloodGroup = scanner.nextLine();
        System.out.print("Enter new Contact Number: ");
        String contactNumber = scanner.nextLine();
        System.out.print("Enter new Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter new Last Donation Date (YYYY-MM-DD): ");
        String lastDonationDate = scanner.nextLine();

        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE Donor SET donor_name = ?, blood_group = ?, contact_number = ?, email = ?, last_donation_date = ? WHERE donor_id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, donorName);
            stmt.setString(2, bloodGroup);
            stmt.setString(3, contactNumber);
            stmt.setString(4, email);
            stmt.setString(5, lastDonationDate);
            stmt.setInt(6, donorId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new RecordNotFoundException("Donor with ID " + donorId + " not found.");
            }
            System.out.println("Donor updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RecordNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteDonor(Scanner scanner) {
        System.out.print("Enter Donor ID to delete: ");
        int donorId = scanner.nextInt();

        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM Donor WHERE donor_id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, donorId);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new RecordNotFoundException("Donor with ID " + donorId + " not found.");
            }
            System.out.println("Donor deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RecordNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
