=== ENTITY PACKAGE GUIDE ===
Purpose:
- Contains pure data classes representing real-world objects
- Serves as the system's "database" structure

What to put here (Example: Patient.java):
1. FIELDS (Private)
   - All attributes should be private
   - Example:
     ```
     private String patientId;
     private String name;
     private LocalDate birthDate;
     ```

2. CONSTRUCTORS
   - Include both no-arg and full constructors
   - Example:
     ```
     public Patient() {}
     public Patient(String id, String name) {
         this.patientId = id;
         this.name = name;
     }
     ```

3. STANDARD METHODS
   - Getters/Setters for all fields
   - toString() for debugging
   - equals()/hashCode() for comparisons
   - Example:
     ```
     public String getPatientId() { return patientId; }
     public void setName(String name) { this.name = name; }
     
     @Override
     public String toString() {
         return "Patient[" + patientId + "] " + name;
     }
     ```

4. VALIDATION (Optional)
   - Add input validation in setters
   - Example:
     ```
     public void setBirthDate(LocalDate date) {
         if (date.isAfter(LocalDate.now())) {
             throw new IllegalArgumentException("Birth date cannot be in future");
         }
         this.birthDate = date;
     }
     ```

DO NOT INCLUDE:
- Business logic
- UI code
- ADT usage