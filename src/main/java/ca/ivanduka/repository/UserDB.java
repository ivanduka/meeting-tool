package ca.ivanduka.repository;

import ca.ivanduka.model.*;

import javax.sql.DataSource;
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

    public void save(User user) throws Exception {

        try (PreparedStatement ps = this.dataSource
                .getConnection()
                .prepareStatement("INSERT INTO users values (?, ?, ?, ?, ?, ?, ?, ?)")) {
            setUserParams(ps, user);
            ps.execute();
        } catch (Exception e) {
            throw new Exception("User " + user.getEmail() + " could not be inserted");
        }
    }

    public void update(User user) throws Exception {

        try (PreparedStatement ps = this.dataSource
                .getConnection()
                .prepareStatement("UPDATE users SET ID = ?, userType = ?, userStatus = ?, firstName = ?, " +
                        "lastName = ?, email = ?,  password = ?,   zoneOffset = ? WHERE ID = ?")) {

            setUserParams(ps, user);
            ps.setString(9, user.getUserID().toString());
            ps.execute();
        } catch (Exception e) {
            throw new Exception("User " + user.getEmail() + " could not be inserted");
        }
    }

//    public User getOne(String email) throws Exception { // FIXME
//        return UserDB.this.<>getOne(email);
//    }

    public <T> User getOne(T object) throws Exception {
        PreparedStatement ps = null;
        User user = null;

        try {
            if (object instanceof UUID) {
                ps = this.dataSource
                        .getConnection()
                        .prepareStatement("SELECT * FROM users WHERE ID = ?");
            } else if (object instanceof String) {
                ps = this.dataSource
                        .getConnection()
                        .prepareStatement("SELECT * FROM users WHERE email = ?");
            }

            if (ps != null) {
                ps.setString(1, object.toString());
                ResultSet res = ps.executeQuery();
                res.next();
                user = readUser(res);
            }
        } catch (Exception e) {
            throw new Exception("User " + object.toString() + " not found");
        } finally {
            if (ps != null) ps.close();
        }

        return user;
    }

    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();

        try (PreparedStatement ps = this.dataSource
                .getConnection()
                .prepareStatement("SELECT * FROM users")) {
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                users.add(readUser(res));
            }
        } catch (Exception e) {
            throw new Exception("Error retrieving all users form the DB");
        }

        return users;
    }


    public void delete(UUID userID) throws Exception {

        try (PreparedStatement ps = this.dataSource
                .getConnection()
                .prepareStatement("DELETE FROM users WHERE ID = ?")) {

            ps.setString(1, userID.toString());
            ps.execute();
        } catch (Exception e) {
            throw new Exception("User " + userID + " could not be deleted");
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
        ps.setString(1, user.getUserID().toString());
        ps.setString(2, user.getUserType().toString());
        ps.setString(3, user.getUserStatus().toString());
        ps.setString(4, user.getFirstName());
        ps.setString(5, user.getLastName());
        ps.setString(6, user.getEmail());
        ps.setString(7, user.getPassword());
        ps.setString(8, user.getZoneOffset().toString());
    }
}
