package boundary;

import control.MedicalTreatmentControl;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MedicalTreatmentUI {

    private final MedicalTreatmentControl medicalTreatmentControl;
    private final Scanner scanner;

    public MedicalTreatmentUI(MedicalTreatmentControl medicalTreatmentControl) {
        this.medicalTreatmentControl = medicalTreatmentControl;
        this.scanner = new Scanner(System.in);
    }

        public void runModule() {
        int choice;
        do {
            System.out.println("\n--- Medical Treatment & Diagnosis Module ---");
            System.out.println("1. Create New Medical Treatment");
            System.out.println("2. View Treatment History");
            System.out.println("3. Update a Treatment Record");
            System.out.println("4. Delete a Treatment Record");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");
            
            try {
                choice = scanner.nextInt();
                // **THIS IS THE FIX:**
                // Consume the leftover newline character right after reading the integer.
                scanner.nextLine(); 

                switch (choice) {
                    case 1 -> doCreateTreatment();
                    case 2 -> doViewHistory();
                    case 3 -> doUpdateTreatment();
                    case 4 -> doDeleteTreatment();
                    case 0 -> System.out.println("Returning to main menu...");
                    default -> System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the bad (non-numeric) input from the scanner
                choice = -1; // Set choice to a non-zero value to continue the loop
            }
        } while (choice != 0);
    }


    // --- CREATE UI (Already implemented) ---
      private void doCreateTreatment() {
        System.out.println("\n--- Create New Medical Treatment ---");

        // Step 1: Basic Info
        System.out.print("Enter Patient ID: ");
        String patientID = scanner.nextLine().toUpperCase();
        System.out.print("Enter Doctor ID: ");
        String doctorID = scanner.nextLine().toUpperCase();

        // Step 2: Patient's specific complaint (manual input)
        System.out.print("Enter Patient's Sickness Description (e.g., Reports headache and fever): ");
        String patientSicknessDesc = scanner.nextLine();

        // Step 3: Select Sick Type from a fixed list
        String sickType = selectSickType();
        if (sickType == null) return; // User cancelled

        // Step 4: Select Diagnosis from a hardcoded list
        String chosenTemplateID = selectDiagnosisTemplate();
        if (chosenTemplateID == null) return; // User cancelled

        // Step 5: Prescription Info
        System.out.print("Enter Medication ID to Prescribe (e.g., M001): ");
        String medID = scanner.nextLine().toUpperCase();
        System.out.print("Enter Quantity to Dispense: ");
        int quantity = -1;
        while(quantity < 0) {
            try {
                quantity = scanner.nextInt();
                scanner.nextLine();
                if(quantity < 0) System.out.println("Quantity cannot be negative.");
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        
        // Step 6: Submit to Control Layer
        String result = medicalTreatmentControl.createTreatment(patientID, doctorID, patientSicknessDesc, sickType,
                                                              chosenTemplateID, medID, quantity);
        System.out.println("\n" + result);
    }
      
       // --- UI Helper Methods for Selection ---

    private String selectSickType() {
        int typeChoice = -1;
        do {
            System.out.println("\nPlease select the Sickness Type:");
            System.out.println("  1. Acute");
            System.out.println("  2. Chronic");
            System.out.println("  3. Follow-up");
            System.out.println("  0. Cancel");
            System.out.print("Enter your choice: ");
            try {
                typeChoice = scanner.nextInt();
                scanner.nextLine();
                switch (typeChoice) {
                    case 1: return "Acute";
                    case 2: return "Chronic";
                    case 3: return "Follow-up";
                    case 0: System.out.println("Operation cancelled."); return null;
                    default: System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (true);
    }

    private String selectDiagnosisTemplate() {
        String[][] templates = medicalTreatmentControl.getDiagnosisTemplatesForDisplay();
        if (templates.length == 0) {
            System.out.println("Error: No diagnosis templates configured.");
            return null;
        }

        int diagChoice = -1;
        do {
            System.out.println("\nPlease select a formal Diagnosis:");
            System.out.printf("%-5s | %-10s | %s\n", "No.", "ID", "Diagnosis Name");
            System.out.println(new String(new char[50]).replace('\0', '-'));
            for (String[] row : templates) {
                System.out.printf("%-5s | %-10s | %s\n", row[0], row[1], row[2]);
            }
            System.out.println("  0. Cancel");
            System.out.print("Enter your choice: ");
            try {
                diagChoice = scanner.nextInt();
                scanner.nextLine();
                if (diagChoice == 0) {
                    System.out.println("Operation cancelled.");
                    return null;
                }
                if (diagChoice > 0 && diagChoice <= templates.length) {
                    // Return the Template ID (which is in the second column)
                    return templates[diagChoice - 1][1];
                } else {
                    System.out.println("Invalid choice. Please select a number from the list.");
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (true);
    }

    // --- READ UI ---
    private void doViewHistory() {
        System.out.println("\n--- Full Medical Treatment History ---");
        String[][] historyData = medicalTreatmentControl.getTreatmentHistoryForDisplay();

        if (historyData.length == 0) {
            System.out.println("No treatment history found.");
            return;
        }

        // Print table header
        System.out.printf("%-12s | %-10s | %-10s | %-15s | %-30s | %-12s | %-5s | %s\n",
                "DiagnosisID", "PatientID", "DoctorID", "Sickness", "Diagnosis", "Medication", "Qty", "Date");
        System.out.println(new String(new char[140]).replace('\0', '-'));

        // Print table rows
        for (String[] row : historyData) {
            System.out.printf("%-12s | %-10s | %-10s | %-15s | %-30s | %-12s | %-5s | %s\n",
                    row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7]);
        }
    }

    // --- UPDATE UI ---
    private void doUpdateTreatment() {
        System.out.println("\n--- Update a Treatment Record ---");
        // Show the list first so the user knows which ID to pick
        doViewHistory();
        
        System.out.print("\nEnter the Diagnosis ID of the record to update (or 0 to cancel): ");
        String id = scanner.nextLine().toUpperCase();
        if (id.equals("0")) return;

        // Check if the ID is valid before proceeding
        if (!medicalTreatmentControl.treatmentIdExists(id)) {
            System.out.println("Error: Diagnosis ID not found.");
            return;
        }
        
        System.out.println("\nEnter new details for record " + id + " (Patient/Doctor/Medication cannot be changed).");
        System.out.print("Enter NEW Sickness Description: ");
        String newSickness = capitalizeFirstLetter(scanner.nextLine());
        System.out.print("Enter NEW Diagnosis Description: ");
        String newDiagnosis = capitalizeFirstLetter(scanner.nextLine());

        if (medicalTreatmentControl.updateTreatment(id, newSickness, newDiagnosis)) {
            System.out.println("Record updated successfully.");
        } else {
            // This is unlikely to be hit due to the check above, but is good practice
            System.out.println("Error: Could not update the record.");
        }
    }

    // --- DELETE UI ---
    private void doDeleteTreatment() {
        System.out.println("\n--- Delete a Treatment Record ---");
        doViewHistory();
        
        System.out.print("\nEnter the Diagnosis ID of the record to DELETE (or 0 to cancel): ");
        String id = scanner.nextLine().toUpperCase();
        if (id.equals("0")) return;
        
        if (!medicalTreatmentControl.treatmentIdExists(id)) {
            System.out.println("Error: Diagnosis ID not found.");
            return;
        }
        
        // Confirmation step
        System.out.print("Are you sure you want to permanently delete record " + id + "? (Y/N): ");
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("Y")) {
            if (medicalTreatmentControl.deleteTreatment(id)) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("Error: Could not delete the record.");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}