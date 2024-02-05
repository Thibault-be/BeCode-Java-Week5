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
  
}
