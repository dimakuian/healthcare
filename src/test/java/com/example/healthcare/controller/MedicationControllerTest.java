package com.example.healthcare.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.healthcare.model.Medication;
import com.example.healthcare.services.MedicationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class MedicationControllerTest {

  @InjectMocks
  private MedicationController medicationController;

  @Mock
  private MedicationService medicationService;

  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(medicationController).build();
  }

  @Test
  public void testCreateMedication() throws Exception {
    Medication medication = Medication.builder()
        .name("Medication 1")
        .dosage("5mg")
        .instructions("Take once a day")
        .build();
    Mockito.when(medicationService.createMedication(Mockito.any())).thenReturn(medication);

    mockMvc.perform(post("/api/medications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(medication)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Medication 1"));
  }

  @Test
  public void testGetMedicationById() throws Exception {
    Medication medication = Medication.builder()
        .name("Medication 1")
        .dosage("5mg")
        .instructions("Take once a day")
        .build();
    Mockito.when(medicationService.getMedicationById(1L)).thenReturn(medication);

    mockMvc.perform(get("/api/medications/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Medication 1"));
  }

  @Test
  public void testUpdateMedication() throws Exception {
    Medication medication = Medication.builder()
        .name("Medication 1")
        .dosage("5mg")
        .instructions("Take once a day")
        .build();
    Mockito.when(medicationService.updateMedication(Mockito.anyLong(), Mockito.any()))
        .thenReturn(medication);

    mockMvc.perform(put("/api/medications/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(medication)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Medication 1"));
  }

  @Test
  public void testDeleteMedication() throws Exception {
    mockMvc.perform(delete("/api/medications/1"))
        .andExpect(status().isNoContent());

    Mockito.verify(medicationService, Mockito.times(1)).deleteMedication(1L);
  }

  // Helper method to convert objects to JSON strings
  private String asJsonString(Object obj) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(obj);
  }
}
