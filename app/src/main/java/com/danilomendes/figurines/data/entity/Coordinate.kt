package com.danilomendes.figurines.data.entity

import com.bluelinelabs.logansquare.annotation.JsonObject

/**
 * Created by danilo on 08-12-2017.
 */
@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
data class Coordinate(var latitude: Long = 0L, var longitude: Long = 0L)