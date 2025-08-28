package dao;


import control.*;
import entity.*;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;
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
            
            // === EXTRA DUTY SHIFTS (Doctor + Schedule + Consultation) ===
                manager.addDutyShift("D101", "08:00-16:00");
                manager.addDutyShift("D102", "12:00-20:00");
                manager.addDutyShift("D103", "07:00-15:00");
                manager.addDutyShift("D104", "14:00-22:00");
                
                manager.addDutyShift("D201", "08:30-16:30");
                manager.addDutyShift("D202", "10:00-18:00");
                manager.addDutyShift("D203", "09:00-17:00");
                manager.addDutyShift("D204", "16:00-00:00");
                
                manager.addDutyShift("D301", "07:30-15:30");
                manager.addDutyShift("D302", "13:00-21:00");
                manager.addDutyShift("D303", "06:00-14:00");
                manager.addDutyShift("D304", "15:00-23:00");
                
                manager.addDutyShift("D401", "08:00-16:00");
                manager.addDutyShift("D402", "12:00-20:00");
                manager.addDutyShift("D403", "09:00-17:00");
                manager.addDutyShift("D404", "14:00-22:00");
                
                manager.addDutyShift("D501", "07:00-15:00");
                manager.addDutyShift("D502", "10:00-18:00");
                manager.addDutyShift("D503", "08:00-16:00");
                manager.addDutyShift("D504", "15:00-23:00");
                
                manager.addDutyShift("D601", "08:30-16:30");
                manager.addDutyShift("D602", "12:00-20:00");
                manager.addDutyShift("D603", "09:00-17:00");
                manager.addDutyShift("D604", "16:00-00:00");
                
                manager.addDutyShift("D701", "07:00-15:00");
                manager.addDutyShift("D702", "10:00-18:00");
                manager.addDutyShift("D703", "08:00-16:00");
                manager.addDutyShift("D704", "15:00-23:00");
                
                manager.addDutyShift("D801", "08:30-16:30");
                manager.addDutyShift("D802", "12:00-20:00");
                manager.addDutyShift("D803", "09:00-17:00");
                manager.addDutyShift("D804", "14:00-22:00");
                
                manager.addDutyShift("D901", "06:00-14:00");
                manager.addDutyShift("D902", "14:00-22:00");
                manager.addDutyShift("D903", "22:00-06:00");
                manager.addDutyShift("D904", "08:00-16:00");
                
                manager.addDutyShift("DC01", "08:00-16:00");
                manager.addDutyShift("DC02", "16:00-00:00");
                manager.addDutyShift("DC03", "08:00-16:00");
                manager.addDutyShift("DC04", "12:00-20:00");
                
        } catch (Exception e) {
            System.out.println("Error loading sample data: " + e.getMessage());
        }
    }

  
     
    private static void addSampleDoctor(DoctorManager manager, String id, String name, 
            String specialization, String contact, int experience, double fee, 
            String[] leaveDates, String hours) {
        
        Doctor doctor = new Doctor(id, name, specialization, contact, experience, fee, 
                leaveDates.length == 0, leaveDates.length > 0);
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
    
<<<<<<< HEAD
    public static void initializeSamplePatients(PatientManager patientManager) {
    try {
        // ================= PATIENTS IN QUEUE (10 patients) =================
        addHardcodedQueuedPatient(patientManager, "Ryan Julian Rajesh", "990101014321", "0123456789", "01/01/1999", "M", "A+", "Peanuts", 200.5, 170.0, 
=======
       public static void initializeSamplePatients(PatientManager patientManager) {
    try {
        // ================= PATIENTS IN QUEUE (10 patients) =================
        addHardcodedQueuedPatient(patientManager, "Ali Bin Abu", "990101014321", "0123456789", "01/01/1999", "M", "A+", "Peanuts", 70.5, 170.0, 
>>>>>>> 93ed186f0bb04ef2f00033157df5552c8a2bb78e
                                "P001", "Q001", "01/01/2023");
        addHardcodedQueuedPatient(patientManager, "Siti Aminah", "000202023456", "0139876543", "02/02/2000", "F", "O-", "", 55.0, 160.0, 
                                "P002", "Q002", "02/01/2025");
        addHardcodedQueuedPatient(patientManager, "John Tan", "981010104321", "0112233445", "10/10/1998", "M", "B+", "Seafood", 68.0, 175.0, 
                                "P003", "Q003", "03/01/2025");
        addHardcodedQueuedPatient(patientManager, "Nur Izzati", "010303035432", "0199988776", "03/03/2001", "F", "AB-", "", 60.0, 162.0, 
                                "P004", "Q004", "04/01/2025");
        addHardcodedQueuedPatient(patientManager, "Raj Kumar", "970707074321", "0105678901", "07/07/1997", "M", "O+", "Dust", 72.3, 180.0, 
                                "P005", "Q005", "05/01/2025");
        addHardcodedQueuedPatient(patientManager, "Lim Mei Ling", "960606062222", "0143322110", "06/06/1996", "F", "A-", "Lactose", 50.0, 155.0, 
                                "P006", "Q006", "06/01/2025");
        addHardcodedQueuedPatient(patientManager, "Ahmad Fauzi", "950505051234", "0176543210", "05/05/1995", "M", "B-", "Penicillin", 80.0, 178.0, 
                                "P007", "Q007", "07/01/2025");
        addHardcodedQueuedPatient(patientManager, "Sarah Chong", "940404043210", "0167890123", "04/04/1994", "F", "AB+", "", 58.0, 165.0, 
                                "P008", "Q008", "08/01/2025");
        addHardcodedQueuedPatient(patientManager, "Mohd Razak", "930303032345", "0156789012", "03/03/1993", "M", "A+", "Shellfish", 75.0, 172.0, 
                                "P009", "Q009", "09/01/2025");
        addHardcodedQueuedPatient(patientManager, "Tan Wei Ling", "920202021234", "0198765432", "02/02/1992", "F", "O+", "Nuts", 52.0, 158.0, 
                                "P010", "Q010", "10/01/2025");

        // ================= PATIENTS NOT IN QUEUE (40 patients) =================
        addHardcodedNonQueuedPatient(patientManager, "Kumar Selvam", "910101013456", "0134567890", "01/01/1991", "M", "B+", "", 82.0, 182.0, 
                                   "P011", "11/01/2025");
        addHardcodedNonQueuedPatient(patientManager, "Fatimah Binti Abdullah", "891212123456", "0123456780", "12/12/1989", "F", "O-", "Eggs", 65.0, 168.0, 
                                   "P012", "12/01/2025");
        addHardcodedNonQueuedPatient(patientManager, "Lee Chong Wei", "881010102345", "0112345678", "10/10/1988", "M", "A-", "", 70.0, 175.0, 
                                   "P013", "13/01/2025");
        addHardcodedNonQueuedPatient(patientManager, "Nurul Syafiqah", "870707073210", "0190123456", "07/07/1987", "F", "AB-", "Soy", 54.0, 160.0, 
                                   "P014", "14/01/2025");
        addHardcodedNonQueuedPatient(patientManager, "Ramesh Naidu", "860606062345", "0178901234", "06/06/1986", "M", "O+", "", 78.0, 180.0, 
                                   "P015", "15/01/2025");
        addHardcodedNonQueuedPatient(patientManager, "Wong Mei Chen", "850505051234", "0167890123", "05/05/1985", "F", "B+", "Dairy", 56.0, 163.0, 
                                   "P016", "16/01/2025");
        addHardcodedNonQueuedPatient(patientManager, "Ahmad Hakimi", "840404043456", "0156789012", "04/04/1984", "M", "A+", "", 85.0, 185.0, 
                                   "P017", "17/01/2025");
        addHardcodedNonQueuedPatient(patientManager, "Norhayati Binti Osman", "830303032345", "0145678901", "03/03/1983", "F", "O-", "Wheat", 62.0, 170.0, 
                                   "P018", "18/01/2025");
        addHardcodedNonQueuedPatient(patientManager, "Chan Kok Leong", "820202021234", "0134567890", "02/02/1982", "M", "AB+", "", 76.0, 178.0, 
                                   "P019", "19/01/2025");
        addHardcodedNonQueuedPatient(patientManager, "Siti Nurhaliza", "810101013456", "0123456789", "01/01/1981", "F", "B-", "Peanuts", 60.0, 165.0, 
                                   "P020", "20/01/2025");
        
        addHardcodedNonQueuedPatient(patientManager, "Muthu Samy", "791212123456", "0198765432", "12/12/1979", "M", "O+", "", 90.0, 182.0, 
                                   "P021", "21/01/2025");
        addHardcodedNonQueuedPatient(patientManager, "Lim Siew Ling", "781010102345", "0187654321", "10/10/1978", "F", "A+", "Shellfish", 58.0, 162.0, 
                                   "P022", "22/01/2025");
        addHardcodedNonQueuedPatient(patientManager, "Abdul Rahman", "770707073210", "0176543210", "07/07/1977", "M", "B+", "", 82.0, 178.0, 
                                   "P023", "23/01/2025");
        addHardcodedNonQueuedPatient(patientManager, "Tan Bee Lian", "760606062345", "0165432109", "06/06/1976", "F", "AB-", "Latex", 63.0, 168.0, 
                                   "P024", "24/01/2025");
        addHardcodedNonQueuedPatient(patientManager, "Krishnan A/L Muthu", "750505051234", "0154321098", "05/05/1975", "M", "O-", "Eggs", 88.0, 183.0, 
                                   "P025", "25/01/2025");
        addHardcodedNonQueuedPatient(patientManager, "Ong Swee Lin", "740404043456", "0143210987", "04/04/1974", "F", "A-", "", 65.0, 170.0, 
                                   "P026", "26/01/2025");
        addHardcodedNonQueuedPatient(patientManager, "Mohd Faisal", "730303032345", "0132109876", "03/03/1973", "M", "B-", "Penicillin", 92.0, 185.0, 
                                   "P027", "27/01/2025");
        addHardcodedNonQueuedPatient(patientManager, "Yusuf Bin Ismail", "720202021234", "0121098765", "02/02/1972", "M", "AB+", "", 84.0, 180.0, 
                                   "P028", "28/01/2025");
        addHardcodedNonQueuedPatient(patientManager, "Noraini Binti Ali", "710101013456", "0110987654", "01/01/1971", "F", "O+", "Nuts", 70.0, 175.0, 
                                   "P029", "29/01/2025");
        addHardcodedNonQueuedPatient(patientManager, "Robert Chan", "691212123456", "0198765432", "12/12/1969", "M", "A+", "", 95.0, 188.0, 
                                   "P030", "30/01/2025");
        
        addHardcodedNonQueuedPatient(patientManager, "Maimunah Binti Ahmad", "681010102345", "0187654321", "10/10/1968", "F", "B+", "Dairy", 68.0, 172.0, 
                                   "P031", "01/02/2025");
        addHardcodedNonQueuedPatient(patientManager, "Ravi Shankar", "670707073210", "0176543210", "07/07/1967", "M", "O-", "", 87.0, 182.0, 
                                   "P032", "02/02/2025");
        addHardcodedNonQueuedPatient(patientManager, "Lily Wong", "660606062345", "0165432109", "06/06/1966", "F", "AB+", "Wheat", 72.0, 178.0, 
                                   "P033", "03/02/2025");
        addHardcodedNonQueuedPatient(patientManager, "Hassan Bin Omar", "650505051234", "0154321098", "05/05/1965", "M", "A-", "", 90.0, 185.0, 
                                   "P034", "04/02/2025");
        addHardcodedNonQueuedPatient(patientManager, "Susila Devi", "640404043456", "0143210987", "04/04/1964", "F", "B-", "Peanuts", 75.0, 180.0, 
                                   "P035", "05/02/2025");
        addHardcodedNonQueuedPatient(patientManager, "Goh Peng Lim", "630303032345", "0132109876", "03/03/1963", "M", "O+", "Shellfish", 92.0, 188.0, 
                                   "P036", "06/02/2025");
        addHardcodedNonQueuedPatient(patientManager, "Zainab Binti Yusof", "620202021234", "0121098765", "02/02/1962", "F", "AB-", "", 78.0, 175.0, 
                                   "P037", "07/02/2025");
        addHardcodedNonQueuedPatient(patientManager, "Arunachalam Muthu", "610101013456", "0110987654", "01/01/1961", "M", "A+", "Latex", 85.0, 182.0, 
                                   "P038", "08/02/2025");
        addHardcodedNonQueuedPatient(patientManager, "Lim Siew Hong", "591212123456", "0198765432", "12/12/1959", "F", "B+", "Eggs", 80.0, 178.0, 
                                   "P039", "09/02/2025");
        addHardcodedNonQueuedPatient(patientManager, "Ismail Bin Ahmad", "581010102345", "0187654321", "10/10/1958", "M", "O-", "", 95.0, 185.0, 
                                   "P040", "10/02/2025");
        
        addHardcodedNonQueuedPatient(patientManager, "Chong Mei Fong", "570707073210", "0176543210", "07/07/1957", "F", "AB+", "", 82.0, 180.0, 
                                   "P041", "11/02/2025");
        addHardcodedNonQueuedPatient(patientManager, "Muthu Palanisamy", "560606062345", "0165432109", "06/06/1956", "M", "A-", "Penicillin", 98.0, 188.0, 
                                   "P042", "12/02/2025");
        addHardcodedNonQueuedPatient(patientManager, "Aishah Binti Mohd", "550505051234", "0154321098", "05/05/1955", "F", "B-", "Nuts", 85.0, 175.0, 
                                   "P043", "13/02/2025");
        addHardcodedNonQueuedPatient(patientManager, "Tan Kok Wai", "540404043456", "0143210987", "04/04/1954", "M", "O+", "", 100.0, 185.0, 
                                   "P044", "14/02/2025");
        addHardcodedNonQueuedPatient(patientManager, "Norhayati Binti Ali", "530303032345", "0132109876", "03/03/1953", "F", "AB-", "Dairy", 88.0, 178.0, 
                                   "P045", "15/02/2025");
        addHardcodedNonQueuedPatient(patientManager, "Raja Kumar", "520202021234", "0121098765", "02/02/1952", "M", "A+", "", 92.0, 182.0, 
                                   "P046", "16/02/2025");
        addHardcodedNonQueuedPatient(patientManager, "Lim Siew Chin", "510101013456", "0110987654", "01/01/1951", "F", "B+", "Wheat", 78.0, 175.0, 
                                   "P047", "17/02/2025");
        addHardcodedNonQueuedPatient(patientManager, "Ahmad Bin Hassan", "491212123456", "0198765432", "12/12/1949", "M", "O-", "Peanuts", 102.0, 188.0, 
                                   "P048", "18/02/2025");
        addHardcodedNonQueuedPatient(patientManager, "Wong Mei Yee", "481010102345", "0187654321", "10/10/1948", "F", "AB+", "", 85.0, 180.0, 
                                   "P049", "19/02/2025");
        addHardcodedNonQueuedPatient(patientManager, "Muthu Samynathan", "470707073210", "0176543210", "07/07/1947", "M", "A-", "Shellfish", 95.0, 185.0, 
                                   "P050", "20/02/2025");

        // Set counters to continue from where we left off
        patientManager.setPatientCounter(51); // Next ID would be P051
        patientManager.setQueueCounter(11);   // Next queue would be Q011
        
    } catch (Exception e) {
        System.out.println("Error loading sample patients: " + e.getMessage());
    }
}

    
    private static void addHardcodedQueuedPatient(PatientManager manager, String name, String ic, 
        String contact, String dob, String gender, String bloodType, 
        String allergies, double weight, double height,
        String patientId, String queueId, String regDate) {
    
    Patient patient = new Patient(
        patientId, name, ic, contact, dob, gender, 
        bloodType, allergies, weight, height, 
        queueId, regDate
    );
    
    manager.addSamplePatient(patient, true);
}
    
    private static void addHardcodedNonQueuedPatient(PatientManager manager, String name, String ic, 
        String contact, String dob, String gender, String bloodType, 
        String allergies, double weight, double height,
        String patientId, String regDate) {
    
    Patient patient = new Patient(
        patientId, name, ic, contact, dob, gender, 
        bloodType, allergies, weight, height, 
        null, regDate  // null queueId for non-queued patients
    );
    
    manager.addSamplePatient(patient, false);


}
    // Main sample consultation initializer
    public static void initializeSampleConsultations(
    ConsultationManager consultationManager,
    DoctorManager doctorManager,
    PatientManager patientManager) {

    try {
<<<<<<< HEAD
=======
        
>>>>>>> 93ed186f0bb04ef2f00033157df5552c8a2bb78e

        // ===== 2. ADD CONSULTATIONS =====
        // Cardiology Consultations (D101-D104)
        addSampleConsultation(consultationManager,
            "C001", "D103", "P001", LocalDate.of(2025, 3, 15), LocalTime.of(10, 0),
            "Completed", "Chest pain evaluation", false, "Prescribed medication for hypertension"
        );
        addSampleConsultation(consultationManager,
            "C002", "D103", "P005", LocalDate.of(2025, 3, 16), LocalTime.of(11, 0),
            "Completed", "Heart palpitations", false, "Recommended ECG and follow-up"
        );
        addSampleConsultation(consultationManager,
            "C003", "D103", "P002", LocalDate.of(2025, 3, 17), LocalTime.of(14, 0),
            "Completed", "High blood pressure check", false, "Advised lifestyle changes"
        );
        addSampleConsultation(consultationManager,
            "C004", "D103", "P010", LocalDate.of(2025, 3, 18), LocalTime.of(15, 30),
            "Completed", "Shortness of breath", false, "Ordered chest X-ray"
        );
        addSampleConsultation(consultationManager,
            "C005", "D103", "P003", LocalDate.of(2025, 3, 19), LocalTime.of(9, 30),
            "Completed", "Annual cardiac checkup", false, "All parameters normal"
        );
        addSampleConsultation(consultationManager,
            "C006", "D103", "P015", LocalDate.of(2025, 3, 20), LocalTime.of(10, 45),
            "Completed", "Post-surgery follow-up", true, "Recovery progressing well"
        );
        addSampleConsultation(consultationManager,
            "C007", "D103", "P004", LocalDate.of(2025, 3, 21), LocalTime.of(16, 0),
            "Completed", "Irregular heartbeat", false, "Scheduled for Holter monitor"
        );
        addSampleConsultation(consultationManager,
            "C008", "D103", "P020", LocalDate.of(2025, 3, 22), LocalTime.of(14, 30),
            "Completed", "Family history of heart disease", false, "Preventive care initiated"
        );

        // Pediatrics Consultations (D201-D204)
        addSampleConsultation(consultationManager,
            "C009", "D201", "P006", LocalDate.of(2025, 3, 15), LocalTime.of(9, 0),
            "Completed", "Childhood vaccination", false, "Administered MMR vaccine"
        );
        addSampleConsultation(consultationManager,
            "C010", "D201", "P016", LocalDate.of(2025, 3, 16), LocalTime.of(10, 30),
            "Completed", "Fever and cough", false, "Diagnosed with common cold"
        );
        addSampleConsultation(consultationManager,
            "C011", "D201", "P007", LocalDate.of(2025, 3, 17), LocalTime.of(15, 0),
            "Completed", "Developmental assessment", false, "Normal development for age"
        );
        addSampleConsultation(consultationManager,
            "C012", "D201", "P017", LocalDate.of(2025, 3, 18), LocalTime.of(16, 30),
            "Completed", "Ear infection", false, "Prescribed antibiotics"
        );
        addSampleConsultation(consultationManager,
            "C013", "D204", "P008", LocalDate.of(2025, 3, 19), LocalTime.of(11, 0),
            "Completed", "Allergy testing", false, "Identified pollen allergy"
        );
        addSampleConsultation(consultationManager,
            "C014", "D204", "P018", LocalDate.of(2025, 3, 20), LocalTime.of(14, 0),
            "Completed", "Asthma management", false, "Adjusted inhaler dosage"
        );
        addSampleConsultation(consultationManager,
            "C015", "D204", "P009", LocalDate.of(2025, 3, 21), LocalTime.of(10, 0),
            "Completed", "Nutrition counseling", false, "Diet plan provided"
        );
        addSampleConsultation(consultationManager,
            "C016", "D204", "P019", LocalDate.of(2025, 3, 22), LocalTime.of(13, 30),
            "Completed", "Skin rash", false, "Prescribed topical cream"
        );

        // Orthopedics Consultations (D301-D304)
        addSampleConsultation(consultationManager,
            "C017", "D302", "P010", LocalDate.of(2025, 3, 15), LocalTime.of(14, 0),
            "Completed", "Knee pain evaluation", false, "Recommended physiotherapy"
        );
        addSampleConsultation(consultationManager,
            "C018", "D302", "P020", LocalDate.of(2025, 3, 16), LocalTime.of(15, 30),
            "Completed", "Sports injury", false, "Sprained ankle, advised rest"
        );
        addSampleConsultation(consultationManager,
            "C019", "D304", "P011", LocalDate.of(2025, 3, 17), LocalTime.of(11, 0),
            "Completed", "Back pain assessment", false, "Prescribed muscle relaxants"
        );
        addSampleConsultation(consultationManager,
            "C020", "D304", "P021", LocalDate.of(2025, 3, 18), LocalTime.of(16, 0),
            "Completed", "Joint stiffness", false, "Recommended mobility exercises"
        );
        addSampleConsultation(consultationManager,
            "C021", "D304", "P012", LocalDate.of(2025, 3, 19), LocalTime.of(9, 30),
            "Completed", "Fracture follow-up", true, "Healing properly, cast removed"
        );
        addSampleConsultation(consultationManager,
            "C022", "D302", "P022", LocalDate.of(2025, 3, 20), LocalTime.of(14, 30),
            "Completed", "Arthritis management", false, "Medication adjustment"
        );
        addSampleConsultation(consultationManager,
            "C023", "D304", "P013", LocalDate.of(2025, 3, 21), LocalTime.of(10, 30),
            "Completed", "Shoulder pain", false, "Recommended MRI scan"
        );
        addSampleConsultation(consultationManager,
            "C024", "D304", "P023", LocalDate.of(2025, 3, 22), LocalTime.of(15, 0),
            "Completed", "Osteoporosis screening", false, "Bone density test ordered"
        );

        // Neurology Consultations (D401-D404)
        addSampleConsultation(consultationManager,
            "C025", "D402", "P014", LocalDate.of(2025, 3, 15), LocalTime.of(11, 0),
            "Completed", "Chronic headaches", false, "Prescribed preventive medication"
        );
        addSampleConsultation(consultationManager,
            "C026", "D402", "P024", LocalDate.of(2025, 3, 16), LocalTime.of(14, 30),
            "Completed", "Tremor evaluation", false, "Scheduled for EEG"
        );
        addSampleConsultation(consultationManager,
            "C027", "D402", "P015", LocalDate.of(2025, 3, 17), LocalTime.of(10, 0),
            "Completed", "Memory concerns", false, "Cognitive assessment normal"
        );
        addSampleConsultation(consultationManager,
            "C028", "D402", "P025", LocalDate.of(2025, 3, 18), LocalTime.of(15, 30),
            "Completed", "Numbness in extremities", false, "Ordered nerve conduction study"
        );
        addSampleConsultation(consultationManager,
            "C029", "D404", "P016", LocalDate.of(2025, 3, 19), LocalTime.of(9, 0),
            "Completed", "Migraine management", false, "New preventive regimen"
        );
        addSampleConsultation(consultationManager,
            "C030", "D404", "P026", LocalDate.of(2025, 3, 20), LocalTime.of(13, 0),
            "Completed", "Seizure follow-up", true, "Medication effective, no recent episodes"
        );
        addSampleConsultation(consultationManager,
            "C031", "D404", "P017", LocalDate.of(2025, 3, 21), LocalTime.of(14, 0),
            "Completed", "Sleep disorder evaluation", false, "Referred for sleep study"
        );
        addSampleConsultation(consultationManager,
            "C032", "D404", "P027", LocalDate.of(2025, 3, 22), LocalTime.of(16, 30),
            "Completed", "Balance issues", false, "Vestibular testing recommended"
        );

        // Oncology Consultations (D501-D504)
        addSampleConsultation(consultationManager,
            "C033", "D502", "P018", LocalDate.of(2025, 3, 15), LocalTime.of(10, 30),
            "Completed", "Post-chemotherapy follow-up", true, "Blood counts improving"
        );
        addSampleConsultation(consultationManager,
            "C034", "D502", "P028", LocalDate.of(2025, 3, 16), LocalTime.of(13, 30),
            "Completed", "Abnormal scan results", false, "Biopsy scheduled"
        );
        addSampleConsultation(consultationManager,
            "C035", "D502", "P019", LocalDate.of(2025, 3, 17), LocalTime.of(14, 30),
            "Completed", "Genetic counseling", false, "Discussed family cancer risk"
        );
        addSampleConsultation(consultationManager,
            "C036", "D502", "P029", LocalDate.of(2025, 3, 18), LocalTime.of(16, 0),
            "Completed", "Pain management", false, "Adjusted pain medication regimen"
        );
        addSampleConsultation(consultationManager,
            "C037", "D504", "P020", LocalDate.of(2025, 3, 19), LocalTime.of(9, 30),
            "Completed", "Treatment planning", false, "Discussed radiation therapy options"
        );
        addSampleConsultation(consultationManager,
            "C038", "D504", "P030", LocalDate.of(2025, 3, 20), LocalTime.of(11, 30),
            "Completed", "Second opinion consultation", false, "Confirmed diagnosis and treatment plan"
        );
        addSampleConsultation(consultationManager,
            "C039", "D504", "P021", LocalDate.of(2025, 3, 21), LocalTime.of(15, 0),
            "Completed", "Side effect management", false, "Strategies for managing treatment side effects"
        );
        addSampleConsultation(consultationManager,
            "C040", "D504", "P031", LocalDate.of(2025, 3, 22), LocalTime.of(10, 0),
            "Completed", "Survivorship care plan", true, "Developed long-term follow-up plan"
        );

        // General Surgery Consultations (D601-D604)
        addSampleConsultation(consultationManager,
            "C041", "D602", "P022", LocalDate.of(2025, 3, 15), LocalTime.of(14, 0),
            "Completed", "Pre-operative assessment", false, "Cleared for surgery next week"
        );
        addSampleConsultation(consultationManager,
            "C042", "D602", "P032", LocalDate.of(2025, 3, 16), LocalTime.of(16, 30),
            "Completed", "Post-operative follow-up", true, "Incision healing well"
        );
        addSampleConsultation(consultationManager,
            "C043", "D602", "P023", LocalDate.of(2025, 3, 17), LocalTime.of(11, 30),
            "Completed", "Lump evaluation", false, "Recommended excision biopsy"
        );
        addSampleConsultation(consultationManager,
            "C044", "D602", "P033", LocalDate.of(2025, 3, 18), LocalTime.of(13, 0),
            "Completed", "Hernia consultation", false, "Scheduled for repair surgery"
        );
        addSampleConsultation(consultationManager,
            "C045", "D604", "P024", LocalDate.of(2025, 3, 19), LocalTime.of(10, 0),
            "Completed", "Gallbladder issues", false, "Ultrasound ordered"
        );
        addSampleConsultation(consultationManager,
            "C046", "D604", "P034", LocalDate.of(2025, 3, 20), LocalTime.of(14, 30),
            "Completed", "Appendectomy follow-up", true, "Recovery complete"
        );
        addSampleConsultation(consultationManager,
            "C047", "D604", "P025", LocalDate.of(2025, 3, 21), LocalTime.of(9, 0),
            "Completed", "Colonoscopy consultation", false, "Procedure scheduled"
        );
        addSampleConsultation(consultationManager,
            "C048", "D604", "P035", LocalDate.of(2025, 3, 22), LocalTime.of(15, 30),
            "Completed", "Wound care", false, "Dressing changed, healing progressing"
        );

        // Dermatology Consultations (D701-D704)
        addSampleConsultation(consultationManager,
            "C049", "D702", "P026", LocalDate.of(2025, 3, 15), LocalTime.of(10, 30),
            "Completed", "Acne treatment", false, "Prescribed topical medication"
        );
        addSampleConsultation(consultationManager,
            "C050", "D702", "P036", LocalDate.of(2025, 3, 16), LocalTime.of(13, 30),
            "Completed", "Psoriasis management", false, "New treatment plan initiated"
        );
        addSampleConsultation(consultationManager,
            "C051", "D702", "P027", LocalDate.of(2025, 3, 17), LocalTime.of(14, 0),
            "Completed", "Mole evaluation", false, "Benign, no action needed"
        );
        addSampleConsultation(consultationManager,
            "C052", "D702", "P037", LocalDate.of(2025, 3, 18), LocalTime.of(16, 0),
            "Completed", "Eczema flare-up", false, "Prescribed steroid cream"
        );
        addSampleConsultation(consultationManager,
            "C053", "D704", "P028", LocalDate.of(2025, 3, 19), LocalTime.of(11, 0),
            "Completed", "Skin biopsy results", false, "Basal cell carcinoma, discussed treatment options"
        );
        addSampleConsultation(consultationManager,
            "C054", "D704", "P038", LocalDate.of(2025, 3, 20), LocalTime.of(15, 30),
            "Completed", "Rosacea treatment", false, "Prescribed metronidazole gel"
        );
        addSampleConsultation(consultationManager,
            "C055", "D704", "P029", LocalDate.of(2025, 3, 21), LocalTime.of(9, 30),
            "Completed", "Hair loss consultation", false, "Recommended minoxidil treatment"
        );
        addSampleConsultation(consultationManager,
            "C056", "D704", "P039", LocalDate.of(2025, 3, 22), LocalTime.of(14, 0),
            "Completed", "Fungal infection", false, "Prescribed antifungal medication"
        );

        // Ophthalmology Consultations (D801-D804)
        addSampleConsultation(consultationManager,
            "C057", "D802", "P030", LocalDate.of(2025, 3, 15), LocalTime.of(11, 0),
            "Completed", "Cataract evaluation", false, "Scheduled for surgery next month"
        );
        addSampleConsultation(consultationManager,
            "C058", "D802", "P040", LocalDate.of(2025, 3, 16), LocalTime.of(14, 30),
            "Completed", "Glaucoma screening", false, "Normal eye pressure"
        );
        addSampleConsultation(consultationManager,
            "C059", "D802", "P031", LocalDate.of(2025, 3, 17), LocalTime.of(10, 0),
            "Completed", "Dry eye treatment", false, "Prescribed artificial tears"
        );
        addSampleConsultation(consultationManager,
            "C060", "D802", "P041", LocalDate.of(2025, 3, 18), LocalTime.of(15, 0),
            "Completed", "Diabetic retinopathy screening", false, "No signs of retinopathy"
        );
        addSampleConsultation(consultationManager,
            "C061", "D804", "P032", LocalDate.of(2025, 3, 19), LocalTime.of(9, 0),
            "Completed", "LASIK consultation", false, "Good candidate for procedure"
        );
        addSampleConsultation(consultationManager,
            "C062", "D804", "P042", LocalDate.of(2025, 3, 20), LocalTime.of(13, 30),
            "Completed", "Macular degeneration", false, "Started vitamin therapy"
        );
        addSampleConsultation(consultationManager,
            "C063", "D804", "P033", LocalDate.of(2025, 3, 21), LocalTime.of(14, 0),
            "Completed", "Eye infection", false, "Prescribed antibiotic drops"
        );
        addSampleConsultation(consultationManager,
            "C064", "D804", "P043", LocalDate.of(2025, 3, 22), LocalTime.of(16, 30),
            "Completed", "Vision changes", false, "Updated glasses prescription"
        );

        // Emergency Medicine Consultations (D901-D904)
        addSampleConsultation(consultationManager,
            "C065", "D901", "P034", LocalDate.of(2025, 3, 15), LocalTime.of(8, 0),
            "Completed", "Minor laceration", false, "Stitches applied, tetanus updated"
        );
        addSampleConsultation(consultationManager,
            "C066", "D901", "P044", LocalDate.of(2025, 3, 15), LocalTime.of(20, 0),
            "Completed", "Asthma attack", false, "Nebulizer treatment, discharged with inhaler"
        );
        addSampleConsultation(consultationManager,
            "C067", "D901", "P035", LocalDate.of(2025, 3, 16), LocalTime.of(10, 0),
            "Completed", "Sprained wrist", false, "X-ray negative, splint applied"
        );
        addSampleConsultation(consultationManager,
            "C068", "D901", "P045", LocalDate.of(2025, 3, 16), LocalTime.of(22, 0),
            "Completed", "Food poisoning", false, "IV fluids administered, discharged"
        );
        addSampleConsultation(consultationManager,
            "C069", "D901", "P036", LocalDate.of(2025, 3, 17), LocalTime.of(14, 0),
            "Completed", "Minor burn", false, "Treated and dressed, follow-up needed"
        );
        addSampleConsultation(consultationManager,
            "C070", "D901", "P046", LocalDate.of(2025, 3, 17), LocalTime.of(3, 0),
            "Completed", "Chest pain", false, "Ruled out cardiac event, referred to cardiologist"
        );
        addSampleConsultation(consultationManager,
            "C071", "D904", "P037", LocalDate.of(2025, 3, 18), LocalTime.of(9, 0),
            "Completed", "Allergic reaction", false, "Epinephrine administered, observed for 4 hours"
        );
        addSampleConsultation(consultationManager,
            "C072", "D904", "P047", LocalDate.of(2025, 3, 18), LocalTime.of(18, 0),
            "Completed", "High fever", false, "Tests performed, viral infection diagnosed"
        );

        // Psychiatry Consultations (DC01-DC04)
        addSampleConsultation(consultationManager,
            "C073", "DC02", "P038", LocalDate.of(2025, 3, 15), LocalTime.of(11, 0),
            "Completed", "Depression evaluation", false, "Started antidepressant therapy"
        );
        addSampleConsultation(consultationManager,
            "C074", "DC02", "P048", LocalDate.of(2025, 3, 16), LocalTime.of(14, 0),
            "Completed", "Anxiety management", false, "Cognitive behavioral techniques discussed"
        );
        addSampleConsultation(consultationManager,
            "C075", "DC02", "P039", LocalDate.of(2025, 3, 17), LocalTime.of(15, 0),
            "Completed", "Sleep disorder", false, "Sleep hygiene education provided"
        );
        addSampleConsultation(consultationManager,
            "C076", "DC02", "P049", LocalDate.of(2025, 3, 18), LocalTime.of(16, 30),
            "Completed", "Stress management", false, "Relaxation techniques taught"
        );
        addSampleConsultation(consultationManager,
            "C077", "DC04", "P040", LocalDate.of(2025, 3, 19), LocalTime.of(10, 0),
            "Completed", "Medication adjustment", true, "Adjusted dosage for better efficacy"
        );
        addSampleConsultation(consultationManager,
            "C078", "DC04", "P050", LocalDate.of(2025, 3, 20), LocalTime.of(13, 0),
            "Completed", "Therapy session", false, "Discussed coping strategies"
        );
        addSampleConsultation(consultationManager,
            "C079", "DC04", "P041", LocalDate.of(2025, 3, 21), LocalTime.of(14, 30),
            "Completed", "PTSD evaluation", false, "Trauma-focused therapy initiated"
        );
        addSampleConsultation(consultationManager,
            "C080", "DC04", "P042", LocalDate.of(2025, 3, 22), LocalTime.of(16, 0),
            "Completed", "Bipolar disorder management", true, "Mood stabilizer effectiveness reviewed"
        );

        // Additional follow-up consultations
        addSampleConsultation(consultationManager,
            "C081", "D103", "P001", LocalDate.of(2025, 4, 5), LocalTime.of(11, 0),
            "Scheduled", "Hypertension follow-up", true, "Blood pressure check"
        );
        addSampleConsultation(consultationManager,
            "C082", "D201", "P006", LocalDate.of(2025, 4, 10), LocalTime.of(10, 0),
            "Scheduled", "Vaccination follow-up", true, "Second dose of vaccine"
        );
        addSampleConsultation(consultationManager,
            "C083", "D302", "P010", LocalDate.of(2025, 4, 12), LocalTime.of(14, 0),
            "Scheduled", "Physiotherapy progress", true, "Evaluate knee recovery"
        );
        addSampleConsultation(consultationManager,
            "C084", "D402", "P014", LocalDate.of(2025, 4, 15), LocalTime.of(9, 30),
            "Scheduled", "Headache treatment review", true, "Assess medication effectiveness"
        );

        
    } catch (Exception e) {
        System.out.println("Error loading sample consultations: " + e.getMessage());
    }
 }
    private static void addSampleConsultation
        (ConsultationManager manager, 
         String consultationId, 
         String doctorId, 
         String patientId, 
        LocalDate date, LocalTime time, String status, String type, boolean followUp, String notes) {
    
    Consultation consultation = new Consultation(
        consultationId, doctorId, patientId, date, time, status, type, followUp, notes
    );
    
    manager.addConsultation(consultation);
}
<<<<<<< HEAD
        
        
=======
>>>>>>> 93ed186f0bb04ef2f00033157df5552c8a2bb78e
}