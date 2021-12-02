package com.oc.mareu;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.oc.mareu.model.Meeting;
import com.oc.mareu.service.MeetingApiService;
import com.oc.mareu.di.DI;


/**
 * Unit test on Meeting service
 */
@RunWith(JUnit4.class)
public class MeetingsUnitTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceMeetingApiService();
    }

    /**
     * Test getMeetings()
     **/
    @Test
    public void getMeetingWithSuccess() {
        Meeting meeting1 = new Meeting("vert", "Réunion 1", "14/08/2021", "15-00",
                "Jojo", "mag@mg.fr");
        service.createMeeting(meeting1);
        assertTrue(service.getMeetings().contains(meeting1));
    }

    /**
     * Test createMeeting()
     **/
    @Test
    public void createNewMeetingWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        Meeting meeting1 = new Meeting("vert", "Réunion 1", "14/08/2021", "15-00",
                "Jojo", "mag@mg.fr");
        service.createMeeting(meeting1);
        assertTrue(meetings.contains(meeting1));
        service.deleteMeeting(meeting1);
        assertFalse(meetings.contains(meeting1));
    }

    /**
     * Test deleteMeeting
     **/
    @Test
    public void deleteMeetingWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        Meeting meeting1 = new Meeting("vert", "Réunion 1", "14/08/2021", "15-00",
                "Jojo", "mag@mg.fr");
        service.createMeeting(meeting1);
        assertTrue(meetings.contains(meeting1));
        service.deleteMeeting(meeting1);
        assertFalse(meetings.contains(meeting1));
    }

    /**
     * Test filterMeeting by room
     **/
    @Test
    public void filterMeetingByRoomWithSuccess() {
        List<Meeting> meetings = service.getMeetings();

    }

    /**
     * Test filterMeeting by date
     **/
    @Test
    public void filterMeetingByDateWithSuccess() {
        List<Meeting> meetings = service.getMeetings();

    }

}