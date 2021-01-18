package com.example.smproject.ui.Facts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.bumptech.glide.Glide;
import com.example.smproject.MainActivity;
import com.example.smproject.R;
import com.example.smproject.ui.Facts.FactsViewModel;
import com.example.smproject.ui.Photos.Photo;
import com.example.smproject.ui.Photos.PhotosFragment;
import com.example.smproject.ui.Photos.PhotosViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FactsFragment extends Fragment {
    private PhotosViewModel photosViewModel;
    private Fact fact;
    private TextView textView;
    private Button but1;
    private Button but2;
    private Button but3;
    private FactsFragment ss;
    private int pom;
    private FactsViewModel factsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

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
}