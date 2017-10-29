package com.danilomendes.figurines.model;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by danilo on 29-10-2017.
 */
public final class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "figurines.db";

    private static final int DATABASE_VERSION = 1;

    private final Map<String, AbstractTable> mTables;

    private final SQLiteDatabase mDatabase;

    @Inject
    public DatabaseHelper(Application application) {
        super(application, DATABASE_NAME, null, DATABASE_VERSION);

        mTables = new HashMap<>(1);
        mTables.put(CompanyTable.TABLE_NAME, new CompanyTable());

        mDatabase = getWritableDatabase();

        for (AbstractTable table : mTables.values()) {
            table.setDb(mDatabase);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (AbstractTable table : mTables.values()) {
            table.onCreate(db);
        }
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    CompanyTable getCompanyTable() {
        return (CompanyTable) mTables.get(CompanyTable.TABLE_NAME);
    }
}
