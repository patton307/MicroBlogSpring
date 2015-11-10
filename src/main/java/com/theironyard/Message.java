package com.theironyard;

/**
 * Created by landonkail on 11/9/15.
 */
public class Message {
    String text;
    int id;

    public Message(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public Message() {

    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

}

