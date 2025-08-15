/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

/**
 *
 * @author natalie
 */

import dao.ClinicInitializer;
import adt.LinkedQueue;
import adt.QueueInterface;
import boundary.PatientUI;
import entity.Patient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class PatientManager {
   
    private QueueInterface<Patient> allPatients = new LinkedQueue<>();
    private final LinkedQueue<Patient> patientQueue = new LinkedQueue<>();
    private final LinkedQueue<Patient> processedPatients = new LinkedQueue<>();
    //private final LinkedQueue<Patient> allPatients = new LinkedQueue<>();
    
    private int patientCounter;
        private int queueCounter;
    private static final int AVG_CONSULTATION_TIME_MIN = 15;

    
    public void addSamplePatient(Patient patient, boolean inQueue) {
    allPatients.enqueue(patient);
    if (inQueue) {
        patientQueue.enqueue(patient);
    }
    
    // Update counters based on the added patient
    try {
        // Update patient counter
        String pid = patient.getPatientID().substring(1); // Remove 'P'
        int currentPid = Integer.parseInt(pid);
        if (currentPid >= patientCounter) {
            patientCounter = currentPid + 1;
        }
        
        // Update queue counter if patient is in queue
        if (inQueue && patient.getQueueID() != null) {
            String qid = patient.getQueueID().substring(1); // Remove 'Q'
            int currentQid = Integer.parseInt(qid);
            if (currentQid >= queueCounter) {
                queueCounter = currentQid + 1;
            }
        }
    } catch (Exception e) {
        System.err.println("Error updating counters: " + e.getMessage());
    }
}
    
    public PatientManager() {
    ClinicInitializer.initializeSamplePatients(this);
}
    
    public void rollbackCounters() {
    if (patientCounter > 0) patientCounter--;
    if (queueCounter > 0) queueCounter--;
}
    
    
    

//-----------------------------------------------------------------------------------------------------------------//    


    public void handleRegistration(PatientUI ui) {
        //generate unique identifier for new patients
    String patientID = generatePatientID();
    String queueID = generateQueueID();
    String registrationDate = getCurrentDate();
    
    // collect patient details through UI interface 
    Patient newPatient = ui.collectPatientDetails(patientID, queueID, registrationDate);
    if (newPatient != null) {
        // add patient to system
        enqueuePatient(newPatient);
        allPatients.enqueue(newPatient);  // Add this line to store in master list
        
        //display registration confirmation
        ui.clearScreen();
        ui.showRegistrationSummary(newPatient);
        
        
        //calculate and display queue position information
        int position = getQueuePosition(newPatient);
        int patientsAhead = position - 1;
        ui.showQueuePosition(position, patientsAhead);
        
        ui.pressEnterToContinue();
        ui.clearScreen();
    }
}
    
//-----------------------------------------------------------------------------------------------------------------//    

    // Purpose: Checks if a patient with the same identification number already exists in the patient queue.

    private boolean isPatientAlreadyInQueue(Patient patient) {
    // Create a temporary queue to store patients while searching
    LinkedQueue<Patient> temp = new LinkedQueue<>();
    
    // Flag to track if patient is found
    boolean found = false;
    
    // Process all patients in the original queue
    while (!patientQueue.isEmpty()) {
        // Remove patient from front of main queue
        Patient p = patientQueue.dequeue();
        
        // Add patient to temporary queue (to preserve order)
        temp.enqueue(p);
        
        // Check if current patient's ID matches the search patient's ID
        if (p.getIdentificationNo().equals(patient.getIdentificationNo())) {
            // Set flag to true if match found
            found = true;
            // Exit loop early since we found a match
            break;
        }
    }
    
    // Restore the original queue by transferring patients back from temp queue
    while (!temp.isEmpty()) {
        // Move each patient from temp queue back to main queue
        patientQueue.enqueue(temp.dequeue());
    }
    
    // Return whether patient was found in queue
    return found;
}
    
    
    
//-----------------------------------------------------------------------------------------------------------------//
    
    
  
public class QueueStatus {
    private int position;
    private LinkedQueue<Patient> nextPatients;

    public QueueStatus(int position, LinkedQueue<Patient> nextPatients) {
        this.position = position;
        this.nextPatients = nextPatients;
    }

    public int getPosition() {
        return position;
    }

    public LinkedQueue<Patient> getNextPatients() {
        return nextPatients;
    }
}
    
    

//-----------------------------------------------------------------------------------------------------------------//


    /**
 * Adds a patient to the queue if they exist and aren't already in it,
 * then displays their position and the next patients in line.
 * 
 * @param ic The identification number of the patient to add.
 * @param ui The user interface component for displaying queue status.
 * @return A success/error message indicating the operation result.
 */


public String handleAddToQueue(String ic, PatientUI ui) {
    try {
        // Step 1: Find the patient by their IC number
        Patient patient = findPatient(ic);
        
        // Step 2: If patient doesn't exist, return an error
        if (patient == null) {
            return "ERROR: Patient not found";
        }

        // Step 3: Check if patient is already in the queue
        if (isPatientAlreadyInQueue(patient)) {
            return "ERROR: Patient already in queue";
        }

        // Step 4: Assign a new queue ID if the patient doesn't have one
        if (patient.getQueueID() == null) {
            patient.setQueueID(generateQueueID());
        }
        
        // Step 5: Add the patient to the queue
        enqueuePatient(patient);
        
        // Step 6: Get the patient's position and the next 3 patients in line
        int position = getQueuePosition(patient);
        LinkedQueue<Patient> nextPatients = getNextPatients(3);
        
        // Step 7: Update the UI with the queue status
        ui.displayQueueStatus(position, nextPatients);
        
        // Step 8: Return success message
        return "Patient added to queue successfully";
    } catch (Exception e) {
        // Handle unexpected errors and return the error message
        return "ERROR: " + e.getMessage();
    }
}
    
//-----------------------------------------------------------------------------------------------------------------//
    

/**
 * Retrieves the queue status (position and next patients) for a patient with the given IC number.
 * 
 * @param ic The identification number of the patient to check
 * @return QueueStatus object containing the patient's position and next patients in line,
 *         or null if the patient is not found
 */
public QueueStatus getQueueStatus(String ic) {
    // Step 1: Find the patient by their IC number
    Patient patient = findPatient(ic);
    
    // Step 2: Return null immediately if patient doesn't exist
    if (patient == null) {
        return null;
    }
    
    // Step 3: Get the patient's current position in the queue
    int position = getQueuePosition(patient);
    
    // Step 4: Get the next 3 patients in line after this patient
    LinkedQueue<Patient> nextPatients = getNextPatients(3);
    
    // Step 5: Create and return a new QueueStatus object with the gathered information
    return new QueueStatus(position, nextPatients);
}


//-----------------------------------------------------------------------------------------------------------------//



/**
 * Retrieves the next 'count' patients in the queue without permanently removing them.
 * Temporarily dequeues patients to examine them, then restores the original queue state.
 * 
 * @param count The maximum number of next patients to retrieve
 * @return A new LinkedQueue containing up to 'count' next patients in line
 */
private LinkedQueue<Patient> getNextPatients(int count) {
    // Create queues to store:
    // 1. The patients we'll return (next in line)
    // 2. A temporary queue to preserve all patients during processing
    LinkedQueue<Patient> nextPatients = new LinkedQueue<>();
    LinkedQueue<Patient> temp = new LinkedQueue<>();
    
    // Determine how many patients we can actually get (limited by queue size)
    int patientsToGet = Math.min(count, patientQueue.size());
    
    // Process the requested number of patients
    for (int i = 0; i < patientsToGet; i++) {
        // Remove patient from front of main queue
        Patient p = patientQueue.dequeue();
        
        // Add to both our return queue and temporary storage
        nextPatients.enqueue(p);
        temp.enqueue(p);
    }
    
    // After getting our patients, restore any remaining patients to temp queue
    while (!patientQueue.isEmpty()) {
        temp.enqueue(patientQueue.dequeue());
    }
    
    // Fully restore the original queue state from our temporary storage
    restoreQueue(temp);
    
    // Return the collected next patients
    return nextPatients;
}



//-----------------------------------------------------------------------------------------------------------------//

    
    /**
 * Adds a patient to the end of the patient queue.
 * This is a simple wrapper around the underlying queue's enqueue operation.
 * 
 * @param patient The Patient object to be added to the queue
 * @throws NullPointerException if the patient parameter is null (depending on queue implementation)
 */
public void enqueuePatient(Patient patient) {
    // Delegates to the patientQueue's enqueue method
    // The patient will be added to the tail/rear of the queue
    patientQueue.enqueue(patient);
}


//-----------------------------------------------------------------------------------------------------------------//    

    /**
 * Processes the next patient in the queue by removing them from the front of the queue
 * and displaying their information. Also shows the status of the next patient if available.
 */
public void processNextPatient() {
    // Check if queue is empty first
    if (patientQueue.isEmpty()) {
        System.out.println("\n No patients in queue.");
        return;  // Exit early if no patients
    }

    // Remove the next patient from front of queue
    Patient processed = patientQueue.dequeue();
    
    // Display processing information
    System.out.println("\n NOW SERVING");
    System.out.println("=============");
    System.out.println("Queue ID: " + processed.getQueueID());
    System.out.println("Name: " + processed.getName()); 
    System.out.println("=============");
    
    // If there are more patients remaining, show status of next patient
    if (!patientQueue.isEmpty()) {
        displayQueueStatus(patientQueue.getFront());
    }
}
    
//-----------------------------------------------------------------------------------------------------------------//

    

    /**
 * Displays the current queue status including the patient's position 
 * and the next 3 patients in line, while preserving the original queue.
 * 
 * @param currentPatient The patient whose queue status is being displayed
 */
private void displayQueueStatus(Patient currentPatient) {
    // Display queue status header
    System.out.println("\n========== QUEUE STATUS ==========");
    
    // Calculate and display position information
    int position = getQueuePosition(currentPatient);
    System.out.println("Your position: " + position);
    System.out.println("Patients ahead: " + (position - 1));  // Shows how many patients are before current
    
    // Header for next patients section
    System.out.println("\nNext 3 patients in queue:");
    
    // Temporary queue to preserve patient order during processing
    LinkedQueue<Patient> tempQueue = new LinkedQueue<>();
    int count = 0;  // Counter for next patients display
    
    // Display next 3 patients (or fewer if queue is smaller)
    while (!patientQueue.isEmpty() && count < 3) {
        // Get next patient from queue
        Patient p = patientQueue.dequeue();
        
        // Display patient info with numbering
        System.out.println((count+1) + ". " + p.getQueueID() + " - " + p.getName());
        
        // Store patient in temporary queue
        tempQueue.enqueue(p);
        count++;
    }
    
    // Restore any remaining patients beyond the 3 we displayed
    while (!patientQueue.isEmpty()) {
        tempQueue.enqueue(patientQueue.dequeue());
    }
    
    // Fully restore original queue from temporary storage
    while (!tempQueue.isEmpty()) {
        patientQueue.enqueue(tempQueue.dequeue());
    }
    
    // Display footer
    System.out.println("==================================");
}
    
    
//-----------------------------------------------------------------------------------------------------------------//    
    
    
    /**
 * Displays the next 3 patients in the queue without permanently removing them.
 * Uses a temporary queue to preserve the original queue order.
 * 
 * Shows each patient with their position (1-3), queue ID, and name.
 * If fewer than 3 patients are in queue, displays only available patients.
 */
    public void displayNextThreePatients() {
    // Create temporary queue to store patients during processing
    LinkedQueue<Patient> temp = new LinkedQueue<>();
    
    // Counter to track how many patients we've displayed
    int count = 0;
    
    // Process up to 3 patients from the front of the queue
    while (!patientQueue.isEmpty() && count < 3) {
        // Remove patient from front of main queue
        Patient p = patientQueue.dequeue();
        
        // Display patient information with numbering
        System.out.println((count+1) + ". " + p.getQueueID() + " - " + p.getName());
        
        // Add patient to temporary queue for later restoration
        temp.enqueue(p);
        
        // Increment counter
        count++;
    }
    
    // Process any remaining patients in queue (beyond the first 3)
    while (!patientQueue.isEmpty()) {
        // Move remaining patients to temporary queue
        temp.enqueue(patientQueue.dequeue());
    }
    
    // Restore all patients back to the original queue
    restoreQueue(temp);
}


    
//-----------------------------------------------------------------------------------------------------------------//    
    
    /**
 * Finds the position of a specific patient in the queue.
 * 
 * @param patient The patient object to locate in the queue
 * @return The 1-based position of the patient if found, -1 if not found
 */
private int getQueuePosition(Patient patient) {
    // Create temporary queue to preserve original queue order
    LinkedQueue<Patient> tempQueue = new LinkedQueue<>();
    
    // Initialize position counter (1-based index)
    int position = 1;
    
    // Flag to track if patient was found
    boolean found = false;

    // Search through the patient queue
    while (!patientQueue.isEmpty()) {
        // Remove patient from front of queue
        Patient p = patientQueue.dequeue();
        
        // Add patient to temporary queue (to preserve order)
        tempQueue.enqueue(p);
        
        // Check if current patient is the one we're looking for
        if (p == patient) {  // Using reference equality comparison
            found = true;
            break;  // Exit loop early if found
        }
        
        // Increment position counter for next patient
        position++;
    }

    // Transfer any remaining patients to temporary queue
    while (!patientQueue.isEmpty()) {
        tempQueue.enqueue(patientQueue.dequeue());
    }
    
    // Restore original queue from temporary storage
    restoreQueue(tempQueue);

    // Return position if found, -1 if not found
    return found ? position : -1;
}
    
//-----------------------------------------------------------------------------------------------------------------//    


 
// =============== QUEUE STATUS METHODS ===============

/**
 * Checks if the patient queue is empty.
 * 
 * @return true if the queue is empty, false otherwise
 */
public boolean isQueueEmpty() {
    // Directly returns the result of the underlying queue's isEmpty() method
    return patientQueue.isEmpty();
}

/**
 * Retrieves (but does not remove) the first patient in the queue.
 * 
 * @return The first Patient object in queue, or null if queue is empty
 */
public Patient getFirstInQueue() {
    // Uses ternary operator to safely return null if queue is empty
    return patientQueue.isEmpty() ? null : patientQueue.getFront();
}

/**
 * Displays all patients in the queue with their positions, 
 * while preserving the original queue order.
 */
public void viewFullQueue() {
    // Handle empty queue case first
    if (patientQueue.isEmpty()) {
        System.out.println("Queue is currently empty.");
        return;  // Early return for empty queue
    }
    
    // Display queue header
    System.out.println("\n=== Full Queue ===");
    
    // Create temporary queue to preserve original order
    LinkedQueue<Patient> temp = new LinkedQueue<>();
    int position = 1;  // 1-based position counter
    
    // Process all patients in queue
    while (!patientQueue.isEmpty()) {
        // Remove patient from front of queue
        Patient p = patientQueue.dequeue();
        
        // Display patient information with position number
        System.out.println(position++ + ". " + p.getQueueID() + " - " + p.getName());
        
        // Store patient in temporary queue
        temp.enqueue(p);
    }
    
    // Restore original queue from temporary storage
    restoreQueue(temp);
}


//-----------------------------------------------------------------------------------------------------------------//


    // ================= HELPER METHODS =================

/**
 * Generates a unique patient ID in the format P001, P002, etc.
 * Auto-increments the patient counter after each generation.
 * 
 * @return A new unique patient ID string
 */
public String generatePatientID() {
    // Format the current patientCounter value with leading zeros (3 digits)
    // and prefix with 'P', then increment counter for next call
    return "P" + String.format("%03d", patientCounter++);
}

/**
 * Generates a unique queue ID in the format Q001, Q002, etc.
 * Auto-increments the queue counter after each generation.
 * 
 * @return A new unique queue ID string
 */


public String generateQueueID() {
    // Format the current queueCounter value with leading zeros (3 digits)
    // and prefix with 'Q', then increment counter for next call
    return "Q" + String.format("%03d", queueCounter++);
}

/**
 * Gets the current date in MM/dd/yyyy format.
 * 
 * @return Current date as formatted string
 */


private String getCurrentDate() {
    // Get current date using Java 8+ LocalDate
    // Format as month/day/year (e.g., "06/15/2023")
    return LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
}


/**
 * Completely restores all patients from a temporary queue to the main allPatients queue.
 * First clears the current queue to ensure no duplicates remain.
 * 
 * @param temp The temporary queue containing patients to restore
 */


private void restoreAllPatients(LinkedQueue<Patient> temp) {
    // Clear the current patient queue completely
    allPatients.clear();
    
    // Transfer all patients from temporary queue back to main queue
    while (!temp.isEmpty()) {
        allPatients.enqueue(temp.dequeue());
    }
}

//-----------------------------------------------------------------------------------------------------------------//



/**
 * Searches for a patient in the system by either their identification number or patient ID.
 * 
 * @param identifier The search string (can be either IC number or patient ID)
 * @return The found Patient object, or null if no match was found
 */


public Patient findPatient(String identifier) {
    // Create temporary queue to preserve original patient order
    LinkedQueue<Patient> temp = new LinkedQueue<>();
    
    // Variable to store found patient (null if not found)
    Patient found = null;
    
    // Process all patients in the main queue
    while (!allPatients.isEmpty()) {
        // Remove patient from front of queue
        Patient p = allPatients.dequeue();
        
        // Add patient to temporary queue (for later restoration)
        temp.enqueue(p);
        
        // Check if current patient matches either identifier type
        if (p.getIdentificationNo().equals(identifier) ||  // Check IC number
            p.getPatientID().equals(identifier)) {        // Check patient ID
            found = p;  // Store matching patient
            break;       // Exit loop early since we found a match
        }
    }
    
    // Restore all patients to original queue
    restoreAllPatients(temp);
    
    // Return found patient (or null if no match)
    return found;
}
   
    
//-----------------------------------------------------------------------------------------------------------------//

/**
 * Updates patient information with validation for provided fields.
 * Only updates fields that receive non-null and non-empty values.
 * Validates weight (2-300kg) and height (50-250cm) ranges.
 * Allows clearing allergies by setting to empty string.
 * Throws IllegalArgumentException for invalid inputs.
 */

    
    public void updatePatient(Patient patient, String newName, String newContact, 
                    String newWeight, String newHeight, String newAllergies) {
    // Update name if new value provided
    if (newName != null && !newName.isEmpty()) {
        patient.setName(newName);
    }
    
    // Update contact if new value provided (validation handled by setter)
    if (newContact != null && !newContact.isEmpty()) {
        patient.setContactInfo(newContact);
    }
    
    // Update weight if new value provided with range validation
    if (newWeight != null && !newWeight.isEmpty()) {
        try {
            double weight = Double.parseDouble(newWeight);
            if (weight < 2 || weight > 300) {
                throw new IllegalArgumentException("Weight must be between 2kg and 300kg");
            }
            patient.setWeight(weight);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Weight must be a number");
        }
    }
    
    // Update height if new value provided with range validation
    if (newHeight != null && !newHeight.isEmpty()) {
        try {
            double height = Double.parseDouble(newHeight);
            if (height < 50 || height > 250) {
                throw new IllegalArgumentException("Height must be between 50cm and 250cm");
            }
            patient.setHeight(height);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Height must be a number");
        }
    }
    
    // Update allergies (allows clearing with empty string)
    if (newAllergies != null) {
        patient.setAllergies(newAllergies);
    }
}

    



//-----------------------------------------------------------------------------------------------------------------//

public boolean deletePatient(Patient patient) {
    // Remove from allPatients
    LinkedQueue<Patient> temp = new LinkedQueue<>();
    boolean found = false;
    
    while (!allPatients.isEmpty()) {
        Patient p = allPatients.dequeue();
        if (p != patient) { // Keep all except the one to delete
            temp.enqueue(p);
        } else {
            found = true;
        }
    }
    restoreAllPatients(temp);
    
    // Remove from queue if present
    if (patientQueue.contains(patient)) {
        temp.clear();
        found = false;
        while (!patientQueue.isEmpty()) {
            Patient p = patientQueue.dequeue();
            if (p != patient) {
                temp.enqueue(p);
            } else {
                found = true;
            }
        }
        restoreQueue(temp);
    }
    
    return found;
}


//-----------------------------------------------------------------------------------------------------------------//



public void displayAllPatients(PatientUI ui) {
    if (allPatients.isEmpty()) {
        ui.showMessage("No patients in the database.");
        return;
    }
    
    // Create a temporary queue to preserve order
    LinkedQueue<Patient> temp = new LinkedQueue<>();
    
    // Header
    ui.showMessage("\n=== ALL PATIENTS IN DATABASE ===");
    ui.showMessage(String.format("%-10s %-20s %-15s %-8s %-6s %-10s %-15s",
        "PatientID", "Name", "IC Number", "Gender", "Age", "BMI", "Status"));
    ui.showMessage("-------------------------------------------------------------------------------");
    
    int count = 1;
    while (!allPatients.isEmpty()) {
        Patient p = allPatients.dequeue();
        temp.enqueue(p);
        
        // Calculate BMI and status
        double bmi = p.calculateBMI();
        String bmiStatus = interpretBMI(bmi);
        String queueStatus = p.getQueueID() != null ? "(In Queue)" : "";
        
        ui.showMessage(String.format("%d. %-10s %-20s %-15s %-8s %-6d %-10.1f %-15s %s",
            count++,
            p.getPatientID(),
            p.getName(),
            p.getIdentificationNo(),
            p.getGender(),
            calculateAge(p.getDateOfBirth()),
            bmi,
            bmiStatus,
            queueStatus));
    }
    
    // Restore the original queue
    restoreAllPatients(temp);
}

//-----------------------------------------------------------------------------------------------------------------//

// Helper method to interpret BMI value
private String interpretBMI(double bmi) {
    if (bmi < 18.5) return "Under";
    else if (bmi < 25) return "Normal";
    else if (bmi < 30) return "Over";
    else return "Obese";
}


//-----------------------------------------------------------------------------------------------------------------//


/**
 * Filters and returns a queue of patients matching the specified gender.
 * Preserves the original patient queue while creating the filtered result.
 * 
 * @param gender The gender to filter by (case-insensitive comparison)
 * @return A new LinkedQueue containing only patients of the specified gender
 *         Returns empty queue if no matches found or if input gender is invalid
 */


public LinkedQueue<Patient> filterPatientsByGender(String gender) {
    // Create queues:
    // filteredQueue - will contain the final matching patients
    // temp - temporary storage to preserve original queue
    LinkedQueue<Patient> filteredQueue = new LinkedQueue<>();
    LinkedQueue<Patient> temp = new LinkedQueue<>();
    
    // Process all patients in the main queue
    while (!allPatients.isEmpty()) {
        // Get next patient from main queue
        Patient p = allPatients.dequeue();
        
        // Preserve patient in temporary queue (for restoration)
        temp.enqueue(p);
        
        // Check if patient's gender matches (case-insensitive comparison)
        if (p.getGender().equalsIgnoreCase(gender)) {
            // Add to results if gender matches
            filteredQueue.enqueue(p);
        }
    }
    
    // Restore original patient queue from temporary storage
    restoreAllPatients(temp);
    
    // Return the filtered results
    return filteredQueue;
}



//-----------------------------------------------------------------------------------------------------------------//

/**
 * Filters and returns patients with a specific blood type.
 * Preserves the original patient queue while creating the filtered result.
 * 
 * @param bloodType The blood type to filter by (case-insensitive comparison)
 * @return A new LinkedQueue containing only patients with matching blood type
 *         Returns empty queue if no matches found or if input is invalid
 */



public LinkedQueue<Patient> filterPatientsByBloodType(String bloodType) {
    // Create queues:
    // filteredQueue - will contain patients with matching blood type
    // temp - temporary storage to preserve original queue
    LinkedQueue<Patient> filteredQueue = new LinkedQueue<>();
    LinkedQueue<Patient> temp = new LinkedQueue<>();
    
    // Process all patients in main queue
    while (!allPatients.isEmpty()) {
        // Get next patient from main queue
        Patient p = allPatients.dequeue();
        
        // Preserve patient in temporary queue
        temp.enqueue(p);
        
        // Check if patient's blood type matches (case-insensitive)
        if (p.getBloodType().equalsIgnoreCase(bloodType)) {
            // Add to results if blood type matches
            filteredQueue.enqueue(p);
        }
    }
    
    // Restore original patient queue from temporary storage
    restoreAllPatients(temp);
    
    // Return queue of matching patients
    return filteredQueue;
}



//-----------------------------------------------------------------------------------------------------------------//



/**
 * Filters and returns patients who currently have a queue ID (are in the active queue).
 * Preserves the original patient queue while creating the filtered result.
 * 
 * @return A new LinkedQueue containing only patients with non-null queue IDs
 *         Returns empty queue if no patients are currently in queue
 */


public LinkedQueue<Patient> filterPatientsInQueue() {
    // Create queues:
    // filteredQueue - will contain patients currently in queue
    // temp - temporary storage to preserve original queue
    LinkedQueue<Patient> filteredQueue = new LinkedQueue<>();
    LinkedQueue<Patient> temp = new LinkedQueue<>();
    
    // Process all patients in main queue
    while (!allPatients.isEmpty()) {
        // Get next patient from main queue
        Patient p = allPatients.dequeue();
        
        // Preserve patient in temporary queue
        temp.enqueue(p);
        
        // Check if patient has a queue ID (is in active queue)
        if (p.getQueueID() != null) {
            // Add to results if patient is in queue
            filteredQueue.enqueue(p);
        }
    }
    
    // Restore original patient queue from temporary storage
    restoreAllPatients(temp);
    
    // Return queue of patients currently in queue
    return filteredQueue;
}


//-----------------------------------------------------------------------------------------------------------------//


// This method filters and returns a queue of patients who are not currently assigned to any queue
    public LinkedQueue<Patient> filterPatientsNotInQueue() {
        
    // Create a new empty queue to store the filtered patients (those not in any queue)
    LinkedQueue<Patient> filteredQueue = new LinkedQueue<>();
    
    // Create a temporary queue to help preserve the original allPatients queue
    LinkedQueue<Patient> temp = new LinkedQueue<>();
    
    // Process all patients in the allPatients queue
    while (!allPatients.isEmpty()) {
        // Remove the first patient from the allPatients queue
        Patient p = allPatients.dequeue();
        // Add the patient to the temporary queue to preserve it
        temp.enqueue(p);
        // Check if the patient is not assigned to any queue (queueID is null)
        if (p.getQueueID() == null) {
            // If patient is not in any queue, add them to the filtered queue
            filteredQueue.enqueue(p);
        }
    }
    
    // Restore the original allPatients queue from the temporary queue
    restoreAllPatients(temp);
    // Return the queue containing only patients not assigned to any queue
    return filteredQueue;
}


//-----------------------------------------------------------------------------------------------------------------//


// This method handles the filtering of patients based on different criteria selected by the user
public void handleFilterPatients(PatientUI ui) {
    // Loop continuously until the user chooses to exit
    while (true) {
        // Display the filter options sub-menu to the user
        ui.showFilterOptions();  // Show filter sub-menu
        
        // Get the user's choice as an integer input
        int choice = ui.getIntInput();
        
        // Process the user's choice using a switch statement
        switch (choice) {
            case 1 -> {  // Filter patients by gender
                // Prompt the user to enter gender (F/M)
                ui.showMessage("Enter gender to filter (F/M): ");
                String gender = ui.getStringInput();
                
                // Filter patients by the specified gender
                LinkedQueue<Patient> filtered = filterPatientsByGender(gender);
                
                // Display the filtered results with appropriate header
                displayFilteredPatients(filtered, ui, "Gender: " + gender);
            }
            case 2 -> {  // Filter patients by blood type
                // Use the existing blood type prompt to get blood type input
                String bloodType = ui.promptBloodType();
                
                // Filter patients by the specified blood type
                LinkedQueue<Patient> filtered = filterPatientsByBloodType(bloodType);
                
                // Display the filtered results with appropriate header
                displayFilteredPatients(filtered, ui, "Blood Type: " + bloodType);
            }
            case 3 -> {  // Filter patients currently in a queue
                // Get all patients currently assigned to any queue
                LinkedQueue<Patient> filtered = filterPatientsInQueue();
                
                // Display the filtered results
                displayFilteredPatients(filtered, ui, "Patients in Queue");
            }
            case 4 -> {  // Filter patients not in any queue
                // Get all patients not assigned to any queue
                LinkedQueue<Patient> filtered = filterPatientsNotInQueue();
                
                // Display the filtered results
                displayFilteredPatients(filtered, ui, "Patients Not in Queue");
            }
            case 5 -> {  // Exit the filter menu and return to previous menu
                return;
            }
            default -> {  // Handle invalid menu choices
                ui.showMessage("Invalid option. Please try again.");
            }
        }
    }
}


//-----------------------------------------------------------------------------------------------------------------//

// This method handles the sorting of patients based on different criteria selected by the user
public void handleSortPatients(PatientUI ui) {
    // Loop continuously until the user chooses to exit
    while (true) {
        // Display the sorting options sub-menu to the user
        ui.showSortOptions();  // Show sort sub-menu
        
        // Get the user's choice as an integer input
        int choice = ui.getIntInput();
        
        // Process the user's choice using a switch statement
        switch (choice) {
            case 1 -> {  // Sort patients by name
                // Prompt user for sort order (ascending/descending) for name
                int order = ui.promptSortOrder("name");
                
                // Sort patients by name in the specified order (true for ascending)
                sortPatientsByName(order == 1);
                
                // Show confirmation message with sort direction
                ui.showMessage("Patients sorted by name " + (order == 1 ? "ascending" : "descending"));
                
                // Display all patients with the new sorting
                displayAllPatients(ui);
            }
            case 2 -> {  // Sort patients by age
                // Prompt user for sort order (ascending/descending) for age
                int order = ui.promptSortOrder("age");
                
                // Sort patients by age in the specified order (true for ascending)
                sortPatientsByAge(order == 1);
                
                // Show confirmation message with sort direction
                ui.showMessage("Patients sorted by age " + (order == 1 ? "ascending" : "descending"));
                
                // Display all patients with the new sorting
                displayAllPatients(ui);
            }
            case 3 -> {  // Sort patients by their position in queue
                // Sort patients by their queue position (no order option needed)
                sortPatientsByQueuePosition();
                
                // Show confirmation message
                ui.showMessage("Patients sorted by queue position.");
                
                // Display all patients with the new sorting
                displayAllPatients(ui);
            }
            case 4 -> {  // Exit the sort menu and return to previous menu
                return;
            }
            default -> {  // Handle invalid menu choices
                ui.showMessage("Invalid option. Please try again.");
            }
        }
    }
}


//-----------------------------------------------------------------------------------------------------------------//

/**
 * Sorts patients by name in either ascending (A-Z) or descending (Z-A) order based on the boolean parameter.
 * Uses a case-insensitive bubble sort algorithm.
 * 
 * When ascending=true: Sorts names A-Z (alphabetical order)
 * When ascending=false: Sorts names Z-A (reverse alphabetical order)
 * 
 * Features:
 * - Handles empty/single-element queues efficiently
 * - Converts queue to array for sorting
 * - Rebuilds the queue after sorting
 */


public void sortPatientsByName(boolean ascending) {
    if (allPatients.isEmpty() || allPatients.size() == 1) {
        return; // Already sorted
    }

    // Extract all patients into an array (using toArray)
    Patient[] patientsArray = allPatients.toArray(new Patient[0]);

    // Bubble sort by name (case-insensitive)
    boolean swapped;
    do {
        swapped = false;
        for (int i = 0; i < patientsArray.length - 1; i++) {
            int comparison = patientsArray[i].getName().compareToIgnoreCase(patientsArray[i + 1].getName());
            if ((ascending && comparison > 0) || (!ascending && comparison < 0)) {
                // Swap
                Patient temp = patientsArray[i];
                patientsArray[i] = patientsArray[i + 1];
                patientsArray[i + 1] = temp;
                swapped = true;
            }
        }
    } while (swapped);

    // Rebuild the queue
    allPatients.clear();
    for (Patient p : patientsArray) {
        allPatients.enqueue(p);
    }
}

//-----------------------------------------------------------------------------------------------------------------//

/**
 * Sorts patients by name in ascending (A-Z) order only.
 * Uses a case-insensitive bubble sort algorithm.
 * 
 * This is a simplified version that always sorts alphabetically.
 * 
 * Features:
 * - Hardcoded for ascending order
 * - More convenient when only A-Z sorting is needed
 * - Same underlying implementation as the parameterized version
 */


public void sortPatientsByName() {
    if (allPatients.isEmpty() || allPatients.size() == 1) {
        return; // Already sorted
    }

    // Extract all patients into an array (using toArray)
    Patient[] patientsArray = allPatients.toArray(new Patient[0]);

    // Bubble sort by name (case-insensitive)
    boolean swapped;
    do {
        swapped = false;
        for (int i = 0; i < patientsArray.length - 1; i++) {
            if (patientsArray[i].getName().compareToIgnoreCase(patientsArray[i + 1].getName()) > 0) {
                // Swap
                Patient temp = patientsArray[i];
                patientsArray[i] = patientsArray[i + 1];
                patientsArray[i + 1] = temp;
                swapped = true;
            }
        }
    } while (swapped);

    // Rebuild the queue
    allPatients.clear();
    for (Patient p : patientsArray) {
        allPatients.enqueue(p);
    }
}

//-----------------------------------------------------------------------------------------------------------------//

/**
 * These methods serve different purposes:
 * 
 * 1. sortPatientsByName(boolean ascending):
 *    - Flexible version that supports both A-Z and Z-A sorting
 *    - Used when sort direction needs to be determined at runtime
 *    - Better for dynamic sorting needs
 * 
 * 2. sortPatientsByName():
 *    - Simplified version that only sorts A-Z
 *    - More convenient when only alphabetical order is needed
 *    - Cleaner API for common case of ascending sort
 */


//-----------------------------------------------------------------------------------------------------------------//



/**
 * Sorts patients by age in either ascending (youngest to oldest) or descending (oldest to youngest) order
 * based on the boolean parameter. Uses a bubble sort algorithm.
 * 
 * When ascending=true: Sorts from youngest to oldest
 * When ascending=false: Sorts from oldest to youngest
 * 
 * Features:
 * - Calculates age from date of birth before comparing
 * - Handles empty/single-element queues efficiently
 * - Converts queue to array for sorting
 * - Rebuilds the queue after sorting
 */


public void sortPatientsByAge(boolean ascending) {
    if (allPatients.isEmpty() || allPatients.size() == 1) {
        return; // Already sorted
    }

    // Extract all patients into an array
    Patient[] patientsArray = allPatients.toArray(new Patient[0]);

    // Bubble sort by age
    boolean swapped;
    do {
        swapped = false;
        for (int i = 0; i < patientsArray.length - 1; i++) {
            int age1 = calculateAge(patientsArray[i].getDateOfBirth());
            int age2 = calculateAge(patientsArray[i + 1].getDateOfBirth());
            
            boolean shouldSwap = ascending ? (age1 > age2) : (age1 < age2);
            if (shouldSwap) {
                // Swap
                Patient temp = patientsArray[i];
                patientsArray[i] = patientsArray[i + 1];
                patientsArray[i + 1] = temp;
                swapped = true;
            }
        }
    } while (swapped);

    // Rebuild the queue
    allPatients.clear();
    for (Patient p : patientsArray) {
        allPatients.enqueue(p);
    }
}



//-----------------------------------------------------------------------------------------------------------------//


/**
 * Displays filtered patients in a formatted table view with their details.
 * Shows a message if no patients match the filter criteria.
 * 
 * @param filtered The queue of filtered patients to display
 * @param ui The PatientUI instance for displaying messages
 * @param filterTitle The title describing the filter criteria used
 * 
 * Features:
 * - Shows clear header with filter criteria
 * - Displays patient details in a formatted table
 * - Preserves the original filtered queue by using a temporary queue
 * - Handles empty results gracefully
 * - Includes queue status (In Queue/Not in Queue) for each patient
 */



private void displayFilteredPatients(LinkedQueue<Patient> filtered, PatientUI ui, String filterTitle) {
    if (filtered.isEmpty()) {
        ui.showMessage("No patients match the filter: " + filterTitle);
        return;
    }
    
    ui.showMessage("\n=== FILTERED PATIENTS (" + filterTitle + ") ===");
    ui.showMessage(String.format("%-10s %-20s %-15s %-12s %-6s",
        "PatientID", "Name", "IC Number", "Blood Type", "Queue Status"));
    ui.showMessage("----------------------------------------------------------");
    
    LinkedQueue<Patient> temp = new LinkedQueue<>();
    int count = 1;
    
    while (!filtered.isEmpty()) {
        Patient p = filtered.dequeue();
        temp.enqueue(p);
        
        String queueStatus = p.getQueueID() != null ? "In Queue" : "Not in Queue";
        ui.showMessage(String.format("%d. %-10s %-20s %-15s %-12s %-6s",
            count++,
            p.getPatientID(),
            p.getName(),
            p.getIdentificationNo(),
            p.getBloodType(),
            queueStatus));
    }
    
    // Restore the filtered queue if needed
    while (!temp.isEmpty()) {
        filtered.enqueue(temp.dequeue());
    }
    
    ui.pressEnterToContinue();
}



//-----------------------------------------------------------------------------------------------------------------//


public void sortPatientsByQueuePosition() {
    // Only makes sense for patients in queue
    LinkedQueue<Patient> inQueue = filterPatientsInQueue();
    LinkedQueue<Patient> notInQueue = filterPatientsNotInQueue();
    
    // Extract patients into an array for sorting
    Patient[] patientsArray = new Patient[inQueue.size()];
    int index = 0;
    LinkedQueue<Patient> temp = new LinkedQueue<>();
    
    // Copy patients to array
    while (!inQueue.isEmpty()) {
        Patient p = inQueue.dequeue();
        patientsArray[index++] = p;
        temp.enqueue(p);
    }
    
    // Restore inQueue
    while (!temp.isEmpty()) {
        inQueue.enqueue(temp.dequeue());
    }
    
    // Bubble sort by queue position
    boolean swapped;
    do {
        swapped = false;
        for (int i = 0; i < patientsArray.length - 1; i++) {
            int pos1 = getQueuePosition(patientsArray[i]);
            int pos2 = getQueuePosition(patientsArray[i+1]);
            if (pos1 > pos2) {
                // Swap
                Patient tempPatient = patientsArray[i];
                patientsArray[i] = patientsArray[i+1];
                patientsArray[i+1] = tempPatient;
                swapped = true;
            }
        }
    } while (swapped);
    
    // Rebuild the allPatients queue
    allPatients.clear();
    
    // Add sorted queued patients first
    for (Patient p : patientsArray) {
        allPatients.enqueue(p);
    }
    
    // Add non-queued patients
    while (!notInQueue.isEmpty()) {
        allPatients.enqueue(notInQueue.dequeue());
    }
}

//-----------------------------------------------------------------------------------------------------------------//



private String generateReportHeader() {
    StringBuilder header = new StringBuilder();
    
    header.append("\n========================================================\n");
    header.append("  TUNKU ABDUL RAHMAN UNIVERSITY HOSPITAL - ON-CAMPUS CLINIC  \n");
    header.append("========================================================\n");
    header.append("            REAL-TIME QUEUE MANAGEMENT SYSTEM REPORT           \n");
    header.append("========================================================\n\n");
    
    header.append("Report Type: ").append("PATIENT QUEUE STATISTICS").append("\n");
    header.append("Generated: ").append(getCurrentDateTime()).append("\n");
    header.append("Location: ").append("Student Health Center - Ground Floor").append("\n");
    
    
    header.append("========================================================\n");
    header.append("CONFIDENTIAL - For clinic staff use only\n");
    header.append("========================================================\n\n");
    
    return header.toString();
}

//-----------------------------------------------------------------------------------------------------------------//

// Helper method for timestamp
private String getCurrentDateTime() {
    return java.time.LocalDateTime.now().format(
        DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss"));
}

//-----------------------------------------------------------------------------------------------------------------//


/**
 * Generates a real-time daily queue report showing:
 * - Current queue status and processed patient counts
 * - System efficiency metrics with visual indicators
 * - Automated recommendations based on queue performance
 * 
 * Provides clinic staff with actionable insights for managing patient flow.
 */


public void generateDailyQueueReport() {
    // Simplified Header
    System.out.println(generateReportHeader());

    // Real-time Metrics
    int inQueue = patientQueue.size();
    int processed = processedPatients.size();
    int avgTime = AVG_CONSULTATION_TIME_MIN;
    
    System.out.println("\n REAL-TIME STATS");
   
    System.out.println("|--------------|------------|");
    System.out.printf ("| %-12s | %-12d |\n", "In Queue", inQueue);
    System.out.printf ("| %-12s | %-12d |\n", "Processed", processed);
    System.out.printf ("| %-12s | %-12d |\n", "Avg Time", avgTime);
    System.out.println("|--------------|------------|");

    // Efficiency Graph
    System.out.println("\n EFFICIENCY METER");
    int efficiency = (int)((processed * avgTime) / (inQueue + 0.1)); // Prevent div by zero
    efficiency = Math.min(100, Math.max(0, efficiency)); // Clamp 0-100
    
    System.out.print("│");
    for (int i = 0; i < 20; i++) {
        System.out.print(i*5 < efficiency ? " * " : "─");
    }
    System.out.printf("│ %d%%\n", efficiency);

    // Status Indicator
    System.out.println("\n STATUS: " + 
        (efficiency > 80 ? " Excellent" : 
         efficiency > 50 ? " Normal" : 
         "! Needs Attention"));

    // Simple Recommendations
    System.out.println("\n QUICK TIPS");
    if (inQueue > 10) {
        System.out.println("- Consider adding temporary staff");
    }
    if (avgTime > 20) {
        System.out.println("- Review consultation processes");
    }
    if (processed == 0) {
        System.out.println("- System just started");
    }

    // Footer
    System.out.println("\n===================================");
    System.out.println("=   Refresh report for live updates    =");
    System.out.println("===================================");
}


//-----------------------------------------------------------------------------------------------------------------//

/**
 * Generates a comprehensive health report for patients in the queue, including:
 * 1. BMI distribution with health risk assessment
 * 2. Blood type availability and emergency readiness
 * 3. Allergy alerts for common allergens
 * 
 * The report provides visual representations of data and highlights critical
 * health risks that require staff attention.
 */


public void generatePatientHealthReport() {
    // Header Section
    System.out.println(generateReportHeader());
    

    // SECTION 1: BMI DISTRIBUTION (Visual Health Risk Assessment)
    System.out.println("=================================");
    System.out.println("=        BMI HEALTH RISK PROFILE     =");
    System.out.println("=================================");
    
    LinkedQueue<Patient> tempBMI = new LinkedQueue<>();
    int[] bmiCounts = new int[4]; // Under, Normal, Over, Obese
    int highRiskPatients = 0;
    
    while (!patientQueue.isEmpty()) {
        Patient p = patientQueue.dequeue();
        double bmi = p.calculateBMI();
        if (bmi < 18.5) bmiCounts[0]++;
        else if (bmi < 25) bmiCounts[1]++;
        else if (bmi < 30) bmiCounts[2]++;
        else {
            bmiCounts[3]++;
            highRiskPatients++;
        }
        tempBMI.enqueue(p);
    }
    restoreQueue(tempBMI);
    
    System.out.println("\n[BMI DISTRIBUTION IN CURRENT QUEUE]");
    System.out.println("Category     | Count | Graph");
    System.out.println("-------------|-------|---------------------");
    String[] categories = {"Underweight", "Normal", "Overweight", "Obese"};
    for (int i = 0; i < categories.length; i++) {
        System.out.printf("%-12s | %-5d | %s\n", 
            categories[i], bmiCounts[i], " * ".repeat(bmiCounts[i]*2));
    }
    
    // BMI Health Advisory
    System.out.println("\n[HEALTH ADVISORY]");
    if (highRiskPatients > 0) {
        System.out.printf("!️ %d obese patients requiring priority nutrition counseling\n", highRiskPatients);
    }
    if (bmiCounts[0] > 0) {
        System.out.printf("!️ %d underweight patients needing dietary assessment\n", bmiCounts[0]);
    }

    // SECTION 2: BLOOD TYPE READINESS
    System.out.println("\n==================================");
    System.out.println("=        BLOOD TYPE READINESS        =");
    System.out.println("==================================");
    
    LinkedQueue<String> bloodTypes = new LinkedQueue<>();
    bloodTypes.enqueue("A+"); bloodTypes.enqueue("A-");
    bloodTypes.enqueue("B+"); bloodTypes.enqueue("B-");
    bloodTypes.enqueue("AB+"); bloodTypes.enqueue("AB-");
    bloodTypes.enqueue("O+"); bloodTypes.enqueue("O-");
    
    System.out.println("\n[BLOOD TYPE DISTRIBUTION]");
    System.out.println("Type | Count | Emergency Readiness");
    System.out.println("-----|-------|---------------------");
    
    while (!bloodTypes.isEmpty()) {
        String type = bloodTypes.dequeue();
        int count = countPatientsInQueueByBloodType(type);
        String alert = "";
        if (type.equals("O-") && count < 2) alert = "! Universal Donor Shortage";
        else if (type.equals("AB+") && count < 1) alert = "! Rare Recipient Type";
        System.out.printf("%-4s | %-5d | %s\n", type, count, alert);
    }

    // SECTION 3: ALLERGY ALERTS
    System.out.println("\n==================================");
    System.out.println("=          ALLERGY ALERTS            =");
    System.out.println("==================================");
    
    LinkedQueue<String> commonAllergens = new LinkedQueue<>();
    commonAllergens.enqueue("Peanuts");
    commonAllergens.enqueue("Seafood");
    commonAllergens.enqueue("Dust");
    commonAllergens.enqueue("Lactose");
    commonAllergens.enqueue("Pollen");
    
    int otherAllergies = 0;
    LinkedQueue<Patient> tempAllergy = new LinkedQueue<>();
    
    System.out.println("\n[COMMON ALLERGENS IN QUEUE]");
    while (!commonAllergens.isEmpty()) {
        String allergen = commonAllergens.dequeue();
        int affected = countPatientsWithAllergy(allergen);
        if (affected > 0) {
            System.out.printf("! %-10s: %d patient(s)\n", allergen, affected);
        }
    }
    
    // Count other allergies
    while (!patientQueue.isEmpty()) {
        Patient p = patientQueue.dequeue();
        if (!p.getAllergies().isEmpty() && 
            !containsCommonAllergen(p.getAllergies())) {
            otherAllergies++;
        }
        tempAllergy.enqueue(p);
    }
    restoreQueue(tempAllergy);
    
    if (otherAllergies > 0) {
        System.out.printf("! Other rare allergies: %d patient(s)\n", otherAllergies);
    }

    // Footer
    System.out.println("\n==================================================");
    System.out.println("KEY:");
    System.out.println("* - Each block represents 1 patient");
    System.out.println("! - Requires staff attention");
    System.out.println("==================================================");
}


//-----------------------------------------------------------------------------------------------------------------//



// Helper Method
private boolean containsCommonAllergen(String allergy) {
    String[] common = {"Peanuts", "Seafood", "Dust", "Lactose", "Pollen"};
    for (String a : common) {
        if (allergy.contains(a)) return true;
    }
    return false;
}


//-----------------------------------------------------------------------------------------------------------------//

private int countPatientsWithAllergy(String allergen) {
    int count = 0;
    LinkedQueue<Patient> temp = new LinkedQueue<>();
    
    while (!patientQueue.isEmpty()) {
        Patient p = patientQueue.dequeue();
        if (p.getAllergies().toUpperCase().contains(allergen.toUpperCase())) {
            count++;
        }
        temp.enqueue(p);
    }
    restoreQueue(temp);
    return count;
}

//-----------------------------------------------------------------------------------------------------------------//


// Helper methods
private int countPatientsInQueueByBloodType(String bloodType) {
    int count = 0;
    LinkedQueue<Patient> temp = new LinkedQueue<>();
    
    while (!patientQueue.isEmpty()) {
        Patient p = patientQueue.dequeue();
        if (p.getBloodType().equals(bloodType)) count++;
        temp.enqueue(p);
    }
    restoreQueue(temp);
    return count;
}



    
//-----------------------------------------------------------------------------------------------------------------//



    // Helper methods
    public int calculateAge(String dob) {
        try {
            String[] parts = dob.split("/");
            int birthYear = Integer.parseInt(parts[2]);
            int currentYear = LocalDate.now().getYear();
            return currentYear - birthYear;
        } catch (Exception e) {
            System.err.println("Error calculating age from DOB: " + dob);
            return 0;
        }
    }
    
    
//-----------------------------------------------------------------------------------------------------------------//

    

    private void restoreQueue(LinkedQueue<Patient> tempQueue) {
    // Clear any existing patients first to avoid duplicates
    LinkedQueue<Patient> clearQueue = new LinkedQueue<>();
    while (!patientQueue.isEmpty()) {
        clearQueue.enqueue(patientQueue.dequeue());
    }
    
    // Restore from temp
    while (!tempQueue.isEmpty()) {
        patientQueue.enqueue(tempQueue.dequeue());
    }
}
    
//-----------------------------------------------------------------------------------------------------------------//    
    
    public boolean patientExists(String identifier) {
    return findPatientInQueue(identifier) != null || findPatient(identifier) != null;
}
    
    
    
//-----------------------------------------------------------------------------------------------------------------//    

private Patient findPatientInQueue(String identifier) {
    LinkedQueue<Patient> temp = new LinkedQueue<>();
    Patient found = null;
    
    while (!patientQueue.isEmpty()) {
        Patient p = patientQueue.dequeue();
        temp.enqueue(p);
        if (p.getIdentificationNo().equals(identifier) || 
            p.getPatientID().equals(identifier)) {
            found = p;
            break;
        }
    }
    restoreQueue(temp);
    return found;
}

}


//-----------------------------------------------------------------------------------------------------------------//

