package com.alllexe.calculator.operation;

public class OperationExecutor {

    private final Operation operationType;
    private final int priority;
    private final Float value;

    public OperationExecutor(Operation operationType, int priority, Float value) {
        this.operationType = operationType;
        this.priority = priority;
        this.value = value;
    }

    public int getPriority() {
        return priority;
    }

    public Float exec(Float result) {
        return operationType.getOperation().apply(value, result);
    }
}
