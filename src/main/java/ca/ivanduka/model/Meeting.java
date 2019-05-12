package ca.ivanduka.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Meeting {
    private UUID meetingID;
    private String name;
    private String location;
    private int durationInMinutes;
    private String details;
    private User organizer;
    private List<TimeAndDate> timeAndDates;
    private List<User> attendees;
    private List<Message> messages;

    // Creating a new Meeting
    public Meeting(String name, String location, int durationInMinutes, String details, User organizer) {
        this.name = name;
        this.location = location;
        this.durationInMinutes = durationInMinutes;
        this.details = details;
        this.organizer = organizer;
        this.meetingID = UUID.randomUUID();
        this.timeAndDates = new ArrayList<>();
        this.attendees = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    // Meeting from a DB
    public Meeting(UUID meetingID, String name, String location, int durationInMinutes, String details,
                   User organizer, List<TimeAndDate> timeAndDates, List<User> attendees, List<Message> messages) {
        this.meetingID = meetingID;
        this.name = name;
        this.location = location;
        this.durationInMinutes = durationInMinutes;
        this.details = details;
        this.organizer = organizer;
        this.timeAndDates = timeAndDates;
        this.attendees = attendees;
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(meetingID, meeting.meetingID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingID);
    }

    @Override
    public String toString() {
        return "Meeting {" +
                "\n\tmeetingID=" + meetingID +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", durationInMinutes=" + durationInMinutes +
                ", details='" + details + '\'' +
                ", \n\ttimeAndDates=" + timeAndDates +
                ", \n\tattendees=" + attendees +
                ", \n\tmessages=" + messages +
                ", \n\torganizer=" + organizer +
                "\n}";
    }

    public UUID getMeetingID() {
        return meetingID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public User getOrganizer() {
        return organizer;
    }

    public List<TimeAndDate> getTimeAndDates() {
        return timeAndDates;
    }


    public List<User> getAttendees() {
        return attendees;
    }

    public List<Message> getMessages() {
        return messages;
    }
}