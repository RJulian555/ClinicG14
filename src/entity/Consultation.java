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
    private String doctorId;
    private String patientId;
    private String consultationStatus;
    private String consultationType;
    private String consultationNotes;
    private String consultationId;
    private LocalTime consultationTime;
    private LocalDate consultationDate;
    private boolean followUpRequired;
    private Doctor doctor;
    private Patient patient;
    // Constructor
    public Consultation(String consultationId, String doctorId, String patientId, LocalDate consultationDate, LocalTime consultationTime, String consultationStatus, String consultationType, boolean followUpRequired, String consultationNotes) {
    this.consultationId = consultationId;
    this.doctorId = doctorId;
    this.patientId = patientId;
    this.consultationDate = consultationDate;
    this.consultationTime = consultationTime;
    this.consultationStatus = consultationStatus;
    this.consultationType = consultationType;
    this.followUpRequired = followUpRequired;
    this.consultationNotes = consultationNotes;
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

    public String getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(String consultationId) {
        this.consultationId = consultationId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
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