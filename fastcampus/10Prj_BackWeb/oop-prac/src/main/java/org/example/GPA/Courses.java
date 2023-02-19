package org.example.GPA;

import org.example.GPA.Course;

import java.util.List;

public class Courses {
    private final List<Course> courses;

    public Courses(List<Course> courses){
        this.courses = courses;
    }

    public double multipliedCreditAndCourseGrade() {
//        double multipliedCreditAndCourseGrade = 0;
//        for (Course course : courses){
//            // * 기존 방식
//            // multipliedCreditAndCourseGrade += course.getCredit() * course.getGradeToNumber();
//            // 가져와서 계산을 하기 때문에 여러군데에서 사용해야 하기 떄문에 응집도가 약함.
//            // 만약 course에서 수행할 경우에는 한 부분만 수정하면 되기 때문에 응집도가 높아짐
//            // multipliedCreditAndCourseGrade += course.multipliedCreditAndCourseGrade();
//        }
//        return multipliedCreditAndCourseGrade;
        return courses.stream()
                .mapToDouble(Course::multipliedCreditAndCourseGrade)
                .sum();
    }

    public int calculateTotalCompleteCredit() {
        return courses.stream()
                .mapToInt(course -> course.getCredit())
                .sum();

    }
}
