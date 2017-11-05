package com.danilomendes.figurines.data.local;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.danilomendes.figurines.utils.L;

import javax.inject.Inject;

/**
 * Created by danilo on 29-10-2017.
 */
public final class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "figurines.db";

    private static final int DATABASE_VERSION = 1;

    @Inject
    DatabaseHelper(Application application) {
        super(application, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        L.log("danilo: onCreate database.");
        db.execSQL(CompanyLocalDataSource.SQL_CREATE);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
