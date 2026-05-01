package com.bootcamp.excepcion;

import java.time.ZonedDateTime;

public class ApiExceptionBody {

    private final String message;

    private final ZonedDateTime zonedDateTime;

    public ApiExceptionBody(String message, ZonedDateTime zonedDateTime){
        this.message=message;
        this.zonedDateTime=zonedDateTime;
    }

    public String getMessage() {
        return message;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    @Override
    public String toString() {
        return "ApiExceptionBody{" +
                "message='" + message + '\'' +
                ", zonedDateTime=" + zonedDateTime +
                '}';
    }
}
