package rbaasland.com.locationmarker.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import rbaasland.com.locationmarker.R;

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
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
                Toast.makeText(getApplicationContext(), "Save Button clicked!", Toast.LENGTH_LONG).show();
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
