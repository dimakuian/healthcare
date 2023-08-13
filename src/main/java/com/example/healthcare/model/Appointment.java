package com.example.healthcare.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Appointment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime appointmentDateTime;
  private String status; // Replaced AppointmentStatus enum with String

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;  // Reference to the User entity

  @ManyToMany
  @JoinTable(name = "appointment_medication",
      joinColumns = @JoinColumn(name = "appointment_id"),
      inverseJoinColumns = @JoinColumn(name = "medication_id"))
  private List<Medication> medications = new ArrayList<>();

}


