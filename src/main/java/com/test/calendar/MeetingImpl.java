package com.test.calendar;

import com.test.meeting.response.Meeting;
import com.test.meetings.MeetingsRunTimeException;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.util.Calendar;

class MeetingImpl {
    private Meeting meeting;

    Meeting getMeeting() {
        return meeting;
    }

    static final long MIN_15 = 15 * 60 * 1000;
    static final long HOURS_2 = 2 * 60 * 60 * 1000;

    MeetingImpl(Meeting meeting) {
         this.meeting = meeting;
    }

    int getStartYear() {
        return new TimestampImpl(getStartTime()).getYear();
    }

    int getStartWeek() {
        return new TimestampImpl(getStartTime()).getWeekOfYear();
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

    Timestamp getEndTime() {
        return meeting.getEndTime();
    }

    void setEndTime(Timestamp endTime) {
        this.meeting.setStartTime(endTime);
    }

    String getTitle() {
        return meeting.getTitle();
    }

    void setTitle(String title) {
        this.meeting.setTitle(title);
    }

    Timestamp getStartTime() {
        return meeting.getStartTime();
    }

    void setStartTime(Timestamp startTime) {
        this.meeting.setStartTime(startTime);
    }
}
