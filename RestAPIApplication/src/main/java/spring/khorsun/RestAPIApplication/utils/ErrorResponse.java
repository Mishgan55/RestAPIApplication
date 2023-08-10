package spring.khorsun.RestAPIApplication.utils;

import java.util.Date;

public class ErrorResponse{
    private String nameError;
    private Date date;

    public ErrorResponse(String nameError, Date date) {
        this.nameError = nameError;
        this.date = date;
    }


    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
