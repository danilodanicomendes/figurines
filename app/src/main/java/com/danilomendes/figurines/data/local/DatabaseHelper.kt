package com.danilomendes.figurines.data.local

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.danilomendes.figurines.util.L
import javax.inject.Inject

/**
 * Created by danilo on 08-12-2017.
 */
class DatabaseHelper @Inject
constructor(application: Application) : SQLiteOpenHelper(
        application, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        L.log("Database onCreate.")
        db.execSQL(CompanyLocalDataSource.SQL_CREATE)
    }

    override fun onConfigure(db: SQLiteDatabase) {
        db.execSQL("PRAGMA foreign_keys=ON;")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    companion object {

        private val DATABASE_NAME = "figurines.db"

        private val DATABASE_VERSION = 1
    }
}