package com.test.meetings;

import com.test.meeting.response.Meeting;
import com.test.meeting.response.MeetingsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class MeetingsController {

    @Autowired
    private MeetingsManager meetingsManager;

    @PostMapping(path = "/meetings")
    public MeetingsResponse SetMeeting(@RequestBody Meeting meeting){
        meetingsManager.addMeeting(meeting);
        return new MeetingsResponse(meeting, "meeting added");
    }

    @DeleteMapping(path = "/meetings/title")
    public MeetingsResponse RemoveMeetingByTitle(@RequestParam("meetingTitle") String meetingTitle) {
        meetingsManager.removeByTitle(meetingTitle);
        return new MeetingsResponse("'" + meetingTitle + "'" + " meeting deleted");
    }

    @DeleteMapping(path = "/meetings")
    public MeetingsResponse RemoveMeetingByStartTime(@RequestParam("fromTime") Timestamp fromTime) {
        meetingsManager.removeByStartTime(fromTime);
        return new MeetingsResponse("meeting deleted");
    }

    @GetMapping(path = "/meetings/next")
    public MeetingsResponse getNextMeeting(){
        return new MeetingsResponse(meetingsManager.nextMeeting(), "next meeting");
    }

    @GetMapping(path = "/meetings")
    public MeetingsResponse getMeetings(){
        return new MeetingsResponse(meetingsManager.getMeetings(),"meetings");
    }

}
