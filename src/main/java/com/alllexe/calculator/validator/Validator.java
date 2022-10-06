package com.alllexe.calculator.validator;

import com.alllexe.calculator.operation.OperationType;

import java.util.Set;

public interface Validator {

    void validate(String input);

    Set<OperationType> getOperations();

}
