package com.test.meetings;

import com.test.meeting.response.Meeting;
import com.test.meeting.response.MeetingsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;

@RestController
public class MeetingsController {

    @Autowired
    private MeetingsManager meetingsManager;

//    @PostMapping(path = "/meetings")
//    public MeetingsResponse SetMeeting(@RequestParam Meeting meeting){
//        meetingsManager.addMeeting(meeting);
//        return new MeetingsResponse(meeting, "meeting added");
//    }

    @PostMapping(path = "/meetings")
    public MeetingsResponse SetMeeting(@RequestParam("startDate") String startDate,
                                       @RequestParam("endDate") String endDate,
                                       @RequestParam("meetingTitle") String meetingTitle ){
        Meeting meeting = null;
        try {
             meeting = meetingsManager.addMeeting(startDate, endDate, meetingTitle);
        } catch (ParseException e) {
            throw new MeetingsRunTimeException(e);
        }
        return new MeetingsResponse(meeting, "meeting added");
    }

    @DeleteMapping(path = "/meetings/title")
    public MeetingsResponse RemoveMeetingByTitle(@RequestParam("meetingTitle") String meetingTitle) {
        meetingsManager.removeByTitle(meetingTitle);
        return new MeetingsResponse("'" + meetingTitle + "'" + " meeting deleted");
    }

    @DeleteMapping(path = "/meetings")
    public MeetingsResponse RemoveMeetingByStartTime(@RequestParam("fromTime") String fromTime) {
        try {
            meetingsManager.removeByStartTime(fromTime);
        } catch (ParseException e) {
            throw new MeetingsRunTimeException(e);
        }
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
