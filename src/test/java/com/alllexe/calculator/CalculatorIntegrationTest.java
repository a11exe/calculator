package com.alllexe.calculator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static com.alllexe.calculator.operation.OperationType.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorIntegrationTest {

    final Calculator calculator
            = new Calculator(new OperationParser(new Validator(
                    new HashSet<>(Arrays.asList(PLUS, MINUS, MULTIPLE, DIVIDE)))));

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
    void whenDivideAndMultipleWithBracesThanRightResult() {
        assertEquals("14.5", calculator.calculate("-0.5+(6-2)*3+6/2"));
    }

    @Test
    void whenBracesThanRightResult() {
        assertEquals("-6.5", calculator.calculate("-0.5+(6-2*4-1)*3+6/2"));
    }
    @Test
    void whenMultipleBracesThanRightResult() {
        assertEquals("2.5", calculator.calculate("-0.5+(6-2*(4-1))*3+6/2"));
    }


}