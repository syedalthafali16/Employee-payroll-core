import model.FullTimeEmployee;
import model.PartTimeEmployee;
import service.PayrollService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        PayrollService payrollService = new PayrollService();
        Scanner scanner = new Scanner(System.in);

        int choice = 0;

        do {
            try {
                System.out.println("\n===== EMPLOYEE PAYROLL SYSTEM =====");
                System.out.println("1. Add Full-Time Employee");
                System.out.println("2. Add Part-Time Employee");
                System.out.println("3. View All Employees");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                choice = scanner.nextInt();

                switch (choice) {

                    case 1:
                        System.out.print("Enter ID: ");
                        int ftId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter Name: ");
                        String ftName = scanner.nextLine();

                        System.out.print("Enter Monthly Salary: ");
                        double salary = scanner.nextDouble();
                        if (salary <= 0) {
                            System.out.println("Salary must be greater than 0.");
                            continue;
                        }

                        System.out.print("Enter Tax Rate (e.g. 0.1): ");
                        double tax = scanner.nextDouble();
                        if (tax < 0 || tax > 1) {
                            System.out.println("Tax rate must be between 0 and 1.");
                            continue;
                        }

                        payrollService.addEmployee(
                                new FullTimeEmployee(ftId, ftName, salary, tax)
                        );
                        break;

                    case 2:
                        System.out.print("Enter ID: ");
                        int ptId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter Name: ");
                        String ptName = scanner.nextLine();

                        System.out.print("Enter Hours Worked: ");
                        int hours = scanner.nextInt();
                        if (hours <= 0) {
                            System.out.println("Hours must be greater than 0.");
                            continue;
                        }

                        System.out.print("Enter Hourly Rate: ");
                        double rate = scanner.nextDouble();
                        if (rate <= 0) {
                            System.out.println("Hourly rate must be greater than 0.");
                            continue;
                        }

                        payrollService.addEmployee(
                                new PartTimeEmployee(ptId, ptName, hours, rate)
                        );
                        break;

                    case 3:
                        payrollService.showAllEmployees();
                        break;

                    case 4:
                        System.out.println("Exiting system. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter 1–4.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numbers only.");
                scanner.nextLine(); // clear wrong input
            }

        } while (choice != 4);

        scanner.close();
    }
}
