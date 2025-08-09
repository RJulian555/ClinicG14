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
            System.out.println("\nDoctor Management System 🏥");
            System.out.println("1. Add New Doctor");
            System.out.println("2.View All Doctors");
            System.out.println("3.Search Doctor by ID");
            System.out.println("4.Specialization Report");
            System.out.println("5.Senior Doctors Report");
            System.out.println("6. Get Next Available Doctor");
            System.out.println("7.Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addDoctorUI();
                case 2 -> displayAllDoctorsUI();
                case 3 -> searchDoctorUI();
                case 4 -> displaySpecializationReportUI();
                case 5 -> displaySeniorDoctorsUI();
                case 6 -> getNextAvailableDoctorUI();
                case 7 -> { return; }
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
        System.out.print("Enter Doctor ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Specialization: ");
        String specialization = scanner.nextLine();
        
        Doctor doctor = new Doctor(id, name, specialization);
        
        System.out.print("Contact Number: ");
        doctor.setContactNumber(scanner.nextLine());
        
        System.out.print("Years of Experience: ");
        doctor.setYearsOfExperience(scanner.nextInt());
        scanner.nextLine();
        
        System.out.print("Consultation Fee: ");
        doctor.setConsultationFee(scanner.nextDouble());
        scanner.nextLine();
        
        System.out.print("Is Available (true/false): ");
        doctor.setAvailable(scanner.nextBoolean());
        scanner.nextLine();
        
        System.out.print("Is On Leave (true/false): ");
        doctor.setOnLeave(scanner.nextBoolean());
        scanner.nextLine();
        
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
            System.out.println("━━━━━━━━━━━━━━━━━━━━");
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

    private void getNextAvailableDoctorUI() {
        System.out.println("\n Finding next available doctor...");
        Doctor doctor = doctorManager.getNextAvailableDoctor();
        
        if (doctor != null) {
            System.out.println(" Next Available Doctor:");
            displayDoctorCard(doctor);
        } else {
            System.out.println("No available doctors at the moment!");
        }
    }
}

