package ca.ivanduka.model;

public enum UserStatuses {
    NOT_CONFIRMED, // Email is not confirmed
    CONFIRMED, // Email is confirmed - user can create new meetings
    SUSPENDED, // User is suspended
}