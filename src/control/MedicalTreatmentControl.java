package control;

import adt.LinkedQueue;
import adt.QueueInterface;
import boundary.MedicalTreatmentUI;
import entity.MedicalTreatment;
import java.util.Date;

public class MedicalTreatmentControl {

    private QueueInterface<MedicalTreatment> treatmentQueue;

    public MedicalTreatmentControl() {
        treatmentQueue = new LinkedQueue<>();
    }

    // === CRUD Operations ===
    public void addTreatment(String diagnosisID, String patientID, String doctorID, String medicationID,
                         String sicknessDescription, String sickType, String diagnosisDescription, Date createdDate) {
    MedicalTreatment treatment = new MedicalTreatment(
            diagnosisID, patientID, doctorID, medicationID,
            diagnosisDescription, sickType, sicknessDescription, createdDate
    );
    treatmentQueue.enqueue(treatment);
    System.out.println("Treatment added successfully.");
}


    public MedicalTreatment removeTreatment() {
        if (treatmentQueue.isEmpty()) {
            System.out.println("No treatments to remove.");
            return null;
        }
        MedicalTreatment removed = treatmentQueue.dequeue();
        System.out.println("Removed treatment for patient ID: " + removed.getPatientID());
        return removed;
    }

    private String[] wrapText(String text, int width) {
    if (text == null) return new String[]{""};
    return text.replaceAll("(.{1," + width + "})(\\s+|$)", "$1\n").split("\n");
}

public void displayAllTreatments() {
    if (treatmentQueue.isEmpty()) {
        System.out.println("No treatments recorded.");
        return;
    }

    System.out.println("\n--- All Treatments ---");

    int sicknessWidth = 30;
    int diagnosisWidth = 20;
    int dateWidth = 28;

    // Table border + header
    System.out.printf("+--------------+--------------+--------------+--------------+--------------------------------+--------------+----------------------+------------------------------+\n");
    System.out.printf("| %-12s | %-12s | %-12s | %-12s | %-30s | %-12s | %-20s | %-28s |\n",
        "DiagnosisID", "PatientID", "DoctorID", "MedID",
        "Sickness Description", "Sick Type", "Diagnosis", "Created Date");
    System.out.printf("+--------------+--------------+--------------+--------------+--------------------------------+--------------+----------------------+------------------------------+\n");

    QueueInterface<MedicalTreatment> tempQueue = new LinkedQueue<>();

    while (!treatmentQueue.isEmpty()) {
        MedicalTreatment t = treatmentQueue.dequeue();

        // Wrap text for sickness, diagnosis, and date
        String[] sicknessLines = wrapText(t.getSicknessDescription(), sicknessWidth);
        String[] diagnosisLines = wrapText(t.getDiagnosisDescription(), diagnosisWidth);
        String[] dateLines = wrapText(t.getCreatedDate().toString(), dateWidth);

        // Find the max lines needed for this row
        int maxLines = Math.max(sicknessLines.length,
                        Math.max(diagnosisLines.length, dateLines.length));

        // Print each wrapped line
        for (int i = 0; i < maxLines; i++) {
            System.out.printf("| %-12s | %-12s | %-12s | %-12s | %-30s | %-12s | %-20s | %-28s |\n",
                (i == 0 ? t.getDiagnosisID() : ""),
                (i == 0 ? t.getPatientID() : ""),
                (i == 0 ? t.getDoctorID() : ""),
                (i == 0 ? t.getMedicationID() : ""),
                (i < sicknessLines.length ? sicknessLines[i] : ""),
                (i == 0 ? t.getSickType() : ""),
                (i < diagnosisLines.length ? diagnosisLines[i] : ""),
                (i < dateLines.length ? dateLines[i] : "")
            );
        }

        // Row separator
        System.out.printf("+--------------+--------------+--------------+--------------+--------------------------------+--------------+----------------------+------------------------------+\n");

        tempQueue.enqueue(t);
    }

    // Restore the queue
    while (!tempQueue.isEmpty()) {
        treatmentQueue.enqueue(tempQueue.dequeue());
    }
}
public void updateDispensedMedicationQuantity(String medicationID, int quantity) {
    if (treatmentQueue.isEmpty()) {
        System.out.println("No treatments recorded.");
        return;
    }

    boolean found = false;
    QueueInterface<MedicalTreatment> tempQueue = new LinkedQueue<>();

    while (!treatmentQueue.isEmpty()) {
        MedicalTreatment t = treatmentQueue.dequeue();

        if (t.getMedicationID().equalsIgnoreCase(medicationID)) {
            // Here we set/update quantity in the entity
            t.setDispensedQuantity(quantity);
            System.out.println("Medication quantity for Medical ID " + medicationID +
                               " updated to reduce " + quantity + " units");
            found = true;
        }

        tempQueue.enqueue(t);
    }

    // Restore the queue
    while (!tempQueue.isEmpty()) {
        treatmentQueue.enqueue(tempQueue.dequeue());
    }

    if (!found) {
        System.out.println("Medication ID " + medicationID + " not found.");
    }
}


    public int getTotalTreatments() {
        return treatmentQueue.size();
    }

    public int getFollowUpNeededCount() {
        int count = 0;
        QueueInterface<MedicalTreatment> tempQueue = new LinkedQueue<>();

        while (!treatmentQueue.isEmpty()) {
            MedicalTreatment t = treatmentQueue.dequeue();
            if (t.getSickType().equalsIgnoreCase("Follow-up")) {
                count++;
            }
            tempQueue.enqueue(t);
        }

        // Restore
        while (!tempQueue.isEmpty()) {
            treatmentQueue.enqueue(tempQueue.dequeue());
        }

        return count;
    }

    // === Reporting Feature 1 ===
    public void generatePrescription() {
    if (treatmentQueue.isEmpty()) {
        System.out.println("No prescriptions to generate.");
        return;
    }

    System.out.println("\n--- Prescription List ---");

    // Table header
    System.out.printf("%-15s %-15s %-15s %-30s%n",
        "Diagnosis ID", "Patient ID", "Medication ID", "DispensedQuantity" , "Diagnosis");

    System.out.println("--------------------------------------------------------------------------");

    QueueInterface<MedicalTreatment> tempQueue = new LinkedQueue<>();

    while (!treatmentQueue.isEmpty()) {
        MedicalTreatment t = treatmentQueue.dequeue();
        System.out.printf("%-15s %-15s %-15s %-30s%n",
            t.getDiagnosisID(),
            t.getPatientID(),
            t.getMedicationID(),
            t.getDispensedQuantity(),
            t.getDiagnosisDescription());
        tempQueue.enqueue(t);
    }

    // Restore original queue
    while (!tempQueue.isEmpty()) {
        treatmentQueue.enqueue(tempQueue.dequeue());
    }
}
    // === Reporting Feature 2 ===
    public void patientSickTrendReport() {
        if (treatmentQueue.isEmpty()) {
            System.out.println("No treatments recorded for trend report.");
            return;
        }
        System.out.println("\n--- Patient Sick Type Trend Report ---");

        int acuteCount = 0, chronicCount = 0, followUpCount = 0;
        QueueInterface<MedicalTreatment> tempQueue = new LinkedQueue<>();

        while (!treatmentQueue.isEmpty()) {
            MedicalTreatment t = treatmentQueue.dequeue();
            String type = t.getSickType().toLowerCase();
            switch (type) {
                case "acute" -> acuteCount++;
                case "chronic" -> chronicCount++;
                case "follow-up" -> followUpCount++;
            }
            tempQueue.enqueue(t);
        }

        // Restore
        while (!tempQueue.isEmpty()) {
            treatmentQueue.enqueue(tempQueue.dequeue());
        }

        System.out.printf("Acute cases: %d%n", acuteCount);
        System.out.printf("Chronic cases: %d%n", chronicCount);
        System.out.printf("Follow-up cases: %d%n", followUpCount);
    }

    // Optional Helper
    public void queueListPrescription() {
        generatePrescription(); // reuse reporting
    }
    
    public static void main(String[] args) {
        MedicalTreatmentUI ui = new MedicalTreatmentUI();
        ui.run();
    }
}
