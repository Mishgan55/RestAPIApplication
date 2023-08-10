package spring.khorsun.RestAPIApplication.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorUtils {

    public static void returnErrors(BindingResult bindingResult){

        StringBuilder stringBuilder = new StringBuilder();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {

            stringBuilder.append(fieldError.getField()).append(" - ")
                    .append(fieldError.getDefaultMessage()==null ? fieldError.getCode() : fieldError.getDefaultMessage())
                    .append(";");

        }
        throw new ErrorException(stringBuilder.toString());

    }
}
