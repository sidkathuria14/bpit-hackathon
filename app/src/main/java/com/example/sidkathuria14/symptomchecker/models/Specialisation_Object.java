package com.example.sidkathuria14.symptomchecker.models;

/**
 * Created by sidkathuria14 on 24/3/18.
 */

public class Specialisation_Object {

    int id,SpecialistId;
    String name;



    public void setId(int id) {
        this.id = id;
    }

    public void setSpecialistId(int specialistId) {
        SpecialistId = specialistId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getSpecialistId() {
        return SpecialistId;
    }

    public String getName() {
        return name;
    }
}
