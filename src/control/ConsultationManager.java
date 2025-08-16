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
    
    public Consultation[] getAllConsultations() {
    return consultationQueue.toArray(new Consultation[0]);
}
    public ConsultationManager() {
   
        this.consultationQueue = new LinkedQueue<>();
    } 
//ADD===========================================================================    
    public void addConsultation(Consultation consultation){
        if (consultation == null) {
        throw new IllegalArgumentException("Consultation cannot be null");
    }
    if (getConsultationByID(consultation.getConsultationId()) != null) {
        throw new IllegalStateException(
            "Consultation with ID " + consultation.getConsultationId() + " already exists"
        );
    }
        
        consultationQueue.enqueue(consultation);
    }
//DELETE========================================================================    
    public boolean deleteConsultation(int consultationId) {
    QueueInterface<Consultation> tempQueue = new LinkedQueue<>();
    boolean deleted = false;

    while (!consultationQueue.isEmpty()) {
        Consultation c = consultationQueue.dequeue();
        if (c.getConsultationId() == consultationId) {
            deleted = true; // Skip enqueueing this one (deletes it)
        } else {
            tempQueue.enqueue(c);
        }
    }

    // Restore the remaining consultations
    while (!tempQueue.isEmpty()) {
        consultationQueue.enqueue(tempQueue.dequeue());
    }

    return deleted; // true if something was deleted
}
//Complete=========================================================================================
    
    public boolean completeConsultation(int consultationId) {
        // Temporary queue to hold consultations while searching
    QueueInterface<Consultation> tempQueue = new LinkedQueue<>();
    boolean found = false;

    while (!consultationQueue.isEmpty()) {
        Consultation c = consultationQueue.dequeue();
        if (c.getConsultationId() == consultationId) {
            c.completeConsultation(); // Call entity method which change the status to complete
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
//==============================================================================
    
    // getConsultationsByDoctor()
    public QueueInterface<Consultation> getConsultationsByDoctor(int doctorId) {
    QueueInterface<Consultation> resultQueue = new LinkedQueue<>();

    for (Consultation consultation : consultationQueue.toArray(new Consultation[0])) {
        if (consultation.getDoctorId() == doctorId) {
            resultQueue.enqueue(consultation);
        }
    }
    return resultQueue;
}
    // getConsultationsByPatient()
    public QueueInterface<Consultation> getConsultationsByPatient(int patientId) {
    QueueInterface<Consultation> resultQueue = new LinkedQueue<>();

    for (Consultation consultation : consultationQueue.toArray(new Consultation[0])) {
        if (consultation.getPatientId() == patientId) {
            resultQueue.enqueue(consultation);
        }
    }
    return resultQueue;
}
    // getConsultationsByDate()
 public QueueInterface<Consultation> getConsultationsByDate(LocalDate date) {
    QueueInterface<Consultation> resultQueue = new LinkedQueue<>();

    for (Consultation consultation : consultationQueue.toArray(new Consultation[0])) {
        if (consultation.getConsultationDate().equals(date)) {
            resultQueue.enqueue(consultation);
        }
    }
    return resultQueue;
}
    
//Update Consultation Notes=====================================================    
    public boolean updateConsultationNotes(int consultationId, String notes) {
        Consultation consultation = getConsultationByID(consultationId);

    if (consultation != null) {
        consultation.setConsultationNotes(notes);
        return true; // Successfully updated
    }

    return false; // Not found
    }
 //=============================================================================   
    public boolean rescheduleConsultation(int consultationId, LocalTime newTime, LocalDate newDate) {
        Consultation consultation = getConsultationByID(consultationId);
        
        if (consultation != null) {
        consultation.setConsultationDate(newDate);
        consultation.setConsultationTime(newTime);
        consultation.rescheduleConsultation(); // update status to "Rescheduled"
        return true;
    }
        return false;
    }
//Is follow uprequired status for consultations=================================    
    public boolean isFollowUpRequired(int consultationId){
        Consultation consultation = getConsultationByID(consultationId);
    return consultation != null && consultation.isFollowUpRequired();
    }
//==============================================================================
    
//Get ConsultationID (to verify if it exists)=========================================
    public Consultation getConsultationByID(int consultationId) {
    for (Consultation consultation : consultationQueue.toArray(new Consultation[0])) {
        if (consultation.getConsultationId() == consultationId) {
            return consultation;
        }
    }
    return null;
}

    
}
