package com.test.calendar;

import java.sql.Timestamp;
import java.util.Calendar;


class TimestampImpl {
    private Timestamp timestamp;

    TimestampImpl(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    Timestamp getTimestamp(){
        return timestamp;
    }

    int getDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp);

        return (cal.get(Calendar.DAY_OF_WEEK));
    }

    int getWeekOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp);

        return (cal.get(Calendar.WEEK_OF_YEAR));
    }

    int getYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp);

        return (cal.get(Calendar.YEAR));
    }

    boolean afterOrEquals(Timestamp ts) {
        return timestamp.after(ts) || timestamp.equals(ts);
    }

    boolean beforeOrEquals(Timestamp ts){
        return timestamp.before(ts)|| timestamp.equals(ts);
    }


    long getTime() {
        return timestamp.getTime();
    }

    public boolean before(Timestamp ts) {
        return timestamp.before(ts);
    }


    public boolean after(Timestamp ts) {
        return timestamp.after(ts);
    }
}
