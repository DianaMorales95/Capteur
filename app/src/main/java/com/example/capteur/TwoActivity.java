package com.example.capteur;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;

public class TwoActivity extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    ImageView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0] < sensor.getMaximumRange()){
                    view.setImageResource(R.drawable.chatproche);
                }else{
                    view.setImageResource(R.drawable.catloin);

                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        start();

    }

    public void start (){
        sensorManager.registerListener(sensorEventListener, sensor, 2000*1000);
    }

    private void stop(){
        sensorManager.unregisterListener(sensorEventListener);
   }

    @Override
    protected void onPause() {
        stop();
        super.onPause();
    }

    @Override
    protected void onPostResume() {
        start();
        super.onPostResume();
    }
}
