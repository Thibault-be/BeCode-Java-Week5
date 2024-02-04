package org.thibault.model;

public class Doctor {
  
  private final String firstName;
  private final String lastName;
  private final String department;
  private int id;
  
  public Doctor(String firstName, String lastName, String department) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.department = department;
    this.id = 0;
  }
  
  public void setId(int id){
    this.id = id;
  }
  
  public int getId(){
    if (this.id == 0) return -1;
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