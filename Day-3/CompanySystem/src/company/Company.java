package company;

public class Company {

    public static String companyName;
    protected static int totalEmployees;
    static String location;
    private static double revenue;

    static {
        companyName = "XYZ";
        totalEmployees = 250;
        location = "Bangalore";
        revenue = 10000000.00;

        System.out.println("Static Block: Company Initialized!");
    }

    public static void showCompanyDetails() {
        System.out.println("\n--- Company Details ---");
        System.out.println("Company Name: " + companyName);
        System.out.println("Total Employees: " + totalEmployees);
        System.out.println("Location: " + location);
        System.out.println("Revenue: " + revenue);
    }

    protected static int getTotalEmployees() {
        return totalEmployees;
    }

    private static double getRevenue() {
        return revenue;
    }

    public static void showRevenueInsideCompany() {
        System.out.println("Revenue (inside Company): " + getRevenue());
    }
}
