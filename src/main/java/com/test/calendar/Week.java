//package com.test.calendar;
//
//import com.test.meeting.response.Meeting;
//
//import java.sql.Timestamp;
//import java.util.*;
//
//
//public class Week {
//    private int weekOfYear;
//    private Map<Integer, Day> days = new HashMap<Integer, Day>();
//
//    public Map<Integer, Day> getDays() {
//        return days;
//    }
//
//    public Week(int weekOfYear) {
//        this.weekOfYear = weekOfYear;
//    }
//
//    public Day getStartDay(Meeting meeting) {
//        return getStartDay(meeting.getStartTime());
//    }
//    public Day getStartDay(Timestamp ts) {
//        TimestampImpl timestamp = new TimestampImpl(ts);
//        int dayOfWeek = timestamp.getDayOfWeek();
//        Day day = days.get(dayOfWeek);
//
//        if (day == null){
//            day = new Day(dayOfWeek);
//            days.put(dayOfWeek,day);
//        }
//        return day;
//    }
//}
