package rbaasland.com.locationmarker;

import java.util.Date;

public class Location{
    private long mLocationId;
    public double mLongitude;
    public double mLatitude;
    public String mDescription;
    public Date mDate;

    public long getmLocationId() {
        return mLocationId;
    }

    public void setmLocationId(long mLocationId) {
        this.mLocationId = mLocationId;
    }
    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDateCurrent() {
        mDate = new Date();
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
