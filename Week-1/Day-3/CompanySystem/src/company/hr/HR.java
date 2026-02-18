package company.hr;
import company.Company;
public class HR extends Company {
    public void showHRAccess() {
        System.out.println("\n--- HR Access Report ---");
        System.out.println("Company Name: " + companyName);
        System.out.println("Total Employees: " + totalEmployees);
    }
}
