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
        private int consultationCounter = 0;
        
        public Consultation[] getAllConsultations() {
    if (consultationQueue.isEmpty()) {
        return new Consultation[0];
    }
    
    // Create properly sized array
    Consultation[] array = new Consultation[consultationQueue.size()];
    return consultationQueue.toArray(array);
}
        public ConsultationManager() {

            this.consultationQueue = new LinkedQueue<>();
        } 
    //DISPLAY===================================================================    
    public void displayConsultations() {

    Consultation[] consultations = consultationQueue.toArray(new Consultation[0]);

    if (consultationQueue.isEmpty()) {
        System.out.println("No consultations found.");
        return;
    }

    System.out.println("Total consultations: " + consultations.length);
    System.out.println("________________________________________________________________________________________________________");
    System.out.printf("%-5s %-10s %-10s %-12s %-8s %-10s %-12s %-10s %-20s%n",
            "ID", "Doctor", "Patient", "Date", "Time", "Status", "Type", "Follow-up", "Notes");
    System.out.println("________________________________________________________________________________________________________");

    for (Consultation c : consultations) {
        System.out.printf("%-5s %-10s %-10s %-12s %-8s %-10s %-12s %-10s %-20s%n",
                c.getConsultationId(),
                c.getDoctorId(),
                c.getPatientId(),
                c.getConsultationDate(),
                c.getConsultationTime(),
                c.getConsultationStatus(),
                c.getConsultationType(),
                (c.isFollowUpRequired() ? "Yes" : "No"),
                (c.getConsultationNotes().isEmpty() ? "None" : c.getConsultationNotes()));
    }
    System.out.println("________________________________________________________________________________________________________");
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
    //Generate Consultation ID======================================================   
    
    // Initialize counter when loading existing consultations
    public void initializeCounterFromExisting() {
        int maxId = 0;
        for (Consultation c : consultationQueue.toArray(new Consultation[0])) {
            String id = c.getConsultationId();
            if (id != null && id.startsWith("C")) {
                try {
                    int num = Integer.parseInt(id.substring(1));
                    if (num > maxId) maxId = num;
                } catch (NumberFormatException e) {
                    // Skip malformed IDs
                }
            }
        }
        consultationCounter = maxId;
    }


    public String generateNewId() {
        // First get the next available number
        int nextNumber = findNextAvailableIdNumber();
        consultationCounter = nextNumber; // Update counter
        return String.format("C%03d", nextNumber);
    }
    
    private int findNextAvailableIdNumber() {
        // Start with simple increment approach
        int tryNumber = consultationCounter + 1;
        
        // Verify this ID doesn't exist (in case of deletions)
        while (getConsultationByID(String.format("C%03d", tryNumber)) != null) {
            tryNumber++;
        }
        
        return tryNumber;
    }
    
    //DELETE========================================================================    
        public boolean deleteConsultation(String consultationId) {
        QueueInterface<Consultation> tempQueue = new LinkedQueue<>();
        boolean deleted = false;

        while (!consultationQueue.isEmpty()) {
            Consultation c = consultationQueue.dequeue();
            if (c.getConsultationId().equals(consultationId)) {
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
    //Update Status====================================================================================    
       public boolean updateConsultationStatus(String consultationId, String newStatus) {
    // Define valid statuses
    String[] validStatuses = {"Pending", "In Progress", "On Hold", "Completed", "Cancelled", "Rescheduled"};
    
    boolean isValid = false;
    for (String status : validStatuses) {
        if (status.equalsIgnoreCase(newStatus)) {
            isValid = true;
            newStatus = status; // use standard capitalization
            break;
        }
    }
    
    if (!isValid) {
        System.out.println("Invalid status. Allowed: Pending, In Progress, On Hold, Completed, Cancelled, Rescheduled.");
        return false;
    }

    for (Consultation consultation : consultationQueue.toArray(new Consultation[0])) {
        if (consultation.getConsultationId().equals(consultationId)) {
            consultation.setConsultationStatus(newStatus);
            return true; // successfully updated
        }
     }
     return false; // consultation not found
    }
    //Complete=========================================================================================

        public boolean completeConsultation(String consultationId) {
            // Temporary queue to hold consultations while searching
        QueueInterface<Consultation> tempQueue = new LinkedQueue<>();
        boolean found = false;

        while (!consultationQueue.isEmpty()) {
            Consultation c = consultationQueue.dequeue();
            if (c.getConsultationId().equals(consultationId)) {
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
    //FILTER SEARCH ==============================================================================

        // Consultations Doctor Filter
        public QueueInterface<Consultation> getConsultationsByDoctor(String doctorId) {
        QueueInterface<Consultation> resultQueue = new LinkedQueue<>();

        for (Consultation consultation : consultationQueue.toArray(new Consultation[0])) {
            if (consultation.getDoctorId().equals(doctorId)) {
                resultQueue.enqueue(consultation);
            }
        }
        return resultQueue;
    }
    //-------------------------------------------------------------------------------------
        // Consultations Patient Filter 
        public QueueInterface<Consultation> getConsultationsByPatient(String patientId) {
        QueueInterface<Consultation> resultQueue = new LinkedQueue<>();

        for (Consultation consultation : consultationQueue.toArray(new Consultation[0])) {
            if (consultation.getPatientId().equals(patientId)) {
                resultQueue.enqueue(consultation);
            }
        }
        return resultQueue;
    }
    //-------------------------------------------------------------------------------------    
        // Consultations Date Filter
     public QueueInterface<Consultation> getConsultationsByDate(LocalDate date) {
        QueueInterface<Consultation> resultQueue = new LinkedQueue<>();

        for (Consultation consultation : consultationQueue.toArray(new Consultation[0])) {
            if (consultation.getConsultationDate().equals(date)) {
                resultQueue.enqueue(consultation);
            }
        }
        return resultQueue;
    }
     //-----------------------------------------------------------------------------------
        public QueueInterface<Consultation> filterConsultationsByStatus(String status) {
    String[] validStatuses = {"Pending", "In Progress", "On Hold", "Completed", "Cancelled", "Rescheduled"};
    boolean isValid = false;
    for (String s : validStatuses) {
        if (s.equalsIgnoreCase(status)) {
            status = s; // standard capitalization
            isValid = true;
            break;
        }
    }
    
    if (!isValid) {
        System.out.println("Invalid status for filtering. Allowed: Pending, In Progress, On Hold, Completed, Cancelled, Rescheduled.");
        return new LinkedQueue<>();
    }

    QueueInterface<Consultation> resultQueue = new LinkedQueue<>();
    for (Consultation consultation : consultationQueue.toArray(new Consultation[0])) {
        if (consultation.getConsultationStatus().equalsIgnoreCase(status)) {
            resultQueue.enqueue(consultation);
        }
    }
    return resultQueue;
    }

     //------------------------------------------------------------------------------------   
     public QueueInterface<Consultation> searchConsultations(
                String patientId,
                String doctorId,
                String status,
                String type,
                LocalDate date) {

            QueueInterface<Consultation> resultQueue = new LinkedQueue<>();
            for (Consultation consultation : consultationQueue.toArray(new Consultation[0])) {
            boolean match = true;

            if (patientId != null && !consultation.getPatientId().equals(patientId)) {
                match = false;
            }
            if (doctorId != null && !consultation.getDoctorId().equals(doctorId)) {
                match = false;
            }
            if (status != null && !consultation.getConsultationStatus().equalsIgnoreCase(status)) {
                match = false;
            }
            if (type != null && !consultation.getConsultationType().equalsIgnoreCase(type)) {
                match = false;
            }
            if (date != null && !consultation.getConsultationDate().equals(date)) {
                match = false;
            }

            if (match) {
                resultQueue.enqueue(consultation);
            }
        }
        return resultQueue;
        }



    //Update Consultation Notes=====================================================    
        public boolean updateConsultationNotes(String consultationId, String notes) {
            Consultation consultation = getConsultationByID(consultationId);

        if (consultation != null) {
            consultation.setConsultationNotes(notes);
            return true; // Successfully updated
        }

        return false; // Not found
        }
     //RESCHEDULE=============================================================================   
        public boolean rescheduleConsultation(String consultationId, LocalTime newTime, LocalDate newDate) {
            Consultation consultation = getConsultationByID(consultationId);

            if (consultation != null) {
            consultation.setConsultationDate(newDate);
            consultation.setConsultationTime(newTime);
            consultation.rescheduleConsultation(); // update status to "Rescheduled"
            return true;
        }
            return false;
        }
    //CANCEL========================================================================    
        public boolean cancelConsultation(String consultationId) {
    for (Consultation consultation : consultationQueue.toArray(new Consultation[0])) {
        if (consultation.getConsultationId().equals(consultationId)) {
            consultation.cancelConsultation(); // <-- call the entity method
            return true;
        }
    }
    return false; // not found
}
    //Is follow up required status for consultations=================================    
        public boolean isFollowUpRequired(String consultationId){
            Consultation consultation = getConsultationByID(consultationId);
        return consultation != null && consultation.isFollowUpRequired();
        }
    //==============================================================================

    //Get ConsultationID (to verify if it exists)=========================================
        public Consultation getConsultationByID(String consultationId) {
        for (Consultation consultation : consultationQueue.toArray(new Consultation[0])) {
            if (consultation.getConsultationId().equals(consultationId)) {
                return consultation;
            }
        }
        return null;
    }

    //===================================================================================



    }
