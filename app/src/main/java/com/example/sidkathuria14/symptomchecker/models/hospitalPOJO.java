package com.example.sidkathuria14.symptomchecker.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class hospitalPOJO {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("hospital_name")
    @Expose
    private String hospitalName;
    @SerializedName("totalBeds")
    @Expose
    private Integer totalBeds;
    @SerializedName("availableBeds")
    @Expose
    private Integer availableBeds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public Integer getTotalBeds() {
        return totalBeds;
    }

    public void setTotalBeds(Integer totalBeds) {
        this.totalBeds = totalBeds;
    }

    public Integer getAvailableBeds() {
        return availableBeds;
    }

    public void setAvailableBeds(Integer availableBeds) {
        this.availableBeds = availableBeds;
    }

}
