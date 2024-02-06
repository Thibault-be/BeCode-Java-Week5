package org.thibault;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.thibault.repositories.*;
import org.thibault.model.*;
import org.thibault.services.*;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        
        
        
        
        AllData data = new AllData();
        Scanner scanner = new Scanner(System.in);
        
        List<String> commandOptions = new ArrayList<>();
        for (Commands command : Commands.values()) {
            commandOptions.add(command.stringCmd);
        }
        
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
                        //add all TradeData instances to AllData object
                        data.addData(newData);
                    });
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        GenerateReports reports = new GenerateReports(data);
        
        System.out.println("Welcome to the covid trade data analysis tool");
        System.out.println("Available commands are:");
        System.out.println("help - help <cmd> - monthly_total - monthly_average - yearly_total - yearly_average - overview");
        
        boolean flag = true;
        while (flag) {
            System.out.println("Please type a command. Blank command leaves the program");
            System.out.print("> ");
            String cmd = scanner.nextLine();
            flag = createCommand(cmd, commandOptions, reports);
        }
        
        SpringApplication.run(Main.class, args);
    }
    
    private static boolean createCommand(String cmd, List<String> commandOptions, GenerateReports reports) {
        
        if (cmd.isEmpty()) {
            System.out.println("exiting program.");
            return false;
        }
        
        if (!commandOptions.contains(cmd)) {
            System.out.println("***This is not a valid command.***\n");
            return true;
        }
        
        //first check if any of the help commands or overview were entered. These don't need further user input.
        switch (cmd) {
            case "help":
                Commands helpCmd = Commands.HELP;
                System.out.println("These are short descriptions of the available commands:\n");
                System.out.println("monthly_total: sum of both the export and import for a specified month of a specified year.");
                System.out.println("monthly_average: average of both the export and import of a specified month of a specified year.");
                System.out.println("yearly_total: overview of all the monthly totals for a particular year. This command returns the total of each month for both import and export and then gives the yearly total for both import and export.");
                System.out.println("yearly_average: overview of all the monthly averages for a particular year, for both import and export. Then it gives the yearly average for both import and export.");
                System.out.println("overview: all the unique values that span the data set: years, countries, commodities, transportation modes, and measures.\n");
                
                System.out.println("for more information write 'help + command'.\n");
                return true;
            case "help monthly_total":
                Commands helpMonthlyTotal = Commands.HELP_MONTHLY_TOTAL;
                System.out.println(helpMonthlyTotal.explanation);
                return true;
            case "help monthly_average":
                Commands helpMonthlyAverage = Commands.HELP_MONTHLY_AVERAGE;
                System.out.println(helpMonthlyAverage.explanation);
                return true;
            case "help yearly_total":
                Commands helpYearlyTotal = Commands.HELP_YEARLY_TOTAL;
                System.out.println(helpYearlyTotal.explanation);
                return true;
            case "help yearly_average":
                Commands helpYearlyAverage = Commands.HELP_YEARLY_AVERAGE;
                System.out.println(helpYearlyAverage.explanation);
                return true;
            case "help overview":
                Commands helpOverview = Commands.HELP_OVERVIEW;
                System.out.println(helpOverview.explanation);
                return true;
            case "overview":
                Commands overview = Commands.OVERVIEW;
                reports.getOverview();
                return true;
        }
        
        //no help command or overview was entered, so we want to generate a report with user input.
        //create scanner so user can input requested data
        Scanner scanner = new Scanner(System.in);
        String year = getYear(scanner);
        
        if (year.isEmpty()) {
            System.out.println("Query aborted. Exiting program");
            return false;
        }
        
        //Yearly reports only need a year
        switch (cmd) {
            case "yearly_total":
                Commands yearlyTotal = Commands.YEARLY_TOTAL;
                reports.getYearlyTotal(year);
                return true;
            case "yearly_average":
                Commands yearlyAverage = Commands.YEARLY_AVERAGE;
                reports.getYearlyAverage(year);
                return true;
        }
        
        //monthly reports need a year and a month
        String month = getMonth(scanner);
        
        if (month.isEmpty()) {
            System.out.println("Query aborted. Exiting program.");
            return false;
        }
        
        switch (cmd) {
            case "monthly_total":
                Commands monthlyTotal = Commands.MONTHLY_TOTAL;
                ArrayList<Long> importExportMonthly = reports.getMonthlyTotal(month, year);
                System.out.println("Exports for " + month + " of " + year + " amounted to " + importExportMonthly.get(0) / 1000000 + " mln. USD.");
                System.out.println("Imports for " + month + " of " + year + " amounted to " + importExportMonthly.get(1) / 1000000 + " mln. USD.\n");
                break;
            case "monthly_average":
                Commands monthlyAverage = Commands.MONTHLY_AVERAGE;
                reports.getMonthlyAverage(month, year);
                break;
        }
        return true;
    }
    
    private static String getMonth(Scanner scanner) {
        
        while (true) {
            System.out.println("Which month do you want to look at? Blank input aborts the query.");
            System.out.print("> ");
            String userMonth = scanner.nextLine().toLowerCase();
            
            if (userMonth.isEmpty()) return "";
            
            for (Months month : Months.values()) {
                if (month.month.toLowerCase().equals(userMonth)) {
                    return userMonth;
                }
            }
            System.out.println("That wasn't a valid month. Please try again or press enter on blank input to abort.");
        }
    }
    
    private static String getYear(Scanner scanner) {
        
        while (true) {
            
            System.out.println("Which year do you want to look at? Leave blank and press enter to abort the query.");
            System.out.print("> ");
            
            String userYear = scanner.nextLine();
            if (userYear.isEmpty()) return "";
            
            if (userYear.matches("2015|2016|2017|2018|2019|2020|2021")) {
                return userYear;
            } else {
                System.out.println("Only data for 2015 - 2016 - 2017 - 2018 - 2019 - 2020 - 2021 is available");
                System.out.println("Please try again");
            }
        }
        
        
        
    }
}


