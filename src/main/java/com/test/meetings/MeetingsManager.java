package com.test.meetings;

import com.test.calendar.*;
import com.test.meeting.response.Meeting;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Component
public class MeetingsManager {
    private MeetingsCollection meetings = new MeetingsCollection();
    private Map<String, List<Meeting>> meetingsListsByTitle = new HashMap<String, List<Meeting>>();
    public static final String ISO_DATE_FORMAT_ZERO_OFFSET = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";


    @PostConstruct
    private void initialize() throws IOException {
        Date date = new Date();

        Timestamp start = new Timestamp(date.getTime() - 52 * 60 * 60 * 1000);
        Timestamp end = new Timestamp(date.getTime() - 50 * 60 * 60 * 1000);
        this.addMeeting(new Meeting(start.getTime(), end.getTime(), "this is my meeting"));

        start = new Timestamp(date.getTime() - 124 * 60 * 60 * 1000);
        end = new Timestamp(date.getTime() - 122 * 60 * 60 * 1000);
        this.addMeeting(new Meeting(start.getTime(), end.getTime(), "second meeting"));

    }

    public List<Meeting> getMeetings() {
        return meetings.getMeetings();
    }

    public Meeting addMeeting(Meeting meeting) {
        meetings.addMeeting(meeting);
        List<Meeting> meetingsByTitle = meetingsListsByTitle.get(meeting.getTitle());
        if (meetingsByTitle == null) {
            meetingsByTitle = new ArrayList<Meeting>();
            meetingsListsByTitle.put(meeting.getTitle(), meetingsByTitle);
        }
        meetingsByTitle.add(meeting);
        return meetings.getMeeting(meeting.getStartTime());
    }

    public void removeByTitle(String meetingTitle) {
        if (!meetingsListsByTitle.containsKey(meetingTitle)) {
            throw new MeetingNotFoundException(meetingTitle + " is not found");
        }
        List<Meeting> meetingsByTitle = meetingsListsByTitle.get(meetingTitle);
        for (Meeting m : meetingsByTitle) {
            meetings.remove(m.getStartTime());
        }
        meetingsListsByTitle.remove(meetingTitle);
    }

    public void removeByStartTime(String fromTime) throws ParseException {
        meetings.remove(getLongDate(fromTime));
    }

    private long getLongDate(String strDate) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(ISO_DATE_FORMAT_ZERO_OFFSET);
        Date date = (Date) formatter.parse(strDate);
        return date.getTime();
    }


    public Meeting nextMeeting() {
        return meetings.nextMeeting();
    }

    public Meeting addMeeting(String startDate, String endDate, String meetingTitle) throws ParseException {
        MeetingImpl meeting = new MeetingImpl(getLongDate(startDate), getLongDate(endDate), meetingTitle);
        return addMeeting(meeting.getMeeting());
    }
}
