package com.alllexe.calculator;

import com.alllexe.calculator.operation.Operation;
import com.alllexe.calculator.operation.OperationType;
import com.alllexe.calculator.parser.OperationParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculatorTest {

    @Mock
    OperationParser operationParser;

    @Test
    void whenCalculateAllOperationTypesWithParentheses() {
        Calculator calculator = new Calculator(operationParser);
        Operation o1 = new Operation(OperationType.MINUS, 0.5f);
        Operation o2 = new Operation(OperationType.PLUS, 6f);
        Operation o21 = new Operation(OperationType.MINUS, 2f);
        o2.getOperations().add(o21);
        Operation o211 = new Operation(OperationType.MULTIPLE, 3f);
        Operation o2111 = new Operation(OperationType.MINUS, 1f);
        o21.getOperations().add(o211);
        o211.getOperations().add(o2111);
        Operation o22 = new Operation(OperationType.MULTIPLE, 3f);
        o2.getOperations().add(o22);
        Operation operation = new Operation(OperationType.PLUS, 0f);
        operation.getOperations().addAll(Arrays.asList(o1, o2));
        when(operationParser.parseOperations(anyString())).thenReturn(operation);
        assertEquals("5.5", calculator.calculate("-0.5+(6-2*(3-1))*3"));
    }

}