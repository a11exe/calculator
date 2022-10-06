package com.alllexe.calculator;

import com.alllexe.calculator.parser.OperationParserImpl;
import com.alllexe.calculator.validator.ValidatorImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static com.alllexe.calculator.operation.OperationType.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorIntegrationTest {

    final Calculator calculator
            = new Calculator(new OperationParserImpl(new ValidatorImpl(
                    new HashSet<>(Arrays.asList(PLUS, MINUS, MULTIPLE, DIVIDE)))));

    @Test
    void whenSumAndMinusThanRightResult() {
        assertEquals("0.0", calculator.calculate("1+2-3"));
    }

    @Test
    void whenSumThanRightResult() {
        assertEquals("6.5", calculator.calculate("0.5+6"));
    }

    @Test
    void whenSumWithMinusThanRightResult() {
        assertEquals("4.5", calculator.calculate("-0.5+6-1"));
    }
    @Test
    void whenDivideAndMultipleWithMinusThanRightResult() {
        assertEquals("2.5", calculator.calculate("-0.5+6-2*3+6/2"));
    }

    @Test
    void whenSimpleParenthesesThanRightResult() {
        assertEquals("10.0", calculator.calculate("-2+(6-2)*3"));
    }
    @Test
    void whenDivideAndMultipleWithParenthesesThanRightResult() {
        assertEquals("14.5", calculator.calculate("-0.5+(6-2)*3+6/2"));
        assertEquals("9.0", calculator.calculate("2*3+6/2"));
        assertEquals("48.0", calculator.calculate("2*3*(6+2)"));
    }

    @Test
    void whenParenthesesThanRightResult() {
        assertEquals("-6.5", calculator.calculate("-0.5+(6-2*4-1)*3+6/2"));
    }
    @Test
    void whenMultipleParenthesesThanRightResult() {
        assertEquals("2.5", calculator.calculate("-0.5+(6-2*(4-1))*3+6/2"));
    }


}