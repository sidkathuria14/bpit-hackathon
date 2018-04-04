package com.example.sidkathuria14.symptomchecker.models;

/**
 * Created by sidkathuria14 on 4/4/18.
 */

public class Suggestion {

    String name;
    String manufacturer;
    String pForm;
    String packSize;
    String mrp;

    public Suggestion(String name, String manufacturer, String pForm, String packSize, String mrp) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.pForm = pForm;
        this.packSize = packSize;
        this.mrp = mrp;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getpForm() {
        return pForm;
    }

    public String getPackSize() {
        return packSize;
    }

    public String getMrp() {
        return mrp;
    }

}
