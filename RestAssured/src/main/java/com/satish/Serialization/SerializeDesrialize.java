package com.satish.Serialization;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;

public class SerializeDesrialize {

        public static void main(String[] args) {
            try {
                // Create Address object
                AddressPOJO addr = new AddressPOJO();
                addr.setCity("Bhubaneswar");
                addr.setState("Odisha");
                addr.setCountry("India");

                // Create Employee object
                EmployeePOJO emp = new EmployeePOJO();
                emp.setId(101);
                emp.setName("Satish");
                emp.setRole("QA Automation Engineer");
                emp.setAddress(addr);
                emp.setSkills(Arrays.asList("Java", "Selenium", "Cucumber", "TestNG"));

                // Create ObjectMapper instance
                ObjectMapper mapper = new ObjectMapper();

                // Serialize POJO to JSON string
                //String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp);
                String jsonString = mapper.writeValueAsString(emp);

                // Print JSON string
                System.out.println("Serialized JSON:\n" + jsonString);

                System.out.println("**************************");
                System.out.println("D E S E R I A L I Z E");
                System.out.println("**************************");

                // De-Serialize POJO to JSON string
                EmployeePOJO empOutput = mapper.readValue(jsonString, EmployeePOJO.class);
                System.out.println(empOutput.getId());
                System.out.println(empOutput.getName());
                System.out.println(empOutput.getAddress().getState());
                System.out.println(empOutput.getSkills().get(2));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
