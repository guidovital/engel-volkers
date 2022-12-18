/**
 * 
 */
package com.engelvolkers.test.controller.login.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

/**
 * This class is used to test the LoginVO
 * 
 * @author Guilherme Vital
 *
 */
class LoginVOTest {

    /**
     * This method is used to test null values for email and password
     */
    @Test
    void whenEmailAndPasswordAreNull() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        var vo = LoginVO.builder()
                .build();

        Set<ConstraintViolation<LoginVO>> violations = validator.validate(vo);
        assertEquals(2, violations.size());
    }

    /**
     * This method is used to test null values for email
     */
    @Test
    void whenOnlyEmailIsNull() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        var vo = LoginVO.builder().password("abc")
                .build();

        Set<ConstraintViolation<LoginVO>> violations = validator.validate(vo);
        assertEquals(1, violations.size());
    }

    /**
     * This method is used to test an incorrect email
     */
    @Test
    void whenEmailIsIncorrect() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        var vo = LoginVO.builder().password("123456").email("guido.com")
                .build();

        Set<ConstraintViolation<LoginVO>> violations = validator.validate(vo);
        assertEquals(1, violations.size());
    }

    /**
     * This method is used to test an email from test.com
     */
    @Test
    void whenEmailIsFromTestDotCom() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        var vo = LoginVO.builder().password("123456").email("guido@test.com")
                .build();

        Set<ConstraintViolation<LoginVO>> violations = validator.validate(vo);
        assertEquals(1, violations.size());
    }

    /**
     * This method is used to test a correct email
     */
    @Test
    void whenEmailIsCorrect() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        var vo = LoginVO.builder().email("guido@gmail.com").password("12323").build();

        Set<ConstraintViolation<LoginVO>> violations = validator.validate(vo);
        assertEquals(true, violations.isEmpty());
    }
}