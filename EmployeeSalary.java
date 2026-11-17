/**
 * Calculates and displays an employee's salary breakdown.
 */
public class EmployeeSalary {
    private static final double HRA_RATE = 0.20;
    private static final double DA_RATE = 0.10;
    private static final double PF_RATE = 0.12;

    private final String employeeName;
    private final double basicSalary;

    public EmployeeSalary(String employeeName, double basicSalary) {
        this.employeeName = employeeName;
        this.basicSalary = basicSalary;
    }

    private double calculateHra() {
        return basicSalary * HRA_RATE;
    }

    private double calculateDa() {
        return basicSalary * DA_RATE;
    }

    private double calculatePf() {
        return basicSalary * PF_RATE;
    }

    private double calculateTotalSalary() {
        return basicSalary + calculateHra() + calculateDa();
    }

    private double calculateNetSalary() {
        return calculateTotalSalary() - calculatePf();
    }

    private void displaySalaryDetails() {
        System.out.println("Employee: " + employeeName);
        System.out.println("Total Salary: " + calculateTotalSalary());
        System.out.println("Net Salary: " + calculateNetSalary());
    }

    public static void main(String[] args) {
        EmployeeSalary employee = new EmployeeSalary("Rahul", 30000);
        employee.displaySalaryDetails();
    }
}
