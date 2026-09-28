package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {

    private static final Map<String, Map<String, Integer>> villageInventories = new HashMap<>();
    private static final Map<String, String> alternativeMeds = new HashMap<>();

    static {
        // Mock data for Nabha's Sub-Center 04 clinic
        Map<String, Integer> subCenter04Stock = new HashMap<>();
        subCenter04Stock.put("MED_PARACETAMOL_500", 150);
        subCenter04Stock.put("MED_AMOXICILLIN_250", 0); // OUT OF STOCK EXCEPTION
        
        villageInventories.put("SUB_CENTER_04", subCenter04Stock);
        alternativeMeds.put("MED_AMOXICILLIN_250", "MED_AZITHROMYCIN_500");
    }

    @PostMapping("/validate")
    public ValidationResponse validatePrescription(@RequestBody PrescriptionRequest request) {
        String subCenter = request.getSubCenterId();
        String medicine = request.getMedicineId();

        int currentStock = 0;
        if (villageInventories.containsKey(subCenter)) {
            currentStock = villageInventories.get(subCenter).getOrDefault(medicine, 0);
        }

        if (currentStock == 0) {
            String substitute = alternativeMeds.getOrDefault(medicine, "NONE_AVAILABLE");
            return new ValidationResponse(
                "WARNING_OUT_OF_STOCK",
                "Primary item is completely unavailable at the patient's local village dispensary.",
                "TRIGGER_SUBSTITUTION_ALERT",
                substitute
            );
        }

        return new ValidationResponse(
            "APPROVED",
            "Medicine allocation confirmed in local inventory layers.",
            "PROCEED_WITH_ISSUANCE",
            "N/A"
        );
    }

    // 🌟 PASTE START HERE: Placed neatly inside the class brackets 🌟

    // 💡 Add this list inside your PrescriptionController class to store patients in memory
    private static final java.util.List<Patient> globalHospitalQueue = new java.util.concurrent.CopyOnWriteArrayList<>();

    // 💡 Endpoint 1: Receives the data from the Flutter App background sync
    @PostMapping("/bulk-sync")
    public org.springframework.http.ResponseEntity<String> bulkSyncPatients(@RequestBody Map<String, java.util.List<Patient>> payload) {
        java.util.List<Patient> records = payload.get("records");
        if (records != null) {
            for (Patient p : records) {
                // Avoid adding duplicates
                if (globalHospitalQueue.stream().noneMatch(existing -> existing.getId().equals(p.getId()))) {
                    globalHospitalQueue.add(p);
                    System.out.println("🚀 [DATA SYNCED] Patient " + p.getName() + " reached Nabha Civil Hospital Queue!");
                }
            }
        }
        return org.springframework.http.ResponseEntity.ok("Sync Successful");
    }

    // 💡 Endpoint 2: Exposes the data to the Doctor's Dashboard webpage
    @GetMapping("/queue")
    @CrossOrigin(origins = "*") // Allows your web dashboard to read the data without errors
    public java.util.List<Patient> getHospitalQueue() {
        try {
            // Automatically sorts the queue by Critical/High cases first for the doctor!
            globalHospitalQueue.sort((p1, p2) -> p2.getTriageStatus().compareTo(p1.getTriageStatus()));
        } catch (UnsupportedOperationException e) {
            // Fallback if the thread-safe copy array rejects direct sorting mutations
        }
        return globalHospitalQueue;
    }

} // ⚠️ THIS IS THE VERY LAST CLOSING CURLY BRACKET. KEEP IT HERE!
