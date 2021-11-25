package com.oc.mareu.service;

import com.oc.mareu.model.Meeting;

import java.util.List;

/**
 * Meeting API service
 */
public interface MeetingApiService {

    /**
     * Get all Meetings
     * @return {@link List}
     */
    List<Meeting> getMeetings();

    /**
     * Get all Meetings filtered by date
     * @return {@link List}
     */
    List<Meeting> getFilteredByDateMeetings(String meetingDate);

    /**
     * Get all Meetings filtered by room
     * @return {@link List}
     */
    List<Meeting> getFilteredByRoomMeetings(String roomName);

    /**
     * Create a meeting
     * @param meeting
     */
    void createMeeting(Meeting meeting);

    /**
     * Deletes a meeting
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);

}
