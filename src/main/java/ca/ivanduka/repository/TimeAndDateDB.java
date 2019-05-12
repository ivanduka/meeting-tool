package ca.ivanduka.repository;

import ca.ivanduka.model.*;

import java.util.List;
import java.util.UUID;

public class TimeAndDateDB implements MySQLAble<TimeAndDate> {
    @Override
    public void create(TimeAndDate timeAndDate) {

    }

    @Override
    public void update(TimeAndDate timeAndDate) {

    }

    @Override
    public TimeAndDate getOne(UUID timeAndDateID) {
return null;
    }

    @Override
    public List<TimeAndDate> getAll() {
        return null;
    }

    @Override
    public void delete(UUID timeAndDateID) {

    }
}
