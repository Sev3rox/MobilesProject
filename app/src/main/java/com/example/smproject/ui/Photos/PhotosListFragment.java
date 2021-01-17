package com.example.smproject.ui.Photos;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
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


public class PhotosListFragment extends Fragment implements AdapterView.OnItemClickListener {

    public interface VolleyCallBack {
        void onSuccess(Photo item,View root,PhotosListFragment thiss);
    }

    private PhotosListViewModel photosListViewModel;
private List<Photo> photos;
ListView mylistview;
Photo pho;
String urll;
String[] urls;
    private Button but1;
    private Button but2;
    public PhotosListFragment(){}


        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {

            photosListViewModel =ViewModelProviders.of(this).get(PhotosListViewModel.class);
            View root = inflater.inflate(R.layout.fragment_photoslist, container, false);


            but1 = (Button)root.findViewById(R.id.buttonphotozdjecia);
            but2 = (Button)root.findViewById(R.id.buttonphotocat2);


            but1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)getActivity()).photoszdjecia();
                }
            });
            but2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                  //  Toast toast = Toast.makeText(MainActivity.context, "stmh", Toast.LENGTH_SHORT);
                    // toast.show();
                }
            });


            photos=new ArrayList<Photo>();
            RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
StringRequest pom=apitext(new VolleyCallBack() {
    @Override
    public void onSuccess(Photo item,View root,PhotosListFragment thiss) {
photos.add(item);
if(photos.size()==8){

        mylistview = (ListView) root.findViewById(R.id.list);

        CustomAdapter adapter = new CustomAdapter(getContext(), photos);
        mylistview.setAdapter(adapter);
        mylistview.setOnItemClickListener(thiss);}
    }
},root,this);


            return root;
        }

    public StringRequest apitext(final VolleyCallBack callback,final View root, final PhotosListFragment thiss) {


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
                            urll=jsonObj.getString("url");
                            pho=new Photo(jsonObj.getString("id"),jsonObj.getString("url"));
                            Log.d(">>>>>>>>>>>>>  ", pho.getId()+"       "+pho.geturl());
                            callback.onSuccess(pho,root,thiss);
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
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        for (int i = 0; i < 8; i++) {

                queue.add(stringRequest);


        }

return stringRequest;


    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

    }
    }

