package org.thibault.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.thibault.model.TradeData;

@Repository
public class AllData {
  
  private List<TradeData> data;
  
  public AllData(){
    this.data = new ArrayList<>();
  }
  
  public List<TradeData> getAllData(){
    return this.data;
  }
  
  public void addData(TradeData tradeData){
    this.data.add(tradeData);
  }
}
