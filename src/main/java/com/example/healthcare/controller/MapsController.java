package com.example.healthcare.controller;

import com.example.healthcare.services.GoogleMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maps")
public class MapsController {
  private final GoogleMapsService googleMapsService;

  @Autowired
  public MapsController(GoogleMapsService googleMapsService) {
    this.googleMapsService = googleMapsService;
  }

  @GetMapping("/directions")
  public ResponseEntity<String> getDirections(@RequestParam String origin, @RequestParam String destination) {
    String directions = googleMapsService.getDirections(origin, destination);
    return ResponseEntity.ok(directions);
  }
}

