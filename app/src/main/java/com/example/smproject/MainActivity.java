package com.example.smproject;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smproject.sqlite.DatabaseHandler;
import com.example.smproject.sqlite.setings;
import com.example.smproject.ui.Photos.Photo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private SensorEventListener gyroscopeEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        if (gyroscopeSensor == null) {

            Toast.makeText(this,"The device doest have gyroscope", Toast.LENGTH_SHORT).show();
            finish();
        }

        gyroscopeEventListener=new SensorEventListener(){
            @Override
            public void onSensorChanged(SensorEvent sensorEvent){
                float[] rotationMatrix = new float[16];
                SensorManager.getRotationMatrixFromVector(
                        rotationMatrix, sensorEvent.values);
                // Remap coordinate system
                float[] remappedRotationMatrix = new float[16];
                SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_X,
                        SensorManager.AXIS_Z,
                        remappedRotationMatrix);
                // Convert to orientations
                float[] orientations = new float[3];
                SensorManager.getOrientation(remappedRotationMatrix, orientations);
                for(int i = 0; i < 3; i++) {
                    orientations[i] = (float)(Math.toDegrees(orientations[i]));
                }
// Convert to orientations

                if(orientations[2] > 50) {
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                } else if(orientations[2] < -50) {
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                } else if(Math.abs(orientations[2]) < 25) {
                    getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i){

            }
        };



        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_photos, R.id.navigation_facts, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        DatabaseHandler db = new DatabaseHandler(this);

        // Inserting Contacts
      /*  Log.d("Insert: ", "Inserting ..");
        db.addSetings(new setings("Ravi", 999));
        db.addSetings(new setings("Srinivas", 900));
        db.addSetings(new setings("Tommy", 990));
        db.addSetings(new setings("Karthik", 9999));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");*/
        List<setings> setings = db.getAllSetings();

        for (setings se : setings) {
            String log = "Id: " + se.getID() + " ,Name: " + se.getName() + " ,Mode: " +
                    se.getMode();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }


    }


    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(gyroscopeEventListener, gyroscopeSensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(gyroscopeEventListener);
    }




    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }




}

