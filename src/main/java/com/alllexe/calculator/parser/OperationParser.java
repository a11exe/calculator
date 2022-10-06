package com.alllexe.calculator.parser;

import com.alllexe.calculator.operation.Operation;

public interface OperationParser {
    Operation parseOperations(String input);
}
