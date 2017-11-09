package com.danilomendes.figurines.data.local;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.danilomendes.figurines.BuildConfig;
import com.danilomendes.figurines.data.entity.IEntity;
import com.danilomendes.figurines.util.Helper;
import com.danilomendes.figurines.util.L;
import com.danilomendes.figurines.util.scheduler.SchedulerProvider;
import com.squareup.sqlbrite2.BriteDatabase;
import com.squareup.sqlbrite2.SqlBrite;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * Created by danilo on 29-10-2017.
 */
abstract class AbstractLocalDataSource<T extends IEntity> implements BaseColumns {

    final BriteDatabase db;

    private static final String SQL_SELECT_QUERY = "SELECT * FROM ?";
    private static final String SQL_SELECT_ORDER_BY_QUERY = SQL_SELECT_QUERY + " ORDER BY ?";

    abstract String getTableName();
    abstract String[] getColumns();
    abstract T instantiateEntity(ContentValues values);

    @SuppressLint("CheckResult")
    AbstractLocalDataSource(DatabaseHelper dbHelper) {
        SqlBrite.Builder builder = new SqlBrite.Builder();
        if (BuildConfig.DEBUG) {
            builder.logger(L::log);
        }
        db = builder.build().wrapDatabaseHelper(dbHelper, SchedulerProvider.Companion.io());
    }

    public void save(List<T> entries) {
        if (Helper.isEmpty(entries)) {
            return;
        }

        BriteDatabase.Transaction transaction = db.newTransaction();
        try {
            for (T entry : entries) {
                db.insert(getTableName(), entry.toContentValues(), SQLiteDatabase.CONFLICT_REPLACE);
            }

            transaction.markSuccessful();
        } finally {
            transaction.end();
        }
    }

    @NonNull
    public Flowable<List<T>> getAll() {
        return db.createQuery(getTableName(), SQL_SELECT_ORDER_BY_QUERY, getTableName(), "")
                .mapToList(this::buildInstanceOfT)
                .toFlowable(BackpressureStrategy.BUFFER);
    }

    private T buildInstanceOfT(Cursor cursor) {
        ContentValues values = new ContentValues();
        Helper.cursorRowToContentValues(cursor, values);
        return instantiateEntity(values);
    }

    private void closeCursor(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }
}
