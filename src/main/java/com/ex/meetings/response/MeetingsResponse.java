package com.ex.meetings.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize
public class MeetingsResponse {
    private HttpStatus statusCode;
    private String message;
    private List<Meeting> meetings;

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public MeetingsResponse(Meeting meeting, String message, HttpStatus statusCode) {
        this( message, statusCode);
        this.meetings = new ArrayList<Meeting>();
        this.meetings.add(meeting);
    }

    public MeetingsResponse(List<Meeting> meetings, String message, HttpStatus statusCode) {
        this( message, statusCode);

        this.meetings = meetings;
    }

    public MeetingsResponse(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

}
