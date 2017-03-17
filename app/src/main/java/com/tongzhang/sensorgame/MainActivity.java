package com.tongzhang.sensorgame;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {


    private TextView textViewInfo = null;
    private TextView textViewX = null;
    private TextView textViewY = null;
    private TextView textViewZ = null;
    private SensorManager sensorManager = null;
    private Sensor sensor = null;
    private float gravity[] = new float[3];

    private SoundPool soundPool=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewInfo = (TextView) findViewById(R.id.TextView01);
        textViewX = (TextView) findViewById(R.id.TextView02);
        textViewY = (TextView) findViewById(R.id.TextView03);
        textViewZ = (TextView) findViewById(R.id.TextView04);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        textViewInfo.setText("\nAccelerometer sensor\n" +
                "sensors name:     " + sensor.getName() + "\n" +
                "sensor vendor:    " + sensor.getVendor() + "\n" +
                "sensor power:     " + sensor.getPower()+ "\n");
    }

    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor arg0, int arg1) {

        }
        @Override
        public void onSensorChanged(SensorEvent e) {
            gravity[0] = e.values[0];
            gravity[1] = e.values[1];
            gravity[2] = e.values[2];
            textViewX.setText("X-axis:  " + gravity[0] + "m/s^2\n");
            textViewY.setText("Y-axis:  " + gravity[1] + "m/s^2\n");
            textViewZ.setText("Z-axis:  " + gravity[2] + "m/s^2\n\n");

            if(gravity[0] > 9) {
                textViewInfo.setText("\n重力指向设备左边");
                soundPool= new SoundPool(10, AudioManager.STREAM_SYSTEM,5);
                soundPool.load(MainActivity.this,R.raw.abc,1);
                soundPool.play(1,1, 1, 0, 0, 1);

            } /*else if(gravity[0] < -9) {
                textViewInfo.setText("\n重力指向设备右边");
            } else if(gravity[1] > 9) {
                textViewInfo.setText("\n重力指向设备下边");
            } else if(gravity[1] < -9) {
                textViewInfo.setText("\n重力指向设备上边");
            } else if(gravity[2] > 9) {
                textViewInfo.setText("\n屏幕朝上");
            } else if(gravity[3] < -9) {
                textViewInfo.setText("\n屏幕朝下");
            }*/
        }


    };

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(listener, sensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(listener);
    }

}
