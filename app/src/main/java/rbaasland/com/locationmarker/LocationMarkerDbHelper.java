package rbaasland.com.locationmarker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocationMarkerDbHelper extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + LocationMarkerContract.LocationMarker.TABLE_NAME + " (" +
                    LocationMarkerContract.LocationMarker.COLUMN_NAME_MARKER_ID + " INTEGER PRIMARY KEY," +
                    LocationMarkerContract.LocationMarker.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    LocationMarkerContract.LocationMarker.COLUMN_NAME_LATITUDE + TEXT_TYPE + COMMA_SEP +
                    LocationMarkerContract.LocationMarker.COLUMN_NAME_LONGITUDE + TEXT_TYPE + COMMA_SEP +
                    LocationMarkerContract.LocationMarker.COLUMN_NAME_DATE + TEXT_TYPE  +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LocationMarkerContract.LocationMarker.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "LocationMarker.db";

    public LocationMarkerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public Cursor getAllRows(SQLiteDatabase db){
        String where = null;
        String whereArgs[] = null;
        String groupBy = null;
        String having = null;
        String order = null;
        String limit = null;

        Cursor cursor = db.query(LocationMarkerContract.LocationMarker.TABLE_NAME, null, where, whereArgs, groupBy, having, order, limit);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }
}