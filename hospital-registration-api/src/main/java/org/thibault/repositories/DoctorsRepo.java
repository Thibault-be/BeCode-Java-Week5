package org.thibault.repositories;

import org.thibault.model.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorsRepo {
  
  private List<Doctor> allDoctors;
  
  public DoctorsRepo(){
    this.allDoctors = new ArrayList<>();
  }
  
  public void addDoctor(Doctor doctor){
    this.allDoctors.add(doctor);
  }
  
  public List<Doctor> getAllDoctors(){
    return this.allDoctors;
  }
  
}
