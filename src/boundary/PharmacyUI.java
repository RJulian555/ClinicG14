/*
 * Boundary Class: PharmacyUI.java
 * Interacts with the user for all pharmacy-related functions.
 */
package boundary;

import adt.QueueInterface;
import control.*;
import entity.Pharmacy;
import java.util.Date;
import java.util.Scanner;

public class PharmacyUI {

    private final PharmacyControl pharmacyControl;
    private final Scanner scanner;

     public PharmacyUI(PharmacyControl pharmacyControl) {
        this.pharmacyControl = pharmacyControl;
        this.scanner = new Scanner(System.in);
    }

    public void runPharmacyModule() {
        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayAllMedication();
                    break;
                case 2:
                    doAddNewMedication();
                    break;
                case 3:
                    doEditMedication();
                    break;
                case 4:
                    doDispenseMedication();
                    break;
                case 5:
                    doDeleteMedication();
                    break;
                case 6:
                    displayLowStockReport();
                    break;
                case 7:
                    displayTotalStockValue();
                    break;
                case 0:
                   
                    
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
    
    private void displayMenu() {
        System.out.println("\n--- Pharmacy Management Module ---");
        System.out.println("1. List All Medication Stock");
        System.out.println("2. Add New Medication");
        System.out.println("3. Edit Medication Details");
        System.out.println("4. Dispense Medication");
        System.out.println("5. Delete Medication");
        System.out.println("6. Generate Low Stock Report");
        System.out.println("7. Generate Total Stock Value Report");
        System.out.println("0. Return to Main Menu"); 
        System.out.println("----------------------------------");
    }

    private void displayAllMedication() {
        System.out.println("\n--- Full Medication Stock List ---");
        QueueInterface<Pharmacy> medsQueue = pharmacyControl.getAllMedicationStock();

        if (medsQueue.isEmpty()) {
            System.out.println("No medication in stock.");
            return;
        }

        Pharmacy[] medsArray = new Pharmacy[medsQueue.size()];
        medsArray = medsQueue.toArray(medsArray);

        System.out.println(String.format("%-5s | %-20s | %-25s | %-10s | %-5s | %-15s",
            "ID", "Name", "Description", "Price", "Qty", "Type"));
        System.out.println(new String(new char[100]).replace('\0', '-'));

        for (Pharmacy med : medsArray) {
            System.out.println(String.format("%-5s | %-20s | %-25s | RM %-7.2f | %-5d | %-15s",
                med.getMedicationID(), med.getMedicationName(), med.getMedicationDescription(),
                med.getMedicationPrice(), med.getMedicationQuantity(), med.getMedicationType()));
        }
    }
    
    private void doAddNewMedication() {
         System.out.println("\n--- Add New Medication ---");

        // Create an empty Pharmacy object first
        Pharmacy newMed = new Pharmacy();

        // Gather user input and use setters to populate the object
        System.out.print("Enter Medication ID (e.g., M006): ");
        newMed.setMedicationID(scanner.nextLine());

        System.out.print("Enter Medication Name: ");
        newMed.setMedicationName(scanner.nextLine());

        System.out.print("Enter Description: ");
        newMed.setMedicationDescription(scanner.nextLine());

        System.out.print("Enter Price: ");
        newMed.setMedicationPrice(scanner.nextDouble());

        System.out.print("Enter Quantity: ");
        newMed.setMedicationQuantity(scanner.nextInt());
        scanner.nextLine(); // consume newline

        System.out.print("Enter Type (Tablet/Liquid/Capsule): ");
        newMed.setMedicationType(scanner.nextLine());
        
        // Set the date automatically
        newMed.setMedicationDate(new Date());
        
        // Pass the fully populated object to the control layer
        pharmacyControl.addNewMedication(newMed);
        
        System.out.println("Medication added successfully!");
    
    }
    
    private void doEditMedication() {
        System.out.println("\n--- Edit Medication ---");
        System.out.print("Enter Medication ID to edit: ");
        String id = scanner.nextLine();

        if (pharmacyControl.findMedicationById(id) == null) {
            System.out.println("Error: Medication ID not found.");
            return;
        }
        
        System.out.print("Enter NEW Medication Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter NEW Description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter NEW Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter NEW Quantity: ");
        int qty = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter NEW Type (Tablet/Liquid/Capsule): ");
        String type = scanner.nextLine();

        if(pharmacyControl.editMedication(id, name, desc, price, qty, type)){
            System.out.println("Medication updated successfully!");
        } else {
            System.out.println("Failed to update medication.");
        }
    }
    
    private void doDispenseMedication() {
        System.out.println("\n--- Dispense Medication ---");
        System.out.print("Enter Medication ID to dispense: ");
        String id = scanner.nextLine();
        System.out.print("Enter quantity to dispense: ");
        int qty = scanner.nextInt();
        scanner.nextLine();

        if (pharmacyControl.dispenseMedication(id, qty)) {
            System.out.println("Dispensed successfully. Stock updated.");
        } else {
            System.out.println("Error: Dispense failed. Check Medication ID or insufficient stock.");
        }
    }

    private void doDeleteMedication() {
        System.out.println("\n--- Delete Medication ---");
        System.out.print("Enter Medication ID to delete: ");
        String id = scanner.nextLine();

        if (pharmacyControl.deleteMedication(id)) {
            System.out.println("Medication deleted successfully.");
        } else {
            System.out.println("Error: Deletion failed. Check Medication ID.");
        }
    }

    private void displayLowStockReport() {
        System.out.println("\n--- Low Stock Report ---");
        System.out.print("Enter stock threshold (e.g., 30): ");
        int threshold = scanner.nextInt();
        scanner.nextLine();
        
        QueueInterface<Pharmacy> lowStockMeds = pharmacyControl.generateLowStockReport(threshold);
        
        if (lowStockMeds.isEmpty()) {
            System.out.println("No medications are below the threshold of " + threshold);
        } else {
            System.out.println("Medications with stock below " + threshold + ":");
            // You can reuse the formatted display logic here as well if you wish
            System.out.println(lowStockMeds.toString().replace(", ", "\n"));
        }
    }

    private void displayTotalStockValue() {
        System.out.println("\n--- Total Stock Value Report ---");
        double totalValue = pharmacyControl.generateTotalStockValueReport();
        System.out.printf("The total value of all medication in stock is: RM %.2f\n", totalValue);
    }
    
    
}



