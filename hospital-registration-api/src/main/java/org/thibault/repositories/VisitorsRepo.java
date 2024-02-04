package org.thibault.repositories;

import java.util.ArrayList;
import java.util.List;
import org.thibault.model.Visitor;

public class VisitorsRepo {
  
  private List<Visitor> allVisitors;
  
  public VisitorsRepo(){
    this.allVisitors = new ArrayList<>();
  }
  
  public void addVisitor(Visitor visitor){
    this.allVisitors.add(visitor);
  }
  
  public List<Visitor> getAllVisitors(){
    return this.allVisitors;
  }
  
  public int getSize(){
    return this.allVisitors.size();
  }
}
