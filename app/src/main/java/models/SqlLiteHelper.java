package models;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SqlLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_MARKERS = "markers";
    public static final String COLUMN_ID = "marker_id";
    public static final String COLUMN_DESCRIPTION = "marker_description";
    public static final String COLUMN_LATITUDE = "marker_latitude";
    public static final String COLUMN_LONGITUDE = "marker_longitude";

    private static final String DATABASE_NAME = "markers.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table "
            + TABLE_MARKERS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_DESCRIPTION
            + " text not null, " + COLUMN_LATITUDE + "double not null, "
            + COLUMN_LONGITUDE + "double not null);";

    public SqlLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SqlLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MARKERS);
        onCreate(db);
    }

}
