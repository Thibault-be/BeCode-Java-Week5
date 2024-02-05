package org.thibault;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Doctor {
  
  private String firstName;
  private String lastName;
  private String department;
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
  
  public void setFirstName(String firstName){
    this.firstName = firstName;
  }
  
  public void setLastName(String lastName){
    this.lastName = lastName;
  }
  
  public void setDepartment(String department){
    this.department = department;
  }
  
}