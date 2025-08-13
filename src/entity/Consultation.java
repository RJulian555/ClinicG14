/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author LESTER
 */
public class Consultation {
    // attributes 
    private String consultationStatus;
    private String consultationType;
    private String consultationNotes;
    private int consultationId;
    private int doctorId;
    private int patientId;
    private LocalTime consultationTime;
    private LocalDate consultationDate;
    private boolean followUpRequired;
    
    // Constructor
    public Consultation(int consultationId, int patientId, int doctorId, LocalDate consultationDate, LocalTime consultationTime, String consultationStatus, String consultationType, boolean followUpRequired) {
    this.consultationId = consultationId;
    this.patientId = patientId;
    this.doctorId = doctorId;
    this.consultationDate = consultationDate;
    this.consultationTime = consultationTime;
    this.consultationStatus = consultationStatus;
    this.consultationType = consultationType;
    this.followUpRequired = followUpRequired;
}
    //Getters and Setters

    public String getConsultationStatus() {
        return consultationStatus;
    }

    public void setConsultationStatus(String consultationStatus) {
        this.consultationStatus = consultationStatus;
    }

    public String getConsultationType() {
        return consultationType;
    }

    public void setConsultationType(String consultationType) {
        this.consultationType = consultationType;
    }

    public String getConsultationNotes() {
        return consultationNotes;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    public int getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(int consultationId) {
        this.consultationId = consultationId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public LocalTime getConsultationTime() {
        return consultationTime;
    }

    public void setConsultationTime(LocalTime consultationTime) {
        this.consultationTime = consultationTime;
    }

    public LocalDate getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(LocalDate consultationDate) {
        this.consultationDate = consultationDate;
    }
    
    public boolean isFollowUpRequired() {
        return followUpRequired;
    }

    public void setFollowUpRequired(boolean followUpRequired) {
        this.followUpRequired = followUpRequired;
    }
    
    //Custom Methods
     public void completeConsultation() {
        this.consultationStatus = "Completed";
    }

    public void cancelConsultation() {
        this.consultationStatus = "Cancelled";
    }
    
     public void rescheduleConsultation() {
        this.consultationStatus = "Rescheduled";
    }
     
     
}
