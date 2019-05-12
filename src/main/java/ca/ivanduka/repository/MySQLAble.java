package ca.ivanduka.repository;

import java.util.List;
import java.util.UUID;

interface MySQLAble<T> {
    void create(T object);
    void update(T object);
    T getOne(UUID objectID);
    List<T> getAll();
    void delete(UUID objectID);
}
