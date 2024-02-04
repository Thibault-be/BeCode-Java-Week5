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
  
  public List<Visit> getVisitsFromCertainPeriod(
          String startDay, String startMonth, String startYear,
          String endDay, String endMonth, String endYear
      ){
    List<Visit> allVisits = getAllVisits();
    List<Visit> filteredVisits = new ArrayList<>();
    
    for(Visit visit : allVisits){
      String tsString = visit.timestampString();
      String[] tsArray = tsString.split("");
      int dd = Integer.valueOf( tsArray[8] + tsArray[9]);
      int mm = Integer.valueOf(tsArray[5] + tsArray[6]);
      int yy = Integer.valueOf(tsArray[0] + tsArray[1] + tsArray[2] +tsArray[3]);
      
      int dayStart = Integer.valueOf(startDay);
      int monthStart = Integer.valueOf(startMonth);
      int yearStart =  Integer.valueOf(startYear);
      int dayEnd = Integer.valueOf(endDay);
      int monthEnd = Integer.valueOf(endMonth);
      int yearEnd = Integer.valueOf(endYear);
      
      System.out.println("mm/yy: " + mm + "/" + yy);
      System.out.println("monthStart: " + monthStart);
      System.out.println("monthEnd: " + monthEnd);
      System.out.println("yearStart: " + yearStart );
      System.out.println("yearEnd: " + yearEnd);
      
      if (yy < yearStart || yy > yearEnd){
        System.out.println("year not in range");
        continue;
      }
      System.out.println("checking if year is in between");
      if (yy > yearStart && yy < yearEnd){
        System.out.println("Year is in between start and end year");
        filteredVisits.add(visit);
        continue;
      }
      System.out.println("checking if year is same but month ok");
      if(
          (yy == yearStart && mm > monthStart) ||
          (yy == yearEnd && mm < monthEnd)
      ){
        System.out.println("same year, but month is in lower of higher");
        filteredVisits.add(visit);
        continue;
      }
      System.out.println("checking if year and month same but day in between");
      if(
        (yy == yearStart && mm == monthStart && dd <= dayStart) ||
        (yy == yearEnd && mm == monthEnd && dd >= dayEnd)
      ){
        filteredVisits.add(visit);
      }
    }
    return filteredVisits;
  }
  
}