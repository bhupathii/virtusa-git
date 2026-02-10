class Employee{
    String name;
    Employee(String name){
        this.name=name;
        }
        void work(){
            System.out.println(name+ " is working as an Employee");
        }
    }
class Developer extends Employee{
    Developer(String name){
        super(name);
    }
    @Override
    void work(){
        System.out.println(name+" is working as a developer");
    }
    void code(){
        System.out.println(name+" is writing code");
    }
}
class Tester extends Employee{
    Tester(String name){
        super(name);
    }
    @Override
    void work(){
        System.out.println(name+" is working as a tester");
    }
    void test(){
        System.out.println(name+" is testing the code");
    }
}
public class EmployeeHeirarchyDemo {
    public static void main(String[] args) {
        Developer dev=new Developer("Rahul");
        dev.work();
        dev.code();
        System.out.println();
        Tester test=new Tester("kiran");
        test.work();
        test.test();
    }
    
}
