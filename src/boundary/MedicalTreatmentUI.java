package boundary;

import control.MedicalTreatmentControl;
import java.util.Date;
import java.util.Scanner;

public class MedicalTreatmentUI {

    private MedicalTreatmentControl control;
    private Scanner scanner;

    public MedicalTreatmentUI() {
        control = new MedicalTreatmentControl();
        scanner = new Scanner(System.in);
    }

    public void run() {
        int choice;
        do {
            System.out.println("\n=== Medical Treatment Module ===");
            System.out.println("1. Add Treatment");
            System.out.println("2. Remove Treatment");
            System.out.println("3. Display All Treatments");
            System.out.println("4. Show Total Treatments");
            System.out.println("5. Show Follow-Up Needed Count");
            System.out.println("6. Generate Prescription List");
            System.out.println("7. Sick Type Trend Report");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                System.out.print("Enter choice: ");
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addTreatmentUI();
                case 2 -> control.removeTreatment();
                case 3 -> control.displayAllTreatments();
                case 4 -> System.out.println("Total treatments: " + control.getTotalTreatments());
                case 5 -> System.out.println("Follow-up needed count: " + control.getFollowUpNeededCount());
                case 6 -> control.generatePrescription();
                case 7 -> control.patientSickTrendReport();
                case 0 -> System.out.println("Thank You and Have a Nice Day!");
                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);
    }

    private void addTreatmentUI() {
    System.out.print("Diagnosis ID: ");
    String diagnosisID = scanner.nextLine();

    System.out.print("Patient ID: ");
    String patientID = scanner.nextLine();

    System.out.print("Doctor ID: ");
    String doctorID = scanner.nextLine();

    System.out.print("Medication ID: ");
    String medicationID = scanner.nextLine();
    
    // ===== Quantity Input =====
    System.out.print("Enter quantity of medication to dispense: ");
    int quantity = Integer.parseInt(scanner.nextLine());
    System.out.println("Medication quantity for Medical ID " + medicationID +
                       " updated to reduce " + quantity + " units");

    // ===== Sickness Description (Free Text) =====
    System.out.println("Enter sickness description (press Enter when done):");
    String sicknessDescription = scanner.nextLine();

    // ===== Sick Type Selection =====
    String sickType = "";
    System.out.println("Select Sick Type:");
    System.out.println("1. Acute");
    System.out.println("2. Chronic");
    System.out.println("3. Follow-up");
    System.out.print("Enter choice: ");
    int sickChoice = Integer.parseInt(scanner.nextLine());

    switch (sickChoice) {
        case 1 -> sickType = "Acute";
        case 2 -> sickType = "Chronic";
        case 3 -> sickType = "Follow-up";
        default -> {
            System.out.println("Invalid choice, defaulting to 'Acute'.");
            sickType = "Acute";
        }
    }

    // ===== Diagnosis Selection =====
String diagnosisDescription = "";
System.out.println("Select Diagnosis:");
System.out.println("1. Flu");
System.out.println("2. Diabetes");
System.out.println("3. Hypertension");
System.out.println("4. Fracture");
System.out.println("5. Cancer");
System.out.println("6. Asthma");
System.out.println("7. Migraine");
System.out.println("8. Pneumonia");
System.out.println("9. Tuberculosis");
System.out.println("10. COVID-19");
System.out.println("11. Arthritis");
System.out.println("12. Gastritis");
System.out.println("13. Stroke");
System.out.println("14. Kidney Failure");
System.out.println("15. Heart Disease");
System.out.println("16. Anemia");
System.out.println("17. Hepatitis");
System.out.println("18. Malaria");
System.out.println("19. Dengue Fever");
System.out.println("20. Allergies");
System.out.println("21. Other");
System.out.print("Enter choice: ");
int diagChoice = Integer.parseInt(scanner.nextLine());

switch (diagChoice) {
    case 1 -> diagnosisDescription = "Flu";
    case 2 -> diagnosisDescription = "Diabetes";
    case 3 -> diagnosisDescription = "Hypertension";
    case 4 -> diagnosisDescription = "Fracture";
    case 5 -> diagnosisDescription = "Cancer";
    case 6 -> diagnosisDescription = "Asthma";
    case 7 -> diagnosisDescription = "Migraine";
    case 8 -> diagnosisDescription = "Pneumonia";
    case 9 -> diagnosisDescription = "Tuberculosis";
    case 10 -> diagnosisDescription = "COVID-19";
    case 11 -> diagnosisDescription = "Arthritis";
    case 12 -> diagnosisDescription = "Gastritis";
    case 13 -> diagnosisDescription = "Stroke";
    case 14 -> diagnosisDescription = "Kidney Failure";
    case 15 -> diagnosisDescription = "Heart Disease";
    case 16 -> diagnosisDescription = "Anemia";
    case 17 -> diagnosisDescription = "Hepatitis";
    case 18 -> diagnosisDescription = "Malaria";
    case 19 -> diagnosisDescription = "Dengue Fever";
    case 20 -> diagnosisDescription = "Allergies";
    case 21 -> {
        System.out.print("Enter other diagnosis: ");
        diagnosisDescription = scanner.nextLine();
    }
    default -> {
        System.out.println("Invalid choice, defaulting to 'Flu'.");
        diagnosisDescription = "Flu";
    }
}

    // ===== Date Created =====
    Date createdDate = new Date();

    // Add treatment
    control.addTreatment(
        diagnosisID, patientID, doctorID, medicationID,
        sicknessDescription, sickType, diagnosisDescription, createdDate
    );
}
}

