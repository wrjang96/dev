package org.example.calculate;

import org.example.calculate.NewArithmeticOperator;

public class AdditionOperator implements NewArithmeticOperator {
    @Override
    public boolean supports(String operator){
        return "+".equals(operator);
    }

    @Override
    public int calculate(PositiveNumber operand1, PositiveNumber operand2){
        return operand2.toInt() + operand1.toInt();
    }

}
