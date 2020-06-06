package com.test.calendar;

import com.test.meeting.response.Meeting;
import com.test.meetings.MeetingsRunTimeException;

import java.sql.Timestamp;
import java.util.*;

public class MeetingsCollection {
//    private Map<Integer, Year> years = new HashMap<Integer, Year>();
    private TreeMap<Long , Meeting> meetings = new TreeMap<Long, Meeting>();

//    public Map<Integer, Year> getYears() {
//        return years;
//    }
//
//    public Year getYear(Timestamp startTime){
//        TimestampImpl ts = new TimestampImpl(startTime);
//        int yearInx = ts.getYear();
//        Year year = years.get(yearInx);
//
//        if (year == null){
//            year = new Year(yearInx);
//            years.put(yearInx,year);
//        }
//        return year;
//    }
//
//    public Day getDayOfMeeting(Timestamp fromTime){
//        Year year = getYear(fromTime);
//        Week week = year.getStartWeek(fromTime);
//        Day day = week.getStartDay(fromTime);
//
//        return day;
//    }

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
