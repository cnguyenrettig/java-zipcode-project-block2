package com.kenzie.app.data.zipcode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {
    //declare properties
    @JsonProperty(value="place name")
    private String place_name;

    @JsonProperty(value="post code")
    private String postCode;

    private String longitude;

    //define methods - getter/setter
    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getLongitude() {
        return longitude;
    }
}
