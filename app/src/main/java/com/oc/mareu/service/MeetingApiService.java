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
