package org.thibault;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.context.annotation.ComponentScan;
import org.thibault.repositories.*;
import org.thibault.model.*;
import org.thibault.services.*;

@SpringBootApplication
@ComponentScan (basePackages = {"org.thibault.services", "org.thibault.controller", "org.thibault.repositories"})
public class Main {
  
  private final AllData data;
  private final GenerateReports reports;
  
  @Autowired
  public Main(AllData data, GenerateReports reports) {
    this.data = data;
    this.reports = reports;
  }
  
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    
    @PostConstruct
    public void loadDataFromCSV(){
      try {
        //make each line in the csv a TradeData instance
        Files.lines(Paths.get("covid-effects.csv")).skip(1)
                .map(line -> line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")) //complex regex to allow for commas in quoted data
                .forEach(splitLine -> {
                  TradeData newData = new TradeData(
                          splitLine[0],
                          splitLine[1],
                          splitLine[2],
                          splitLine[3],
                          splitLine[4],
                          splitLine[5],
                          splitLine[6],
                          splitLine[7],
                          splitLine[8],
                          splitLine[9]
                  );
                  data.addData(newData);
                });
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
      }
    }
}


