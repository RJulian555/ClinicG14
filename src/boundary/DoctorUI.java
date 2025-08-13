/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;


import control.DoctorManager;
import entity.Doctor;
/**
 *
 * @author user
 */

public class DoctorUI {
    private DoctorManager doctorManager;

    public DoctorUI(DoctorManager manager) {
        this.doctorManager = manager;
    }

    public void displayMainMenu() {
        // TODO: Implement menu with these options:
        // 1. Add New Doctor
        // 2. View All Doctors
        // 3. Search Doctor by ID
        // 4. Generate Specialization Report
        // 5. Generate Senior Doctors Report
        // 6. Return to Main Menu
    }

    private Doctor inputDoctorDetails() {
        Doctor newDoctor = null;
        // TODO: Collect input for all Doctor fields
        // TODO: Input validation (e.g., valid phone number format)
        return newDoctor;
    }

    private void displayDoctors(Doctor[] doctors) {
        // TODO: Format and print doctor list
    }

    private void displayReport(String report) {
        // TODO: Format and print reports
    }
}

