package com.test.meetings;

import com.test.meeting.response.Meeting;
import com.test.meeting.response.MeetingsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
public class MeetingsController  {

    @Autowired
    private MeetingsManager meetingsManager;

    @PostMapping(path = "/meetings")
    public MeetingsResponse SetMeeting(@RequestParam("fromTime") String startTime,
                                       @RequestParam("toTime") String endTime,
                                       @RequestParam("meetingTitle") String meetingTitle ){
        Meeting meeting = null;
        try {
             meeting = meetingsManager.addMeeting(startTime, endTime, meetingTitle);
        } catch (ParseException e) {
            throw new MeetingsRunTimeException(e);
        }
        return new MeetingsResponse(meeting, "meeting added", HttpStatus.OK);
    }

    @DeleteMapping(path = "/meetings/title")
    public MeetingsResponse RemoveMeetingByTitle(@RequestParam("meetingTitle") String meetingTitle) {
        meetingsManager.removeByTitle(meetingTitle);
        return new MeetingsResponse("'" + meetingTitle + "'" + " meeting deleted", HttpStatus.OK);
    }

    @DeleteMapping(path = "/meetings")
    public MeetingsResponse RemoveMeetingByStartTime(@RequestParam("fromTime") String fromTime) {
        try {
            meetingsManager.removeByStartTime(fromTime);
        } catch (ParseException e) {
            throw new MeetingsRunTimeException(e);
        }
        return new MeetingsResponse("meeting from " + fromTime +"deleted", HttpStatus.OK);
    }

    @GetMapping(path = "/meetings/next")
    public MeetingsResponse getNextMeeting(){
        return new MeetingsResponse(meetingsManager.nextMeeting(), "next meeting", HttpStatus.OK);
    }

    @GetMapping(path = "/meetings")
    public MeetingsResponse getMeetings(){
        return new MeetingsResponse(meetingsManager.getMeetings(),"meetings", HttpStatus.OK);
    }

}
