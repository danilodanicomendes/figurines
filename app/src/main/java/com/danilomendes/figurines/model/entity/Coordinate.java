package com.danilomendes.figurines.model.entity;

import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by danilo on 11-10-2017.
 */
@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
public class Coordinate {

    private long latitude;

    private long longitude;

    public Coordinate() {

    }

    public Coordinate(long latitude, long longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }
}
