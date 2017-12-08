package com.danilomendes.figurines.util

import android.content.ContentValues
import android.database.Cursor

/**
 * Read the entire contents of a cursor row and store them in a ContentValues.
 * The changes are: convert the values to what they are. Native implementation
 * simply converted everything to String except if it was Blob. This messed up
 * the conversion from int to boolean.
 *
 * To see the native implementation see DatabaseUtils.cursorRowToContentValues.
 *
 * @param values the {@link ContentValues} to put the row into.
 */
fun Cursor.rowToContentValues(values: ContentValues) {
    val columns = this.columnNames
    val length = columns.size
    for (i in 0 until length) {
        when (this.getType(i)) {
            Cursor.FIELD_TYPE_INTEGER -> values.put(columns[i], this.getInt(i))
            Cursor.FIELD_TYPE_FLOAT -> values.put(columns[i], this.getFloat(i))
            Cursor.FIELD_TYPE_STRING -> values.put(columns[i], this.getString(i))
            Cursor.FIELD_TYPE_BLOB -> values.put(columns[i], this.getBlob(i))
            Cursor.FIELD_TYPE_NULL -> {
            }
        }
    }
}