package com.alllexe.calculator;

import com.alllexe.calculator.exception.InputNotValidException;
import com.alllexe.calculator.operation.OperationType;

import java.util.Set;

public class Validator {

    private final String characters = "0-9.()";

    private final Set<OperationType> operationTypes;

    public Validator(Set<OperationType> operationTypes) {
        this.operationTypes = operationTypes;
    }

    public void validate(String input) {
        if (input.isBlank()) {
            throw new InputNotValidException("String is empty");
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
    }

    public Set<OperationType> getOperations() {
        return operationTypes;
    }
}
