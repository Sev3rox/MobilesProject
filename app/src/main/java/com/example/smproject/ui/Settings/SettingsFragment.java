package com.example.smproject.ui.Settings;

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
import com.bumptech.glide.Glide;
import com.example.smproject.MainActivity;
import com.example.smproject.R;
import com.example.smproject.sqlite.DatabaseHandler;
import com.example.smproject.sqlite.setings;
import com.example.smproject.ui.Photos.Photo;
import com.example.smproject.ui.Photos.PhotosFragment;
import com.example.smproject.ui.Settings.SettingsViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SettingsFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        String url=null;
        DatabaseHandler db = new DatabaseHandler(MainActivity.context);
        List<setings> setings = db.getAllSetings();
        setings pom2=setings.get(0);
        url=pom2.getName();
        if(url!=null){
        ImageView imgview= root.findViewById(R.id.image_photosset);
        Glide.with(this).load(url).into(imgview);}
        return root;
    }


}
