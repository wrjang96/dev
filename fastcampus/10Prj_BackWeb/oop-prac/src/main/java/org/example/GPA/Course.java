package org.example.GPA;

public class Course {
    private final String subject;
    private final int credit;
    private final String grade;
    public Course(String subject , int credit , String grade) {
        this.subject = subject;
        this.credit = credit;
        this.grade = grade;
    }

    public int getCredit() {
        return credit;
    }


    public double getGradeToNumber() {
        double gradeInt = 0;
        switch (this.grade) {
            case "A+":
                gradeInt = 4.5;
                break;
            case "A":
                gradeInt = 4.0;
                break;
            case "B+":
                gradeInt = 3.5;
                break;
            case "B":
                gradeInt = 3.0;
                break;
            case "C+":
                gradeInt = 2.5;
                break;
            case "C":
                gradeInt = 2.0;
                break;
            case "D+":
                gradeInt = 1.5;
                break;
            case "D":
                gradeInt = 1.0;
                break;
            case "F":
                gradeInt = 0.0;
                break;
        }
        return gradeInt;
    }

    public double multipliedCreditAndCourseGrade() {
        return credit * getGradeToNumber();
    }
}
