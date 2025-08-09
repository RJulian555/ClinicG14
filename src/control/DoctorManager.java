/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.LinkedQueue;
import adt.QueueInterface;
import entity.Doctor;

/**
 *
 * @author user
 */
public class DoctorManager {
    private QueueInterface<Doctor> doctorQueue;
    
    
    
    public DoctorManager() {
         this.doctorQueue = new LinkedQueue<>();;
    }
    
  //-----------------------------------------------------------------------------------------------------------------//  
    public void addDoctor(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor cannot be null");
        }
        if (doctorExists(doctor.getDoctorID())) {
            throw new IllegalStateException("Doctor with ID " + doctor.getDoctorID() + " already exists");
        }
        doctorQueue.enqueue(doctor);
    }
//-----------------------------------------------------------------------------------------------------------------//  
    public boolean removeDoctor(String doctorID) {
        LinkedQueue<Doctor> tempQueue = new LinkedQueue<>();
        boolean found = false;
        
        while (!doctorQueue.isEmpty()) {
            Doctor current = doctorQueue.dequeue();
            if (!found && current.getDoctorID().equals(doctorID)) {
                found = true;
            } else {
                tempQueue.enqueue(current);
            }
        }
        
        doctorQueue = tempQueue;
        return found;
    }
//-----------------------------------------------------------------------------------------------------------------//  
    public boolean updateDoctor(String doctorID, Doctor newDetails) {
        LinkedQueue<Doctor> tempQueue = new LinkedQueue<>();
        boolean found = false;
        
        while (!doctorQueue.isEmpty()) {
            Doctor current = doctorQueue.dequeue();
            if (current.getDoctorID().equals(doctorID)) {
                // Update all fields except ID
                current.setName(newDetails.getName());
                current.setSpecialization(newDetails.getSpecialization());
                current.setContactNumber(newDetails.getContactNumber());
                current.setYearsOfExperience(newDetails.getYearsOfExperience());
                current.setAvailable(newDetails.isAvailable());
                current.setConsultationFee(newDetails.getConsultationFee());
                current.setOnLeave(newDetails.isOnLeave());
                current.setLeaveDates(newDetails.getLeaveDates());
                current.setWorkingHours(newDetails.getWorkingHours());
                found = true;
            }
            tempQueue.enqueue(current);
        }
        
        doctorQueue = tempQueue;
        return found;
    }
//-----------------------------------------------------------------------------------------------------------------//  
    public Doctor getNextAvailableDoctor() {
        LinkedQueue<Doctor> tempQueue = new LinkedQueue<>();
        Doctor foundDoctor = null;
        
        while (!doctorQueue.isEmpty()) {
            Doctor current = doctorQueue.dequeue();
            if (foundDoctor == null && current.isAvailable() && !current.isOnLeave()) {
                foundDoctor = current;
            }
            tempQueue.enqueue(current);
        }
        
        doctorQueue = tempQueue;
        return foundDoctor;
    }
//-----------------------------------------------------------------------------------------------------------------//  
    // Query Methods
    public Doctor getDoctorByID(String doctorID) {
        for (Doctor doctor : doctorQueue.toArray(new Doctor[0])) {
            if (doctor.getDoctorID().equals(doctorID)) {
                return doctor;
            }
        }
        return null;
    }
//-----------------------------------------------------------------------------------------------------------------//  
    public Doctor[] getAllDoctors() {
        return doctorQueue.toArray(new Doctor[0]);
    }
//-----------------------------------------------------------------------------------------------------------------//  
    public boolean doctorExists(String doctorID) {
        return getDoctorByID(doctorID) != null;

    }
//-----------------------------------------------------------------------------------------------------------------//  
    // Reports
    public String generateSpecializationReport() {
         LinkedQueue<String> specializations = new LinkedQueue<>();
        StringBuilder report = new StringBuilder("Specialization Report:\n");
        
        // Count doctors per specialization
        for (Doctor doctor : doctorQueue.toArray(new Doctor[0])) {
            String spec = doctor.getSpecialization();
            int count = 1;
            
            // Filter queue by this specialization
            QueueInterface<Doctor> filtered = doctorQueue.filter(d -> 
                d.getSpecialization().equals(spec));
            count = filtered.size();
            
            if (!specializations.contains(spec)) {
                report.append(String.format("- %s: %d doctors\n", spec, count));
                specializations.enqueue(spec);
            }
        }
        
        return report.toString();
    }
//-----------------------------------------------------------------------------------------------------------------//  
    public String generateSeniorDoctorsReport(int minYears) {
        QueueInterface<Doctor> seniors = doctorQueue.filter(d -> 
            d.getYearsOfExperience() >= minYears);
        
        StringBuilder report = new StringBuilder(
            String.format("Senior Doctors Report (%d+ years experience):\n", minYears));
        
        for (Doctor doctor : seniors.toArray(new Doctor[0])) {
            report.append(String.format("- %s (%s): %d years\n", 
                doctor.getName(), 
                doctor.getSpecialization(), 
                doctor.getYearsOfExperience()));
        }
        
        return report.toString();
    }
//-----------------------------------------------------------------------------------------------------------------//  
}
