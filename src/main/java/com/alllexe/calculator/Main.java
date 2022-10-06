package com.alllexe.calculator;

import com.alllexe.calculator.parser.OperationParserImpl;
import com.alllexe.calculator.operation.OperationType;
import com.alllexe.calculator.validator.ValidatorImpl;

import java.util.*;

import static com.alllexe.calculator.operation.OperationType.*;

public class Main {
    public static void main(String[] args) {
        Set<OperationType> operationTypes = new HashSet<>(Arrays.asList(PLUS, MINUS, MULTIPLE, DIVIDE));
        Calculator calculator = new Calculator(new OperationParserImpl(new ValidatorImpl(operationTypes)));
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter string for calculation or type 'exit'");
        String strInput = scanner.nextLine();
        while (!"exit".equals(strInput)) {
            System.out.println("Result is:" + calculator.calculate(strInput));
            System.out.println("Enter string for calculator or type 'exit'");
            strInput = scanner.nextLine();
        }

    }
}
