package com.example.healthcare.services;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import com.example.healthcare.model.Appointment;
import com.example.healthcare.repository.AppointmentRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {

  @Mock
  private AppointmentRepository appointmentRepository;

  @InjectMocks
  private AppointmentService appointmentService;

  @Test
  public void testCreateAppointment() {
    Appointment appointmentToCreate = new Appointment();
    when(appointmentRepository.save(appointmentToCreate)).thenReturn(appointmentToCreate);

    Appointment createdAppointment = appointmentService.createAppointment(appointmentToCreate);

    assertEquals(appointmentToCreate, createdAppointment);
  }

  @Test
  public void testGetAppointmentById() {
    Long appointmentId = 1L;
    Appointment appointment = new Appointment();
    appointment.setId(appointmentId);

    when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));

    Optional<Appointment> retrievedAppointment = appointmentService.getAppointmentById(appointmentId);

    assertTrue(retrievedAppointment.isPresent());
    assertEquals(appointmentId, retrievedAppointment.get().getId());
  }

  @Test
  public void testUpdateAppointment() {
    Long appointmentId = 1L;
    Appointment originalAppointment = new Appointment();
    originalAppointment.setId(appointmentId);

    when(appointmentRepository.existsById(appointmentId)).thenReturn(true);
    when(appointmentRepository.save(any(Appointment.class))).thenReturn(originalAppointment);

    Appointment updatedAppointment = new Appointment();
    updatedAppointment.setId(appointmentId);
    appointmentService.updateAppointment(appointmentId, updatedAppointment);

    verify(appointmentRepository).save(updatedAppointment);
  }

  @Test
  public void testDeleteAppointment() {
    Long appointmentId = 1L;

    appointmentService.deleteAppointment(appointmentId);

    verify(appointmentRepository).deleteById(appointmentId);
  }
}
