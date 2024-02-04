package org.thibault.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thibault.model.Visit;
import org.thibault.services.VisitsService;

import java.util.List;

@RestController
@RequestMapping
public class VisitsController {
  
  private VisitsService visitsService;
  
  public VisitsController(VisitsService visitsService) {
    this.visitsService = visitsService;
  }
  
  @PostMapping ("api/visits")
  public ResponseEntity<String> registerNewVisit(@RequestBody Visit visit){
    this.visitsService.registerNewVisit(visit);
    return new ResponseEntity<>("Visit successfully registered", HttpStatus.CREATED);
  }
  
  @GetMapping ("api/visits")
  public List<Visit> getVisitsOnDate(
          @RequestParam String day,
          @RequestParam String month,
          @RequestParam String year
        ){
    return this.visitsService.getVisitOnDate(day, month, year);
  }
}
