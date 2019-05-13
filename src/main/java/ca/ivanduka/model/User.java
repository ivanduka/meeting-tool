package ca.ivanduka.model;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private UUID userID;
    private UserTypes userType;
    private UserStatuses userStatus;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ZoneOffset zoneOffset;

    // New user registration
    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userID = UUID.randomUUID();
        this.userType = UserTypes.USER;
        this.userStatus = UserStatuses.NOT_CONFIRMED;
        this.zoneOffset = ZoneOffset.of("+00:00");
    }

    // User from a DB
    public User(UUID userID, UserTypes userType, String firstName, String lastName, String email,
                String password, UserStatuses userStatus, ZoneOffset zoneOffset) {
        this.userID = userID;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userStatus = userStatus;
        this.zoneOffset = zoneOffset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userID.equals(user.userID);
    }

    @Override
    public int hashCode() {
        return userID.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userType=" + userType +
                ", userStatus=" + userStatus +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", zoneOffset=" + zoneOffset +
                '}';
    }

    public UUID getUserID() {
        return userID;
    }

    public UserTypes getUserType() {
        return userType;
    }

    public void setUserType(UserTypes userType) {
        this.userType = userType;
    }

    public UserStatuses getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatuses userStatus) {
        this.userStatus = userStatus;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ZoneOffset getZoneOffset() {
        return zoneOffset;
    }

    public void setZoneOffset(ZoneOffset zoneOffset) {
        this.zoneOffset = zoneOffset;
    }
}
