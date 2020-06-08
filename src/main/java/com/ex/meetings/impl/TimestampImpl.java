package com.ex.meetings.impl;

import java.util.Calendar;


class TimestampImpl {
    private long timestamp;

    TimestampImpl(long timestamp) {
        this.timestamp = timestamp;
    }

    long getTimestamp(){
        return timestamp;
    }

    int getDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);

        return (cal.get(Calendar.DAY_OF_WEEK));
    }

    int getWeekOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);

        return (cal.get(Calendar.WEEK_OF_YEAR));
    }

    int getYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);

        return (cal.get(Calendar.YEAR));
    }

    boolean afterOrEquals(long ts) {
        return (timestamp > ts) || (timestamp == ts);
    }

    boolean beforeOrEquals(long ts){
        return (timestamp < ts || timestamp == ts);
    }


    long getTime() {
        return timestamp;
    }

    public boolean before(long ts) {
        return (timestamp < ts);
    }


    public boolean after(long ts) {
        return (timestamp > ts);
    }
}
