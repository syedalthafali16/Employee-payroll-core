package util;

import model.Employee;
import model.FullTimeEmployee;
import model.PartTimeEmployee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private static final String FILE_NAME = "employees.txt";

    // Save employees to file
    public static void saveEmployees(List<Employee> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Employee e : employees) {
                writer.write(e.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving employees.");
        }
    }

    // Load employees from file
    public static List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return employees;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue; // skip empty lines
                }

                String[] parts = line.split(",");

                if (parts.length < 5) {
                    continue; // skip invalid lines
                }

                if (parts[0].equals("FULL")) {
                    employees.add(new FullTimeEmployee(
                            Integer.parseInt(parts[1]),
                            parts[2],
                            Double.parseDouble(parts[3]),
                            Double.parseDouble(parts[4])
                    ));
                } 
                else if (parts[0].equals("PART")) {
                    employees.add(new PartTimeEmployee(
                            Integer.parseInt(parts[1]),
                            parts[2],
                            Integer.parseInt(parts[3]),
                            Double.parseDouble(parts[4])
                    ));
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading employees.");
        }

        return employees;
    }
}
