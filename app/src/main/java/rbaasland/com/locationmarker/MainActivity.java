package rbaasland.com.locationmarker;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        setContentView(R.layout.activity_main);

        Button saveLocationBtn = (Button) findViewById(R.id.save_loc_btn);
        saveLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "SAve Button clicked!", Toast.LENGTH_LONG).show();
            LocationMarkerDbHelper mDbHelper = new LocationMarkerDbHelper(MainActivity.this);

            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(LocationMarkerContract.LocationMarker.COLUMN_NAME_DESCRIPTION, "TEST-Description");
            values.put(LocationMarkerContract.LocationMarker.COLUMN_NAME_LATITUDE, "-10.0");
            values.put(LocationMarkerContract.LocationMarker.COLUMN_NAME_LONGITUDE, "20.0");
            values.put(LocationMarkerContract.LocationMarker.COLUMN_NAME_DATE, "TEST-Date");

            long newRowId = db.insert(
                LocationMarkerContract.LocationMarker.TABLE_NAME,
                null,
                values
            );

            SQLiteDatabase db2 = mDbHelper.getReadableDatabase();

            String[] projection = {
                    LocationMarkerContract.LocationMarker.COLUMN_NAME_MARKER_ID,
                    LocationMarkerContract.LocationMarker.COLUMN_NAME_LATITUDE,
                    LocationMarkerContract.LocationMarker.COLUMN_NAME_LONGITUDE,
                    LocationMarkerContract.LocationMarker.COLUMN_NAME_DESCRIPTION,
                    LocationMarkerContract.LocationMarker.COLUMN_NAME_DATE
            };

            String[] selection = {
                    LocationMarkerContract.LocationMarker.COLUMN_NAME_MARKER_ID,
                    LocationMarkerContract.LocationMarker.COLUMN_NAME_LATITUDE,
                    LocationMarkerContract.LocationMarker.COLUMN_NAME_LONGITUDE,
                    LocationMarkerContract.LocationMarker.COLUMN_NAME_DESCRIPTION,
                    LocationMarkerContract.LocationMarker.COLUMN_NAME_DATE
            };

            // How you want the results sorted in the resulting Cursor
            String sortOrder =
                    LocationMarkerContract.LocationMarker.COLUMN_NAME_MARKER_ID + " DESC";

            Cursor cursor = db2.query(
                    LocationMarkerContract.LocationMarker.TABLE_NAME,                     // The table to query
                    projection,                               // The columns to return
                    null,                                       // The columns for the WHERE clause
                    null,                                       // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    sortOrder                                 // The sort order
            );

            ArrayList<Location> listOfMarkers = new ArrayList<Location>();

            while(cursor.moveToNext()) {
                Location cursorLocation = new Location();
                long itemId = cursor.getLong(
                        cursor.getColumnIndexOrThrow(LocationMarkerContract.LocationMarker.COLUMN_NAME_MARKER_ID));

                String longitude = cursor.getString(
                        cursor.getColumnIndexOrThrow(LocationMarkerContract.LocationMarker.COLUMN_NAME_LONGITUDE));

                String latitude = cursor.getString(
                        cursor.getColumnIndexOrThrow(LocationMarkerContract.LocationMarker.COLUMN_NAME_LATITUDE));

                String description = cursor.getString(
                        cursor.getColumnIndexOrThrow(LocationMarkerContract.LocationMarker.COLUMN_NAME_DESCRIPTION));

                String date = cursor.getString(
                        cursor.getColumnIndexOrThrow(LocationMarkerContract.LocationMarker.COLUMN_NAME_DATE));

                cursorLocation.setmLocationId(itemId);
                cursorLocation.setLatitude(-10.00);
                cursorLocation.setLongitude(20.01);
                cursorLocation.setDescription(description);

                listOfMarkers.add(cursorLocation);
            }
            cursor.close();
            }
        });

        Button viewLocationsBtn = (Button) findViewById(R.id.view_loc_btn);
        viewLocationsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LocationsActivity.class));
                Toast.makeText(getApplicationContext(), "View Button clicked!", Toast.LENGTH_LONG).show();
            }
        });
    }
}