package org.thibault.services;

import java.util.*;
import org.springframework.stereotype.Service;
import org.thibault.model.*;
import org.thibault.repositories.*;
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
  
  //Provides an overview of all the monthly totals for a particular year.
  // This returns the total of each month for both import and export and then gives
  // the yearly total for both import and export.
  public Map<String, Map<String,Long>> getYearlyTotal(String year, String country, String commodity, String transportMode){
    Long importTotal = 0L;
    Long exportTotal = 0L;
    Map<String, Map<String, Long>> monthlyAndYearlyTotals = new HashMap<>();
    
    for (Months month : Months.values()){
      Map<String, Long> exportImportPerMonth = new HashMap<>();
      ArrayList<Long> monthTotal = getMonthlyTotal(month.month.toLowerCase(), year, country, commodity, transportMode);
      
      exportImportPerMonth.put("export", monthTotal.get(0));
      exportImportPerMonth.put("import", monthTotal.get(1));
      monthlyAndYearlyTotals.put(month.toString(), exportImportPerMonth);
      
      importTotal += monthTotal.get(1);
      exportTotal += monthTotal.get(0);
    }
    Map<String, Long> exportImportYearlyTotal = new HashMap<>();
    exportImportYearlyTotal.put("export", exportTotal);
    exportImportYearlyTotal.put("import", importTotal);
    monthlyAndYearlyTotals.put("Yearly totals", exportImportYearlyTotal);
    
    return monthlyAndYearlyTotals;
  }
  
  //Provides an overview of all the monthly averages for a particular year, for both import and export.
  // Then it gives the yearly average for both import and export.
  public Map<String, Map<String, Long>> getYearlyAverage( String year, String country, String commodity, String transportMode){
    
    Map<String, Map<String, Long>> monthlyTotals = new HashMap<>();
    
    Long exportTotal = 0L;
    Long importTotal = 0L;
    Long exportCount = 0L;
    Long importCount = 0L;

    for (Months month : Months.values()){
      
      Map<String, Long> exportImportTotalPerMonth = new HashMap<>();
      ArrayList<Long> monthData = getMonthlyTotal(month.month.toLowerCase(), year, country, commodity, transportMode);
      Long exportAverage = monthData.get(0) / monthData.get(2) ;
      Long importAverage = monthData.get(1) / monthData.get(3) ;
      
      exportTotal += monthData.get(0);
      importTotal += monthData.get(1);
      exportCount += monthData.get(2);
      importCount += monthData.get(3);
      exportImportTotalPerMonth.put("export", exportAverage);
      exportImportTotalPerMonth.put("import", importAverage);
      
      monthlyTotals.put(month.toString(), exportImportTotalPerMonth);
      
    }
    
    Long yearlyExportAverage = exportTotal / exportCount;
    Long yearlyImportAverage = importTotal / importCount;
    
    Map<String, Long> exportImportYearlyTotal = new HashMap<>();
    exportImportYearlyTotal.put("export", yearlyExportAverage);
    exportImportYearlyTotal.put("import", yearlyImportAverage);
    
    monthlyTotals.put("yearly averages", exportImportYearlyTotal);
    
    return monthlyTotals;
  }

//  //Returns all the unique values that span the data set:
//  // years, countries, commodities, transportation modes, and measures.
  public Map<String, HashSet<String>> getOverview(){
    
    Map<String, HashSet<String>> uniqueValues = new HashMap<>();
    
    HashSet<String> uniqueYears = new HashSet<>();
    HashSet<String> uniqueCountries = new HashSet<>();
    HashSet<String> uniqueCommodities = new HashSet<>();
    HashSet<String> uniqueTransportModes = new HashSet<>();
    HashSet<String> uniqueMeasures = new HashSet<>();

    for (TradeData td : allData.getAllData()){
      uniqueYears.add(td.getYear());
      uniqueCountries.add(td.getCountry());
      uniqueCommodities.add(td.getCommodity());
      uniqueTransportModes.add(td.getTransportMode());
      uniqueMeasures.add(td.getMeasure());
    }
    
    uniqueValues.put("years", uniqueYears);
    uniqueValues.put("countries", uniqueCountries);
    uniqueValues.put("commodities", uniqueCommodities);
    uniqueValues.put("transportation modes", uniqueTransportModes);
    uniqueValues.put("measures", uniqueMeasures);
    
    return uniqueValues;
  }
}
