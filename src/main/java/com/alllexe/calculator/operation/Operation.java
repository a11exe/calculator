package com.alllexe.calculator.operation;

import java.util.ArrayList;
import java.util.List;

public class Operation {

    private final OperationType operationType;
    private Float value;
    private final List<Operation> operations;

    public Operation(OperationType operationType,
                     Float value) {
        this.operationType = operationType;
        this.value = value;
        this.operations = new ArrayList<>();
    }

    public Float exec(Float result) {
        for (Operation operation : operations) {
            value = operation.exec(value);
        }
//        System.out.println("Perform "+result+" "+operationType.getSign()+" "+value);
        Float r = operationType.getOperation().apply(result, value);
//        System.out.println("result "+r);
        return r;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public OperationType getOperationType() {
        return operationType;
    }

}
