package com.alllexe.calculator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static com.alllexe.calculator.operation.Operation.SUM;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorIntegrationTest {

    final Calculator calculator
            = new Calculator(new OperationParser(new Validator(new HashSet<>(Arrays.asList(SUM)))));

    @Test
    void whenSumThanRightResult() {
        assertEquals("6.5", calculator.calculate("0.5+6"));
    }

    @Test
    void whenSumWithMinusThanRightResult() {
        assertEquals("4.5", calculator.calculate("-0.5+6-1"));
    }

}