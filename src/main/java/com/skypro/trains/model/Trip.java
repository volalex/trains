package com.skypro.trains.model;

import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Trip {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Instant departureTime;
  private Instant arrivalTime;
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "departure_station_id", nullable = false)
  private Station departureStation;
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "arrival_station_id", nullable = false)
  private Station arrivalStation;
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "train_serial", nullable = false)
  private Train train;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Instant getDepartureTime() {
    return departureTime;
  }

  public void setDepartureTime(Instant departureTime) {
    this.departureTime = departureTime;
  }

  public Instant getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(Instant arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public Station getDepartureStation() {
    return departureStation;
  }

  public void setDepartureStation(Station departureStation) {
    this.departureStation = departureStation;
  }

  public Station getArrivalStation() {
    return arrivalStation;
  }

  public void setArrivalStation(Station arrivalStation) {
    this.arrivalStation = arrivalStation;
  }

  public Train getTrain() {
    return train;
  }

  public void setTrain(Train train) {
    this.train = train;
  }
}
