package ru.appline;

import java.time.Instant;
import java.util.Date;

public class ResponseModel {
    // TODO: 11/6/20 проверить почему в ответе это поле отсутствует
    private final Date dateAndTime = Date.from(Instant.now());
    private int status;
    private String message;

    public ResponseModel(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
