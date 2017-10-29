package com.danilomendes.figurines.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.danilomendes.figurines.model.entity.IEntity;
import com.danilomendes.figurines.utils.Helper;
import com.danilomendes.figurines.utils.L;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by danilo on 29-10-2017.
 */
abstract class AbstractTable<T extends IEntity> implements BaseColumns {

    private SQLiteDatabase db;

    abstract void onCreate(SQLiteDatabase db);

    abstract String getTableName();
    abstract String[] getColumns();

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    void insertAll(List<T> entries) {
        if (Helper.isEmpty(entries)) {
            return;
        }

        db.beginTransaction();
        try {
            for (T entry : entries) {
                db.insert(getTableName(), null, entry.toContentValues());
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
                result.add(getInstanceOfT(values));
            }
        } finally {
            closeCursor(cursor);
        }

        return Single.just(result);
    }

    @SuppressWarnings("unchecked")
    private T getInstanceOfT(ContentValues values) {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> tClass = (Class<T>) type.getActualTypeArguments()[0];

        try {
            return tClass.getConstructor(ContentValues.class).newInstance(values);
        } catch (InstantiationException | IllegalAccessException
                | InvocationTargetException | NoSuchMethodException e) {
            L.log("Error instantiating T " + tClass.getSimpleName(), e);
            throw new RuntimeException(tClass.getSimpleName()
                    + " must have a constructor with ContentValues.");
        }
    }

    private void closeCursor(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }
}
