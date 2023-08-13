package com.example.healthcare.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleMapsService {
  @Value("${google.maps.api.key}") // Inject your API key from configuration
  private String apiKey;

  public String getDirections(String origin, String destination) {
    String apiUrl = "https://maps.googleapis.com/maps/api/directions/json" +
        "?origin=" + origin +
        "&destination=" + destination +
        "&key=" + apiKey;

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

    if (response.getStatusCode().is2xxSuccessful()) {
      return response.getBody();
    } else {
      // Handle error cases
      return "Error fetching directions";
    }
  }
}

