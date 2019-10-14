package uk.ac.york.tftv.im.mi.practical2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowLight extends AppCompatActivity implements SensorEventListener {
    private Sensor light;
    private SensorManager sensor_mgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_light);

        sensor_mgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        light = sensor_mgr.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensor_mgr.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);

    }

    public void buttonPressed(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        TextView tv = (TextView) findViewById(R.id.lightValue);
        tv.setText(String.valueOf(sensorEvent.values[0]));
        //Challenge 3
        int darkness = 255 - (int)sensorEvent.values[0];
        if(darkness<0) darkness=0;
        tv.setTextColor(Color.argb(255,darkness,darkness,darkness));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {//Challenge 2
        //we no longer need the sensor data since we are not in focus.
        super.onPause();
        sensor_mgr.unregisterListener(this);
    }
    @Override
    protected void onResume() {//Challenge 2
        //the user has come back! start listening again.
        super.onResume();
        sensor_mgr.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
    }

}
