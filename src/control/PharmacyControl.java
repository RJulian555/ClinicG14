/*
 * Control Class: PharmacyControl.java
 * Manages the business logic for the pharmacy module.
 */
package control;

import adt.LinkedQueue;
import adt.QueueInterface;
import entity.*;
import java.util.Comparator;
import java.util.Date;


public class PharmacyControl {

    // Here is my ADT. It's a private variable, hidden from the UI.
    private QueueInterface<Pharmacy> medicationStock;
    // **NEW QUEUES FOR THE PRESCRIPTION WORKFLOW**
    private QueueInterface<Prescription> pendingPrescriptions;
    private QueueInterface<Prescription> heldPrescriptions;

     public PharmacyControl() {
        this.medicationStock = new LinkedQueue<>();
        this.pendingPrescriptions = new LinkedQueue<>();
        this.heldPrescriptions = new LinkedQueue<>();
    }

      // This method is called by the DAO initializer
    public void addMedication(Pharmacy pharmacy) {
        medicationStock.enqueue(pharmacy);
    }
    
    // This method is called by the UI. It takes raw data.
   public void addNewMedication(String id, String name, String desc, double price, int qty, String type) {
    // 1. It creates the Entity object
    Pharmacy newMed = new Pharmacy(id, name, desc, price, qty, type, new Date());
    
    // 2. It calls another method to handle the ADT interaction
    this.addMedication(newMed);
}

     public String[][] getMedicationStockForDisplay(String sortBy) {
        // 1. Sort the data using the ADT's sort method
         if ("name".equalsIgnoreCase(sortBy)) {
            // Sort by Medication Name
            medicationStock.sort(Comparator.comparing(Pharmacy::getMedicationName));
        } else {
            // **DEFAULT CASE:** For any other input (including "id" or null), sort by ID.
            medicationStock.sort(Comparator.comparing(Pharmacy::getMedicationID));
        }

        // 2. Convert the ADT to an array of Entity objects
        Pharmacy[] medsArray = new Pharmacy[medicationStock.size()];
        medsArray = medicationStock.toArray(medsArray);

        // 3. Create a simple 2D String array to hold the data for the UI
        String[][] displayData = new String[medicationStock.size()][6];

        // 4. Loop through the entity objects and extract the data into the simple array
        for (int i = 0; i < medsArray.length; i++) {
            Pharmacy med = medsArray[i]; // This is the Entity interaction
            displayData[i][0] = med.getMedicationID();
            displayData[i][1] = med.getMedicationName();
            displayData[i][2] = med.getMedicationDescription();
            displayData[i][3] = String.format("RM %.2f", med.getMedicationPrice());
            displayData[i][4] = String.valueOf(med.getMedicationQuantity());
            displayData[i][5] = med.getMedicationType();
        }

        // 5. Return the simple data structure to the UI
        return displayData;
    }
   
     /**
     * **NEW, SAFE METHOD FOR THE UI**
     * Checks if a medication with the given ID exists in the system.
     * This method is safe for the UI to call because it only returns a boolean.
     *
     * @param id The Medication ID to check.
     * @return true if the medication exists, false otherwise.
     */
    public boolean medicationIdExists(String id) {
        // It uses the private helper method to do the actual search.
        return findMedicationById(id) != null;
    }
     
    // Helper method to find a medication by its ID
     //change private to prevent the UI from accessing entities directly
    private Pharmacy findMedicationById(String id) {
        Pharmacy[] meds = new Pharmacy[medicationStock.size()];
        meds = medicationStock.toArray(meds);
        for (Pharmacy med : meds) {
            if (med.getMedicationID().equalsIgnoreCase(id)) {
                return med;
            }
        }
        return null;
    }

    // Corresponds to +editMedication()  
    public boolean editMedicationName(String id, String newName) {
        Pharmacy med = findMedicationById(id);
        if (med != null) {
            med.setMedicationName(newName);
            return true;
        }
        return false;
    }

    public boolean editMedicationDescription(String id, String newDesc) {
        Pharmacy med = findMedicationById(id);
        if (med != null) {
            med.setMedicationDescription(newDesc);
            return true;
        }
        return false;
    }

    public boolean editMedicationPrice(String id, double newPrice) {
        Pharmacy med = findMedicationById(id);
        if (med != null) {
            med.setMedicationPrice(newPrice);
            return true;
        }
        return false;
    }

    public boolean editMedicationQuantity(String id, int newQty) {
        Pharmacy med = findMedicationById(id);
        if (med != null) {
            med.setMedicationQuantity(newQty);
            return true;
        }
        return false;
    }

    public boolean editMedicationType(String id, String newType) {
        Pharmacy med = findMedicationById(id);
        if (med != null) {
            med.setMedicationType(newType);
            return true;
        }
        return false;
    }

    


    // Corresponds to +deleteMedication()
    public boolean deleteMedication(String id) {
        Pharmacy medToDelete = findMedicationById(id);
        if (medToDelete != null) {
            return medicationStock.hold(medToDelete);
        }
        return false;
    }

    // Corresponds to +dispenseMedication()
    public boolean dispenseMedication(String id, int quantityToDispense) {
        Pharmacy med = findMedicationById(id);
        if (med != null && med.getMedicationQuantity() >= quantityToDispense) {
            med.setMedicationQuantity(med.getMedicationQuantity() - quantityToDispense);
            return true;
        }
        return false;
    }

    
    
   

    // Report 1: +lowStockReport()
    public String[][] getLowStockReportForDisplay(int threshold) {
        // 1. Call the private helper method to get the filtered queue of entities.
        QueueInterface<Pharmacy> lowStockQueue = this.generateLowStockReport(threshold);

        // 2. Convert the ADT to an array of Entity objects.
        Pharmacy[] medsArray = new Pharmacy[lowStockQueue.size()];
        medsArray = lowStockQueue.toArray(medsArray);

        // 3. Create a simple 2D String array for the UI.
        String[][] displayData = new String[lowStockQueue.size()][6];

        // 4. Loop through the entities and extract their data into the simple array.
        for (int i = 0; i < medsArray.length; i++) {
            Pharmacy med = medsArray[i];
            displayData[i][0] = med.getMedicationID();
            displayData[i][1] = med.getMedicationName();
            displayData[i][2] = med.getMedicationDescription();
            displayData[i][3] = String.format("RM %.2f", med.getMedicationPrice());
            displayData[i][4] = String.valueOf(med.getMedicationQuantity());
            displayData[i][5] = med.getMedicationType();
        }

        // 5. Return the simple data structure to the UI.
        return displayData;
    }

    /**
     * This method now becomes a private helper. Its only job is to perform the
     * business logic of filtering the data.
     */
    private QueueInterface<Pharmacy> generateLowStockReport(int threshold) {
        return medicationStock.filter(med -> med.getMedicationQuantity() < threshold);
    }

    // Report 2: +medicationTrentReport() -> Renamed to generateTotalStockValueReport
    public double generateTotalStockValueReport() {
        double totalValue = 0.0;
        Pharmacy[] meds = new Pharmacy[medicationStock.size()];
        meds = medicationStock.toArray(meds);

        for (Pharmacy med : meds) {
            totalValue += med.getMedicationPrice() * med.getMedicationQuantity();
        }
        return totalValue;
    }
    
    public boolean isStockEmpty() {
        return medicationStock.isEmpty();
    }
    
     // **NEW METHODS FOR PRESCRIPTION MANAGEMENT**

    /**
     * Called by MedicalTreatmentControl to add a new prescription to the approval queue.
     */
    public void requestPrescriptionApproval(Prescription prescription) {
        pendingPrescriptions.enqueue(prescription);
    }

    /**
     * Gets the next pending prescription for the UI to display, without removing it.
     * @return A string array with prescription details, or null if the queue is empty.
     */
    public String[] getNextPrescriptionForApproval() {
        if (pendingPrescriptions.isEmpty()) {
            return null;
        }
        Prescription p = pendingPrescriptions.getFront();
        Pharmacy med = findMedicationById(p.getMedicationID());
        return new String[]{
            p.getTreatmentID(),
            p.getPatientID(),
            p.getMedicationID(),
            med != null ? med.getMedicationName() : "Unknown Medication",
            String.valueOf(p.getQuantity()),
            med != null ? String.valueOf(med.getMedicationQuantity()) : "N/A"
        };
    }

    /**
     * Approves the prescription at the front of the queue, reducing medication stock.
     * @return A status message.
     */
    public String approveNextPrescription() {
        if (pendingPrescriptions.isEmpty()) {
            return "No pending prescriptions to approve.";
        }
        Prescription p = pendingPrescriptions.dequeue();
        boolean success = this.dispenseMedication(p.getMedicationID(), p.getQuantity());
        if (success) {
            return "Prescription " + p.getTreatmentID() + " approved. Stock updated.";
        } else {
            // If dispensing fails (e.g., insufficient stock), put it on hold.
            heldPrescriptions.enqueue(p);
            return "Error: Insufficient stock for " + p.getMedicationID() + ". Prescription " + p.getTreatmentID() + " has been put on hold.";
        }
    }

    /**
     * Declines the prescription at the front of the queue and moves it to the "held" queue.
     * @return A status message.
     */
    public String declineNextPrescription() {
        if (pendingPrescriptions.isEmpty()) {
            return "No pending prescriptions to decline.";
        }
        Prescription p = pendingPrescriptions.dequeue();
        heldPrescriptions.enqueue(p);
        return "Prescription " + p.getTreatmentID() + " declined and moved to the held list.";
    }

    // **NEW METHODS FOR MANAGING HELD PRESCRIPTIONS**

    public String[][] getHeldPrescriptionsForDisplay() {
        Prescription[] heldArray = new Prescription[heldPrescriptions.size()];
        heldArray = heldPrescriptions.toArray(heldArray);
        String[][] displayData = new String[heldArray.length][4];
        for(int i=0; i<heldArray.length; i++){
            displayData[i][0] = heldArray[i].getTreatmentID();
            displayData[i][1] = heldArray[i].getPatientID();
            displayData[i][2] = heldArray[i].getMedicationID();
            displayData[i][3] = String.valueOf(heldArray[i].getQuantity());
        }
        return displayData;
    }

    /**
     * Releases a held prescription by moving it back to the pending approval queue.
     */
    public String releaseHeldPrescription(String treatmentID) {
        Prescription toRelease = findAndRemoveHeld(treatmentID);
        if (toRelease != null) {
            pendingPrescriptions.enqueue(toRelease);
            return "Prescription " + treatmentID + " has been released back to the approval queue.";
        }
        return "Error: Held prescription with ID " + treatmentID + " not found.";
    }

    /**
     * Permanently deletes a held prescription.
     */
    public String deleteHeldPrescription(String treatmentID) {
        Prescription toDelete = findAndRemoveHeld(treatmentID);
        if (toDelete != null) {
            return "Prescription " + treatmentID + " has been permanently deleted.";
        }
        return "Error: Held prescription with ID " + treatmentID + " not found.";
    }
    
    // Private helper to find and remove from the hold queue
    private Prescription findAndRemoveHeld(String treatmentID){
        if(heldPrescriptions.isEmpty()) return null;
        
        QueueInterface<Prescription> tempQueue = new LinkedQueue<>();
        Prescription found = null;

        while(!heldPrescriptions.isEmpty()){
            Prescription current = heldPrescriptions.dequeue();
            if(current.getTreatmentID().equalsIgnoreCase(treatmentID) && found == null){
                found = current;
            } else {
                tempQueue.enqueue(current);
            }
        }
        // Restore the hold queue
        while(!tempQueue.isEmpty()){
            heldPrescriptions.enqueue(tempQueue.dequeue());
        }
        return found;
    }
    
    
}