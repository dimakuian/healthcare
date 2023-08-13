package com.example.healthcare.controller;

import com.example.healthcare.model.Medication;
import com.example.healthcare.services.MedicationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medications")
public class MedicationController {
  private final MedicationService medicationService;

  @Autowired
  public MedicationController(MedicationService medicationService) {
    this.medicationService = medicationService;
  }

  @GetMapping
  public List<Medication> getAllMedications() {
    return medicationService.getAllMedications();
  }

  @GetMapping("/{id}")
  public Medication getMedicationById(@PathVariable Long id) {
    return medicationService.getMedicationById(id);
  }

  @PostMapping
  public Medication createMedication(@RequestBody Medication medication) {
    return medicationService.createMedication(medication);
  }

  @PutMapping("/{id}")
  public Medication updateMedication(@PathVariable Long id, @RequestBody Medication updatedMedication) {
    return medicationService.updateMedication(id, updatedMedication);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMedication(@PathVariable Long id) {
    medicationService.deleteMedication(id);
    return ResponseEntity.noContent().build();
  }
}

