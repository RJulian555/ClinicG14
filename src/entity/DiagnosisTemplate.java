/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author user
 */
/**
 * Represents a single, pre-defined diagnosis template from the database.
 * This is a read-only template with an ID, a name, and a default description.
 */
public class DiagnosisTemplate {
    private final String templateId;
    private final String diagnosisName;
    private final String defaultDescription;

    public DiagnosisTemplate(String templateId, String diagnosisName, String defaultDescription) {
        this.templateId = templateId;
        this.diagnosisName = diagnosisName;
        this.defaultDescription = defaultDescription;
    }

    // --- Getters ---
    public String getTemplateId() { return templateId; }
    public String getDiagnosisName() { return diagnosisName; }
    public String getDefaultDescription() { return defaultDescription; }
}
