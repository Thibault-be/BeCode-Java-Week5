package org.thibault.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.Date;

public class Visit {
  
  private Visitor visitor;
  private Doctor doctor;
  private Timestamp timestamp;
  
  @JsonCreator
  public Visit( @JsonProperty("visitor") Visitor visitor,
                @JsonProperty("doctor") Doctor doctor,
                @JsonProperty("timestamp") Timestamp timestamp) {
    this.visitor = visitor;
    this.doctor = doctor;
    this.timestamp = timestamp;
  }
}
