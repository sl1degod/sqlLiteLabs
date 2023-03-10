package com.example.sql;

public class Singleton {
    private static Singleton INSTANCE;

    private String email;

    private Singleton() {}

    public static Singleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton();
        }
        return INSTANCE;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
//    123

//    zxc

//    12333

//    12121212121
}
