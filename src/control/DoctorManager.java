/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entity.Doctor;

/**
 *
 * @author user
 */
public class DoctorManager {
    // ADT will be initialized here later
    // private QueueInterface<Doctor> doctorQueue;
    
    public DoctorManager() {
        // Initialize ADT later
        // doctorQueue = new LinkedQueue<>();
    }
    
    // ADT-related methods (to be implemented)
    // Core Methods
    public void addDoctor(Doctor doctor) {
        // TODO: Validate doctor data before adding
        // TODO: Check for duplicate doctorID
        
    }

    public boolean removeDoctor(String doctorID) {
        // TODO: Implement removal by doctorID
        // Hint: Dequeue doctors temporarily to find target
        return false;
    }

    public boolean updateDoctor(String doctorID, Doctor newDetails) {
        // TODO: Find doctor by ID and update fields
        return false;
    }

    public Doctor getNextAvailableDoctor() {
        // TODO: Return first available doctor (isAvailable=true, isOnLeave=false)
        return null;
    }

    // Query Methods
    public Doctor getDoctorByID(String doctorID) {
        // TODO: Search queue by doctorID
        return null;
    }

    public Doctor[] getAllDoctors() {
        // TODO: Convert queue to array
        return new Doctor[0];
    }

    public boolean doctorExists(String doctorID) {
        // TODO: Check if doctorID exists in system
        return false;
    }

    // Reports
    public String generateSpecializationReport() {
        // TODO: Count doctors per specialization
        return "Report not implemented";
    }

    public String generateSeniorDoctorsReport(int minYears) {
        // TODO: Filter doctors with yearsOfExperience >= minYears
        return "Report not implemented";
    }
}
