package com.test.meetings;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Meeting not found")
public class MeetingNotFoundException extends  RuntimeException{
    private static final long serialVersionUID = 1L;

    public MeetingNotFoundException(String s) {
        super(s);
    }

}
