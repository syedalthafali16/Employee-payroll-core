package model;

public abstract class Employee {
    protected int id;
    protected String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract double calculateSalary();

    public abstract String toFileString();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
