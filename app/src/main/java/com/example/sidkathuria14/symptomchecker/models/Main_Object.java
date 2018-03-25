package com.example.sidkathuria14.symptomchecker.models;

import java.util.ArrayList;

/**
 * Created by sidkathuria14 on 24/3/18.
 */

public class Main_Object {

    Issue Issue;
    ArrayList<Specialisation_Object> Specialisation;

    public Issue getIssue() {
        return Issue;
    }

    public void setIssue(Issue issue) {
        this.Issue = issue;
    }

    public ArrayList<Specialisation_Object> getSpecialisation() {
        return Specialisation;
    }
}
