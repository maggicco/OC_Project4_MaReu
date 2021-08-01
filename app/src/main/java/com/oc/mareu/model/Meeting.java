package com.oc.mareu.model;

public class Meeting {

    /** Color */
    private String color;

    /** Full name */
    private String roomName;

    /** Date */
    private String date;

    /** Hour */
    private String hour;

    /** Creator */
    private String creator;

    // TODO: 01/08/2021   Declare set of strings 
    /** Member */
    private String member;

    /**
     * Constructor
     */
    public Meeting(String color, String roomName, String date, String hour, String creator, String member) {
        this.color = color;
        this.roomName = roomName;
        this.date = date;
        this.hour = hour;
        this.creator = creator;
        this.member = member;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }
}
