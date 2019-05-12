package ca.ivanduka.repository;

import ca.ivanduka.model.*;

import java.util.List;
import java.util.UUID;

public class MeetingDB implements MySQLAble<Meeting> {

    @Override
    public void create(Meeting meeting) {

    }

    @Override
    public void update(Meeting meeting) {

    }

    @Override
    public Meeting getOne(UUID meetingID) {
        return null;
    }

    @Override
    public List<Meeting> getAll() {
        return null;
    }

    @Override
    public void delete(UUID meetingID) {

    }
}
