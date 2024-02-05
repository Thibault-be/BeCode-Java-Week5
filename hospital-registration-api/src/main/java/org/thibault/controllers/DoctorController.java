package org.thibault.controllers;

import org.springframework.web.bind.annotation.*;
import org.thibault.DTOs.UpdateDoctorObject;
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
  
  @PutMapping("/api/doctors/{id}")
  public String updateDoctor(
          @PathVariable String id,
          @RequestBody UpdateDoctorObject updateDoctorObject
          ){
    System.out.println("in controller and going to call service doctorbyId");
    this.doctorService.updateDoctorById(Integer.parseInt(id),
                        updateDoctorObject.getFirstName(),
                        updateDoctorObject.getLastName(),
                        updateDoctorObject.getDepartment());
    return "Doctor updated successfully.";
  }
}

//Update a Doctor
//
//URL: PUT /api/doctors/{id}
//Path Parameters:
//id (Integer): ID of the doctor to update.
//Request Body: JSON object with the following fields:
//name (String): Updated name of the doctor.
//specialization (String): Updated specialization of the doctor.
//        Response: 200 OK on successful update.
//Description: Use this endpoint to update the details of a specific doctor.