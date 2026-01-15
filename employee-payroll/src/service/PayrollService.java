package service;

import model.Employee;
import util.FileUtil;

import java.util.List;

public class PayrollService {

    private List<Employee> employees;

    public PayrollService() {
        employees = FileUtil.loadEmployees();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        FileUtil.saveEmployees(employees);
        System.out.println("Employee saved successfully.");
    }

    public void showAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        for (Employee e : employees) {
            System.out.println(
                    "ID: " + e.getId() +
                    ", Name: " + e.getName() +
                    ", Salary: " + e.calculateSalary()
            );
        }
    }
    public java.util.List<model.Employee> getAllEmployees() {
        return employees;
    }
}
