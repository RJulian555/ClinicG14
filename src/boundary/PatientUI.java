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
import control.PatientManager;
import entity.Patient;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PatientUI {
    
    private final PatientManager controller;
    private final Scanner scanner;

    public PatientUI(PatientManager manager) {
        this.controller = manager;
        this.scanner = new Scanner(System.in);
    }

    // Utility method for right-padding strings
    private String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
    
//-----------------------------------------------------------------------------------------------------------------//  

    public String prompt(String message, String regex, String errorMsg) {
        while (true) {
            System.out.print("\n" + message);
            String input = scanner.nextLine().trim();
            if (regex == null || Pattern.matches(regex, input)) {
                return input;
            }
            System.out.println("\n‼ ERROR: " + errorMsg);
        }
    }
    
//-----------------------------------------------------------------------------------------------------------------//  

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
                System.out.println("\n‼ Invalid selection. Please enter 1-9");
                return promptBloodType();
        }
    }

    //-----------------------------------------------------------------------------------------------------------------//  

    public void displayMenu() {
    while (true) {
        System.out.println("\n=== Patient Management ===");
        System.out.println("1. Register New Patient");
        System.out.println("2. Add Patient to Queue");
        System.out.println("3. Process Next Patient in Queue");
        System.out.println("4. View Queue Status");
        System.out.println("5. Find/Manage Patient");
        System.out.println("7. View All Patients and Database Operations");
        System.out.println("8. Generate Health Report");
        System.out.println("9. Generate Queue Report");
        System.out.println("0. Exit");             
        System.out.print("Select option: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> controller.handleRegistration(this);
            case "2" -> addToQueue();
            case "3" -> controller.processNextPatient();
            case "4" -> {
                if (controller.isQueueEmpty()) {
                    System.out.println("Queue is empty.");
                } else {
                    controller.viewFullQueue();
                }
            }
            
            case "5" -> managePatient();
            case "6" -> {
                // First show all patients
                controller.displayAllPatients(this);
                pressEnterToContinue();
                // Then show the database operations menu
                handlePatientDatabaseOperations();
            }  
            
            case "7" -> {
                  // First show all patients
                  controller.displayAllPatients(this);
                  pressEnterToContinue();
                  // Then show the database operations menu
                  handlePatientDatabaseOperations();
            }
            case "8" -> {
                controller.generatePatientHealthReport();
                pressEnterToContinue();
            }
            case "9" -> {
                controller.generateDailyQueueReport();
                pressEnterToContinue();
            }
            case "0" -> {                                     
                System.out.println(" Exiting... Goodbye!");
                return;
            }
            default -> System.out.println(" Invalid option. Please select 0-9.");
        }
    }
}
    
 //-----------------------------------------------------------------------------------------------------------------//  
    
    
    
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
                System.out.println("\n‼ REGISTRATION ERROR: " + e.getMessage());
                if (!promptRetry()) return null;
            }
        }
    }
    
    
    
//-----------------------------------------------------------------------------------------------------------------//  

    
    
    public void showRegistrationSummary(Patient patient) {
        System.out.println("\n========== REGISTRATION SUCCESS ==========");
        System.out.println("| Name: " + padRight(patient.getName(), 40) + "|");
        System.out.println("| Patient ID: " + padRight(patient.getPatientID(), 35) + "|");
        System.out.println("| Queue ID: " + padRight(patient.getQueueID(), 36) + "|");
        System.out.println("| Registration Date: " + padRight(patient.getRegistrationDate(), 28) + "|");
        System.out.println("==========================================");
    }
    

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void pressEnterToContinue() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }


    public void addToQueue() {
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
    
    
    
 //-----------------------------------------------------------------------------------------------------------------//  
    
    
    
    public void showQueuePosition(int position, int patientsAhead) {
    System.out.println("\n========== QUEUE POSITION ==========");
    System.out.println("Your Queue ID: Q" + String.format("%03d", position));
    System.out.println("Patients ahead of you: " + patientsAhead);
    
    // Calculate estimated wait time (15 mins per patient)
    int waitMinutes = patientsAhead * 15;
    System.out.printf("Estimated wait time: ~%d minutes\n", waitMinutes);
    System.out.println("===================================");
}
    
    
    
    
 //-----------------------------------------------------------------------------------------------------------------//  
    
    
    
    
    public void displayQueueStatus(int position, LinkedQueue<Patient> nextPatients) {
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
    
    
    
 //-----------------------------------------------------------------------------------------------------------------//  


    
    public void managePatient() {
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
            case "2" -> updatePatient(patient);
            case "3" -> {
                if (patient.getQueueID() == null) {
                    String result = controller.handleAddToQueue(patient.getIdentificationNo(), this);
                    System.out.println(result);
                } else {
                    System.out.println("ℹ Patient is already in queue");
                }
                pressEnterToContinue();
            }
            case "4" -> {
                if (deletePatient(patient)) {
                    return; // Exit if deleted
                }
            }
            case "5" -> { return; }
            default -> System.out.println("❌ Invalid option");
        }
    }
}
    
    
    
 //-----------------------------------------------------------------------------------------------------------------//  


    

private void displayPatientDetails(Patient p) {
    System.out.println("\n=== PATIENT DETAILS ===");
    System.out.println("Patient ID: " + p.getPatientID());
    System.out.println("Queue ID: " + (p.getQueueID() != null ? p.getQueueID() : "Not in queue"));
    System.out.println("Name: " + p.getName());
    System.out.println("IC Number: " + p.getIdentificationNo());
    System.out.println("Contact: " + p.getContactInfo());
    System.out.println("Date of Birth: " + p.getDateOfBirth());
    System.out.println("Age: " + controller.calculateAge(p.getDateOfBirth()));
    System.out.println("Gender: " + p.getGender());
    System.out.println("Blood Type: " + p.getBloodType());
    System.out.println("Allergies: " + (p.getAllergies().isEmpty() ? "None" : p.getAllergies()));
    System.out.println("Weight: " + p.getWeight() + " kg");
    System.out.println("Height: " + p.getHeight() + " cm");
    System.out.println("BMI: " + String.format("%.1f", p.calculateBMI()) + " (" + interpretBMI(p.calculateBMI()) + ")");
    System.out.println("Registration Date: " + p.getRegistrationDate());
    
    // Queue status if applicable
    if (p.getQueueID() != null) {
        System.out.println("\n=== QUEUE STATUS ===");
        System.out.println("Time in queue: " + p.getTimeInQueueMinutes() + " minutes");
    }
}


 //-----------------------------------------------------------------------------------------------------------------//  

// Helper method to interpret BMI value
private String interpretBMI(double bmi) {
    if (bmi < 18.5) return "Underweight";
    else if (bmi < 25) return "Normal";
    else if (bmi < 30) return "Overweight";
    else return "Obese";
}

 //-----------------------------------------------------------------------------------------------------------------//  



private void updatePatient(Patient patient) {
    System.out.println("\n=== UPDATE PATIENT ===");
    System.out.println("Leave field blank to keep current value");
    
    // Collect all inputs in the desired order
    String newName = prompt("Name [" + patient.getName() + "]: ");
    String newContact = prompt("Contact [" + patient.getContactInfo() + "]: ");
    String newWeight = prompt("Weight (kg) [" + patient.getWeight() + "]: ");
    String newHeight = prompt("Height (cm) [" + patient.getHeight() + "]: ");
    String newAllergies = prompt("Allergies [" + 
        (patient.getAllergies().isEmpty() ? "None" : patient.getAllergies()) + "]: ");
    
    try {
        controller.updatePatient(patient, newName, newContact, 
                              newHeight, newWeight, newAllergies);
        System.out.println("Patient updated successfully");
    } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
        if (promptRetry()) {
            updatePatient(patient); // Recursive retry
        }
    }
}



 //-----------------------------------------------------------------------------------------------------------------//  




private boolean deletePatient(Patient patient) {
    String confirm = prompt("Confirm delete? (Y/N): ", null, null);
    if (confirm.equalsIgnoreCase("Y")) {
        return controller.deletePatient(patient);
    }
    return false;
}



 //-----------------------------------------------------------------------------------------------------------------//  



public void showAllPatientsMenu() {
    clearScreen();
    System.out.println("\n=== PATIENT DATABASE OPERATIONS ===");
    System.out.println("1. View All Patients");
    System.out.println("2. Filter Patients");
    System.out.println("3. Sort Patients");
    System.out.println("4. Back to Main Menu");
    System.out.print("Select option: ");
}


 //-----------------------------------------------------------------------------------------------------------------//  

public void handlePatientDatabaseOperations() {
    while (true) {
        showAllPatientsMenu();  // Shows the sub-menu
        String choice = scanner.nextLine();
        
        switch (choice) {
            case "1" -> {
                controller.displayAllPatients(this);
                pressEnterToContinue();  // Only one press enter here
            }
            case "2" -> controller.handleFilterPatients(this);
            case "3" -> controller.handleSortPatients(this);
            case "4" -> { return; }  // Return to main menu
            default -> System.out.println("! Invalid option. Please select 1-4.");
        }
    }
}



 //-----------------------------------------------------------------------------------------------------------------//  



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


 //-----------------------------------------------------------------------------------------------------------------//  


public void showSortOptions() {
    clearScreen();
    System.out.println("\n=== SORT PATIENTS ===");
    System.out.println("1. By Name");
    System.out.println("2. By Age");
    System.out.println("3. By Queue Position");
    System.out.println("4. Back");
    System.out.print("Select option: ");
}



 //-----------------------------------------------------------------------------------------------------------------//  


public void showMessage(String message) {
    System.out.println(message);
}

 //-----------------------------------------------------------------------------------------------------------------//  

public int getIntInput() {
    while (true) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
}

 //-----------------------------------------------------------------------------------------------------------------//  



public String getStringInput() {
    return scanner.nextLine();
}


 //-----------------------------------------------------------------------------------------------------------------//  



private String prompt(String message) {
    System.out.print(message);
    return scanner.nextLine().trim();
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

    
    
//-----------------------------------------------------------------------------------------------------------------//      

    public static void main(String[] args) {
        PatientManager manager = new PatientManager();
        
        PatientUI ui = new PatientUI(manager);
        ui.displayMenu();
    }
}