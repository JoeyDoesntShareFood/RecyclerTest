package com.example.jyothisp.recyclertest;

import java.io.Serializable;

public class Event implements Serializable {

    private String mTitle, mMessage, mDescription, mRules , photoURl;
    private String mPrize1, mPrize2, mPrize3, mFee;
    //TODO: do something about the description of the event.

    public Event(String title, String message, String mDescription, String mRules, String mPrize1, String mPrize2, String mPrize3, String mFee) {
        mTitle = title;
        mMessage = message;
        this.mDescription = mDescription;
        this.mRules = mRules;
//        this.photoURl = photoURl;
        this.mPrize1 = mPrize1;
        this.mPrize2 = mPrize2;
        this.mPrize3 = mPrize3;
        this.mFee = mFee;
    }

    public Event (String title , String message){
        mTitle = title;
        mMessage = message;
    }
    public Event() {
    }

    public void setmTitle(String title) {
        mTitle = title;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmMessage() {
        return mMessage;
    }


    public String getmPrize2() {
        return mPrize2;
    }

    public String getmPrize1() {
        return mPrize1;
    }

    public String getmFee() {
        return mFee;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmRules() {
        return mRules;
    }

    public String getPhotoURl() {
        return photoURl;
    }

    public class coordinator {
        private String name, phone_number;

        public coordinator(String name, String phone_number) {
            this.name = name;
            this.phone_number = phone_number;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public String getName() {
            return name;
        }


    }

}
