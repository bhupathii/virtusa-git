import java.util.*;
public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Name of the Employee: ");
        String name = sc.nextLine();
        System.out.println("Enter the Basic salary: ");
        Double salary=sc.nextDouble();
        double hra = (salary*20)/100;
        double da = (salary*10)/100;
        Double grossSalary=salary+hra+da;
        System.out.println("--------Salary Details--------");
        System.out.println("Employee Name: "+name);
        System.out.println("Employee Basic Salary: "+salary);
        System.out.println("Employee HRA(20%): "+hra);
        System.out.println("Employee DA(10%): "+da);
        System.out.println("Employee Gross Salary: "+grossSalary);
        sc.close();

    }
    
}
