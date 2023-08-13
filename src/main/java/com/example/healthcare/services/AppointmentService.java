package com.example.healthcare.services;

import com.example.healthcare.model.Appointment;
import com.example.healthcare.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
  private final AppointmentRepository appointmentRepository;

  @Autowired
  public AppointmentService(AppointmentRepository appointmentRepository) {
    this.appointmentRepository = appointmentRepository;
  }

  public List<Appointment> getAllAppointments() {
    return appointmentRepository.findAll();
  }

  public Optional<Appointment> getAppointmentById(Long id) {
    return appointmentRepository.findById(id);
  }

  public Appointment createAppointment(Appointment appointment) {
    return appointmentRepository.save(appointment);
  }

  public void updateAppointment(Long id, Appointment updatedAppointment) {
    if (appointmentRepository.existsById(id)) {
      updatedAppointment.setId(id);
      appointmentRepository.save(updatedAppointment);
    }
  }

  public void deleteAppointment(Long id) {
    appointmentRepository.deleteById(id);
  }
}

