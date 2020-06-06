package com.test.calendar;

import com.test.meeting.response.Meeting;
import com.test.meetings.MeetingNotFoundException;
import com.test.meetings.MeetingsRunTimeException;

import java.sql.Timestamp;
import java.util.*;

public class MeetingsCollection {
    private TreeMap<Long , Meeting> meetings = new TreeMap<Long, Meeting>();
    private Map<Long, Integer> weeks = new HashMap<Long, Integer>();

    public static final int HOURS_40_MS = 40 * 60 * 60 * 1000;

    public  List<Meeting>  getMeetings(){
        return new ArrayList<Meeting>(meetings.values());
    }

    public void addMeeting(Meeting meeting){
        MeetingImpl newMeeting = new MeetingImpl(meeting);
        newMeeting.validate();

        aggregateMeetingToWeeks(newMeeting);

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

    private void aggregateMeetingToWeeks(MeetingImpl m) {
        TimestampImpl ts = new TimestampImpl(m.getStartTime());

        long firstDayOfWeek = getFirstDayOfWeek(ts.getTime());

        int hoursTotal = 0;
        if (!weeks.containsKey(firstDayOfWeek)){
            weeks.put(firstDayOfWeek, hoursTotal);
        } else {
            hoursTotal = weeks.get(firstDayOfWeek);
        }

        hoursTotal += m.getMeetingTimeInMS();
        if (hoursTotal > HOURS_40_MS){
            throw new MeetingsRunTimeException("Meetings time in this week exceede 40 hours");
        }

        weeks.put(firstDayOfWeek, hoursTotal);


    }

    private long getFirstDayOfWeek(long time){

        // get today and clear time of day
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);

        // get start of this week in milliseconds
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        System.out.println("Start of this week:       " + cal.getTime());
        System.out.println("... in milliseconds:      " + cal.getTimeInMillis());
        return cal.getTimeInMillis();

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
