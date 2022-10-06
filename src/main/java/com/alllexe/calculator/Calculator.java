package com.alllexe.calculator;

import com.alllexe.calculator.exception.GeneralApplicationException;
import com.alllexe.calculator.operation.Operation;
import com.alllexe.calculator.parser.OperationParser;

public class Calculator {

    private final OperationParser operationParser;

    public Calculator(OperationParser operationParser) {
        this.operationParser = operationParser;
    }

    public String calculate(String input) {
        String result;
        try {
            Operation operation = operationParser.parseOperations(input);
            result = operation.exec(0f).toString();
        } catch (GeneralApplicationException e) {
            result = e.getLocalizedMessage();
        }
        return result;
    }
}
