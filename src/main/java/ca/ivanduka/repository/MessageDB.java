package ca.ivanduka.repository;

import ca.ivanduka.model.*;

import java.util.List;
import java.util.UUID;

public class MessageDB implements MySQLAble<Message> {
    @Override
    public void create(Message message) {

    }

    @Override
    public void update(Message message) {

    }

    @Override
    public Message getOne(UUID messageID) {
        return null;
    }

    @Override
    public List<Message> getAll() {
        return null;
    }

    @Override
    public void delete(UUID messageID) {

    }
}
