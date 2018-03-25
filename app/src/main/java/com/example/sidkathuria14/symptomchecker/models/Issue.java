package com.example.sidkathuria14.symptomchecker.models;

/**
 * Created by sidkathuria14 on 24/3/18.
 */

public class Issue {

    int ID,ranking; double Accuracy;String Name,ProfName,IcdName;

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public double getAccuracy() {
        return Accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.Accuracy = accuracy;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getProfName() {
        return ProfName;
    }

    public void setProfName(String profName) {
        ProfName = profName;
    }

    public String getIcdName() {
        return IcdName;
    }

    public void setIcdName(String icdName) {
        IcdName = icdName;
    }
}
