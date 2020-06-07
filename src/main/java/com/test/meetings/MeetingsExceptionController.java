package com.test.meetings;

import com.test.meeting.response.Meeting;
import com.test.meeting.response.MeetingsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MeetingsExceptionController {

    @ExceptionHandler(value = MeetingNotFoundException.class)
    public ResponseEntity<MeetingsResponse> exception(MeetingNotFoundException exception) {

        MeetingsResponse response = new MeetingsResponse("Meeting not found: " + exception.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<MeetingsResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MeetingsRunTimeException.class)
    public ResponseEntity<MeetingsResponse> exception(MeetingsRunTimeException exception){

        MeetingsResponse response = new MeetingsResponse("Meeting error occured: " + exception.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<MeetingsResponse>(response, HttpStatus.BAD_REQUEST);

    }
}
