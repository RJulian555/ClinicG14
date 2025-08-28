/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author natalie
 */


import java.time.LocalDateTime;
import java.time.Duration;



public class Patient {
    private String patientID;
    private String name;
    private String identificationNo;
    private String contactInfo;
    private String dateOfBirth; // Format: MM/dd/yyyy
    private String gender;
    private String bloodType;
    private String allergies; // can be empty
    private double weight; // in kg
    private double height; // in cm
    private String queueID;
    private String registrationDate; // Format: MM/dd/yyyy
    
    
    private LocalDateTime queueEntryTime;
    private LocalDateTime processingStartTime;
    private LocalDateTime processingEndTime;
    
    

    public Patient(String patientID, String name, String identificationNo, String contactInfo,
                   String dateOfBirth, String gender, String bloodType, String allergies,
                   double weight, double height, String queueID, String registrationDate) {
        setPatientID(patientID);
        setName(name);
        setIdentificationNo(identificationNo);
        setContactInfo(contactInfo);
        setDateOfBirth(dateOfBirth);
        setGender(gender);
        setBloodType(bloodType);
        setAllergies(allergies);
        setWeight(weight);
        setHeight(height);
        setQueueID(queueID);
        setRegistrationDate(registrationDate);
        
        // Initialize timing fields
        this.queueEntryTime = LocalDateTime.now(); // Set when patient is created
        this.processingStartTime = null;
        this.processingEndTime = null;
        
        
    }
    
    
    

    // Getters and Setters with Validation
   

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
    if (name == null || !name.matches("^[\\p{L} .'-/]+$")) {
        throw new IllegalArgumentException("Name must contain letters and spaces only.");
    }
    this.name = name.toUpperCase();
}


    public String getIdentificationNo() {
        return identificationNo;
    }

    public void setIdentificationNo(String identificationNo) {
        if (identificationNo == null || !identificationNo.matches("^\\d{12}$")) {
            throw new IllegalArgumentException("Identification number must be exactly 12 digits.");
        }
        this.identificationNo = identificationNo;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
    if (contactInfo == null || !contactInfo.matches("^\\d{10,11}$")) {
        throw new IllegalArgumentException("Contact number must be 10-11 digits only.");
    }
    this.contactInfo = contactInfo;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender == null || !gender.matches("^(?i)[FM]$")) {
            throw new IllegalArgumentException("Gender must be 'F' or 'M'");
        }
        this.gender = gender.toUpperCase();
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        if (bloodType == null || !bloodType.matches("^(A|B|AB|O)[+-]$")) {
            throw new IllegalArgumentException("Blood type must be one of: A+, A-, B+, B-, AB+, AB-, O+, O-");
        }
        this.bloodType = bloodType.toUpperCase();
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = (allergies != null) ? allergies : "";
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be a positive number.");
        }
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be a positive number.");
        }
        this.height = height;
    }

  

    public String getQueueID() {
        return queueID;
    }

    public void setQueueID(String queueID) {
        this.queueID = queueID;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    public LocalDateTime getQueueEntryTime() {
        return queueEntryTime;
    }
    
    public void setQueueEntryTime(LocalDateTime queueEntryTime) {
        this.queueEntryTime = queueEntryTime;
    }
    
    public LocalDateTime getProcessingStartTime() {
        return processingStartTime;
    }

    public void setProcessingStartTime(LocalDateTime processingStartTime) {
        this.processingStartTime = processingStartTime;
    }

    public LocalDateTime getProcessingEndTime() {
        return processingEndTime;
    }

    public void setProcessingEndTime(LocalDateTime processingEndTime) {
        this.processingEndTime = processingEndTime;
    }


    // Add timing calculation methods
    public long getTimeInQueueMinutes() {
        if (queueEntryTime == null) return 0;
        LocalDateTime endTime = processingStartTime != null ? processingStartTime : LocalDateTime.now();
        return Duration.between(queueEntryTime, endTime).toMinutes();
    }

    public long getProcessingTimeMinutes() {
        if (processingStartTime == null || processingEndTime == null) return 0;
        return Duration.between(processingStartTime, processingEndTime).toMinutes();
    }

    // Helper method to mark processing start
    public void startProcessing() {
        this.processingStartTime = LocalDateTime.now();
    }

    // Helper method to mark processing end
    public void endProcessing() {
        this.processingEndTime = LocalDateTime.now();
    }
    
    public double calculateBMI() {
    return weight / ((height/100) * (height/100));
    }


    @Override
    public String toString() {
        return "PatientID: " + patientID + ", Name: " + name + ", IC: " + identificationNo;
    }
}

