package com.oc.mareu.model;

public class Meeting{

    /** Color */
    private String color;

    /** Full name */
    private String roomName;

    /** Date */
    private String date;

    /** Hour */
    private String hour;

    /** Creator */
    private String meetingCreator;

    /** Member */
    private String members;

    /**
     * Constructor
     */
    public Meeting(String color, String roomName, String date, String hour, String meetingCreator, String members) {
        this.color = color;
        this.roomName = roomName;
        this.date = date;
        this.hour = hour;
        this.meetingCreator = meetingCreator;
        this.members = members;
    }

    /**
     * Getters and Setters
     * @return
     */
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMeetingCreator() {
        return meetingCreator;
    }

    public void setMeetingCreator(String meetingCreator) {
        this.meetingCreator = meetingCreator;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

}
