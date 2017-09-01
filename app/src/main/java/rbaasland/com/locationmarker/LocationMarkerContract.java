package rbaasland.com.locationmarker;

import android.provider.BaseColumns;

public final class LocationMarkerContract {
    public static abstract class LocationMarker implements BaseColumns {
        public static final String TABLE_NAME = "marker";
        public static final String COLUMN_NAME_MARKER_ID = "markerid";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_DATE = "date";
    }
}