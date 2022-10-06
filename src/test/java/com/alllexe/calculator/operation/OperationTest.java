package com.alllexe.calculator.operation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationTest {

    @Test
    void execSimple() {
        Operation operation = new Operation(OperationType.PLUS, 8f);
        assertEquals(12f, operation.exec(4f));
    }

    @Test
    void execWithSubOperation() {
        Operation operation = new Operation(OperationType.PLUS, 8f);
        Operation subOperation = new Operation(OperationType.MULTIPLE, 4f);
        operation.getOperations().add(subOperation);
        assertEquals(36f, operation.exec(4f));
    }
}