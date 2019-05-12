package ca.ivanduka.repository;

import java.sql.ResultSet;

class Utils {
    static void close(ResultSet res) {
        try {
            if (res != null) res.close();
        } catch (Exception e) {
            System.out.println("WTF?!\n" + e);
        }
    }
}
