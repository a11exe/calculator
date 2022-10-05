package com.alllexe.calculator;

import com.alllexe.calculator.operation.Operation;
import com.alllexe.calculator.operation.OperationExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.alllexe.calculator.operation.Operation.SUM;

public class OperationParser {

    private final Validator validator;

    public OperationParser(Validator validator) {
        this.validator = validator;
    }

    public List<OperationExecutor> parseOperations(String input) {
        List<OperationExecutor> operationExecutorList = new ArrayList<>();
        validator.isInputValid(input);

        StringBuilder value = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            String substring = input.substring(i, i + 1);
            Operation operation = getOperation(substring, validator.getOperations());
            if (operation != null) {
                operationExecutorList.add(
                        new OperationExecutor(
                                operation,
                                operation.getPriority(),
                                Float.parseFloat(value.toString())));
                value.setLength(0);
            } else {
                value.append(substring);
            }
        }

        operationExecutorList.add(
                new OperationExecutor(
                        SUM,
                        SUM.getPriority(),
                        Float.parseFloat(value.toString())));

        return operationExecutorList;
    }

    private Operation getOperation(String str, Set<Operation> operations) {
       return operations.stream()
               .filter(o -> o.getSign().equals(str))
               .findFirst()
               .orElse(null);
    }
}
