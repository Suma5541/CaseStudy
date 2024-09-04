package com.bloodbank.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.bloodbank.management.RecordNotFoundException;

public class RequestManager {

    public void registerRequest(Scanner scanner) {
        System.out.print("Enter Requester Name: ");
        String requesterName = scanner.nextLine();
        System.out.print("Enter Blood Group Requested: ");
        String bloodGroupRequested = scanner.nextLine();
        System.out.print("Enter Request Date (YYYY-MM-DD): ");
        String requestDate = scanner.nextLine();
        System.out.print("Enter Request Status (Pending, Fulfilled, Cancelled): ");
        String requestStatus = scanner.nextLine();

        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO Request (requester_name, blood_group_requested, request_date, request_status) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, requesterName);
            stmt.setString(2, bloodGroupRequested);
            stmt.setString(3, requestDate);
            stmt.setString(4, requestStatus);
            stmt.executeUpdate();
            System.out.println("Request registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewRequest(Scanner scanner) {
        System.out.print("Enter Request ID to view: ");
        int requestId = scanner.nextInt();

        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM Request WHERE request_id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, requestId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Request ID: " + rs.getInt("request_id"));
                System.out.println("Requester Name: " + rs.getString("requester_name"));
                System.out.println("Blood Group Requested: " + rs.getString("blood_group_requested"));
                System.out.println("Request Date: " + rs.getDate("request_date"));
                System.out.println("Request Status: " + rs.getString("request_status"));
            } else {
                throw new RecordNotFoundException("Request with ID " + requestId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RecordNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateRequest(Scanner scanner) {
        System.out.print("Enter Request ID to update: ");
        int requestId = scanner.nextInt();
        scanner.nextLine();  

        System.out.print("Enter new Requester Name: ");
        String requesterName = scanner.nextLine();
        System.out.print("Enter new Blood Group Requested: ");
        String bloodGroupRequested = scanner.nextLine();
        System.out.print("Enter new Request Date (YYYY-MM-DD): ");
        String requestDate = scanner.nextLine();
        System.out.print("Enter new Request Status (Pending, Fulfilled, Cancelled): ");
        String requestStatus = scanner.nextLine();

        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE Request SET requester_name = ?, blood_group_requested = ?, request_date = ?, request_status = ? WHERE request_id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, requesterName);
            stmt.setString(2, bloodGroupRequested);
            stmt.setString(3, requestDate);
            stmt.setString(4, requestStatus);
            stmt.setInt(5, requestId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new RecordNotFoundException("Request with ID " + requestId + " not found.");
            }
            System.out.println("Request updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RecordNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteRequest(Scanner scanner) {
        System.out.print("Enter Request ID to delete: ");
        int requestId = scanner.nextInt();

        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM Request WHERE request_id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, requestId);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new RecordNotFoundException("Request with ID " + requestId + " not found.");
            }
            System.out.println("Request deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RecordNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
