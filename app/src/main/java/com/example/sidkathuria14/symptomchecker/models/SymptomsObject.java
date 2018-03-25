package com.example.sidkathuria14.symptomchecker.models;

/**
 * Created by sidkathuria14 on 25/3/18.
 */

public class SymptomsObject {

    int ID;String Name;

    public SymptomsObject(int id, String name) {
        this.ID = id;
        this.Name = name;
    }

    public int getId() {
        return ID;
    }

    public String getName() {
        return Name;
    }
}
