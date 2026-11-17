import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A console application for managing employee records.
 */
public class EmployeeManagement {
    private static final List<Employee> employees = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Employee Management ===");
            System.out.println("1. Add employee");
            System.out.println("2. Display all employees");
            System.out.println("3. Search employee by ID");
            System.out.println("4. Update employee details");
            System.out.println("5. Delete employee");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    addEmployee();
                    break;
                case "2":
                    displayEmployees();
                    break;
                case "3":
                    searchEmployee();
                    break;
                case "4":
                    updateEmployee();
                    break;
                case "5":
                    deleteEmployee();
                    break;
                case "6":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose a number from 1 to 6.");
            }
        }

        scanner.close();
    }

    private static void addEmployee() {
        System.out.print("Employee ID: ");
        String id = readRequiredText();

        if (findEmployeeById(id) != null) {
            System.out.println("An employee with that ID already exists.");
            return;
        }

        System.out.print("Name: ");
        String name = readRequiredText();
        System.out.print("Department: ");
        String department = readRequiredText();
        double salary = readSalary();

        employees.add(new Employee(id, name, department, salary));
        System.out.println("Employee added successfully.");
    }

    private static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees have been added yet.");
            return;
        }

        System.out.println("\nEmployee details:");
        for (Employee employee : employees) {
            printEmployee(employee);
        }
    }

    private static void searchEmployee() {
        System.out.print("Enter employee ID to search: ");
        String id = readRequiredText();
        Employee employee = findEmployeeById(id);

        if (employee == null) {
            System.out.println("No employee found with ID " + id + ".");
        } else {
            printEmployee(employee);
        }
    }

    private static void updateEmployee() {
        System.out.print("Enter the ID of the employee to update: ");
        String id = readRequiredText();
        Employee employee = findEmployeeById(id);

        if (employee == null) {
            System.out.println("No employee found with ID " + id + ".");
            return;
        }

        System.out.println("Enter the updated details:");
        System.out.print("Name: ");
        employee.setName(readRequiredText());
        System.out.print("Department: ");
        employee.setDepartment(readRequiredText());
        employee.setSalary(readSalary());
        System.out.println("Employee details updated successfully.");
    }

    private static void deleteEmployee() {
        System.out.print("Enter the ID of the employee to delete: ");
        String id = readRequiredText();
        Employee employee = findEmployeeById(id);

        if (employee == null) {
            System.out.println("No employee found with ID " + id + ".");
            return;
        }

        employees.remove(employee);
        System.out.println("Employee deleted successfully.");
    }

    private static Employee findEmployeeById(String id) {
        for (Employee employee : employees) {
            if (employee.getId().equalsIgnoreCase(id)) {
                return employee;
            }
        }
        return null;
    }

    private static String readRequiredText() {
        while (true) {
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.print("This field cannot be empty. Please try again: ");
        }
    }

    private static double readSalary() {
        while (true) {
            System.out.print("Salary: ");
            String input = scanner.nextLine().trim();
            try {
                double salary = Double.parseDouble(input);
                if (Double.isFinite(salary) && salary >= 0) {
                    return salary;
                }
            } catch (NumberFormatException ignored) {
                // Prompt again below when the input is not a valid number.
            }
            System.out.println("Please enter a valid, non-negative salary.");
        }
    }

    private static void printEmployee(Employee employee) {
        System.out.printf("ID: %s | Name: %s | Department: %s | Salary: %.2f%n",
                employee.getId(), employee.getName(), employee.getDepartment(), employee.getSalary());
    }

    private static class Employee {
        private final String id;
        private String name;
        private String department;
        private double salary;

        Employee(String id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        String getId() {
            return id;
        }

        String getName() {
            return name;
        }

        void setName(String name) {
            this.name = name;
        }

        String getDepartment() {
            return department;
        }

        void setDepartment(String department) {
            this.department = department;
        }

        double getSalary() {
            return salary;
        }

        void setSalary(double salary) {
            this.salary = salary;
        }
    }
}
