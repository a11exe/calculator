package com.alllexe.calculator.operation;

import java.util.function.BinaryOperator;

public enum Operation {
    PLUS("+", 0, Float::sum),
    MINUS("-", 0, ((a, b) -> a - b));

    private final String sign;
    private final int priority;
    private final BinaryOperator<Float> operation;

    Operation(String sign, int priority, BinaryOperator<Float> operation) {
        this.sign = sign;
        this.priority = priority;
        this.operation = operation;
    }

    public String getSign() {
        return sign;
    }

    public int getPriority() {
        return priority;
    }

    public BinaryOperator<Float> getOperation() {
        return operation;
    }
}
