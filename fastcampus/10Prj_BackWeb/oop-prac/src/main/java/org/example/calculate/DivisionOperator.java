package org.example.calculate;

import org.example.calculate.NewArithmeticOperator;

public class DivisionOperator implements NewArithmeticOperator {
    @Override
    public boolean supports(String operator) {
        return "/".equals(operator);
    }

    @Override
    public int calculate(PositiveNumber operand1, PositiveNumber operand2) {
        if (operand2.toInt() == 0){
            throw new IllegalArgumentException("We can divide by Zero");
        }
        return operand1.toInt()/operand2.toInt();
    }
}
