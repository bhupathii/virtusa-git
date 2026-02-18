package com.mile1.service;

import com.mile1.bean.Student;
import com.mile1.exception.*;

public class StudentReport {
    public String findGrade(Student student) {
        int[] marks = student.getMarks();
        for (int mark : marks) {
            if (mark < 35) {
                return "F";
            }
        }
        
        int sum = marks[0] + marks[1] + marks[2];
        if (sum <= 150) {
            return "D";
        } else if (sum <= 200) {
            return "C";
        } else if (sum <= 250) {
            return "B";
        } else {
            return "A";
        }
    }

    public String validate(Student student) throws NullMarksArrayException, NullNameException, NullStudentException {
        if (student == null) {
            throw new NullStudentException();
        }
        if (student.getName() == null) {
            throw new NullNameException();
        }
        if (student.getMarks() == null) {
            throw new NullMarksArrayException();
        }
        
        return findGrade(student);
    }
}