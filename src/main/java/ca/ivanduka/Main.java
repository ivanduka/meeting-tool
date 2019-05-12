package ca.ivanduka;

import ca.ivanduka.model.*;
import ca.ivanduka.repository.*;
import com.mysql.cj.jdbc.MysqlDataSource;
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

            new UserDB(ds);
            List<User> users = UserDB.getAll();

            for (var user : users) System.out.println(user);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
