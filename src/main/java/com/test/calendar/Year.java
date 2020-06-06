//package com.test.calendar;
//
//import com.test.meeting.response.Meeting;
//
//import java.sql.Timestamp;
//import java.util.HashMap;
//import java.util.Map;
//
//
//public class Year {
//    private int year;
//    private Map<Integer, Week> weeks = new HashMap<Integer, Week>();
//
//    public Map<Integer, Week> getWeeks() {
//        return weeks;
//    }
//
//    public Year(int year) {
//        this.year = year;
//    }
//
//    public Week getStartWeek(Meeting meeting) {
//        return getStartWeek(meeting.getStartTime());
//    }
//    public Week getStartWeek(Timestamp ts) {
//        TimestampImpl timestamp = new TimestampImpl(ts);
//        int weekOfYear = timestamp.getWeekOfYear();
//        Week week = weeks.get(weekOfYear);
//
//        if (week == null){
//            week = new Week(weekOfYear);
//            weeks.put(weekOfYear,week);
//        }
//        return week;
//
//    }
//}
