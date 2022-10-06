package com.alllexe.calculator;

import com.alllexe.calculator.exception.InputNotValidException;
import com.alllexe.calculator.operation.OperationType;
import com.alllexe.calculator.validator.ValidatorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

class ValidatorImplTest {

    final ValidatorImpl validatorImpl = new ValidatorImpl(new HashSet<>(
            Arrays.asList(
                    OperationType.PLUS,
                    OperationType.MINUS,
                    OperationType.MULTIPLE,
                    OperationType.DIVIDE)));

    @Test
    void whenInputNotValidThanException() {
        Assertions.assertThrows(InputNotValidException.class,
                () -> validatorImpl.validate(""));
    }

    @Test
    void whenInputContainsCharsThanException() {
        Assertions.assertThrows(InputNotValidException.class,
                () -> validatorImpl.validate("3+dds"));
    }

    @Test
    void whenInputStartsMultipleThanException() {
        Assertions.assertThrows(InputNotValidException.class,
                () -> validatorImpl.validate("*3+5"));
    }

    @Test
    void whenInputEndsWrongThanException() {
        Assertions.assertThrows(InputNotValidException.class,
                () -> validatorImpl.validate("3+5-"));
    }

    @Test
    void whenInputParenthesesLeftWrongThanException() {
        Assertions.assertThrows(InputNotValidException.class,
                () -> validatorImpl.validate("((3+5)"));
    }

    @Test
    void whenInputParenthesesRigtWrongThanException() {
        Assertions.assertThrows(InputNotValidException.class,
                () -> validatorImpl.validate("((3+5)))"));
    }

    @Test
    void whenInputParenthesesLeftAbsentThanException() {
        Assertions.assertThrows(InputNotValidException.class,
                () -> validatorImpl.validate("3+5)"));
    }

    @Test
    void whenInputParenthesesRightAbsentThanException() {
        Assertions.assertThrows(InputNotValidException.class,
                () -> validatorImpl.validate("(3+5"));
    }

    @Test
    void whenInputParenthesesWrongThanException() {
        Assertions.assertThrows(InputNotValidException.class,
                () -> validatorImpl.validate("())3+5("));
    }

    @Test
    void whenInputParenthesesEmptyThanException() {
        Assertions.assertThrows(InputNotValidException.class,
                () -> validatorImpl.validate("3+()+5"));
    }

    @Test
    void whenInputParenthesesReverseEmptyThanException() {
        Assertions.assertThrows(InputNotValidException.class,
                () -> validatorImpl.validate("(3+7)(-1+5"));
    }

    @Test
    void whenInputValidThanNoException() {
        validatorImpl.validate("(5+4)");
    }
}