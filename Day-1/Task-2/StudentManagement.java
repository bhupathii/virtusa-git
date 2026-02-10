class Student{
    int id;
    String name;
    Double marks;
    Student(int id,String name,Double marks){
        this.id=id;
        this.name=name;
        this.marks=marks;
    }
    String Grade(){
        if (marks>=90) return "A+";
        else if (marks>=80) return "A";
        else if (marks>=70) return "B";
        else if (marks>60) return "C";
        else if (marks>50) return "D";
        else return "Fail";
        }
    void Display(){
    System.out.println("-----Student Details------");
    System.out.println("Student ID: "+id);
    System.out.println("Student Name: "+name);
    System.out.println("Student Mark: "+marks);
    System.out.println("Student Grade: "+Grade());
    }
}
public class StudentManagement {
    public static void main(String[] args) {
        Student s1=new Student(21, "Rahul", 87.0);
        Student s2=new Student(22, "Vivek", 77.0);
        Student s3=new Student(23, "Kiran", 93.0);
        s1.Display();
        s2.Display();
        s3.Display();
    }
}
