package com.skypro.trains.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Train {
  @Id
  private String serial;
  @Column(nullable = false)
  private int carriageNumber;
  @Column(nullable = false)
  private int capacity;


  public String getSerial() {
    return serial;
  }

  public void setSerial(String serial) {
    this.serial = serial;
  }

  public int getCarriageNumber() {
    return carriageNumber;
  }

  public void setCarriageNumber(int carriageNumber) {
    this.carriageNumber = carriageNumber;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }
}
