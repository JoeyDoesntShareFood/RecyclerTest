package com.example.jyothisp.recyclertest;

import java.util.ArrayList;

public class Department {
    private String mDepartmentName;
    private ArrayList<Event> mDepartmentEvents;

    public Department(String mDepartmentName, ArrayList<Event> mDepartmentEvents){
        this.mDepartmentEvents = mDepartmentEvents;
        this.mDepartmentName = mDepartmentName;
    }

    public String getmDepartmentName() {
        return mDepartmentName;
    }

    public ArrayList<Event> getmDepartmentEvents() {
        return mDepartmentEvents;
    }
}
