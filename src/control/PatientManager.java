/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

/**
 *
 * @author natalie
 */

import adt.LinkedQueue;
import adt.QueueInterface;




<<<<<<< HEAD
import entity.Doctor;
=======

>>>>>>> 93ed186f0bb04ef2f00033157df5552c8a2bb78e
import boundary.PatientUI;
import entity.Consultation;
import entity.Patient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class PatientManager {
<<<<<<< HEAD
=======
    
    
   
    private final QueueInterface<Patient> allPatients = new LinkedQueue<>();
    private final QueueInterface<Patient> patientQueue = new LinkedQueue<>();
    private final QueueInterface<Patient> processedPatients = new LinkedQueue<>();
    //private final LinkedQueue<Patient> allPatients = new LinkedQueue<>();
>>>>>>> 93ed186f0bb04ef2f00033157df5552c8a2bb78e
    
    
   
    private final QueueInterface<Patient> allPatients;
    private final QueueInterface<Patient> patientQueue = new LinkedQueue<>();
   
    
    
    
    private int patientCounter;
    private int queueCounter;
<<<<<<< HEAD

    public PatientManager() {
        this.allPatients = new LinkedQueue<>();
    }
    

=======
    private static final int AVG_CONSULTATION_TIME_MIN = 15;
    
    
     /*
    public PatientManager(DoctorManager doctorManager) {
    this.doctorManager = doctorManager;
} */
    
>>>>>>> 93ed186f0bb04ef2f00033157df5552c8a2bb78e
    public QueueInterface<Patient> getAllPatients() {
    return allPatients;         // your master list
}
    
    
<<<<<<< HEAD
//-----------------------------------------------------------------------------------------------------------------//    
      
    
=======
>>>>>>> 93ed186f0bb04ef2f00033157df5552c8a2bb78e
  
    public void addSamplePatient(Patient patient, boolean inQueue) {
    // Add patient object to master list queue
    allPatients.enqueue(patient);
    
    // Add same patient to active queue if flag is true
    if (inQueue) {
        patientQueue.enqueue(patient);
    }
    
    // Start try-block for safe counter updates
    try {
        // Grab digits after "P" in patient ID
        String pid = patient.getPatientID().substring(1);
        // Convert those digits to int
        int currentPid = Integer.parseInt(pid);
        
        // Make patientCounter at least one higher than existing ID
        if (currentPid >= patientCounter) {
            patientCounter = currentPid + 1;
        }
        
        // Only update queueCounter if patient is actually going into queue
        if (inQueue && patient.getQueueID() != null) {
            // Grab digits after "Q" in queue ID
            String qid = patient.getQueueID().substring(1);
            // Convert those digits to int
            int currentQid = Integer.parseInt(qid);
            
            // Make queueCounter at least one higher than existing queue ID
            if (currentQid >= queueCounter) {
                queueCounter = currentQid + 1;
            }
        }
    // Catch any parsing or other runtime errors silently
    } catch (Exception e) {
        // Print short error message to console
        System.err.println("Error updating counters: " + e.getMessage());
        // Let method finish; patient still added
    }
}
    
    

//-----------------------------------------------------------------------------------------------------------------//    


    public void handleRegistration(PatientUI ui) {
    // create new unique patient ID
    String patientID = generatePatientID();
    // create new unique queue ID
    String queueID = generateQueueID();
    // grab today’s date as string
    String registrationDate = getCurrentDate();
    
    // let UI build the Patient object
    Patient newPatient = ui.collectPatientDetails(patientID, queueID, registrationDate);
    // proceed only if user didn’t cancel
    if (newPatient != null) {
        
        newPatient.setQueueID(generateQueueID());  
        // put patient in live queue
        enqueuePatient(newPatient);
        // also store patient in master list
        allPatients.enqueue(newPatient);
        
        // wipe screen
        ui.clearScreen();
        // show recap of new patient
        ui.showRegistrationSummary(newPatient);
        
        // figure out where patient stands in line
        int position = getQueuePosition(newPatient);
        // patients in front of them
        int patientsAhead = position - 1;
        // display that info
        ui.showQueuePosition(position, patientsAhead);
        
        // wait for user to press Enter
        ui.pressEnterToContinue();
        // clear screen before returning
        ui.clearScreen();
    }
}
    
//-----------------------------------------------------------------------------------------------------------------//    

    private boolean isPatientAlreadyInQueue(Patient patient) {
    // temp queue to hold patients while we scan
    LinkedQueue<Patient> temp = new LinkedQueue<>();
    
    // assume not found until proven otherwise
    boolean found = false;
    
    // empty the live queue into temp, one by one
    while (!patientQueue.isEmpty()) {
        Patient p = patientQueue.dequeue();   // grab front
        temp.enqueue(p);                      // park it in temp
        
        // IC already present? flip flag and stop searching
        if (p.getIdentificationNo().equals(patient.getIdentificationNo())) {
            found = true;
            break;
        }
    }
    
    // move everyone back from temp to original queue
    while (!temp.isEmpty()) {
        patientQueue.enqueue(temp.dequeue());
    }
    
    // true if duplicate IC detected, false otherwise
    return found;
}
<<<<<<< HEAD
    
=======
>>>>>>> 93ed186f0bb04ef2f00033157df5552c8a2bb78e

    
    
//-----------------------------------------------------------------------------------------------------------------//
    
    
  
// immutable container object for a single queue lookup
public class QueueStatus {

    // 1-based position of the queried patient in the queue
    private final int position;

    // snapshot of the next N patients after the queried patient
    private final QueueInterface<Patient> nextPatients;

    // constructor captures the two pieces of data
    public QueueStatus(int position, QueueInterface<Patient> nextPatients) {
        this.position = position;
        this.nextPatients = nextPatients;
    }

    // getter for the patient's queue position
    public int getPosition() {
        return position;
    }

    // getter for the queue snapshot
    public QueueInterface<Patient> getNextPatients() {
        return nextPatients;
    }
}
    
    

//-----------------------------------------------------------------------------------------------------------------//



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
        
        // Step 4 – fresh queue ID every time
        patient.setQueueID(generateQueueID());
        
        // Step 5: Add the patient to the queue
        enqueuePatient(patient);
        
        // Step 6: Get the patient's position and the next 3 patients in line
        int position = getQueuePosition(patient);
        QueueInterface<Patient> nextPatients = getNextPatients(3);
        
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
    QueueInterface<Patient> nextPatients = getNextPatients(3);
    
    // Step 5: Create and return a new QueueStatus object with the gathered information
    return new QueueStatus(position, nextPatients);
}


//-----------------------------------------------------------------------------------------------------------------//


// Returns the next N patients without changing the queue order.
private QueueInterface<Patient> getNextPatients(int count) {
    QueueInterface<Patient> nextPatients = new LinkedQueue<>();
    QueueInterface<Patient> temp         = new LinkedQueue<>();

    int n = Math.min(count, patientQueue.size());

    // Take N patients
    for (int i = 0; i < n; i++) {
        Patient p = patientQueue.dequeue();
        nextPatients.enqueue(p);
        temp.enqueue(p);
    }

    // Save the rest
    while (!patientQueue.isEmpty()) {
        temp.enqueue(patientQueue.dequeue());
    }

    // Put everyone back
    restoreQueue(temp);
    return nextPatients;
}



//-----------------------------------------------------------------------------------------------------------------//

    
public void enqueuePatient(Patient patient) {
    // Delegates to the patientQueue's enqueue method
    // The patient will be added to the tail/rear of the queue
    patientQueue.enqueue(patient);
}


//-----------------------------------------------------------------------------------------------------------------//    



// Serve the next patient in line
public void processNextPatient() {

    if (patientQueue.isEmpty()) {                 // no one waiting
        System.out.println("\n No patients in queue.");
        return;
    }

    Patient processed = patientQueue.dequeue();   // remove front patient

    // Show who is being served
    System.out.println("\n NOW SERVING");
    System.out.println("=====================");
    System.out.println("Queue ID: " + processed.getQueueID());
    System.out.println("Name: " + processed.getName());
    System.out.println("=====================");
    
    viewFullQueue();

}


    
//-----------------------------------------------------------------------------------------------------------------//

    

// Show where “currentPatient” stands and who is next.
private void displayQueueStatus(Patient currentPatient) {
    // 1) Print a simple header
    System.out.println("\n========== QUEUE STATUS ==========");

    // 2) Ask helper: “What spot is this patient in?”
    int pos = getQueuePosition(currentPatient);

    // 3) Tell the patient
    System.out.println("Your position: " + pos);

    // 4) Tell how many people are in front of them
    System.out.println("Patients ahead: " + (pos - 1));

    // 5) Small blank line, then title for the next list
    System.out.println("\nNext 3 patients:");

    // 6) Get (but do NOT remove from real queue) the next 3 patients
    QueueInterface<Patient> next = getNextPatients(3);

    // 7) Counter so we can show 1. 2. 3.
    int n = 1;

    // 8) Loop through those 3 patient objects
    while (!next.isEmpty()) {
        // 9) Take one out of the temporary queue
        Patient p = next.dequeue();

        // 10) Print their queue ID and name with the counter
        System.out.println(n++ + ". " + p.getQueueID() + " - " + p.getName());
    }

    // 11) Print footer line
    System.out.println("==================================");
}
    
//-----------------------------------------------------------------------------------------------------------------//    
   /* 
    public void displayNextThreePatients() {
    // Get the next 3 patients without disturbing the real queue
    QueueInterface<Patient> next = getNextPatients(3);

    int n = 1;
    while (!next.isEmpty()) {
        Patient p = next.dequeue();
        System.out.println(n++ + ". " + p.getQueueID() + " - " + p.getName());
    }
}
*/

    
//-----------------------------------------------------------------------------------------------------------------//    
    

private int getQueuePosition(Patient patient) {
    QueueInterface<Patient> temp = new LinkedQueue<>();
    int pos = 1;

    while (!patientQueue.isEmpty()) {
        Patient p = patientQueue.dequeue();
        temp.enqueue(p);
        if (p == patient) break;
        pos++;
    }
    // grab any leftovers
    while (!patientQueue.isEmpty()) {
        temp.enqueue(patientQueue.dequeue());
    }
    restoreQueue(temp);
    return (pos <= temp.size()) ? pos : -1;
}
    
//-----------------------------------------------------------------------------------------------------------------//    



public boolean isQueueEmpty() {
    // Directly returns the result of the underlying queue's isEmpty() method
    return patientQueue.isEmpty();
}


public Patient getFirstInQueue() {
    // Uses ternary operator to safely return null if queue is empty
    return patientQueue.isEmpty() ? null : patientQueue.getFront();
}

public void viewFullQueue() {
    if (patientQueue.isEmpty()) {
        System.out.println("\n+------- Queue is empty -------+");
        return;
    }

    System.out.println("\n+---------- Full Queue ----------+");

    QueueInterface<Patient> temp = new LinkedQueue<>();
    int pos = 1;

    while (!patientQueue.isEmpty()) {
        Patient p = patientQueue.dequeue();
        System.out.printf("| %-2d. %-5s - %-18s |\n",
                          pos++, p.getQueueID(), p.getName());
        temp.enqueue(p);
    }

    System.out.println("+--------------------------------+");
    restoreQueue(temp);
}


//-----------------------------------------------------------------------------------------------------------------//





public String generatePatientID() {
    // Format the current patientCounter value with leading zeros (3 digits)
    // and prefix with 'P', then increment counter for next call
    return "P" + String.format("%03d", patientCounter++);
}



public String generateQueueID() {
    // Format the current queueCounter value with leading zeros (3 digits)
    // and prefix with 'Q', then increment counter for next call
    return "Q" + String.format("%03d", queueCounter++);
}



private String getCurrentDate() {
    // Get current date using Java 8+ LocalDate
    // Format as month/day/year (e.g., "06/15/2023")
    return LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
}

private void restoreAllPatients(QueueInterface<Patient> temp) {
    while (!temp.isEmpty()) {
        allPatients.enqueue(temp.dequeue());
    }
}

//-----------------------------------------------------------------------------------------------------------------//


public Patient findPatient(String identifier) {
    QueueInterface<Patient> temp = new LinkedQueue<>();
    Patient found = null;

    while (!allPatients.isEmpty()) {
        Patient p = allPatients.dequeue();
        temp.enqueue(p);
        if (p.getIdentificationNo().equals(identifier) ||
            p.getPatientID().equals(identifier)) {
            found = p;
            break;
        }
    }

    restoreAllPatients(temp);
    return found;
}
   
    
//-----------------------------------------------------------------------------------------------------------------//



    
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
    // Delete from the master list
    boolean removed = deleteFromQueue(allPatients, patient);

    // Delete from the active queue (if present)
    deleteFromQueue(patientQueue, patient);

    return removed;
}

/* Generic helper that removes one item and rebuilds the same queue */
private boolean deleteFromQueue(QueueInterface<Patient> queue, Patient toDelete) {
    QueueInterface<Patient> temp = new LinkedQueue<>();
    boolean found = false;

    // drain
    while (!queue.isEmpty()) {
        Patient p = queue.dequeue();
        if (p != toDelete) {
            temp.enqueue(p);
        } else {
            found = true;
        }
    }

    // refill (in-place)
    while (!temp.isEmpty()) {
        queue.enqueue(temp.dequeue());
    }

    return found;
}


//-----------------------------------------------------------------------------------------------------------------//

public void displayAllPatients(PatientUI ui) {
    if (allPatients.isEmpty()) {
        ui.showMessage("No patients in the database.");
        return;
    }

    QueueInterface<Patient> temp = new LinkedQueue<>();

    // header
    ui.showMessage("");
    ui.showMessage("+----+------------+------------------------+--------------+------+-----+-------+---------------+");
    ui.showMessage("| No | PatientID  | Name                   | IC Number    | Sex  | Age | BMI   | Status        |");
    ui.showMessage("+----+------------+------------------------+--------------+------+-----+-------+---------------+");

    int count = 1;
    while (!allPatients.isEmpty()) {
        Patient p = allPatients.dequeue();
        temp.enqueue(p);

        double bmi = p.calculateBMI();
        String bmiStatus = interpretBMI(bmi);
        String queueStatus = p.getQueueID() != null ? "In Queue" : "";

        ui.showMessage(String.format("| %-2d | %-10s | %-22s | %-12s | %-4s | %-3d | %-5.1f | %-13s |",
                count++,
                p.getPatientID(),
                p.getName().length() > 22 ? p.getName().substring(0, 20) + ".." : p.getName(),
                p.getIdentificationNo(),
                p.getGender(),
                calculateAge(p.getDateOfBirth()),
                bmi,
                bmiStatus));
    }

    ui.showMessage("+----+------------+------------------------+--------------+------+-----+-------+---------------+");
    restoreAllPatients(temp);
}

//-----------------------------------------------------------------------------------------------------------------//

// Helper method to interpret BMI value
private String interpretBMI(double bmi) {
    if (bmi < 18.5) return "Under weight";
    else if (bmi < 25) return "Normal weight";
    else if (bmi < 30) return "Over weight";
    else return "Obese";
}


//-----------------------------------------------------------------------------------------------------------------//




public QueueInterface<Patient> filterPatientsByGender(String gender) {
    QueueInterface<Patient> filtered = new LinkedQueue<>();
    QueueInterface<Patient> temp = new LinkedQueue<>();

    while (!allPatients.isEmpty()) {
        Patient p = allPatients.dequeue();
        temp.enqueue(p);
        if (p.getGender().equalsIgnoreCase(gender)) {
            filtered.enqueue(p);
        }
    }

    restoreAllPatients(temp);
    return filtered;
}



//-----------------------------------------------------------------------------------------------------------------//


public QueueInterface<Patient> filterPatientsByBloodType(String bloodType) {
    QueueInterface<Patient> filtered = new LinkedQueue<>();
    QueueInterface<Patient> temp = new LinkedQueue<>();

    while (!allPatients.isEmpty()) {
        Patient p = allPatients.dequeue();
        temp.enqueue(p);
        if (p.getBloodType().equalsIgnoreCase(bloodType)) {
            filtered.enqueue(p);
        }
    }

    restoreAllPatients(temp);
    return filtered;
}



//-----------------------------------------------------------------------------------------------------------------//





public QueueInterface<Patient> filterPatientsInQueue() {
    QueueInterface<Patient> filtered = new LinkedQueue<>();
    QueueInterface<Patient> temp = new LinkedQueue<>();

    while (!allPatients.isEmpty()) {
        Patient p = allPatients.dequeue();
        temp.enqueue(p);
        if (p.getQueueID() != null) {
            filtered.enqueue(p);
        }
    }

    restoreAllPatients(temp);
    return filtered;
}


//-----------------------------------------------------------------------------------------------------------------//


// Return patients not currently assigned to any queue
public QueueInterface<Patient> filterPatientsNotInQueue() {
    QueueInterface<Patient> filtered = new LinkedQueue<>();
    QueueInterface<Patient> temp = new LinkedQueue<>();

    while (!allPatients.isEmpty()) {
        Patient p = allPatients.dequeue();
        temp.enqueue(p);
        if (p.getQueueID() == null) {
            filtered.enqueue(p);
        }
    }

    restoreAllPatients(temp);
    return filtered;
}


//-----------------------------------------------------------------------------------------------------------------//


// Handle patient filtering based on user-selected criteria
public void handleFilterPatients(PatientUI ui) {
    while (true) {
        ui.showFilterOptions();
        int choice = ui.getIntInput();

        switch (choice) {
            case 1 -> {
                ui.showMessage("Enter gender to filter (F/M): ");
                String gender = ui.getStringInput();
                QueueInterface<Patient> filtered = filterPatientsByGender(gender);
                displayFilteredPatients(filtered, ui, "Gender: " + gender);
            }
            case 2 -> {
                String bloodType = ui.promptBloodType();
                QueueInterface<Patient> filtered = filterPatientsByBloodType(bloodType);
                displayFilteredPatients(filtered, ui, "Blood Type: " + bloodType);
            }
            case 3 -> {
                QueueInterface<Patient> filtered = filterPatientsInQueue();
                displayFilteredPatients(filtered, ui, "Patients in Queue");
            }
            case 4 -> {
                QueueInterface<Patient> filtered = filterPatientsNotInQueue();
                displayFilteredPatients(filtered, ui, "Patients Not in Queue");
            }
            case 5 -> {
                return;
            }
            default -> ui.showMessage("Invalid option. Please try again.");
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


public void displayFilteredPatients(QueueInterface<Patient> filtered,
                                    PatientUI ui,
                                    String filterTitle) {

    if (filtered.isEmpty()) {
        ui.showMessage("No patients match the filter: " + filterTitle);
        return;
    }

    ui.showMessage("");   // blank line
    ui.showMessage("+----+------------+------------------------+--------------+------------+");
    ui.showMessage("| No | PatientID  | Name                   | IC Number    | Queue Status |");
    ui.showMessage("+----+------------+------------------------+--------------+------------+");

    int count = 1;
    QueueInterface<Patient> temp = new LinkedQueue<>();
    while (!filtered.isEmpty()) {
        Patient p = filtered.dequeue();
        temp.enqueue(p);

        String queueStatus = p.getQueueID() != null ? "In Queue" : "Not in Queue";
        ui.showMessage(String.format("| %-2d | %-10s | %-22s | %-12s | %-10s |",
                count++,
                p.getPatientID(),
                p.getName().length() > 22 ? p.getName().substring(0, 20) + ".." : p.getName(),
                p.getIdentificationNo(),
                queueStatus));
    }

    ui.showMessage("+----+------------+------------------------+--------------+------------+");
    while (!temp.isEmpty()) {
        filtered.enqueue(temp.dequeue());
    }

    ui.pressEnterToContinue();
}


//-----------------------------------------------------------------------------------------------------------------//

public void sortPatientsByQueuePosition() {
    // Get queued and non-queued patients via the interface
    QueueInterface<Patient> inQueue      = filterPatientsInQueue();
    QueueInterface<Patient> notInQueue   = filterPatientsNotInQueue();

    // Build array for sorting
    Patient[] patientsArray = new Patient[inQueue.size()];
    int index = 0;

    QueueInterface<Patient> temp = new LinkedQueue<>();
    while (!inQueue.isEmpty()) {
        Patient p = inQueue.dequeue();
        patientsArray[index++] = p;
        temp.enqueue(p);
    }
    // restore inQueue snapshot
    while (!temp.isEmpty()) {
        inQueue.enqueue(temp.dequeue());
    }

    // Bubble-sort by queue position
    boolean swapped;
    do {
        swapped = false;
        for (int i = 0; i < patientsArray.length - 1; i++) {
            int pos1 = getQueuePosition(patientsArray[i]);
            int pos2 = getQueuePosition(patientsArray[i + 1]);
            if (pos1 > pos2) {
                Patient swap = patientsArray[i];
                patientsArray[i] = patientsArray[i + 1];
                patientsArray[i + 1] = swap;
                swapped = true;
            }
        }
    } while (swapped);

    // Rebuild master list
    allPatients.clear();
    for (Patient p : patientsArray) {
        allPatients.enqueue(p);
    }
    while (!notInQueue.isEmpty()) {
        allPatients.enqueue(notInQueue.dequeue());
    }
}

//-----------------------------------------------------------------------------------------------------------------//



private String generateReportHeader() {
    return "\n=============================\n"
         + "         TARUMT G14 CLINIC\n"
         + "=============================\n"
         + "Generated: " + getCurrentDateTime() + "\n"
         + "CONFIDENTIAL - Clinic staff only\n\n";
}


//-----------------------------------------------------------------------------------------------------------------//

// Helper method for timestamp
private String getCurrentDateTime() {
    return java.time.LocalDateTime.now().format(
        DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss"));
}

//-----------------------------------------------------------------------------------------------------------------//

public void generateMonthlyConsultationCalendar(ConsultationManager cm) {
    System.out.print(generateReportHeader());
    final int W = 86;
    final String LINE   = "-".repeat(W);
    final String DOUBLE = "=".repeat(W);

    // --- Gather data ---
    QueueInterface<Patient> patients = getAllPatients();
    QueueInterface<Patient> tempP    = new LinkedQueue<>();
    // Map: YearMonth -> Map<PatientID, Count>
    java.util.Map<java.time.YearMonth, java.util.Map<String, Integer>> calendar = new java.util.TreeMap<>();

    while (!patients.isEmpty()) {
        Patient p = patients.dequeue();
        tempP.enqueue(p);

        QueueInterface<Consultation> consults = cm.getConsultationsByPatient(p.getPatientID());
        QueueInterface<Consultation> tempC    = new LinkedQueue<>();
        while (!consults.isEmpty()) {
            Consultation c = consults.dequeue();
            tempC.enqueue(c);

            java.time.YearMonth ym = java.time.YearMonth.from(c.getConsultationDate());
            calendar
                .computeIfAbsent(ym, k -> new java.util.TreeMap<>())
                .merge(p.getPatientID(), 1, Integer::sum);
        }
        while (!tempC.isEmpty()) consults.enqueue(tempC.dequeue());
    }
    while (!tempP.isEmpty()) patients.enqueue(tempP.dequeue());

    // --- Header ---
    System.out.println("+" + DOUBLE + "+");
    System.out.println("| " + StringCenter.center("PATIENT MONTHLY VISIT LOG", W - 2) + " |");
    System.out.println("+" + LINE + "+");

    if (calendar.isEmpty()) {
        System.out.println("| No consultations on record                        |");
        System.out.println("+" + LINE + "+");
        return;
    }

    // ========== 1. TABLE ==========
    java.time.YearMonth currentMonth = null;

    for (var monthEntry : calendar.entrySet()) {
    java.time.YearMonth ym = monthEntry.getKey();

    // ---- separator / header when month changes ----
    if (!ym.equals(currentMonth)) {
        if (currentMonth != null) System.out.println();        // blank line
        System.out.printf("--- %s ---%n", ym);                 // month header
        System.out.printf("| %-25s | %-6s |%n", "Patient", "Visits");
        System.out.println("+" + "-".repeat(27) + "+" + "-".repeat(8) + "+");
        currentMonth = ym;
    }

    // ---- patients of this month ----
    for (var patientEntry : monthEntry.getValue().entrySet()) {
        Patient pat = findPatient(patientEntry.getKey());
        String name = (pat == null) ? "Unknown" : pat.getName();
        System.out.printf("| %-25s | %-10s | %-6d |%n", name, ym, patientEntry.getValue());
    }
}
System.out.println("+" + LINE + "+");

    // ========== 2. SEPARATE GRAPH ==========
    System.out.println();
    System.out.println("+" + "-".repeat(40) + "+");
    System.out.println("| " + StringCenter.center("MONTHLY VISIT SUMMARY (GRAPH)", 38) + " |");
    System.out.println("+----+------------+------------------------+");
    System.out.println("| #  | Month      | Graph                  |");
    System.out.println("+----+------------+------------------------+");

    // Aggregate by month
    java.util.Map<java.time.YearMonth, Integer> monthTotals = new java.util.TreeMap<>();
    for (var e : calendar.entrySet()) {
        int total = e.getValue().values().stream().mapToInt(i -> i).sum();
        monthTotals.put(e.getKey(), total);
    }

    int row = 1;   // <-- fixes the counter
    for (var e : monthTotals.entrySet()) {
        String bar = "*".repeat(Math.min(e.getValue(), 20));
        System.out.printf("| %-2d | %-10s | %-22s |\n", row++, e.getKey(), bar);
    }

    System.out.println("+----+------------+------------------------+");
}

//-----------------------------------------------------------------------------------------------------------------//

public void generatePatientVisitSummary(PatientUI ui,
                                        ConsultationManager cm,
                                        DoctorManager dm) {
    System.out.print(generateReportHeader());
    ui.clearScreen();

    final int W = 93;
    final String LINE   = "-".repeat(W);
    final String DOUBLE = "=".repeat(W);

    /* ---------- collect data once ---------- */
    QueueInterface<Patient> q   = getAllPatients();
    QueueInterface<Patient> tmp = new LinkedQueue<>();
    java.util.List<PatientRow> rows = new java.util.ArrayList<>();

    while (!q.isEmpty()) {
        Patient p = q.dequeue();
        tmp.enqueue(p);

         QueueInterface<Consultation> cons   = cm.getConsultationsByPatient(p.getPatientID());
        QueueInterface<Consultation> tmpCon = new LinkedQueue<>();

        int visits = 0;
        java.util.Set<String> docs = new java.util.TreeSet<>();
        while (!cons.isEmpty()) {
            Consultation c = cons.dequeue();
            tmpCon.enqueue(c);
            visits++;
            Doctor d = dm.getDoctorByID(c.getDoctorId());
            if (d != null) docs.add(d.getName());
        }
        while (!tmpCon.isEmpty()) cons.enqueue(tmpCon.dequeue());

        rows.add(new PatientRow(p, docs, visits));
    }
    while (!tmp.isEmpty()) q.enqueue(tmp.dequeue());

    /* ---------- SECTION 1 : FULL TABLE (unchanged) ---------- */
    System.out.println("+" + DOUBLE + "+");
    System.out.println("| " + StringCenter.center("PATIENTS & THEIR LIFETIME DOCTOR ROSTER", W - 2) + " |");
    System.out.println("+" + LINE + "+");

    System.out.printf("| %-5s | %-22s | %-30s | %-6s |%n",
                      "ID", "Name", "Doctors (Lifetime)", "Visits");
    System.out.println("+" + "-".repeat(7) + "+" + "-".repeat(24) + "+" + "-".repeat(32) +
                       "+" + "-".repeat(8) + "+");

    for (PatientRow r : rows) {
        String docs = String.join(", ", r.doctors);
        if (docs.isEmpty()) docs = "-";
        System.out.printf("| %-5s | %-22s | %-30s | %-6d |%n",
                          r.patient.getPatientID(),
                          r.patient.getName().length() > 22
                              ? r.patient.getName().substring(0, 19) + "..."
                              : r.patient.getName(),
                          docs.length() > 30 ? docs.substring(0, 27) + "..." : docs,
                          r.visits);
    }
    System.out.println("+" + LINE + "+");

    /* ---------- SECTION 2 : TOP-10 BAR-CHART ---------- */
    rows.sort((a, b) -> Integer.compare(b.visits, a.visits));   // highest first
    int limit = Math.min(10, rows.size());

    System.out.println();
    System.out.println("+" + DOUBLE + "+");
    System.out.println("| " + StringCenter.center("TOP 10 HIGH-VISIT PATIENTS (* = 1 visit)", W - 2) + " |");
    System.out.println("+" + LINE + "+");

    for (int i = 0; i < limit; i++) {
        PatientRow r = rows.get(i);
        String bar = "*".repeat(Math.min(r.visits, 20));   // cap at 20 stars
        System.out.printf("%2d. %-6s %-22s %s%n",
                          (i + 1),
                          r.patient.getPatientID(),
                          r.patient.getName().length() > 22
                              ? r.patient.getName().substring(0, 19) + "..."
                              : r.patient.getName(),
                          bar);
    }
    System.out.println("+" + LINE + "+");
}

/* helper DTO */
private static class PatientRow {
    final Patient patient;
    final java.util.Set<String> doctors;
    final int visits;
    PatientRow(Patient p, java.util.Set<String> d, int v) {
        this.patient = p; this.doctors = d; this.visits = v;
    }
}


//-----------------------------------------------------------------------------------------------------------------//

/** convenience centering helper */
static class StringCenter {
    public static String center(String s, int len) {
        if (s == null) s = "";
        int pad = len - s.length();
        if (pad <= 0) return s;
        int left = pad / 2;
        return " ".repeat(left) + s + " ".repeat(pad - left);
    }
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

    

    private void restoreQueue(QueueInterface<Patient> tempQueue) {
    patientQueue.clear();                 // faster than dequeue-loop
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
    QueueInterface<Patient> temp = new LinkedQueue<>();
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




//-----------------------------------------------------------------------------------------------------------------//

    public void setPatientCounter(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setQueueCounter(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
<<<<<<< HEAD
//-----------------------------------------------------------------------------------------------------------------//
  
    
    
// Returns the most-recent doctor name for a given patient
public String getLatestConsultationDoctorName(ConsultationManager cm,
                                              DoctorManager dm,
                                              Patient patient){
    Consultation latest = getLatestConsultation(cm, patient); // find latest consult
    if (latest == null) return "";                 // no consult → ""
    entity.Doctor d = dm.getDoctorByID(latest.getDoctorId()); // fetch doctor
    return (d == null) ? "" : d.getName();         // return name or ""
}

// Returns the newest consultation for a patient
public Consultation getLatestConsultation(ConsultationManager cm, Patient patient) {
    Consultation latest = null;                    // result holder
    adt.QueueInterface<Consultation> q = cm.getConsultationsByPatient(patient.getPatientID()); // queue of consults
    adt.QueueInterface<Consultation> tempQueue = new adt.LinkedQueue<>(); // helper queue
    while (!q.isEmpty()) {                         // scan every consult
        Consultation c = q.dequeue();              // take one
        tempQueue.enqueue(c);                      // store for restore
        if (latest == null) latest = c;            // first becomes latest
        else {                                     // compare by date then time
            int cmp = c.getConsultationDate().compareTo(latest.getConsultationDate());
            if (cmp > 0 || (cmp == 0 && 
                c.getConsultationTime().compareTo(latest.getConsultationTime()) > 0))
                latest = c;
        }
    }
    while (!tempQueue.isEmpty()) q.enqueue(tempQueue.dequeue()); // restore original queue
    return latest;                                // newest consult (or null)
}


//-----------------------------------------------------------------------------------------------------------------//

=======
    
public String getLatestConsultationDoctorName(ConsultationManager cm,
                                              DoctorManager dm,
                                              Patient patient){
    Consultation latest = getLatestConsultation(cm, patient);
    if (latest == null) return "";
    entity.Doctor d = dm.getDoctorByID(latest.getDoctorId());
    return (d == null) ? "" : d.getName();
>>>>>>> 93ed186f0bb04ef2f00033157df5552c8a2bb78e
}

    
    // In PatientManager class - FIXED VERSION
public Consultation getLatestConsultation(ConsultationManager cm, Patient patient) {
    Consultation latest = null;

    // Use the patient's ID instead of this.patientID
    adt.QueueInterface<Consultation> q = cm.getConsultationsByPatient(patient.getPatientID());
    
    // CRITICAL: Use temporary queue to avoid destructive operations
    adt.QueueInterface<Consultation> tempQueue = new adt.LinkedQueue<>();
    
    while (!q.isEmpty()) {
        Consultation c = q.dequeue();
        tempQueue.enqueue(c);
        
        if (latest == null) {
            latest = c;
        } else {
            // Compare by date first, then time
            int cmp = c.getConsultationDate().compareTo(latest.getConsultationDate());
            if (cmp > 0 || (cmp == 0 && 
                c.getConsultationTime().compareTo(latest.getConsultationTime()) > 0)) {
                latest = c;
            }
        }
    }
    
    // RESTORE ORIGINAL QUEUE - This was missing!
    while (!tempQueue.isEmpty()) {
        q.enqueue(tempQueue.dequeue());
    }
    
    return latest;
}

    
    
    
}