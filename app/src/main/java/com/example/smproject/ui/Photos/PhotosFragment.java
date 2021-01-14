package com.example.smproject.ui.Photos;




//?api_key=bd096c4f-f71e-4596-a925-867039182cfb

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class PhotosFragment extends Fragment {





    private PhotosViewModel photosViewModel;
    private String url;
    private Photo pho;
    private Bitmap bmp;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        photosViewModel =
                ViewModelProviders.of(this).get(PhotosViewModel.class);
        apitext();
        View root = inflater.inflate(R.layout.fragment_photos, container, false);
        final TextView textView = root.findViewById(R.id.text_photos);
        final ImageView imgview=root.findViewById(R.id.image_photos);
        try {
            bmp=photosViewModel.getImage(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        photosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
               imgview.setImageBitmap(bmp);

            }
        });

        return root;
    }
    public void apitext() {
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
