package org.thibault.repositories;

import org.springframework.stereotype.Repository;
import org.thibault.model.Doctor;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorsRepo {
  
  private List<Doctor> allDoctors;
  
  public DoctorsRepo(){
    this.allDoctors = new ArrayList<>();
  }
  
  public void addDoctor(Doctor doctor){
    System.out.println("about to add doctor " + doctor.getFirstName());
    this.allDoctors.add(doctor);
    System.out.println("I have added the doctor.");
  }
  
  public List<Doctor> getAllDoctors(){
    return this.allDoctors;
  }
  
  public void updateDoctorById(int id, String firstName, String lastName, String department){
    for (Doctor doctor : this.allDoctors){
      if (doctor.getId() == id){
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        doctor.setDepartment(department);
        return;
      }
    }
    System.out.println("Doctor not found");
  }
}
