package com.danilomendes.figurines.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.danilomendes.figurines.model.entity.IEntity;
import com.danilomendes.figurines.utils.Helper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by danilo on 29-10-2017.
 */
abstract class AbstractTable<T extends IEntity> implements BaseColumns {

    SQLiteDatabase db;

    abstract void onCreate(SQLiteDatabase db);
    abstract String getTableName();
    abstract String[] getColumns();
    abstract T instantiateEntity(ContentValues values);

    void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    void insertAll(List<T> entries) {
        if (Helper.isEmpty(entries)) {
            return;
        }

        db.beginTransaction();
        try {
            for (T entry : entries) {
                db.insertWithOnConflict(getTableName(), null,
                        entry.toContentValues(), SQLiteDatabase.CONFLICT_REPLACE);
            }

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @NonNull
    Single<List<T>> getAll() {
        Cursor cursor = null;
        List<T> result = new ArrayList<>();

        try {
            String[] columns = getColumns();
            cursor = db.query(getTableName(), columns, null,
                    null, null, null, null);

            while (cursor.moveToNext()) {
                ContentValues values = new ContentValues(columns.length);
                Helper.cursorRowToContentValues(cursor, values);
                result.add(instantiateEntity(values));
            }
        } finally {
            closeCursor(cursor);
        }

        return Single.just(result);
    }

    private void closeCursor(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }
}
