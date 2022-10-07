package com.alllexe.calculator.parser;

import com.alllexe.calculator.validator.Validator;
import com.alllexe.calculator.operation.OperationType;
import com.alllexe.calculator.operation.Operation;

import java.util.*;

import static com.alllexe.calculator.operation.OperationType.PLUS;

public class OperationParserImpl implements OperationParser {

    private final Validator validator;

    public OperationParserImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public Operation parseOperations(String input) {
        validator.validate(input);
        input = removeParentheses(input);
        return parseStringWithoutParentheses(input);
    }

    private String removeParentheses(String input) {
        while (input.contains("(")) {
            input = replaceParenthesesByCalculatedResult(input);
        }
        return input;
    }

    private String replaceParenthesesByCalculatedResult(String str) {
        int lastOpen = 0;
        for (int i = 0; i < str.length(); i++) {
            String substring = str.substring(i, i + 1);
            if ("(".equals(substring)) {
                lastOpen = i;
            }
            if (")".equals(substring)) {
//                System.out.println("Extract parentheses: " + str.substring(lastOpen + 1, i));
                Operation operation = parseStringWithoutParentheses(str.substring(lastOpen + 1, i));
                Float parenthesesResult = operation.exec(0f);
                return str.substring(0, lastOpen) + parenthesesResult.toString() + str.substring(i + 1);
            }
        }
        return str;
    }

    private Operation getBlankOperation() {
        return new Operation(PLUS, 0.0f);
    }

    private void addOperation(Operation operation,
                              List<Operation> operationList,
                              Operation prevOperation) {
        if (operation.getOperationType().getPriority() > 1) {
            prevOperation.getOperations().add(operation);
        } else {
            operationList.add(operation);
        }
    }

    private Operation parseStringWithoutParentheses(String input) {
        StringBuilder value = new StringBuilder();
        OperationType operationType;
        // add first operation
        Operation operationMain = getBlankOperation();

        List<Operation> operationList = operationMain.getOperations();
        Operation prevOperation = getBlankOperation();
        OperationType prevOperationType = prevOperation.getOperationType();
        operationList.add(prevOperation);
        int valueSign = 1;
        for (int i = 0; i < input.length(); i++) {
            String substring = input.substring(i, i + 1);
            if ("-".equals(substring) && value.toString().isBlank()) {
                valueSign = -1;
                continue;
            }
            operationType = getOperationType(substring, validator.getOperations());

            if (operationType != null) {
                Operation operation = new Operation(
                        prevOperationType,
                        valueSign * getValue(value.toString()));
                addOperation(operation, operationList, prevOperation);
                valueSign = 1;
                value.setLength(0);
                prevOperationType = operationType;
                prevOperation = operation;
            } else {
                value.append(substring);
            }
        }

        if (!value.toString().isBlank()) {
            Operation operation = new Operation(
                    prevOperationType,
                    valueSign * getValue(value.toString()));
            addOperation(operation, operationList, prevOperation);
        }
        return operationMain;
    }

    private Float getValue(String value) {
        validator.validateNumber(value);
        return Float.parseFloat(value);
    }


    private OperationType getOperationType(String str, Set<OperationType> operationTypes) {
        return operationTypes.stream()
                .filter(o -> o.getSign().equals(str))
                .findFirst()
                .orElse(null);
    }
}
