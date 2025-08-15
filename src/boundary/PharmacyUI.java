/*
 * Boundary Class: PharmacyUI.java
 * Interacts with the user for all pharmacy-related functions.
 */
package boundary;


import control.*;
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
                case 8:
                    doReviewPendingPrescriptions();
                    break;
                case 9:
                    doManageHeldPrescriptions();
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
        System.out.println("--- Medication Stock ---");
        System.out.println("1. List All Medication Stock");
        System.out.println("2. Add New Medication");
        System.out.println("3. Edit Medication Details");
        System.out.println("4. Delete Medication");
        System.out.println("--- Reports ---");
        System.out.println("5. Generate Low Stock Report");
        System.out.println("6. Generate Total Stock Value Report");
        System.out.println("--- Prescription Workflow ---");
        System.out.println("8. Review Pending Prescriptions");
        System.out.println("9. Manage Held Prescriptions");
        System.out.println("----------------------------------");
        System.out.println("0. Return to Main Menu");
    }

    private void displayAllMedication() {
        System.out.println("\n--- Full Medication Stock List ---");

        System.out.println("How would you like to sort the list?");
        System.out.println("  1. By Medication ID (Default)");
        System.out.println("  2. By Medication Name");
        System.out.print("Enter your choice (1-2): ");
        
        // Set the default sortBy variable to "id".
        String sortBy = "id"; 
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Only change the sortBy variable if the user explicitly selects option 2.
            if (choice == 2) {
                sortBy = "name";
            }
        } catch (Exception e) {
            // If user enters non-numeric input, just proceed with the default "id" sort.
            scanner.nextLine(); // Clear the invalid input
        }
        
        // Step 1: Call the control layer to get a simple, displayable 2D array.
        // The UI has no idea how this data was retrieved or processed.
        String[][] medsData = pharmacyControl.getMedicationStockForDisplay(sortBy);

        
    
        
        // Step 2: Check if the returned data is empty.
        if (medsData.length == 0) {
            System.out.println("No medication in stock.");
            return;
        }
        
         // Step 3: Display the sorted data.
        System.out.println("\nDisplaying list sorted by: " + capitalizeFirstLetter(sortBy));
        printMedicationTable(medsData);
    }

       
   
    
     private void doAddNewMedication() {
        System.out.println("\n--- Add New Medication ---");

        // Step 1: Gather all user input into local, primitive variables.
        // The UI does not know what these variables will be used for.
         System.out.print("Enter Medication ID (e.g., M008): ");
        // MODIFIED: Convert ID to uppercase immediately.
        String id = scanner.nextLine().toUpperCase();

        System.out.print("Enter Medication Name: ");
        // MODIFIED: Use the helper to capitalize the name.
        String name = capitalizeFirstLetter(scanner.nextLine());

        System.out.print("Enter Description: ");
        // MODIFIED: Use the helper to capitalize the description.
        String desc = capitalizeFirstLetter(scanner.nextLine());

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter Quantity: ");
        int qty = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter Type (Tablet/Liquid/Capsule): ");
        // MODIFIED: Use the helper to capitalize the type.
        String type = capitalizeFirstLetter(scanner.nextLine());
        
        // Step 2: Pass the raw data to the Control layer.
        // The UI's job is done. It does not create any objects.
        pharmacyControl.addNewMedication(id, name, desc, price, qty, type); 
        System.out.println("Medication added successfully!");
    }
    
     
     //This now uses the safe query method to validate the ID first.
      private void doEditMedication() {
        System.out.println("\n--- Edit Medication ---");

        //  First, check if there is anything to edit at all.
        if (pharmacyControl.isStockEmpty()) {
            System.out.println("There is no medication in stock to edit.");
            return;
        }

        //  Show the user the list of medications so they can see the IDs.
        // We can simply reuse the method we already built.
        displayAllMedication();

        // Now that the user has seen the list, prompt them for an ID.
        System.out.print("\nEnter the ID of the medication you wish to edit from the list above (or type '0' to cancel): ");
        String id = scanner.nextLine().toUpperCase();

        // Provide an easy way for the user to back out.
        if (id.equals("0")) {
            System.out.println("Edit operation cancelled.");
            return;
        }

        // Validate the ID they entered.
        if (!pharmacyControl.medicationIdExists(id)) {
            System.out.println("\nError: The ID '" + id + "' was not found in the list.");
            return;
        }
        
           // --- Start of the new Edit Sub-Menu Loop ---
        int editChoice;
        do {
            // Display the sub-menu
            System.out.println("\nWhat would you like to edit for Medication '" + id + "'?");
            System.out.println("  1. Name");
            System.out.println("  2. Description");
            System.out.println("  3. Price");
            System.out.println("  4. Quantity");
            System.out.println("  5. Type");
            System.out.println("  0. Finish Editing and Save");
            System.out.print("Enter your choice: ");

            try {
                editChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (editChoice) {
                    case 1:
                        System.out.print("Enter NEW Name: ");
                        String newName = capitalizeFirstLetter(scanner.nextLine());
                        pharmacyControl.editMedicationName(id, newName);
                        System.out.println("Name updated.");
                        break;
                    case 2:
                        System.out.print("Enter NEW Description: ");
                        String newDesc = capitalizeFirstLetter(scanner.nextLine());
                        pharmacyControl.editMedicationDescription(id, newDesc);
                        System.out.println("Description updated.");
                        break;
                    case 3:
                        System.out.print("Enter NEW Price: ");
                        double newPrice = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        pharmacyControl.editMedicationPrice(id, newPrice);
                        System.out.println("Price updated.");
                        break;
                    case 4:
                        System.out.print("Enter NEW Quantity: ");
                        int newQty = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        pharmacyControl.editMedicationQuantity(id, newQty);
                        System.out.println("Quantity updated.");
                        break;
                    case 5:
                        System.out.print("Enter NEW Type (Tablet/Liquid/Capsule): ");
                        String newType = capitalizeFirstLetter(scanner.nextLine());
                        pharmacyControl.editMedicationType(id, newType);
                        System.out.println("Type updated.");
                        break;
                    case 0:
                        System.out.println("\nAll changes for '" + id + "' have been saved.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select from the menu.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the bad input
                editChoice = -1; // Set choice to a non-zero value to continue the loop
            }

        } while (editChoice != 0);
    }
        
        
    
    
    private void doDispenseMedication() {
        System.out.println("\n--- Dispense Medication ---");
        System.out.print("Enter Medication ID to dispense: ");
        String id = scanner.nextLine().toUpperCase();
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
        String id = scanner.nextLine().toUpperCase();

        if (pharmacyControl.deleteMedication(id)) {
            System.out.println("Medication deleted successfully.");
        } else {
            System.out.println("Error: Deletion failed. Check Medication ID.");
        }
    }

     private void displayLowStockReport() {
        System.out.println("\n--- Low Stock Report ---");
        System.out.print("Enter stock threshold (e.g., 50): ");
        int threshold = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Step 1: Call the control layer to get the simple, displayable report data.
        String[][] lowStockData = pharmacyControl.getLowStockReportForDisplay(threshold);

        // Step 2: Check if the report is empty.
        if (lowStockData.length == 0) {
            System.out.println("No medications are below the threshold of " + threshold + ".");
        } else {
            System.out.println("\nMedications with stock below " + threshold + ":");
            // Step 3: Reuse our existing helper method to print the formatted table.
            printMedicationTable(lowStockData);
        }
    }
     
     /**
     * This helper method is used by both displayAllMedication and displayLowStockReport.
     */
    private void printMedicationTable(String[][] tableData) {
        // Print the header
        System.out.printf("%-5s | %-20s | %-25s | %-10s | %-5s | %-15s\n",
                "ID", "Name", "Description", "Price", "Qty", "Type");
        System.out.println(new String(new char[100]).replace('\0', '-'));

        // Print each row of data
        for (String[] medRow : tableData) {
            System.out.printf("%-5s | %-20s | %-25s | %-10s | %-5s | %-15s\n",
                    medRow[0], medRow[1], medRow[2], medRow[3], medRow[4], medRow[5]);
        }
    }

    private void displayTotalStockValue() {
        System.out.println("\n--- Total Stock Value Report ---");
        double totalValue = pharmacyControl.generateTotalStockValueReport();
        System.out.printf("The total value of all medication in stock is: RM %.2f\n", totalValue);
    }
    
    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str; // Return as-is if null or empty
        }
        // Return the first character as uppercase + the rest of the string as lowercase
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
    
    // **NEW UI METHOD FOR APPROVALS**
    private void doReviewPendingPrescriptions() {
        System.out.println("\n--- Review Pending Prescriptions ---");
        while(true) {
            String[] p = pharmacyControl.getNextPrescriptionForApproval();
            if (p == null) {
                System.out.println("There are no more pending prescriptions to review.");
                break;
            }

            System.out.println("\nNext Prescription for Approval:");
            System.out.println("  Treatment ID: " + p[0]);
            System.out.println("  Patient ID:   " + p[1]);
            System.out.println("  Medication:   " + p[2] + " (" + p[3] + ")");
            System.out.println("  Qty Required: " + p[4]);
            System.out.println("  Stock on Hand:" + p[5]);
            System.out.print("\nChoose an action: (1-Approve, 2-Decline, 0-Stop Reviewing): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            String result = "";
            if (choice == 1) {
                result = pharmacyControl.approveNextPrescription();
            } else if (choice == 2) {
                result = pharmacyControl.declineNextPrescription();
            } else {
                System.out.println("Stopping review process.");
                break;
            }
            System.out.println("Result: " + result);
        }
    }

    // **NEW UI METHOD FOR MANAGING HELD ITEMS**
    private void doManageHeldPrescriptions() {
        System.out.println("\n--- Manage Held Prescriptions ---");
        String[][] heldData = pharmacyControl.getHeldPrescriptionsForDisplay();

        if (heldData.length == 0) {
            System.out.println("There are no held prescriptions.");
            return;
        }

        System.out.printf("%-12s | %-12s | %-15s | %s\n", "Treatment ID", "Patient ID", "Medication ID", "Qty");
        System.out.println(new String(new char[55]).replace('\0', '-'));
        for(String[] row : heldData){
            System.out.printf("%-12s | %-12s | %-15s | %s\n", row[0], row[1], row[2], row[3]);
        }
        
        System.out.print("\nEnter Treatment ID to action (or 0 to cancel): ");
        String id = scanner.nextLine().toUpperCase();
        if(id.equals("0")) return;

        System.out.print("Choose action (1-Release to queue, 2-Delete permanently): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        String result = "";
        if (choice == 1) {
            result = pharmacyControl.releaseHeldPrescription(id);
        } else if (choice == 2) {
            result = pharmacyControl.deleteHeldPrescription(id);
        } else {
            result = "Invalid action choice.";
        }
        System.out.println("Result: " + result);
    }
    
}



