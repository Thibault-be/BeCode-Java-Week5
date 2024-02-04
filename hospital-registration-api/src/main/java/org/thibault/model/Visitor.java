package org.thibault.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Visitor {
  
  private String firstName;
  private String lastName;
  private Integer id;
  
  @JsonCreator
  public Visitor(@JsonProperty("firstName") String firstName,
                 @JsonProperty("lastName") String lastName,
                 @JsonProperty("id") int id) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.id = id;
  }
  
  public String firstName() {
    return firstName;
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public String lastName() {
    return lastName;
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
}
