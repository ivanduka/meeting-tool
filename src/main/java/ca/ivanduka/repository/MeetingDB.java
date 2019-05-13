package ca.ivanduka.repository;

import ca.ivanduka.model.*;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MeetingDB {

    private static DataSource dataSource;

    MeetingDB(DataSource dataSource) {
        MeetingDB.dataSource = dataSource;
    }

    public void create(Meeting meeting) {

    }

    public void update(Meeting meeting) {

    }

    public Meeting getOne(UUID meetingID) {
        return null;
    }

    public List<Meeting> getAll() {
        return null;
    }

    List<Meeting> getAllByUserID(UUID userID) {
        List<Meeting> meetings = new ArrayList<>();

        try (ResultSet res = dataSource
                .getConnection()
                .createStatement()
                .executeQuery("SELECT * FROM meetings WHERE organizerID = '" + userID + "'")) {

            while (res.next()) {
                UUID meetingID = UUID.fromString(res.getString("ID"));
                String name = res.getString("name");
                String location = res.getString("location");
                String details = res.getString("details");
                int duration = res.getInt("duration");
                List<TimeAndDate> timeAndDates = null;// FIXME
                List<User> attendees = null;// FIXME
                List<Message> messages = null; // FIXME

                var meeting = new Meeting(meetingID, name, location, duration, details,
                        userID, timeAndDates, attendees, messages);

                meetings.add(meeting);
            }

            return meetings;

        } catch (Exception e) {
            System.out.println("WTF?!\n" + e);
        }

        return meetings;
    }

    public void delete(UUID meetingID) {

    }
}
