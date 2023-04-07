package com.skypro.trains.dto;

import java.time.Instant;

public record TripListDTO(long id,
                          Instant departureTime,
                          Instant arrivalTime,
                          String departureCity,
                          String arrivalCity,
                          String serial,
                          int carriageNumber) {

}
