package com.alllexe.calculator;

import com.alllexe.calculator.exception.InputNotValidException;
import com.alllexe.calculator.operation.OperationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class ValidatorTest {

    final Validator validator = new Validator(Collections.singleton(OperationType.PLUS));

    @Test
    void whenInputNotValidThanException() {
        Assertions.assertThrows(InputNotValidException.class,
                () ->validator.isInputValid(""));
    }

    @Test
    void whenInputValidThanNoException() {
        validator.isInputValid("5+4");
    }
}