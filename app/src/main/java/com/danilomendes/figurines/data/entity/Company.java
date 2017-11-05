package com.danilomendes.figurines.data.entity;

import android.content.ContentValues;

import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.danilomendes.figurines.data.local.CompanyLocalDataSource;

/**
 * Created by danilo on 11-10-2017.
 */
@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
public final class Company implements IEntity {

    private String codeName;

    private String name;

    private String address;

    private String postalCode;

    private String city;

    private String logo;

    private Coordinate coordinates;

    private String insetColor;

    private String shortDescription;

    public Company() {
    }

    @SuppressWarnings("unused")
    public Company(ContentValues values) {
        codeName = values.getAsString(CompanyLocalDataSource._CODE_NAME);
        name = values.getAsString(CompanyLocalDataSource._NAME);
        address = values.getAsString(CompanyLocalDataSource._ADDRESS);
        postalCode = values.getAsString(CompanyLocalDataSource._POSTAL_CODE);
        city = values.getAsString(CompanyLocalDataSource._CITY);
        logo = values.getAsString(CompanyLocalDataSource._LOGO);
        insetColor = values.getAsString(CompanyLocalDataSource._INSET_COLOR);
        shortDescription = values.getAsString(CompanyLocalDataSource._SHORT_DESCRIPTION);
        coordinates = new Coordinate(values.getAsLong(CompanyLocalDataSource._COORDINATE_LATITUDE),
                values.getAsLong(CompanyLocalDataSource._COORDINATE_LONGITUDE));
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(CompanyLocalDataSource._CODE_NAME, codeName);
        values.put(CompanyLocalDataSource._NAME, name);
        values.put(CompanyLocalDataSource._ADDRESS, address);
        values.put(CompanyLocalDataSource._POSTAL_CODE, postalCode);
        values.put(CompanyLocalDataSource._CITY, city);
        values.put(CompanyLocalDataSource._LOGO, logo);
        values.put(CompanyLocalDataSource._COORDINATE_LATITUDE, coordinates.getLatitude());
        values.put(CompanyLocalDataSource._COORDINATE_LONGITUDE, coordinates.getLongitude());
        values.put(CompanyLocalDataSource._INSET_COLOR, insetColor);
        values.put(CompanyLocalDataSource._SHORT_DESCRIPTION, shortDescription);
        return values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getInsetColor() {
        return insetColor;
    }

    public void setInsetColor(String insetColor) {
        this.insetColor = insetColor;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
