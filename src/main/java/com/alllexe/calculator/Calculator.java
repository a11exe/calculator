package com.alllexe.calculator;

import com.alllexe.calculator.exception.GeneralApplicationException;
import com.alllexe.calculator.operation.OperationExecutor;

import java.util.Comparator;
import java.util.List;

public class Calculator {

    private final OperationParser operationParser;

    public Calculator(OperationParser operationParser) {
        this.operationParser = operationParser;
    }

    public String calculate(String input) {
        String result;
        try {
            List<OperationExecutor> operationExecutors = operationParser.parseOperations(input);
            operationExecutors.sort(Comparator.comparingInt(OperationExecutor::getPriority));
            Float operationResult = 0f;
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
