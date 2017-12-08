package com.danilomendes.figurines.data.entity

import android.content.ContentValues

/**
 * Created by danilo on 29-10-2017.
 */
interface IEntity {
    fun toContentValues(): ContentValues
}
