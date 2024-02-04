package org.thibault.services;

import org.springframework.stereotype.Service;
import org.thibault.model.Visit;
import org.thibault.repositories.VisitsRepo;

import java.util.List;

@Service
public class VisitsService {
  
  private VisitsRepo visitsRepo;
  
  public VisitsService(VisitsRepo visitsRepo){
    this.visitsRepo = visitsRepo;
  }
  
  public void registerNewVisit(Visit visit){
    this.visitsRepo.registerNewVisit(visit);
  }
  
  public List<Visit> getAllVisits(){
    return this.visitsRepo.getAllVisits();
  }
  
  
}
