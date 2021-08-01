package com.oc.mareu.service;

import com.oc.mareu.model.Meeting;

import java.util.Arrays;
import java.util.List;

/**
 * Meeting generator
 */
public class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("vert", "Reunion A -", " 14-07-2022 -", " 14:45 - ",
                    "BigYo", "magà@ht.fr"),
            new Meeting("rouge", "Reunion B", "09-09-2021", "08:45",
                    "BigMdf", "logos@ht.fr"),
            new Meeting("orange", "Reunion A", "14-07-2022", "14:45",
                    "BigYo", "magà@ht.fr"),
            new Meeting("vert", "Reunion B", "09-09-2021", "08:45",
                    "BigMdf", "logos@ht.fr")
    );

    static List<Meeting> generateMeetings() {
        return DUMMY_MEETINGS;
    }

}
