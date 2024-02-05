package org.thibault.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thibault.model.Doctor;
import org.thibault.services.DoctorService;

@RestController
@RequestMapping
public class DoctorController {
  
  private DoctorService doctorService;
  
  public DoctorController(DoctorService doctorService){
    this.doctorService = doctorService;
  }

  @PostMapping ("/api/doctors")
  public String addDoctor(@RequestBody Doctor doctor){
    this.doctorService.addDoctor(doctor);
    return "Doctor added successfully.";
  }
  
  
}



//Manage Doctors
//
//URL: POST /api/doctors
//Request Body: JSON object with the following fields:
//name (String): Name of the doctor.
//specialization (String): Specialization of the doctor.
//Response: 201 Created on successful addition, along with the doctor's unique id.
//Description: This endpoint allows hospital management to add a new doctor along with their specialization
