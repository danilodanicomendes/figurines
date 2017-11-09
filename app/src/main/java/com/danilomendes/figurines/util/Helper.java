package com.danilomendes.figurines.util;

import android.content.ContentValues;
import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.support.annotation.Nullable;

import java.util.Collection;

/**
 * Created by danilo on 29-10-2017.
 */
public final class Helper {

    public static boolean isEmpty(@Nullable Collection<?> list) {
        return list == null || list.isEmpty();
    }

    /**
     * Read the entire contents of a cursor row and store them in a ContentValues.
     * The changes are: convert the values to what they are. Native implementation
     * simply converted everything to String except if it was Blob. This messed up
     * the conversion from int to boolean.
     *
     * To see the native implementation see DatabaseUtils.cursorRowToContentValues.
     *
     * @param cursor the cursor to read from.
     * @param values the {@link ContentValues} to put the row into.
     */
    public static void cursorRowToContentValues(Cursor cursor, ContentValues values) {
        AbstractWindowedCursor awc = (cursor instanceof AbstractWindowedCursor) ?
                (AbstractWindowedCursor) cursor : null;

        if (awc != null) {
            String[] columns = cursor.getColumnNames();
            int length = columns.length;
            for (int i = 0; i < length; i++) {
                switch (awc.getType(i)) {
                    case Cursor.FIELD_TYPE_NULL:
                        break;
                    case Cursor.FIELD_TYPE_INTEGER:
                        values.put(columns[i], cursor.getInt(i));
                        break;
                    case Cursor.FIELD_TYPE_FLOAT:
                        values.put(columns[i], cursor.getFloat(i));
                        break;
                    case Cursor.FIELD_TYPE_STRING:
                        values.put(columns[i], cursor.getString(i));
                        break;
                    case Cursor.FIELD_TYPE_BLOB:
                        values.put(columns[i], cursor.getBlob(i));
                        break;
                }
            }
        }
    }
}
