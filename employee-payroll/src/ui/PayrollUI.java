package ui;

import model.Employee;
import model.FullTimeEmployee;
import service.PayrollService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PayrollUI {

    private PayrollService payrollService = new PayrollService();

    public PayrollUI() {

        JFrame frame = new JFrame("Employee Payroll System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        JButton btnFullTime = new JButton("Add Full-Time Employee");
        JButton btnPartTime = new JButton("Add Part-Time Employee");
        JButton btnView = new JButton("View Employees");

        btnFullTime.addActionListener(e -> openFullTimeForm());
        btnPartTime.addActionListener(e -> openPartTimeForm());
        btnView.addActionListener(e -> openEmployeeTable());

        panel.add(btnFullTime);
        panel.add(btnPartTime);
        panel.add(btnView);

        frame.add(panel);
        frame.setVisible(true);
    }

    // ---------- FULL-TIME FORM ----------
    private void openFullTimeForm() {

        JFrame formFrame = new JFrame("Add Full-Time Employee");
        formFrame.setSize(350, 300);
        formFrame.setLocationRelativeTo(null);

        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));

        JTextField txtId = new JTextField();
        JTextField txtName = new JTextField();
        JTextField txtSalary = new JTextField();
        JTextField txtTax = new JTextField();

        JButton btnSave = new JButton("Save");

        form.add(new JLabel("Employee ID:"));
        form.add(txtId);

        form.add(new JLabel("Name:"));
        form.add(txtName);

        form.add(new JLabel("Monthly Salary:"));
        form.add(txtSalary);

        form.add(new JLabel("Tax Rate (0-1):"));
        form.add(txtTax);

        form.add(new JLabel());
        form.add(btnSave);

        btnSave.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String name = txtName.getText();
                double salary = Double.parseDouble(txtSalary.getText());
                double tax = Double.parseDouble(txtTax.getText());

                if (salary <= 0 || tax < 0 || tax > 1 || name.isEmpty()) {
                    JOptionPane.showMessageDialog(formFrame,
                            "Please enter valid values.");
                    return;
                }

                payrollService.addEmployee(
                        new FullTimeEmployee(id, name, salary, tax)
                );

                JOptionPane.showMessageDialog(formFrame,
                        "Full-Time Employee saved successfully.");
                formFrame.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(formFrame,
                        "Please enter numbers correctly.");
            }
        });

        formFrame.add(form);
        formFrame.setVisible(true);
    }

    // ---------- PART-TIME FORM ----------
    private void openPartTimeForm() {

        JFrame formFrame = new JFrame("Add Part-Time Employee");
        formFrame.setSize(350, 300);
        formFrame.setLocationRelativeTo(null);

        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));

        JTextField txtId = new JTextField();
        JTextField txtName = new JTextField();
        JTextField txtHours = new JTextField();
        JTextField txtRate = new JTextField();

        JButton btnSave = new JButton("Save");

        form.add(new JLabel("Employee ID:"));
        form.add(txtId);

        form.add(new JLabel("Name:"));
        form.add(txtName);

        form.add(new JLabel("Hours Worked:"));
        form.add(txtHours);

        form.add(new JLabel("Hourly Rate:"));
        form.add(txtRate);

        form.add(new JLabel());
        form.add(btnSave);

        btnSave.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String name = txtName.getText();
                int hours = Integer.parseInt(txtHours.getText());
                double rate = Double.parseDouble(txtRate.getText());

                if (hours <= 0 || rate <= 0 || name.isEmpty()) {
                    JOptionPane.showMessageDialog(formFrame,
                            "Please enter valid values.");
                    return;
                }

                payrollService.addEmployee(
                        new model.PartTimeEmployee(id, name, hours, rate)
                );

                JOptionPane.showMessageDialog(formFrame,
                        "Part-Time Employee saved successfully.");
                formFrame.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(formFrame,
                        "Please enter numbers correctly.");
            }
        });

        formFrame.add(form);
        formFrame.setVisible(true);
    }

    // ---------- EMPLOYEE TABLE ----------
    private void openEmployeeTable() {

        JFrame tableFrame = new JFrame("Employee List");
        tableFrame.setSize(600, 400);
        tableFrame.setLocationRelativeTo(null);

        String[] columns = {"ID", "Name", "Type", "Salary"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        List<Employee> employees = payrollService.getAllEmployees();

        for (Employee e : employees) {
            String type = (e instanceof FullTimeEmployee) ? "Full-Time" : "Part-Time";
            Object[] row = {
                    e.getId(),
                    e.getName(),
                    type,
                    e.calculateSalary()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        tableFrame.add(scrollPane);
        tableFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new PayrollUI();
    }
}
