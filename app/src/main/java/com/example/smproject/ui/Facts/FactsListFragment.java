package com.example.smproject.ui.Facts;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FactsListFragment extends Fragment implements AdapterView.OnItemClickListener {

    public interface VolleyCallBack {
        void onSuccess(String item,View root,FactsListFragment thiss);
    }

    private List<String> photos;
    ListView mylistview;
    String pho;
    String urll;
    String[] urls;
    String url;
    private Button but1;
    private Button but2;
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private SensorEventListener lightEventListener;
    private float light;
    public FactsListFragment(){}


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_catslist, container, false);


        but1 = (Button)root.findViewById(R.id.buttonfactcat);


        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).facts();
            }
        });
        sensorManager=(SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        lightSensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);


        lightEventListener=new SensorEventListener(){
            @Override
            public void onSensorChanged(SensorEvent sensorEvent){
                light=sensorEvent.values[0];
              //  TextView view=(TextView)root.findViewById(R.id.tekst2);
               // Log.d(">>>>>>>>>>>>>  ", view.toString());
                //int colorCode=(int) view.getTag();
             //   Log.d(">>>>>>>>>>>>>  ", Float.toString(light));
                Log.d(">>>>>>>>>>>>>  ", ""+light);
                            /*if(light>126&&(((TextView)root.findViewById(R.id.tekst2)).getContext().getResources()).getColor(R.color.colorText)!=colorCode)
                            {
                                ((TextView)root.findViewById(R.id.tekst2)).setBackgroundColor((((TextView)root.findViewById(R.id.tekst2)).getContext().getResources()).getColor(R.color.colorText));
                                ((TextView)root.findViewById(R.id.tekst2)).setTag(((((TextView)root.findViewById(R.id.tekst2)).getContext().getResources()).getColor(R.color.colorText)));
                            }
                            else if(light<126&&(((TextView)root.findViewById(R.id.tekst2)).getContext().getResources()).getColor(R.color.colorText)==colorCode)
                            {
                                ((TextView)root.findViewById(R.id.tekst2)).setBackgroundColor((((TextView)root.findViewById(R.id.tekst2)).getContext().getResources()).getColor(R.color.colorPrimary));
                                ((TextView)root.findViewById(R.id.tekst2)).setTag(((((TextView)root.findViewById(R.id.tekst2)).getContext().getResources()).getColor(R.color.colorPrimary)));
                            }*/
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i){

            }
        };
        url = "https://catfact.ninja/fact\n";

        photos=new ArrayList<String>();
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest pom=apitext(new VolleyCallBack() {
            @Override
            public void onSuccess(String item, final View root, FactsListFragment thiss) {
                photos.add(item);

                if(photos.size()==8){

                    mylistview = (ListView) root.findViewById(R.id.list2);

                    //CustomAdapter adapter = new CustomAdapter(getContext(), photos);
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),R.layout.factlist_item,photos);
                    mylistview.setAdapter(adapter);
                    //mylistview.setAdapter(adapter);
                    mylistview.setOnItemClickListener(thiss);


                    //Log.d(">>>>>>>>>",((TextView)root.findViewById(R.id.tekst2)).getBackground().toString());
                }
            }
        },root,this);
        return root;
    }

    public StringRequest apitext(final VolleyCallBack callback,final View root, final FactsListFragment thiss) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                           // Log.d(">>>>>>>>>>>>>  ", response);
                            //JSONArray arr = new JSONArray(response);
                            JSONObject jsonObj=new JSONObject(response);
                            String fact=new String(jsonObj.getString("fact"));
                            //Log.d(">>>>>>>>>>>>>  ", pho.getId()+"       "+pho.geturl());

                            callback.onSuccess(fact,root,thiss);
                        }

                        catch(final JSONException e){
                           // Log.d(">>>>>>>>>>>>>  ", ""+e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Log.d(">>>>>>>>>>>>>  ", "" + error);
                    }

                }

        );
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        for (int i = 0; i < 8; i++) {

            queue.add(stringRequest);


        }

        return stringRequest;


    }
    @Override
    public void onResume(){
        super.onResume();
 {
            sensorManager.registerListener(lightEventListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPause(){
        super.onPause();
       {
            sensorManager.unregisterListener(lightEventListener);
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

    }
}

