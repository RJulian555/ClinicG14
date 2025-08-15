package boundary;

import dao.ClinicInitializer;
import control.*;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class MainUI {
    
     // --- All variables are now non-static instance variables ---
    private final Scanner scanner;
    private final DoctorManager doctorManager;
    private final PharmacyControl pharmacyControl;
    private final MedicalTreatmentControl medicalTreatmentControl;
    
     /**
     * The constructor now receives the necessary manager objects.
     * It no longer creates them itself.
     */
    public MainUI(DoctorManager doctorManager, PharmacyControl pharmacyControl, MedicalTreatmentControl medicalTreatmentControl) {
        this.doctorManager = doctorManager;
        this.pharmacyControl = pharmacyControl;
        this.medicalTreatmentControl = medicalTreatmentControl; // Store the new control object
        this.scanner = new Scanner(System.in);
    }
    
     /**
     * The main loop is now in a public "launch" method instead of main().
     */
    public void launch() {
        while (true) {
            displayMainMenu();
            int choice = getMenuChoice();
            
            switch (choice) {
                case 1:
                    // new DoctorUI(doctorManager).displayMainMenu(); // This remains the same
                    new DoctorUI(doctorManager).displayMainMenu();
                    System.out.println("\nDoctor Module UI would launch here");
                    pressEnterToContinue();
                    break;
                case 2:
                    System.out.println("\nPatient Module UI would launch here");
                    pressEnterToContinue();
                    break;
                case 3:
                    System.out.println("\nConsultation Module UI would launch here");
                    pressEnterToContinue();
                    break;
                case 4:
                    MedicalTreatmentUI medicalTreatmentUI = new MedicalTreatmentUI(this.medicalTreatmentControl);
                    // 2. Call the correct method to start the module's loop.
                    medicalTreatmentUI.runModule();
                    System.out.println("\nMedical Records Module UI would launch here");
                    pressEnterToContinue();
                    break;
                case 5:
                    // **THIS IS THE INTEGRATION POINT**
                    // Create and launch the PharmacyUI, passing the control object to it.
                    PharmacyUI pharmacyUI = new PharmacyUI(this.pharmacyControl);
                    pharmacyUI.runPharmacyModule();
                    pressEnterToContinue();
                    
                    break;
                case 6:
                    System.out.println("\nExiting system...");
                    return; // Return from the launch method to exit cleanly
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    
    
    
    
   
    
    private static void displayMainMenu() {
        System.out.println("\n|------------------------------|");
        System.out.println("|   CLINIC MANAGEMENT SYSTEM   |");
        System.out.println("|------------------------------|");
        System.out.println("| 1. Doctor Module             |");
        System.out.println("| 2. Patient Module            |");
        System.out.println("| 3. Consultation Module       |");
        System.out.println("| 4. Medical Treatment Module    |");
        System.out.println("| 5. Pharmacy Module           |");
        System.out.println("| 6. Exit System               |");
        System.out.println("|------------------------------|");
        System.out.print("Select module to access: ");
    }
    
    private int getMenuChoice() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number (1-6): ");
            }
        }
    }
    
    private void pressEnterToContinue() {
        System.out.println("\nPress Enter to return to main menu...");
        scanner.nextLine();
    }
    
    
    
    
}