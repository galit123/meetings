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

    public void removeByStartTime(Timestamp fromTime) {
        meetings.remove(fromTime.getTime());
    }

    public Meeting nextMeeting() {
        return meetings.nextMeeting();
    }
}
