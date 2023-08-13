package com.example.healthcare.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.healthcare.model.Appointment;
import com.example.healthcare.services.AppointmentService;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AppointmentService appointmentService;


  @Test
  public void testGetAllAppointments() throws Exception {
    Appointment appointment = new Appointment();
    appointment.setId(1L);
    when(appointmentService.getAllAppointments()).thenReturn(Arrays.asList(appointment));

    mockMvc.perform(get("/appointments"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(1L));
  }

  @Test
  public void testGetAppointmentById() throws Exception {
    Appointment appointment = new Appointment();
    when(appointmentService.getAppointmentById(anyLong())).thenReturn(Optional.of(appointment));

    mockMvc.perform(get("/appointments/1"))
        .andExpect(status().isOk());
  }

  @Test
  public void testCreateAppointment() throws Exception {
    Appointment appointment = new Appointment();
    when(appointmentService.createAppointment(any(Appointment.class))).thenReturn(appointment);

    mockMvc.perform(post("/appointments")
            .contentType("application/json")
            .content("{}"))
        .andExpect(status().isOk());
  }

  @Test
  public void testUpdateAppointment() throws Exception {
    mockMvc.perform(put("/appointments/1")
            .contentType("application/json")
            .content("{}"))
        .andExpect(status().isOk());

    verify(appointmentService, times(1)).updateAppointment(eq(1L), any(Appointment.class));
  }

  @Test
  public void testDeleteAppointment() throws Exception {
    mockMvc.perform(delete("/appointments/1"))
        .andExpect(status().isOk());

    verify(appointmentService, times(1)).deleteAppointment(1L);
  }
}
