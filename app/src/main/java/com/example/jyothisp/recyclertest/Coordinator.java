package com.example.jyothisp.recyclertest;

import java.io.Serializable;

public class Coordinator implements Serializable{
    private String name, number;

    public Coordinator(){}

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
