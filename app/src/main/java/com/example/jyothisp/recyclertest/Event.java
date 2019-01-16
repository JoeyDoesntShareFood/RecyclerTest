package com.example.jyothisp.recyclertest;

import java.io.Serializable;

public class Event implements Serializable{

    private String mTitle, mMessage, mDescription, mRules;
    private int mPrize1, mPrize2, mFee;

    //TODO: do something about the description of the event.

    public Event(String title, String message){
        mTitle = title;
        mMessage = message;
    }

    public Event(){}

    public void setmTitle(String title){
        mTitle = title;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmMessage() {
        return mMessage;
    }


    public int getmPrize2() {
        return mPrize2;
    }

    public int getmPrize1() {
        return mPrize1;
    }

    public int getmFee() {
        return mFee;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmRules() {
        return mRules;
    }
}
