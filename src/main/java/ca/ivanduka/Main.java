package ca.ivanduka;

import ca.ivanduka.model.*;
import ca.ivanduka.repository.*;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        try {
            MysqlDataSource ds = new MysqlDataSource();
            ds.setServerName("localhost");
            ds.setPort(3306);
            ds.setRequireSSL(false);
            ds.setServerTimezone("UTC");
            ds.setUser("root");
            ds.setPassword("qwerty123");
            ds.setDatabaseName("meetingTool");

            UserDB userDB = new UserDB(ds);

            User newUser = new User("User " + LocalDateTime.now().getSecond(), "User " +
                    LocalDateTime.now().getSecond(), "john@winterfell.com " + LocalDateTime.now().getSecond(),
                    "qwerty");
            userDB.save(newUser);

            User user2 = userDB.getOne(UUID.fromString("b57fdce4-7341-11e9-b34c-3534645992cf"));
            user2.setUserStatus(UserStatuses.NOT_CONFIRMED);
            userDB.update(user2);

            System.out.println();
            List<User> users = userDB.getAll();
            for (var user : users) System.out.println(user);

            userDB.delete(newUser.getUserID());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

//        class Test {
//            void print(String string) {
//                this.<String>print(string);
//            }
//
//            <T> void print(T object) {
//                System.out.println(object.toString());
//            }
//        }

    }
}
