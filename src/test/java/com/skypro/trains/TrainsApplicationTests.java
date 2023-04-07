package com.skypro.trains;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.skypro.trains.model.Trip;
import com.skypro.trains.repository.TripRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TrainsApplicationTests {

  @Autowired
  TripRepository tripRepository;
  @Autowired
  MockMvc mockMvc;

  @Test
  void contextLoads() {
  }

  @Test
  void dataLoads() {
    List<Trip> trips = tripRepository.findAll();
    Assertions.assertEquals(3, trips.size());
  }

  @Test
  void getAll() throws Exception {
    mockMvc.perform(get("/trip"))
        .andExpect(status().isOk());
  }

  @Test
  void getByDepartureDate() throws Exception {
    mockMvc.perform(get("/trip?departureDate=2023-04-07"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(1));
  }

  @Test
  void getByDepartureCity() throws Exception {
    mockMvc.perform(get("/trip?departureCity=New York&departureDate=2023-04-07"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(1));
  }

}
