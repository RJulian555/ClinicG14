/*
 * Control Class: PharmacyControl.java
 * Manages the business logic for the pharmacy module.
 */
package control;

import adt.QueueInterface;
import adt.LinkedQueue;
import entity.Pharmacy;
import java.util.Comparator;
import java.util.Date;

public class PharmacyControl {

    private QueueInterface<Pharmacy> medicationStock;

    public PharmacyControl() {
        // Initialize the queue and add some sample data
        medicationStock = new LinkedQueue<>();
        medicationStock.enqueue(new Pharmacy("M001", "Paracetamol", "Pain reliever", 10.50, 100, "Tablet", new Date()));
        medicationStock.enqueue(new Pharmacy("M002", "Ibuprofen", "Anti-inflammatory", 15.00, 50, "Tablet", new Date()));
        medicationStock.enqueue(new Pharmacy("M003", "Cough Syrup", "Soothes cough", 25.00, 30, "Liquid", new Date()));
        medicationStock.enqueue(new Pharmacy("M004", "Antacid", "Relieves heartburn", 12.75, 80, "Liquid", new Date()));
        medicationStock.enqueue(new Pharmacy("M005", "Aspirin", "Blood thinner", 8.00, 20, "Tablet", new Date()));
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