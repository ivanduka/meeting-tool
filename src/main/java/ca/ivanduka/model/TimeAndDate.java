package ca.ivanduka.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TimeAndDate {
    private UUID timeAndDateID;
    private LocalDateTime startTime;
    private List<User> agreedUsers;

    // Creating a new TimeAndDate
    public TimeAndDate(LocalDateTime startTime, User organizer) {
        this.startTime = startTime;
        this.agreedUsers = new ArrayList<>();
        this.agreedUsers.add(organizer);
    }

    // TimeAndDate from a DB
    public TimeAndDate(UUID timeAndDateID, LocalDateTime startTime, List<User> agreedUsers) {
        this.timeAndDateID = timeAndDateID;
        this.startTime = startTime;
        this.agreedUsers = agreedUsers;
    }

    @Override
    public String toString() {
        return "TimeAndDate{" +
                "timeAndDateID=" + timeAndDateID +
                ", startTime=" + startTime +
                ", agreedUsers=" + agreedUsers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeAndDate that = (TimeAndDate) o;
        return timeAndDateID.equals(that.timeAndDateID);
    }

    @Override
    public int hashCode() {
        return timeAndDateID.hashCode();
    }

    public UUID getTimeAndDateID() {
        return timeAndDateID;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public List<User> getAgreedUsers() {
        return agreedUsers;
    }
}
