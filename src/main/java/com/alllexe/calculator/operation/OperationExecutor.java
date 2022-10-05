package com.alllexe.calculator.operation;

import java.util.ArrayList;
import java.util.List;

public class OperationExecutor {

    private final OperationType operationType;
    private Float value;
    private final List<OperationExecutor> operationExecutorList;

    public OperationExecutor(OperationType operationType,
                             Float value) {
        this.operationType = operationType;
        this.value = value;
        this.operationExecutorList = new ArrayList<>();
    }

    public Float exec(Float result) {
        System.out.println("Perform "+result+" "+operationType.getSign()+" "+value);
        Float r = operationType.getOperation().apply(result, value);
        System.out.println("result "+r);
        return r;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public List<OperationExecutor> getOperationExecutorList() {
        return operationExecutorList;
    }
}
