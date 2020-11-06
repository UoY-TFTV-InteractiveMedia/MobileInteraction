package uk.ac.york.tftv.im.mi.position;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private LocationManager l_mgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        l_mgr = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        //do we have permission to get location data?
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            startSensor();
        } else {
            ((TextView) findViewById(R.id.dataView)).setText("Asking for permission");
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
    }

    void startSensor(){
        try {

            l_mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    ((TextView) findViewById(R.id.dataView)).setText(
                            "POSITION:\n" +
                                    "LAT: " + String.valueOf(location.getLatitude()) + "\n" +
                                    "LNG: " + String.valueOf(location.getLongitude()) + "\n" +
                                    "ACC: " + String.valueOf(location.getAccuracy()) + "m\n"
                    );
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        } catch (SecurityException e) {
            //user rejected request for permission
            ((TextView) findViewById(R.id.dataView)).setText(e.getLocalizedMessage());
        }
    }

    void onRequestPermissionsResult(int requestCode, String[] permissions, int grantResults) {
        if(grantResults== PackageManager.PERMISSION_GRANTED) {
            //hooray - we have permission, let's start getting data
            startSensor();
        }
        else {
            ((TextView) findViewById(R.id.dataView)).setText("Sorry, location permissions required");
        }
    }

}
