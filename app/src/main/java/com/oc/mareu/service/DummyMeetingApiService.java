package com.oc.mareu.service;

import com.oc.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyMeetingApiService implements MeetingApiService {

    final private List<Meeting> meetings = new ArrayList<>();

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public List<Meeting> getFilteredByDateMeetings(String meetingDate) {

        List<Meeting> meetings = new ArrayList<>();

        for (Meeting meeting : getMeetings()) {

            if (meeting.getDate().equals(meetingDate))
                meetings.add(meeting);

        }

        return meetings;

    }

    @Override
    public List<Meeting> getFilteredByRoomMeetings(String roomName) {

        List<Meeting> meetings = new ArrayList<>();

        for (Meeting meeting : getMeetings()) {

            if (meeting.getRoomName().equals(roomName))
                meetings.add(meeting);

        }

        return meetings;
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }
}
