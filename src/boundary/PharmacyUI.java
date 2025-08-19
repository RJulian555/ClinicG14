/*
 * Boundary Class: PharmacyUI.java
 * Interacts with the user for all pharmacy-related functions.
 */
package boundary;


import control.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PharmacyUI {

    // --- ANSI Color Codes for a prettier chart ---
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    
    private final PharmacyControl pharmacyControl;
    private final Scanner scanner;

     public PharmacyUI(PharmacyControl pharmacyControl) {
        this.pharmacyControl = pharmacyControl;
        this.scanner = new Scanner(System.in);
    }

    public void runPharmacyModule() {
        int choice;
        do {
            displayMenu(); // This method now ONLY displays the menu.
            
            System.out.print("Enter your choice: "); // The prompt is here, and only here.
            
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> displayAllMedication();
                    case 2 -> doAddNewMedication();
                    case 3 -> doEditMedication();
                    case 4 -> doDeleteMedication();
                    case 5 -> displayLowStockReport();
                    case 6 -> displayTotalStockValue();
                    case 8 -> doReviewPendingPrescriptions();
                    case 9 -> doManageHeldPrescriptions();
                    case 0 -> System.out.println("\nReturning to Main Menu...");
                    default -> System.out.println("\nInvalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a whole number.");
                scanner.nextLine(); // Clear the bad input
                choice = -1; // Set choice to a non-zero value to continue the loop
            }
        } while (choice != 0);
    }
    
    private void displayMenu() {
        // Define the width of the menu's content area. You can adjust this value.
        final int MENU_WIDTH = 45;
        
        // Create the border and separator lines based on the defined width
        String border = "+" + "-".repeat(MENU_WIDTH + 2) + "+";
        String separator = "|" + "-".repeat(MENU_WIDTH + 2) + "|";

        // Create a reusable format string for all menu items to ensure perfect alignment
        // The format is: "|  1. Text goes here                |"
        String itemFormat = "|  %-2d. %-" + (MENU_WIDTH - 5) + "s |";
        
        // A format for the section headers
        String headerFormat = "| --- %-" + (MENU_WIDTH - 4) + "s |";

        System.out.println(); // Add a blank line for spacing
        System.out.println(border);
        // Use the helper method to print a centered title
        System.out.println(centerTextForDisplayMenu("Pharmacy Management Module", MENU_WIDTH));
        System.out.println(separator);
        
        // Medication Stock section
        System.out.printf(headerFormat, "Medication Stock");
        System.out.println();
        System.out.printf(itemFormat, 1, "List All Medication Stock");
        System.out.println();
        System.out.printf(itemFormat, 2, "Add New Medication");
        System.out.println();
        System.out.printf(itemFormat, 3, "Edit Medication Details");
        System.out.println();
        System.out.printf(itemFormat, 4, "Delete Medication");
        System.out.println();
        System.out.println(separator);

        // Reports section
        System.out.printf(headerFormat, "Reports");
        System.out.println();
        System.out.printf(itemFormat, 5, "Generate Low Stock Report");
        System.out.println();
        System.out.printf(itemFormat, 6, "Generate Total Stock Value Report");
        System.out.println();
        System.out.println(separator);

        // Prescription Workflow section
        System.out.printf(headerFormat, "Prescription Workflow");
        System.out.println();
        System.out.printf(itemFormat, 8, "Review Pending Prescriptions");
        System.out.println();
        System.out.printf(itemFormat, 9, "Manage Held Prescriptions");
        System.out.println();
        System.out.println(separator);

        // Exit Option
        System.out.printf(itemFormat, 0, "Return to Main Menu");
        System.out.println();
        System.out.println(border);
    }
    
     private String centerTextForDisplayMenu(String text, int width) {
        if (text.length() >= width) {
            return "| " + text.substring(0, width) + " |";
        }
        int padding = width - text.length();
        int leftPadding = padding / 2;
        int rightPadding = padding - leftPadding;
        return "| " + " ".repeat(leftPadding) + text + " ".repeat(rightPadding) + " |";
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

        // Step 1: Get the next available ID from the control layer BEFORE asking for input.
        String newId = pharmacyControl.getNextMedicationID();

        // Step 2: Inform the user what the new ID will be.
        System.out.println("A new Medication ID will be automatically assigned: " + newId);

        // Step 3: Gather all OTHER details from the user.
        System.out.print("Enter Medication Name: ");
        String name = capitalizeFirstLetter(scanner.nextLine());

        System.out.print("Enter Description: ");
        String desc = capitalizeFirstLetter(scanner.nextLine());

        System.out.print("Enter Price: ");
        double price = -1;
        while(price < 0) {
            try {
                price = scanner.nextDouble();
                if(price < 0) System.out.println("Price cannot be negative.");
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Clear bad input
                System.out.println("Invalid input. Please enter a valid price.");
            }
        }
        
        System.out.print("Enter Quantity: ");
        int qty = -1;
        while(qty < 0) {
            try {
                qty = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if(qty < 0) System.out.println("Quantity cannot be negative.");
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Clear bad input
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
        
         String type = selectMedicationType();
        if (type == null) {
            System.out.println("Add new medication cancelled.");
            return; // Exit if the user cancels the selection
        }
        
        // Step 4: Call the control layer, passing the auto-generated ID along with the user's input.
        pharmacyControl.addNewMedication(newId, name, desc, price, qty, type);
        
        System.out.println("\nMedication '" + newId + " - " + name + "' was added successfully!");
    }
       
       /**
     * **NEW HELPER METHOD**
     * Displays a dynamic menu of medication types and returns the user's selection.
     * @return The selected medication type as a String, or null if the user cancels.
     */
    private String selectMedicationType() {
        // 1. Get the list of unique types from the control layer.
        String[] types = pharmacyControl.getDistinctMedicationTypes();

        // Fallback for an empty system, though unlikely with 100 sample items.
        if (types.length == 0) {
            System.out.println("No pre-existing types found.");
            System.out.print("Please enter the type manually: ");
            return capitalizeFirstLetter(scanner.nextLine());
        }

        int choice = -1;
        do {
            System.out.println("\nPlease select a Medication Type:");
            // 2. Display the options in a numbered list.
            for (int i = 0; i < types.length; i++) {
                System.out.printf("  %d. %s\n", (i + 1), types[i]);
            }
            System.out.println("  0. Cancel");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 0) {
                    return null; // User chose to cancel
                }
                if (choice > 0 && choice <= types.length) {
                    // 3. Return the selected type string from the array.
                    return types[choice - 1];
                } else {
                    System.out.println("Invalid choice. Please select a number from the list.");
                }
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Clear bad input
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (true); // Loop until a valid choice is made or cancelled
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
                        String newType = selectMedicationType();
                        if (newType != null) { // Check for cancellation
                            pharmacyControl.editMedicationType(id, newType);
                            System.out.println("Type updated.");
                        } else {
                            System.out.println("Update for Type cancelled.");
                        }
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
        int threshold = 50; // Default value
        try {
            threshold = scanner.nextInt();
            scanner.nextLine();
            if (threshold <= 0) {
                System.out.println("Threshold must be positive. Using default of 50.");
                threshold = 50;
            }
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid input. Using default threshold of 50.");
        }

        System.out.println("\nHow would you like to view the report?");
        System.out.println("  1. Table View");
        System.out.println("  2. Chart View");
        System.out.print("Enter your choice (1-2): ");
        int choice = 1;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid choice. Defaulting to Table View.");
        }

        if (choice == 2) {
            final int TOP_N_COUNT = 10;
            String[][] chartData = pharmacyControl.getTopNLowStockDataForChart(threshold, TOP_N_COUNT);
            if (chartData.length == 0) {
                System.out.println("\nNo medications are below the threshold of " + threshold + ".");
            } else {
                // **THE FIX:** Pass the 'threshold' as the Y-axis height.
                drawBarChart("Top " + chartData.length + " Low Stock Medications", chartData, "Medications", threshold);
            }
        } else {
            // Table view logic remains the same
            String[][] lowStockData = pharmacyControl.getLowStockReportForDisplay(threshold);
            if (lowStockData.length == 0) {
                System.out.println("\nNo medications are below the threshold of " + threshold + ".");
            } else {
                System.out.println("\nDisplaying all " + lowStockData.length + " medications with stock below " + threshold + ":");
                printMedicationTable(lowStockData);
            }
        }
    }
     
     
      /**
     * **NEW HELPER METHOD**
     * This method takes a title and data, and renders a text-based bar chart in the console.
     * @param title The title to be displayed above the chart.
     * @param data A 2D String array where each inner array is [label, value].
     */
     private void drawBarChart(String title, String[][] data, String xAxisLabel, int yAxisHeight) {
        // --- Configuration ---
        final String BAR_CHAR = "*";

        System.out.println("\n" + ANSI_YELLOW + title + " (Threshold: " + yAxisHeight + ")" + ANSI_RESET);
        System.out.println("   ^"); // Y-axis arrow

        // --- Determine the dynamic width for each column ---
        int columnWidth = String.valueOf(data.length).length() + 2;
        columnWidth = Math.max(5, columnWidth);

        // --- Draw the chart body using the dynamic Y-axis height ---
        for (int y = yAxisHeight; y >= 1; y--) {
            // Adjust the Y-axis step for taller charts to keep them compact
            if (yAxisHeight > 20 && y % 2 != 0 && y != 1) {
                continue; // Skip odd numbers for a cleaner look on tall charts
            }
            
            System.out.printf("%3d |", y);
            for (String[] row : data) {
                int value = Integer.parseInt(row[1]);
                String barSegment = (value >= y) ? BAR_CHAR.repeat(columnWidth - 2) : "";
                System.out.print(" " + ANSI_GREEN + centerTextForBarChart(barSegment, columnWidth - 2) + ANSI_RESET + " ");
            }
            System.out.println();
        }

        // --- Draw the X-axis line ---
        System.out.print("---+" + "-".repeat(columnWidth * data.length));
        System.out.println("-> " + xAxisLabel);

        // --- Draw the numbered legend labels ---
        System.out.print("    "); // Alignment space
        for (int i = 0; i < data.length; i++) {
            System.out.print(centerTextForBarChart(String.valueOf(i + 1), columnWidth));
        }
        System.out.println("\n");

        // --- Print the legend itself ---
        System.out.println("Legend:");
        for (int i = 0; i < data.length; i++) {
            System.out.printf("  %d. %s (Qty: %s)\n", (i + 1), data[i][0], data[i][1]);
        }
    }
    
    private String centerTextForBarChart(String text, int width) {
        
        if (text.length() >= width) {
            return text.substring(0, width);
        }
        int padding = width - text.length();
        int leftPadding = padding / 2;
        int rightPadding = padding - leftPadding;
        return " ".repeat(leftPadding) + text + " ".repeat(rightPadding);
    }
    
    
     
     /**
     * This helper method is used by both displayAllMedication and displayLowStockReport.
     */
     private void printMedicationTable(String[][] tableData) {
        // --- Define the column widths ---
        // These can be adjusted to fit your data.
        int idWidth = 5;
        int nameWidth = 25;
        int descWidth = 35;
        int priceWidth = 10;
        int qtyWidth = 5;
        int typeWidth = 15;

        // --- Create the format string for both the header and the data rows ---
        // The `%-Ns` format means: Left-align (-) a string (s) in a space of N characters.
        String formatString = "| %-" + idWidth + "s | %-" + nameWidth + "s | %-" + descWidth + "s | %-" + priceWidth + "s | %-" + qtyWidth + "s | %-" + typeWidth + "s |";
        
        // --- Create a separator line of the correct total length ---
        // The total length is the sum of widths + spaces and bars.
        int totalWidth = idWidth + nameWidth + descWidth + priceWidth + qtyWidth + typeWidth + 19; // 19 is for bars and spaces
        String separator = "-".repeat(totalWidth);

        // --- Print the Header ---
        System.out.println(separator);
        System.out.printf(formatString, "ID", "Name", "Description", "Price", "Qty", "Type");
        System.out.println();
        System.out.println(separator);

        // --- Print each row of data using the same format string ---
        for (String[] medRow : tableData) {
            System.out.printf(formatString, medRow[0], medRow[1], medRow[2], medRow[3], medRow[4], medRow[5]);
            System.out.println();
        }

        // --- Print the Footer ---
        System.out.println(separator);
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