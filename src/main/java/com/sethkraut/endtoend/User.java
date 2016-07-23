package com.sethkraut.endtoend;

/**
 * User class
 */
public class User {
    private final String username;
    private final String firstName;
    private final String lastName;

    public static User withName(String firstName, String lastName) {
        return new User( (firstName.charAt(0) + lastName).toLowerCase(), firstName, lastName);
    }

    public User(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
