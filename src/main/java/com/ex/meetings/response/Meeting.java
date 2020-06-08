package com.ex.meetings.response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Meeting {
    static final String ISO_DATE_FORMAT_ZERO_OFFSET = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private long startTime;
    private long endTime;
    private String title;

    public Meeting(long startTime, long endTime, String title) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
    }

    public long getEndTime() {
        return endTime;
    }

    public String getToTime(){
        return getFormattedDate(endTime);
    }

    public String getFromTime(){
        return getFormattedDate(startTime);
    }

    private String getFormattedDate(long longDate){
        Date date = new Date(longDate);
        DateFormat df = new SimpleDateFormat(ISO_DATE_FORMAT_ZERO_OFFSET);
        String dateAsString = df.format(date);

        return dateAsString;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
