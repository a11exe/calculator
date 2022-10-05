package com.alllexe.calculator;

import com.alllexe.calculator.exception.InputNotValidException;
import com.alllexe.calculator.operation.Operation;

import java.util.Set;

public class Validator {

    private final String characters = "0-9.";

    private final Set<Operation> operations;

    public Validator(Set<Operation> operations) {
        this.operations = operations;
    }

    public void isInputValid(String input) {
        StringBuilder regex = new StringBuilder("[" + characters);
        for (Operation operation : operations) {
            regex.append('\\').append(operation.getSign());
        }
        regex.append("]+");
        if (!input.matches(regex.toString())) {
            throw new InputNotValidException("String contains illegal characters");
        }
    }

    public Set<Operation> getOperations() {
        return operations;
    }
}
