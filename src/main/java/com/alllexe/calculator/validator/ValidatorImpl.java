package com.alllexe.calculator.validator;

import com.alllexe.calculator.exception.InputNotValidException;
import com.alllexe.calculator.operation.OperationType;

import java.util.Set;

public class ValidatorImpl implements Validator {

    private final String characters = "0-9.()";

    private final Set<OperationType> operationTypes;

    public ValidatorImpl(Set<OperationType> operationTypes) {
        this.operationTypes = operationTypes;
    }

    @Override
    public void validate(String input) {
        if (input.isBlank()) {
            throw new InputNotValidException("String is empty");
        }
        if (input.contains("()")) {
            throw new InputNotValidException("String contains empty parentheses");
        }
        if (input.contains(")(")) {
            throw new InputNotValidException("String contains empty parentheses");
        }
        String start = input.substring(0, 1);
        if (!start.matches("[0-9-(]")) {
            throw new InputNotValidException("String starts from wrong character");
        }
        String end = input.substring(input.length()-1);
        if (!end.matches("[0-9)]")) {
            throw new InputNotValidException("String ends with wrong character");
        }

        StringBuilder regex = new StringBuilder("[" + characters);
        for (OperationType operationType : operationTypes) {
            regex.append('\\').append(operationType.getSign());
        }
        regex.append("]+");
        if (!input.matches(regex.toString())) {
            throw new InputNotValidException("String contains illegal characters");
        }
        int parenthesesCounter = 0;
        for (int i = 0; i < input.length(); i++) {
            String substring = input.substring(i, i + 1);
            if ("(".equals(substring)) {
                parenthesesCounter++;
            }
            if (")".equals(substring)) {
                parenthesesCounter--;
            }
            if (parenthesesCounter < 0) {
                throw new InputNotValidException("String contains wrong parentheses");
            }
        }
        if (parenthesesCounter != 0) {
            throw new InputNotValidException("String contains wrong parentheses");
        }
    }

    @Override
    public Set<OperationType> getOperations() {
        return operationTypes;
    }
}
