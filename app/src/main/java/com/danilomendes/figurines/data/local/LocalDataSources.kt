package com.danilomendes.figurines.data.local

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.danilomendes.figurines.data.entity.Company
import com.danilomendes.figurines.data.entity.IEntity
import com.danilomendes.figurines.util.L
import com.danilomendes.figurines.util.rowToContentValues
import com.danilomendes.figurines.util.scheduler.SchedulerProvider
import com.squareup.sqlbrite2.BriteDatabase
import com.squareup.sqlbrite2.BuildConfig
import com.squareup.sqlbrite2.SqlBrite
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

/**
 * Created by danilo on 08-12-2017.
 */
abstract class AbstractLocalDataSource<T : IEntity>(
        dbHelper: DatabaseHelper) : BaseColumns {

    private val SQL_SELECT_QUERY = "SELECT * FROM "

    protected abstract fun getTableName(): String
    protected abstract fun getColumns(): Array<String>
    protected abstract fun instantiateEntity(values: ContentValues): T

    private val db: BriteDatabase = SqlBrite.Builder().logger(L::log).build()
            .wrapDatabaseHelper(dbHelper, SchedulerProvider.io())

    init {
        // Enable log if in debug.
        db.setLoggingEnabled(BuildConfig.DEBUG)
    }

    fun save(entries: List<T>) {
        val transaction = db.newTransaction()
        try {
            for (entry in entries) {
                db.insert(getTableName(), entry.toContentValues(), SQLiteDatabase.CONFLICT_REPLACE)
            }

            transaction.markSuccessful()
        } finally {
            transaction.end()
        }
    }

    fun getAll(): Single<List<T>> {
        val cursor = db.query(SQL_SELECT_QUERY + getTableName())
        val result = ArrayList<T>()

        try {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    result.add(buildInstanceOfT(cursor))
                }
            }
        } finally {
            closeCursor(cursor)
        }

        return Single.just(result)
    }

    private fun buildInstanceOfT(cursor: Cursor): T {
        val values = ContentValues(cursor.columnCount)
        cursor.rowToContentValues(values)
        return instantiateEntity(values)
    }

    private fun closeCursor(cursor: Cursor?) {
        if (cursor != null && !cursor.isClosed) {
            cursor.close()
        }
    }
}

class CompanyLocalDataSource @Inject
constructor(dbHelper: DatabaseHelper) : AbstractLocalDataSource<Company>(dbHelper) {

    protected override fun getTableName(): String {
        return TABLE_NAME
    }

    protected override fun getColumns(): Array<String> {
        return arrayOf(_CODE_NAME, _NAME, _ADDRESS, _POSTAL_CODE, _CITY, _LOGO, _INSET_COLOR,
                _SHORT_DESCRIPTION, _COORDINATE_LATITUDE, _COORDINATE_LONGITUDE)
    }

    protected override fun instantiateEntity(values: ContentValues): Company {
        return Company(values)
    }

    companion object {
        internal val TABLE_NAME = "company"

        val _CODE_NAME = "code_name"
        val _NAME = "name"
        val _ADDRESS = "address"
        val _POSTAL_CODE = "postal_code"
        val _CITY = "city"
        val _LOGO = "logo"
        val _INSET_COLOR = "inset_color"
        val _SHORT_DESCRIPTION = "short_description"
        val _COORDINATE_LATITUDE = "coordinate_latitude"
        val _COORDINATE_LONGITUDE = "coordinate_longitude"

        internal val SQL_CREATE = ("CREATE TABLE " + TABLE_NAME + " ("
                + _CODE_NAME + " TEXT PRIMARY KEY, "
                + _NAME + " TEXT, "
                + _ADDRESS + " TEXT, "
                + _POSTAL_CODE + " TEXT, "
                + _CITY + " TEXT, "
                + _LOGO + " TEXT, "
                + _INSET_COLOR + " TEXT, "
                + _SHORT_DESCRIPTION + " TEXT, "
                + _COORDINATE_LATITUDE + " TEXT,"
                + _COORDINATE_LONGITUDE + " TEXT);")
    }
}