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

     public PharmacyControl() {
        this.medicationStock = new LinkedQueue<>();
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
}