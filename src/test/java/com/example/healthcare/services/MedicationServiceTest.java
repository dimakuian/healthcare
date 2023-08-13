package com.example.healthcare.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.healthcare.model.Medication;
import com.example.healthcare.repository.MedicationRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MedicationServiceTest {

  @InjectMocks
  private MedicationService medicationService;

  @Mock
  private MedicationRepository medicationRepository;

  @Test
  public void testCreateMedication() {
    Medication medication =
        Medication.builder()
            .name("Medication 1")
            .dosage("5mg")
            .instructions("Take once a day")
            .build();

    Mockito.when(medicationRepository.save(Mockito.any())).thenReturn(medication);

    Medication createdMedication = medicationService.createMedication(medication);

    assertEquals("Medication 1", createdMedication.getName());
    assertEquals("5mg", createdMedication.getDosage());
    assertEquals("Take once a day", createdMedication.getInstructions());
  }

  @Test
  public void testGetMedicationById() {
    Medication medication = Medication.builder()
        .name("Medication 1")
        .dosage("5mg")
        .instructions("Take once a day")
        .build();
    Mockito.when(medicationRepository.findById(1L)).thenReturn(Optional.of(medication));

    Medication retrievedMedication = medicationService.getMedicationById(1L);

    assertEquals("Medication 1", retrievedMedication.getName());
  }

  @Test
  public void testUpdateMedication() {
    Medication medication = Medication.builder()
        .name("Medication 1")
        .dosage("5mg")
        .instructions("Take once a day")
        .build();
    Mockito.when(medicationRepository.findById(1L)).thenReturn(Optional.of(medication));

    Medication updatedMedication = Medication.builder()
        .name("Updated Medication")
        .dosage("10mg")
        .instructions("Take twice a day")
        .build();
    Mockito.when(medicationRepository.save(Mockito.any())).thenReturn(updatedMedication);

    Medication result = medicationService.updateMedication(1L, updatedMedication);

    assertEquals("Updated Medication", result.getName());
    assertEquals("10mg", result.getDosage());
    assertEquals("Take twice a day", result.getInstructions());
  }

  @Test
  public void testDeleteMedication() {
    medicationService.deleteMedication(1L);

    Mockito.verify(medicationRepository, Mockito.times(1)).deleteById(1L);
  }
}