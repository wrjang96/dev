package org.example;

import org.example.GPA.Course;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class CourseTest {
    @DisplayName("과목(코스)를 생성한다.")
    @Test
    void createTest(){
        assertThatCode(() -> new Course("oop", 3, "A+"))
                .doesNotThrowAnyException();
    }
}
