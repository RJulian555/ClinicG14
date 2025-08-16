/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;
import control.ConsultationManager;
import entity.Consultation;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author LESTER
 */
public class ConsultationUI {
    private ConsultationManager consultationManager;
    private Scanner scanner;
    
    public ConsultationUI(ConsultationManager manager) {
        this.consultationManager = manager;
        this.scanner = new Scanner(System.in); 
    } 
    
    public void displayMainMenu() {
    int choice;
    do {
        System.out.println("\n----------------------------------------");
        System.out.println("\n ---       Consultation Menu ---        ");
        System.out.println("\n----------------------------------------");
        System.out.println("1.           View Consultations           ");
        System.out.println("2.            Add Consultation            ");
        System.out.println("3.        Reschedule Consultation         ");
        System.out.println("4.          Cancel Consultation           ");
        System.out.println("0.                Exit                    ");
        System.out.print("Enter your choice: ");
        
        choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                displayConsultations();
                break;
            case 2:
                addConsultation();
                break;
            case 3:
                rescheduleConsultation();
                break;
            case 4:
                cancelConsultation();
                break;
            case 0:
                System.out.println("Exiting Consultation Menu.");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    } while (choice != 0);
}

private void displayConsultations() {
     Consultation[] consultations = consultationManager.getAllConsultations();
    if (consultations.length == 0) {
        System.out.println("No consultations available.");
        return;
    }
    for (Consultation c : consultations) {
        System.out.println("ID: " + c.getConsultationId() +
                           ", Doctor ID: " + c.getDoctorId() +
                           ", Patient ID: " + c.getPatientId() +
                           ", Date: " + c.getConsultationDate() +
                           ", Time: " + c.getConsultationTime() +
                           ", Status: " + c.getConsultationStatus());
    }
}

private void addConsultation() {
    try {
        System.out.println("\n--- Add Consultation ---");

        System.out.print("Consultation ID (int): ");
        int consultationId = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Patient ID (int): ");
        int patientId = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Doctor ID (int): ");
        int doctorId = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Date (YYYY-MM-DD): ");
        LocalDate consultationDate = LocalDate.parse(scanner.nextLine().trim());

        System.out.print("Time (HH:MM): ");
        LocalTime consultationTime = LocalTime.parse(scanner.nextLine().trim());

        System.out.print("Status (e.g., Pending/Scheduled): ");
        String status = scanner.nextLine().trim();
        if (status.isEmpty()) status = "Pending";

        System.out.print("Type (e.g., General/Follow-up): ");
        String type = scanner.nextLine().trim();
        if (type.isEmpty()) type = "General";

        System.out.print("Is follow-up required? (yes/no): ");
        boolean followUpRequired = scanner.nextLine().trim().equalsIgnoreCase("yes");

        // Create the consultation object
        Consultation c = new Consultation(
            consultationId,
            patientId,
            doctorId,
            consultationDate,
            consultationTime,
            status,
            type,
            followUpRequired
        );

        consultationManager.addConsultation(c);
        System.out.println("✅ Consultation added successfully.");

    } catch (java.time.format.DateTimeParseException e) {
        System.out.println("❌ Invalid date/time format. Use YYYY-MM-DD and HH:MM.");
    } catch (NumberFormatException e) {
        System.out.println("❌ IDs must be integers.");
    } catch (IllegalArgumentException | IllegalStateException e) {
        System.out.println("❌ " + e.getMessage());
    }
}

private void rescheduleConsultation() {
     Scanner scanner = new Scanner(System.in);
    
    try {
        // 1. Display current consultations
        System.out.println("\nCurrent Consultations:");
        Consultation[] consultations = consultationManager.getAllConsultations();
        for (Consultation c : consultations) {
            System.out.println("ID: " + c.getConsultationId() + 
                           " | Date: " + c.getConsultationDate() + 
                           " | Time: " + c.getConsultationTime() + 
                           " | Status: " + c.getConsultationStatus());
        }
        
        // 2. Get consultation ID to reschedule
        System.out.print("\nEnter Consultation ID to reschedule: ");
        int consultationId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        // 3. Verify consultation exists
        Consultation existing = consultationManager.getConsultationByID(consultationId);
        if (existing == null) {
            System.out.println("Consultation with ID " + consultationId + " not found!");
            return;
        }
        
        // 4. Get new date and time
        System.out.print("Enter new date (YYYY-MM-DD): ");
        LocalDate newDate = LocalDate.parse(scanner.nextLine());
        
        System.out.print("Enter new time (HH:MM): ");
        LocalTime newTime = LocalTime.parse(scanner.nextLine());
        
        // 5. Attempt reschedule
        boolean success = consultationManager.rescheduleConsultation(
            consultationId, newTime, newDate);
        
        // 6. Display result
        if (success) {
            System.out.println("Consultation rescheduled successfully!");
            System.out.println("New Date: " + newDate);
            System.out.println("New Time: " + newTime);
        } else {
            System.out.println("Failed to reschedule consultation.");
        }
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        System.out.println("Please enter valid input format.");
    }
}

private void cancelConsultation() {
     Scanner scanner = new Scanner(System.in);
    
    try {
        // 1. Display current consultations
        System.out.println("\nCurrent Consultations:");
        Consultation[] consultations = consultationManager.getAllConsultations();
        for (Consultation c : consultations) {
            System.out.println("ID: " + c.getConsultationId() + 
                           " | Patient: " + c.getPatientId() + 
                           " | Doctor: " + c.getDoctorId() + 
                           " | Status: " + c.getConsultationStatus());
        }
        
        // 2. Get consultation ID to cancel
        System.out.print("\nEnter Consultation ID to cancel: ");
        int consultationId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        // 3. Confirm cancellation
        System.out.print("Are you sure you want to cancel this consultation? (Y/N): ");
        String confirmation = scanner.nextLine().trim().toUpperCase();
        
        if (!confirmation.equals("Y")) {
            System.out.println("Cancellation aborted.");
            return;
        }
        
        // 4. Attempt cancellation
        boolean success = consultationManager.deleteConsultation(consultationId);
        
        // 5. Display result
        if (success) {
            System.out.println("Consultation cancelled successfully!");
        } else {
            System.out.println("Failed to cancel consultation. ID not found.");
        }
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
    
    
}
