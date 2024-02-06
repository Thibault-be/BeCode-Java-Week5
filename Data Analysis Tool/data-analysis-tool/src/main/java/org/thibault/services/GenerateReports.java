package org.thibault.services;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.stereotype.Service;
import org.thibault.model.*;
import org.thibault.repositories.*;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class GenerateReports {
  
  private AllData allData;
  
  public GenerateReports(AllData allData){
    this.allData = allData;
  }
  
  //Returns the sum of both the export and import for a specified month of a specified year.
  public ArrayList<Long> getMonthlyTotal(String month,
                                         String year,
                                         String country,
                                         String commodity,
                                         String transportMode){
    ArrayList<Long> exportImportTotal = new ArrayList<>();
    long exportSum = 0;
    long importSum = 0;
    long exportCount = 0;
    long importCount = 0;
    Pattern countryPattern = Pattern.compile(country);
    Pattern commodityPattern = Pattern.compile(commodity);
    Pattern transportModePattern = Pattern.compile(transportMode);
    for (TradeData td : allData.getAllData()){
      
      Matcher countryMatcher = countryPattern.matcher(td.getCountry());
      Matcher commodityMatcher = commodityPattern.matcher(td.getCommodity());
      Matcher transportModeMatcher = transportModePattern.matcher(td.getTransportMode());
      
      if (td.getMonth().toLowerCase().equals(month) &&
          td.getYear().equals(year) &&
          countryMatcher.matches() &&
          commodityMatcher.matches() &&
          transportModeMatcher.matches() &&
          td.getMeasure().equals("$")){
          
        if (td.getDirection().equals("Exports")) {
          exportSum += td.getValue();
          exportCount++;
        }
        if (td.getDirection().equals("Imports")) {
          importSum += td.getValue();
          importCount++;
        }
      }
    }
    exportImportTotal.add(exportSum);
    exportImportTotal.add(importSum);
    exportImportTotal.add(exportCount);
    exportImportTotal.add(importCount);
    return exportImportTotal;
  }
  
  //Returns the average of both the export and import of a specified month of a specified year.
    public ArrayList<Long> getMonthlyAverage(String month,
                                             String year,
                                             String country,
                                             String commodity,
                                             String transportMode){
    ArrayList<Long> exportImportTotal = getMonthlyTotal(month, year, country, commodity, transportMode);
    long exportTotal = exportImportTotal.get(0);
    long importTotal = exportImportTotal.get(1);
    long exportCount = exportImportTotal.get(2);
    long importCount = exportImportTotal.get(3);
    
    ArrayList<Long> exportImportAverage = new ArrayList<>();
    long exportAverage = exportTotal/ exportCount;
    long importAverage = importTotal / importCount;
    
    exportImportAverage.add(exportAverage);
    exportImportAverage.add(importAverage);
    
    return exportImportAverage;
    
  }
//
//
//  //Provides an overview of all the monthly totals for a particular year.
//  // This command returns the total of each month for both import and export and then gives
//  // the yearly total for both import and export.
//  public void getYearlyTotal(String year){
//
//    long importTotal = 0;
//    long exportTotal = 0;
//    for (Months month : Months.values()){
//      ArrayList<Long> monthTotal = getMonthlyTotal(month.month.toLowerCase(), year);
//      importTotal += monthTotal.get(1);
//      exportTotal += monthTotal.get(0);
//    }
//    System.out.println("The total export value for " + year + " amounted to " + exportTotal /1000000 + " mln USD.");
//    System.out.println("The total import value for " + year + " amounted to " + importTotal /1000000 + " mln USD.");
//  }
//
//  //Provides an overview of all the monthly averages for a particular year, for both import and export.
//  // Then it gives the yearly average for both import and export.
//  public void getYearlyAverage( String year){
//
//    long yearTotalExportValue = 0;
//    long yearTotalImportValue = 0;
//    long exportCount = 0;
//    long importCount = 0;
//
//    for (Months month : Months.values()){
//      ArrayList<Long> monthData = getMonthlyAverage(month.month.toLowerCase(), year);
//      yearTotalExportValue += monthData.get(0);
//      yearTotalImportValue += monthData.get(3);
//      exportCount += monthData.get(2);
//      importCount += monthData.get(5);
//    }
//
//    System.out.println("The yearly average for " + year + " of exports is " + yearTotalExportValue / exportCount /1000000 + " mln USD.");
//    System.out.println("The yearly average for " + year + " of imports is " + yearTotalImportValue / importCount /1000000 + " mln USD.");
//  }
//
//  //Returns all the unique values that span the data set:
//  // years, countries, commodities, transportation modes, and measures.
//  public void getOverview(){
//    HashSet<String> uniqueYears = new HashSet<>();
//    HashSet<String> uniqueCountries = new HashSet<>();
//    HashSet<String> uniqueCommodities = new HashSet<>();
//    HashSet<String> uniqueTransportModes = new HashSet<>();
//    HashSet<String> uniqueMeasures = new HashSet<>();
//
//    for (TradeData td : allData.getAllData()){
//      uniqueYears.add(td.getYear());
//      uniqueCountries.add(td.getCountry());
//      uniqueCommodities.add(td.getCommodity());
//      uniqueTransportModes.add(td.getTransportMode());
//      uniqueMeasures.add(td.getMeasure());
//    }
//
//    printUniqueValues(uniqueYears, "Years");
//    printUniqueValues(uniqueCountries, "Countries");
//    printUniqueValues(uniqueCommodities, "Commodities");
//    printUniqueValues(uniqueTransportModes, "Transport modes");
//    printUniqueValues(uniqueMeasures, "Measures");
//  }
//
//  public static void printUniqueValues(HashSet<String> hs, String label){
//    StringBuilder bob = new StringBuilder();
//
//    System.out.print(label + " > ");
//    for (String value : hs){
//      bob.append(value);
//      bob.append(" - ");
//    }
//    bob.deleteCharAt(bob.length()-1);
//    bob.deleteCharAt(bob.length()-1);
//    System.out.println(bob);
//  }
}
