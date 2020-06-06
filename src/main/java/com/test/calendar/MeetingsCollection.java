package com.test.calendar;

import com.test.meeting.response.Meeting;
import com.test.meetings.MeetingNotFoundException;
import com.test.meetings.MeetingsRunTimeException;

import java.sql.Timestamp;
import java.util.*;

public class MeetingsCollection {
    private TreeMap<Long , Meeting> meetings = new TreeMap<Long, Meeting>();

    public  List<Meeting>  getMeetings(){
        return new ArrayList<Meeting>(meetings.values());
    }

    public void addMeeting(Meeting meeting){
        MeetingImpl newMeeting = new MeetingImpl(meeting);
        newMeeting.validate();

        long startTime = meeting.getStartTime().getTime();
        if (meetings.ceilingEntry(startTime) != null){
            long prev = meetings.ceilingKey(startTime);
            Meeting prevMeeting = meetings.get(prev);

            if (newMeeting.isOverlapping(prevMeeting)){
                throw new MeetingsRunTimeException("new meeting is overlapping with an existing meeting");
            }
        }

        if (meetings.floorKey(startTime) != null){
            long next = meetings.floorKey(startTime);
            Meeting nextMeeting = meetings.get(next);

            if (newMeeting.isOverlapping(nextMeeting)){
                throw new MeetingsRunTimeException("new meeting is overlapping with an existing meeting");
            }
        }

        meetings.put(meeting.getStartTime().getTime(), meeting);
    }


    public Meeting nextMeeting(Timestamp ts) {
        Long nextKey = meetings.ceilingKey(ts.getTime());

        if (nextKey == null){
            throw new MeetingNotFoundException("Can't find a scheduled future meeting");
        }

        return meetings.get(nextKey);
    }

    public Meeting nextMeeting(){
        Timestamp now = new Timestamp(new Date().getTime());
        return nextMeeting(now);
    }

    public Meeting getMeeting(long time) {
        return meetings.get(time);
    }

    public void remove(long startTime) {
        meetings.remove(startTime);
    }
}
