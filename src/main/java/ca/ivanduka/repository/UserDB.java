package ca.ivanduka.repository;

import ca.ivanduka.model.*;

import java.util.List;
import java.util.UUID;

public class UserDB implements MySQLAble<User>{

    @Override
    public void create(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public User getOne(UUID userID) {
        return null;
    }

    public User getOne(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void delete(UUID userID) {

    }

    public void delete(String email) {

    }
}
