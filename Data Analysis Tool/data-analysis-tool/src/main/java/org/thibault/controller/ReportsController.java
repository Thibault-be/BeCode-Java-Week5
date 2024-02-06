package org.thibault.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thibault.services.GenerateReports;

import java.util.ArrayList;

@RestController
@RequestMapping
public class ReportsController {
  
  private GenerateReports generateReports;
  
  @Autowired // Ensure GenerateReports is injected
  public ReportsController(GenerateReports generateReports) {
    this.generateReports = generateReports;
  }
  
  @GetMapping ("/monthly_total")
  public String getMonthlyTotal(
        @RequestParam String month,
        @RequestParam String year
      ){
    ArrayList<Long> exportImportTotal = this.generateReports.getMonthlyTotal(month, year);
    return "The total export is " + exportImportTotal.get(0);
  }
  
  
}
