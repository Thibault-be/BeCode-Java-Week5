package org.thibault.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thibault.services.GenerateReports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class ReportsController {
  
  private GenerateReports generateReports;
  
  @Autowired // Ensure GenerateReports is injected
  public ReportsController(GenerateReports generateReports) {
    this.generateReports = generateReports;
  }
  
  @GetMapping ("/monthly_total")
  public Map<String, Long> getMonthlyTotal(
        @RequestParam(required = true) String month,
        @RequestParam(required = true) String year,
        @RequestParam(required = false, defaultValue = ".*") String country,
        @RequestParam(required = false, defaultValue = ".*") String commodity,
        @RequestParam(required = false, defaultValue = ".*") String transportMode
      ){
    ArrayList<Long> exportImportTotal = this.generateReports.getMonthlyTotal(month, year, country, commodity, transportMode);
    Map<String, Long> result = new HashMap<>();
    result.put("export", exportImportTotal.get(0));
    result.put("import", exportImportTotal.get(1));
    return result;
  }
  
  
}
