package com.bloodbank.management;

import java.util.Scanner;

public class BloodBankManagementSystem {

    private static DonorManager donorManager = new DonorManager();
    private static InventoryManager inventoryManager = new InventoryManager();
    private static RequestManager requestManager = new RequestManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("---- Blood Bank Management System ----");
            System.out.println("1. Donor Management");
            System.out.println("2. Inventory Management");
            System.out.println("3. Request Management");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    manageDonors(scanner);
                    break;
                case 2:
                    manageInventory(scanner);
                    break;
                case 3:
                    manageRequests(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void manageDonors(Scanner scanner) {
        System.out.println("---- Donor Management ----");
        System.out.println("1. Add Donor");
        System.out.println("2. View Donor");
        System.out.println("3. Update Donor");
        System.out.println("4. Delete Donor");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  

        switch (choice) {
            case 1:
                donorManager.addDonor(scanner);
                break;
            case 2:
                donorManager.viewDonor(scanner);
                break;
            case 3:
                donorManager.updateDonor(scanner);
                break;
            case 4:
                donorManager.deleteDonor(scanner);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void manageInventory(Scanner scanner) {
        System.out.println("---- Inventory Management ----");
        System.out.println("1. Add Inventory");
        System.out.println("2. View Inventory");
        System.out.println("3. Update Inventory");
        System.out.println("4. Delete Inventory");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  

        switch (choice) {
            case 1:
                inventoryManager.addInventory(scanner);
                break;
            case 2:
                inventoryManager.viewInventory(scanner);
                break;
            case 3:
                inventoryManager.updateInventory(scanner);
                break;
            case 4:
                inventoryManager.deleteInventory(scanner);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void manageRequests(Scanner scanner) {
        System.out.println("---- Request Management ----");
        System.out.println("1. Register Request");
        System.out.println("2. View Request");
        System.out.println("3. Update Request");
        System.out.println("4. Delete Request");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  

        switch (choice) {
            case 1:
                requestManager.registerRequest(scanner);
                break;
            case 2:
                requestManager.viewRequest(scanner);
                break;
            case 3:
                requestManager.updateRequest(scanner);
                break;
            case 4:
                requestManager.deleteRequest(scanner);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
