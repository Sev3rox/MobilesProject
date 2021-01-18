package com.example.smproject.ui.Facts;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.smproject.MainActivity;
import com.example.smproject.R;
import com.example.smproject.sqlite.DatabaseHandler;
import com.example.smproject.sqlite.setings;
import com.example.smproject.ui.Facts.FactsViewModel;
import com.example.smproject.ui.Photos.Photo;
import com.example.smproject.ui.Photos.PhotosFragment;
import com.example.smproject.ui.Photos.PhotosViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class FactsFragment extends Fragment {
    private PhotosViewModel photosViewModel;
    private Fact fact;
    private TextView textView;
    private Button but1;
    private Button but2;
    private Button but3;
    private FactsFragment ss;
    private int pom;
    private Sensor lightSensor;
    private SensorEventListener lightEventListener;
    private float light;
    private boolean booll=true;
    private SensorManager sensorManager;
    private FactsViewModel factsViewModel;
    setings pom2;
    DatabaseHandler db = new DatabaseHandler(MainActivity.context);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*DatabaseHandler db = new DatabaseHandler(MainActivity.context);
        List<setings> setings = db.getAllSetings();
        setings pom2=setings.get(0);
        //pom2.setName(pom);
        db.updateSetings(pom2);

        for (setings se : setings) {
            String log = "Id: " + se.getID() + " ,Name: " + se.getName() + " ,Mode: " +
                    se.getMode() + " ,Mode2: " + se.getMode2();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }*/
        factsViewModel =
                ViewModelProviders.of(this).get(FactsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_facts, container, false);
        factsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        but1 = (Button)root.findViewById(R.id.buttonfactlist);
        but2 = (Button)root.findViewById(R.id.buttonfactnext);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apitext(ss);
            }
        });
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).factslist();
            }
        });
        sensorManager=(SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        lightSensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);


        lightEventListener=new SensorEventListener(){
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                light = sensorEvent.values[0];
                ConstraintLayout l=(ConstraintLayout) root.findViewById(R.id.bckgc);
                if(light>20000&&booll==true)
                {booll=false;
                    l.setBackgroundResource(R.drawable.cats1);
                }
                else if(light<20000&&booll==false)
                {booll=true;
                    l.setBackgroundResource(R.drawable.cats2);
                }

            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i){

            }
        };
        ss=this;
        textView= root.findViewById(R.id.text_facts);
        apitext(this);

        return root;
    }
    public void apitext(final FactsFragment s) {
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "https://catfact.ninja/fact\n";
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            Log.d(">>>>>>>>>>>>>  ", response);
                            //JSONArray arr = new JSONArray(response);
                            JSONObject jsonObj=new JSONObject(response);
                            fact=new Fact(jsonObj.getString("fact"));
                            textView.setText(fact.text);
                            Log.d(">>>>>>>>>>>>>  ", fact.text);
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
        List<setings> setings = db.getAllSetings();
        pom2=setings.get(0);
        if(pom2.getMode2()==1)
            sensorManager.registerListener(lightEventListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onPause(){
        super.onPause();
        List<setings> setings = db.getAllSetings();
        pom2=setings.get(0);
        if(pom2.getMode2()==1)
            sensorManager.unregisterListener(lightEventListener);

    }
}