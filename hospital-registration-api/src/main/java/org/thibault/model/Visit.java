package org.thibault.model;

import java.sql.Timestamp;
import java.util.Date;

public class Visit {
  
  private Visitor visitor;
  private Doctor doctor;
  private Timestamp timestamp;
  
  public Visit(Visitor visitor, Doctor doctor) {
    this.visitor = visitor;
    this.doctor = doctor;
    Date date = new Date();
    this.timestamp = new Timestamp(date.getTime());
  }
}
