package com.example.healthcare.controller;

import com.example.healthcare.model.Appointment;
import com.example.healthcare.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
  private final AppointmentService appointmentService;

  @Autowired
  public AppointmentController(AppointmentService appointmentService) {
    this.appointmentService = appointmentService;
  }

  @GetMapping
  public List<Appointment> getAllAppointments() {
    return appointmentService.getAllAppointments();
  }

  @GetMapping("/{id}")
  public Optional<Appointment> getAppointmentById(@PathVariable Long id) {
    return appointmentService.getAppointmentById(id);
  }

  @PostMapping
  public Appointment createAppointment(@RequestBody Appointment appointment) {
    return appointmentService.createAppointment(appointment);
  }

  @PutMapping("/{id}")
  public void updateAppointment(@PathVariable Long id, @RequestBody Appointment updatedAppointment) {
    appointmentService.updateAppointment(id, updatedAppointment);
  }

  @DeleteMapping("/{id}")
  public void deleteAppointment(@PathVariable Long id) {
    appointmentService.deleteAppointment(id);
  }
}

