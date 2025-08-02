/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entity.Consultation;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author LESTER
 */
public class ConsultationManager {
     // ADT will be initialized here later
    // private QueueInterface<Doctor> doctorQueue;
    
    public ConsultationManager() {
        // Initialize ADT later
        // doctorQueue = new LinkedQueue<>();
    }
    
    public void addConsultation(Consultation consultation){
        //consultations.add(consultation);
    }
    
    public boolean deleteConsultation(int consultationId) {
        //return consultations.removeIf(c -> c.getConsultationId() == consultationId);
        return false;
    }
    public boolean completeConsultation(int consultationId) {
        //add methods
        return false;
    }
    
    // getConsultationsByDoctor()
    // getConsultationsByPatient()
    // getConsultationsByDate()
    
    public boolean updateConsultationNotes(int consultationId, String notes) {
        //add methods
        return false;
    }
    public boolean rescheduleConsultation(int consultationId, LocalTime newTime, LocalDate newDate) {
        //add methods
        return false;
    }
    public boolean isFollowUpRequired(int consultationId){
        //add methods
        return false;
    }
}
