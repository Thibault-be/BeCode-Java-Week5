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
    this.allDoctors.add(doctor);
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
  
  public Doctor getDoctorById(int id){
    for (Doctor doctor : this.allDoctors){
      if (doctor.getId() == id) return doctor;
    }
    System.out.println("Doctor with id " + id + " not found");
    return null;
  }
  
  public void removeDoctor(int id){
    Doctor doctorToRemove = getDoctorById(id);
    this.allDoctors.remove(doctorToRemove);
  }
}
