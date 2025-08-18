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
            addSampleMedication(pharmacyControl, "M001", "Paracetamol", "Pain reliever", 10.50, 100, "Tablet");
            addSampleMedication(pharmacyControl, "M002", "Ibuprofen", "Anti-inflammatory", 15.00, 50, "Tablet");
            addSampleMedication(pharmacyControl, "M003", "Cough Syrup", "Soothes cough", 25.00, 30, "Liquid");
            addSampleMedication(pharmacyControl, "M004", "Antacid", "Relieves heartburn", 12.75, 80, "Liquid");
            addSampleMedication(pharmacyControl, "M005", "Aspirin", "Blood thinner", 8.00, 20, "Tablet");
            addSampleMedication(pharmacyControl, "M006", "Amoxicillin", "Antibiotic", 35.20, 60, "Capsule");
            addSampleMedication(pharmacyControl, "M007", "Vitamin C", "Supplement", 18.00, 150, "Tablet");
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
            treatmentControl.addDiagnosisTemplate("DIAG07", "Allergic Rhinitis", "Runny nose, sneezing, and congestion caused by allergens. Suggest antihistamines.");
            treatmentControl.addDiagnosisTemplate("DIAG08", "Asthma Attack", "Narrowing of airways causing wheezing and shortness of breath. Requires inhaler use.");
            treatmentControl.addDiagnosisTemplate("DIAG09", "Bronchitis", "Inflammation of the bronchial tubes, leading to cough and mucus. Suggest fluids and rest.");
            treatmentControl.addDiagnosisTemplate("DIAG10", "Pneumonia", "Lung infection causing cough, fever, and breathing difficulty. Antibiotics may be needed.");
            treatmentControl.addDiagnosisTemplate("DIAG11", "Gastritis", "Stomach lining inflammation causing pain and nausea. Avoid spicy food and use antacids.");
            treatmentControl.addDiagnosisTemplate("DIAG12", "Food Poisoning", "Nausea, vomiting, and diarrhea caused by contaminated food. Recommend fluids and rest.");
            treatmentControl.addDiagnosisTemplate("DIAG13", "Constipation", "Difficulty passing stools. Suggest high fiber diet and hydration.");
            treatmentControl.addDiagnosisTemplate("DIAG14", "Diarrhea", "Frequent loose stools. Risk of dehydration. Recommend oral rehydration salts.");
            treatmentControl.addDiagnosisTemplate("DIAG15", "Acid Reflux", "Burning sensation in the chest after eating. Suggest antacids and lifestyle changes.");
            treatmentControl.addDiagnosisTemplate("DIAG16", "Urinary Tract Infection", "Burning sensation during urination, frequent urge to urinate. Needs antibiotics.");
            treatmentControl.addDiagnosisTemplate("DIAG17", "Kidney Stones", "Severe pain in lower back or side. May require pain relief or medical removal.");
            treatmentControl.addDiagnosisTemplate("DIAG18", "Hypertension", "High blood pressure often without symptoms. Recommend monitoring and lifestyle change.");
            treatmentControl.addDiagnosisTemplate("DIAG19", "Hypotension", "Low blood pressure causing dizziness and fainting. Suggest hydration and rest.");
            treatmentControl.addDiagnosisTemplate("DIAG20", "Diabetes (Type 2)", "High blood sugar levels. Recommend lifestyle changes and possible medication.");
            treatmentControl.addDiagnosisTemplate("DIAG21", "Diabetes (Type 1)", "Insufficient insulin production. Requires insulin therapy.");
            treatmentControl.addDiagnosisTemplate("DIAG22", "Anemia", "Low red blood cell count causing fatigue. Iron supplements may be required.");
            treatmentControl.addDiagnosisTemplate("DIAG23", "Dehydration", "Loss of body fluids leading to weakness and dizziness. Recommend oral rehydration.");
            treatmentControl.addDiagnosisTemplate("DIAG24", "Heat Exhaustion", "Weakness, heavy sweating, and fainting due to high temperatures. Cool down immediately.");
            treatmentControl.addDiagnosisTemplate("DIAG25", "Heat Stroke", "Severe overheating causing confusion and collapse. Requires emergency care.");
            treatmentControl.addDiagnosisTemplate("DIAG26", "Chickenpox", "Viral infection with itchy blisters. Supportive care and avoid scratching.");
            treatmentControl.addDiagnosisTemplate("DIAG27", "Measles", "Viral infection with fever, cough, and rash. Highly contagious, requires isolation.");
            treatmentControl.addDiagnosisTemplate("DIAG28", "Mumps", "Swelling of salivary glands. Rest, fluids, and pain relievers recommended.");
            treatmentControl.addDiagnosisTemplate("DIAG29", "Whooping Cough", "Severe coughing fits followed by a ‘whoop’ sound. Requires antibiotics.");
            treatmentControl.addDiagnosisTemplate("DIAG30", "Tuberculosis", "Bacterial lung infection causing cough and weight loss. Needs long-term antibiotics.");
            treatmentControl.addDiagnosisTemplate("DIAG31", "COVID-19", "Respiratory infection with cough, fever, and fatigue. May need isolation and antiviral therapy.");
            treatmentControl.addDiagnosisTemplate("DIAG32", "Sinusitis", "Inflammation of sinuses causing facial pain and congestion. Suggest decongestants.");
            treatmentControl.addDiagnosisTemplate("DIAG33", "Otitis Media", "Middle ear infection causing pain and fever. May require antibiotics.");
            treatmentControl.addDiagnosisTemplate("DIAG34", "Conjunctivitis", "Red, itchy eyes caused by infection or allergy. Use eye drops or antihistamines.");
            treatmentControl.addDiagnosisTemplate("DIAG35", "Eczema", "Chronic skin condition with dry, itchy patches. Use moisturizers and avoid triggers.");
            treatmentControl.addDiagnosisTemplate("DIAG36", "Psoriasis", "Skin condition with red, scaly patches. May need topical or systemic treatment.");
            treatmentControl.addDiagnosisTemplate("DIAG37", "Acne", "Clogged pores leading to pimples. Use topical treatments or antibiotics.");
            treatmentControl.addDiagnosisTemplate("DIAG38", "Sunburn", "Skin damage due to UV rays. Apply soothing lotions and avoid sun exposure.");
            treatmentControl.addDiagnosisTemplate("DIAG39", "Fungal Infection", "Itchy rash caused by fungus (athlete’s foot, ringworm). Use antifungal cream.");
            treatmentControl.addDiagnosisTemplate("DIAG40", "Scabies", "Skin infestation by mites causing intense itching. Requires medicated cream.");
            treatmentControl.addDiagnosisTemplate("DIAG41", "Depression", "Persistent sadness and lack of interest. Suggest counseling and possible medication.");
            treatmentControl.addDiagnosisTemplate("DIAG42", "Anxiety Disorder", "Excessive worry and restlessness. Therapy or medication may be needed.");
            treatmentControl.addDiagnosisTemplate("DIAG43", "Panic Attack", "Sudden intense fear with rapid heartbeat. Encourage relaxation techniques.");
            treatmentControl.addDiagnosisTemplate("DIAG44", "Insomnia", "Difficulty falling or staying asleep. Suggest sleep hygiene and relaxation.");
            treatmentControl.addDiagnosisTemplate("DIAG45", "Bipolar Disorder", "Mood swings between highs and lows. Requires medical treatment.");
            treatmentControl.addDiagnosisTemplate("DIAG46", "Schizophrenia", "Mental disorder with distorted thinking and hallucinations. Needs antipsychotics.");
            treatmentControl.addDiagnosisTemplate("DIAG47", "PTSD", "Anxiety after traumatic events. Therapy and medication may be required.");
            treatmentControl.addDiagnosisTemplate("DIAG48", "Dementia", "Decline in memory and thinking skills. Supportive care and medication available.");
            treatmentControl.addDiagnosisTemplate("DIAG49", "Alzheimer Disease", "Progressive brain disorder causing memory loss. Supportive treatment only.");
            treatmentControl.addDiagnosisTemplate("DIAG50", "Parkinson Disease", "Nervous system disorder causing tremors and stiffness. Requires medication.");
            treatmentControl.addDiagnosisTemplate("DIAG51", "Epilepsy", "Seizure disorder. Requires anticonvulsant medication.");
            treatmentControl.addDiagnosisTemplate("DIAG52", "Stroke", "Brain damage due to interrupted blood flow. Emergency treatment required.");
            treatmentControl.addDiagnosisTemplate("DIAG53", "Heart Attack", "Blocked blood flow to the heart muscle. Emergency treatment required.");
            treatmentControl.addDiagnosisTemplate("DIAG54", "Arrhythmia", "Irregular heartbeat. May need medication or pacemaker.");
            treatmentControl.addDiagnosisTemplate("DIAG55", "Heart Failure", "Weak heart unable to pump effectively. Lifestyle changes and medication needed.");
            treatmentControl.addDiagnosisTemplate("DIAG56", "Obesity", "Excess body fat increasing health risks. Recommend diet and exercise.");
            treatmentControl.addDiagnosisTemplate("DIAG57", "Hyperthyroidism", "Overactive thyroid causing weight loss and anxiety. Requires medication.");
            treatmentControl.addDiagnosisTemplate("DIAG58", "Hypothyroidism", "Underactive thyroid causing fatigue and weight gain. Needs hormone therapy.");
            treatmentControl.addDiagnosisTemplate("DIAG59", "Vitamin D Deficiency", "Lack of vitamin D causing bone pain. Recommend supplements and sunlight.");
            treatmentControl.addDiagnosisTemplate("DIAG60", "Vitamin B12 Deficiency", "Causes anemia and neurological issues. Requires supplements.");
            treatmentControl.addDiagnosisTemplate("DIAG61", "Scurvy", "Vitamin C deficiency causing gum bleeding. Recommend citrus fruits.");
            treatmentControl.addDiagnosisTemplate("DIAG62", "Rickets", "Bone softening in children due to vitamin D deficiency. Needs supplements.");
            treatmentControl.addDiagnosisTemplate("DIAG63", "Osteoporosis", "Weak and brittle bones. Recommend calcium and exercise.");
            treatmentControl.addDiagnosisTemplate("DIAG64", "Arthritis", "Joint inflammation causing pain and stiffness. Pain relievers may help.");
            treatmentControl.addDiagnosisTemplate("DIAG65", "Gout", "Sudden joint pain due to uric acid buildup. Suggest dietary changes.");
            treatmentControl.addDiagnosisTemplate("DIAG66", "Carpal Tunnel Syndrome", "Nerve compression in the wrist causing pain and numbness. May need splint.");
            treatmentControl.addDiagnosisTemplate("DIAG67", "Tendonitis", "Inflammation of tendons causing pain. Rest and anti-inflammatory drugs help.");
            treatmentControl.addDiagnosisTemplate("DIAG68", "Sciatica", "Nerve pain radiating down the leg. Rest and physiotherapy recommended.");
            treatmentControl.addDiagnosisTemplate("DIAG69", "Herniated Disc", "Spinal disc displacement causing back pain. May need surgery.");
            treatmentControl.addDiagnosisTemplate("DIAG70", "Fracture", "Broken bone requiring immobilization or surgery.");
            treatmentControl.addDiagnosisTemplate("DIAG71", "Dislocation", "Bone out of joint position. Needs reduction by medical staff.");
            treatmentControl.addDiagnosisTemplate("DIAG72", "Whiplash", "Neck injury due to sudden jerk. Rest and pain relievers recommended.");
            treatmentControl.addDiagnosisTemplate("DIAG73", "Burn (First Degree)", "Red skin without blisters. Cool water treatment.");
            treatmentControl.addDiagnosisTemplate("DIAG74", "Burn (Second Degree)", "Blistered, painful skin. Medical care required.");
            treatmentControl.addDiagnosisTemplate("DIAG75", "Burn (Third Degree)", "Severe skin damage affecting deeper tissues. Emergency care needed.");
            treatmentControl.addDiagnosisTemplate("DIAG76", "Frostbite", "Skin and tissue freezing due to cold exposure. Warm gradually.");
            treatmentControl.addDiagnosisTemplate("DIAG77", "Hypothermia", "Dangerously low body temperature. Emergency warming required.");
            treatmentControl.addDiagnosisTemplate("DIAG78", "Snake Bite", "Venom injection causing pain and swelling. Emergency antivenom required.");
            treatmentControl.addDiagnosisTemplate("DIAG79", "Dog Bite", "Puncture wounds risk infection. May need rabies vaccination.");
            treatmentControl.addDiagnosisTemplate("DIAG80", "Bee Sting", "Localized pain and swelling. Severe allergy may require epinephrine.");
            treatmentControl.addDiagnosisTemplate("DIAG81", "Anaphylaxis", "Severe allergic reaction. Emergency epinephrine required.");
            treatmentControl.addDiagnosisTemplate("DIAG82", "Poisoning", "Ingestion of toxic substance. Emergency care required.");
            treatmentControl.addDiagnosisTemplate("DIAG83", "Lead Poisoning", "Chronic exposure causes developmental issues. Requires chelation therapy.");
            treatmentControl.addDiagnosisTemplate("DIAG84", "Carbon Monoxide Poisoning", "Headache, dizziness, and confusion. Requires oxygen therapy.");
            treatmentControl.addDiagnosisTemplate("DIAG85", "Alcohol Intoxication", "Impaired judgment and coordination. Rest and hydration required.");
            treatmentControl.addDiagnosisTemplate("DIAG86", "Drug Overdose", "Toxic reaction to excessive drug use. Emergency care required.");
            treatmentControl.addDiagnosisTemplate("DIAG87", "Appendicitis", "Severe abdominal pain needing surgical removal of appendix.");
            treatmentControl.addDiagnosisTemplate("DIAG88", "Gallstones", "Hardened deposits in gallbladder causing pain. May require surgery.");
            treatmentControl.addDiagnosisTemplate("DIAG89", "Pancreatitis", "Inflammation of pancreas causing abdominal pain. Needs hospitalization.");
            treatmentControl.addDiagnosisTemplate("DIAG90", "Hepatitis A", "Viral liver infection spread by food. Usually self-limiting.");
            treatmentControl.addDiagnosisTemplate("DIAG91", "Hepatitis B", "Viral liver infection spread by blood. May become chronic.");
            treatmentControl.addDiagnosisTemplate("DIAG92", "Hepatitis C", "Viral liver infection causing chronic damage. Needs antiviral drugs.");
            treatmentControl.addDiagnosisTemplate("DIAG93", "Cirrhosis", "Chronic liver damage due to alcohol or hepatitis. Supportive care required.");
            treatmentControl.addDiagnosisTemplate("DIAG94", "Jaundice", "Yellowing of skin due to liver issues. Treat underlying cause.");
            treatmentControl.addDiagnosisTemplate("DIAG95", "Cholecystitis", "Gallbladder inflammation. May need surgery.");
            treatmentControl.addDiagnosisTemplate("DIAG96", "Irritable Bowel Syndrome", "Chronic digestive disorder with cramps and bloating. Lifestyle management needed.");
            treatmentControl.addDiagnosisTemplate("DIAG97", "Crohn Disease", "Inflammatory bowel disease affecting intestines. Requires medication.");
            treatmentControl.addDiagnosisTemplate("DIAG98", "Ulcerative Colitis", "Inflammatory bowel disease causing bloody diarrhea. Needs treatment.");
            treatmentControl.addDiagnosisTemplate("DIAG99", "Peptic Ulcer", "Sores in stomach lining causing pain. Antacids and antibiotics recommended.");
            treatmentControl.addDiagnosisTemplate("DIAG100", "Hemorrhoids", "Swollen veins in rectum causing pain and bleeding. Suggest high fiber diet.");
        } catch (Exception e) {
            System.out.println("Error loading sample diagnoses: " + e.getMessage());
        }
    }
    
}