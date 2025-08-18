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
import adt.QueueInterface;
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
        System.out.println("10.          Filter Consultation            ");
        System.out.println("0.                  Exit                   ");
        System.out.print("Enter your choice: ");
        
        choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

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

        // Auto-generate consultation ID
        String consultationId = consultationManager.generateNewId();

        System.out.print("Doctor ID (e.g., D202): ");
        String doctorId = scanner.nextLine().trim();
        
        System.out.print("Patient ID (e.g., P101): ");
        String patientId = scanner.nextLine().trim();

        // Date (loop until valid)
        LocalDate consultationDate = null;
        while (consultationDate == null) {
        try {
            System.out.print("Date (YYYY-MM-DD): ");
            consultationDate = LocalDate.parse(scanner.nextLine().trim());
         } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Invalid date format. Use YYYY-MM-DD.");
             }
         }

       
        // Time (loop until valid)
        LocalTime consultationTime = null;
        while (consultationTime == null) {
        try {
            System.out.print("Time (HH:MM): ");
            consultationTime = LocalTime.parse(scanner.nextLine().trim());
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Invalid time format. Use HH:MM.");
           }
        }
     

        System.out.print("Status (e.g., Pending/Scheduled): ");
        String status = scanner.nextLine().trim();
        if (status.isEmpty()) status = "Scheduled";

        System.out.print("Type (e.g., General): ");
        String type = scanner.nextLine().trim();
        if (type.isEmpty()) type = "General";
        
        System.out.print("Enter notes: ");
        String consultationNotes = scanner.nextLine().trim();

        // Create the consultation object
        Consultation c = new Consultation(
            consultationId,
            doctorId.toUpperCase(),
            patientId.toUpperCase(),
            consultationDate,
            consultationTime,
            status,
            type,
            false,
            consultationNotes    
        );

        consultationManager.addConsultation(c);
        System.out.println("\nConsultation added successfully.");

    } catch (IllegalArgumentException | IllegalStateException e) {
        System.out.println(e.getMessage());
    }
}

//=========== UPDATE CONSULTATION STATUS UI ====================================================================================

    private void updateConsultationStatus() {
    System.out.print("Enter Consultation ID to update: ");
    String consultationId = scanner.nextLine();

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
    String consultationId = scanner.nextLine();

    boolean success = consultationManager.updateConsultationStatus(consultationId, "Completed");
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
        String consultationId = scanner.nextLine(); // now it's String

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

//=========== CANCEL CONSULTATION UI ===================================================================================================

    private void cancelConsultation() {
     Scanner scanner = new Scanner(System.in);

    try {
        // 1. Display current consultations
        System.out.println("Current Consultations:");
        Consultation[] consultations = consultationManager.getAllConsultations();
        for (Consultation c : consultations) {
            System.out.println("ID: " + c.getConsultationId() +
                    " | Patient: " + c.getPatientId() +
                    " | Doctor: " + c.getDoctorId() +
                    " | Status: " + c.getConsultationStatus());
        }

        // 2. Get consultation ID to cancel (now String)
        System.out.print("Enter Consultation ID to cancel: ");
        String consultationId = scanner.nextLine().trim();

        // 3. Confirm cancellation
        System.out.print("\nAre you sure you want to cancel this consultation? (Y/N): ");
        String confirmation = scanner.nextLine().trim().toUpperCase();

        if (!confirmation.equals("Y")) {
            System.out.println("\nCancellation aborted.");
            return;
        }

        // 4. Attempt cancellation (must update deleteConsultation to accept String)
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
    System.out.print("Enter consultation ID: ");
    String id = scanner.nextLine();
    Consultation c = consultationManager.getConsultationByID(id);
    if (c != null) {
        System.out.println(c);
    } else {
        System.out.println("\nConsultation not found.");
    }
}



//=========== FILTER CONSULTATIONS UI  ==================================================================================================
    private void filterConsultations() {
    try {
        System.out.println("\n--- Filter Consultations ---");
        System.out.println("Leave a field blank if you don't want to filter by it.");

        // 1. Get Doctor ID
        System.out.print("Doctor ID (e.g., D202): ");
        String doctorId = scanner.nextLine().trim();
        if (doctorId.isEmpty()) doctorId = null;

        // 2. Get Patient ID
        System.out.print("Patient ID (e.g., P101): ");
        String patientId = scanner.nextLine().trim();
        if (patientId.isEmpty()) patientId = null;

        // 3. Get Status
        System.out.print("Status (Pending/In Progress/On Hold/Completed/Cancelled): ");
        String status = scanner.nextLine().trim();
        if (status.isEmpty()) status = null;

        // 4. Get Date
        LocalDate date = null;
        System.out.print("Date (YYYY-MM-DD): ");
        String dateInput = scanner.nextLine().trim();
        if (!dateInput.isEmpty()) {
            try {
                date = LocalDate.parse(dateInput);
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Invalid date format. Skipping date filter.");
            }
        }

        // 5. Get filtered results
        QueueInterface<Consultation> results = consultationManager.searchConsultations(
                patientId, doctorId, status, null, date
        );

        // 6. Display results in the same format as displayConsultations
        if (results.isEmpty()) {
            System.out.println("\nNo consultations found with the given filters.");
            return;
        }

        System.out.println("\nFiltered Consultations:");
        System.out.println("________________________________________________________________________________________________________");
        System.out.printf("%-5s %-10s %-10s %-12s %-8s %-10s %-12s %-10s %-20s%n",
                "ID", "Doctor", "Patient", "Date", "Time", "Status", "Type", "Follow-up", "Notes");
        System.out.println("________________________________________________________________________________________________________");

        for (Consultation c : results.toArray(new Consultation[0])) {
            System.out.printf("%-5s %-10s %-10s %-12s %-8s %-10s %-12s %-10s %-20s%n",
                    c.getConsultationId(),
                    c.getDoctorId(),
                    c.getPatientId(),
                    c.getConsultationDate(),
                    c.getConsultationTime(),
                    c.getConsultationStatus(),
                    c.getConsultationType(),
                    (c.isFollowUpRequired() ? "Yes" : "No"),
                    (c.getConsultationNotes().isEmpty() ? "None" : c.getConsultationNotes()));
        }
        System.out.println("________________________________________________________________________________________________________");

    } catch (Exception e) {
        System.out.println("Error filtering consultations: " + e.getMessage());
    }
    }

//=========== MARK FOLLOW-UP REQUIRED UI ====================================================================================================
    private void markFollowUpRequired() {
    System.out.print("Enter Consultation ID: ");
    String consultationId = scanner.nextLine().trim();

    Consultation c = consultationManager.getConsultationByID(consultationId);
    if (c == null) {
        System.out.println("\nConsultation not found.");
        return;
    }

    System.out.println("Current follow-up status: " +
            (c.isFollowUpRequired() ? "Yes" : "No"));

    System.out.print("\nDo you want to mark this consultation as requiring follow-up? (Y/N): ");
    String choice = scanner.nextLine().trim();

    if (choice.equalsIgnoreCase("Y")) {
        c.setFollowUpRequired(true);
        System.out.println("\nFollow-up marked as required.");
    } else {
        c.setFollowUpRequired(false);
        System.out.println("\nFollow-up marked as not required.");
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
