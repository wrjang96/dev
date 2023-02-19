package org.example.GPA;

import org.example.GPA.Courses;

public class GradeCalculator {
    private final Courses courses;


    public GradeCalculator(Courses courses){
        this.courses = courses;
    }

    public double calculateGrade()  {
        double totalMultipliedCreditAndCourseGrade = courses.multipliedCreditAndCourseGrade();
        int totalCompletedCredit = courses.calculateTotalCompleteCredit();

        return totalMultipliedCreditAndCourseGrade / totalCompletedCredit;
    }
}
