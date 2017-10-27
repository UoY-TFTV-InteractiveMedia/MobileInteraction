package uk.ac.york.tftv.im.mi.position;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends Activity {
    private LocationManager l_mgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l_mgr = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        try {
            l_mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    ((TextView) findViewById(R.id.updateTextView)).setText(
                            "POSITION:\n"+
                            "LAT: "+ String.valueOf(location.getLatitude())+"\n"+
                            "LNG: "+ String.valueOf(location.getLongitude())+"\n"+
                            "ACC: "+ String.valueOf(location.getAccuracy())+"m\n"
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
        }
        catch (SecurityException e) {
            //user rejected request for permission
            ((TextView) findViewById(R.id.updateTextView)).setText(e.getLocalizedMessage());
        }
    }

}
