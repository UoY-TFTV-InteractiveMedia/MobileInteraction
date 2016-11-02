package uk.ac.york.tftv.im.mi.week6sensorlistenerdemo;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{ //Our class subclasses the Activity class, but also implements the abstract Interface SensorEventListener
    private SensorManager s_mgr;
    private Sensor s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s_mgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        s = s_mgr.getDefaultSensor(Sensor.TYPE_LIGHT); //Also try, e.g. Sensor.TYPE_PROXIMITY
        s_mgr.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL); //Here we tell the SensorManager that this class (MainActivity) will be listening for sensor events (see next)
    }

    /*
    * onAccuracyChanged and onSensorChanged are the two functions that all SensorEventListener classes must implement.
    * the sensor manager will send all events and updates to these methods.
    * */

    public void onAccuracyChanged(Sensor sensor, int value) {
        /**
         * The sensor is telling us the accuracy of the sensor has changed. This is usually HIGH MEDIUM or LOW. For example the location sensor might start off giving low accuracy data, but the longer it is enabled, the higher the accuracy reported.
         */

    }

    public void onSensorChanged(SensorEvent event) {
        /**
         * The sensor has contacted us to tell us the sensor data (in event)
         */
        ((TextView)findViewById(R.id.textView)).setText("SENSOR DATA:\n" +
                String.valueOf(event.values[0]));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        s_mgr.unregisterListener(this); //NB it is important to unregister the listener or it will continue running, draining battery
    }
}
