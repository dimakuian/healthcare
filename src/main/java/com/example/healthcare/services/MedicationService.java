package com.example.healthcare.services;

import com.example.healthcare.model.Medication;
import com.example.healthcare.repository.MedicationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicationService {
  private final MedicationRepository medicationRepository;

  @Autowired
  public MedicationService(MedicationRepository medicationRepository) {
    this.medicationRepository = medicationRepository;
  }

  public List<Medication> getAllMedications() {
    return medicationRepository.findAll();
  }

  public Medication getMedicationById(Long id) {
    return medicationRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Medication not found with id: " + id));
  }

  public Medication createMedication(Medication medication) {
    return medicationRepository.save(medication);
  }

  public Medication updateMedication(Long id, Medication updatedMedication) {
    Medication medication = getMedicationById(id);
    medication.setName(updatedMedication.getName());
    medication.setDosage(updatedMedication.getDosage());
    medication.setInstructions(updatedMedication.getInstructions());
    return medicationRepository.save(medication);
  }

  public void deleteMedication(Long id) {
    medicationRepository.deleteById(id);
  }
}

