package org.example;

import org.example.GPA.Course;
import org.example.GPA.Courses;
import org.example.GPA.GradeCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
public class GradeCalculatorTest {
    // 학점 계산기 도메인 : 이수환 과목, 학점 계산기
    // 이수한 과목 :  객체지향 프로그래밍, 자료구조 --> 과목 (코스) 안에 클래스로 포함
    // 1. 도메인을 구성하는 객체에는 어떤 것들이 있는지 고민
    // 2. 객체들 간의 관계를 고민
    // 3. 동적인 객체를 정적인 타입으로 추상화해서 도메인 모델링 하기
    // 4. 협력을 설계
    // 5. 객체들을 포괄하는 타입에 적절한 책임을 할당
    // 6. 구현하기

    @DisplayName("평균 학점을 계산한다.")
    @Test
    void calculateGradeTest(){
        List<Course> courses = List.of(new Course("DataStructure", 3, "B+"),
                new Course("OOP", 3, "A+"));

        GradeCalculator gradeCalculator = new GradeCalculator(new Courses(courses));
        double gradeResult = gradeCalculator.calculateGrade();

        assertThat(gradeResult).isEqualTo(4.0);

    }


}
