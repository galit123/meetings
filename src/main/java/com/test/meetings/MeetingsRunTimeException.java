package com.test.meetings;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class MeetingsRunTimeException extends RuntimeException {
    public MeetingsRunTimeException(String s) {
        super(s);
    }
}
