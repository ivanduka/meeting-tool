package ca.ivanduka;

import ca.ivanduka.model.*;
import ca.ivanduka.repository.*;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.time.LocalDateTime;
import java.util.List;

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

            User user2 = userDB.getOne("ivan.duka@ukr.net");
            if (user2.getUserStatus().equals(UserStatuses.NOT_CONFIRMED)) {
                user2.setUserStatus(UserStatuses.CONFIRMED);
            } else {
                user2.setUserStatus(UserStatuses.NOT_CONFIRMED);
            }
            userDB.update(user2);

            System.out.println();
            List<User> users = userDB.getAll();
            for (var user : users) System.out.println(user);

            userDB.delete(newUser.getUserID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
