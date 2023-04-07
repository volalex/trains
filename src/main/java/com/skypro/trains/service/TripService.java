package com.skypro.trains.service;

import com.skypro.trains.dto.TripListDTO;
import com.skypro.trains.repository.TripRepository;
import com.skypro.trains.repository.TripSpecifications;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TripService {

  private final TripRepository tripRepository;

  public TripService(TripRepository tripRepository) {
    this.tripRepository = tripRepository;
  }

  public List<TripListDTO> findTrips(
      String departureCity,
      String arrivalCity,
      LocalDate departureDate,
      LocalDate arrivalDate) {
    return this.tripRepository.findAll(
            TripSpecifications.getTripsSpec(departureCity, arrivalCity, departureDate, arrivalDate))
        .stream()
        .map(trip -> new TripListDTO(trip.getId(), trip.getDepartureTime(), trip.getArrivalTime(),
            trip.getDepartureStation().getCity(),
            trip.getArrivalStation().getCity(),
            trip.getTrain().getSerial(),
            trip.getTrain().getCarriageNumber()))
        .toList();
  }
}
