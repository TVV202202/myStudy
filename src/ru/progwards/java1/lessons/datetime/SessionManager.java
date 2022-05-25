package ru.progwards.java1.lessons.datetime;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class SessionManager {
    private static Map<Integer, UserSession> sessions = new HashMap<>();
    private int sessionValid;

    public SessionManager(int sessionValid) {
        this.sessionValid = sessionValid;
    }

    public void add(UserSession userSession) {
        sessions.put(userSession.getSessionHandle(), userSession);
    }

    public UserSession find(String userName) {
        for (Map.Entry<Integer, UserSession> entry : sessions.entrySet()) {
            if (entry.getValue().getUserName().equals(userName)) {
                LocalDateTime temp = entry.getValue().getLastAccess();
                Duration duration1 = Duration.between(LocalDateTime.now(), temp);
                Duration duration2 = Duration.between(temp.plusSeconds(sessionValid), temp);
                if (duration1.compareTo(duration2) < 0) {
                    return null;
                } else {
                    UserSession userSession = new UserSession(userName);
                    sessions.put(userSession.getSessionHandle(), userSession);
                    return sessions.get(userSession.getSessionHandle());
                }
            }
        }
        return null;
    }

    public UserSession get(int sessionHandle) {
        if (!sessions.containsKey(sessionHandle)) return null;
        LocalDateTime temp = sessions.get(sessionHandle).getLastAccess();
        Duration duration1 = Duration.between(LocalDateTime.now(), temp);
        Duration duration2 = Duration.between(temp.plusSeconds(sessionValid), temp);
        if (duration1.compareTo(duration2) < 0) {
            return null;
        } else {
            UserSession userSession = new UserSession(sessions.get(sessionHandle).getUserName());
            sessions.put(sessionHandle, userSession);
            return sessions.get(sessionHandle);
        }
    }

    public void delete(int sessionHandle) {
        sessions.remove(sessionHandle);
    }

    public void deleteExpired() {
        LocalDateTime tst = LocalDateTime.now();

        var entries = sessions.entrySet();
        var entryIterator = entries.iterator();
        while (entryIterator.hasNext()){
            var session = entryIterator.next();
            LocalDateTime temp = session.getValue().getLastAccess();
            Duration duration1 = Duration.between(tst, temp);
            //System.out.println(duration1);
            Duration duration2 = Duration.between(temp.plusSeconds(sessionValid), temp);
            //System.out.println(duration2);
            if (duration1.compareTo(duration2) < 0) {
                entryIterator.remove();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        SessionManager sessionManager = new SessionManager(1);

        System.out.println(sessionManager.find("test1"));
        System.out.println();
        UserSession test1 = new UserSession("test_1") ;
        sessionManager.add(test1);

        UserSession test2 = new UserSession("test_2") ;
        sessionManager.add(test2);

        for (UserSession el: sessionManager.sessions.values())
            System.out.println(el.getUserName());
        System.out.println();

        System.out.println(sessionManager.get(test2.getSessionHandle()).getUserName());
        System.out.println(sessionManager.get(test2.getSessionHandle()).getUserName());
        System.out.println(sessionManager.get(test2.getSessionHandle()).getUserName());
        Thread.sleep(1001);
        System.out.println(sessionManager.get(test2.getSessionHandle()));
        System.out.println();
        System.out.println(sessionManager.sessions.values());
        System.out.println();

        UserSession test3 = new UserSession("test_3") ;
        Thread.sleep(500);
        sessionManager.add(test3);
        UserSession test4 = new UserSession("test_4") ;
        Thread.sleep(500);
        sessionManager.add(test4);

        for (UserSession el: sessionManager.sessions.values())
            System.out.println(el.getUserName());
        System.out.println();
        sessionManager.deleteExpired();
        for (UserSession el: sessionManager.sessions.values())
            System.out.println(el.getUserName());
        sessionManager.delete(test4.getSessionHandle());
        System.out.println();
        for (UserSession el: sessionManager.sessions.values())
            System.out.println(el.getUserName());
        System.out.println("tst");
    }
}
