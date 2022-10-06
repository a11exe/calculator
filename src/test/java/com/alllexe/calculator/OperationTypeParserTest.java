package com.alllexe.calculator;

import com.alllexe.calculator.operation.OperationType;
import com.alllexe.calculator.operation.Operation;
import com.alllexe.calculator.parser.OperationParserImpl;
import com.alllexe.calculator.validator.ValidatorImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.alllexe.calculator.operation.OperationType.PLUS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperationTypeParserTest {

    @Mock
    ValidatorImpl validatorImpl;

    @Test
    void parseOperations() {
        OperationParserImpl operationParserImpl = new OperationParserImpl(validatorImpl);

        Set<OperationType> operationTypes = new HashSet<>(Collections.singletonList(PLUS));
        doNothing().when(validatorImpl).validate(anyString());
        when(validatorImpl.getOperations()).thenReturn(operationTypes);
        Operation operation =
                operationParserImpl.parseOperations("4+5");
        assertEquals(3, operation.getOperations().size());

    }
}