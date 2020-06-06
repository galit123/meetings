package com.test.meetings;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Meeting not found")
public class MeetingNotFoundException extends  RuntimeException{
    public MeetingNotFoundException(String s) {
        super(s);
    }

}
