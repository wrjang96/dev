package org.example.calculate;

import org.example.Calculator;
import org.example.calculate.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CalculatorTest {
    @DisplayName("덧셈 연산을 수행한다. ")
    @ParameterizedTest
    @MethodSource("fwormulaAndResult")
    void calTest(int operand1, String operator, int operand2, int result){
//        int result = Calculator.calculate(1,"+",2);
//        assertThat(result).isEqualTo(3);
        int calculateResult = Calculator.calculate(new PositiveNumber(operand1),operator,new PositiveNumber(operand2));
        assertThat(calculateResult).isEqualTo(result);
    }
    private static Stream<Arguments> formulaAndResult(){
        return Stream.of(
                arguments(1,"+",2,3),
                arguments(1,"-",2,-1),
                arguments(1,"*",2,2),
                arguments(4,"/",2,2)
                );
    }

    @DisplayName("나눗셈에서 0을 나누는 경우 Illegal Argument 예외를 발생시킨다.")
    @Test
    void calculateExceptionTest(){
        assertThatCode(() -> Calculator.calculate(new PositiveNumber(10),"/", new PositiveNumber(0)))
                .isInstanceOf(IllegalArgumentException.class);

    }
}
