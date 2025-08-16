package control;

import adt.QueueInterface;
import adt.LinkedQueue;
import entity.MedicalTreatment;
import entity.Prescription;
import entity.DiagnosisTemplate; // Correctly changed from entity.*
import java.util.Date;

public class MedicalTreatmentControl {

    private QueueInterface<MedicalTreatment> treatmentHistory;
    private QueueInterface<DiagnosisTemplate> diagnosisTemplates;
    private PharmacyControl pharmacyControl;

    public MedicalTreatmentControl(PharmacyControl pharmacyControl) {
        this.treatmentHistory = new LinkedQueue<>();
        this.diagnosisTemplates = new LinkedQueue<>();
        this.pharmacyControl = pharmacyControl;
    }

    // --- Template Management Methods (Correct) ---
    public void addDiagnosisTemplate(String id, String name, String desc) {
        diagnosisTemplates.enqueue(new DiagnosisTemplate(id, name, desc));
    }

    public String[][] getDiagnosisTemplatesForDisplay() {
        DiagnosisTemplate[] templates = new DiagnosisTemplate[diagnosisTemplates.size()];
        templates = diagnosisTemplates.toArray(templates);
        String[][] displayData = new String[templates.length][3];
        for (int i = 0; i < templates.length; i++) {
            displayData[i][0] = String.valueOf(i + 1);
            displayData[i][1] = templates[i].getTemplateId();
            displayData[i][2] = templates[i].getDiagnosisName();
        }
        return displayData;
    }

    private DiagnosisTemplate findTemplateById(String templateId) {
        DiagnosisTemplate[] templates = new DiagnosisTemplate[diagnosisTemplates.size()];
        templates = diagnosisTemplates.toArray(templates);
        for (DiagnosisTemplate template : templates) {
            if (template.getTemplateId().equalsIgnoreCase(templateId)) {
                return template;
            }
        }
        return null;
    }

    // --- CRUD Operations ---

    // CREATE (This is correct from the last version)
   public String createTreatment(String patientID, String doctorID, String patientSicknessDesc, String sickType,
                                  String chosenTemplateID, String medicationID, int quantity) {

        // **STEP 1: INTERACTION WITH PHARMACY MODULE**
        // Ask the PharmacyControl if the requested medication ID is valid by checking
        // against the data loaded by the ClinicInitializer.
        // **FIX: Corrected the typo from "medicationIdIdExists" to "medicationIdExists"**
        if (!pharmacyControl.medicationIdExists(medicationID)) {
            return "Error: Medication ID '" + medicationID + "' does not exist in the pharmacy stock.";
        }
        
        // Find the chosen diagnosis template
        DiagnosisTemplate template = findTemplateById(chosenTemplateID);
        if (template == null) {
            return "Error: Invalid Diagnosis Template ID selected.";
        }
        
        // Generate a unique ID for this treatment record
        String newTreatmentID = "T" + String.format("%03d", treatmentHistory.size() + 1);

        // Create the full medical treatment record
        MedicalTreatment newTreatment = new MedicalTreatment(
            newTreatmentID, template.getTemplateId(), patientID, doctorID, medicationID,
            patientSicknessDesc, sickType, template.getDefaultDescription(), new Date()
        );
        newTreatment.setDispensedQuantity(quantity);
        treatmentHistory.enqueue(newTreatment);

        // **STEP 2: INTERACTION WITH PHARMACY MODULE**
        // Create a prescription object to send to the pharmacy's approval queue.
        Prescription newPrescription = new Prescription(newTreatmentID, patientID, medicationID, quantity);
        
        // Send the request to the PharmacyControl.
        pharmacyControl.requestPrescriptionApproval(newPrescription);

        return "Medical Treatment " + newTreatmentID + " created successfully. Prescription sent to pharmacy for approval.";
    }

    // READ (This is now the only "get history" method)
    public String[][] getTreatmentHistoryForDisplay() {
        MedicalTreatment[] history = new MedicalTreatment[treatmentHistory.size()];
        history = treatmentHistory.toArray(history);
        String[][] displayData = new String[history.length][8];
        for (int i = 0; i < history.length; i++) {
            MedicalTreatment t = history[i];
            displayData[i][0] = t.getTreatmentID();
            displayData[i][1] = t.getPatientID();
            displayData[i][2] = t.getDoctorID();
            displayData[i][3] = t.getPatientSicknessDescription();
            displayData[i][4] = t.getDiagnosisDescription();
            displayData[i][5] = t.getMedicationID();
            displayData[i][6] = String.valueOf(t.getDispensedQuantity());
            displayData[i][7] = t.getCreatedDate().toString();
        }
        return displayData;
    }

    // UPDATE (Now uses treatmentID as the key)
    public boolean updateTreatment(String treatmentID, String newSicknessDesc, String newDiagnosisDesc) {
        MedicalTreatment treatmentToUpdate = findTreatmentById(treatmentID);
        if (treatmentToUpdate != null) {
            treatmentToUpdate.setPatientSicknessDescription(newSicknessDesc);
            treatmentToUpdate.setDiagnosisDescription(newDiagnosisDesc);
            return true;
        }
        return false;
    }

    // DELETE (Now uses treatmentID as the key)
    public boolean deleteTreatment(String treatmentID) {
        MedicalTreatment treatmentToDelete = findTreatmentById(treatmentID);
        if (treatmentToDelete != null) {
            QueueInterface<MedicalTreatment> tempQueue = new LinkedQueue<>();
            while (!treatmentHistory.isEmpty()) {
                MedicalTreatment current = treatmentHistory.dequeue();
                if (!current.getTreatmentID().equals(treatmentID)) {
                    tempQueue.enqueue(current);
                }
            }
            this.treatmentHistory = tempQueue;
            return true;
        }
        return false;
    }

    // --- Helper Methods ---

    // Safe check for the UI, now using treatmentID
    public boolean treatmentIdExists(String treatmentID) {
        return findTreatmentById(treatmentID) != null;
    }

    // Private helper, now finds by treatmentID
    private MedicalTreatment findTreatmentById(String treatmentID) {
        if (treatmentHistory.isEmpty()) return null;
        MedicalTreatment[] historyArray = new MedicalTreatment[treatmentHistory.size()];
        historyArray = treatmentHistory.toArray(historyArray);
        for (MedicalTreatment treat : historyArray) {
            if (treat.getTreatmentID().equalsIgnoreCase(treatmentID)) {
                return treat;
            }
        }
        return null;
    }
}