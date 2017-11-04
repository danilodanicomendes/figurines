package com.danilomendes.figurines.model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.danilomendes.figurines.model.entity.Company;
import com.danilomendes.figurines.utils.Helper;

import java.util.List;

/**
 * Created by danilo on 29-10-2017.
 */
public final class CompanyTable extends AbstractTable<Company> {
    static final String TABLE_NAME = "company";

    public static final String _CODE_NAME = "code_name";
    public static final String _NAME = "name";
    public static final String _ADDRESS = "address";
    public static final String _POSTAL_CODE = "postal_code";
    public static final String _CITY = "city";
    public static final String _LOGO = "logo";
    public static final String _INSET_COLOR = "inset_color";
    public static final String _SHORT_DESCRIPTION = "short_description";
    public static final String _COORDINATE_LATITUDE = "coordinate_latitude";
    public static final String _COORDINATE_LONGITUDE = "coordinate_longitude";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + " ("
                        + _CODE_NAME + " TEXT PRIMARY KEY, "
                        + _NAME + " TEXT, "
                        + _ADDRESS + " TEXT, "
                        + _POSTAL_CODE + " TEXT, "
                        + _CITY + " TEXT, "
                        + _LOGO + " TEXT, "
                        + _INSET_COLOR + " TEXT, "
                        + _SHORT_DESCRIPTION + " TEXT, "
                        + _COORDINATE_LATITUDE + " TEXT,"
                        + _COORDINATE_LONGITUDE + " TEXT);"
        );
    }

    @Override
    String getTableName() {
        return TABLE_NAME;
    }

    @NonNull
    @Override
    String[] getColumns() {
        return new String[]{_CODE_NAME, _NAME, _ADDRESS, _POSTAL_CODE, _CITY,
                _LOGO,_INSET_COLOR, _SHORT_DESCRIPTION, _COORDINATE_LATITUDE,
                _COORDINATE_LONGITUDE};
    }

    @Override
    Company instantiateEntity(ContentValues values) {
        return new Company(values);
    }

    void deleteAllBut(List<Company> entries) {
        if (Helper.isEmpty(entries)) {
            return;
        }

        StringBuilder inQuery = new StringBuilder(entries.size());
        for (int i = 0; i < entries.size(); i++) {
            inQuery.append('\'').append(entries.get(i).getCodeName()).append('\'');

            if (i < entries.size() - 1) {
                inQuery.append(',');
            }
        }

        db.delete(getTableName(), _CODE_NAME + " NOT IN ("
                + inQuery.toString() + ")", null);
    }
}
