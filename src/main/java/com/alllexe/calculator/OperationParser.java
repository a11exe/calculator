package com.alllexe.calculator;

import com.alllexe.calculator.operation.OperationType;
import com.alllexe.calculator.operation.OperationExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.alllexe.calculator.operation.OperationType.MINUS;
import static com.alllexe.calculator.operation.OperationType.PLUS;

public class OperationParser {

    private final Validator validator;

    public OperationParser(Validator validator) {
        this.validator = validator;
    }

    public List<OperationExecutor> parseOperations(String input) {
        List<OperationExecutor> operationExecutorList = new ArrayList<>();
        validator.isInputValid(input);

        StringBuilder value = new StringBuilder();
        OperationType prevOperationType = null;
        OperationType operationType = null;
        OperationExecutor prevOperationExecutor = null;
        for (int i = 0; i < input.length(); i++) {
            String substring = input.substring(i, i + 1);
            if (prevOperationType == null) {
                if ("-".equals(substring)) {
                    prevOperationType = MINUS;
                    continue;
                } else {
                    prevOperationType = PLUS;
                }
            }

            operationType = getOperation(substring, validator.getOperations());
            if (operationType != null) {
                OperationExecutor operationExecutor = new OperationExecutor(
                        prevOperationType,
                        prevOperationType.getPriority(),
                        Float.parseFloat(value.toString()),
                        prevOperationExecutor);
                operationExecutorList.add(operationExecutor);
                prevOperationExecutor = operationExecutor;

                value.setLength(0);
                prevOperationType = operationType;
            } else {
                value.append(substring);
            }
        }

        operationExecutorList.add(
                new OperationExecutor(
                        prevOperationType,
                        prevOperationType.getPriority(),
                        Float.parseFloat(value.toString()),
                        prevOperationExecutor));

        return operationExecutorList;
    }

    private OperationType getOperation(String str, Set<OperationType> operationTypes) {
        return operationTypes.stream()
                .filter(o -> o.getSign().equals(str))
                .findFirst()
                .orElse(null);
    }
}
