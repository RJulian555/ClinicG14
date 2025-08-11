/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author user
 */
public class Doctor {
    // Attributes
    private String doctorID;
    private String name;
    private String specialization;
    private String contactNumber;
    private int yearsOfExperience;
    private boolean isAvailable;
    private double consultationFee;
    private boolean isOnLeave;
    private String[] leaveDates;
    private String workingHours;

    // Constructor
    public Doctor(String doctorID, String name, String specialization) {
        this.doctorID = doctorID;
        this.name = name;
        this.specialization = specialization;
        // TODO: Initialize other default values
        this.leaveDates = new String[0]; // Initialize empty array
        this.isAvailable = true; // Default to available
        this.isOnLeave = false; // Default to not on leave
    }
    
    // Getters and Setters for doctors
    // getters for doctor
    public String getDoctorID() { 
        return doctorID; 
    }
    
    public String getName() { 
        return name; 
    }
    
    public String getSpecialization() { 
        return specialization; 
    }
    
    public String getContactNumber() { 
        return contactNumber; 
    }
    
    public int getYearsOfExperience() { 
        return yearsOfExperience; 
    }
    
    public boolean isAvailable() { 
        return isAvailable; 
    }
    
    public double getConsultationFee() { 
        return consultationFee; 
    }
    
    public boolean isOnLeave() { 
        return isOnLeave; 
    }
    
    public String[] getLeaveDates() { 
          return leaveDates != null ? leaveDates.clone() : new String[0];
    }
    
    public String getWorkingHours() { 
        return workingHours; 
    }

    // setters for doctor
    public void setDoctorID(String doctorID) { 
        this.doctorID = doctorID; 
    }
    
    public void setName(String name) { 
        this.name = name; 
    }
    
    public void setSpecialization(String specialization) { 
        this.specialization = specialization; 
    }
    
    public void setContactNumber(String contactNumber) {
    if (contactNumber == null || !contactNumber.matches("^[0-9+-]+$")) {
        throw new IllegalArgumentException("Invalid contact number");
    }
    this.contactNumber = contactNumber;
}
    
    public void setYearsOfExperience(int yearsOfExperience) { 
        this.yearsOfExperience = yearsOfExperience; 
    }
    
    public void setAvailable(boolean isAvailable) { 
        this.isAvailable = isAvailable; 
    }
    
    public void setConsultationFee(double fee) {
    if (fee < 0) {
        throw new IllegalArgumentException("Fee cannot be negative");
    }
    this.consultationFee = fee;
}
    
    public void setOnLeave(boolean isOnLeave) { 
        this.isOnLeave = isOnLeave; 
    }
    
    public void setLeaveDates(String[] leaveDates) { 
        this.leaveDates = leaveDates != null ? leaveDates.clone() : new String[0];
    }
    
    public void setWorkingHours(String workingHours) { 
        this.workingHours = workingHours; 
    }
    
    // Standard Methods
    @Override
    public String toString() {
        return "Doctor ID: " + doctorID + " | Name: " + name + " | Specialization: " + specialization;
    }
    
    public void addLeaveDate(String date) {
    if (leaveDates == null) {
        leaveDates = new String[]{date};
    } else {
        String[] newDates = new String[leaveDates.length + 1];
        System.arraycopy(leaveDates, 0, newDates, 0, leaveDates.length);
        newDates[leaveDates.length] = date;
        leaveDates = newDates;
    }
    this.isOnLeave = true;
    this.isAvailable = false;
}

public void removeLeaveDate(String date) {
    if (leaveDates != null) {
        String[] newDates = new String[leaveDates.length - 1];
        int index = 0;
        for (String d : leaveDates) {
            if (!d.equals(date)) {
                newDates[index++] = d;
            }
        }
        leaveDates = newDates.length > 0 ? newDates : null;
        this.isOnLeave = (leaveDates != null && leaveDates.length > 0);
        this.isAvailable = !this.isOnLeave;
    }
}

public String getFormattedLeaveDates() {
    if (leaveDates == null || leaveDates.length == 0) {
        return "No leave scheduled";
    }
    return String.join(", ", leaveDates);
}

    
}
