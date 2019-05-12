package ca.ivanduka.repository;

import ca.ivanduka.model.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDB {

    private static DataSource dataSource;

    public UserDB(DataSource dataSource) {
        UserDB.dataSource = dataSource;
    }

    public void create(User user) {

    }

    public void update(User user) {

    }

    public User getOne(UUID userID) {
        return null;
    }

    public User getOne(String email) {
        return null;
    }

    public static List<User> getAll() {
        List<User> users = new ArrayList<>();

        ResultSet res = null;

        try {
            res = dataSource
                    .getConnection()
                    .createStatement()
                    .executeQuery("SELECT * FROM users");

            while (res.next()) {
                UUID userID = UUID.fromString(res.getString("ID"));
                UserTypes userType = UserTypes.valueOf(res.getString("userType"));
                UserStatuses userStatus = UserStatuses.valueOf(res.getString("userStatus"));
                String firstName = res.getString("firstName");
                String lastName = res.getString("lastName");
                String email = res.getString("email");
                String password = res.getString("password");
                ZoneOffset zoneOffset = ZoneOffset.of(res.getString("zoneOffset"));

                List<Meeting> meetings = new MeetingDB(dataSource).getAllByUserID(userID); // TODO: find and attach meetings


                User user = new User(userID, userType, firstName, lastName, email, password,
                        userStatus, zoneOffset, meetings);
                users.add(user);
            }

            return users;
        } catch (Exception e) {
            System.out.println("WTF?!\n" + e);
        } finally {
            Utils.close(res);
        }

        return users;
    }


    public void delete(UUID userID) {

    }

    public void delete(String email) {

    }
}
