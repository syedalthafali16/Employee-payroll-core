package model;

public class FullTimeEmployee extends Employee {

    private double monthlySalary;
    private double taxRate;

    public FullTimeEmployee(int id, String name, double monthlySalary, double taxRate) {
        super(id, name);
        this.monthlySalary = monthlySalary;
        this.taxRate = taxRate;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary - (monthlySalary * taxRate);
    }

    @Override
    public String toFileString() {
        return "FULL," + id + "," + name + "," + monthlySalary + "," + taxRate;
    }
}
