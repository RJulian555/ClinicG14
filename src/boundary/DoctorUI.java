package boundary;

import adt.QueueInterface;
import control.DoctorManager;
import entity.Doctor;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

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

            System.out.println("\n|-------------------------------------|");
            System.out.println("|          MAIN MENU OPTIONS          |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 1. Add New Doctor                   |");
            System.out.println("| 2. View All Doctors                 |");
            System.out.println("| 3. View Senior Doctors              |");
            System.out.println("| 4. Search Doctor by ID              |");
            System.out.println("| 5. Doctor Specialization Report     |");
            System.out.println("| 6. Doctor Workload Report           |");
            System.out.println("| 7. Filter Doctors by Workload       |");
            System.out.println("| 8. Manage Doctor Leaves             |");
            System.out.println("| 9. Check Slot Availability          |");
            System.out.println("| 10. Set Duty Shift                  |");
            System.out.println("| 11. Exit                            |");
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
                case 6 -> displayWorkloadReportUI();
                case 7 -> filterDoctorsByWorkloadUI();
                case 8 -> manageLeavesUI();
                case 9 -> checkSlotAvailabilityUI();
                case 10 -> setDutyShiftUI();
                case 11 -> { return; }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }

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

    private Doctor inputDoctorDetails() {
    System.out.println("\nAdd New Doctor (enter 'cancel' at any time to exit)");

    // Automatically generate ID
    String id = doctorManager.generateDoctorID();
    System.out.println("Automatically assigned Doctor ID: " + id);

    System.out.print("Enter Name: ");
    String name = scanner.nextLine();
    if (name.equalsIgnoreCase("cancel")) return null;

    // List of specializations derived from the sample data
    String[] specializations = {
        "Cardiology", "Pediatrics", "Orthopedics", "Neurology", "Oncology",
        "General Surgery", "Dermatology", "Ophthalmology", "Emergency Medicine", "Psychiatry"
    };

    // Display available specializations and let the user choose one
    System.out.println("Choose a specialization from the list below:");
    for (int i = 0; i < specializations.length; i++) {
        System.out.println((i + 1) + ". " + specializations[i]);
    }

    System.out.print("Enter the number corresponding to your choice: ");
    int choice;
    try {
        choice = Integer.parseInt(scanner.nextLine());
        if (choice < 1 || choice > specializations.length) {
            System.out.println("Invalid choice. Please select a valid number.");
            return null;
        }
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number.");
        return null;
    }

    String specialization = specializations[choice - 1];

    System.out.print("Enter Contact Number (10 digits): ");
    String contact = scanner.nextLine();
    if (contact.equalsIgnoreCase("cancel")) return null;

    System.out.print("Enter Years of Experience: ");
    int years;
    try {
        years = Integer.parseInt(scanner.nextLine());
        if (years < 0) {
            System.out.println("Years of experience must be non-negative.");
            return null;
        }
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid number.");
        return null;
    }

    System.out.print("Enter Consultation Fee: ");
    double fee;
    try {
        fee = Double.parseDouble(scanner.nextLine());
        if (fee < 0) {
            System.out.println("Consultation fee must be non-negative.");
            return null;
        }
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid number.");
        return null;
    }

    System.out.print("Is Available (true/false): ");
    boolean available;
    try {
        available = Boolean.parseBoolean(scanner.nextLine());
    } catch (Exception e) {
        System.out.println("Invalid input. Please enter 'true' or 'false'.");
        return null;
    }

    System.out.print("Is On Leave (true/false): ");
    boolean onLeave;
    try {
        onLeave = Boolean.parseBoolean(scanner.nextLine());
    } catch (Exception e) {
        System.out.println("Invalid input. Please enter 'true' or 'false'.");
        return null;
    }

    return new Doctor(id, name, specialization, contact, years, fee, available, onLeave);
}

    private void displayAllDoctorsUI() {
        System.out.println("\nALL DOCTORS LIST");
        Doctor[] doctors = doctorManager.getAllDoctors();

        if (doctors.length == 0) {
            System.out.println("No doctors found!");
            return;
        }

        System.out.println("+--------+------------------+----------------------+--------------+-----------+-----------+------------+");
        System.out.println("| ID     | Name             | Specialization       | Contact      | Exp (Yrs) | Fee (RM)  | Status     |");
        System.out.println("+--------+------------------+----------------------+--------------+-----------+-----------+------------+");

        for (Doctor doctor : doctors) {
            System.out.printf("| %-6s | %-16s | %-20s | %-12s | %-9d | %-9.2f | %-10s |\n",
                doctor.getDoctorID(),
                doctor.getName(),
                doctor.getSpecialization(),
                doctor.getContactNumber(),
                doctor.getYearsOfExperience(),
                doctor.getConsultationFee(),
                getStatusString(doctor));
        }

        System.out.println("+--------+------------------+----------------------+--------------+-----------+-----------+------------+");
        System.out.println("Total Doctors: " + doctors.length);
    }

    private String getStatusString(Doctor doctor) {
        if (doctor.isOnLeave()) return "On Leave";
        return doctor.isAvailable() ? "Available" : "Unavailable";
    }

    private void displaySeniorDoctorsUI() {
    final int[] bands = {1, 5, 10, 15};
    final String[] titles = {
        "1+ YEAR(S) OF EXPERIENCE",
        "5+ YEARS OF EXPERIENCE",
        "10+ YEARS OF EXPERIENCE",
        "15+ YEARS OF EXPERIENCE"
    };

    System.out.println("\n=== SENIOR DOCTORS MENU ===");
    for (int i = 0; i < titles.length; i++) {
        System.out.println((i + 1) + ". " + titles[i]);
    }
    System.out.print("Choose an option (1-4): ");

    int choice;
    try {
        choice = Integer.parseInt(scanner.nextLine().trim());
    } catch (NumberFormatException e) {
        System.out.println("Invalid input.");
        return;
    }

    if (choice < 1 || choice > 4) {
        System.out.println("Choice out of range.");
        return;
    }

    int minYears = bands[choice - 1];
    String report = doctorManager.generateSeniorDoctorsReport(minYears);
    System.out.println(report);
}

    
    
    
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

    private void updateDoctorUI(Doctor oldDoctor) {
        System.out.println("\nUpdate Doctor Details");
        System.out.println("Current details:");
        displayDoctorCard(oldDoctor);
        System.out.println("\nEnter new details (or 'cancel' at any prompt):");

        Doctor newDetails = inputDoctorDetails();

        if (newDetails == null) {
            System.out.println("\nUpdate cancelled. No changes made.");
            return;
        }

        newDetails.setDoctorID(oldDoctor.getDoctorID());

        if (doctorManager.updateDoctor(oldDoctor.getDoctorID(), newDetails)) {
            System.out.println("\nDoctor updated successfully! New details:");
            displayDoctorCard(newDetails);
        } else {
            System.out.println("Failed to update doctor!");
        }

        System.out.println("\nPress Enter to return to main menu...");
        scanner.nextLine(); // Wait for user
    }

    private void displaySpecializationReportUI() {
        System.out.println("\nSpecialization Distribution");
        System.out.println(doctorManager.generateSpecializationReportWithPatientCount());
    }

    private void displayWorkloadReportUI() {
        System.out.println("\nDoctor Workload Report");
        System.out.println(doctorManager.generateDoctorWorkloadReport());
    }

    private void filterDoctorsByWorkloadUI() {
        System.out.print("\nEnter minimum consultations: ");
        int minConsultations = scanner.nextInt();
        scanner.nextLine();

        QueueInterface<Doctor> filteredDoctors = doctorManager.filterDoctorsByPatientWorkload(minConsultations);
        if (filteredDoctors.size() == 0) {
            System.out.println("No doctors found with the specified workload.");
            return;
        }

            System.out.println("\nDoctors with at least " + minConsultations + " consultations:");
            System.out.println("\nDoctors with at least " + minConsultations + " consultations:");
            System.out.println("+----------+----------------------+--------------------+--------------+---------+----------+-----------+");
            System.out.println("| ID       | Name                 | Specialization     | Contact      | Exp yrs | Fee RM   | Status    |");
            System.out.println("+----------+----------------------+--------------------+--------------+---------+----------+-----------+");

            for (Doctor doctor : filteredDoctors.toArray(new Doctor[0])) {
                displayDoctorRow(doctor);
            }

            System.out.println("+----------+----------------------+--------------------+--------------+---------+----------+-----------+");

    }
    
    private void displayDoctorRow(Doctor d) {
    // 8-char ID, 20-char name, 18-char spec, 12-char contact, 3-digit years, 6-char fee, 8-char status
    System.out.printf("| %-8s | %-20s | %-18s | %-12s | %3d yrs | RM%6.2f | %-8s |\n",
            d.getDoctorID(),
            d.getName(),
            d.getSpecialization(),
            d.getContactNumber(),
            d.getYearsOfExperience(),
            d.getConsultationFee(),
            d.isOnLeave() ? "OnLeave" : (d.isAvailable() ? "Available" : "Occupied"));
}


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
    
    private void setDutyShiftUI() {
    System.out.print("Doctor ID: ");
    String id = scanner.nextLine();
    System.out.print("Shift pattern (HH:MM-HH:MM): ");
    String shift = scanner.nextLine();
    if (doctorManager.addDutyShift(id, shift))
        System.out.println("Shift updated.");
    else
        System.out.println("Doctor not found.");
}

private void checkSlotAvailabilityUI() {
    System.out.print("Doctor ID: ");
    String id = scanner.nextLine();
    System.out.print("Date (YYYY-MM-DD): ");
    LocalDate date = LocalDate.parse(scanner.nextLine());
    System.out.print("Time (HH:MM): ");
    LocalTime slot = LocalTime.parse(scanner.nextLine());

    String availabilityReport = doctorManager.checkDoctorAvailability(id, date, slot);
    System.out.println(availabilityReport);

    if (!availabilityReport.contains("Available")) {
        String suggestionReport = doctorManager.suggestAlternativeSlots(id, date, slot);
        System.out.println(suggestionReport);
    }
}

}