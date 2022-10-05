package com.alllexe.calculator;

import com.alllexe.calculator.exception.GeneralApplicationException;
import com.alllexe.calculator.operation.OperationExecutor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator {

    private final OperationParser operationParser;

    public Calculator(OperationParser operationParser) {
        this.operationParser = operationParser;
    }

    public String calculate(String input) {
        String result;
        try {
            List<OperationExecutor> operationExecutors = operationParser.parseOperations(input);
            List<Integer> priorities = operationExecutors.stream()
                    .map(OperationExecutor::getPriority)
                    .filter(p -> p > 0)
                    .distinct()
                    .sorted((a, b) -> (b - a))
                    .collect(Collectors.toList());
            Float operationResult = 0f;
            priorities.forEach(priority -> {
                List<OperationExecutor> executors = operationExecutors.stream()
                        .filter(oe -> oe.getPriority() == priority)
                        .collect(Collectors.toList());
                executors.forEach(
                        operationExecutor -> {
                            Float opResult = operationExecutor.exec(operationExecutor.getPrev().getValue());
                            operationExecutor.getPrev().setValue(opResult);
                        }
                );
                operationExecutors.removeAll(executors);
            });
            for (OperationExecutor operationExecutor : operationExecutors) {
                operationResult = operationExecutor.exec(operationResult);
            }
            result = operationResult.toString();
        } catch (GeneralApplicationException e) {
            result = e.getLocalizedMessage();
        }
        return result;
    }

}
