package com.test.calendar;

import com.test.meeting.response.Meeting;
import com.test.meetings.MeetingsRunTimeException;

import java.sql.Timestamp;
import java.util.Calendar;

class MeetingImpl {
    private Meeting meeting;

    static final long MIN_15 = 15 * 60 * 1000;
    static final long HOURS_2 = 2 * 60 * 60 * 1000;

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

        long meetingTime = getEndTime().getTime() - getStartTime().getTime();

        if (meetingTime < MIN_15) {
            throw new MeetingsRunTimeException("Meeting is less than 15 min");
        } else if (meetingTime > HOURS_2) {
            throw new MeetingsRunTimeException("Meeting is longer than 2 hours");
        }

    }

    long getMeetingTimeInMS(){
        return getEndTime().getTime() - getStartTime().getTime();
    }
    Timestamp getEndTime() {
        return meeting.getEndTime();
    }

    Timestamp getStartTime() {
        return meeting.getStartTime();
    }

}
