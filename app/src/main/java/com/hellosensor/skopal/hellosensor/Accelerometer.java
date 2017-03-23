package com.hellosensor.skopal.hellosensor;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Accelerometer extends AppCompatActivity implements SensorEventListener {

    Sensor accelerometer;
    SensorManager sensorManager;
    TextView acceleration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        acceleration = (TextView)findViewById(R.id.textView);
    }

    public void onSensorChanged(SensorEvent event) {
        // alpha is calculated as t / (t + dT)
        // with t, the low-pass filter's time-constant
        // and dT, the event delivery rate

        final double alpha = 0.8;
        double gravity[] = {0,0,0};
        double linear_acceleration[] = {0,0,0};

        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];

        acceleration.setText("X: "+ linear_acceleration[0] + "\nY: "+ linear_acceleration[1] + "\nZ: " +  linear_acceleration[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onBackPressed(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        this.finish();
    }
}
