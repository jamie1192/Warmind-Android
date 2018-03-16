package com.example.jamie.warmindjsonfunctions;

/**
 * Created by jastl on 11/06/2017.
 */

public class userData {
    private String username;
    private String console;

    public userData(String username, String console) {
        this.username= username;
        this.console = console;
    }

    public String getUsername() { return username; }

    public String getConsole() { return console; }
}
