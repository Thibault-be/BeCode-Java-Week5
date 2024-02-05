package org.thibault.services;

import org.springframework.stereotype.Service;
import org.thibault.model.Doctor;
import org.thibault.repositories.DoctorsRepo;

@Service
public class DoctorService {
  
  private DoctorsRepo doctorsRepo;
  
  public DoctorService(DoctorsRepo doctorsRepo){
    this.doctorsRepo = doctorsRepo;
  }
  
  public void addDoctor(Doctor doctor){
    this.doctorsRepo.addDoctor(doctor);
  }
  
  public void updateDoctorById(int id, String firstName, String lastName, String department){
    System.out.println("in service and going to call doctorby Id in repo");
    this.doctorsRepo.updateDoctorById(id, firstName, lastName, department);
    
  }
}
