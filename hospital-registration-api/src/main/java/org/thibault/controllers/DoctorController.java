package org.thibault.controllers;

import org.springframework.web.bind.annotation.*;
import org.thibault.DTOs.UpdateDoctorObject;
import org.thibault.model.Doctor;
import org.thibault.services.DoctorService;

import java.util.List;

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
  
  @GetMapping("/api/doctors")
  public List<Doctor> getAllDoctors(){
    return this.doctorService.getAllDoctors();
  }
  
  @GetMapping("/api/doctors/{id}")
  public Doctor getDoctorById(
          @PathVariable String id
      ){
    return this.doctorService.getDoctorById(Integer.valueOf(id));
  }
  
  @DeleteMapping("/api/doctors/{id}")
  public String removeDoctor(
          @PathVariable String id
          ){
    this.doctorService.removeDoctor(Integer.valueOf(id));
    return "Doctor with id " + id + " was removed.";
  }
}


