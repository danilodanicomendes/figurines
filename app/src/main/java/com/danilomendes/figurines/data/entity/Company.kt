package com.danilomendes.figurines.data.entity

import android.content.ContentValues
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.danilomendes.figurines.data.local.CompanyLocalDataSource

/**
 * Company entity.
 *
 * Note: The primary constructor has default values because otherwise the class wouldn't
 * have an empty constructor for Logan Square parser to use (At this time they did not
 * added support for Kotlin yet)
 *
 * Created by danilo on 21-11-2017.
 */
@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
data class Company(var codeName: String = "",
                   var name: String = "",
                   var address: String = "",
                   var postalCode: String = "",
                   var city: String = "",
                   var logo: String = "",
                   var coordinates: Coordinate = Coordinate(),
                   var insetColor: String = "",
                   var shortDescription: String = "") : IEntity {

    constructor(values: ContentValues) : this() {
        codeName = values.getAsString(CompanyLocalDataSource._CODE_NAME)
        name = values.getAsString(CompanyLocalDataSource._NAME)
        address = values.getAsString(CompanyLocalDataSource._ADDRESS)
        postalCode = values.getAsString(CompanyLocalDataSource._POSTAL_CODE)
        city = values.getAsString(CompanyLocalDataSource._CITY)
        logo = values.getAsString(CompanyLocalDataSource._LOGO)
        insetColor = values.getAsString(CompanyLocalDataSource._INSET_COLOR)
        shortDescription = values.getAsString(CompanyLocalDataSource._SHORT_DESCRIPTION)
        coordinates = Coordinate(values.getAsLong(CompanyLocalDataSource._COORDINATE_LATITUDE)!!,
                values.getAsLong(CompanyLocalDataSource._COORDINATE_LONGITUDE)!!)
    }


    override fun toContentValues(): ContentValues {
        val values = ContentValues()
        values.put(CompanyLocalDataSource._CODE_NAME, codeName)
        values.put(CompanyLocalDataSource._NAME, name)
        values.put(CompanyLocalDataSource._ADDRESS, address)
        values.put(CompanyLocalDataSource._POSTAL_CODE, postalCode)
        values.put(CompanyLocalDataSource._CITY, city)
        values.put(CompanyLocalDataSource._LOGO, logo)
        values.put(CompanyLocalDataSource._COORDINATE_LATITUDE, coordinates.latitude)
        values.put(CompanyLocalDataSource._COORDINATE_LONGITUDE, coordinates.longitude)
        values.put(CompanyLocalDataSource._INSET_COLOR, insetColor)
        values.put(CompanyLocalDataSource._SHORT_DESCRIPTION, shortDescription)
        return values
    }
}