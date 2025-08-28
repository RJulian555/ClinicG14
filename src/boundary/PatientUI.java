/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

/**
 *
 * @author natalie
 */


import adt.LinkedQueue;
import adt.QueueInterface;
import control.PatientManager;
import entity.Consultation;
import entity.Doctor;
import entity.Patient;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PatientUI {
    private final PatientManager controller;
    private final Scanner scanner;
    private control.ConsultationManager consultationManager;
    private control.DoctorManager doctorManager;
    

    public PatientUI(PatientManager manager) {
        this.controller = manager;
        this.scanner = new Scanner(System.in);
        
    }
    
    public PatientUI(PatientManager pm,
                     control.ConsultationManager cm,
                     control.DoctorManager dm) {
        this(pm);                 // reuse old constructor
        this.consultationManager = cm;
        this.doctorManager = dm;
    }

    public void displayMainMenu() {
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            
            System.out.println("\n|-------------------------------------|");
            System.out.println("|        PATIENT MANAGEMENT MENU      |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 1. Register New Patient             |");
            System.out.println("| 2. Add Patient to Queue             |");
            System.out.println("| 3. Process Next Patient in Queue    |");
            System.out.println("| 4. View Queue Status                |");
            System.out.println("| 5. Find/Manage Patient              |");
            System.out.println("| 6. View All Patients                |");
            System.out.println("| 7. Patient Visit Summary            |");
            System.out.println("| 8. Patient Monthly Visit Log        |");
            System.out.println("| 9. Exit to Main Menu                |");
            System.out.println("|-------------------------------------|");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> controller.handleRegistration(this);
                case 2 -> addToQueueUI();
                case 3 -> processNextPatientUI();
                case 4 -> viewQueueStatusUI();
                case 5 -> managePatientUI();
                case 6 -> {
                    controller.displayAllPatients(this);
                    pressEnterToContinue();
                    handlePatientDatabaseOperationsUI();
                }
                case 7 -> {
                    controller.generatePatientVisitSummary(this, consultationManager, doctorManager);
                    pressEnterToContinue();
                }
                case 8 -> {
                    controller.generateMonthlyConsultationCalendar(consultationManager);
                    pressEnterToContinue();
                }
                case 9 -> { return; }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }

    // All the UI methods remain the same, just renamed to include "UI" suffix
    private void addToQueueUI() {
        String ic = prompt("Enter patient IC: ", "^\\d{12}$", "❌ Invalid IC format");
        String result = controller.handleAddToQueue(ic, this);
        
        if (result.startsWith("ERROR:")) {
            System.out.println("\n❌ " + result.substring(6));
            if (result.contains("not found")) {
                System.out.println("Starting registration...");
                controller.handleRegistration(this);
            }
        } else {
            System.out.println("\n" + result);
        }
    }

    private void processNextPatientUI() {
        controller.processNextPatient();
        pressEnterToContinue();
    }

    private void viewQueueStatusUI() {
        if (controller.isQueueEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            controller.viewFullQueue();
        }
        pressEnterToContinue();
    }

    private void managePatientUI() {
        String identifier = prompt("Enter patient IC or ID: ", null, "Invalid input");
        Patient patient = controller.findPatient(identifier);
        
        if (patient == null) {
            System.out.println("❌ Patient not found");
            pressEnterToContinue();
            return;
        }
        
        while (true) {
            System.out.println("\n=== Managing Patient: " + patient.getName() + " ===");
            System.out.println("1. View Full Details");
            System.out.println("2. Update Information");
            System.out.println("3. Add to Queue");
            System.out.println("4. Delete Patient");
            System.out.println("5. Back to Main Menu");
            System.out.print("Select option: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1" -> {
                    displayPatientDetails(patient);
                    pressEnterToContinue();
                }
                case "2" -> updatePatientUI(patient);
                case "3" -> {
                    if (patient.getQueueID() == null) {
                        String result = controller.handleAddToQueue(patient.getIdentificationNo(), this);
                        System.out.println(result);
                    } else {
                        System.out.println("ℹ️ Patient is already in queue");
                    }
                    pressEnterToContinue();
                }
                case "4" -> {
                    if (deletePatientUI(patient)) {
                        return;
                    }
                }
                case "5" -> { return; }
                default -> System.out.println("❌ Invalid option");
            }
        }
    }

    private void handlePatientDatabaseOperationsUI() {
        while (true) {
            showAllPatientsMenu();
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1" -> {
                    controller.displayAllPatients(this);
                    pressEnterToContinue();
                }
                case "2" -> controller.handleFilterPatients(this);
                case "3" -> controller.handleSortPatients(this);
                case "4" -> { return; }
                default -> System.out.println("! Invalid option. Please select 1-4.");
            }
        }
    }

    // All the helper methods remain exactly the same
    private String prompt(String message, String regex, String errorMsg) {
        while (true) {
            System.out.print("\n" + message);
            String input = scanner.nextLine().trim();
            if (regex == null || Pattern.matches(regex, input)) {
                return input;
            }
            System.out.println("\n‼️ ERROR: " + errorMsg);
        }
    }

    public String promptBloodType() {
        System.out.println("\nSelect Blood Type:");
        System.out.println("1. A+");
        System.out.println("2. A-");
        System.out.println("3. B+");
        System.out.println("4. B-");
        System.out.println("5. AB+");
        System.out.println("6. AB-");
        System.out.println("7. O+");
        System.out.println("8. O-");
        System.out.println("9. Unknown (will be recorded as O+)");
        System.out.print("Enter choice (1-9): ");
        
        String choice = scanner.nextLine();
        switch(choice) {
            case "1": return "A+";
            case "2": return "A-";
            case "3": return "B+";
            case "4": return "B-";
            case "5": return "AB+";
            case "6": return "AB-";
            case "7": return "O+";
            case "8": return "O-";
            case "9": return "O+";
            default: 
                System.out.println("\n‼️ Invalid selection. Please enter 1-9");
                return promptBloodType();
        }
    }

    public Patient collectPatientDetails(String patientID, String queueID, String registrationDate) {
        while (true) {
            try {
                System.out.println("\n============ PATIENT REGISTRATION ============");
                String name = prompt("Full Name: ", "^[\\p{L} .'-/]+$", "Only letters, spaces, hyphens (-), apostrophes ('), periods (.) and slashes (/) allowed");
                String ic = prompt("IC Number (12 digits): ", "^\\d{12}$", "Must be exactly 12 digits");
                String contact = prompt("Contact Number (10-11 digits): ", "^\\d{10,11}$", "Must be 10 or 11 digits");
                String dob = prompt("Date of Birth (DD/MM/YYYY): ", "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$", 
                    "Invalid format. Example: 03/05/2001");
                String gender = prompt("Gender (F/M): ", "^(?i)[FM]$", "Enter F for Female or M for Male");
                String bloodType = promptBloodType();
                String allergies = prompt("Allergies (leave blank if none): ", null, null);
                
                double weight = Double.parseDouble(prompt("Weight (kg): ", "^\\d+\\.?\\d*$", "Numbers only (e.g. 55.5)"));
                if (weight < 2 || weight > 300) throw new IllegalArgumentException("Weight must be between 2kg and 300kg");
                
                double height = Double.parseDouble(prompt("Height (cm): ", "^\\d+\\.?\\d*$", "Numbers only (e.g. 165)"));
                if (height < 50 || height > 250) throw new IllegalArgumentException("Height must be between 50cm and 250cm");

                return new Patient(patientID, name, ic, contact, dob, gender, bloodType, allergies, weight, height, queueID, registrationDate);
                
            } catch (IllegalArgumentException e) {
                System.out.println("\n‼️ REGISTRATION ERROR: " + e.getMessage());
                if (!promptRetry()) return null;
            }
        }
    }

    public void showRegistrationSummary(Patient patient) {
        System.out.println("\n========== REGISTRATION SUCCESS ==========");
        System.out.println("| Name: " + padRight(patient.getName(), 40) + "");
        System.out.println("| Patient ID: " + padRight(patient.getPatientID(), 35) + "");
        System.out.println("| Queue ID: " + padRight(patient.getQueueID(), 36) + "");
        System.out.println("| Registration Date: " + padRight(patient.getRegistrationDate(), 28) + "");
        System.out.println("==========================================");
    }

    private String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    private void displayPatientDetails(Patient p) {
    // Calculate values first
    int age = controller.calculateAge(p.getDateOfBirth());
    double bmi = p.calculateBMI();
    String bmiStatus = interpretBMI(bmi);
    
    System.out.println("\n=====================================================");
    System.out.println("                  PATIENT DETAILS");
    System.out.println("=====================================================");
    System.out.printf("%-20s: %s%n", "Patient ID", p.getPatientID());
    System.out.printf("%-20s: %s%n", "Queue ID", (p.getQueueID() != null ? p.getQueueID() : "Not in queue"));
    System.out.println("-----------------------------------------------------");
    System.out.printf("%-20s: %s%n", "Name", p.getName());
    System.out.printf("%-20s: %s%n", "IC Number", p.getIdentificationNo());
    System.out.printf("%-20s: %s%n", "Contact", p.getContactInfo());
    System.out.println("-----------------------------------------------------");
    System.out.printf("%-20s: %s%n", "Date of Birth", p.getDateOfBirth());
    System.out.printf("%-20s: %d%n", "Age", age);
    System.out.printf("%-20s: %s%n", "Gender", p.getGender());
    System.out.printf("%-20s: %s%n", "Blood Type", p.getBloodType());
    System.out.printf("%-20s: %s%n", "Allergies", (p.getAllergies().isEmpty() ? "None" : p.getAllergies()));
    System.out.println("-----------------------------------------------------");
    System.out.printf("%-20s: %.1f kg%n", "Weight", p.getWeight());
    System.out.printf("%-20s: %.1f cm%n", "Height", p.getHeight());
    System.out.printf("%-20s: %.1f (%s)%n", "BMI", bmi, bmiStatus);
    System.out.println("-----------------------------------------------------");
    System.out.printf("%-20s: %s%n", "Registration Date", p.getRegistrationDate());
    System.out.println("=====================================================");
    
    if (p.getQueueID() != null) {
        System.out.println("\n-----------------------------------------------------");
        System.out.println("                  QUEUE STATUS");
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-20s: %d minutes%n", "Time in queue", p.getTimeInQueueMinutes());
        System.out.println("-----------------------------------------------------");
    }
    
    displayLatestConsultation(p);
}

    private String interpretBMI(double bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 25) return "Normal";
        else if (bmi < 30) return "Overweight";
        else return "Obese";
    }

    private void updatePatientUI(Patient patient) {
    System.out.println("\n=== UPDATE PATIENT ===");
    System.out.println("Leave field blank to keep current value");
    
    String newName = prompt("Name [" + patient.getName() + "]: ", null, null);
    String newContact = prompt("Contact [" + patient.getContactInfo() + "]: ", null, null);
    String newWeight = prompt("Weight (kg) [" + patient.getWeight() + "]: ", null, null);
    String newHeight = prompt("Height (cm) [" + patient.getHeight() + "]: ", null, null);
    String newAllergies = prompt("Allergies [" + 
        (patient.getAllergies().isEmpty() ? "None" : patient.getAllergies()) + "]: ", null, null);
    
    try {
        controller.updatePatient(patient, newName, newContact, 
                              newHeight, newWeight, newAllergies);
        System.out.println("Patient updated successfully");
    } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
        if (promptRetry()) {
            updatePatientUI(patient);
        }
    }
}

    private boolean deletePatientUI(Patient patient) {
        String confirm = prompt("Confirm delete? (Y/N): ", null, null);
        if (confirm.equalsIgnoreCase("Y")) {
            return controller.deletePatient(patient);
        }
        return false;
    }

    private void showAllPatientsMenu() {
        clearScreen();
        System.out.println("\n=== PATIENT DATABASE OPERATIONS ===");
        System.out.println("1. View All Patients");
        System.out.println("2. Filter Patients");
        System.out.println("3. Sort Patients");
        System.out.println("4. Back to Main Menu");
        System.out.print("Select option: ");
    }
    
    
    public void showFilterOptions() {
    clearScreen();
    System.out.println("\n=== FILTER PATIENTS ===");
    System.out.println("1. By Gender");
    System.out.println("2. By Blood Type");
    System.out.println("3. Patients in Queue");
    System.out.println("4. Patients Not in Queue");
    System.out.println("5. Back");
    System.out.print("Select option: ");
}

public void showSortOptions() {
    clearScreen();
    System.out.println("\n=== SORT PATIENTS ===");
    System.out.println("1. By Name");
    System.out.println("2. By Age");
    System.out.println("3. By Queue Position");
    System.out.println("4. Back");
    System.out.print("Select option: ");
}

public void displayQueueStatus(int position, QueueInterface<Patient> nextPatients) {
    System.out.println("\n========== QUEUE STATUS ==========");
    System.out.println("Your position: " + position);
    System.out.println("Patients ahead: " + (position - 1));
    
    System.out.println("\nNext 3 patients in queue:");
    int count = 1;
    
    // Create temporary queue to preserve original
    LinkedQueue<Patient> temp = new LinkedQueue<>();
    while (!nextPatients.isEmpty()) {
        Patient p = nextPatients.dequeue();
        System.out.println(count++ + ". " + p.getQueueID() + " - " + p.getName());
        temp.enqueue(p);
    }
    
    // Restore patients to original queue
    while (!temp.isEmpty()) {
        nextPatients.enqueue(temp.dequeue());
    }
    
    System.out.println("==================================");
}

public void showQueuePosition(int position, int patientsAhead) {
    System.out.println("\n========== QUEUE POSITION ==========");
    System.out.println("Your Queue ID: Q" + String.format("%03d", position));
    System.out.println("Patients ahead of you: " + patientsAhead);
    
    // Calculate estimated wait time (15 mins per patient)
    int waitMinutes = patientsAhead * 15;
    System.out.printf("Estimated wait time: ~%d minutes\n", waitMinutes);
    System.out.println("===================================");
}

    public void handleFilterPatients(PatientUI ui) {
    while (true) {
        ui.showFilterOptions();  // Show filter sub-menu
        String choice = ui.getStringInput();  // Changed to String input
        
        switch (choice) {
            case "1" -> {
                ui.showMessage("Enter gender to filter (F/M): ");
                String gender = ui.getStringInput();
                LinkedQueue<Patient> filtered = filterPatientsByGender(gender);
                ui.displayFilteredPatients(filtered, "Gender: " + gender);
            }
            case "2" -> {
                String bloodType = ui.promptBloodType();
                LinkedQueue<Patient> filtered = filterPatientsByBloodType(bloodType);
                ui.displayFilteredPatients(filtered, "Blood Type: " + bloodType);
            }
            case "3" -> {
                LinkedQueue<Patient> filtered = filterPatientsInQueue();
                ui.displayFilteredPatients(filtered, "Patients in Queue");
            }
            case "4" -> {
                LinkedQueue<Patient> filtered = filterPatientsNotInQueue();
                ui.displayFilteredPatients(filtered, "Patients Not in Queue");
            }
            case "5" -> { return; }
            default -> ui.showMessage("Invalid option. Please try again.");
        }
    }
}

public String handleAddToQueue(String ic, PatientUI ui) {
    try {
        Patient patient = findPatient(ic);
        if (patient == null) {
            return "ERROR: Patient not found";
        }

        if (isPatientAlreadyInQueue(patient)) {
            return "ERROR: Patient already in queue";
        }

        if (patient.getQueueID() == null) {
            patient.setQueueID(generateQueueID());
        }
        
        patientQueue.enqueue(patient);
        
        int position = getQueuePosition(patient);
        LinkedQueue<Patient> nextPatients = getNextPatients(3);
        
        // Changed to match UI's expected parameters
        ui.displayQueueStatus(position, nextPatients);
        
        return "Patient added to queue successfully";
    } catch (Exception e) {
        return "ERROR: " + e.getMessage();
    }
}



    
    
    

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void pressEnterToContinue() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public boolean promptRetry() {
        System.out.print("Retry? (Y/N): ");
        return scanner.nextLine().trim().equalsIgnoreCase("Y");
    }

    public int promptSortOrder(String sortField) {
        System.out.println("\n=== SORT BY " + sortField.toUpperCase() + " ===");
        System.out.println("1. Ascending order");
        System.out.println("2. Descending order");
        System.out.print("Select sort order: ");
        
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1 || choice == 2) {
                    return choice;
                }
                System.out.println("Invalid choice. Please enter 1 or 2.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter 1 or 2.");
            }
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public String getStringInput() {
        return scanner.nextLine();
    }

    private boolean isPatientAlreadyInQueue(Patient patient) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private Patient findPatient(String ic) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private String generateQueueID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private int getQueuePosition(Patient patient) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private LinkedQueue<Patient> getNextPatients(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void displayFilteredPatients(LinkedQueue<Patient> filtered, String patients_Not_in_Queue) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private LinkedQueue<Patient> filterPatientsNotInQueue() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private LinkedQueue<Patient> filterPatientsInQueue() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private LinkedQueue<Patient> filterPatientsByBloodType(String bloodType) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private LinkedQueue<Patient> filterPatientsByGender(String gender) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static class patientQueue {

        private static void enqueue(Patient patient) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public patientQueue() {
        }
    }
    
    


// === ASCII-BOX VERSION ===
private void displayLatestConsultation(Patient p) {
    if (consultationManager == null || doctorManager == null) return;

    Consultation last = controller.getLatestConsultation(consultationManager, p);
    if (last == null) {
        System.out.println("\n+--------------------------+");
        System.out.println("|  NO CONSULTATIONS FOUND  |");
        System.out.println("+--------------------------+");
        return;
    }

    Doctor doc = doctorManager.getDoctorByID(last.getDoctorId());
    String doctorName = (doc == null) ? "N/A" : doc.getName();

    System.out.println("\n+----------------------------------------------+");
    System.out.println("|          LATEST CONSULTATION                 |");
    System.out.println("+----------------------------------------------+");
    System.out.printf("| ID: %-41s |%n", last.getConsultationId());
    System.out.printf("| Date: %-39s |%n", last.getConsultationDate());
    System.out.printf("| Time: %-39s |%n", last.getConsultationTime());
    System.out.printf("| Doctor: %-37s |%n", doctorName);
    System.out.printf("| Type: %-39s |%n", last.getConsultationType());
    System.out.printf("| Status: %-37s |%n", last.getConsultationStatus());
    System.out.printf("| Follow-up: %-36s |%n", last.isFollowUpRequired() ? "Yes" : "No");
    if (last.getConsultationNotes() != null && !last.getConsultationNotes().isBlank()) {
        System.out.printf("| Notes: %-40s |%n", last.getConsultationNotes());
    }
    System.out.println("+----------------------------------------------+");
}

}
    
