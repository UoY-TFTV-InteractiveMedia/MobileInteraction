package uk.ac.york.tftv.im.mi.week6_sensordemos;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private SensorManager s_mgr;
    private Sensor s;
    private SensorEventListener s_listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void buttonPressed(View v) {
        ((Button)findViewById(R.id.button)).setEnabled(false);//disable button

        s_mgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        s = s_mgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        s_mgr.registerListener(s_listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                /*
                Guide to the format of sensor data is here:
                https://developer.android.com/guide/topics/sensors/sensors_motion.html#sensors-motion-grav
                For accelerometer, values[0] is X, values[1] is Y and 2 is Z
                 */
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                ((TextView) findViewById(R.id.dataView)).setText("ACCELEROMETER\n"+
                        "X:" + String.valueOf(x) + "\n" +
                        "Y:" + String.valueOf(y) + "\n" +
                        "Z:" + String.valueOf(z));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },s,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(s_listener!=null)
            s_mgr.unregisterListener(s_listener); //NB it is important to unregister the listener or it will continue running, draining battery
    }
}
