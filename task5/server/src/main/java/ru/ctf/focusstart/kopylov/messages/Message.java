package ru.ctf.focusstart.kopylov.messages;

import ru.ctf.focusstart.kopylov.users.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Message {
    private final String userName;
    private final String message;
    private final String date;

    public Message(User user, String message) {
        this.userName = user.getName();
        this.message = message;
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        this.date = dateFormat.format(calendar.getTime());
    }

    public String getMessage() {
        return String.format("[%s] %s: %s", date, userName, message);
    }
}
