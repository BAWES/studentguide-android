package com.techno.studentguide.api;

public class ResponseBase {


    private String httpcode;
    private String status;
    private String message;
    public String getHttpcode() {
        return httpcode;
    }

    public void setHttpcode(String httpcode) {
        this.httpcode = httpcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
