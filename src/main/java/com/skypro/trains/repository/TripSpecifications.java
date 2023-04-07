package com.skypro.trains.repository;

import com.skypro.trains.model.Station;
import com.skypro.trains.model.Station_;
import com.skypro.trains.model.Trip;
import com.skypro.trains.model.Trip_;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Pair;
import org.springframework.util.StringUtils;

public class TripSpecifications {

  public static Specification<Trip> getTripsSpec(
      String departureCity,
      String arrivalCity,
      LocalDate departureDate,
      LocalDate arrivalDate) {
    List<Specification<Trip>> specList = new ArrayList<>();

    if (StringUtils.hasText(departureCity)) {
      specList.add(departureCityNameEquals(departureCity));
    }
    if (StringUtils.hasText(arrivalCity)) {
      specList.add(arrivalCityNameEquals(arrivalCity));
    }
    if (departureDate != null) {
      specList.add(departureDateEquals(departureDate));
    }
    if (arrivalDate != null) {
      specList.add(arrivalDateEquals(arrivalDate));
    }
    if (specList.isEmpty()) {
      return null;
    }
    Specification<Trip> combinedSpec = Specification.where(specList.get(0));
    for (int i = 1; i < specList.size(); i++) {
      combinedSpec = combinedSpec.and(specList.get(i));
    }
    return combinedSpec;
  }

  public static Specification<Trip> departureCityNameEquals(String departureCity) {
    return (root, query, criteriaBuilder) -> {
      Join<Trip, Station> join = root.join(Trip_.departureStation);
      return criteriaBuilder.equal(join.get(Station_.city), departureCity);
    };
  }

  public static Specification<Trip> arrivalCityNameEquals(String arrivalCity) {
    return (root, query, criteriaBuilder) -> {
      Join<Trip, Station> join = root.join(Trip_.arrivalStation);
      return criteriaBuilder.equal(join.get(Station_.city), arrivalCity);
    };
  }

  public static Specification<Trip> departureDateEquals(LocalDate departureDate) {
    return (root, query, criteriaBuilder) -> {
      Pair<Instant, Instant> dateRange = toDateRange(departureDate);
      return criteriaBuilder.between(root.get(Trip_.departureTime), dateRange.getFirst(),
          dateRange.getSecond());
    };
  }

  public static Specification<Trip> arrivalDateEquals(LocalDate arrivalDate) {
    return (root, query, criteriaBuilder) -> {
      Pair<Instant, Instant> dateRange = toDateRange(arrivalDate);
      return criteriaBuilder.between(root.get(Trip_.arrivalTime), dateRange.getFirst(),
          dateRange.getSecond());
    };
  }

  private static Pair<Instant, Instant> toDateRange(LocalDate localDate) {
    Instant startOfDay = localDate.atStartOfDay(ZoneId.of("UTC")).toInstant();
    Instant endOfDay = localDate.atTime(23, 59, 59).atZone(ZoneId.of("UTC")).toInstant();
    return Pair.of(startOfDay, endOfDay);
  }
}
