package com.test.meetings;

import com.test.meeting.response.Meeting;
import com.test.meeting.response.MeetingsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@RestController
public class MeetingsController implements ErrorController {

    @Autowired
    private MeetingsManager meetingsManager;

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

    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
                        + "<div>Exception Message: <b>%s</b></div><body></html>",
                statusCode, exception==null? "N/A": exception.getMessage());
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
