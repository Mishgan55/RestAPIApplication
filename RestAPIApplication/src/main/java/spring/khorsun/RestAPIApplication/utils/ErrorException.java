package spring.khorsun.RestAPIApplication.utils;

public class ErrorException extends RuntimeException{
    public ErrorException(String message) {
        super(message);
    }
}
