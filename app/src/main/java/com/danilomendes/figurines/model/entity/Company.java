package com.danilomendes.figurines.model.entity;

import android.content.ContentValues;

import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.danilomendes.figurines.model.CompanyTable;

/**
 * Created by danilo on 11-10-2017.
 */
@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
public class Company implements IEntity {

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
        codeName = values.getAsString(CompanyTable._CODE_NAME);
        name = values.getAsString(CompanyTable._NAME);
        address = values.getAsString(CompanyTable._ADDRESS);
        postalCode = values.getAsString(CompanyTable._POSTAL_CODE);
        city = values.getAsString(CompanyTable._CITY);
        logo = values.getAsString(CompanyTable._LOGO);
        insetColor = values.getAsString(CompanyTable._INSET_COLOR);
        shortDescription = values.getAsString(CompanyTable._SHORT_DESCRIPTION);
        coordinates = new Coordinate(values.getAsLong(CompanyTable._COORDINATE_LATITUDE),
                values.getAsLong(CompanyTable._COORDINATE_LONGITUDE));
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(CompanyTable._CODE_NAME, codeName);
        values.put(CompanyTable._NAME, name);
        values.put(CompanyTable._ADDRESS, address);
        values.put(CompanyTable._POSTAL_CODE, postalCode);
        values.put(CompanyTable._CITY, city);
        values.put(CompanyTable._LOGO, logo);
        values.put(CompanyTable._COORDINATE_LATITUDE, coordinates.getLatitude());
        values.put(CompanyTable._COORDINATE_LONGITUDE, coordinates.getLongitude());
        values.put(CompanyTable._INSET_COLOR, insetColor);
        values.put(CompanyTable._SHORT_DESCRIPTION, shortDescription);
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
