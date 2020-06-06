package com.test.calendar;

import com.test.meeting.response.Meeting;
import com.test.meetings.MeetingsRunTimeException;

import java.text.ParseException;
import java.util.Calendar;

public class MeetingImpl {
    private Meeting meeting;

    public Meeting getMeeting() {
        return meeting;
    }

    static final long MIN_15 = 15 * 60 * 1000;
    static final long HOURS_2 = 2 * 60 * 60 * 1000;

    public MeetingImpl(long startDate, long endDate, String meetingTitle) throws ParseException {
        this(new Meeting(startDate, endDate, meetingTitle));
    }

    MeetingImpl(Meeting meeting) {
         this.meeting = meeting;
    }

    int getStartTimeDayOfWeek() {
        return new TimestampImpl(getStartTime()).getDayOfWeek();
    }

    int getEndTimeDayOfWeek() {
        return new TimestampImpl(getEndTime()).getDayOfWeek();
    }

    boolean isOnSaturday() {
        return (Calendar.SATURDAY == getStartTimeDayOfWeek()) ||
                (Calendar.SATURDAY == getEndTimeDayOfWeek());
    }

    boolean isOverlapping(Meeting other) {
        TimestampImpl startTime = new TimestampImpl(getStartTime());
        TimestampImpl endTime = new TimestampImpl(getEndTime());

        if (startTime.afterOrEquals(other.getStartTime())
                && startTime.beforeOrEquals(other.getEndTime()) ||
                (endTime.afterOrEquals(other.getStartTime()) &&
                        endTime.beforeOrEquals(other.getEndTime()))) {
            return true;
        }
        return false;
    }

    void validate() {
        if (isOnSaturday()) {
            throw new MeetingsRunTimeException("Meetings are not allowed on Saturday");
        }

        long meetingTime = getEndTime() - getStartTime();

        if (meetingTime < MIN_15) {
            throw new MeetingsRunTimeException("Meeting is less than 15 min");
        } else if (meetingTime > HOURS_2) {
            throw new MeetingsRunTimeException("Meeting is longer than 2 hours");
        }

    }

    long getMeetingTimeInMS(){
        return getEndTime() - getStartTime();
    }
    long getEndTime() {
        return meeting.getEndTime();
    }

    long getStartTime() {
        return meeting.getStartTime();
    }

}
