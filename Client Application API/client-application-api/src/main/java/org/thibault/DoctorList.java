package org.thibault;

import java.util.ArrayList;
import java.util.List;

public class DoctorList {
  
  private List<Doctor> doctors;
  
  public DoctorList(){
    this.doctors = new ArrayList<>();
  }
  
  public void add(Doctor doctor){
    this.doctors.add(doctor);
  }
  
  public List<Doctor> getAllDoctors(){
    return this.doctors;
  }
  
}
