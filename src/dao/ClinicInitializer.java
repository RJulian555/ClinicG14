package dao;

import adt.*;
import control.*;
import entity.*;
import java.util.Date;
/**
 *
 * @author user
 */
public class ClinicInitializer {
    
    
     public static void initializeSampleDoctors(DoctorManager manager) {
        try {
            // Cardiology  
            addSampleDoctor(manager, "D101", "Dr. Ahmad", "Cardiology", "+60123456789", 12, 150.00, new String[]{"2023-12-25", "2024-01-01"}, "9am-5pm");
            addSampleDoctor(manager, "D102", "Dr. Siti", "Cardiology", "+60198765432", 8, 120.00, new String[]{"2023-12-31"}, "10am-6pm");
            addSampleDoctor(manager, "D103", "Dr. Lim", "Cardiology", "+60111223344", 15, 180.00, new String[]{}, "8am-4pm");
            addSampleDoctor(manager, "D104", "Dr. Fatimah", "Cardiology", "+60122334455", 10, 160.00, new String[]{"2024-02-14"}, "11am-7pm");

            // Pediatrics  
            addSampleDoctor(manager, "D201", "Dr. Mei Ling", "Pediatrics", "+60345678901", 15, 100.00, new String[]{}, "8am-4pm");
            addSampleDoctor(manager, "D202", "Dr. Kumar", "Pediatrics", "+60456789012", 5, 80.00, new String[]{"2024-02-01"}, "2pm-10pm");
            addSampleDoctor(manager, "D203", "Dr. Ali", "Pediatrics", "+60333444555", 7, 90.00, new String[]{"2024-01-15"}, "9am-5pm");
            addSampleDoctor(manager, "D204", "Dr. Aina", "Pediatrics", "+60444555666", 12, 110.00, new String[]{}, "10am-6pm");

            // Orthopedics  
            addSampleDoctor(manager, "D301", "Dr. Rajesh", "Orthopedics", "+60567890123", 20, 200.00, new String[]{"2023-12-20", "2023-12-21"}, "9am-5pm");
            addSampleDoctor(manager, "D302", "Dr. Fatimah", "Orthopedics", "+60678901234", 7, 130.00, new String[]{}, "11am-7pm");
            addSampleDoctor(manager, "D303", "Dr. Wong", "Orthopedics", "+60555666777", 18, 220.00, new String[]{"2024-01-10"}, "8am-4pm");
            addSampleDoctor(manager, "D304", "Dr. Sofia", "Orthopedics", "+60666777888", 9, 170.00, new String[]{}, "2pm-10pm");

            // Neurology  
            addSampleDoctor(manager, "D401", "Dr. Lim", "Neurology", "+60789012345", 18, 250.00, new String[]{"2024-01-15"}, "8am-4pm");
            addSampleDoctor(manager, "D402", "Dr. Ali", "Neurology", "+60890123456", 10, 180.00, new String[]{}, "10am-6pm");
            addSampleDoctor(manager, "D403", "Dr. Chen", "Neurology", "+60777888999", 22, 280.00, new String[]{"2024-02-05"}, "9am-5pm");
            addSampleDoctor(manager, "D404", "Dr. Priya", "Neurology", "+60888999000", 14, 210.00, new String[]{}, "11am-7pm");

            // Oncology 
            addSampleDoctor(manager, "D501", "Dr. Chen", "Oncology", "+60901234567", 22, 300.00, new String[]{"2023-12-28"}, "9am-5pm");
            addSampleDoctor(manager, "D502", "Dr. Devi", "Oncology", "+60111234567", 6, 160.00, new String[]{}, "2pm-10pm");
            addSampleDoctor(manager, "D503", "Dr. Ahmad", "Oncology", "+60999887766", 25, 320.00, new String[]{"2024-01-20"}, "8am-4pm");
            addSampleDoctor(manager, "D504", "Dr. Anis", "Oncology", "+60188776655", 8, 190.00, new String[]{}, "10am-6pm");

            // General Surgery 
            addSampleDoctor(manager, "D601", "Dr. Wong", "General Surgery", "+60122345678", 14, 170.00, new String[]{"2024-01-10"}, "8am-4pm");
            addSampleDoctor(manager, "D602", "Dr. Anwar", "General Surgery", "+60133456789", 9, 140.00, new String[]{}, "10am-6pm");
            addSampleDoctor(manager, "D603", "Dr. Kamal", "General Surgery", "+60144556677", 19, 200.00, new String[]{"2024-02-15"}, "9am-5pm");
            addSampleDoctor(manager, "D604", "Dr. Nina", "General Surgery", "+60155667788", 11, 160.00, new String[]{}, "11am-7pm");

            // Dermatology 
            addSampleDoctor(manager, "D701", "Dr. Tan", "Dermatology", "+60144567890", 11, 110.00, new String[]{"2023-12-15"}, "9am-5pm");
            addSampleDoctor(manager, "D702", "Dr. Kamal", "Dermatology", "+60155678901", 4, 90.00, new String[]{}, "11am-7pm");
            addSampleDoctor(manager, "D703", "Dr. Lee", "Dermatology", "+60166778899", 16, 130.00, new String[]{"2024-01-25"}, "8am-4pm");
            addSampleDoctor(manager, "D704", "Dr. Sara", "Dermatology", "+60177889900", 7, 100.00, new String[]{}, "10am-6pm");

            // Ophthalmology 
            addSampleDoctor(manager, "D801", "Dr. Lee", "Ophthalmology", "+60166789012", 17, 190.00, new String[]{"2024-01-05"}, "8am-4pm");
            addSampleDoctor(manager, "D802", "Dr. Saras", "Ophthalmology", "+60177890123", 8, 125.00, new String[]{}, "10am-6pm");
            addSampleDoctor(manager, "D803", "Dr. Raj", "Ophthalmology", "+60188992233", 20, 210.00, new String[]{"2024-02-20"}, "9am-5pm");
            addSampleDoctor(manager, "D804", "Dr. Maya", "Ophthalmology", "+60199003344", 12, 170.00, new String[]{}, "11am-7pm");

            // Emergency Medicine 
            addSampleDoctor(manager, "D901", "Dr. John", "Emergency Medicine", "+60188901234", 13, 160.00, new String[]{}, "24-hour shifts");
            addSampleDoctor(manager, "D902", "Dr. Aisha", "Emergency Medicine", "+60199012345", 5, 110.00, new String[]{"2023-12-24"}, "24-hour shifts");
            addSampleDoctor(manager, "D903", "Dr. Sam", "Emergency Medicine", "+60100112233", 18, 190.00, new String[]{"2024-01-31"}, "24-hour shifts");
            addSampleDoctor(manager, "D904", "Dr. Lina", "Emergency Medicine", "+60111223344", 9, 140.00, new String[]{}, "24-hour shifts");

            // Psychiatry 
            addSampleDoctor(manager, "DC01", "Dr. Richard", "Psychiatry", "+60109123456", 19, 220.00, new String[]{"2024-01-20"}, "9am-5pm");
            addSampleDoctor(manager, "DC02", "Dr. Nor", "Psychiatry", "+60119234567", 7, 150.00, new String[]{}, "2pm-10pm");
            addSampleDoctor(manager, "DC03", "Dr. David", "Psychiatry", "+60109334455", 22, 240.00, new String[]{"2024-02-10"}, "8am-4pm");
            addSampleDoctor(manager, "DC04", "Dr. Zara", "Psychiatry", "+60119445566", 10, 180.00, new String[]{}, "10am-6pm");    
            
            
        } catch (Exception e) {
            System.out.println("Error loading sample data: " + e.getMessage());
        }
    }

  
     
    private static void addSampleDoctor(DoctorManager manager, String id, String name, 
            String specialization, String contact, int experience, double fee, 
            String[] leaveDates, String hours) {
        
        Doctor doctor = new Doctor(id, name, specialization);
        doctor.setContactNumber(contact);
        doctor.setYearsOfExperience(experience);
        doctor.setConsultationFee(fee);
        doctor.setLeaveDates(leaveDates);
        doctor.setWorkingHours(hours);
        doctor.setAvailable(leaveDates.length == 0);
        doctor.setOnLeave(leaveDates.length > 0);
        
        manager.addDoctor(doctor);
    }
    
    
    public static void initializeSamplePharmacyStock(PharmacyControl pharmacyControl) {
        try {
             // --- Pain Relievers & Anti-Inflammatories ---
            addSampleMedication(pharmacyControl, "M001", "Paracetamol 500mg", "Pain and fever relief", 10.50, 250, "Tablet");
            addSampleMedication(pharmacyControl, "M002", "Ibuprofen 200mg", "Anti-inflammatory", 15.00, 180, "Tablet");
            addSampleMedication(pharmacyControl, "M003", "Aspirin 100mg", "Blood thinner, pain relief", 8.00, 90, "Tablet");
            addSampleMedication(pharmacyControl, "M004", "Naproxen 220mg", "Long-lasting pain relief", 22.75, 60, "Tablet");
            addSampleMedication(pharmacyControl, "M005", "Diclofenac Gel", "Topical pain relief", 35.50, 45, "Cream");
            addSampleMedication(pharmacyControl, "M006", "Celecoxib 200mg", "Prescription anti-inflammatory", 55.00, 25, "Capsule");
            addSampleMedication(pharmacyControl, "M007", "Tramadol 50mg", "Strong pain relief", 48.20, 30, "Capsule");
            addSampleMedication(pharmacyControl, "M008", "Mefenamic Acid", "Menstrual pain relief", 19.80, 75, "Tablet");
            
            // --- Antibiotics ---
            addSampleMedication(pharmacyControl, "M009", "Amoxicillin 500mg", "Broad-spectrum antibiotic", 35.20, 120, "Capsule");
            addSampleMedication(pharmacyControl, "M010", "Doxycycline 100mg", "Tetracycline antibiotic", 42.00, 80, "Capsule");
            addSampleMedication(pharmacyControl, "M011", "Azithromycin 250mg", "Macrolide antibiotic", 65.00, 50, "Tablet");
            addSampleMedication(pharmacyControl, "M012", "Ciprofloxacin 500mg", "Fluoroquinolone antibiotic", 58.50, 40, "Tablet");
            addSampleMedication(pharmacyControl, "M013", "Cephalexin 250mg", "Cephalosporin antibiotic", 40.10, 65, "Capsule");
            addSampleMedication(pharmacyControl, "M014", "Metronidazole 400mg", "Anaerobic bacteria antibiotic", 33.00, 55, "Tablet");

            // --- Allergy, Cold & Flu ---
            addSampleMedication(pharmacyControl, "M015", "Loratadine 10mg", "Non-drowsy antihistamine", 18.50, 150, "Tablet");
            addSampleMedication(pharmacyControl, "M016", "Cetirizine 10mg", "Antihistamine for allergies", 15.00, 200, "Tablet");
            addSampleMedication(pharmacyControl, "M017", "Guaifenesin Syrup", "Expectorant for cough", 25.00, 80, "Liquid");
            addSampleMedication(pharmacyControl, "M018", "Dextromethorphan", "Cough suppressant", 28.90, 70, "Liquid");
            addSampleMedication(pharmacyControl, "M019", "Pseudoephedrine", "Nasal decongestant", 21.40, 110, "Tablet");
            addSampleMedication(pharmacyControl, "M020", "Fluticasone Spray", "Nasal steroid for allergies", 45.00, 35, "Spray");
            addSampleMedication(pharmacyControl, "M021", "Chlorpheniramine", "Antihistamine for cold", 9.50, 300, "Tablet");
            addSampleMedication(pharmacyControl, "M022", "Oseltamivir 75mg", "Antiviral for influenza", 110.00, 15, "Capsule");
            
            // --- Stomach & Digestive Health ---
            addSampleMedication(pharmacyControl, "M023", "Antacid Liquid", "Relieves heartburn", 12.75, 120, "Liquid");
            addSampleMedication(pharmacyControl, "M024", "Omeprazole 20mg", "Proton-pump inhibitor", 38.00, 90, "Capsule");
            addSampleMedication(pharmacyControl, "M025", "Loperamide 2mg", "Anti-diarrheal", 14.20, 130, "Tablet");
            addSampleMedication(pharmacyControl, "M026", "Bismuth Subsalicylate", "Upset stomach relief", 24.50, 60, "Liquid");
            addSampleMedication(pharmacyControl, "M027", "Simethicone 125mg", "Anti-gas relief", 17.80, 100, "Tablet");
            addSampleMedication(pharmacyControl, "M028", "Hyoscine Butylbromide", "Stomach cramp relief", 29.00, 50, "Tablet");
            addSampleMedication(pharmacyControl, "M029", "Domperidone 10mg", "Anti-nausea", 31.50, 40, "Tablet");
            addSampleMedication(pharmacyControl, "M030", "Ranitidine 150mg", "Acid reducer", 25.50, 20, "Tablet"); // Low stock example

            // --- Vitamins & Supplements ---
            addSampleMedication(pharmacyControl, "M031", "Vitamin C 1000mg", "Immune support", 18.00, 220, "Tablet");
            addSampleMedication(pharmacyControl, "M032", "Vitamin D3 1000IU", "Bone health", 25.00, 150, "Tablet");
            addSampleMedication(pharmacyControl, "M033", "Multivitamin", "General wellness", 45.00, 100, "Tablet");
            addSampleMedication(pharmacyControl, "M034", "Iron Supplement", "For anemia", 22.30, 80, "Tablet");
            addSampleMedication(pharmacyControl, "M035", "Calcium + Magnesium", "Bone and muscle support", 33.80, 95, "Tablet");
            addSampleMedication(pharmacyControl, "M036", "Fish Oil Omega-3", "Heart and brain health", 55.00, 60, "Capsule");
            addSampleMedication(pharmacyControl, "M037", "Folic Acid 400mcg", "Prenatal supplement", 19.90, 110, "Tablet");
            addSampleMedication(pharmacyControl, "M038", "Probiotic Capsules", "Digestive health", 68.00, 40, "Capsule");
            
            // --- Topical (Skin) ---
            addSampleMedication(pharmacyControl, "M039", "Hydrocortisone Cream", "Anti-itch cream", 16.50, 85, "Cream");
            addSampleMedication(pharmacyControl, "M040", "Clotrimazole Cream", "Antifungal cream", 21.00, 70, "Cream");
            addSampleMedication(pharmacyControl, "M041", "Mupirocin Ointment", "Topical antibiotic", 39.50, 30, "Ointment");
            addSampleMedication(pharmacyControl, "M042", "Bacitracin Ointment", "First aid antibiotic", 14.00, 140, "Ointment");
            addSampleMedication(pharmacyControl, "M043", "Calamine Lotion", "Skin soothing lotion", 11.20, 90, "Lotion");
            addSampleMedication(pharmacyControl, "M044", "Salicylic Acid Wash", "Acne treatment", 28.00, 50, "Liquid");
            
            // --- Cardiovascular ---
            addSampleMedication(pharmacyControl, "M045", "Amlodipine 5mg", "Blood pressure control", 24.00, 180, "Tablet");
            addSampleMedication(pharmacyControl, "M046", "Atorvastatin 20mg", "Cholesterol control", 75.00, 100, "Tablet");
            addSampleMedication(pharmacyControl, "M047", "Metoprolol 25mg", "Beta-blocker", 32.50, 120, "Tablet");
            addSampleMedication(pharmacyControl, "M048", "Lisinopril 10mg", "ACE inhibitor", 28.00, 150, "Tablet");
            addSampleMedication(pharmacyControl, "M049", "Furosemide 40mg", "Diuretic (water pill)", 15.60, 90, "Tablet");
            addSampleMedication(pharmacyControl, "M050", "Warfarin 5mg", "Anticoagulant", 41.00, 40, "Tablet");

            // --- Diabetes ---
            addSampleMedication(pharmacyControl, "M051", "Metformin 500mg", "Type 2 diabetes", 19.00, 300, "Tablet");
            addSampleMedication(pharmacyControl, "M052", "Gliclazide 80mg", "Type 2 diabetes", 27.50, 120, "Tablet");
            addSampleMedication(pharmacyControl, "M053", "Sitagliptin 100mg", "DPP-4 inhibitor", 150.00, 30, "Tablet");
            addSampleMedication(pharmacyControl, "M054", "Insulin Glargine", "Long-acting insulin", 120.00, 20, "Injection");
            addSampleMedication(pharmacyControl, "M055", "Insulin Aspart", "Rapid-acting insulin", 95.00, 25, "Injection");

            // --- Mental Health ---
            addSampleMedication(pharmacyControl, "M056", "Sertraline 50mg", "Antidepressant (SSRI)", 60.00, 70, "Tablet");
            addSampleMedication(pharmacyControl, "M057", "Escitalopram 10mg", "Antidepressant (SSRI)", 72.00, 60, "Tablet");
            addSampleMedication(pharmacyControl, "M058", "Alprazolam 0.5mg", "Anti-anxiety", 45.00, 45, "Tablet");
            addSampleMedication(pharmacyControl, "M059", "Lorazepam 1mg", "Anti-anxiety", 38.50, 55, "Tablet");
            
            // --- Miscellaneous ---
            addSampleMedication(pharmacyControl, "M060", "Levothyroxine 50mcg", "Thyroid hormone", 22.00, 100, "Tablet");
            addSampleMedication(pharmacyControl, "M061", "Salbutamol Inhaler", "Asthma relief", 34.00, 80, "Inhaler");
            addSampleMedication(pharmacyControl, "M062", "Prednisolone 5mg", "Corticosteroid", 26.80, 90, "Tablet");
            addSampleMedication(pharmacyControl, "M063", "Allopurinol 100mg", "Gout treatment", 18.90, 75, "Tablet");
            addSampleMedication(pharmacyControl, "M064", "Tamsulosin 0.4mg", "Prostate medication", 58.00, 40, "Capsule");
            addSampleMedication(pharmacyControl, "M065", "Artificial Tears", "Dry eye relief", 19.50, 120, "Eye Drop");
            addSampleMedication(pharmacyControl, "M066", "Ketotifen Eye Drops", "Allergy eye relief", 29.00, 50, "Eye Drop");
            addSampleMedication(pharmacyControl, "M067", "Oral Rehydration Salt", "Dehydration treatment", 5.50, 400, "Powder");
            addSampleMedication(pharmacyControl, "M068", "Smokers Lozenge", "Nicotine replacement", 42.00, 30, "Lozenge");
            
            // --- Filling up to 100 ---
            addSampleMedication(pharmacyControl, "M069", "Bisacodyl 5mg", "Laxative", 12.00, 150, "Tablet");
            addSampleMedication(pharmacyControl, "M070", "Dimenhydrinate 50mg", "Motion sickness", 16.00, 90, "Tablet");
            addSampleMedication(pharmacyControl, "M071", "Zinc Oxide Ointment", "Diaper rash, skin protectant", 20.00, 60, "Ointment");
            addSampleMedication(pharmacyControl, "M072", "Glucosamine Sulfate", "Joint health supplement", 75.00, 50, "Tablet");
            addSampleMedication(pharmacyControl, "M073", "Melatonin 3mg", "Sleep aid", 35.00, 80, "Tablet");
            addSampleMedication(pharmacyControl, "M074", "Permethrin Cream", "Scabies treatment", 48.00, 20, "Cream");
            addSampleMedication(pharmacyControl, "M075", "Clindamycin 150mg", "Lincosamide antibiotic", 52.00, 50, "Capsule");
            addSampleMedication(pharmacyControl, "M076", "Erythromycin 250mg", "Macrolide antibiotic", 44.00, 60, "Tablet");
            addSampleMedication(pharmacyControl, "M077", "Famotidine 20mg", "Acid reducer (H2 blocker)", 23.00, 110, "Tablet");
            addSampleMedication(pharmacyControl, "M078", "Lactulose Solution", "Constipation relief", 31.00, 40, "Liquid");
            addSampleMedication(pharmacyControl, "M079", "Paracetamol 120mg/5ml", "Children's fever relief", 15.50, 100, "Liquid");
            addSampleMedication(pharmacyControl, "M080", "Ibuprofen 100mg/5ml", "Children's pain relief", 19.50, 90, "Liquid");
            addSampleMedication(pharmacyControl, "M081", "Betamethasone Cream", "Topical steroid", 33.00, 45, "Cream");
            addSampleMedication(pharmacyControl, "M082", "Terbinafine Cream", "Athlete's foot treatment", 27.00, 55, "Cream");
            addSampleMedication(pharmacyControl, "M083", "Clobetasol Ointment", "Potent topical steroid", 50.00, 25, "Ointment");
            addSampleMedication(pharmacyControl, "M084", "Diazepam 5mg", "Anxiety, muscle relaxant", 39.00, 30, "Tablet");
            addSampleMedication(pharmacyControl, "M085", "Citalopram 20mg", "Antidepressant (SSRI)", 55.00, 65, "Tablet");
            addSampleMedication(pharmacyControl, "M086", "Olanzapine 5mg", "Antipsychotic", 125.00, 20, "Tablet");
            addSampleMedication(pharmacyControl, "M087", "Rosuvastatin 10mg", "Cholesterol control", 85.00, 90, "Tablet");
            addSampleMedication(pharmacyControl, "M088", "Telmisartan 40mg", "Blood pressure (ARB)", 65.00, 100, "Tablet");
            addSampleMedication(pharmacyControl, "M089", "Hydrochlorothiazide", "Diuretic", 12.50, 130, "Tablet");
            addSampleMedication(pharmacyControl, "M090", "Spironolactone 25mg", "Diuretic", 21.50, 70, "Tablet");
            addSampleMedication(pharmacyControl, "M091", "Gabapentin 300mg", "Nerve pain, anticonvulsant", 78.00, 50, "Capsule");
            addSampleMedication(pharmacyControl, "M092", "Pregabalin 75mg", "Nerve pain", 95.00, 40, "Capsule");
            addSampleMedication(pharmacyControl, "M093", "Montelukast 10mg", "Asthma, allergies", 68.00, 60, "Tablet");
            addSampleMedication(pharmacyControl, "M094", "Budesonide Inhaler", "Asthma preventer", 88.00, 30, "Inhaler");
            addSampleMedication(pharmacyControl, "M095", "Finasteride 5mg", "Prostate medication", 72.00, 25, "Tablet");
            addSampleMedication(pharmacyControl, "M096", "Sildenafil 50mg", "Erectile dysfunction", 99.00, 15, "Tablet");
            addSampleMedication(pharmacyControl, "M097", "Esomeprazole 40mg", "Proton-pump inhibitor", 82.00, 50, "Capsule");
            addSampleMedication(pharmacyControl, "M098", "Pantoprazole 40mg", "Proton-pump inhibitor", 77.00, 55, "Tablet");
            addSampleMedication(pharmacyControl, "M099", "Ondansetron 4mg", "Anti-nausea", 51.00, 35, "Tablet");
            addSampleMedication(pharmacyControl, "M100", "Methylphenidate 10mg", "ADHD treatment", 130.00, 10, "Tablet");
            
        } catch (Exception e) {
            System.out.println("Error loading sample pharmacy data: " + e.getMessage());
        }
    }
    
    private static void addSampleMedication(PharmacyControl pharmacyControl, String id, String name, 
            String description, double price, int quantity, String type) {

        // Here we create the entity object within the DAO layer
        Pharmacy medication = new Pharmacy(id, name, description, price, quantity, type, new Date());
        
        // And pass the complete object to the control layer's new method
        pharmacyControl.addMedication(medication);
    }
    
    public static void initializeSampleDiagnoses(MedicalTreatmentControl treatmentControl) {
        try {
            treatmentControl.addDiagnosisTemplate("DIAG01", "Common Flu", "Viral infection of the upper respiratory tract. Recommend rest and hydration.");
            treatmentControl.addDiagnosisTemplate("DIAG02", "Strep Throat", "Bacterial infection causing a severe sore throat. Requires antibiotics.");
            treatmentControl.addDiagnosisTemplate("DIAG03", "Minor Sprain", "Stretching or tearing of ligaments. Apply RICE method (Rest, Ice, Compression, Elevation).");
            treatmentControl.addDiagnosisTemplate("DIAG04", "Tension Headache", "Mild to moderate pain in the head, often described as a tight band. Suggest pain relievers.");
            treatmentControl.addDiagnosisTemplate("DIAG05", "Migraine", "Severe, recurring headache, often accompanied by nausea and light sensitivity.");
            treatmentControl.addDiagnosisTemplate("DIAG06", "Indigestion", "Discomfort in the upper abdomen, often after eating. Recommend antacids.");
        } catch (Exception e) {
            System.out.println("Error loading sample diagnoses: " + e.getMessage());
        }
    }
    
}