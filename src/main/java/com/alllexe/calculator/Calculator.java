package com.alllexe.calculator;

import com.alllexe.calculator.exception.GeneralApplicationException;
import com.alllexe.calculator.operation.OperationExecutor;

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
            result = calcOperationsResult(0f, operationExecutors).toString();
        } catch (GeneralApplicationException e) {
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public Float calcOperationsResult(Float result, List<OperationExecutor> operationExecutors) {
        for (OperationExecutor operationExecutor : operationExecutors) {
            List<OperationExecutor> subOperationExecutorList = operationExecutor.getOperationExecutorList();
            if (subOperationExecutorList.size() > 0) {
                Float subResult = calcOperationsResult(operationExecutor.getValue(), subOperationExecutorList);
                operationExecutor.setValue(subResult);
                subOperationExecutorList.clear();
            }
            result = operationExecutor.exec(result);
        }
        return result;
    }
}
