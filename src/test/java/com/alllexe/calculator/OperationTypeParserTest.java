package com.alllexe.calculator;

import com.alllexe.calculator.operation.OperationType;
import com.alllexe.calculator.operation.OperationExecutor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.alllexe.calculator.operation.OperationType.PLUS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperationTypeParserTest {

    @Mock
    Validator validator;

    @Test
    void parseOperations() {
        OperationParser operationParser = new OperationParser(validator);

        Set<OperationType> operationTypes = new HashSet<>(Collections.singletonList(PLUS));
        doNothing().when(validator).validate(anyString());
        when(validator.getOperations()).thenReturn(operationTypes);
        List<OperationExecutor> operationExecutorList =
                operationParser.parseOperations("4+5");
        assertEquals(2, operationExecutorList.size());

    }
}