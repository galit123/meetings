//package com.test.calendar;
//
//import com.test.meeting.response.Meeting;
//import com.test.meetings.MeetingNotFoundException;
//import com.test.meetings.MeetingsRunTimeException;
//
//import java.sql.Timestamp;
//import java.util.LinkedList;
//import java.util.List;
//
//
//public class Day {
//    private int dayOfWeek;
//    private List<Meeting> meetings = new LinkedList<Meeting>();
//
//    public List<Meeting> getMeetings() {
//        return meetings;
//    }
//
//    public void setMeetings(List<Meeting> meetings) {
//        this.meetings = meetings;
//    }
//
//    public Day(int dayOfWeek) {
//        this.dayOfWeek = dayOfWeek;
//    }
//
//
//    public Day addMeeting(Meeting meeting){
//        MeetingImpl newMeeting = new MeetingImpl(meeting);
//        newMeeting.validate();
//        int inx = 0;
//        while (inx < meetings.size()){
//            Meeting m = meetings.get(inx);
//            if (newMeeting.isOverlapping(m)){
//                throw new MeetingsRunTimeException("new meeting is overlapping with an existing meeting");
//            }
//            if (meeting.getStartTime().before(m.getStartTime())){
//                break;
//            }
//            inx++;
//
//        }
//        meetings.add(inx, meeting);
//
//        return this;
//    }
//
//    public void removeByStartTime(Timestamp ts) {
//        int inx = 0;
//        Meeting m = null;
//        while (inx < meetings.size()) {
//            m = meetings.get(inx);
//
//            if (ts.equals(m.getStartTime())) {
//                break;
//            }
//            inx++;
//        }
//
//        if (inx == meetings.size()){
//            throw new MeetingNotFoundException("meeting is not found for " + ts);
//        }
//
//        if (m != null) {
//            meetings.remove(m);
//        }
//    }
//
//    public void remove(Meeting meeting) {
//        meetings.remove(meeting);
//    }
//}
