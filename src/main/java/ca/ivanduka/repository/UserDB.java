package ca.ivanduka.repository;

import ca.ivanduka.model.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDB {

    private DataSource dataSource;

    public UserDB(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(User user) {
        try (Connection conn = this.dataSource.getConnection();
             PreparedStatement ps = conn
                     .prepareStatement("INSERT INTO users(firstName, lastName, email, password, userType, " +
                             "userStatus, zoneOffset, ID) values (?, ?, ?, ?, ?, ?, ?, ?)")) {
            setUserParams(ps, user);
            ps.executeUpdate();
            if (ps.getUpdateCount() == 0) throw new Exception(user.getUserID() + " was not saved");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(User user) {
        try (PreparedStatement ps = this.dataSource
                .getConnection()
                .prepareStatement("UPDATE users SET firstName = ?, lastName = ?, email = ?, password = ?, " +
                        "userType = ?, userStatus = ?, zoneOffset = ? WHERE ID = ?")) {
            setUserParams(ps, user);
            ps.executeUpdate();
            if (ps.getUpdateCount() == 0) throw new Exception(user.getUserID() + " was not updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getOne(String email) {
        User user = null;

        try (PreparedStatement ps = this.dataSource
                .getConnection()
                .prepareStatement("SELECT * FROM users WHERE email = ?")) {
            ps.setString(1, email);

            ResultSet res = ps.executeQuery();
            res.next();
            user = readUser(res);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try (PreparedStatement ps = this.dataSource
                .getConnection()
                .prepareStatement("SELECT * FROM users")) {
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                users.add(readUser(res));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }


    public void delete(UUID userID) {
        try (PreparedStatement ps = this.dataSource
                .getConnection()
                .prepareStatement("DELETE FROM users WHERE ID = ?")) {
            ps.setString(1, userID.toString());
            ps.executeUpdate();
            if (ps.getUpdateCount() == 0) throw new Exception(userID + " was not deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private User readUser(ResultSet res) throws Exception {
        UUID userID = UUID.fromString(res.getString("ID"));
        UserTypes userType = UserTypes.valueOf(res.getString("userType"));
        UserStatuses userStatus = UserStatuses.valueOf(res.getString("userStatus"));
        String firstName = res.getString("firstName");
        String lastName = res.getString("lastName");
        String email = res.getString("email");
        String password = res.getString("password");
        ZoneOffset zoneOffset = ZoneOffset.of(res.getString("zoneOffset"));

        return new User(userID, userType, firstName, lastName, email, password,
                userStatus, zoneOffset);
    }

    private void setUserParams(PreparedStatement ps, User user) throws Exception {
        ps.setString(1, user.getFirstName());
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getPassword());
        ps.setString(5, user.getUserType().toString());
        ps.setString(6, user.getUserStatus().toString());
        ps.setString(7, user.getZoneOffset().toString());
        ps.setString(8, user.getUserID().toString());
    }
}
