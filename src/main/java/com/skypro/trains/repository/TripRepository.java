package com.skypro.trains.repository;

import com.skypro.trains.model.Trip;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long>, JpaSpecificationExecutor<Trip> {

  @Override
  @EntityGraph(attributePaths = {"train", "arrivalStation", "departureStation"})
  List<Trip> findAll(Specification<Trip> tripSpecification);
}
