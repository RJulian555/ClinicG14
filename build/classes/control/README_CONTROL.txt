=== CONTROL PACKAGE GUIDE ===
Purpose:
- Contains business logic and system operations
- Mediates between Boundary and Entity layers

What to put here (Example: PatientManager.java):
1. ADT USAGE
   - Should declare ADT interfaces (not implementations)
   - Example:
     ```
     private QueueInterface<Patient> patientQueue;
     public PatientManager() {
         patientQueue = new LinkedQueue<>();
     }
     ```

2. CORE METHODS
   - Perform system operations
   - Handle data validation
   - Example:
     ```
     public void registerPatient(Patient p) {
         if (findPatient(p.getPatientId()) != null) {
             throw new DuplicatePatientException();
         }
         patientQueue.enqueue(p);
     }
     ```

3. ERROR HANDLING
   - Throw custom exceptions for business rules
   - Example:
     ```
     public Patient getNextPatient() throws EmptyQueueException {
         if (patientQueue.isEmpty()) {
             throw new EmptyQueueException("No patients in queue");
         }
         return patientQueue.dequeue();
     }
     ```

Rules:
- Never interact directly with UI
- All data access through Entities
