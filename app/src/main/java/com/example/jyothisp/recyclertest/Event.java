package com.example.jyothisp.recyclertest;

public class Event {

    private String mTitle, mMessage;

    //TODO: do something about the description of the event.

    public Event(String title, String message){
        mTitle = title;
        mMessage = message;
    }

    public Event(){}

    public String getmTitle() {
        return mTitle;
    }

    public String getmMessage() {
        return mMessage;
    }


}
