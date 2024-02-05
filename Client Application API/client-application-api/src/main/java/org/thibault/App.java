package org.thibault;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Scanner;

public class App
{
    public static void main( String[] args ) {
        
        DoctorList doctors = new DoctorList();
        doctors.add(new Doctor("Emma", "Johnson", "HR", 1));
        doctors.add(new Doctor("Liam", "Smith", "Marketing", 2));
        doctors.add(new Doctor("Sophia", "Williams", "IT", 3));
        doctors.add(new Doctor("Noah", "Anderson", "Finance", 4));
        doctors.add(new Doctor("Olivia", "Miller", "Operations", 5));
        doctors.add(new Doctor("Ethan", "Brown", "Sales", 6));
        doctors.add(new Doctor("Ava", "Davis", "Research", 7));
        doctors.add(new Doctor("Mason", "Moore", "Engineering", 8));
        doctors.add(new Doctor("Isabella", "Taylor", "Customer Support", 9));
        doctors.add(new Doctor("Logan", "Clark", "Quality Assurance", 10));
        
        postDoctorsFromList(doctors);
        
        Scanner scanner = new Scanner(System.in);
        
        while (true){
            System.out.println("Which method do you want to test? GET or POST?");
            System.out.print("> ");
            String input = scanner.nextLine();
            
            switch (input){
                case "get":
                    getDoctorRequest();
                    break;
                case "post":
                    postDoctorRequest();
                    break;
                case "stop":
                    return;
            }
        }
    }
    
    public static void getDoctorRequest(){
        String url = "http://localhost:8080/api";
        WebClient.Builder builder = WebClient.builder();
        String response = builder.build()
                .get()
                .uri(url + "/doctors")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(response);
    }
    
    public static void postDoctorRequest(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the doctor's first name?");
        System.out.print("> ");
        String firstName = scanner.nextLine();
        System.out.println("What is the doctor's last name?");
        System.out.print("> ");
        String lastName = scanner.nextLine();
        System.out.println("What is the doctor's department?");
        System.out.print("> ");
        String department = scanner.nextLine();
        System.out.println("What is the doctor's id?");
        System.out.print("> ");
        String id = scanner.nextLine();
        
        Doctor newDoctor = new Doctor(firstName, lastName, department, Integer.valueOf(id));
        
        String url = "http://localhost:8080/api";
        WebClient.Builder builder = WebClient.builder();
        String response = builder.build()
                .post()
                .uri(url + "/doctors")
                .body(BodyInserters.fromValue(newDoctor))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
    
    public static void postDoctorsFromList(DoctorList doctors){
        String url = "http://localhost:8080/api";
        WebClient.Builder builder = WebClient.builder();
        for (Doctor doctor : doctors.getAllDoctors()){
            String response = builder.build()
                    .post()
                    .uri(url + "/doctors")
                    .body(BodyInserters.fromValue(doctor))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        }
    }
}

