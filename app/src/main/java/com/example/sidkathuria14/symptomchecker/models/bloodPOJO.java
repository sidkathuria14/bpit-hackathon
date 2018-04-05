package com.example.sidkathuria14.symptomchecker.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class bloodPOJO {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("blood")
    @Expose
    private Integer blood;
    @SerializedName("bank")
    @Expose
    private Integer bank;
    @SerializedName("availableBlood")
    @Expose
    private Integer availableBlood;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlood() {
        return blood;
    }

    public void setBlood(Integer blood) {
        this.blood = blood;
    }

    public Integer getBank() {
        return bank;
    }

    public void setBank(Integer bank) {
        this.bank = bank;
    }

    public Integer getAvailableBlood() {
        return availableBlood;
    }

    public void setAvailableBlood(Integer availableBlood) {
        this.availableBlood = availableBlood;
    }
}
