package com.alllexe;

import com.alllexe.calculator.Calculator;
import com.alllexe.calculator.OperationParser;
import com.alllexe.calculator.Validator;
import com.alllexe.calculator.operation.OperationType;

import java.util.*;

import static com.alllexe.calculator.operation.OperationType.PLUS;

public class Main {
    public static void main(String[] args) {
        Set<OperationType> operationTypes = new HashSet<>(Collections.singletonList(PLUS));
        Calculator calculator = new Calculator(new OperationParser(new Validator(operationTypes)));
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter string for calculator or type 'exit'");
        String strInput = scanner.nextLine();
        while (!"exit".equals(strInput)) {
            System.out.println("Result is:" + calculator.calculate(strInput));
            System.out.println("Enter string for calculator or type 'exit'");
            strInput = scanner.nextLine();
        }

    }
}
