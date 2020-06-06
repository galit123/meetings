package com.test.meetings;

import com.test.calendar.*;
import com.test.meeting.response.Meeting;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;


@Component
public class MeetingsManager {
    private MeetingsCollection meetings = new MeetingsCollection();
    private Map<String, List<Meeting>> meetingsListsByTitle = new HashMap<String, List<Meeting>>();

    @PostConstruct
    private void initialize() throws IOException {
        Date date = new Date();

        Timestamp start = new Timestamp(date.getTime() - 52 * 60 * 60 * 1000);
        Timestamp end = new Timestamp(date.getTime() - 50 * 60 * 60 * 1000);
        this.addMeeting(new Meeting(start, end, "this is my meeting"));

        start = new Timestamp(date.getTime() - 124 * 60 * 60 * 1000);
        end = new Timestamp(date.getTime() - 122 * 60 * 60 * 1000);
        this.addMeeting(new Meeting(start, end, "second meeting"));

    }

    public List<Meeting> getMeetings() {
        return meetings.getMeetings();
    }

//        public  List<Meeting>  getMeetings(){
//        List<Meeting> meetings = new ArrayList<Meeting>();
//        for (int y: this.meetings.getYears().keySet()){
//            Year year = this.meetings.getYears().get(y);
//
//            for (int w: year.getWeeks().keySet()){
//                Week week = year.getWeeks().get(w);
//
//                for (int d: week.getDays().keySet()){
//                    Day day = week.getDays().get(d);
//
//                    for (Meeting m : day.getMeetings()){
//                        meetings.add(m);
//                    }
//                }
//            }
//        }
//        return meetings;
//    }

    public Meeting addMeeting(Meeting meeting) {
        meetings.addMeeting(meeting);
        List<Meeting> meetingsByTitle = meetingsListsByTitle.get(meeting.getTitle());
        if (meetingsByTitle == null){
            meetingsByTitle = new ArrayList<Meeting>();
            meetingsListsByTitle.put(meeting.getTitle(), meetingsByTitle);
        }
        meetingsByTitle.add(meeting);
        return meetings.getMeeting(meeting.getStartTime().getTime());
    }


//    public Meeting addMeeting(Meeting meeting){
//        Day day = meetings.getDayOfMeeting(meeting.getStartTime());
//        day.addMeeting(meeting);
//
//        List<Meeting> meetings = meetingsListsByTitle.get(meeting.getTitle());
//        if (meetings == null){
//            meetings = new ArrayList<Meeting>();
//            meetingsListsByTitle.put(meeting.getTitle(), meetings);
//        }
//        meetings.add(meeting);
//        return meeting;
//    }

    //    public void removeByTitle(String meetingTitle) {
//        if (!meetingsListsByTitle.containsKey(meetingTitle)){
//            throw new MeetingNotFoundException(meetingTitle + " is not found");
//        }
//        List<Meeting> meetingsByTitle = meetingsListsByTitle.get(meetingTitle);
//        for (Meeting m: meetingsByTitle){
//            Day day = meetings.getDayOfMeeting(m.getStartTime());
//            day.remove(m);
//        }
//        meetingsListsByTitle.remove(meetingTitle);
//    }
    public void removeByTitle(String meetingTitle) {
        if (!meetingsListsByTitle.containsKey(meetingTitle)) {
            throw new MeetingNotFoundException(meetingTitle + " is not found");
        }
        List<Meeting> meetingsByTitle = meetingsListsByTitle.get(meetingTitle);
        for (Meeting m : meetingsByTitle) {
            meetings.remove(m.getStartTime().getTime());
        }
        meetingsListsByTitle.remove(meetingTitle);
    }
//
//    public void removeByStartTime(Timestamp fromTime) {
//        Day day = meetings.getDayOfMeeting(fromTime);
//        day.removeByStartTime(fromTime);
//    }
//
    public void removeByStartTime(Timestamp fromTime) {
        meetings.remove(fromTime.getTime());
    }

    public Meeting nextMeeting() {
        return meetings.nextMeeting();
    }
}
