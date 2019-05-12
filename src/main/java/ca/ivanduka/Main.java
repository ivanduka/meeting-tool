package ca.ivanduka;

import ca.ivanduka.model.*;
import java.time.*;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Ivan", "Duka",
                "ivan.duka.pm@gmail.com", "123456");
        User user2 = new User("John", "Snow",
                "j.snow@gmail.com", "3222");

        Meeting meeting = new Meeting("Initial meeting", "Immigrant Center", 45,
                "Just to meet everyone", user1);

//        TimeAndDate timeAndDate = new TimeAndDate()

//        System.out.println(user1);
//        System.out.println(user2);
        System.out.println(meeting);

        LocalDateTime localDateTime = LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);
        ZoneOffset offset = ZoneOffset.of("+02:00");
        ZoneOffset offset2 = ZoneOffset.of("+00:00");
        ZoneOffset offset3 = ZoneOffset.of("-10:00");

        OffsetDateTime offSetByTwo = OffsetDateTime.of(localDateTime, offset);
        System.out.println(offSetByTwo);
        System.out.println(offSetByTwo.withOffsetSameInstant(offset2));
        System.out.println(offSetByTwo.withOffsetSameInstant(offset3));
        System.out.println(offset3);

    }
}
