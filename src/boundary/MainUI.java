package boundary;

import dao.ClinicInitializer;
import control.DoctorManager;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class MainUI {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Initialize all managers
        DoctorManager doctorManager = new DoctorManager();
        // Other managers would be initialized here
        // PatientManager patientManager = new PatientManager();
        // PharmacyManager pharmacyManager = new PharmacyManager();
        
        // Load sample data
        initializeAllData(doctorManager /*, other managers */);
        
        // Main system loop
        while (true) {
            displayMainMenu();
            int choice = getMenuChoice();
            
            switch (choice) {
                case 1:
                    new DoctorUI(doctorManager).displayMainMenu();
                    break;
                case 2:
                    System.out.println("\nPatient Module UI would launch here");
                    // new PatientUI(patientManager).displayMainMenu();
                    pressEnterToContinue();
                    break;
                case 3:
                    System.out.println("\nConsultation Module UI would launch here");
                    // new ConsultationUI().displayMainMenu();
                    pressEnterToContinue();
                    break;
                case 4:
                    System.out.println("\nMedical Records Module UI would launch here");
                    // new MedicalRecordsUI().displayMainMenu();
                    pressEnterToContinue();
                    break;
                case 5:
                    System.out.println("\nPharmacy Module UI would launch here");
                    // new PharmacyUI(pharmacyManager).displayMainMenu();
                    pressEnterToContinue();
                    break;
                case 6:
                    System.out.println("\nExiting system...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    
    private static void initializeAllData(DoctorManager doctorManager /*, other managers */) {
        System.out.println("Initializing sample data...");
        ClinicInitializer.initializeSampleDoctors(doctorManager);
        System.out.println("Doctor data initialized successfully!");
        
        // Placeholder for other modules' initialization
        System.out.println("[Other modules would initialize their sample data here]");
        pressEnterToContinue();
    }
    
    private static void displayMainMenu() {
        System.out.println("\n|------------------------------|");
        System.out.println("|   CLINIC MANAGEMENT SYSTEM   |");
        System.out.println("|------------------------------|");
        System.out.println("| 1. Doctor Module             |");
        System.out.println("| 2. Patient Module            |");
        System.out.println("| 3. Consultation Module       |");
        System.out.println("| 4. Medical Records Module    |");
        System.out.println("| 5. Pharmacy Module           |");
        System.out.println("| 6. Exit System               |");
        System.out.println("|------------------------------|");
        System.out.print("Select module to access: ");
    }
    
    private static int getMenuChoice() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number (1-6): ");
            }
        }
    }
    
    private static void pressEnterToContinue() {
        System.out.println("\nPress Enter to return to main menu...");
        scanner.nextLine();
    }
}