package com.alllexe.calculator;

import com.alllexe.calculator.operation.OperationExecutor;
import com.alllexe.calculator.operation.OperationType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculatorTest {

    @Mock
    OperationParser operationParser;

    @Test
    void whenCalculateAllOperationTypesWithParentheses() {
        Calculator calculator = new Calculator(operationParser);
        List<OperationExecutor> operationExecutorList = new ArrayList<>();
        OperationExecutor o1 = new OperationExecutor(OperationType.MINUS, 0.5f);
        OperationExecutor o2 = new OperationExecutor(OperationType.PLUS, 6f);
        OperationExecutor o21 = new OperationExecutor(OperationType.MINUS, 2f);
        o2.getOperationExecutorList().add(o21);
        OperationExecutor o211 = new OperationExecutor(OperationType.MULTIPLE, 3f);
        OperationExecutor o2111 = new OperationExecutor(OperationType.MINUS, 1f);
        o21.getOperationExecutorList().add(o211);
        o211.getOperationExecutorList().add(o2111);
        OperationExecutor o22 = new OperationExecutor(OperationType.MULTIPLE, 3f);
        o2.getOperationExecutorList().add(o22);

        operationExecutorList.addAll(Arrays.asList(o1, o2));
        when(operationParser.parseOperations(anyString())).thenReturn(operationExecutorList);
        assertEquals("5.5", calculator.calculate("-0.5+(6-2*(3-1))*3"));
    }

    @Test
    void whenCalculateAllOperationTypesWithoutParentheses() {
        Calculator calculator = new Calculator(operationParser);
        List<OperationExecutor> operationExecutorList = new ArrayList<>();
        OperationExecutor o1 = new OperationExecutor(OperationType.MINUS, 0.5f);
        OperationExecutor o2 = new OperationExecutor(OperationType.PLUS, 2f);
        OperationExecutor o21 = new OperationExecutor(OperationType.MULTIPLE, 4f);
        o2.getOperationExecutorList().add(o21);
        OperationExecutor o3 = new OperationExecutor(OperationType.MINUS, 1f);
        OperationExecutor o4 = new OperationExecutor(OperationType.PLUS, 8f);
        OperationExecutor o41 = new OperationExecutor(OperationType.DIVIDE, 2f);
        o4.getOperationExecutorList().add(o41);
        operationExecutorList.addAll(Arrays.asList(o1, o2, o3, o4));
        when(operationParser.parseOperations(anyString())).thenReturn(operationExecutorList);
        assertEquals("10.5", calculator.calculate("-0.5+2*4-1+8/2"));
    }
}