import company.Company;
import company.Employee;
import company.hr.HR;
public class MainApp {
    public static void main(String[] args) {
        Company.showCompanyDetails();
        Company.showRevenueInsideCompany();
        Employee emp1 = new Employee(101, "Karthik");
        emp1.showEmployeeDetails();
        HR hr1 = new HR();
        hr1.showHRAccess();
        System.out.println("\n--- Access Tests ---");
        System.out.println("Company Name: " + Company.companyName);
    }
}
