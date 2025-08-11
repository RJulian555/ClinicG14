/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;


import control.DoctorManager;
import entity.Doctor;
import java.util.Scanner;
/**
 *
 * @author user
 */

public class DoctorUI {
    private DoctorManager doctorManager;
    private Scanner scanner;
    public DoctorUI(DoctorManager manager) {
        this.doctorManager = manager;
        this.scanner = new Scanner(System.in);
    }
    
    
    public void displayMainMenu() {
    while (true) {
        // Clear console (works for most terminals)
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        // Display dashboard first
        //displayDashboard();
        
        System.out.println("\n|-------------------------------------|");
        System.out.println("|          MAIN MENU OPTIONS          |");
        System.out.println("|-------------------------------------|");
        System.out.println("| 1. Add New Doctor                   |");
        System.out.println("| 2. View All Doctors                 |");
        System.out.println("| 3. View Senior Doctors              |");
        System.out.println("| 4. Search Doctor by ID              |");
        System.out.println("| 5. Doctor Specialization Report     |");
        System.out.println("| 6. Get Next Available Doctor        |");
        System.out.println("| 7. Manage Doctor Leaves             |");
        System.out.println("| 8. Exit                             |");
        System.out.println("|_____________________________________|");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addDoctorUI();
                case 2 -> displayAllDoctorsUI();
                case 3 -> displaySeniorDoctorsUI();
                case 4 -> searchDoctorUI();
                case 5 -> displaySpecializationReportUI();
                case 6 -> getNextAvailableDoctorUI();
                case 7 -> manageLeavesUI();
                case 8 -> { return; }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }

    //-----------------------------------------------------------------------------------------------------------------//  

    private void addDoctorUI() {
        System.out.println("\nAdd New Doctor");
        Doctor doctor = inputDoctorDetails();
        try {
            doctorManager.addDoctor(doctor);
            System.out.println("Doctor added successfully!");
            displayDoctorCard(doctor);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    //-----------------------------------------------------------------------------------------------------------------//  

    private Doctor inputDoctorDetails() {
        System.out.println("\nAdd New Doctor (enter 'cancel' at any time to exit)");
    
    // Doctor ID
    //System.out.print("Enter Doctor ID: ");
    //String id = scanner.nextLine();
    //if (id.equalsIgnoreCase("cancel")) return null;
    
//TODO make the id automatically assigned
    // Automatically generate ID
    String id = doctorManager.generateDoctorID();
    System.out.println("Automatically assigned Doctor ID: " + id);
    
    
    // Name
    System.out.print("Enter Name: ");
    String name = scanner.nextLine();
    if (name.equalsIgnoreCase("cancel")) return null;
    
    // Specialization
    //TODO :give choice for specialization so faster but also give choice to type it in
    System.out.println("\nSelect Specialization:");
    System.out.println(" 1. Cardiology");
    System.out.println(" 2. Pediatrics");
    System.out.println(" 3. Neurology");
    System.out.println(" 4. Orthopedics");
    System.out.println(" 5. Dermatology");
    System.out.println(" 6. General Surgery");
    System.out.println(" 7. Emergency Medicine");
    System.out.println(" 8. Oncology");
    System.out.println(" 9. Psychiatry");
    System.out.println("10. Ophthalmology");
    System.out.println("11. Other (enter custom)");
    System.out.print("Enter choice (1-11): ");

    String specialization;
    while (true) {
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("cancel")) return null;

        switch (choice) {
            case "1": specialization = "Cardiology"; break;
            case "2": specialization = "Pediatrics"; break;
            case "3": specialization = "Neurology"; break;
            case "4": specialization = "Orthopedics"; break;
            case "5": specialization = "Dermatology"; break;
            case "6": specialization = "General Surgery"; break;
            case "7": specialization = "Emergency Medicine"; break;
            case "8": specialization = "Oncology"; break;
            case "9": specialization = "Psychiatry"; break;
            case "10": specialization = "Ophthalmology"; break;
            case "11":
                System.out.print("Enter custom specialization: ");
                specialization = scanner.nextLine();
                if (specialization.trim().isEmpty()) {
                    System.out.println("Specialization cannot be empty!");
                    continue;
                }
                break;
            default:
                System.out.println("Invalid choice! Enter 1-11:");
                continue;
        }
        break;
    }

    Doctor doctor = new Doctor(id, name, specialization);

    
    // Contact Number with validation
    while (true) {
        System.out.print("Contact Number (10 digits): ");
        String contact = scanner.nextLine();
        if (contact.equalsIgnoreCase("cancel")) return null;
        
        if (contact.matches("\\d{10}")) {
            doctor.setContactNumber(contact);
            break;
        }
        System.out.println("Invalid contact number. Please enter 10 digits.");
    }
    
    // Years of Experience with validation
    while (true) {
        System.out.print("Years of Experience: ");
        String expInput = scanner.nextLine();
        if (expInput.equalsIgnoreCase("cancel")) return null;
        
        try {
            int years = Integer.parseInt(expInput);
            if (years >= 0) {
                doctor.setYearsOfExperience(years);
                break;
            }
            System.out.println("Please enter a positive number.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Please try again.");
        }
    }
    
    // Consultation Fee with validation
    while (true) {
        System.out.print("Consultation Fee: ");
        String feeInput = scanner.nextLine();
        if (feeInput.equalsIgnoreCase("cancel")) return null;
        
        try {
            double fee = Double.parseDouble(feeInput);
            if (fee >= 0) {
                doctor.setConsultationFee(fee);
                break;
            }
            System.out.println("Please enter a positive amount.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please try again.");
        }
    }
    
    // Availability
    while (true) {
        System.out.print("Is Available (true/false): ");
        String availableInput = scanner.nextLine();
        if (availableInput.equalsIgnoreCase("cancel")) return null;
        
        if (availableInput.equalsIgnoreCase("true")) {
            doctor.setAvailable(true);
            break;
        } else if (availableInput.equalsIgnoreCase("false")) {
            doctor.setAvailable(false);
            break;
        }
        System.out.println("Please enter 'true' or 'false'.");
    }
    
    // On Leave status
    while (true) {
        System.out.print("Is On Leave (true/false): ");
        String leaveInput = scanner.nextLine();
        if (leaveInput.equalsIgnoreCase("cancel")) return null;
        
        if (leaveInput.equalsIgnoreCase("true")) {
            doctor.setOnLeave(true);
            break;
        } else if (leaveInput.equalsIgnoreCase("false")) {
            doctor.setOnLeave(false);
            break;
        }
        System.out.println("Please enter 'true' or 'false'.");
    }
    
    // In inputDoctorDetails() after setting onLeave status:
    if (doctor.isOnLeave() && (doctor.getLeaveDates() == null || doctor.getLeaveDates().length == 0)) {
        System.out.print("Enter leave date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        doctor.addLeaveDate(date);
    }
    
    System.out.println("Doctor added successfully!");
    return doctor;
    }
    //-----------------------------------------------------------------------------------------------------------------//  

    private void displayAllDoctorsUI() {
        System.out.println("\nAll Doctors");
        Doctor[] doctors = doctorManager.getAllDoctors();
        
        if (doctors.length == 0) {
            System.out.println("No doctors found!");
            return;
        }
        
        for (Doctor doctor : doctors) {
            displayDoctorCard(doctor);
            System.out.println("---------------------");
        }
    }
    //-----------------------------------------------------------------------------------------------------------------//  

    private void displayDoctorCard(Doctor doctor) {
        System.out.println("\nID: " + doctor.getDoctorID());
        System.out.println("Name: " + doctor.getName());
        System.out.println("Specialization: " + doctor.getSpecialization());
        System.out.println("Contact: " + doctor.getContactNumber());
        System.out.println("Experience: " + doctor.getYearsOfExperience() + " years");
        System.out.printf("Fee: RM%.2f\n", doctor.getConsultationFee());
        System.out.println("Available: " + (doctor.isAvailable() ? "Yes" : "No"));
        System.out.println("On Leave: " + (doctor.isOnLeave() ? "Yes" : "No"));
    }
    //-----------------------------------------------------------------------------------------------------------------//  

    private void searchDoctorUI() {
        System.out.print("\nEnter Doctor ID to search: ");
        String id = scanner.nextLine();
        
        Doctor doctor = doctorManager.getDoctorByID(id);
        if (doctor != null) {
            System.out.println("\nSearch Result:");
            displayDoctorCard(doctor);
            
            System.out.println("\n1. Update Details");
            System.out.println("2. Remove Doctor");
            System.out.println("3. Back to Menu");
            System.out.print("Choose action: ");
            
            int action = scanner.nextInt();
            scanner.nextLine();
            
            if (action == 1) {
                updateDoctorUI(doctor);
            } else if (action == 2) {
                if (doctorManager.removeDoctor(id)) {
                    System.out.println("Doctor removed successfully!");
                }
            }
        } else {
            System.out.println("Doctor not found!");
        }
    }
    //-----------------------------------------------------------------------------------------------------------------//  

    private void updateDoctorUI(Doctor oldDoctor) {
        System.out.println("\n Update Doctor Details");
        Doctor newDetails = inputDoctorDetails();
        
        if (doctorManager.updateDoctor(oldDoctor.getDoctorID(), newDetails)) {
            System.out.println("Doctor updated successfully!");
        } else {
            System.out.println("Failed to update doctor!");
        }
    }
    //-----------------------------------------------------------------------------------------------------------------//  

    private void displaySpecializationReportUI() {
        System.out.println("\nSpecialization Distribution");
        System.out.println(doctorManager.generateSpecializationReport());
    }
    //-----------------------------------------------------------------------------------------------------------------//  

    private void displaySeniorDoctorsUI() {
        System.out.print("\nEnter minimum years of experience: ");
        int years = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println(doctorManager.generateSeniorDoctorsReport(years));
    }
    
//-----------------------------------------------------------------------------------------------------------------//

private void manageLeavesUI() {
    while (true) {
        System.out.println("\nDOCTOR LEAVE MANAGEMENT");
        System.out.println("1. View All Leave Schedules");
        System.out.println("2. Register New Leave");
        System.out.println("3. End Leave Period");
        System.out.println("4. Update Leave Statuses");
        System.out.println("5. Back to Main Menu");
        System.out.print("Choose option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        switch (choice) {
            case 1 -> {
                System.out.println(doctorManager.viewAllLeaves());
            }
            case 2 -> {
                System.out.print("Enter Doctor ID: ");
                String id = scanner.nextLine();
                System.out.print("Enter Leave Date (YYYY-MM-DD): ");
                String date = scanner.nextLine();
                if (doctorManager.registerLeave(id, date)) {
                    System.out.println("Leave registered successfully!");
                } else {
                    System.out.println("Failed to register leave!");
                }
            }
            case 3 -> {
                System.out.print("Enter Doctor ID: ");
                String endId = scanner.nextLine();
                System.out.print("Enter Leave Date to End (YYYY-MM-DD): ");
                String endDate = scanner.nextLine();
                if (doctorManager.endLeave(endId, endDate)) {
                    System.out.println("Leave ended successfully!");
                } else {
                    System.out.println("Failed to end leave!");
                }
            }
            case 4 -> {
                doctorManager.processLeaveStatusUpdates();
                System.out.println("Leave statuses updated based on current date!");
            }
            case 5 -> { return; }
            default -> System.out.println("Invalid choice!");
        }
    }
}
    
    
//-----------------------------------------------------------------------------------------------------------------//  

    private void getNextAvailableDoctorUI() {
        System.out.println("\n Finding next available doctor...");
        Doctor availableDoctor = doctorManager.getNextAvailableDoctorBySpecialization(scanner);
        
        if (availableDoctor != null) {
            System.out.println(" \nThe next available doctor would be : "  +  availableDoctor.getName());
      
        } else {
            System.out.println("No available doctors at the moment!");
        } 
    }
}

