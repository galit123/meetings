package com.ex.meetings.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
public class MeetingsRunTimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MeetingsRunTimeException(String s) {
        super(s);
    }

    public MeetingsRunTimeException(Exception e) {
        super(e);
    }
}
