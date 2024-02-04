package org.thibault.services;

import org.thibault.model.Visitor;
import org.thibault.repositories.VisitorsRepo;

import java.util.List;

public class VisitorService {
  
  private VisitorsRepo visitorsRepo;
  
  public void addVisitor(Visitor visitor){
    this.visitorsRepo.addVisitor(visitor);
  }
  
  public List<Visitor> getAllVisitors() {
    return this.visitorsRepo.getAllVisitors();
  }
  
  public int getSize(){
    return this.visitorsRepo.getSize();
  }
  
}
