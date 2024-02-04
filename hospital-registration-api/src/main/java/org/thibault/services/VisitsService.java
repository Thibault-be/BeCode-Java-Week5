package org.thibault.services;

import org.springframework.stereotype.Service;
import org.thibault.model.Visit;
import org.thibault.repositories.VisitsRepo;

import java.util.ArrayList;
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
  
  public List<Visit> getVisitOnDate(String day, String month, String year){
    List<Visit> allVisits = getAllVisits();
    List<Visit> filteredVisits = new ArrayList<>();
    for(Visit visit : allVisits){
      String tsString = visit.timestampString();
      String[] tsArray = tsString.split("");
      String dd = tsArray[8] + tsArray[9];
      String mm = tsArray[5] + tsArray[6];
      String yy = tsArray[0] + tsArray[1] + tsArray[2] +tsArray[3];
      if (dd.equals(day) && mm.equals(month) && yy.equals(year)){
        filteredVisits.add(visit);
      }
    }
    return filteredVisits;
    }
  }
  
  

