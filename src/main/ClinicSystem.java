/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import boundary.DoctorUI;
import control.DoctorManager;
import entity.Doctor;
import java.time.LocalDate;
/**
 *
 * @author user
 */
public class ClinicSystem {
    public static void main(String[] args) {
        DoctorManager manager = new DoctorManager();
        initializeSampleData(manager); // Load 20 sample doctors
        new DoctorUI(manager).displayMainMenu();
    }

    private static void initializeSampleData(DoctorManager manager) {
        try {
            // Cardiology Department
            addSampleDoctor(manager, "D101", "Dr. Ahmad", "Cardiology", "+60123456789", 12, 150.00, 
                new String[]{"2023-12-25", "2024-01-01"}, "9am-5pm");
            addSampleDoctor(manager, "D102", "Dr. Siti", "Cardiology", "+60198765432", 8, 120.00, 
                new String[]{"2023-12-31"}, "10am-6pm");
            // Pediatrics Department
            addSampleDoctor(manager, "D201", "Dr. Mei Ling", "Pediatrics", "+60345678901", 15, 100.00, 
                new String[]{}, "8am-4pm");
            addSampleDoctor(manager, "D202", "Dr. Kumar", "Pediatrics", "+60456789012", 5, 80.00, 
                new String[]{"2024-02-01"}, "2pm-10pm");
            // Orthopedics Department
            addSampleDoctor(manager, "D301", "Dr. Rajesh", "Orthopedics", "+60567890123", 20, 200.00, 
                new String[]{"2023-12-20", "2023-12-21"}, "9am-5pm");
            addSampleDoctor(manager, "D302", "Dr. Fatimah", "Orthopedics", "+60678901234", 7, 130.00, 
                new String[]{}, "11am-7pm");
            // Neurology Department
            addSampleDoctor(manager, "D401", "Dr. Lim", "Neurology", "+60789012345", 18, 250.00, 
                new String[]{"2024-01-15"}, "8am-4pm");
            addSampleDoctor(manager, "D402", "Dr. Ali", "Neurology", "+60890123456", 10, 180.00, 
                new String[]{}, "10am-6pm");
            // Oncology Department
            addSampleDoctor(manager, "D501", "Dr. Chen", "Oncology", "+60901234567", 22, 300.00, 
                new String[]{"2023-12-28"}, "9am-5pm");
            addSampleDoctor(manager, "D502", "Dr. Devi", "Oncology", "+60111234567", 6, 160.00, 
                new String[]{}, "2pm-10pm");
            // General Surgery
            addSampleDoctor(manager, "D601", "Dr. Wong", "General Surgery", "+60122345678", 14, 170.00, 
                new String[]{"2024-01-10"}, "8am-4pm");
            addSampleDoctor(manager, "D602", "Dr. Anwar", "General Surgery", "+60133456789", 9, 140.00, 
                new String[]{}, "10am-6pm");
            // Dermatology
            addSampleDoctor(manager, "D701", "Dr. Tan", "Dermatology", "+60144567890", 11, 110.00, 
                new String[]{"2023-12-15"}, "9am-5pm");
            addSampleDoctor(manager, "D702", "Dr. Kamal", "Dermatology", "+60155678901", 4, 90.00, 
                new String[]{}, "11am-7pm");
            // Ophthalmology
            addSampleDoctor(manager, "D801", "Dr. Lee", "Ophthalmology", "+60166789012", 17, 190.00, 
                new String[]{"2024-01-05"}, "8am-4pm");
            addSampleDoctor(manager, "D802", "Dr. Saras", "Ophthalmology", "+60177890123", 8, 125.00, 
                new String[]{}, "10am-6pm");
            // Emergency Medicine
            addSampleDoctor(manager, "D901", "Dr. John", "Emergency Medicine", "+60188901234", 13, 160.00, 
                new String[]{}, "24-hour shifts");
            addSampleDoctor(manager, "D902", "Dr. Aisha", "Emergency Medicine", "+60199012345", 5, 110.00, 
                new String[]{"2023-12-24"}, "24-hour shifts");
            // Psychiatry
            addSampleDoctor(manager, "DC01", "Dr. Richard", "Psychiatry", "+60109123456", 19, 220.00, 
                new String[]{"2024-01-20"}, "9am-5pm");
            addSampleDoctor(manager, "DC02", "Dr. Nor", "Psychiatry", "+60119234567", 7, 150.00, 
                new String[]{}, "2pm-10pm");
            
        } catch (Exception e) {
            System.out.println("Error loading sample data: " + e.getMessage());
        }
    }

    private static void addSampleDoctor(DoctorManager manager, String id, String name, 
            String specialization, String contact, int experience, double fee, 
            String[] leaveDates, String hours) {
        
        Doctor doctor = new Doctor(id, name, specialization);
        doctor.setContactNumber(contact);
        doctor.setYearsOfExperience(experience);
        doctor.setConsultationFee(fee);
        doctor.setLeaveDates(leaveDates);
        doctor.setWorkingHours(hours);
        doctor.setAvailable(leaveDates.length == 0); // Available if no leave dates
        doctor.setOnLeave(leaveDates.length > 0);
        
        manager.addDoctor(doctor);
    }
}
