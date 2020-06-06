package com.test.meetings;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
public class MeetingsRunTimeException extends RuntimeException {
    public MeetingsRunTimeException(String s) {
        super(s);
    }
}
