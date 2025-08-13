/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.LinkedQueue;
import adt.QueueInterface;
import entity.Consultation;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author LESTER
 */
public class ConsultationManager {
 
    private QueueInterface<Consultation> consultationQueue;
    
    public ConsultationManager() {
   
        this.consultationQueue = new LinkedQueue<>();
    }
    
    public boolean addConsultation(Consultation consultation) {
        
    if (consultation == null) {
        throw new IllegalArgumentException("Consultation cannot be null!");
    }
    
    // 2. Generate ID 
    if (consultation.getConsultationId() == 0) {
        consultation.setConsultationId(generateNextId());
    }    
    
    QueueInterface<Consultation> tempQueue = new LinkedQueue<>();
    boolean isDuplicate = false;
    boolean doctorAvailable = true;

    while (!consultationQueue.isEmpty()) {
        Consultation existing = consultationQueue.dequeue();
        tempQueue.enqueue(existing);

        // Check for duplicate
        if (existing.getDoctorId() == consultation.getDoctorId() &&
            existing.getPatientId() == consultation.getPatientId() &&
            existing.getConsultationDate().equals(consultation.getConsultationDate()) &&
            existing.getConsultationTime().equals(consultation.getConsultationTime())) {
            isDuplicate = true;
        }

        // Check doctor availability
        if (existing.getDoctorId() == consultation.getDoctorId() &&
            existing.getConsultationDate().equals(consultation.getConsultationDate()) &&
            existing.getConsultationTime().equals(consultation.getConsultationTime())) {
            doctorAvailable = false;
        }
    }
// Restore the queue
    while (!tempQueue.isEmpty()) {
        consultationQueue.enqueue(tempQueue.dequeue());
    }

    // 4. Validate and add
    if (isDuplicate) {
        return false;
    }

    if (!doctorAvailable) {
        return false;
    }

    consultationQueue.enqueue(consultation);
    return true;
}

private int generateNextId() {
    int maxId = 0;
    QueueInterface<Consultation> tempQueue = new LinkedQueue<>();
    
    while (!consultationQueue.isEmpty()) {
        Consultation c = consultationQueue.dequeue();
        tempQueue.enqueue(c);
        if (c.getConsultationId() > maxId) {
            maxId = c.getConsultationId();
        }
    }
    
    // Restore the queue
    while (!tempQueue.isEmpty()) {
        consultationQueue.enqueue(tempQueue.dequeue());
    }
    
    return maxId + 1;
}

 //---------------------- Delete Consultation-----------------------------------  
    public boolean deleteConsultation(int consultationId) {
    if (consultationId <= 0) {
        throw new IllegalArgumentException("Consultation ID must be positive");
    }
    
    LinkedQueue<Consultation> tempQueue = new LinkedQueue<>();
    boolean found = false;
    
    while (!consultationQueue.isEmpty()) {
        Consultation current = consultationQueue.dequeue();
        if (current.getConsultationId() == consultationId) {
            found = true;
            // After Deletion Logs the deletion
            System.out.println("Deleted consultation: " + current);
        } else {
            tempQueue.enqueue(current);
        }
    }
    
    consultationQueue = tempQueue;
    return found;
}
    
//------------------------------------------------------------------------------
    public boolean completeConsultation(int consultationId) {
        // Temporary queue to hold consultations while searching
    QueueInterface<Consultation> tempQueue = new LinkedQueue<>();
    boolean found = false;

    while (!consultationQueue.isEmpty()) {
        Consultation c = consultationQueue.dequeue();
        if (c.getConsultationId() == consultationId) {
            c.completeConsultation(); // Call entity method
            found = true;
        }
        tempQueue.enqueue(c);
    }

    // Restore consultations back to the main queue
    while (!tempQueue.isEmpty()) {
        consultationQueue.enqueue(tempQueue.dequeue());
    }

    return found;
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
