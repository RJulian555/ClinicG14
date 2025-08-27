/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;
import control.ConsultationManager;
import control.DoctorManager;
import control.PatientManager;
import entity.Consultation;
import entity.Patient;
import entity.Doctor;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import adt.QueueInterface;
/**
 *
 * @author LESTER
 */
public class ConsultationUI {
    private ConsultationManager consultationManager;
    private DoctorManager doctorManager;
    private PatientManager patientManager;
    private Scanner scanner;
    
    public ConsultationUI(ConsultationManager manager, DoctorManager doctorManager, PatientManager patientManager) {
    this.consultationManager = manager;
    this.doctorManager = doctorManager;
    this.patientManager = patientManager;
    this.scanner = new Scanner(System.in); 
}
    
    public void displayMainMenu() {
    int choice = -1;
    do {
        System.out.println("\n-----------------------------------------");
        System.out.println("\n---         Consultation Menu         ---");
        System.out.println("\n-----------------------------------------");
        System.out.println("1.         View All Consultations          ");
        System.out.println("2.          Search Consultation            ");
        System.out.println("3.          Add New Consultation           ");
        System.out.println("4.       Update Consultation Notes         ");
        System.out.println("5.       Update Consultation Status        ");
        System.out.println("6.        Reschedule Consultation          ");
        System.out.println("7.          Cancel Consultation            ");
        System.out.println("8.      Mark Consultation as Complete      ");
        System.out.println("9. Mark Consultation as Follow-up Required ");
        System.out.println("10.          Filter Consultation           ");
        System.out.println("11.       Doctor Completion Report         ");
        System.out.println("12.     Patient Consultation History       ");
        System.out.println("0.                  Exit                   ");
        System.out.print("Enter your choice: ");
        
        String input = scanner.nextLine().trim();
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            continue;
        }

        switch (choice) {
            case 1:
                displayConsultations();
                break;
            case 2:
                searchConsultations();
                break;
            case 3:
                addConsultation();
                break;
            case 4:
                updateConsultationNotes();
                break;
            case 5:
                updateConsultationStatus();
                break;    
            case 6:
                rescheduleConsultation();
                break;
            case 7:
                cancelConsultation();
                break;
            case 8:
                completeConsultation();
                break;    
            case 9:
                markFollowUpRequired();
                break;
            case 10:    
                filterConsultations();
                break;
            case 11:
                System.out.println(consultationManager.generateDoctorCompletionReport());
                break;
            case 12:
                System.out.println(consultationManager.generatePatientHistoryReport());
                break;
            case 0:
                System.out.println("Exiting Consultation Menu.");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    } while (choice != 0);
}

//=========== DISPLAY ALL CONSULTATION UI ====================================================================================   
    
private void displayConsultations() {
   consultationManager.displayConsultations();
}
//=========== ADD CONSULTATION UI =============================================================================================
private void addConsultation() {
    try {
        System.out.println("\n--- Add Consultation ---");

        // Show available doctors first
        System.out.println("View available doctors? (Y/N): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("y")) {
            consultationManager.displayAvailableDoctors();
        } else {
            System.out.println("Skipping doctor availability check.");
        }

        // Auto-generate consultation ID
        String consultationId = consultationManager.generateNewId();

        System.out.print("Doctor ID (e.g., D202): ");
        String doctorId = scanner.nextLine().trim();
        
        // Check doctor availability
        if (!consultationManager.isDoctorAvailable(doctorId)) {
            System.out.println("Doctor is not available or has scheduling conflicts!");
            return;
        }

        // Get the next patient from the queue
        Patient nextPatient = patientManager.getFirstInQueue();
        if (nextPatient == null) {
            System.out.println("No patients in the queue.");
            return;
        }

        // Date input
        LocalDate consultationDate = null;
        while (consultationDate == null) {
            try {
                System.out.print("Date (YYYY-MM-DD): ");
                consultationDate = LocalDate.parse(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid date format. Use YYYY-MM-DD.");
            }
        }

        // Time input
        LocalTime consultationTime = null;
        while (consultationTime == null) {
            try {
                System.out.print("Time (HH:MM): ");
                consultationTime = LocalTime.parse(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid time format. Use HH:MM.");
            }
        }

        System.out.print("Type (e.g., General, Follow-up, Emergency): ");
        String type = scanner.nextLine().trim();
        if (type.isEmpty()) type = "General";
        
        System.out.print("Enter initial notes: ");
        String consultationNotes = scanner.nextLine().trim();

        // Create consultation object
        Consultation c = new Consultation(
            consultationId,
            doctorId.toUpperCase(),
            nextPatient.getPatientID().toUpperCase(),
            consultationDate,
            consultationTime,
            "Scheduled", // Default status
            type,
            false,
            consultationNotes    
        );

        if (consultationManager.addConsultation(c)) {
            System.out.println("\nConsultation added successfully.");
            patientManager.processNextPatient(); // Remove patient from queue after consultation is scheduled
        } else {
            System.out.println("\nFailed to add consultation.");
        }

    } catch (IllegalArgumentException | IllegalStateException e) {
        System.out.println("Error: " + e.getMessage());
    }
}

//=========== UPDATE CONSULTATION STATUS UI ====================================================================================

    private void updateConsultationStatus() {
    System.out.print("Enter Consultation ID to update: ");
    String consultationId = scanner.nextLine().trim().toUpperCase();

    Consultation c = consultationManager.getConsultationByID(consultationId);
    if (c == null) {
        System.out.println("\nConsultation not found.");
        return;
    }

    String[] workingStatuses = {"Pending", "In Progress", "On Hold"};
    System.out.println("Select new status:");
    for (int i = 0; i < workingStatuses.length; i++) {
        System.out.println((i + 1) + ". " + workingStatuses[i]);
    }
    System.out.print("Enter choice (1-" + workingStatuses.length + "): ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // consume newline

    if (choice < 1 || choice > workingStatuses.length) {
        System.out.println("\nInvalid choice. Status not updated.");
        return;
    }

    String newStatus = workingStatuses[choice - 1];
    boolean updated = consultationManager.updateConsultationStatus(consultationId, newStatus);

    if (updated) {
        System.out.println("\nConsultation status updated to " + newStatus + " successfully.");
    } else {
        System.out.println("\nFailed to update consultation status.");
        }
    }

//=========== COMPLETE CONSULTATION UI ========================================================================================

private void completeConsultation() {
    System.out.print("Enter consultation ID to complete: ");
    String consultationId = scanner.nextLine().trim().toUpperCase();

    boolean success = consultationManager.completeConsultation(consultationId);
    if (success) {
        System.out.println("\nConsultation marked as completed.");
    } else {
        System.out.println("\nConsultation not found.");
    }
}

//=========== RESCHEDULE CONSULTATION UI =========================================================================================

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
        String consultationId = scanner.nextLine().trim().toUpperCase(); // Convert to uppercase

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

        // 5. Check doctor availability
        String doctorId = existing.getDoctorId();
        Doctor doctor = doctorManager.getDoctorByID(doctorId);
         if (doctor == null || !doctor.isAvailable()) {
            System.out.println("Rescheduling failed: The assigned doctor is not available.");
            return;
        }

        // 6. Attempt reschedule
        boolean success = consultationManager.rescheduleConsultation(
            consultationId, newDate, newTime);

        // 7. Display result
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

//=========== CANCEL CONSULTATION UI ===================================================================================================
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
        String consultationId = scanner.nextLine().trim().toUpperCase();

        // 3. Confirm cancellation
        System.out.print("\nAre you sure you want to cancel this consultation? (Y/N): ");
        String confirmation = scanner.nextLine().trim().toUpperCase();

        if (!confirmation.equals("Y")) {
            System.out.println("\nCancellation aborted.");
            return;
        }

        // 4. Attempt cancellation
        boolean success = consultationManager.cancelConsultation(consultationId);

        // 5. Display result
        if (success) {
            System.out.println("\nConsultation cancelled successfully!");
        } else {
            System.out.println("\nFailed to cancel consultation. ID not found.");
        }
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}

//=========== SEARCH CONSULTATION UI ==============================================================================================

 private void searchConsultations() {
    System.out.println("\n--- Search Consultation ---");
    while (true) {
        System.out.print("Enter Consultation ID (or type 'exit' to return to the main menu): ");
        String input = scanner.nextLine().trim().toUpperCase();

        if ("exit".equalsIgnoreCase(input)) {
            System.out.println("Returning to the main menu.");
            break;
        }

        Consultation c = consultationManager.getConsultationByID(input);
        if (c != null) {
            consultationManager.displayConsultationDetails(input);
        } else {
            System.out.println("\nConsultation not found. Please try again.");
        }
    }
}

//=========== HELPER METHOD TO DISPLAY QUEUE RESULTS ================================
private void displayConsultationQueue(QueueInterface<Consultation> queue, String title) {
    if (queue.isEmpty()) {
        System.out.println("\nNo consultations found.");
        return;
    }
    
    System.out.println("\n" + title + ":");
    System.out.println("________________________________________________________________________________________________________");
    System.out.printf("%-5s %-10s %-10s %-12s %-8s %-10s %-12s %-10s%n",
            "ID", "Doctor", "Patient", "Date", "Time", "Status", "Type", "Follow-up");
    System.out.println("________________________________________________________________________________________________________");
    
    for (Consultation c : queue.toArray(new Consultation[0])) {
        System.out.printf("%-5s %-10s %-10s %-12s %-8s %-10s %-12s %-10s%n",
                c.getConsultationId(),
                c.getDoctorId(),
                c.getPatientId(),
                c.getConsultationDate(),
                c.getConsultationTime(),
                c.getConsultationStatus(),
                c.getConsultationType(),
                (c.isFollowUpRequired() ? "Yes" : "No"));
    }
    System.out.println("________________________________________________________________________________________________________");
}



//=========== FILTER CONSULTATIONS UI  ==================================================================================================
private void filterConsultations() {
    System.out.println("\n--- Filter Consultations ---");

    //Doctor ID 
    System.out.print("Doctor ID (blank = any): ");
    String doctorId = readOptionalString();
    if (doctorId != null) doctorId = doctorId.toUpperCase();
    
    //Patient ID
    System.out.print("Patient ID (blank = any): ");
    String patientId = readOptionalString();
    if (patientId != null) patientId = patientId.toUpperCase();

    //Status
    String[] allowedStatus = { "Pending", "In Progress", "Completed", "Cancelled", "Rescheduled", "On Hold" };
    String status = pickFromMenu("Status", allowedStatus);

    //Type
    String[] allowedType = { "General", "Follow-up", "Emergency" };
    String type = pickFromMenu("Type", allowedType);

    //Date
    LocalDate date = null;
    while (date == null) {
        System.out.print("Date (YYYY-MM-DD, T=today, blank = any): ");
        String in = scanner.nextLine().trim();
        if (in.isEmpty()) break;
        if (in.equalsIgnoreCase("T")) {
            date = LocalDate.now();
            break;
        }
        try {
            date = LocalDate.parse(in);
        } catch (Exception e) {
            System.out.println("  Invalid date, please try again.");
        }
    }

    /* ---------- Execute search ---------- */
    QueueInterface<Consultation> results = consultationManager.searchConsultations(
            patientId, doctorId, status, type, date);

    if (results.isEmpty()) {
        System.out.println("\nNo consultations matched the given filters.");
    } else {
        displayConsultationQueue(results, "Filtered Consultations");
    }
}

        /* ---------- helper utilities ---------- */

        // Reads a line; returns null if the user just hits ENTER.
        private String readOptionalString() {
            String s = scanner.nextLine().trim();
        return s.isEmpty() ? null : s;
        }

        // Shows a numbered menu and returns the chosen value (or null if blank).
        private String pickFromMenu(String label, String[] choices) {
         while (true) {
        System.out.println(label + ":");
        for (int i = 0; i < choices.length; i++) {
            System.out.printf("   %d) %s%n", i + 1, choices[i]);
        }
        System.out.print("Enter number or leave blank for any: ");
        String in = scanner.nextLine().trim();
        if (in.isEmpty()) return null;
        try {
            int idx = Integer.parseInt(in) - 1;
            if (idx >= 0 && idx < choices.length) return choices[idx];
        } catch (NumberFormatException ignore) {}
        System.out.println("  Invalid choice, please try again.");
    }
}

//=========== MARK FOLLOW-UP REQUIRED UI ====================================================================================================
private void markFollowUpRequired() {
        System.out.print("Enter Consultation ID: ");
        String consultationId = scanner.nextLine().trim().toUpperCase();
    
        Consultation c = consultationManager.getConsultationByID(consultationId);
        if (c == null) {
            System.out.println("\nConsultation not found.");
            return;
        }

        System.out.println("Current follow-up status: " +
                (c.isFollowUpRequired() ? "Yes" : "No"));

        System.out.print("\nToggle follow-up required? (Y/N): ");
        String choice = scanner.nextLine().trim();

        boolean newValue = choice.equalsIgnoreCase("Y");
        boolean success = consultationManager.setFollowUpRequired(consultationId, newValue);

        if (success) {
            System.out.println("\nFollow-up status updated to: " +
                    (newValue ? "Required" : "Not required"));
        } else {
            System.out.println("\nFailed to update follow-up status.");
        }
    }
 //=========== UPDATE CONSULTATION NOTES UI ==============================================
private void updateConsultationNotes() {
    System.out.print("Enter Consultation ID to update notes: ");
    String consultationId = scanner.nextLine().trim();

    // Retrieve the consultation
    Consultation c = consultationManager.getConsultationByID(consultationId);
    if (c == null) {
        System.out.println("\nConsultation not found.");
        return;
    }

    System.out.println("Current notes: " + (c.getConsultationNotes().isEmpty() ? "None" : c.getConsultationNotes()));
    System.out.print("Enter new notes: ");
    String newNotes = scanner.nextLine().trim();

    // Update using manager method
    boolean success = consultationManager.updateConsultationNotes(consultationId, newNotes);
    if (success) {
        System.out.println("\nConsultation notes updated successfully.");
    } else {
        System.out.println("\nFailed to update consultation notes.");
    }
}  
    
}
