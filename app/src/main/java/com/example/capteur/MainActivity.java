package com.example.capteur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView sortie;
    private TextView label;
    private SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    Button buttonSuiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        buttonSuiv = findViewById(R.id.but);

        sortie = findViewById(R.id.list);
        label = findViewById(R.id.label);
        //activité 1
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for(Sensor sensor: deviceSensors){
            show(sensor.getName());
        }

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y= event.values[1];
                //activité 3
                 if(x<-5){
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                    label.setText("droite");
                }
                else if(x>5){
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                    label.setText("gauche");
                }
                else  {
                    getWindow().getDecorView().setBackgroundColor(Color.BLACK);

                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        start();
    }

    //methode pour montrer la list de capteurs
    private void show(String text){

        sortie.append(text + "\n");

    }
    private void start(){
        sensorManager.registerListener(sensorEventListener,sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void stop(){

        sensorManager.unregisterListener(sensorEventListener);

    }

    //Autres activités

    public void Next(View view){
        Intent next = new Intent(this, TwoActivity.class);
        startActivity(next);
    }




}
