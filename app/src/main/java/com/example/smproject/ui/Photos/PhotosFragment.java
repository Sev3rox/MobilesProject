package com.example.smproject.ui.Photos;




//?api_key=bd096c4f-f71e-4596-a925-867039182cfb

import android.app.FragmentManager;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.smproject.MainActivity;
import com.example.smproject.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import static androidx.core.content.ContextCompat.getSystemService;

public class PhotosFragment extends Fragment {



    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private SensorEventListener gyroscopeEventListener;

    private PhotosViewModel photosViewModel;
    private Photo pho;
    private ImageView imgview;
    private Button but1;
    private Button but2;
    private Button but3;
    private PhotosFragment ss;
    private int pom;



    public View onCreateView(@NonNull LayoutInflater inflater,
                ViewGroup container, Bundle savedInstanceState) {



            final View root = inflater.inflate(R.layout.fragment_photos, container, false);
            pom=0;



         but1 = (Button)root.findViewById(R.id.buttonphotonext);
        but2 = (Button)root.findViewById(R.id.buttonphotolist);
        but3 = (Button)root.findViewById(R.id.buttonphotocat);
        ss=this;
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apitext(ss);
            }
        });
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



               ((MainActivity)getActivity()).photoslist();
            }
        });
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //https://api.thecatapi.com/v1/images/search?breed_ids=beng
                ((MainActivity)getActivity()).photoscats();
            }
        });
     imgview= root.findViewById(R.id.image_photos);
        apitext(this);

        sensorManager=(SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        gyroscopeSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);


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
                if(pom!=1){
                apitext(ss);
                pom=1;}
                } else if(orientations[2] < -50) {

                } else if(Math.abs(orientations[2]) < 25) {

                    pom=0;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i){

            }
        };





        return root;
    }





    public void apitext(final PhotosFragment s) {
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "https://api.thecatapi.com/v1/images/search";
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            Log.d(">>>>>>>>>>>>>  ", response);
                            JSONArray arr = new JSONArray(response);
                            JSONObject jsonObj=arr.getJSONObject(0);
                            pho=new Photo(jsonObj.getString("id"),jsonObj.getString("url"));
                            Log.d(">>>>>>>>>>>>>  ", pho.getId()+"       "+pho.geturl());
                            Glide.with(s).load(pho.geturl()).into(imgview);
                        }

                        catch(final JSONException e){
                            Log.d(">>>>>>>>>>>>>  ", ""+e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(">>>>>>>>>>>>>  ", "" + error);
                    }

                }

        );
        queue.add(stringRequest);



    }



    @Override
    public void onResume(){
        super.onResume();
        sensorManager.registerListener(gyroscopeEventListener, gyroscopeSensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onPause(){
        super.onPause();
        sensorManager.unregisterListener(gyroscopeEventListener);
    }






}
