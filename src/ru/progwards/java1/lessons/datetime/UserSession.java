package ru.progwards.java1.lessons.datetime;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserSession {
    private int sessionHandle;
    private String userName;
    private LocalDateTime lastAccess;

    public UserSession(String userName) {
        this.userName = userName;
        lastAccess = LocalDateTime.now();
        ZoneOffset zoneOffset = ZoneOffset.of("+00:00");
        sessionHandle = new Random().nextInt();
    }
    public void updateLastAccess(){
        lastAccess = LocalDateTime.now();
    }

    public int getSessionHandle() {
        return sessionHandle;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

}
