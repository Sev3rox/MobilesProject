package com.example.smproject.ui.Photos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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

public class PhotosCatsRndFragment  extends Fragment {
    private Button but1;
    private Button but2;
    private Photo pho;
    private ImageView imgview;
    private PhotosCatsRndFragment ss;
    String url = "https://api.thecatapi.com/v1/images/search?breed_ids=";
private String pom;
    public PhotosCatsRndFragment(String pom){this.pom=pom;}
        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            final View root = inflater.inflate(R.layout.fragment_photoscatsrnd, container, false);


            but1 = (Button)root.findViewById(R.id.buttonphotonextcats);
            but2 = (Button)root.findViewById(R.id.buttonphotobackcats);
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



                    ((MainActivity)getActivity()).photoscats();
                }
            });
url+=pom;
            imgview= root.findViewById(R.id.image_photoscats);
            apitext(this);


            return root;}







    public void apitext(final PhotosCatsRndFragment s) {
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());

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



    }

