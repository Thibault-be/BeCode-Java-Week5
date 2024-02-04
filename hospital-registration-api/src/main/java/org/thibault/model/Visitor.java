package org.thibault.model;

public class Visitor {
  
  private String firstName;
  private String lastName;
  private Integer id;
  
  public Visitor(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.id = 0;
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
  
  public void setId(int id){
    this.id = id;
  }
  
}
