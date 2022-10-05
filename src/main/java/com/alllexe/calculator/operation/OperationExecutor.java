package com.alllexe.calculator.operation;

public class OperationExecutor {

    private final OperationType operationType;
    private final int priority;
    private Float value;
    private OperationExecutor prev;

    public OperationExecutor(OperationType operationType,
                             int priority,
                             Float value,
                             OperationExecutor prev) {
        this.operationType = operationType;
        this.priority = priority;
        this.value = value;
        this.prev = prev;
    }

    public int getPriority() {
        return priority;
    }

    public Float exec(Float result) {
        return operationType.getOperation().apply(result, value);
    }

    public OperationExecutor getPrev() {
        return prev;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
