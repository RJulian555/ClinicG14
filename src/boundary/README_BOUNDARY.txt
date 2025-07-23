=== BOUNDARY PACKAGE GUIDE ===
Purpose:
- Handles all user interaction
- Presents data to users and collects input

What to put here (Example: PatientUI.java):
1. COMPONENTS
   - Scanner for console input (or Swing components)
   - Control class instance
   - Example:
     ```
     private PatientManager manager;
     private Scanner scanner;
     
     public PatientUI() {
         manager = new PatientManager();
         scanner = new Scanner(System.in);
     }
     ```

2. MENU METHODS
   - Should be clearly organized
   - Example:
     ```
     public void showMainMenu() {
         System.out.println("1. Register Patient");
         System.out.println("2. View Next Patient");
         // ...
     }
     ```

3. INPUT HANDLING
   - Validate all user input
   - Example:
     ```
     private String getValidId() {
         while (true) {
             System.out.print("Enter patient ID: ");
             String input = scanner.nextLine();
             if (input.matches("P\\d{4}")) {
                 return input;
             }
             System.out.println("Invalid format (should be P followed by 4 digits)");
         }
     }
     ```

Rules:
- Never access Entities directly
- All business logic goes through Control
