package rbaasland.com.locationmarker.activities;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import rbaasland.com.locationmarker.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    // Default latitude for the map
    private double mLatitude = 38.3887885;

    // Default longitude for the map
    private double mLongitude = -93.5316315;

    // The default zoom on the map
    private float mZoom = 18.0f;

    // The minimum zoom the map is allowed to render
    private float mMaxZoom = 21.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 11);
            return;
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        LocationManager locationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria cri = new Criteria();
        cri.setAccuracy(Criteria.ACCURACY_COARSE);
        String provider = locationmanager.getBestProvider(cri, false);

        if (provider != null & !provider.equals("")) {
            LocationListener locationChangeListener = new LocationListener() {
                public void onLocationChanged(Location l) {
                    if (l != null) {
                        mLatitude = l.getLatitude();
                        mLongitude = l.getLongitude();
                        mZoom = mMaxZoom;
                    }
                }
                public void onProviderEnabled(String p) {}
                public void onProviderDisabled(String p) {}
                public void onStatusChanged(String p, int status, Bundle extras) {}
            };

            Location location = locationmanager.getLastKnownLocation(provider);
            locationmanager.requestLocationUpdates(provider, 2000, 1, locationChangeListener);
            if (location != null) {
                mLatitude = location.getLatitude();
                mLongitude = location.getLongitude();
                mZoom = mMaxZoom;
            }
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Move the camera to either the default location, or the users current location
        LatLng position = new LatLng(mLatitude, mLongitude);
        mMap.moveCamera(CameraUpdateFactory.zoomTo(mZoom));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        mMap.addMarker(new MarkerOptions()
                .position(position)
                .title("Marker Dropped")
        );
    }
}
