package org.thibault.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Doctor {
  
  private final String firstName;
  private final String lastName;
  private final String department;
  private int id;
  
  @JsonCreator
  public Doctor(      @JsonProperty("firstName") String firstName,
                      @JsonProperty("lastName") String lastName,
                      @JsonProperty("department") String department,
                      @JsonProperty("id") int id) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.department = department;
    this.id = id;
  }
  
  public void setId(int id){
    this.id = id;
  }
  
  public int getId(){
    return this.id;
  }
  
  public String getFirstName() {
    return firstName;
  }
  
  public String getLastName() {
    return lastName;
  }
  
  public String getDepartment() {
    return department;
  }
}