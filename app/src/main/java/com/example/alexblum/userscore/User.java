package com.example.alexblum.userscore;

/**
 * Created by Alexander on 11/30/2016.
 */

public class User {

    public String score;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String score, String email) {
        this.score = score;
        this.email = email;
    }

}
