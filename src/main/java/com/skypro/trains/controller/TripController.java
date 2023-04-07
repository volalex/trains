package com.skypro.trains.controller;

import com.skypro.trains.dto.TripListDTO;
import com.skypro.trains.service.TripService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trip")
public class TripController {

  private TripService tripService;

  public TripController(TripService tripService) {
    this.tripService = tripService;
  }

  @GetMapping
  public List<TripListDTO> getTrips(
      @RequestParam(name = "departureCity", required = false) String departureCity,
      @RequestParam(name = "arrivalCity", required = false) String arrivalCity,
      @RequestParam(name = "departureDate", required = false)
      @DateTimeFormat(iso = ISO.DATE)
      LocalDate departureDate,
      @RequestParam(name = "arrivalDate", required = false)
      @DateTimeFormat(iso = ISO.DATE)
      LocalDate arrivalDate) {
    return this.tripService.findTrips(departureCity, arrivalCity, departureDate, arrivalDate);
  }
}
