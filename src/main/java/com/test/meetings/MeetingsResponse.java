package com.test.meetings;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.test.meeting.response.Meeting;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize
public class MeetingsResponse {
    private String message;
    private List<Meeting> meetings;

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public MeetingsResponse(Meeting meeting, String message) {
        this( message);
        this.meetings = new ArrayList<Meeting>();
        this.meetings.add(meeting);
    }

    public MeetingsResponse(List<Meeting> meetings, String message) {
        this( message);

        this.meetings = meetings;
    }

    public MeetingsResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
