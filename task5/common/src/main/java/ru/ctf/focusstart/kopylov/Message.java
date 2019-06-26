package ru.ctf.focusstart.kopylov;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

@JsonAutoDetect
public class Message {
    @JsonProperty("Author")
    private String author;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("Time")
    private String time;

    @JsonProperty("Type")
    private String type;

    public Message() {
    }

    public Message(String author, String message, String type) {
        this.author = author;
        this.message = message;
        this.type = type;
        Date time = new Date();
        SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss");
        this.time = formatTime.format(time);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Message{" +
                "author='" + author + '\'' +
                ", message='" + message + '\'' +
                ", time='" + time + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
