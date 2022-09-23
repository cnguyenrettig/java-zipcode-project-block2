package com.kenzie.app.data.zipcode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ZipCodeDTO {
    //declare properties
    private String state;
    private List<Place> places;

    //getter/setter

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}
