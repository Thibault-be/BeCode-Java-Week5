package org.thibault.repositories;

import org.thibault.model.Visit;

import java.util.ArrayList;
import java.util.List;

public class VisitsRepo {
  
  private List<Visit> allVisits;
  
  public VisitsRepo(){
    this.allVisits = new ArrayList<>();
  }
  
  public void addVisit(Visit visit){
    this.allVisits.add(visit);
  }
  
  public List<Visit> getAllVisits(){
    return this.allVisits;
  }
  
}
