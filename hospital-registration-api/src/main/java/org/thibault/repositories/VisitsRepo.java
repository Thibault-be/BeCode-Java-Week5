package org.thibault.repositories;

import org.springframework.stereotype.Repository;
import org.thibault.model.Visit;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VisitsRepo {
  
  private List<Visit> allVisits;
  
  public VisitsRepo(){
    this.allVisits = new ArrayList<>();
  }
  
  public void registerNewVisit(Visit visit){
    this.allVisits.add(visit);
  }
  
  public List<Visit> getAllVisits(){
    return this.allVisits;
  }
  
}
