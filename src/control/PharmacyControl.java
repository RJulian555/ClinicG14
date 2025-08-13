/*
 * Control Class: PharmacyControl.java
 * Manages the business logic for the pharmacy module.
 */
package control;

import adt.QueueInterface;
import adt.LinkedQueue;
import entity.Pharmacy;
import boundary.*;
import java.util.Comparator;
import java.util.Date;


public class PharmacyControl {

    private QueueInterface<Pharmacy> medicationStock;

    public PharmacyControl() {
        // Initialize the queue and add some sample data
        medicationStock = new LinkedQueue<>();
       
    }

     // The Initializer will call this method.
    public void addMedication(Pharmacy pharmacy) {
        medicationStock.enqueue(pharmacy);
    }
    
    // Corresponds to +addNewMedication()
    public void addNewMedication(Pharmacy newMed) {
        medicationStock.enqueue(newMed);
    }

    // Helper method to find a medication by its ID
    public Pharmacy findMedicationById(String id) {
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
    public boolean editMedication(String id, String newName, String newDesc, double newPrice, int newQty, String newType) {
        Pharmacy med = findMedicationById(id);
        if (med != null) {
            med.setMedicationName(newName);
            med.setMedicationDescription(newDesc);
            med.setMedicationPrice(newPrice);
            med.setMedicationQuantity(newQty);
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

    // Corresponds to +listAllMedicationStock()
    public QueueInterface<Pharmacy> getAllMedicationStock() {
        medicationStock.sort(Comparator.comparing(Pharmacy::getMedicationName));
        return medicationStock;
    }

    // Report 1: +lowStockReport()
    public QueueInterface<Pharmacy> generateLowStockReport(int threshold) {
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
}