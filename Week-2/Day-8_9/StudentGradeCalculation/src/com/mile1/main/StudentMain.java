package com.mile1.main;

import com.mile1.bean.Student;
import com.mile1.exception.*;
import com.mile1.service.*;

public class StudentMain {

    static Student data[] = new Student[4];

    static {
        data[0] = new Student("Sekar", new int[]{35, 35, 35});
        data[1] = new Student(null, new int[]{11, 22, 33});
        data[2] = null;
        data[3] = new Student("Manoj", null);
    }

    public static void main(String[] args) {

        StudentReport studentReport = new StudentReport();
        StudentService studentService = new StudentService();

        System.out.println("Grades Calculation:");

        for (Student student : data) {
            try {
                String grade = studentReport.validate(student);
                System.out.println("GRADE=" + grade);
            } catch (NullNameException | NullMarksArrayException | NullStudentException e) {
                System.out.println("GRADE=" + e);
            }
        }

        int nullMarksCount = studentService.findNumberOfNullMarks(data);
        int nullNamesCount = studentService.findNumberOfNullNames(data);
        int nullObjectsCount = studentService.findNumberOfNullObjects(data);

        System.out.println("\nNumber of Objects with Marks array as null = " + nullMarksCount);
        System.out.println("Number of Objects with Name as null = " + nullNamesCount);
        System.out.println("Number of Objects that are entirely null = " + nullObjectsCount);
    }
}