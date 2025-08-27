/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package control;

    import adt.LinkedQueue;
    import adt.QueueInterface;
    import entity.Consultation;
    import entity.Doctor;
    import entity.Patient;
    import control.DoctorManager;
    import control.PatientManager;
    import java.time.LocalDate;
    import java.time.LocalTime;
    /**
     *
     * @author LESTER
     */
    public class ConsultationManager {

        private QueueInterface<Consultation> consultationQueue;
        private int consultationCounter = 0;
        private DoctorManager doctorManager;
        private PatientManager patientManager;
        
        public Consultation[] getAllConsultations() {
    if (consultationQueue.isEmpty()) {
        return new Consultation[0];
    }
    
    
    Consultation[] array = new Consultation[consultationQueue.size()];
    return consultationQueue.toArray(array);
}
        public ConsultationManager(DoctorManager doctorManager, PatientManager patientManager) {
             this.consultationQueue = new LinkedQueue<>();
             this.doctorManager = doctorManager;
             this.patientManager = patientManager;
             this.consultationCounter = 0;
}
        
    
    public QueueInterface<Consultation> getAllConsultationsQueue() {
        return consultationQueue;
    }    
    //DISPLAY===================================================================    
     public void displayConsultations() {
        Consultation[] consultations = consultationQueue.toArray(new Consultation[0]);

        if (consultationQueue.isEmpty()) {
            System.out.println("No consultations found.");
            return;
        }

        System.out.println("Total consultations: " + consultations.length);
        System.out.println("____________________________________________________________________________________________________________________________________________________________________________");
        System.out.printf("%-5s %-15s %-15s %-12s %-8s %-10s %-15s %-10s %-20s%n",
                "ID", "Doctor", "Patient", "Date", "Time", "Status", "Type", "Follow-up", "Notes");
        System.out.println("____________________________________________________________________________________________________________________________________________________________________________");

        for (Consultation c : consultations) {
            String doctorName = "Unknown";
            String patientName = "Unknown";
            
            // Get doctor and patient details from their managers
            Doctor doctor = doctorManager.getDoctorByID(c.getDoctorId());
            Patient patient = patientManager.findPatient(c.getPatientId());
            
            if (doctor != null) {
                doctorName = doctor.getName();
            }
            if (patient != null) {
                patientName = patient.getName();
            }
            
            System.out.printf("%-5s %-15s %-15s %-12s %-8s %-10s %-15s %-10s %-20s%n",
                    c.getConsultationId(),
                    doctorName,
                    patientName,
                    c.getConsultationDate(),
                    c.getConsultationTime(),
                    c.getConsultationStatus(),
                    c.getConsultationType(),
                    (c.isFollowUpRequired() ? "Yes" : "No"),
                    (c.getConsultationNotes().isEmpty() ? "None" : c.getConsultationNotes()));
        }
        System.out.println("____________________________________________________________________________________________________________________________________________________________________________");
    }

        
        
        
        
    //ADD===========================================================================    
     public boolean addConsultation(String doctorId, LocalDate date, LocalTime time, String consultationType) {
        // Check if doctor exists
        Doctor doctor = doctorManager.getDoctorByID(doctorId);
        if (doctor == null) {
            System.out.println("Doctor with ID " + doctorId + " not found.");
            return false;
        }
        
        // Check if doctor is generally available
        if (!doctor.isAvailable()) {
          System.out.println("Doctor " + doctor.getName() + " is not available.");
        return false;
        }
        
        // Get the next patient from the queue
        Patient nextPatient = patientManager.getFirstInQueue();
        if (nextPatient == null) {
            System.out.println("No patients in the queue.");
            return false;
        }
        
        // Display confirmation
        System.out.println("\n=== CONSULTATION CONFIRMATION ===");
        System.out.println("Doctor: " + doctor.getName() + " (" + doctor.getSpecialization() + ")");
        System.out.println("Patient: " + nextPatient.getName() + " (ID: " + nextPatient.getPatientID() + ")");
        System.out.println("Date: " + date + " Time: " + time);
        System.out.println("Type: " + consultationType);
        System.out.println("==================================\n");
        
        // Create and add consultation
        Consultation consultation = new Consultation(
            generateNewId(), 
            doctorId, 
            nextPatient.getPatientID(), 
            date, 
            time, 
            "Scheduled", 
            consultationType, 
            false, 
            ""
        );
        
        consultationQueue.enqueue(consultation);
        
        // Remove patient from queue after consultation is scheduled
         patientManager.processNextPatient();
        
        System.out.println("Consultation scheduled successfully!");
        return true;
    }
     
    public boolean addSampleConsultation(String consultationId, String doctorId, String patientId, 
                                   LocalDate date, LocalTime time, String status, 
                                   String consultationType, boolean followUp, String notes) {
    
    // Check if doctor exists
    Doctor doctor = doctorManager.getDoctorByID(doctorId);
    if (doctor == null) {
        System.out.println("Doctor with ID " + doctorId + " not found.");
        return false;
    }
    
    // Check if patient exists
    Patient patient = patientManager.findPatient(patientId);
    if (patient == null) {
        System.out.println("Patient with ID " + patientId + " not found.");
        return false;
    }
    
    // Create and add consultation
    Consultation consultation = new Consultation(
        consultationId, 
        doctorId, 
        patientId, 
        date, 
        time, 
        status, 
        consultationType, 
        followUp, 
        notes
    );
    
    consultationQueue.enqueue(consultation);
    System.out.println("Sample consultation " + consultationId + " added successfully!");
    return true;
} 
        public boolean addConsultation(Consultation consultation) {
        if (consultation == null) {
            throw new IllegalArgumentException("Consultation cannot be null");
        }
        
        // Validate doctor exists
        Doctor doctor = doctorManager.getDoctorByID(consultation.getDoctorId());
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor with ID " + consultation.getDoctorId() + " does not exist");
        }
        
        // Validate patient exists
        Patient patient = patientManager.findPatient(consultation.getPatientId());
        if (patient == null) {
            throw new IllegalArgumentException("Patient with ID " + consultation.getPatientId() + " does not exist");
        }
        
        // Validate doctor availability
        if (!doctor.isAvailable()) {
            throw new IllegalStateException("Doctor is not available on " + consultation.getConsultationDate());
        }
        
        // Check for time conflict
        if (!isDoctorAvailable(consultation.getDoctorId())) {
            throw new IllegalStateException("Doctor already has a consultation at this time");
        }
        
        if (getConsultationByID(consultation.getConsultationId()) != null) {
            throw new IllegalStateException(
                "Consultation with ID " + consultation.getConsultationId() + " already exists"
            );
        }

        consultationQueue.enqueue(consultation);
        return true;
    }
    
    
     // NEW: GET AVAILABLE DOCTORS ================================================
    public void displayAvailableDoctors() {
    System.out.println("\n=== AVAILABLE DOCTORS ===");
    System.out.printf("%-8s %-20s %-15s %-12s%n", "ID", "Name", "Specialization", "Available");
    System.out.println("----------------------------------------------------------------");

    Doctor[] allDoctors = doctorManager.getAllDoctors();
    for (Doctor doctor : allDoctors) {
        boolean available = doctor.isAvailable();
        System.out.printf("%-8s %-20s %-15s %-12s%n",
                doctor.getDoctorID(),
                doctor.getName(),
                doctor.getSpecialization(),
                available ? "Yes" : "No");
    }
    System.out.println("----------------------------------------------------------------");
}
    
    // NEW: CHECK DOCTOR AVAILABILITY ============================================
    public boolean isDoctorAvailable(String doctorId) {
      Doctor doctor = doctorManager.getDoctorByID(doctorId);
    if (doctor == null) return false;
    
    // Check if doctor is generally available
    return doctor.isAvailable();
    }
     
    // NEW: GET CONSULTATIONS WITH DETAILS =======================================
    public void displayConsultationDetails(String consultationId) {
        Consultation consultation = getConsultationByID(consultationId);
        if (consultation == null) {
            System.out.println("Consultation not found.");
            return;
        }
        
        Doctor doctor = doctorManager.getDoctorByID(consultation.getDoctorId());
        Patient patient = patientManager.findPatient(consultation.getPatientId());
        
        System.out.println("\n=== CONSULTATION DETAILS ===");
        System.out.println("ID: " + consultation.getConsultationId());
        System.out.println("Date: " + consultation.getConsultationDate());
        System.out.println("Time: " + consultation.getConsultationTime());
        System.out.println("Status: " + consultation.getConsultationStatus());
        System.out.println("Type: " + consultation.getConsultationType());
        System.out.println("Follow-up Required: " + (consultation.isFollowUpRequired() ? "Yes" : "No"));
        System.out.println("Notes: " + consultation.getConsultationNotes());
        
        System.out.println("\n--- DOCTOR INFORMATION ---");
        if (doctor != null) {
            System.out.println("ID: " + doctor.getDoctorID());
            System.out.println("Name: " + doctor.getName());
            System.out.println("Specialization: " + doctor.getSpecialization());
            System.out.println("Contact: " + doctor.getContactNumber());
        } else {
            System.out.println("Doctor information not available.");
        }
        
        System.out.println("\n--- PATIENT INFORMATION ---");
        if (patient != null) {
            System.out.println("ID: " + patient.getPatientID());
            System.out.println("Name: " + patient.getName());
            System.out.println("IC: " + patient.getIdentificationNo());
            System.out.println("Contact: " + patient.getContactInfo());
            System.out.println("Blood Type: " + patient.getBloodType());
            System.out.println("Allergies: " + patient.getAllergies());
        } else {
            System.out.println("Patient information not available.");
        }
        System.out.println("===========================\n");
    }
    
    // MODIFIED: ADD CONSULTATION WITH VALIDATION ================================
    
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
       public boolean rescheduleConsultation(String consultationId, LocalDate newDate, LocalTime newTime) {
        Consultation consultation = getConsultationByID(consultationId);
        if (consultation != null) {
            consultation.setConsultationDate(newDate);
            consultation.setConsultationTime(newTime);
            return true;
        }
        return false;
    }
    //CANCEL========================================================================    
    public boolean cancelConsultation(String consultationId) {
    QueueInterface<Consultation> tempQueue = new LinkedQueue<>();
    boolean found = false;

    while (!consultationQueue.isEmpty()) {
        Consultation c = consultationQueue.dequeue();
        if (c.getConsultationId().equals(consultationId)) {
            c.cancelConsultation(); // Call the entity method to mark as cancelled
            found = true;
        } else {
            tempQueue.enqueue(c);
        }
    }

    // Restore the remaining consultations
    while (!tempQueue.isEmpty()) {
        consultationQueue.enqueue(tempQueue.dequeue());
    }

    return found; // true if consultation was found and cancelled
    }
    //Is follow up required status for consultations=================================    
        public boolean isFollowUpRequired(String consultationId){
            Consultation consultation = getConsultationByID(consultationId);
        return consultation != null && consultation.isFollowUpRequired();
        }
        
        public boolean setFollowUpRequired(String consultationId, boolean required) {
            Consultation consultation = getConsultationByID(consultationId);
        if (consultation == null) return false;
                consultation.setFollowUpRequired(required);
            return true;
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
//-------------- DOCTOR COMPLETION REPORT -------------- 
public String generateDoctorCompletionReport() {
    final int BAR = 25;
    StringBuilder out = new StringBuilder();

    /* 1.  Gather data  */
    Doctor[] docs = doctorManager.getAllDoctors();
    int[] completed = new int[docs.length];

    for (Consultation c : consultationQueue.toArray(new Consultation[0])) {
        if ("Completed".equalsIgnoreCase(c.getConsultationStatus())) {
            for (int i = 0; i < docs.length; i++) {
                if (docs[i].getDoctorID().equalsIgnoreCase(c.getDoctorId())) {
                    completed[i]++;
                    break;
                }
            }
        }
    }

    /* 2.  Find max for bar scaling  */
    int max = 0;
    for (int n : completed) {
        if (n > max) max = n;
    }

    /* 3.  Clean table  */
    out.append("\n========== DOCTOR COMPLETION SUMMARY ==========\n\n");
    out.append("Doctor ID   Doctor Name          Completed\n");
    out.append("------------------------------------------\n");
    for (int i = 0; i < docs.length; i++) {
        out.append(String.format("%-11s %-20s %7d%n",
                docs[i].getDoctorID(),
                docs[i].getName(),
                completed[i]));
    }

    /* 4.  ASCII bar chart  */
    out.append("\nVISUAL COMPLETION CHART\n");
    out.append("------------------------------------------\n");
    for (int i = 0; i < docs.length; i++) {
        int barLen = (max == 0) ? 0 : (completed[i] * BAR) / Math.max(1, max);
        String bar = "=".repeat(Math.max(1, barLen));
        out.append(String.format("%-4s %-18s %3d %s%n",
                docs[i].getDoctorID(),
                docs[i].getName(),
                completed[i],
                bar));
    }
    out.append("=============================================\n");
    return out.toString();
}

    /* -------------- CLEAN PATIENT HISTORY REPORT (table + graph) -------------- */
public String generatePatientHistoryReport() {
    final int BAR = 20;
    StringBuilder out = new StringBuilder();

    /* 1.  Gather counts in one pass */
    Patient[] pts = patientManager.getAllPatients().toArray(new Patient[0]);
    int[] total   = new int[pts.length];
    int[] comp    = new int[pts.length];
    int[] pend    = new int[pts.length];
    int[] cancl   = new int[pts.length];

    for (Consultation c : consultationQueue.toArray(new Consultation[0])) {
        for (int i = 0; i < pts.length; i++) {
            if (pts[i].getPatientID().equalsIgnoreCase(c.getPatientId())) {
                total[i]++;
                switch (c.getConsultationStatus().toLowerCase()) {
                    case "completed" -> comp[i]++;
                    case "pending", "in progress" -> pend[i]++;
                    case "cancelled" -> cancl[i]++;
                }
                break;
            }
        }
    }

    int max = 0;
    for (int n : total) if (n > max) max = n;

    /* 2.  Clean table */
    out.append("\n========== PATIENT CONSULTATION SUMMARY ==========\n\n");
    out.append("Patient ID  Name                 Total Comp Pend Cancl\n");
    out.append("----------------------------------------------------\n");
    for (int i = 0; i < pts.length; i++) {
        out.append(String.format("%-11s %-20s %5d %4d %4d %5d%n",
                pts[i].getPatientID(),
                pts[i].getName().length() > 20 ? pts[i].getName().substring(0, 20) : pts[i].getName(),
                total[i], comp[i], pend[i], cancl[i]));
    }

    /* 3.  ASCII bar chart (Total consultations) */
    out.append("\nVISUAL TOTAL CONSULTATIONS\n");
    out.append("----------------------------------------------------\n");
    for (int i = 0; i < pts.length; i++) {
        int barLen = (max == 0) ? 0 : (total[i] * BAR) / Math.max(1, max);
        String bar = "=".repeat(Math.max(1, barLen));
        out.append(String.format("%-4s %-20s %3d %s%n",
                pts[i].getPatientID(),
                pts[i].getName(),
                total[i],
                bar));
    }
    out.append("====================================================\n");
    return out.toString();
}


    }
