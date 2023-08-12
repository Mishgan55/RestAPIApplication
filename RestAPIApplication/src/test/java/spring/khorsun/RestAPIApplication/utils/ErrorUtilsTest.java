package spring.khorsun.RestAPIApplication.utils;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ErrorUtilsTest {

    @Test
    void testReturnErrorsWithNoFieldErrors() {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(new ArrayList<>());

        try {
            ErrorUtils.returnErrors(bindingResult);
        } catch (ErrorException e) {
            assertEquals("", e.getMessage());
        }
    }

    @Test
    void testReturnErrorsWithFieldErrors() {
        BindingResult bindingResult = mock(BindingResult.class);

        List<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldError("object", "fieldName1", "Error message 1"));
        fieldErrors.add(new FieldError("object", "fieldName2", "Error message 2"));

        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        try {
            ErrorUtils.returnErrors(bindingResult);
        } catch (ErrorException e) {
            assertEquals("fieldName1 - Error message 1;fieldName2 - Error message 2;", e.getMessage());
        }
    }
}